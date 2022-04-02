import java.util.ArrayList;

public class IDLList<E>{

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node> indices = new ArrayList<Node>();

    public IDLList(){

    }

    public boolean append(E elem){
        
        return true;
    }

    public boolean add(E elem){
        Node target_node = new Node(elem);
        if(this.indices.size() == 0){
            this.indices.add(target_node);
        }
        return true;
    }

    static class Node<E>{
        E data;
        Node<E> next;
        Node<E> prev;

        public Node(E elem){
            this.data = elem;
        }

        public Node(Node<E> next, Node<E> prev){
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args){
        IDLList test_list = new IDLList();
        // ArrayList<Node> test_arr = new ArrayList<Node>();
        // Node test_node = new Node(4);
        // test_arr.add(test_node);

        // Node store_node = test_arr.get(0);

        // System.out.println(store_node.data);
        test_list.add(4);
        System.out.println(test_list.indices.get(0).data);

    }
}
