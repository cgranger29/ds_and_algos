class IDLListTest{
    public static void main(String[] args){
        IDLList<Integer> test_list = new IDLList<>();
        // ArrayList<Node> test_arr = new ArrayList<Node>();
        // Node test_node = new Node(4);
        // test_arr.add(test_node);

        // Node store_node = test_arr.get(0);

        // System.out.println(store_node.data);
        test_list.add(1);
        test_list.add(2);

        System.out.println(test_list.getHead());
        System.out.println(test_list.getTail());

        try{
            System.out.println(test_list.get(1));
        } catch(Exception e){
            System.out.println(e);
        }
        
    }
}