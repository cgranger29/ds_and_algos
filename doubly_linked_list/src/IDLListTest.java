class IDLListTest{
    public static void main(String[] args){
        IDLList<String> test_list = new IDLList<>();
        // ArrayList<Node> test_arr = new ArrayList<Node>();
        // Node test_node = new Node(4);
        // test_arr.add(test_node);

        // Node store_node = test_arr.get(0);

        // System.out.println(store_node.data);
        test_list.append("Hi");
        test_list.append("there");
        test_list.append("how");
        test_list.append("are");
        test_list.append("blah");
        // test_list.add(2);

        System.out.println(test_list.toString());
        
    }
}