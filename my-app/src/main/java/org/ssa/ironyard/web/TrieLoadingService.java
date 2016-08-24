package org.ssa.ironyard.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrieLoadingService
{
    static final Logger LOGGER = LogManager.getLogger(TrieLoadingService.class);

    @Autowired
    public TrieLoadingService(FileFactory dictionaryFile, Text9Trie trie) throws URISyntaxException, IOException
    {   
        File file = dictionaryFile.getInstance();

        BufferedReader reader = null;
        
        try
        {   
            reader = Files.newBufferedReader(file.toPath(), Charset.defaultCharset());

            String line;

            while (null != (line = reader.readLine()))
            {
                String[] words = line.split("\\W+");
                for (String word : words)
                {
                    if (!word.isEmpty())
                    {
                        trie.addWord(word);
                    }
                }
            }
        }
        catch (IOException iex)
        {
            System.err.println(iex);
            throw iex;
        }
        finally
        {
            if (null != reader)
                reader.close();
        }

    }

}
