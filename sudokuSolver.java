public class sudokuSolver {

	public void Print(int[][] grid, int row, int col) {
		if (col == 9) {
			row++;
			col = 0;
		}
		if (row == 9) {
			Display(grid);
			return;
		}

		if (grid[row][col] != 0) {
			Print(grid, row, col + 1);
		} else {
			for (int val = 1; val <= 9; val++) {
				if (isitsafe(grid, row, col, val)) {
					grid[row][col] = val;
					Print(grid, row, col + 1);
					grid[row][col] = 0;
				}
			}
		}
	}

	public static boolean isitsafe(int[][] grid, int row, int col, int val) {
		
		// row
		for (int r = 0; r < grid.length; r++) {
			if (grid[r][col] == val) {
				return false;
			}
		}
		// col
		for (int c = 0; c < grid.length; c++) {
			if (grid[row][c] == val) {
				return false;
			}
		}

		// 3*3 matrix
		int r = row - row % 3;
		int c = col - col % 3;
		for (int i = r; i < r + 3; i++) {
			for (int j = c; j < c + 3; j++) {
				if (grid[i][j] == val) {
					return false;
				}
			}
		}
		return true;

	}

	public static void Display(int[][] grid) {
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}
}
