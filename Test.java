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
            {{1,2},{3,4},{2,1,1,2}},
            {{5,6},{7,8}},
        };

        int[] idx = {1,2,3};
        int el = 5;
        
        int[] idxNew = Arrays.copyOf(idx, idx.length + 1);
        idxNew[idx.length] = el;

        System.out.println(idxNew.length); // 4
        // System.out.println(idxNew); // 4
        for(int i = 0; i < idxNew.length; i++) {
            System.out.print(idxNew[i]); 
        } // 1 2 3 5
        System.out.println(); 

        // idxNew menjadi empty array
        idxNew = new int[0];
        System.out.println(idxNew.length); // 0

        int[] point = {1, 2};
        int[][] key_cons = {{4,4}};
        int[][][] all_key_cons = {
            {{2,2}}
        };

        int[][] new_key_cons = Arrays.copyOf(key_cons, key_cons.length + 1);
        new_key_cons[key_cons.length] = point;
        System.out.println(new_key_cons.length); // 2
        // new_key_cons = {{4,4}, {1, 2}}
        // new_key_cons = new int[0][0];
        // System.out.println(new_key_cons.length); //0
        

        int[][][] new_all_key_cons = Arrays.copyOf(all_key_cons, all_key_cons.length + 1);
        new_all_key_cons[all_key_cons.length] = new_key_cons;
        // new_all_key_cons = {
        //     {{2, 2}},
        //     {{4, 4}, {1, 2}}
        // }

        System.out.println("Here");
        for(int i = 0; i < new_all_key_cons.length; i++) {
            for(int j = 0; j < new_all_key_cons[i].length; j++) {
                if (new_key_cons[1] == new_all_key_cons[i][j]) {
                    System.out.print(new_all_key_cons[i][j][0]);
                    System.out.println(new_all_key_cons[i][j][1]);
                }
            }  
        } 
        System.out.println("Here");
        int a = 2;
        int b = 3;
        int[] key = {a, b};
        
        System.out.println(key[0]); // 2
        System.out.println(key[1]); // 3
        System.out.println("==="); 

        System.out.println(new_key_cons[0][1]); // 4
        System.out.println(new_all_key_cons[1][0][1]); // 4
        
        System.out.println("==="); 
        
        System.out.println(M[1][1][1]); // 8
        System.out.println(lenKeysArr); // 7
        System.out.println("==="); 
        System.out.println(M.length); // 2
        System.out.println(M[0].length); // 3
        System.out.println(M[0][2].length); // 4
        
        System.out.println("===");
        
        String str = "asdf";
        System.out.println(str); // d

    }
}