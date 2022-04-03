class IDLListTest{
    public static void main(String[] args){

        //intialize LL for int values
        IDLList<Integer> DLL_int = new IDLList<>();

        //test methods for empty list
        DLL_int.toString(); //DLL is currently empty.
        DLL_int.remove(); //The DLL is currently empty. No nodes to remove.
        DLL_int.removeAt(100); //The DLL is currently empty. No nodes to remove.

        //add and append methods
        DLL_int.add(3);
        System.out.println(DLL_int.toString()); //3->
        DLL_int.append(5);
        System.out.println(DLL_int.toString()); //3->5->
        DLL_int.add(1);
        System.out.println(DLL_int.toString()); //1->3->5->
        DLL_int.add(1, 4);
        System.out.println(DLL_int.toString()); //1->4->3->5->
        DLL_int.add(100, 10); //Index is out of range. Index must be between 0 and 4
        DLL_int.add(4, 7);
        System.out.println(DLL_int.toString()); //1->4->3->5->7->
        DLL_int.append(6);
        System.out.println(DLL_int.toString()); //1->4->3->5->7->6->

        System.out.println(DLL_int.size()); //6

        //remove methods
        System.out.println(DLL_int.remove()); // 1
        System.out.println(DLL_int.toString()); //4->3->5->7->6->
        System.out.println(DLL_int.remove(50)); //prints "Node with value of 50 NOT found in LL." RETURNS false
        System.out.println(DLL_int.remove(3)); //prints "Node with value of 3 found in LL...Removing node." RETURNS true
        System.out.println(DLL_int.toString()); //4->5->7->6->
        System.out.println(DLL_int.removeAt(3)); //6
        System.out.println(DLL_int.toString()); //4->5->7->

        //utility methods
        System.out.println(DLL_int.getHead()); //4
        System.out.println(DLL_int.getLast()); //7
        System.out.println(DLL_int.size()); //3

        //empty the list
        System.out.println(DLL_int.remove());
        System.out.println(DLL_int.toString()); //5->7->
        System.out.println(DLL_int.remove());
        System.out.println(DLL_int.toString()); //7->
        System.out.println(DLL_int.remove()); //The DLL is currently empty. No nodes to remove. RETURNS null
        System.out.println(DLL_int.toString()); //DLL is currently empty. RETURNS ""
    }
}