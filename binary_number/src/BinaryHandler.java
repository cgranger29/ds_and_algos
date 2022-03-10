public class BinaryHandler{

    public static void main(String[] args){
        BinaryNumber test = new BinaryNumber(9);
        test.getBinaryNumber();

        test.printLength();
        BinaryNumber test2 = new BinaryNumber("1001");
        test2.getBinaryNumber();

        test2.printLength();

        System.out.println("getDigit return value: " + test2.getDigit(2));
    }
}