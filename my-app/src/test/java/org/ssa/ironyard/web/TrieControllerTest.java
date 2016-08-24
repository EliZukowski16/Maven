package org.ssa.ironyard.web;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class TrieControllerTest
{
    TrieController trieController = new TrieController();

    @Test
    public void blankInput() throws URISyntaxException
    {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/trie");
        request.setParameter("digits", "");
        String suggestions= trieController.suggestions(request);
        assertEquals("Error: can not process blank input", suggestions);
    }
    
    @Test
    public void badInput() throws URISyntaxException
    {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/trie");
        request.setParameter("digits", "abcd");
        String suggestions= trieController.suggestions(request);
        assertEquals("Error: input must be numeric (0-9)", suggestions);
    }
    
    @Test
    public void success()
    {
        this.trieController.setTrie(new Mock9Trie());
    }
    
    

}

class Mock9Trie implements Text9Trie
{

    List<String> mockSuggest;
    
    @Override
    public void addWord(String word)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean contains(String word)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean remove(String word)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<String> suggest(String digits)
    {
       return mockSuggest;
    }
    
}
