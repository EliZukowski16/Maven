package org.ssa.ironyard.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationHelper
{
    @Bean
    public Text9Trie getDictionary()
    {
        return new T9Trie();
    }

}
