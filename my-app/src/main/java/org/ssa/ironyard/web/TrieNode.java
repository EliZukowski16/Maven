package org.ssa.ironyard.web;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class TrieNode
{
    private Map<Character, TrieNode> children;
    private final Character nodeValue;
    private boolean isWord;

    public TrieNode()
    {
        this.children = new HashMap<>();
        this.nodeValue = null;
        this.isWord = false;
    }

    public TrieNode(Character c)
    {
        this.children = new HashMap<>();
        this.nodeValue = c;
        this.isWord = false;
    }

    public void addWord(String word)
    {
        word = word.toLowerCase();

        if (word.length() == 0)
        {
            isWord = true;

            return;
        }
        else
        {
            TrieNode child = children.get(word.charAt(0));
            String restOfWord = word.substring(1);

            if (child == null)
            {
                children.put(word.charAt(0), new TrieNode(word.charAt(0)));
                child = children.get(word.charAt(0));

                child.addWord(restOfWord);
            }
            else
            {
                child.addWord(restOfWord);
            }
        }
    }

    public boolean contains(String word)
    {
        word = word.toLowerCase();

        if (word.length() == 0)
        {
            if (isWord)
                return true;
            return false;
        }

        TrieNode child = children.get(word.charAt(0));
        String restOfWord = word.substring(1);

        if (child != null)
        {
            return child.contains(restOfWord);
        }

        return false;

    }

    public boolean remove(String word)
    {
        word = word.toLowerCase();

        if (word.length() == 0)
        {
            if (isWord)
            {
                isWord = false;
                return true;
            }
            return false;
        }

        TrieNode child = children.get(word.charAt(0));
        String restOfWord = word.substring(1);

        if (child != null)
        {
            return child.remove(restOfWord);
        }

        return false;
    }

    public void clear()
    {
        children = new HashMap<>();
    }

    public List<String> suggest(String word)
    {
        word = word.toLowerCase();

        List<String> suggestedWords = new ArrayList<>();
        TrieNode node = getNode(word);

        if (node != null && !word.isEmpty())
        {
            collectWords(node, word.substring(0, word.length() - 1), suggestedWords);
        }

        suggestedWords.sort(Comparator.naturalOrder());

        return suggestedWords;

    }

    private void collectWords(TrieNode node, String builtWord, List<String> suggestedWords)
    {

        builtWord = builtWord + String.valueOf(node.nodeValue);
        if (node.isWord)
        {
            suggestedWords.add(builtWord);
        }

        if (node.children.isEmpty())
        {
            return;
        }
        else
        {
            for (Entry<Character, TrieNode> e : node.children.entrySet())
            {
                TrieNode child = node.children.get(e.getKey());
                collectWords(child, builtWord, suggestedWords);
            }
        }

        return;
    }

    private TrieNode getNode(String word)
    {
        if (word.length() == 0)
        {
            return this;
        }
        else
        {
            TrieNode child = children.get(word.charAt(0));
            String restOfWord = word.substring(1);
            if (child != null)
            {
                return child.getNode(restOfWord);
            }
        }

        return null;
    }
}
