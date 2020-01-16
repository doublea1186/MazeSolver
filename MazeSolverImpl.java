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
        // TODO: implements
        int rows = maze[0].length;
        int columns = maze.length;
        int[][] solution = new int[rows][columns];

        if (checkExceptions(maze, sourceCoord) || checkExceptions(maze, goalCoord)) {
            throw new IllegalArgumentException();
        } else {
            if (findSolution(maze, sourceCoord, goalCoord)) {

            }
        }

        return null;
    }

    public static boolean findSolution(int[][] maze, Coordinate current, Coordinate end) {
        if (current.equals(end)) {
            return true;
        } else if (withinBounds(maze, increment(current, 0, -1))) {

        } else if (withinBounds(maze, increment(current, 0, 1))) {

        } else if (withinBounds(maze, increment(current, -1, 0))) {

        } else if (withinBounds(maze, increment(current, 1, 0))) {

        }
            return false;
    }

    public static boolean checkExceptions(int[][] maze, Coordinate x) {
        int rows = maze[0].length;
        int columns = maze.length;

        if (maze == null || rows <= 1 || columns <= 1 || !withinBounds(maze, x)) {
            return false;
        }

        return true;
    }

    public static boolean withinBounds(int[][] maze, Coordinate point) {
        int x = point.getX();
        int y = point.getY();

        if (maze[y][x] == 1 || x < 0 || y < 0 || maze[0].length < y || maze.length < x) {
            return false;
        }

        return true;
    }

    public static Coordinate increment(Coordinate point, int x, int y) {

        return new Coordinate(point.getX() + x, point.getY() + y);
    }
}
