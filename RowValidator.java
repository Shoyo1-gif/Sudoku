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