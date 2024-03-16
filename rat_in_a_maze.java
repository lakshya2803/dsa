import java.util.Stack;

public class rat_in_a_maze {
    public boolean issafe(int maze[][],int n,int x,int y,int [][]visited){
        if( (x>=0 && x<n) && (y>=0 && y<n) && visited[x][y] == 0 && maze[x][y] == 1){
            return true;
        }
        return false;
    }
    public void ratinmaze(int [][]maze,int n,int [][]visited,int x,int y,Stack<Character> path){
        // base case 
        if(x==n-1 && y==n-1){
            System.out.println(path);
            return;
        }
        visited[x][y] = 1;
        //down
        int newx = x+1;
        int newy = y;
        if(issafe(maze, n, newx, newy, visited)){
            path.add('D');
            ratinmaze(maze, n, visited, newx, newy, path);
            path.pop();
        }
        //left
        newx = x;
        newy = y-1;
        if(issafe(maze, n, newx, newy, visited)){
            path.add('L');
            ratinmaze(maze, n, visited, newx, newy, path);
            path.pop();
        }
        //right
        newx = x;
        newy = y+1;
        if(issafe(maze, n, newx, newy, visited)){
            path.add('R');
            ratinmaze(maze, n, visited, newx, newy, path);
            path.pop();
        }
        //up
        newx = x-1;
        newy = y;
        if(issafe(maze, n, newx, newy, visited)){
            path.add('U');
            ratinmaze(maze, n, visited, newx, newy, path);
            path.pop();
        }
        visited[x][y] = 0;

    }
    public static void main(String[] args) {
        int n = 4;
        int [][]maze = {{1,0,0,0},
                        {1,1,0,0},
                        {0,1,0,1},
                        {0,1,1,1}};
        int [][]visited = new int[n][n];
        rat_in_a_maze r = new rat_in_a_maze();
        Stack<Character> path = new Stack<>();
        r.ratinmaze(maze, n, visited, 0, 0,path);
    }
}