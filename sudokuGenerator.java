import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Arrays;

public class sudokuGenerator {

    private static final String API_URL = "https://sudoku-generator1.p.rapidapi.com/sudoku/generate";
    private static final String API_KEY = "3f7802f995mshcb8dee8c4d2f88dp139051jsn6afbae4ac0b8";
    private static final String API_HOST = "sudoku-generator1.p.rapidapi.com";

    public int[][] generateSudoku(String difficulty) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "?difficulty=" + difficulty))
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HOST)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return parseSudokuResponse(response.body());
        } else {
            throw new IOException("API request failed with status code: " + response.statusCode());
        }
    }

    private static int[][] parseSudokuResponse(String responseBody) {
        // Extract the puzzle string from the JSON response
        String puzzleString = responseBody.split("\"puzzle\":\"")[1].split("\"")[0];

        int[][] grid = new int[9][9];
        int index = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = Character.getNumericValue(puzzleString.charAt(index++));
            }
        }
        return grid;
    }

    // Utility method to print the Sudoku grid
    public static void printSudoku(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}
    // public static void main(String[] args) {
    //     try {
    //         int[][] sudoku = generateSudoku("easy");
    //         System.out.println("Generated Sudoku puzzle:");
    //         printSudoku(sudoku);
    //     } catch (IOException | InterruptedException e) {
    //         e.printStackTrace();
    //     }
    // }
