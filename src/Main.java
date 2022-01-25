package src;

public class Main {
    public static void main(String[] args) {
        char[][] puzzle = readPuzzle("../test/in.txt");
        int puzzle_rows = puzzle.length;
        int puzzle_cols = puzzle[0].length;

        String[] keys_arr = readKeywords("../test/in.txt");
        int[] keys_stat = new int[keys_arr.length];
        int[][][] all_keys_constructors = {};

        boolean found = false;
        int key_count = 0;
        int row = 0; 
        int col = 0; 
        int dir_state = 0;

        int boundary_length;
        int x_boundary;
        int y_boundary;
        int finding_idx;

        while(!found && key_count < keys_arr.length) {
            String curr_key = keys_arr[key_count];

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
                                    // System.out.println(key_idx[0]);
                                    // System.out.println(key_idx[1]);
                                    key_const = appendArr_2d(key_const, key_idx);
                                    // printKeyCons(key_const);
                                    
                                    
                                    finding_idx++;
                                }
                                if (finding_idx == curr_key.length()) {
                                    keys_stat[key_count] = 1;
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
                                    keys_stat[key_count] = 1;
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
                                    keys_stat[key_count] = 1;
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
                                    keys_stat[key_count] = 1;
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
                                    keys_stat[key_count] = 1;
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
                                    keys_stat[key_count] = 1;
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
                                    keys_stat[key_count] = 1;
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
                                    keys_stat[key_count] = 1;
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
            if (isAllOne(keys_stat)) {
                found = true;
            }
            key_count++;
        }

        // output
        output_program(puzzle, all_keys_constructors, keys_arr);
    }
}