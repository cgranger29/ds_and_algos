import java.util.Arrays;

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
        reallocate(data, amount + data.length);

    }

    public void add(BinaryNumber aBinaryNumber){
        // need to implement
    }

    @Override
    public String toString(){
        return Arrays.toString(data);
    }

    // public int toDecimal(){
    //     // need to implement
    // }

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

    private void reallocate(int[] originalArr, int newLength){

        // int[] newArr = Arrays.copyOf(originalArr, newLength);

        int[] newArr = new int[newLength];

        int oldArrLength = newLength - originalArr.length;
        //populate beggining with padded zeros
        for(int i = 0; i < oldArrLength;i++){
            newArr[i] = 0;
        }

        for(int i = 0; i < originalArr.length; i++){
            newArr[oldArrLength + i] = originalArr[i];
        }

        data = newArr;
        

    }



}