package org.ssa.ironyard.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ssa.ironyard.backtracking.SudokuSolver;

public class SudokuSolverTest
{

    String[][] initial;
    String[][] solution;
    SudokuSolver solver;
    static String easyPuzzle;
    static String mediumPuzzle;
    static String hardPuzzle;
    static String evilPuzzle;
    static String invalidPuzzle;

    @BeforeClass
    public static void classSetup()
    {
        easyPuzzle = "178000000900100043300007051010026080700010005020730060290800004630001008000000372";
        mediumPuzzle = "000023180000090004070016020004000806000204000703000200050180070200060000067340000";
        hardPuzzle = "800007120320049007907000000000050000705000406000090000000000702200870065071500004";
        evilPuzzle = "000000000000003085001020000000507000004000100090000000500000073002010000000040009";
        invalidPuzzle = "000000000000003085001020000000507000004000100090000000500000073002010000000040029";
    }

    @Before
    public void setup()
    {
        solver = new SudokuSolver();
    }

    public static String[][] convertToArray(String sudokuValues)
    {
        String[][] sudoku = new String[9][9];

        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                sudoku[row][col] = sudokuValues.substring(0, 1);
                sudokuValues = sudokuValues.substring(1);
            }
        }

        return sudoku;
    }

    public void solutionSatisfiesRulesOfSudoku()
    {
        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                for (int i = 0; i < 9; i++)
                {
                    if (i == row)
                    {
                        assertTrue(solution[row][col].equals(solver.getSolvedBoard()[i][col]));
                    }
                    else if (i == col)
                    {
                        assertTrue(solution[row][col].equals(solver.getSolvedBoard()[row][i]));
                    }

                    else
                    {
                        assertFalse(solution[row][col].equals(solver.getSolvedBoard()[i][col]));
                        assertFalse(solution[row][col].equals(solver.getSolvedBoard()[row][i]));
                    }

                    if (initial[row][col].equals("0"))
                    {
                        assertFalse(initial[row][col].equals(solution[row][col]));
                    }
                    else
                    {
                        assertTrue(initial[row][col].equals(solution[row][col]));
                    }
                }

                int boxStartRow = (row / 3) * 3;
                int boxStartCol = (col / 3) * 3;
                int boxEndRow = boxStartRow + 2;
                int boxEndCol = boxStartCol + 2;

                for (int i = boxStartRow; i <= boxEndRow; i++)
                {
                    for (int j = boxStartCol; j <= boxEndCol; j++)
                    {
                        if (i == row && j == col)
                        {
                            assertTrue(solution[row][col].equals(solver.getSolvedBoard()[i][j]));
                        }
                        else
                        {
                            assertFalse(solution[row][col].equals((solver.getSolvedBoard()[i][j])));
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testAnEmptyBoardSolvesCorrectly()
    {
        System.err.println("Empty Puzzle Test");

        System.err.println(solver.toString());

        solver.solveBoard();
        this.solution = solver.getSolvedBoard();
        this.initial = solver.getInitialBoard();

        System.err.println(solver.toString());

        solutionSatisfiesRulesOfSudoku();

    }

    @Test
    public void testAnEasyPuzzleSolvesCorrectly()
    {
        System.err.println("Easy Puzzle Test");

        solver = new SudokuSolver(convertToArray(easyPuzzle));
        System.err.println(solver.toString());

        solver.solveBoard();
        this.solution = solver.getSolvedBoard();
        this.initial = solver.getInitialBoard();

        System.err.println(solver.toString());

        solutionSatisfiesRulesOfSudoku();

    }

    @Test
    public void testAMediumPuzzleSolvesCorrectly()
    {
        System.err.println("Medium Puzzle Test");

        solver = new SudokuSolver(convertToArray(mediumPuzzle));
        System.err.println(solver.toString());

        solver.solveBoard();
        this.solution = solver.getSolvedBoard();
        this.initial = solver.getInitialBoard();

        System.err.println(solver.toString());

        solutionSatisfiesRulesOfSudoku();
    }

    @Test
    public void testAHardPuzzleSolvesCorrectly()
    {
        System.err.println("Hard Puzzle Test");

        solver = new SudokuSolver(convertToArray(hardPuzzle));
        System.err.println(solver.toString());

        solver.solveBoard();
        this.solution = solver.getSolvedBoard();
        this.initial = solver.getInitialBoard();

        System.err.println(solver.toString());

        solutionSatisfiesRulesOfSudoku();
    }

    @Test
    public void testAnEvilPuzzleSolvesCorrectly()
    {
        System.err.println("Evil Puzzle Test");

        solver = new SudokuSolver(convertToArray(evilPuzzle));
        System.err.println(solver.toString());

        solver.solveBoard();
        this.solution = solver.getSolvedBoard();
        this.initial = solver.getInitialBoard();

        System.err.println(solver.toString());

        solutionSatisfiesRulesOfSudoku();
    }
    
    @Test
    public void testThatAnInvalidPuzzleReturnsTheOriginalPuzzle()
    {
        System.err.println("Invalid Puzzle Test");

        solver = new SudokuSolver(convertToArray(invalidPuzzle));
        System.err.println(solver.toString());

        solver.solveBoard();
        this.solution = solver.getSolvedBoard();
        this.initial = solver.getInitialBoard();
        
        for(int row = 0; row < 9; row++)
        {
            for(int col = 0; col < 9; col++)
            {
                assertEquals(initial[row][col], solution[row][col]);
            }
        }

        System.err.println(solver.toString());
    }
}
