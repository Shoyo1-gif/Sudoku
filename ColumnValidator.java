class ColumnValidator extends Thread {
    private int[][] board;
    private boolean[] validationResult;
    private int column;

    public ColumnValidator(int[][] board, boolean[] validationResult, int column) {
        this.board = board;
        this.validationResult = validationResult;
        this.column = column;
    }

    @Override
    public void run() {
        //boolean[] digitCheck = new boolean[9];
        for (int i = 0; i < 9; i++) {
            int num = board[i][column];
            if (num < 1 || num > 9) {
                validationResult[column] = false;
                return;
            }
            //digitCheck[num - 1] = true;
        }
        validationResult[column] = true;
    }
}