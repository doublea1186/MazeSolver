import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MazeSolverImplTest {

    private int[][] smallWriteupMaze;
    private int[][] bigWriteupMaze;
    private int[][] closedMaze;
    private int[][] openMaze;
    private int[][] mediumMaze;

    @Before
    public void setupTestMazes() {
        smallWriteupMaze = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        closedMaze = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };

        openMaze = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        mediumMaze = new int[][]{
                {1, 0, 1, 0},
                {0, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 0, 0, 1},
                {1, 0, 1, 1},
                {0, 0, 1, 1},
                {0, 1, 1, 1}
        };

        bigWriteupMaze = new int[][]{
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

    }

    @Test
    public void testReturnsSmallMazeSolutionPathInWriteup() {
        int[][] solutionPath = {
                {1, 1, 1, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 0, 0}
        };
        assertArrayEquals(solutionPath, MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(0, 0), new Coordinate(0, 2)));
    }

    @Test
    public void testReturnsBigMazeSolutionPathInWriteup() {
        int[][] bigWriteupSolution = new int[][]{
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] returnedPath = MazeSolverImpl.solveMaze(bigWriteupMaze, new Coordinate(2, 0),
                new Coordinate(4, 0));
        assertArrayEquals(bigWriteupSolution, returnedPath);
    }

    /*
      Note: the above tests are the ones included in the writeup and NOT exhaustive. The autograder
      uses other test cases not listed above. Please thoroughly read all stub files, including
      CoordinateTest.java!

      For help with creating test cases, please see this link:
      https://www.seas.upenn.edu/~cis121/current/testing_guide.html
     */

    @Test
    public void testIncrement() {
        Coordinate x = new Coordinate(0, 0);
        Coordinate y = MazeSolverImpl.increment(x, 1, 1);
        assertEquals(y.getX(), 1);
        assertEquals(y.getY(), 1);
        y = MazeSolverImpl.increment(y, -2, -2);
        assertEquals(y.getX(), -1);
        assertEquals(y.getY(), -1);
    }

    @Test
    public void testBounds() {
        Coordinate a = new Coordinate(0, 1);
        Coordinate x = new Coordinate(0, 0);
        Coordinate y = new Coordinate(-1, -1);
        Coordinate z = new Coordinate(10000, 10000);
        assertTrue(MazeSolverImpl.checkExceptions(smallWriteupMaze, x));
        assertFalse(MazeSolverImpl.checkExceptions(smallWriteupMaze, a));
        assertFalse(MazeSolverImpl.checkExceptions(smallWriteupMaze, y));
        assertFalse(MazeSolverImpl.checkExceptions(smallWriteupMaze, z));
    }

    @Test
    public void testExceptions() {
        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(0, 1);
        int[][] m1 = new int[1][1];
        assertFalse(MazeSolverImpl.checkExceptions(null, a));
        assertFalse(MazeSolverImpl.checkExceptions(smallWriteupMaze, b));
        assertFalse(MazeSolverImpl.checkExceptions(m1, a));
        assertTrue(MazeSolverImpl.checkExceptions(smallWriteupMaze, a));
    }

    @Test
    public void testFindSolution() {
        int[][] empty = new int[4][4];
        Coordinate x = new Coordinate(3, 3);
        Coordinate y = new Coordinate(0, 0);

        assertFalse(MazeSolverImpl.findSolution(smallWriteupMaze, x, y, empty));
        assertFalse(MazeSolverImpl.findSolution(smallWriteupMaze, y, x, empty));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrownExceptions() {
        int[][] maze = new int[1][1];
        Coordinate x = new Coordinate(-1, -1);

        MazeSolverImpl.solveMaze(maze, x, x);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInput() {
        Coordinate x = new Coordinate(0, 0);

        MazeSolverImpl.solveMaze(null, x, x);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() {
        int[][] maze = new int[0][0];
        Coordinate x = new Coordinate(0, 0);

        MazeSolverImpl.solveMaze(maze, x, x);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSourceYNegativeBounds() {
        Coordinate source = new Coordinate(0, -1);
        Coordinate goal = new Coordinate(0, 0);
        MazeSolverImpl.solveMaze(openMaze, source, goal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSourceYPositiveBounds() {
        Coordinate source = new Coordinate(0, 100);
        Coordinate goal = new Coordinate(0, 0);
        MazeSolverImpl.solveMaze(openMaze, source, goal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSourceXNegativeBounds() {
        Coordinate source = new Coordinate(-1, 0);
        Coordinate goal = new Coordinate(0, 0);
        MazeSolverImpl.solveMaze(openMaze, source, goal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSourceXPositiveBounds() {
        Coordinate source = new Coordinate(100, 0);
        Coordinate goal = new Coordinate(0, 0);
        MazeSolverImpl.solveMaze(openMaze, source, goal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGoalYNegativeBounds() {
        Coordinate source = new Coordinate(0, 0);
        Coordinate goal = new Coordinate(0, -1);
        MazeSolverImpl.solveMaze(openMaze, source, goal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGoalYPositiveBounds() {
        Coordinate source = new Coordinate(0, 0);
        Coordinate goal = new Coordinate(0, 100);
        MazeSolverImpl.solveMaze(openMaze, source, goal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGoalXNegativeBounds() {
        Coordinate source = new Coordinate(0, 0);
        Coordinate goal = new Coordinate(-1, 0);
        MazeSolverImpl.solveMaze(openMaze, source, goal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGoalXPositiveBounds() {
        Coordinate source = new Coordinate(100, 0);
        Coordinate goal = new Coordinate(100, 0);
        MazeSolverImpl.solveMaze(openMaze, source, goal);
    }

    @Test
    public void noPaths() {
        closedMaze[0][0] = 0;
        closedMaze[3][3] = 0;
        Coordinate x = new Coordinate(0, 0);
        Coordinate y = new Coordinate(3, 3);
        assertNull(MazeSolverImpl.solveMaze(closedMaze, x, y));
    }

    @Test
    public void testSolveMaze() {
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 2);
        MazeSolverImpl.solveMaze(smallWriteupMaze, start, end);
    }

    @Test
    public void solveOneSolutionPath() {
        Coordinate x = new Coordinate(4, 0);
        Coordinate y = new Coordinate(0, 1);
        int[][] path = new int[9][9];

        int[][] maze = {
                {1, 1, 1, 1, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0},
                {1, 0, 1, 0, 0, 0, 1, 1, 1},
                {1, 0, 1, 1, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1, 0, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1}
        };
        int[][] solution = {
                {0, 0, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 1, 1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0, 0}
        };
        assertTrue(MazeSolverImpl.findSolution(maze, x, y, path));
        assertArrayEquals(path, solution);
    }

    @Test
    public void longMaze() {
        Coordinate x = new Coordinate(0, 1);
        Coordinate y = new Coordinate(7, 0);

        int[][] maze = {
                {1, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0}
        };

    int[][] solution = {
            {0, 0, 1, 1, 1, 0, 0, 1},
            {1, 1, 1, 0, 1, 1, 1, 1}
        };

    assertArrayEquals(MazeSolverImpl.solveMaze(maze, x, y), solution);
    }

    @Test
    public void testMediumMaze() {
        Coordinate x = new Coordinate(1, 0);
        Coordinate y = new Coordinate(0, 7);

        int[][] solution = {
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        };
        assertArrayEquals(MazeSolverImpl.solveMaze(mediumMaze, x, y), solution);
    }

    @Test
    public void testInEfficientSolution() {
        Coordinate x = new Coordinate(4, 0);
        Coordinate y = new Coordinate(0, 0);

        int[][] path = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        int[][] maze = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        int[][] solution = {
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };

        assertTrue(MazeSolverImpl.findSolution(maze, x, y, path));
        assertArrayEquals(path, solution);
    }
}
