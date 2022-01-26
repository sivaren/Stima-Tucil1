package src;

import java.io.*;
import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);

    // Method untuk menampilkan layar awal
    public static void displayMenu() {
        System.out.println("==========================================");
        System.out.println("      (^///^) SELAMAT DATANG (^///^)      ");
        System.out.println("==========================================");
        System.out.println("Masukkan nama file (xxx.txt): ");
        System.out.print("> ");
    }

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
                    char[] array = str.toCharArray();
                    for(int j = 0; j < cols; j++){
                        puzzle[i][j] = array[j];
                    }    
                    // puzzle[i] = str.toCharArray();
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
    
    // Method untuk mengosongkan array 2D
    public static int[][] clearArr_2d(int[][] array) {
        int[][] newArr = new int[0][0];    

        return newArr;
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

    // Method untuk menampilkan keywords yang ditemukan,
    // posisinya pada puzzle, serta jumlah perbandingan huruf yang dilakukan
    public static void print_key_found(char[][] puzzle, String key_word, int key_comp, int[][] key_const) {
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
        System.out.println("Total perbandingan huruf: " + key_comp);
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        String inputFile;
        displayMenu();
        inputFile = in.nextLine();
        System.out.println("==========================================");
        
        char[][] puzzle = readPuzzle("test/" + inputFile);
        int puzzle_rows = puzzle.length;
        int puzzle_cols = puzzle[0].length;
        
        String[] keys_arr = readKeywords("test/" + inputFile);
        int[] keys_stat = new int[keys_arr.length];
        int[] key_comp = new int[keys_arr.length];

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
                    while(dir_state < 8 && keys_stat[key_count] == 0) {
                        int[][] key_const = new int[curr_key.length()][2];
                        if (dir_state == 0) {
                            boundary_length = row + 1;

                            if(curr_key.length() <= boundary_length) {
                                finding_idx = 0;

                                while ((finding_idx < curr_key.length()) && (getCharFromString(curr_key, finding_idx) == puzzle[row-finding_idx][col])) {
                                    int[] key_idx = new int[2];
                                    key_idx[0] = row-finding_idx;
                                    key_idx[1] = col;
                                    key_const[finding_idx] = key_idx;
                                    key_comp[key_count]++;
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_comp[key_count], key_const);
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
                                    int[] key_idx = new int[2];
                                    key_idx[0] = row-finding_idx;
                                    key_idx[1] = col+finding_idx;
                                    key_const[finding_idx] = key_idx;
                                    key_comp[key_count]++;
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_comp[key_count], key_const);
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
                                    int[] key_idx = new int[2];
                                    key_idx[0] = row;
                                    key_idx[1] = col+finding_idx;
                                    key_const[finding_idx] = key_idx;
                                    key_comp[key_count]++;
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_comp[key_count], key_const);
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
                                    int[] key_idx = new int[2];
                                    key_idx[0] = row+finding_idx;
                                    key_idx[1] = col+finding_idx;
                                    key_const[finding_idx] = key_idx;
                                    key_comp[key_count]++;
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_comp[key_count], key_const);
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
                                    int[] key_idx = new int[2];
                                    key_idx[0] = row+finding_idx;
                                    key_idx[1] = col;
                                    key_const[finding_idx] = key_idx;
                                    key_comp[key_count]++;
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_comp[key_count], key_const);
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
                                    int[] key_idx = new int[2];
                                    key_idx[0] = row+finding_idx;
                                    key_idx[1] = col-finding_idx;
                                    key_const[finding_idx] = key_idx;
                                    key_comp[key_count]++;
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_comp[key_count], key_const);
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
                                    int[] key_idx = new int[2];
                                    key_idx[0] = row;
                                    key_idx[1] = col-finding_idx;
                                    key_const[finding_idx] = key_idx;
                                    key_comp[key_count]++;
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_comp[key_count], key_const);
                                    key_const = clearArr_2d(key_const);
                                } else {
                                    key_const = clearArr_2d(key_const);
                                }
                            }
                        } 
                        else if (dir_state == 7) {
                            boundary_length = row + 1;
                            x_boundary = col + 1;

                            if(x_boundary < boundary_length){
                                boundary_length = x_boundary;    
                            }
                            if (curr_key.length() <= boundary_length) {
                                finding_idx = 0;

                                while ((finding_idx < curr_key.length()) && (getCharFromString(curr_key, finding_idx) == puzzle[row-finding_idx][col-finding_idx])) {
                                    int[] key_idx = new int[2];
                                    key_idx[0] = row-finding_idx;
                                    key_idx[1] = col-finding_idx;
                                    key_const[finding_idx] = key_idx;
                                    key_comp[key_count]++;
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys_stat[key_count] = 1;
                                    print_key_found(puzzle, curr_key, key_comp[key_count], key_const);
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
        System.out.println("=======================");
        System.out.println("Execution time: " + (elapsedTime/1000000) + " ms");
        System.out.println("=======================");
    }
}