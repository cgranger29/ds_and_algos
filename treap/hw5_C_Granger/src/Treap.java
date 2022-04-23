import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

class Treap<E extends Comparable<E>>{
    
    private Random priorityGenerator;
    private Node<E> root;
    
    public Treap(){
        //default seed value will just be 1000
        this.priorityGenerator = new Random(10000);
        this.root = null;
    }

    public Treap(long seed){
        this.priorityGenerator = new Random(seed);;
        this.root = null;
    }

    public boolean add(E key){

        // this just calls add(E key, int priority) and passes in a default priority generation
        //bounding it to 10000, can be modified if you want a greater rng factor and less chance of collision

        int add_priority = this.priorityGenerator.nextInt(10000);

        return this.add(key, add_priority);

    }

    public boolean add(E key, int priority){
        Node<E> add_node = new Node(key, priority);

        this.root = recursiveAdd(this.root, add_node, priority);

        return true;

    }

    public Node<E> recursiveAdd(Node<E> root, Node<E> add_node, int priority){
        //recursive helper function. Much easier than an iterative solution. No need for reheap helper either
        if(root == null){
            return add_node;
        }
        
        if(add_node.data.compareTo(root.data) > 0){
            root.right = recursiveAdd(root.right, add_node, priority);
            // if root.right isnt null then check right node with root priority.
            // THIS IS A LEFT ROTATION IF TRUE
            if(root.right != null && root.right.priority > root.priority){
                root = root.rotateLeft();
            }
        } else if (add_node.data.compareTo(root.data) < 0){
            root.left = recursiveAdd(root.left, add_node, priority);
            // almost identical logic to right check but now with the left
            // if root.left isnt null then check left node with root priority.
            // THIS IS A RIGHT ROTATION IF TRUE
            if(root.left != null && root.left.priority > root.priority){
                root = root.rotateRight();
            }
        } else{
            // return root if neither of above
            return root;
        }
        // return the root at end of every recursive call
        return root;
    }
    public void reheap(Stack<Node<E>> path, Node<E> root){

        // not needed in recursive solution, left it in anyway
        while(path.size() != 0){
            Node<E> temp_root = path.pop();
            if (temp_root.right != null && temp_root.right.priority > temp_root.priority){
                //left rotation
                System.out.println("need to rotate left");
                root = temp_root.rotateLeft();
                // temp_root = temp_root.rotateLeft();
            } else if(temp_root.left != null && temp_root.left.priority > temp_root.priority){
                //right rotation
                System.out.println("need to rotate right");
                root = temp_root.rotateRight();
            }
        }
    }

    public boolean delete(E key){
        //adding helper with root parameter so we can recursively solve this.
        // a priority has to be passed in but since we are just initalizing it for comparison we will just pass in 0
        
        //adding a find for initial key check
        if (this.find(key) == false){
            return false;
        } 
        Node<E> nodeToDelete = new Node(key, 0);
        Node<E> recursiveTreapReturn = recursiveDelete(root, nodeToDelete);
        this.root = recursiveTreapReturn;
        return true;
    }

    public Node<E> recursiveDelete(Node<E> root, Node<E> nodeToDelete){
        /*
        delete will be fairly similar to add. We search the treap until we find the node to delete. After deletion we link necessary nodes
        and afterwards we would need to perform rotations if the heap is not satisfied. There are a few cases to consider:

        - if node to delete has no children we're good and we can just set to null (favorite case)
        - if node has one child i dont think any rotation would be needed since treap should already be a max heap (not sure on this yet)
        - finally if node to delete has two children we need to rotate based on priority. It should follow the same logic as the add so not really any new code getting added here
        
        recursive solve cases

        - if root is null we just return null (base)
        - go left if root.val < search key
        - go right otherwise
        - traverse till we either find the node to delete and then we need to consider above cases or we dont find the node to delete and nothing to be done
        */
        //base is if root is null nothing to delete
        if(root == null){
            return null;
        }

        //greater than 
        if(root.data.compareTo(nodeToDelete.data) < 0){
            //this has to be root.right because we are modifying the right subtree. root doesnt work in this case.....
            root.right = recursiveDelete(root.right, nodeToDelete);
            //less than, same logic as right we need to set root.left subtree to the return value of the recursive call
        } else if(root.data.compareTo(nodeToDelete.data) > 0){
            root.left = recursiveDelete(root.left, nodeToDelete);
        } else{
            // if root is equal to node to delete we can begin our tests for cases
        

        //start from 0 children and work are way up to 2

        //0 children is just set root to null if no children
        if (root.left == null && root.right == null){
            root = null;
        
        // if there is only one child we just need to set current node we are on (the target node to delete) to its child that is not null. no rotation needed
        } else if((root.left == null && root.right != null) || (root.right == null && root.left != null)){
            // need two different cases here to check which side is not null
            
            if(root.left != null){
                Node<E> childNode = root.left;
                root = childNode;
            } else{
                Node<E> childNode = root.right;
                root = childNode;
            }
            // root = null;
        }

        //last case is there are two children. This one needs rotations but we arent comparing the priorities of the root since its being deleted
        // we need to compare the left and right children priority and rotate based on that
        // also similar to above we need to either set root.left or root.right depending on outcome
        else{
            if(root.left.priority < root.right.priority){
                // left rotations we set root.left to recursive return and same logic for right
                root = root.rotateLeft();

                root.left = recursiveDelete(root.left, nodeToDelete);
            } else{
                root = root.rotateRight();
                root.right = recursiveDelete(root.right, nodeToDelete);
            }
        }

        // need to return at the end for this to work
        
        }
        return root;
    }

    private boolean find(Node<E> root, E key){
        //same as find(E key) but starting root on the stack is the root parameter passed in
        Stack<Node<E>> treapStack = new Stack<>();

        //null case
        if(this.root == null){
            return false;
        }
        //root for start of stack
        treapStack.add(root);

        while(treapStack.size() != 0){
            Node<E> currNode = treapStack.pop();
            if(currNode != null){
                if(currNode.data == key){
                    return true;
                } 
                treapStack.add(currNode.left);
                treapStack.add(currNode.right);
        }

        }
        //end of stack and no return == key not found
        return false;
    }


    public boolean find(E key){
        //finds key if it exists in the treap. Using BFS approach for fun
        Stack<Node<E>> treapStack = new Stack<>();

        //null case
        if(this.root == null){
            return false;
        }
        //root for start of stack
        treapStack.add(this.root);

        while(treapStack.size() != 0){
            Node<E> currNode = treapStack.pop();
            if(currNode != null){
                if(currNode.data == key){
                    System.out.println("Found key: " + key + " in treap.");
                    return true;
                } 
                treapStack.add(currNode.left);
                treapStack.add(currNode.right);
        }

        }
        //end of stack and no return == key not found
        System.out.println("Key " + key + " not found in treap.");
        return false;
    }

    public String toString(){
        String treapStr = "";

        
        return toStringHelper(this.root, treapStr);
  
        
    }

    private String toStringHelper(Node<E> root, String treapStr){
        //prorder traversal
        if(root == null){

            treapStr += "[null] (null) (null);";
            return treapStr;
        } else{
            treapStr += root.toString() + " ";
            treapStr = toStringHelper(root.left, treapStr);
            treapStr = toStringHelper(root.right, treapStr);
        }

        return treapStr;
    }

    
    
    private static class Node<E extends Comparable<E>>{
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;


        public Node(E data, int priority){
            this.data = data;
            this.priority = priority;
        }

        public Node<E> rotateRight(){

            Node<E> temp_left= this.left;
            Node<E> swap_node = this.left.right;
        
            temp_left.right = this;
            this.left = swap_node;
            return temp_left;
            
        }

        public Node<E> rotateLeft(){
            //same logic as rotate right
            Node<E> temp_right = this.right;
            Node<E> swap_node = this.right.left;

            temp_right.left = this;
            this.right = swap_node;

            return temp_right;

        }

        public String toString(){
            E left_str;
            E right_str;
            if(this.left == null){
                left_str = null;
            } else{
                left_str = this.left.data;
            }
            if(this.right == null){
                right_str = null;
            } else{
                right_str = this.right.data;
            }
            return "[" + this.data + ", " + this.priority + "] " + "(" + left_str + ") (" + right_str + ");"; 
        }
    }

    public static void main(String[] args){
        Treap<Integer> test_int_treap = new Treap();
        Treap<String> test_string_treap = new Treap();

        //int treap testing
        test_int_treap.add(5);
        test_int_treap.add(4);
        test_int_treap.add(6);
        test_int_treap.add(7);
        System.out.println("#####CURRENT TREAP IS : #####");
        System.out.println(test_int_treap.toString());
        System.out.println("##########");
        test_int_treap.add(3);
        test_int_treap.add(2);
        test_int_treap.find(20);
        test_int_treap.delete(4);
        test_int_treap.delete(40);
        System.out.println("#####CURRENT TREAP IS : #####");
        System.out.println(test_int_treap.toString());
        System.out.println("##########");

        //string treap testing
        test_string_treap.add("f");
        test_string_treap.add("b");
        test_string_treap.add("e");
        test_string_treap.add("h");
        test_string_treap.add("z");
        test_string_treap.add("l");
        System.out.println("#####CURRENT TREAP IS : #####");
        System.out.println(test_string_treap.toString());
        System.out.println("##########");
        test_string_treap.delete("e");
        System.out.println("#####CURRENT TREAP IS : #####");
        System.out.println(test_string_treap.toString());
        System.out.println("##########");
        test_string_treap.delete("i");
        System.out.println("#####CURRENT TREAP IS : #####");
        System.out.println(test_string_treap.toString());
        System.out.println("##########");
        test_string_treap.find("b");
        test_string_treap.find("m");
        
    }
}