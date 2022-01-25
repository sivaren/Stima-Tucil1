package src;
import java.util.*;

public class Primitif {
    public static char getCharFromString(String str, int index) {
        return str.charAt(index);
    }

    public static void fillKeystat(int[] keys_stat) {
        for(int i = 0; i < keys_stat.length; i++){
            keys_stat[i] = 0;
        }
    }

    public static int[] appendArr(int[] array, int el) {
        int[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = el;

        return newArray;
    }
    
    public static int[][] appendArr_2d(int[][] array, int[] el) {
        int[][] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = el;

        return newArray;
    }
    
    public static int[][] clearArr_2d(int[][] array) {
        int[][] newArr = new int[0][0];    
        return newArr;
    }
    
    public static int[][][] appendArr_3d(int[][][] array, int[][] el) {
        int[][][] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = el;
        
        return newArray;
    }
    
    public static boolean isRowCol_avail(int row, int col, int[][] keys_constructors) {
        int[] point = {row,col};
        boolean found = false;

        for(int i = 0; i < keys_constructors.length; i++) {
            if(point[0] == keys_constructors[i][0] && point[1] == keys_constructors[i][1]) {
                found =  true;
                break;
            }
        }
        
        return found;
    }

    public static boolean isAllOne(int[] array) {
        for(int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                return false;
            }
        }
        return true;
    }

    public static void output_program(char[][] puzzle, int[][][] all_keys_constructors, String[] keys_arr){
        for(int i=0; i < keys_arr.length; i++){
            System.out.println(keys_arr[i]);
            for(int row=0; row < puzzle.length; row++) {
                for(int col=0; col < puzzle[0].length; col++){
                    
                    if (isRowCol_avail(row, col, all_keys_constructors[i])) {
                        System.out.print(puzzle[row][col]);
                        System.out.print(" ");
                    } else {
                        System.out.print("-");
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
