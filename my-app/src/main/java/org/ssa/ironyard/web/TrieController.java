package org.ssa.ironyard.web;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

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
    TrieLoadingService loadedTrie;
    
    @RequestMapping("/trie")
    @ResponseBody
    public String suggestions(HttpServletRequest request) throws URISyntaxException
    {
        LOGGER.debug("will attempt to load corncob.txt");
        URL resource = getClass().getClassLoader().getResource("corncob_lowercase.txt");
        File file = new File(resource.toURI());
        LOGGER.debug("file {}, exists? {}",file, file.exists());
        if(Strings.isNotBlank(request.getParameter("digits")))
           return String.join("<br/>", this.loadedTrie.suggestions(request.getParameter("digits")));
        
        return String.join("<br/>", "not", "yet", "implemented");
            
    }

}
