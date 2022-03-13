public class BinaryHandler{

    public static void main(String[] args){
        BinaryNumber test = new BinaryNumber("100101000000000000000000");
        // test.getBinaryNumber();

        // test.printLength();
        BinaryNumber test2 = new BinaryNumber("1001");
        // test2.getBinaryNumber();

        // test2.printLength();

        // System.out.println("getDigit return value: " + test2.getDigit(2));

        test2.getBinaryNumber();

        test2.add(test);

        System.out.println("New number after add: " + test2.toString());

        System.out.println("New number to decimal: " + test2.toDecimal());


    }
}