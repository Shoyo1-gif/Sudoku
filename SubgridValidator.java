class SubgridValidator extends Thread {
    private int[][] board;
    private boolean[] validationResult;
    private int startRow, startColumn, index;

    public SubgridValidator(int[][] board, boolean[] validationResult, int startRow, int startColumn, int index) {
        this.board = board;
        this.validationResult = validationResult;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.index = index;
    }

    @Override
    public void run() {
        
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                int num = board[startRow + row][startColumn + column];
                if (num < 1 || num > 9) {
                    validationResult[index] = false;
                    return;
                }
            }
        }
        validationResult[index] = true;
    }
}