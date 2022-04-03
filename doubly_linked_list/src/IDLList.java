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
        System.out.println(this.indices.size());
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

    public E getLast(){
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
            target_node.prev = temp_head;
            this.tail = target_node;
        }

        this.size += 1;
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
            temp_head.prev = target_node;
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
        this.size += 1;
        return true;
    }

    public String toString(){
        // coding this as a helper that shows links for now.
        // need to change once complete to output proper format
        String return_string = "";
        for(Node<E> node: this.indices){
            System.out.println("Current node is: " + node.data);
            if (node.prev == null){
                System.out.println("Prev node for " + node.data + " is " + null);
            } else{
                System.out.println("Prev node for " + node.data + " is "  + node.prev.data);
            }

            if(node.next == null){
                System.out.println("Next node for " + node.data + " is " + null);
            } else {
                System.out.println("Next node for " + node.data + " is " + node.next.data);
            }
        }

        return return_string;
    }

    public boolean add(int index, E elem)throws Exception{
        /*
        Cases to handle:

        - if index passed in is == size then its valid and we just add to the end of list. So invalid is anything < 0 or anything > indices.size()
        - if index passed in is >- size of indices then its out of range and we throw an exception.
        - if index == 0 and indices size == 0 then we can just add to indices and set head to elem
        - if index == 0 and indices size != 0 then we modify the head
        - if index == indices.size() - 1 then we only need to modify tail.
        - for all other cases its assumed that there is a prev and next pointer that needs to be changed

        */
        if(index > this.indices.size() || index < 0){
            throw new Exception("Index is out of range. Index must be between 0 and " + (this.indices.size()));
        } else if(this.indices.size() == 0 && index == 0){

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
