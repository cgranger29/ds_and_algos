class IDLListTest{
    public static void main(String[] args){
        IDLList<Integer> test_list = new IDLList<>();
        // ArrayList<Node> test_arr = new ArrayList<Node>();
        // Node test_node = new Node(4);
        // test_arr.add(test_node);

        // Node store_node = test_arr.get(0);

        // System.out.println(store_node.data);

        try{
            test_list.add(1);
            test_list.append(2);
            test_list.append(3);
            test_list.append(4);
            System.out.println("Node " + test_list.removeAt(1) + " removed.");
            System.out.println("Node " + test_list.removeAt(1) + " removed.");
            System.out.println("Node " + test_list.removeAt(1) + " removed.");
            System.out.println("Node " + test_list.removeAt(0) + " removed.");
            System.out.println("Node " + test_list.removeAt(0) + " removed.");

        } catch(Exception e){
            System.out.println(e);
        }
        

        // test_list.add(2);

        System.out.println(test_list.toString());
        System.out.println("head is :" + test_list.getHead());
        System.out.println("tail is : "+ test_list.getLast());
        
    }
}