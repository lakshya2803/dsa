import java.util.*;

public class n_queen_backtracking {

    static boolean issafe(int row, int col, char[][] board){
        // horizontal
        for(int i =0;i<board.length;i++){
            if(board[row][i] == 'Q'){
                return false;
            }
        }
        // vertical
        for(int i=0;i<board.length;i++){
            if(board[i][col] == 'Q'){
                return false;
            }
        }
        // upper left
        int r= row;
        for(int c = col;c>=0 &&r>=0;c--,r--){
            if(board[r][c] == 'Q'){
                return false;
            }
        }
        // upper right
        r = row;
        for(int c = col;c<board.length && r>=0;c++,r--){
            if(board[r][c] == 'Q'){
                return false;
            }
        }
        // bottom left
        r=row;
        for(int c= col;r<board.length && c>=0;c--,r++){
            if(board[r][c] == 'Q'){
                return false;
            }
        }
        //bottom right
        r = row;
        for(int c = col;c<board.length && r<board.length; c++,r++){
            if(board[r][c] == 'Q'){
                return false;
            }
        }

        return true;
    }

    static void saveboard(char [][]board, List<List<String>> boards){
        String row = "";
        List<String> nboard = new ArrayList<>();
        for(int i=0;i<board.length;i++){
            row = "";
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]== 'Q'){
                    row += 'Q';
                }
                else{
                    row += '.';
                }
            }
            nboard.add(row);
        }
        boards.add(nboard);
    }

    static void nqueen(char [][]board,int row, List<List<String>> boards){
        int n = board.length;
        if(row == n){
            saveboard(board, boards);
            return;
        }
        for(int col = 0;col<n;col++){
            if(issafe(row, col, board))
            {
                board[row][col] = 'Q';
                nqueen(board,row+1,boards);
                board[row][col] = '.';
            }
        }
    }
    public static List<List<String>> nqu(int n){
        List<List<String>> allboards = new ArrayList<>();
        char [][]board = new char[n][n];
        nqueen(board, 0, allboards);
        return allboards;
    }
    public static void main(String[] args){
        List<List<String>> chess = new ArrayList<>();
        chess = nqu(4);
        System.out.println(chess);
    }
}
