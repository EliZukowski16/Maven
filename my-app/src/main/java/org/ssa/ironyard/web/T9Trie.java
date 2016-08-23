package org.ssa.ironyard.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class T9Trie implements Text9Trie
{

    private final TrieNode trieRoot;
    private final Map<String, char[]> t9Map;

    public T9Trie()
    {
        trieRoot = new TrieNode();
        t9Map = new HashMap<>();

        this.buildT9Map();
    }

    @Override
    public void addWord(String word)
    {
        trieRoot.addWord(word.toLowerCase());
    }

    @Override
    public boolean contains(String word)
    {
        return trieRoot.contains(word.toLowerCase());
    }

    @Override
    public boolean remove(String word)
    {
        return trieRoot.remove(word.toLowerCase());
    }

    @Override
    public void clear()
    {
        trieRoot.clear();
    }

    @Override
    public List<String> suggest(String digits)
    {
        List<String> suggestedWords = new ArrayList<>();
        List<String> prefixes = new ArrayList<>();
        char[] prefix = new char[digits.length()];

        buildPrefix(digits, prefix, prefixes, 0);

        for (String p : prefixes)
        {
            suggestedWords.addAll(trieRoot.suggest(p));
        }

        return suggestedWords;

    }

    private void buildPrefix(String digits, char[] prefix, List<String> prefixes, int index)
    {
        if (digits.isEmpty())
        {
            StringBuilder sb = new StringBuilder();
            prefixes.add(sb.append(prefix).toString());
            return;
        }

        char[] possibleChars = t9Map.getOrDefault(digits.substring(0, 1), new char[]
        {});

        if (possibleChars.length == 0)
        {
            buildPrefix(digits.substring(1), prefix, prefixes, index);
        }

        for (char d : possibleChars)
        {
            prefix[index] = d;

            buildPrefix(digits.substring(1), prefix, prefixes, index + 1);
        }

    }

    private void buildT9Map()
    {
        t9Map.put("0", new char[]
        {});
        t9Map.put("1", new char[]
        {});
        t9Map.put("2", new char[]
        { 'a', 'b', 'c' });
        t9Map.put("3", new char[]
        { 'd', 'e', 'f' });
        t9Map.put("4", new char[]
        { 'g', 'h', 'i' });
        t9Map.put("5", new char[]
        { 'j', 'k', 'l' });
        t9Map.put("6", new char[]
        { 'm', 'n', 'o' });
        t9Map.put("7", new char[]
        { 'p', 'q', 'r', 's' });
        t9Map.put("8", new char[]
        { 't', 'u', 'v' });
        t9Map.put("9", new char[]
        { 'w', 'x', 'y', 'z' });
    }

}
