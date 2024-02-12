class RowValidator extends Thread {
    private int[][] board;
    private boolean[] validationResult;
    private int row;

    public RowValidator(int[][] board, boolean[] validationResult, int row) {
        this.board = board;
        this.validationResult = validationResult;
        this.row = row;
    }

    @Override
    public void run() {
        for (int i = 0; i < 9; i++) {
            int num = board[row][i];
            if (num < 1 || num > 9) {
                validationResult[row] = false;
                return;
            } 
        }
        validationResult[row] = true;
    }
}