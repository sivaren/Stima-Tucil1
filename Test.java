import java.util.*;

class Test {
    public static void main(String[] args) {
        // System.out.println("==========================================");
        // System.out.println("      (^///^) SELAMAT TINGGAL (^///^)     ");
        // System.out.println("==========================================");

        int lenKeysArr = 7;
        int rowPuzzle = 7;
        int colPuzzle = 8;
        
        // int[][][] M = new int[lenKeysArr][rowPuzzle][colPuzzle];
        int[][][] M = {
            {{1,2},{3,4}},
            {{5,6},{7,8}},
        };

        System.out.println(M[1][1][1]); // 8
        System.out.println(lenKeysArr); // 7
    }
}