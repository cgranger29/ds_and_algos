public class BinaryHandler{

    public static void main(String[] args){

        try{
        BinaryNumber test = new BinaryNumber("11111111111111111111111111111111111111111111111111");
        // test.getBinaryNumber();

        // test.printLength();
        BinaryNumber test2 = new BinaryNumber("111011111111111111111111111");
        // test2.getBinaryNumber();

        // test2.printLength();

        // System.out.println("getDigit return value: " + test2.getDigit(2));

        test2.getBinaryNumber();

        test2.add(test);

        System.out.println("New number after add: " + test2.toString());

        System.out.println("New number to decimal: " + test2.toDecimal());

        System.out.println(test2.toString());

        test2.clearOverflow();
        
        } catch(Exception e){
            e.printStackTrace();
        }


    }
}