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
                if ((col + 1) % 3 == 0 && col < 8) System.out.print("| ");
            }
            System.out.println();
            if ((row + 1) % 3 == 0 && row < 8) System.out.println("---------------------");
        }

        boolean[] validationResult = new boolean[9];
        Thread[] validatorThreads = new Thread[9];

        for (int row = 0; row < 9; row++) {
            validatorThreads[row] = new RowValidator(sudokuGrid, validationResult, row);
            validatorThreads[row].start();
        }

        for (int row = 0; row < 9; row++) {
            try {
                validatorThreads[row].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //for loop to bring column in to check each column
        for(int col = 0; col < 9; col++){
            validatorThreads[col] = new ColumnValidator(sudokuGrid, validationResult, col);
        validatorThreads[col].start();
        }

        //Print statement showing the validation for rows
        for (int row = 0; row < 9; row++) {
            System.out.println("Row " + row + " validation result: " + validationResult[row]);
        }
        //Print statemnt showinf the validation for columns
        for(int col = 0; col < 9; col++){
            System.out.println("Col" + col + " Validation result: " + validationResult[col]);
        }

    }
    
}
