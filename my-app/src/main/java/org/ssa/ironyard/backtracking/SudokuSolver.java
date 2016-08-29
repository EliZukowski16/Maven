package org.ssa.ironyard.backtracking;

public class SudokuSolver
{
    private final String[][] initialBoard;

    private String[][] solvedBoard;

    Cell startingCell;

    public SudokuSolver(String[][] initialBoard)
    {
        this.initialBoard = initialBoard;
        this.solvedBoard = initialBoard;
    }

    public SudokuSolver()
    {
        this.initialBoard = new String[9][9];
        
        for(int row = 0; row < 9; row++)
        {
            for(int col = 0; col < 9; col++)
            {
                this.getInitialBoard()[row][col] = "0";
            }
        }
        
        this.solvedBoard = this.initialBoard;
    }

    private class Cell
    {
        int row, col;

        public Cell(int row, int col)
        {
            this.row = row;
            this.col = col;
        }
    }

    private boolean isValid(Cell cell, String value)
    {

        for (int i = 0; i < 9; i++)
            if (getSolvedBoard()[cell.row][i].equals(value))
                return false;

        for (int i = 0; i < 9; i++)
            if (getSolvedBoard()[i][cell.col].equals(value))
                return false;

        int boxStartRow = (cell.row / 3) * 3;
        int boxStartCol = (cell.col / 3) * 3;
        int boxEndRow = boxStartRow + 2;
        int boxEndCol = boxStartCol + 2;

        for (int i = boxStartRow; i <= boxEndRow; i++)
            for (int j = boxStartCol; j <= boxEndCol; j++)
                if (getSolvedBoard()[i][j].equals(value))
                    return false;

        return true;
    }

    private Cell getBestCell()
    {
        int numberSolved = 0;
        int numberEmptyCells = 0;
        
        
        Cell bestCell = new Cell(0,0);

        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                if (getInitialBoard()[row][col].equals("0"))
                {

                    int count = 0;
                    numberEmptyCells++;

                    for (int i = 0; i < 9; i++)
                    {
                        if (!getInitialBoard()[row][i].equals("0"))
                            count++;
                        
                        
                        if(!getInitialBoard()[i][col].equals("0"))
                            count++;
                    }
                    
                    if(count > numberSolved)
                    {
                        bestCell = new Cell(row, col);
                        numberSolved = count;
                    }
                }
            }
        }
        
        if(numberEmptyCells == 0)
            return null;
        
        return bestCell;
        
        
    }

    private boolean solve(Cell currentCell)
    {
        
        if(currentCell == null)
            return true;

        for (int i = 1; i < 10; i++)
        {
            if (isValid(currentCell, String.valueOf(i)))
            {
                getSolvedBoard()[currentCell.row][currentCell.col] = String.valueOf(i);
                
                if (solve(getBestCell()))
                    return true;
                
                getSolvedBoard()[currentCell.row][currentCell.col] = "0";
            }
        }

        return false;
    }

    public String solveBoard()
    {
        String solvedString = "";
        
        startingCell = getBestCell();

        this.solve(startingCell);

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                solvedString = solvedString + getSolvedBoard()[i][j];
            }
        }

        return solvedString;
    }

    public String toString()
    {
        String board = "";

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                board = board + getSolvedBoard()[i][j] + " ";
            }

            board = board + "\n";
        }

        return board;
    }

    public String[][] getSolvedBoard()
    {
        return solvedBoard;
    }

    public String[][] getInitialBoard()
    {
        return initialBoard;
    }

}
