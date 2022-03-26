import java.util.Arrays;

public class BinaryNumber{

    private int data[];
    private boolean overflow;
    
    public BinaryNumber(int length) throws Exception{

        if(length <= 0){
            throw new Exception("Int must be greater than 0.");
        }
        data = new int[length];
        for(int i = 0; i < length; i++){
            data[i] = 0;
        }
    }

    public BinaryNumber(String str) throws Exception{
        data = new int[str.length()];
        
        for(int i = 0; i < str.length(); i++){
            if (Character.getNumericValue(str.charAt(i)) != 0 && Character.getNumericValue(str.charAt(i)) != 1){
                throw new Exception("String must only contain 0 or 1. " + Character.getNumericValue(str.charAt(i)) + " is invalid.");
            }
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
        // 0 + 0 = 0, 0 carryover
        // 0 + 1 ( or 1 + 0) = 1, 0 carryover
        // 1 + 1 = 0, 1 carryover
        // 1 + 1 + 1 (this implies a 1 + 1 but with 1 carryover which is max) = 1, 1 carryover
        
        // to make this easier the binary number with smallest len should be padded to match
        // if lens are the same no need for this
        if(data.length == aBinaryNumber.getLength()){
            // add them together with rules
            System.out.println("Lengths are equal");
        } else if(aBinaryNumber.getLength() < data.length){
            aBinaryNumber.shiftR((data.length - aBinaryNumber.getLength()));

        } else{
            shiftR((aBinaryNumber.getLength() - this.getLength()));
        }

        //now the add logic, need to loop from end to start and keep track of carryover
        boolean carryOver = false;
        int[] newNum = new int[this.getLength()];

        //new array to hold new binary number
        for (int i = 0; i < this.getLength(); i++){
            newNum[i] = 0;
        }
        
        for(int i = this.getLength() - 1; i >= 0; i--){

            if(this.getDigit(i) == 0 && aBinaryNumber.getDigit(i) == 0 && !carryOver){
                newNum[i] = 0;

            } else if(this.getDigit(i) == 1 && aBinaryNumber.getDigit(i) == 0 && !carryOver){
                newNum[i] = 1;
            } else if (this.getDigit(i) == 0 && aBinaryNumber.getDigit(i) == 1 && !carryOver){
                newNum[i] = 1;
            } else if (this.getDigit(i) == 1 && aBinaryNumber.getDigit(i) == 1 && !carryOver){
                newNum[i] = 0;
                carryOver = true;
            } else if(this.getDigit(i) == 0 && aBinaryNumber.getDigit(i) == 0 && carryOver){
                newNum[i] = 1;
                carryOver = false;
            } else if (this.getDigit(i) == 1 && aBinaryNumber.getDigit(i) == 0 && carryOver){
                newNum[i] = 0;
                carryOver = true;
            } else if (this.getDigit(i) == 0 && aBinaryNumber.getDigit(i) == 1 && carryOver){
                newNum[i] = 0;
                carryOver = true;
            } else if(this.getDigit(i) == 1 && aBinaryNumber.getDigit(i) == 1 && carryOver){
                newNum[i] = 1;
                carryOver = true;

            }
            

        }

        if(carryOver){
            this.data = newNum;
            this.addOneToArr();
        } else{
            this.data = newNum;
        }
        
      
    }

    @Override
    public String toString(){
        if (this.toDecimal() >= Integer.MAX_VALUE){
            this.overflow = true;
            return "Overflow";
        }

        String newStr = "";

        for(int i = 0; i < this.getLength(); i ++){
            newStr += Integer.toString(data[i]);
        }
        return newStr;
    }

    public int toDecimal(){
        // loop the current data array and keep a cumulative sum of the digits. 2^0 + 2^1 etc...
        int decimalSum = 0;
        int exp = this.getLength() - 1;
        for(int i = 0; i < this.getLength(); i++){
            if(this.getDigit(i) == 1){
                decimalSum += Math.pow(2, exp);
            }

            exp -= 1;
        }

        return decimalSum;
    }

    public void clearOverflow(){
        this.data = new int[1];
        this.data[0] = 0;
        this.overflow = false;
        System.out.println("Overflow cleared and binary number set to " + this.toString());
    }

    public void addOneToArr(){
        int[] newArr = new int[this.getLength() + 1];

        newArr[0] = 1;
        for(int i = 0; i < this.data.length; i++){
            newArr[i + 1] = this.data[i];
        }

        this.data = newArr;
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