package org.ssa.ironyard.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.ssa.ironyard.backtracking.SudokuSolver;

@Component
public class Board {

    String intialState;
    String solvedState;

    public Board() {
        this.intialState = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        this.solvedState ="";
    }

    public Board(String initialState) {

        this.intialState = initialState;
        this.solvedState = "";

    }
    public String add(int position, String value){
        char [] temp = this.getIntialState().toCharArray();
        temp[position]= value.charAt(0);
        return this.setIntialState(String.valueOf(temp));
    }

    public String solveBoard() {
        String temp = this.getIntialState();

        String[][] board = new String[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                board[i][j] = temp.substring(0, 1);
                temp = temp.substring(1);
            }

        return this.setSolvedState(new SudokuSolver(board).solveBoard());
    }

    public List<String> checkBoard(String check) {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < 81; i++)
            if (!(check.charAt(i) == '0'))
                if (!(check.charAt(i) == this.solvedState.charAt(i)))
                    temp.add(String.valueOf(i));
        
        return temp;

    }

    public String getIntialState() {
        return intialState;
    }

    public String getSolvedState() {
        return solvedState;
    }

    public String setSolvedState(String solvedState) {

        return this.solvedState = solvedState;
    }
    public String setIntialState(String intialState) {
       return this.intialState = intialState;
    }

}