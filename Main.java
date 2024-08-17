import java.io.*;
import java.util.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] grid = inputGrid();
        if (grid != null) {
            sudokuSolver solver = new sudokuSolver();
            solver.Print(grid, 0, 0);
        } else {
            System.out.println("Failed to initialize the Sudoku grid.");
        }
    }

    // Method to select input method
    public static int[][] inputGrid() {
        System.out.println("Choose input method:");
        System.out.println("1. Read from a text file");
        System.out.println("2. Input manually");
        System.out.println("3. Use example grid");
        System.out.println("4. Generate random using an API key");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return readGridFromFile();
            case 2:
                return inputGridManually();
            case 3:
                return exampleGrid();
            case 4:
                return generateGridFromAPI();
            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }

    // 1. Read grid from a text file
    public static int[][] readGridFromFile() {
      try {
          File file = new File("board.txt");
          Scanner fileScanner = new Scanner(file);
          int[][] grid = new int[9][9];
          int row = 0;
  
          while (fileScanner.hasNextLine() && row < 9) {
              String line = fileScanner.nextLine().trim();
              String[] numbers = line.split("\\s+");
              
              if (numbers.length != 9) {
                  System.out.println("Error: Line " + (row + 1) + " does not contain 9 numbers.");
                  return null;
              }
  
              for (int col = 0; col < 9; col++) {
                  try {
                      int num = Integer.parseInt(numbers[col]);
                      if (num >= 0 && num <= 9) {
                          grid[row][col] = num;
                      } else {
                          System.out.println("Error: Invalid number " + num + " at row " + (row + 1) + ", column " + (col + 1));
                          return null;
                      }
                  } catch (NumberFormatException e) {
                      System.out.println("Error: Invalid number format at row " + (row + 1) + ", column " + (col + 1));
                      return null;
                  }
              }
              row++;
          }
  
          fileScanner.close();
  
          if (row != 9) {
              System.out.println("Error: File does not contain 9 rows.");
              return null;
          }
  
          return grid;
      } catch (FileNotFoundException e) {
          System.out.println("Error: File 'board.txt' not found.");
          return null;
      }
  }

    // 2. Input grid manually
    public static int[][] inputGridManually() {
        int[][] grid = new int[9][9];
        System.out.println("Enter the Sudoku grid (row by row, use 0 for empty cells):");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }
        return grid;
    }

    // 3. Use example grid
    public static int[][] exampleGrid() {
        return new int[][] { 
            { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, 
            { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
            { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
            { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
            { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
            { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
            { 0, 0, 5, 2, 0, 6, 3, 0, 0 } 
        };
    }

    // 4. Generate random grid using an API key
    public static int[][] generateGridFromAPI() {
        sudokuGenerator generator = new sudokuGenerator();
        try {
          return generator.generateSudoku("easy");
      } catch (IOException | InterruptedException e) {
          e.printStackTrace();
      }
    }
}
