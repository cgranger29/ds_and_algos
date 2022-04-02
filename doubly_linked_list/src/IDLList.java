import java.util.ArrayList;

public class IDLList<E>{


    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    public IDLList(){
        head = null;
        tail = null;
        size = 0;
        indices = new ArrayList<Node<E>>();
    }

    public E get(int index) throws Exception{
        if (index >= this.indices.size()){
             throw new Exception("Index is out of range. Index must be between 0 and " + (this.indices.size() - 1));
        }
        return this.indices.get(index).data;
    }

    public E getHead(){
        if (this.head == null){
            return null;
        }
        return this.head.data;
    }

    public E getTail(){
        if(this.tail == null){
            return null;
        }
        return this.tail.data;
    }

    public boolean append(E elem){
        Node<E> target_node = new Node<E>(elem);
        if(this.indices.size() == 0){
            this.indices.add(target_node);
            this.head = target_node;
        } else{
            this.indices.add(target_node);
            Node<E> temp_head = this.head;
            while(temp_head.next != null){
                temp_head = temp_head.next;
            }
            temp_head.next = target_node;
            this.tail = target_node;
        }
        return true;
    }

    public boolean add(E elem){
        Node<E> target_node = new Node<E>(elem);
        if(this.indices.size() == 0){
            this.indices.add(target_node);
            this.head = target_node;
        } else{
            //update head
            Node<E> temp_head = this.head;
            this.head = target_node;
            this.head.next = temp_head;
            this.indices.add(0, this.head);
            this.size = this.indices.size();

            //update tail
            Node<E> temp_tail = this.head;
            while(temp_tail.next != null){
                temp_tail = temp_tail.next;
            }

            this.tail = temp_tail;


        }
        return true;
    }

    private static class Node<E>{
        E data;
        Node<E> next;
        Node<E> prev;

        public Node(E elem){
            this.data = elem;
        }

        public Node(E elem, Node<E> next, Node<E> prev){
            this.data = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    // public static void main(String[] args){
    //     IDLList<Integer> test_list = new IDLList<>();
    //     // ArrayList<Node> test_arr = new ArrayList<Node>();
    //     // Node test_node = new Node(4);
    //     // test_arr.add(test_node);

    //     // Node store_node = test_arr.get(0);

    //     // System.out.println(store_node.data);
    //     test_list.add(1);
    //     test_list.add(2);

    //     System.out.println(test_list.getHead());
    //     System.out.println(test_list.getTail());

    //     try{
    //         System.out.println(test_list.get(1));
    //     } catch(Exception e){
    //         System.out.println(e);
    //     }
        

    // }
}
