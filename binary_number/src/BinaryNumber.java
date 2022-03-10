public class BinaryNumber{

    private int data[];
    private boolean overflow;
    
    public BinaryNumber(int length){
        data = new int[length];
        for(int i = 0; i < length; i++){
            data[i] = 0;
        }
    }

    public BinaryNumber(String str){
        data = new int[str.length()];
        
        for(int i = 0; i < str.length(); i++){
            data[i] = Character.getNumericValue(str.charAt(i));
        }
    }

    public int getLength(){
        return data.length;
    }

    public int getDigit(int index){
        return data[index];
    }

    public void shiftR(int amount){
        // need to implement
    }

    public void add(BinaryNumber aBinaryNumber){
        // need to implement
    }

    public String toString(){
        // need to implement
    }

    public int toDecimal(){
        // need to implement
    }

    public void clearOverflow(){
        // need to implement
    }

    public void getBinaryNumber(){
        // extra accessor, can remove when done
        for(int i = 0; i < data.length; i++){
            System.out.print(data[i]);
        }

        System.out.println("");
    }

    public void printLength(){
        // extra accessor, can remove when done
        System.out.println("Length of Binary Number: " + this.getLength());
    }



}