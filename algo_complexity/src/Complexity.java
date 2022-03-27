public class Complexity {

    public static int[] bst_arr = {1,2,3,4,5,6};
    public static int[][] two_dim_arr = new int[2][2];
    public static int[][][] three_dim_arr = new int[2][2][2];

    public static void method1(){
        // n^2, populate 2d array

        int counter = 0;

        for(int i = 0; i < two_dim_arr.length; i++){
            for(int j = 0; j < two_dim_arr.length; j++){
                two_dim_arr[i][j] = counter;
                counter +=1;
            }
        }

        // printing every value of the 2d array also n^2
        for(int i = 0; i < two_dim_arr.length; i++){
            for(int j = 0; j < two_dim_arr.length; j++){
                System.out.print(two_dim_arr[i][j]);
            }
        }

        
        
    }

    public static void method2(){
        // n^3, populate 3d array

        int counter = 0;

        for(int i = 0; i < three_dim_arr.length; i ++){
            for (int j = 0; j < three_dim_arr.length; j++){
                for (int k = 0; k < three_dim_arr.length; k++){
                    three_dim_arr[i][j][k] = counter;
                    counter += 1;
                }
            }
        }

        for(int i = 0; i < three_dim_arr.length; i ++){
            for (int j = 0; j < three_dim_arr.length; j++){
                for (int k = 0; k < three_dim_arr.length; k++){
                    System.out.print(three_dim_arr[i][j][k]);
                }
            }
        }
    }

    public static void method3(){
        // logn, binary search that looks for 1 in the array
        int left = 0;
        int right = bst_arr.length;
        int midpoint;
        boolean val_found = false;

        while (left < right){
            midpoint = left + (right - left) / 2;
            if (bst_arr[midpoint] == 1){
                System.out.println("1 found at index " + midpoint);
                val_found = true;
                break;
            } else if(bst_arr[midpoint] > 1){
                right = midpoint;
            } else {
                left = midpoint + 1;
            }
        }

        if (!val_found){
            System.out.println("1 not found in the array.");
        }
    }

    public static void method4(){
        // nlogn, for this we just need a for loop that increments linearly O(n) and 
        // then a nested for loop that gets divided by some constant each time. 2 is the easiest O(logn)
        int num = 50;

        int counter = 0;

        // loops num times O(n);
        for (int i = 0; i < num; i ++){
            // loops num / 2 times O(logn), loop needs to start at num since its divided by 2 each time until it hits 0
            counter += 1;
            for (int j = num; j > 0; j/=2){
                counter += 1;
            }
        }

        System.out.println("counter: " + counter);
    }

    public static void method5(){
        // loglogn, this needs to divide by n amount
    }
    public static void main(String args[]){
        method1();
        System.out.println("");
        method2();
        System.out.println("");
        method3();
        System.out.println("");
        method4();
    }
}
