package org.ssa.ironyard.backtracking;

public class SudokuSolver
{
    final String[][] initialBoard;

    String[][] solvedBoard;

    public SudokuSolver(String[][] initialBoard)
    {
        this.initialBoard = initialBoard;
        this.solvedBoard = initialBoard;
    }

    class Cell
    {
        int row, col;

        public Cell(int row, int col)
        {
            this.row = row;
            this.col = col;
        }
    }

    boolean isValid(Cell cell, String value)
    {

        for (int i = 0; i < 9; i++)
            if (solvedBoard[cell.row][i].equals(value))
                return false;

        for (int i = 0; i < 9; i++)
            if (solvedBoard[i][cell.col].equals(value))
                return false;

        int boxStartRow = (cell.row / 3) * 3;
        int boxStartCol = (cell.col / 3) * 3;
        int boxEndRow = boxStartRow + 2;
        int boxEndCol = boxStartCol + 2;

        for (int i = boxStartRow; i <= boxEndRow; i++)
            for (int j = boxStartCol; j <= boxEndCol; j++)
                if (solvedBoard[i][j].equals(value))
                    return false;
        
        return true;
    }
    
    Cell getNextCell(Cell currentCell)
    {
        int row = currentCell.row;
        int col = currentCell.col;
        
        if(col > 8)
        {
            col = 0;
            row++;
        }
        
        if(row > 8)
            return null;
        
        return new Cell(row, col);
    }
    
    boolean solve(Cell currentCell)
    {
        if(currentCell == null)
            return true;
        
        if(!solvedBoard[currentCell.row][currentCell.col].equals("0"))
        {
            return solve(getNextCell(currentCell));
        }
        
        for(int i = 1; i < 10; i++)
        {
            if(isValid(currentCell, String.valueOf(i)))
            {
                solvedBoard[currentCell.row][currentCell.col] = String.valueOf(i);
                if(solve(getNextCell(currentCell)))
                {
                    return true;
                }
                solvedBoard[currentCell.row][currentCell.col] = "0";
            }
        }
        
        return false;
    }

}
