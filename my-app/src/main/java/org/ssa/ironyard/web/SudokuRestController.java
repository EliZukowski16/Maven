package org.ssa.ironyard.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;
import org.ssa.ironyard.model.Board;


@RestController
@RequestMapping("/sudoku")
public class SudokuRestController {
    @Autowired
    @Qualifier("default-games")
    Map<Integer, Board> games;
    
    @RequestMapping("")
    public View home()
    {
        return new InternalResourceView("home-sudoku.html"); //BINGO - although could just use string probably
    }

    @RequestMapping(produces = "application/json", value = "/{difficulty}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, String>> start(@PathVariable String difficulty) throws IllegalArgumentException {

        ResponseEntity.status(HttpStatus.CREATED);

        if (difficulty.equals("easy")) {
            Map<String, String> easyMap = new HashMap<>();
            easyMap.put("intial", games.get(0).getIntialState());
            easyMap.put("gameId", "0");
            return ResponseEntity.ok().body(easyMap);
        }

        if (difficulty.equals("medium")) {
            Map<String, String> easyMap = new HashMap<>();
            easyMap.put("game", games.get(1).getIntialState());
            return ResponseEntity.ok().body(easyMap);

        }
        Map<String, String> easyMap = new HashMap<>();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(easyMap);

    }

    @RequestMapping("/{gameID}")
    @ResponseBody
    public ResponseEntity<Map<String, List<String>>> gameState(HttpSession put, HttpServletRequest request,
            @PathVariable String gameID) {
        ResponseEntity.status(HttpStatus.CREATED);
        Map<String, List<String>> easyMap = new HashMap<>();

        String current = request.getParameter("solution");
        if (!Strings.isEmpty(request.getParameter("solution"))) {

            games.get(0).solveBoard();
            if ((games.get(0)).checkBoard(current).size() == 0) {
                easyMap.put("errors", games.get(0).checkBoard(current));
                easyMap.put("game", Arrays.asList(gameID));
                return ResponseEntity.ok().body(easyMap);
            }
            easyMap.put("errors", games.get(0).checkBoard(current));
            easyMap.put("game", Arrays.asList(gameID));
            return ResponseEntity.ok().body(easyMap);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(easyMap);
    }

}