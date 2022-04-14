import java.util.Random;

class Treap<E extends Comparable<E>>{
    
    private Random priorityGenerator;
    private Node<E> root;
    
    public Treap(){
        //default seed value will just be 1000
        this.priorityGenerator = new Random(1000);
        this.root = null;
    }

    public Treap(long seed){
        this.priorityGenerator = new Random(seed);;
        root = null;
    }

    public boolean add(E key){
        //Integer.MAX_VALUE -1 should prevent negatives from overflow

        // int add_priority = this.priorityGenerator.nextInt(Integer.MAX_VALUE - 1);
        int add_priority = this.priorityGenerator.nextInt(1000);
        Node<E> add_node = new Node(key, add_priority);

        System.out.println("added node: " + add_node.data + " with priority of " + add_node.priority);
        if (this.root == null){
            this.root = add_node;
            return true;
        } else {
            //first we check the tree like its a BST
            Node<E> temp_root = this.root;
            if(add_node.data.compareTo(temp_root.data) > 0){
                System.out.println("node to add " + add_node.data + " is GREATER THAN root " + temp_root.data);
            } else if(add_node.data.compareTo(temp_root.data) < 0){
                System.out.println("node to add " + add_node.data + " is LESS THAN root " + temp_root.data);
            } else{
                System.out.println("node to add " + add_node.data + " is EQUAL TO root " + temp_root.data);
            }
        }

        return false;

    }

    public boolean add(E key, int priority){
        //implement
        return false;
    }

    public boolean delete(E key){
        //implement
        return false;
    }

    private boolean find(Node<E> root, E key){
        //implement
        return false;
    }

    public boolean find(E key){
        //implement
        return false;
    }

    public String toString(){
        //implement
        return "";
    }

    
    
    public class Node<E extends Comparable<E>>{
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;


        public Node(E data, int priority){
            this.data = data;
            this.priority = priority;
        }

        // public Node<E> rotateRight(){
        //     //implement

        // }

        // public Node<E> rotateLeft(){
        //     //implement
        // }
    }

    public static void main(String[] args){
        Treap<Integer> test_int_treap = new Treap();
        Treap<String> test_string_treap = new Treap();
        test_int_treap.add(1);
        test_int_treap.add(2);
        test_string_treap.add("a");
        test_string_treap.add("b");
        



    }
}