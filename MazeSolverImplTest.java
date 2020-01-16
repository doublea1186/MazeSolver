import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class MazeSolverImplTest {

    private int[][] smallWriteupMaze;
    private int[][] bigWriteupMaze;

    @Before
    public void setupTestMazes() {
        smallWriteupMaze = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
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

    // TODO: Add your tests here!

    @Test
    public void testIncrement() {
        Coordinate x = new Coordinate(0, 0);
        Coordinate y = MazeSolverImpl.increment(x, 1,1);
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
        assertTrue(MazeSolverImpl.withinBounds(smallWriteupMaze, x));
        assertFalse(MazeSolverImpl.withinBounds(smallWriteupMaze, a));
        assertFalse(MazeSolverImpl.withinBounds(smallWriteupMaze, y));
        assertFalse(MazeSolverImpl.withinBounds(smallWriteupMaze, z));
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

    }

    @Test
    public void testSolveMaze() {
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);
        MazeSolverImpl.solveMaze(smallWriteupMaze, start, end);
    }
}
