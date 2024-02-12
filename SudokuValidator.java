public class SudokuValidator {
    public static void main(String[] args) {
        class RowValidator extends Thread {
            private final int[][] board;
            private final boolean[] validationResult;
            private final int row;
        
            public RowValidator(int[][] board, boolean[] validationResult, int row) {
                this.board = board;
                this.validationResult = validationResult;
                this.row = row;
            }
        
            @Override
            public void run() {
                boolean[] digitCheck = new boolean[9];
                for (int i = 0; i < 9; i++) {
                    int num = board[row][i];
                    if (num < 1 || num > 9 || digitCheck[num - 1]) {
                        validationResult[row] = false;
                        return;
                    }
                    digitCheck[num - 1] = true;
                }
                validationResult[row] = true;
            }
        }
        
        class ColumnValidator extends Thread {
            private final int[][] board;
            private final boolean[] validationResult;
            private final int column;
        
            public ColumnValidator(int[][] board, boolean[] validationResult, int column) {
                this.board = board;
                this.validationResult = validationResult;
                this.column = column;
            }
        
            @Override
            public void run() {
                boolean[] digitCheck = new boolean[9];
                for (int i = 0; i < 9; i++) {
                    int num = board[i][column];
                    if (num < 1 || num > 9 || digitCheck[num - 1]) {
                        validationResult[column] = false;
                        return;
                    }
                    digitCheck[num - 1] = true;
                }
                validationResult[column] = true;
            }
        }
        
        class SubgridValidator extends Thread {
            private final int[][] board;
            private final boolean[] validationResult;
            private final int startRow, startColumn, index;
        
            public SubgridValidator(int[][] board, boolean[] validationResult, int startRow, int startColumn, int index) {
                this.board = board;
                this.validationResult = validationResult;
                this.startRow = startRow;
                this.startColumn = startColumn;
                this.index = index;
            }
        
            @Override
            public void run() {
                boolean[] digitCheck = new boolean[9];
                for (int row = 0; row < 3; row++) {
                    for (int column = 0; column < 3; column++) {
                        int num = board[startRow + row][startColumn + column];
                        if (num < 1 || num > 9 || digitCheck[num - 1]) {
                            validationResult[index] = false;
                            return;
                        }
                        digitCheck[num - 1] = true;
                    }
                }
                validationResult[index] = true;
            }
        }
        
        int[][] board = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        boolean[] validationResult = new boolean[27]; // 9 for rows, 9 for columns, 9 for subgrids

        // Validate rows
        for (int i = 0; i < 9; i++) {
            new RowValidator(board, validationResult, i).start();
        }

        // Validate columns
        for (int i = 0; i < 9; i++) {
            new ColumnValidator(board, validationResult, i + 9).start(); // Offset by 9 for columns
        }

        // Validate subgrids
        for (int i = 0; i < 9; i++) {
            int startRow = (i / 3) * 3;
            int startColumn = (i % 3) * 3;
            new SubgridValidator(board, validationResult, startRow, startColumn, i + 18).start(); // Offset by 18 for subgrids
        }

        // Wait for all threads to complete and aggregate results
        boolean isValid = true;
        for (boolean result : validationResult) {
            isValid &= result;
        }

        System.out.println("Sudoku is " + (isValid ? "valid" : "invalid"));
    }
}
