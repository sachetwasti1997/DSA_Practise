package DynamicProgramming;

import java.util.*;

public class WordBreakItr {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] t = new boolean[s.length()+1];
        t[s.length()] = true;

        for (int i = s.length()-1; i>=0; i--) {
            for (String w: wordDict) {
                if (i+w.length() <= s.length() && s.startsWith(w, i)) {
                    t[i] = t[i] || t[i+w.length()];
                }
            }
        }
        return t[0];
    }

    public static void main(String[] args) {
        WordBreakItr itr = new WordBreakItr();
//        boolean b = itr.wordBreak("leetcode", List.of("leet","code"));
//        System.out.println(itr.wordBreak("catsandog", List.of("cats","dog","sand","and","cat")));
        System.out.println(itr.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", List.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }

}
