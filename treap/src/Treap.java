import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

class Treap<E extends Comparable<E>>{
    
    private Random priorityGenerator;
    private Node<E> root;
    private int depth = 0;
    
    public Treap(){
        //default seed value will just be 1000
        this.priorityGenerator = new Random(1000);
        this.root = null;
    }

    public Treap(long seed){
        this.priorityGenerator = new Random(seed);;
        this.root = null;
    }

    public int getDepth(){
        // will use to implement nice print format if I have time. Something like:
               /*
                            4
                          /   \
                         3     5
                        /  \
                        5   6
                        will need to calculate max width of each level:
                       root will be max width of entire tree / 2
                       each level after will be the prev level + 2 spaces??? one for left and one for right
                       idk this seems like a lot of work at first glance so TBD
               */
        return this.depth;
    }

    public boolean add(E key){

        // this just calls add(E key, int priority) and passes in a default priority generation
        //Integer.MAX_VALUE -1 should prevent negatives from overflow

        // int add_priority = this.priorityGenerator.nextInt(Integer.MAX_VALUE - 1);
        int add_priority = this.priorityGenerator.nextInt(1000);

        return this.add(key, add_priority);

    }

    public boolean add(E key, int priority){
        //Integer.MAX_VALUE -1 should prevent negatives from overflow
        // int add_priority = this.priorityGenerator.nextInt(Integer.MAX_VALUE - 1);
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

        // not needed in recursive solution
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
        //implement
        return false;
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
                    return true;
                } 
                treapStack.add(currNode.left);
                treapStack.add(currNode.right);
        }

        }
        //end of stack and no return == key not found
        return false;
    }

    public String toString(){
        toStringHelper(this.root);
        return "";
        
    }

    private void toStringHelper(Node<E> root){
        //prorder traversal
        if(root == null){
            System.out.println("null ");
        } else{
            System.out.println(root.toString() + " ");
            toStringHelper(root.left);
            toStringHelper(root.right);
        }
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
        test_int_treap.add(5);
        test_int_treap.add(4);
        test_int_treap.add(6);
        test_int_treap.add(7);
        test_int_treap.add(3);
        test_int_treap.add(2);
        test_int_treap.find(20);
        test_int_treap.toString();

        System.out.println("\nTREE CURRENT DEPTH IS " + test_int_treap.getDepth());

        //string treap testing
        // test_string_treap.add("a");
        // test_string_treap.add("b");
        



    }
}