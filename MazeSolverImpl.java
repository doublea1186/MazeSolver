public class MazeSolverImpl {

    /**
     * You should write your code within this method. A good rule of thumb, especially with
     * recursive problems like this, is to write your input exception handling within this
     * method and then use helper methods to carry out the actual recursion.
     * <p>
     * How you set up the recursive methods is up to you, but note that since this is a *static*
     * method, all helper methods that it calls must *also* be static. Make them package-private
     * (i.e. without private or public modifiers) so you can test them individually.
     *
     * @param maze See the writeup for more details about the format of the input maze.
     * @param sourceCoord The source (starting) coordinate
     * @param goalCoord The goal (ending) coordinate
     * @return a matrix of the same dimension as the input maze containing the solution
     * path marked with 1's, or null if no path exists. See the writeup for more details.
     * @throws IllegalArgumentException in the following instances:
     * 1. If the maze is null
     * 2. If m * n <= 1 where m and n are the dimensions of the maze
     * 3. If either the source coordinate OR the goal coordinate are out of the matrix bounds.
     * 4. If your source or goal coordinate are on a blocked tile.
     */
    public static int[][] solveMaze(int[][] maze, Coordinate sourceCoord, Coordinate goalCoord) {

        if (!checkExceptions(maze, sourceCoord) || !checkExceptions(maze, goalCoord)) {
            throw new IllegalArgumentException();
        } else {
            int rows = maze.length;
            int columns = maze[0].length;
            int[][] solution = new int[rows][columns];

            if (findSolution(maze, sourceCoord, goalCoord, solution)) {
                return solution;
            }
        }

        return null;
    }

    public static boolean findSolution(int[][] maze, Coordinate current,
                                       Coordinate end, int[][] path) {
        int y = current.getY();
        int x = current.getX();
        path[y][x] = 1;
        maze[y][x] = 1;
        Coordinate down = increment(current, 0, -1);
        Coordinate up = increment(current, 0, 1);
        Coordinate left = increment(current, -1, 0);
        Coordinate right = increment(current, 1, 0);

        if (current.equals(end)) {
            return true;
        }
        if (checkExceptions(maze, down) && !isVisited(maze, down)) {
            if (findSolution(maze, down, end, path)) {
                return true;
            }
        }
        if (checkExceptions(maze, up) && !isVisited(maze, up)) {
            if (findSolution(maze, up, end, path)) {
                return true;
            }
        }
        if (checkExceptions(maze, left) && !isVisited(maze, left)) {
            if (findSolution(maze, left, end, path)) {
                return true;
            }
        }
        if (checkExceptions(maze, right) && !isVisited(maze, right)) {
            if (findSolution(maze, right, end, path)) {
                return true;
            }
        }

        path[y][x] = 0;
        return false;
    }

    public static boolean isVisited(int[][] maze, Coordinate x) {
        return maze[x.getY()][x.getX()] == 1;
    }

    public static boolean checkExceptions(int[][] maze, Coordinate point) {
        int x = point.getX();
        int y = point.getY();

        if (x >= 0 && y >= 0 && maze != null && maze.length > 0 && maze[0].length > 0
                && maze.length * maze[0].length > 1 && maze.length - 1 >= y &&
                maze[0].length - 1 >= x && maze[y][x] != 1) {

            return true;
        }
        return false;
    }

    public static Coordinate increment(Coordinate point, int x, int y) {
        return new Coordinate(point.getX() + x, point.getY() + y);
    }

}
