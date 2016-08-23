package org.ssa.ironyard.web;

import java.util.List;


public interface Text9Trie
{
    void addWord(String word);

    boolean contains(String word);

    boolean remove(String word);

    void clear();

    List<String> suggest(String digits);

}
