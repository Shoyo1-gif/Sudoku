public class SudokuDisplay {
    public static void main(String[] args) {
        //hard coded the sudoku values
        int[][] sudokuGrid = {
            {6,2,4,5,3,9,1,8,7},
            {5,1,9,7,2,8,6,3,4},
            {8,3,7,6,1,4,2,9,5},
            {1,4,3,8,6,5,7,2,9},
            {9,5,8,2,4,7,3,6,1},
            {7,6,2,3,9,1,4,5,8},
            {3,7,1,9,5,6,8,4,2},
            {4,9,6,1,8,2,5,7,3},
            {2,8,5,4,7,3,9,1,4},
        };

        //Printed out the grid and the sudoku with a forloop
        for (int row = 0; row < sudokuGrid.length; row++) {
            for (int col = 0; col < sudokuGrid[row].length; col++) {
                System.out.print(sudokuGrid[row][col] + " ");
                if ((col + 1) % 3 == 0 && col < 8) System.out.print("| ");
            }
            System.out.println();
            if ((row + 1) % 3 == 0 && row < 8) System.out.println("---------------------");
        }
        
        //Storing a validation result as boolean for true and false outputs
        boolean[] validationResult = new boolean[9];
        Thread[] validatorThreads = new Thread[9];

        //for loop to start the row thread using .start()
        for (int row = 0; row < 9; row++) {
            validatorThreads[row] = new RowValidator(sudokuGrid, validationResult, row);
            validatorThreads[row].start();
        }

        //put the try and catch block for the JOIN() method
        for (int row = 0; row < 9; row++) {
            try {
                validatorThreads[row].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //for loop to start the column thread using .start()
        for(int col = 0; col < 9; col++){
            validatorThreads[col] = new ColumnValidator(sudokuGrid, validationResult, col);
        validatorThreads[col].start();
        }

        //addded an index varialble as that will determine the subgrid
        int index = 0;
        //started the subgrid thread
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
            validatorThreads[index] = new SubgridValidator(sudokuGrid, validationResult, row, col, index);
            validatorThreads[index].start();
            index++;
            }
        }



        //Print statement showing the validation for the subgrids
        for (int i = 0; i < validationResult.length; i++) {
            System.out.println("Subgrid " + i + " validation result: " + validationResult[i]);
            }
        //Print statement showing the validation for rows
        for (int row = 0; row < 9; row++) {
            System.out.println("Row " + row + " validation result: " + validationResult[row]);
        }
        //Print statemnt showinf the validation for columns
        for(int col = 0; col < 9; col++){
            System.out.println("Col " + col + " Validation result: " + validationResult[col]);
        }

    }
    
}
