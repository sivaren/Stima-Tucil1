import java.util.*;

public class Arr {

    // String[] keys_arr;
    int[] keys_stat;
    // int[][][] all_keys_constructors;

    public Arr(int n) {
        // this.keys_arr = new String[n];
        this.keys_stat = new int[n];
        fillKeystat(this.keys_stat);
        // this.all_keys_constructors = new int[n][][];
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

    // public static void appendArr(int[] array, int el) {
    //     array = Arrays.copyOf(array, array.length + 1);
    //     array[array.length-1] = el;
    // }
    
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
    
    public static char getCharFromString(String str, int index) {
        return str.charAt(index);
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

    public static void printKeyCons (int[][] key_cons) {
        for (int i = 0; i < key_cons.length; i++) {
            for(int j = 0; j < key_cons[i].length; j++) {
                System.out.print(key_cons[i][j]);
            }
            System.out.println();
        }
    }

    public static void printAllKeyCons (int[][][] all_keys_constructors) {
        for (int i = 0; i < all_keys_constructors.length; i++) {
            for(int j = 0; j < all_keys_constructors[i].length; j++) {
                for(int k = 0; k < all_keys_constructors[i][j].length; k++) {
                    System.out.print(all_keys_constructors[i][j][k]);
                }
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        char[][] puzzle = {
            {'J', 'S', 'O', 'L', 'U', 'T', 'I', 'S'},
            {'S', 'U', 'N', 'A', 'R', 'U', 'U', 'A'},
            {'N', 'E', 'P', 'T', 'U', 'N', 'E', 'T'},
            {'S', 'O', 'N', 'I', 'E', 'I', 'S', 'U'},
            {'R', 'C', 'E', 'V', 'T', 'R', 'E', 'R'},
            {'A', 'H', 'T', 'R', 'A', 'E', 'S', 'N'},
            {'M', 'M', 'E', 'R', 'C', 'U', 'R', 'Y'}
        };

        String[] keys_arr = {
            "EARTH",
            "JUPITER",
            "MARS",
            "MERCURY",
            "NEPTUNE",
            "SATURN",
            "URANUS"
        };

        Arr keys = new Arr(7);
        // int[][][] all_keys_constructors = new int[7][7][7];
        int[][][] all_keys_constructors = {};

        int puzzle_rows = puzzle.length;
        int puzzle_cols = puzzle[0].length;

        boolean found = false;
        int key_count = 0;
        int row = 0; 
        int col = 0; 
        int dir_state = 0;

        int boundary_length;
        int x_boundary;
        int y_boundary;
        int finding_idx;
            
        // String curr_key = keys_arr[key_count];
        // System.out.println(curr_key);

        while(!found && key_count < keys_arr.length) {
            String curr_key = keys_arr[key_count];

            row = 0;
            while(row < puzzle_rows && keys.keys_stat[key_count] == 0) {
                col = 0;
                while(col < puzzle_cols && keys.keys_stat[key_count] == 0) {
                    dir_state = 0;
                    int[][] key_const = new int[0][0];

                    while(dir_state < 8 && keys.keys_stat[key_count] == 0) {
                        if (dir_state == 0) {
                            boundary_length = row + 1;

                            if(curr_key.length() <= boundary_length) {
                                finding_idx = 0;

                                while ((finding_idx < curr_key.length()) && (getCharFromString(curr_key, finding_idx) == puzzle[row-finding_idx][col])) {
                                    int[] key_idx = {row-finding_idx, col};
                                    // System.out.println(key_idx[0]);
                                    // System.out.println(key_idx[1]);
                                    key_const = appendArr_2d(key_const, key_idx);
                                    // printKeyCons(key_const);
                                    
                                    
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys.keys_stat[key_count] = 1;
                                    all_keys_constructors = appendArr_3d(all_keys_constructors, key_const);
                                    // System.out.println(all_keys_constructors[0][0][0]);
                                } else {
                                    key_const = clearArr_2d(key_const);
                                }
                            }
                        } 
                        else if (dir_state == 1) {
                            boundary_length = row + 1;
                            x_boundary = puzzle_cols - col;

                            if (x_boundary < boundary_length) {
                                boundary_length = x_boundary;
                            }
                            if(curr_key.length() <= boundary_length) {
                                finding_idx = 0;

                                while ((finding_idx < curr_key.length()) && (getCharFromString(curr_key, finding_idx) == puzzle[row-finding_idx][col+finding_idx])) {
                                    int[] key_idx = {row-finding_idx, col+finding_idx};
                                    key_const = appendArr_2d(key_const, key_idx);
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys.keys_stat[key_count] = 1;
                                    all_keys_constructors = appendArr_3d(all_keys_constructors, key_const);
                                } else {
                                    key_const = clearArr_2d(key_const);
                                }
                            }
                        }
                        else if (dir_state == 2) {
                            boundary_length = puzzle_cols - col;

                            if(curr_key.length() <= boundary_length) {
                                finding_idx = 0;

                                while ((finding_idx < curr_key.length()) && (getCharFromString(curr_key, finding_idx) == puzzle[row][col+finding_idx])) {
                                    int[] key_idx = {row, col+finding_idx};
                                    key_const = appendArr_2d(key_const, key_idx);
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys.keys_stat[key_count] = 1;
                                    all_keys_constructors = appendArr_3d(all_keys_constructors, key_const);
                                } else {
                                    key_const = clearArr_2d(key_const);
                                }
                            }
                        }
                        else if (dir_state == 3) {
                            boundary_length = puzzle_cols - col ;
                            y_boundary = puzzle_rows - row;

                            if (y_boundary < boundary_length) {
                                boundary_length = y_boundary;
                            }
                            if (curr_key.length() <= boundary_length) {
                                finding_idx = 0;

                                while ((finding_idx < curr_key.length()) && (getCharFromString(curr_key, finding_idx) == puzzle[row+finding_idx][col+finding_idx])) {
                                    int[] key_idx = {row+finding_idx, col+finding_idx};
                                    key_const = appendArr_2d(key_const, key_idx);
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys.keys_stat[key_count] = 1;
                                    all_keys_constructors = appendArr_3d(all_keys_constructors, key_const);
                                } else {
                                    key_const = clearArr_2d(key_const);
                                }
                            }
                        }
                        else if (dir_state == 4) {
                            boundary_length = puzzle_rows - row;

                            if (curr_key.length() <= boundary_length) {
                                finding_idx = 0;

                                while ((finding_idx < curr_key.length()) && (getCharFromString(curr_key, finding_idx) == puzzle[row+finding_idx][col])) {
                                    int[] key_idx = {row+finding_idx, col};
                                    key_const = appendArr_2d(key_const, key_idx);
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys.keys_stat[key_count] = 1;
                                    all_keys_constructors = appendArr_3d(all_keys_constructors, key_const);
                                } else {
                                    key_const = clearArr_2d(key_const);
                                }
                            }
                        }
                        else if (dir_state == 5) {
                            boundary_length = puzzle_rows - row;
                            x_boundary = col + 1;

                            if(x_boundary < boundary_length){
                                boundary_length = x_boundary;
                            }
                            if (curr_key.length() <= boundary_length) {
                                finding_idx = 0;

                                while ((finding_idx < curr_key.length()) && (getCharFromString(curr_key, finding_idx) == puzzle[row+finding_idx][col-finding_idx])) {
                                    int[] key_idx = {row+finding_idx, col-finding_idx};
                                    key_const = appendArr_2d(key_const, key_idx);
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys.keys_stat[key_count] = 1;
                                    all_keys_constructors = appendArr_3d(all_keys_constructors, key_const);
                                } else {
                                    key_const = clearArr_2d(key_const);
                                }
                            }
                        }
                        else if (dir_state == 6) {
                            boundary_length = col + 1;

                            if (curr_key.length() <= boundary_length) {
                                finding_idx = 0;

                                while ((finding_idx < curr_key.length()) && (getCharFromString(curr_key, finding_idx) == puzzle[row][col-finding_idx])) {
                                    int[] key_idx = {row, col-finding_idx};
                                    key_const = appendArr_2d(key_const, key_idx);
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys.keys_stat[key_count] = 1;
                                    all_keys_constructors = appendArr_3d(all_keys_constructors, key_const);
                                } else {
                                    key_const = clearArr_2d(key_const);
                                }
                            }
                        } else {
                            boundary_length = row + 1;
                            x_boundary = col + 1;

                            if(x_boundary < boundary_length){
                                boundary_length = x_boundary;    
                            }
                            if (curr_key.length() <= boundary_length) {
                                finding_idx = 0;

                                while ((finding_idx < curr_key.length()) && (getCharFromString(curr_key, finding_idx) == puzzle[row-finding_idx][col-finding_idx])) {
                                    int[] key_idx = {row-finding_idx, col-finding_idx};
                                    key_const = appendArr_2d(key_const, key_idx);
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys.keys_stat[key_count] = 1;
                                    all_keys_constructors = appendArr_3d(all_keys_constructors, key_const);
                                } else {
                                    key_const = clearArr_2d(key_const);
                                }
                            }
                        }
                        dir_state++;
                    }
                    col++;
                }
                row++;
            }
            if (isAllOne(keys.keys_stat)) {
                found = true;
            }
            key_count++;
        }

        // output
        output_program(puzzle, all_keys_constructors, keys_arr);

        // printAllKeyCons(all_keys_constructors);

        // System.out.print(all_keys_constructors[0]);
        // System.out.print(keys.keys_stat[1]);
        // System.out.print(keys.keys_stat[2]);
        // System.out.print(keys.keys_stat[3]);
        // System.out.print(keys.keys_stat[4]);
        // System.out.print(keys.keys_stat[5]);
        // System.out.print(keys.keys_stat[6]);
        
        // System.out.println(all_keys_constructors[6][2][1]);
    }
}
