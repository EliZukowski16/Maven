package org.ssa.ironyard.web;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrieController
{
    static final Logger LOGGER = LogManager.getLogger(TrieController.class);
    @Autowired
    Text9Trie trie;

    public void setTrie(Text9Trie trie)
    {
        this.trie = trie;
    }

    @RequestMapping("/trie")
    @ResponseBody
    public String suggestions(HttpServletRequest request) throws URISyntaxException
    {
        if (Strings.isNotBlank(request.getParameter("digits")))
        {
            if(request.getParameter("digits").matches("[0-9]*?"))
            {
                return String.join("<br/>", trie.suggest(request.getParameter("digits")));
            }
            
            return "Error: input must be numeric (0-9)";
        }
        
        return "Error: can not process blank input";

    }
    
    

}
