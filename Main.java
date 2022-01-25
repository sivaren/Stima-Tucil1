
import java.io.*;
import java.util.*;

public class Main {
    // Method membuka file dan mengembalikan matrix dari puzzle
    public static char[][] readPuzzle(String file) throws IOException {
        char[][] puzzle = null;
        String puzzle_item = "";
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            if (line != null) {
                while (line != null && line.length() > 0) {
                    puzzle_item += line.toUpperCase();
                    puzzle_item += "\n";
                    line = reader.readLine();
                }
                String[] puzzle_row = puzzle_item.split("\n");
                int rows = puzzle_row.length;
                String[] puzzle_col = puzzle_row[0].split(" ");
                int cols = puzzle_col.length;

                puzzle = new char[rows][cols];
                for(int i = 0; i < rows; i++) {
                    String str = puzzle_row[i].replaceAll("\s", "");     
                    puzzle[i] = str.toCharArray();
                }
            }
            return puzzle;
        } 
        catch (FileNotFoundException e) {}
        return null;
    }

    // Method membuka file dan mengembalikan array dari keywords
    public static String[] readKeywords(String file) throws IOException {
        String[] key_words = null;
        String key_word = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            if (line != null) {
                while (line != null && line.length() > 0) {
                    line = reader.readLine();
                }
                line = reader.readLine();

                while (line != null) {
                    key_word += line.toUpperCase();
                    key_word += "\n";
                    line = reader.readLine();
                }
                key_words = key_word.split("\n");
            }
            return key_words;
        } 
        catch (FileNotFoundException e) {}
        return null;
    }

    // Method untuk mendapatkan char dari suatu string
    public static char getCharFromString(String str, int index) {
        return str.charAt(index);
    }

    // Method untuk menginisialisasi array status dari key = 0
    public static void fillKeystat(int[] keys_stat) {
        for(int i = 0; i < keys_stat.length; i++){
            keys_stat[i] = 0;
        }
    }
    
    // Method untuk menambahkan elemen dari array 2D
    public static int[][] appendArr_2d(int[][] array, int[] el) {
        int[][] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = el;

        return newArray;
    }
    
    // Method untuk mengosongkan array 2D
    public static int[][] clearArr_2d(int[][] array) {
        int[][] newArr = new int[0][0];    

        return newArr;
    }

    // Method untuk menambahkan elemen dari array 3D
    public static int[][][] appendArr_3d(int[][][] array, int[][] el) {
        int[][][] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = el;
        
        return newArray;
    }
    
    // Method untuk mengecek keberadaan suatu titik {row, col} pada array 2D keys_constructors
    public static boolean isRowCol_avail(int row, int col, int[][] keys_constructors) {
        int[] point = {row, col};
        boolean found = false;

        for(int i = 0; i < keys_constructors.length; i++) {
            if(point[0] == keys_constructors[i][0] && point[1] == keys_constructors[i][1]) {
                found =  true;
                break;
            }
        }
        return found;
    }

    // Method untuk mengecek apakah semua elemen pada array bernilai sama dengan satu
    public static boolean isAllOne(int[] array) {
        for(int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                return false;
            }
        }
        return true;
    }

    // Method untuk menampilkan keywords yang ditemukan, beserta posisinya pada puzzle
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

    public static void print_key_found(char[][] puzzle, String key_word, int[][] key_const) {
        System.out.println(key_word);
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                if (isRowCol_avail(i, j, key_const)) {
                    System.out.print(puzzle[i][j]);
                    System.out.print(" ");
                } else {
                    System.out.print("-");
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        char[][] puzzle = readPuzzle("test/Small1.txt");
        int puzzle_rows = puzzle.length;
        int puzzle_cols = puzzle[0].length;

        String[] keys_arr = readKeywords("test/Small1.txt");
        int[] keys_stat = new int[keys_arr.length];

        // int[][][] all_keys_constructors = {};

        // for(int i = 0; i < puzzle.length; i++) {
        //     for(int j = 0; j < puzzle[0].length; j++) { 
        //         System.out.print(puzzle[i][j]);
        //     }
        //     System.out.println();
        // }
        
        // // System.out.println("===");

        // for(int i = 0; i < keys_arr.length; i++) {
        //     System.out.println(keys_arr[i]);
        // }

        boolean found = false;
        int key_count = 0;
        int row = 0; 
        int col = 0; 
        int dir_state = 0;

        int boundary_length;
        int x_boundary;
        int y_boundary;
        int finding_idx;
        String curr_key;

        long startTime = System.nanoTime();

        while(!found && key_count < keys_arr.length) {
            curr_key = keys_arr[key_count];

            row = 0;
            while(row < puzzle_rows && keys_stat[key_count] == 0) {
                col = 0;
                while(col < puzzle_cols && keys_stat[key_count] == 0) {
                    dir_state = 0;
                    int[][] key_const = new int[0][0];

                    while(dir_state < 8 && keys_stat[key_count] == 0) {
                        if (dir_state == 0) {
                            boundary_length = row + 1;

                            if(curr_key.length() <= boundary_length) {
                                finding_idx = 0;

                                while ((finding_idx < curr_key.length()) && (getCharFromString(curr_key, finding_idx) == puzzle[row-finding_idx][col])) {
                                    int[] key_idx = {row-finding_idx, col};
                                    key_const = appendArr_2d(key_const, key_idx);
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_const);
                                    key_const = clearArr_2d(key_const);
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
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_const);
                                    key_const = clearArr_2d(key_const);
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
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_const);
                                    key_const = clearArr_2d(key_const);
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
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_const);
                                    key_const = clearArr_2d(key_const);
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
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_const);
                                    key_const = clearArr_2d(key_const);
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
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_const);
                                    key_const = clearArr_2d(key_const);
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
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_const);
                                    key_const = clearArr_2d(key_const);
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
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_const);
                                    key_const = clearArr_2d(key_const);
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
            if (isAllOne(keys_stat)) {
                found = true;
            }
            key_count++;
        }
        long elapsedTime = System.nanoTime() - startTime;

        // output
        // output_program(puzzle, all_keys_constructors, keys_arr);
        System.out.println(found);
        System.out.println("===");
        System.out.println("Execution time: " + elapsedTime/1000000 + " ms");
    }
}