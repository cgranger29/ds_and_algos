import java.util.ArrayList;

public class IDLList<E>{


    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    public IDLList(){
        // constructor for an empty DLL.
        head = null;
        tail = null;
        size = 0;
        indices = new ArrayList<Node<E>>();
    }

    public E get(int index){
        // Gets the node in the DLL by index.

        if (index >= this.indices.size()){
             System.out.println("Index is out of range. Index must be between 0 and " + (this.indices.size() - 1));
             return null;
        }
        return this.indices.get(index).data;
    }

    public E getHead(){
        // return head of DLL. If DLL is empty returns null.

        if (this.head == null){
            return null;
        }
        return this.head.data;
    }

    public E getLast(){
        // return tail of the DLL. If DLL is empty returns null.

        if(this.tail == null){
            return null;
        }
        return this.tail.data;
    }

    public boolean append(E elem){
        // appends elem to the end of the LL.

        Node<E> target_node = new Node<E>(elem);
        if(this.size == 0){
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
        // adds elem to the head of the LL.

        Node<E> target_node = new Node<E>(elem);
        if(this.size == 0){
            this.indices.add(target_node);
            this.head = target_node;
        } else{
            //update head
            Node<E> temp_head = this.head;
            this.head = target_node;
            temp_head.prev = target_node;
            this.head.next = temp_head;
            this.indices.add(0, this.head);

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

    public void outputNodesAndLinks(){
        // helper function to iterate LL and print current node with prev and next node links. This is just a helper/convenience function for testing

        for(Node<E> node: this.indices){
            System.out.println("Current node is: " + node.data + ", reference ID: " + node);
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

        System.out.println(this.indices.toString());
        System.out.println("Size of indices is " + this.size);
    }
    public String toString(){
        /*
            Outputs LL in string format. Output is structured as node.data->node.data->....-> with empty pointer at end denoting a null terminator.
            If LL is empty, a blank string is returned
        */
        if(this.size == 0){
            System.out.println("DLL is currently empty.");
            return "";
        }
        String return_string = "";
        for(Node<E> node: this.indices){
            return_string += node.data.toString() + "->";        
        }

        return return_string;
    }

    public int size(){
        return this.size;
    }

    public boolean add(int index, E elem){
        /*
        Adds elem to designated index in LL. Returns true if added else throws exception if index is out of range.

        */
        if(index > this.indices.size() || index < 0){
            System.out.println("Index is out of range. Index must be between 0 and " + (this.indices.size()));
            return false;
        } else if(index == 0){
            // either add or append can be used here
            this.add(elem);
        } else if (index == this.size){
            // if we're looking to add to say index 2 for an array that has size 2 it would be valid and it would just be an append
            this.append(elem);
        } else {
            Node<E> temp_next = this.indices.get(index);
            Node<E> temp_prev = temp_next.prev;
            temp_prev.next = null;
            temp_next.prev = null;
            Node<E> target_node = new Node(elem, temp_next, temp_prev);
            temp_prev.next = target_node;
            temp_next.prev = target_node;
            indices.add(index, target_node);
            this.size += 1;

        }

        return true;
    }

    public E remove(){
        // Removes head of LL and returns if value found else returns null.

        if(this.head == null){
            System.out.println("The DLL is currently empty. No nodes to remove.");
            return null;
        } else if(this.size == 1){
            this.head = null;
            this.tail = null;
            Node<E> target_node = this.indices.get(0);
            this.indices.clear();
            this.size -=1;
            return target_node.data;
 
        } else{
            Node<E> target_node = this.head;
            this.head = this.head.next;
            this.head.prev = null;
            this.indices.remove(0);
            this.size -= 1;
            return target_node.data;


        }
        
    }

    public E removeLast(){
        // Removes tail of LL if found. Else returns null

        if(this.tail == null){
            System.out.println("The DLL is currently empty. No nodes to remove.");
            return null;
        } else if(this.size == 1){
            Node<E> target_node = this.tail;
            this.tail = null;
            this.head = null;
            this.size -=1;
            return target_node.data;
        } else{
            Node<E> target_node = this.tail;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.indices.remove(indices.size() - 1);
            this.size -=1;
            return target_node.data;
        }
    }

    public E removeAt(int index){
        // Removes specified elem at provided index. Returns null if index is out of range.

        if(index < 0 || index >= this.size){
            if(this.size == 0){
                System.out.println("The DLL is currently empty. No nodes to remove.");
            }else{
                System.out.println("Index " + index + " is out of range. Provided index must be between 0 and " + (this.size - 1));
            }
            
            return null;
        } else if (index == 0){
            return this.remove();
        } else if(index == (this.size - 1)){
            return this.removeLast();
        } else {
            Node<E> target_node = this.indices.get(index);
            Node<E> temp_left = target_node.prev;
            Node<E> temp_right = target_node.next;
            temp_right.prev = temp_left;
            temp_left.next = temp_right;
            this.indices.remove(index);
            this.size -=1;
            return target_node.data;
        }
    }

    public boolean remove(E elem){
        // removes elem if node.data is equal to elem and returns true. If elem not found in LL returns false.

        for(int i = 0; i < this.indices.size(); i++){
            if(indices.get(i).data == elem){
                System.out.println("Node with value of " + elem + " found in LL...Removing node.");
                this.removeAt(i);
                return true;
            }
        }
        System.out.println("Node with value of " + elem + " NOT found in LL.");
        return false;
    }

    private static class Node<E>{
        // Inner node class

        E data;
        Node<E> next;
        Node<E> prev;

        public Node(E elem){
            //Construtor for Node with just elem provided.

            this.data = elem;
        }

        public Node(E elem, Node<E> next, Node<E> prev){
            //Constructor for node where elem, prev, and next are provided
    
            this.data = elem;
            this.next = next;
            this.prev = prev;
        }
    }

}
