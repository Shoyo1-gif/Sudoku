public class Sudoku implements Runnable{
    public void run(){
        System.out.println("This is a thread outside the main");
    }
    public static void main(String[] args) {
        Sudoku obj = new Sudoku();
        Thread thread = new Thread(obj);
        thread.start();
        System.out.println("Hello world this thread is inside the main methodcan ");

    }
    /*public static int[][] Grid = {
        {0,4,0,0,2,6,0,0,8};
        {8,3,0,0,7,0,0,0,5};
        {1,0,6,9,0,0,0,4,0};
        {0,0,0,0,0,9,4,0,0};
        {0,0,1,6,5,0,3,0,0};
        {3,0,0,4,0,0,0,2,0};
        {7,0,0,0,0,8,0,5,0};
        {0,2,9,0,0,1,0,0,6};
        {0,0,0,0,0,3,1,7,0};
    };

    private int[][] board;
    public static final int empty = 0;
    public static final int size = 9;*/
    
    
}