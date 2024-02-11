public class SudokuDisplay {
    public static void main(String[] args) {
        int[][] sudokuGrid = {
            {0,4,0,0,2,6,0,0,8},
            {8,3,0,0,7,0,0,0,5},
            {1,0,6,9,0,0,0,4,0},
            {0,0,0,0,0,9,4,0,0},
            {0,0,1,6,5,0,3,0,0},
            {3,0,0,4,0,0,0,2,0},
            {7,0,0,0,0,8,0,5,0},
            {0,2,9,0,0,1,0,0,6},
            {0,0,0,0,0,3,1,7,0},
        };

        for (int row = 0; row < sudokuGrid.length; row++) {
            for (int col = 0; col < sudokuGrid[row].length; col++) {
                System.out.print(sudokuGrid[row][col] + " ");
            //    if ((col + 1) % 3 == 0 && col < 8) System.out.print("| ");
            }
            System.out.println();
            //if ((row + 1) % 3 == 0 && row < 8) System.out.println("---------------------");
        }
    }
}
