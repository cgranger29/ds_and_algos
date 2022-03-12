public class BinaryHandler{

    public static void main(String[] args){
        BinaryNumber test = new BinaryNumber(9);
        // test.getBinaryNumber();

        // test.printLength();
        BinaryNumber test2 = new BinaryNumber("10010001010100010010100");
        // test2.getBinaryNumber();

        // test2.printLength();

        // System.out.println("getDigit return value: " + test2.getDigit(2));

        test2.shiftR(1000);

        test2.getBinaryNumber();

        System.out.println(test2.toString());


    }
}