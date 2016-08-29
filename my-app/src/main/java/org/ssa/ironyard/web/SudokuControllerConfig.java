package org.ssa.ironyard.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ssa.ironyard.model.Board;
@Configuration
public class SudokuControllerConfig {
    static final Logger LOGGER = LogManager.getLogger(SudokuControllerConfig.class);

    @Bean(name = "default-games")
    public Map<String, Board> defaults() throws URISyntaxException, IOException {
        Map<String, Board> games = new HashMap<>();

        File gameFile = new WebFileFactory("easy-1.txt").getInstance();

        LOGGER.debug("Got sudoku file {}", gameFile.toURI());

        BufferedReader reader = null;

        try {
            reader = Files.newBufferedReader(gameFile.toPath(), Charset.defaultCharset());

            String line;
            int i = 0;
            
            while (null != (line = reader.readLine())) {
                games.put(String.valueOf(i), new Board(line.trim())); 
                
                LOGGER.debug("\n i = " + i);
                LOGGER.debug("\n i = " + games.get(i));
                i++;
            }
        } catch (IOException iex) {
            System.err.println(iex);
            throw iex;
        } finally {
            if (null != reader)
                reader.close();
        }

        return games;
    }
}