package org.ssa.ironyard.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrieLoadingService
{
    static final Logger LOGGER = LogManager.getLogger(TrieLoadingService.class);

    @Autowired
    T9Trie trie;

    public TrieLoadingService(T9Trie trie) throws URISyntaxException, IOException
    {
        this.trie = trie;
        
        LOGGER.debug("will attempt to load corncob.txt");
        URL resource = getClass().getClassLoader().getResource("corncob_lowercase.txt");
        File file = new File(resource.toURI());
        LOGGER.debug("file {}, exists? {}", file, file.exists());

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
                        this.trie.addWord(word);
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
    
    public List<String> suggestions(String digits)
    {
        return this.trie.suggest(digits);
    }

}
