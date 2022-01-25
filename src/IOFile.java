package src;
import java.io.*;

public class IOFile {
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


    public static void main(String[] args) throws IOException {
        char[][] puzzle_raw = readPuzzle("in.txt");
        for(int i = 0; i < puzzle_raw.length; i++) {
            for(int j = 0; j < puzzle_raw[0].length; j++) { 
                System.out.print(puzzle_raw[i][j]);
            }
            System.out.println();
        }
        
        System.out.println("===");

        String[] key_words = readKeywords("in.txt");
        for(int i = 0; i < key_words.length; i++) {
            System.out.println(key_words[i]);
        }
    }
}
