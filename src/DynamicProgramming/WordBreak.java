package DynamicProgramming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    private boolean segmentWords1Rec(String s, int i, List<String> wrds) {
//        if (t[i] != null)
//            return t[i];
//        if (wrds.contains(s.substring(i, j+1))) {
//            return t[i] = true;
//        }
//        boolean b = false;
//        for (int k = i; k <= j; k++) {
//            if (wrds.contains(s.substring(i, k+1)))
//                b = b || segmentWords1(s, k+1, j, t, wrds);
//        }
//        return t[i] = b;
        if (i >= s.length()) {
            return true;
        }
        boolean b = false;
        for (String w: wrds) {
            if (i+w.length() <= s.length() && s.substring(i, i+w.length()).equals(w)) {
                b = b || segmentWords1Rec(s, i+w.length(), wrds);
            }
        }
        return b;
    }

    private boolean segmentWords(String s, int i, List<String> wrds, Boolean[] t) {
        if (i >= s.length())
            return true;
        if (t[i] != null)
            return t[i];
        boolean b = false;
        for (String w: wrds) {
            if (i+w.length() <= s.length() && s.startsWith(w, i)) {
                b = b || segmentWords(s, i+w.length(), wrds, t);
            }
        }
        return t[i] = b;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        var wrds = new HashSet<>(wordDict);
        Boolean[] t = new Boolean[s.length() + 1];

        boolean res = segmentWords(s, 0, wordDict, t);

        for (int i=0; i<=s.length(); i++) {
//            for (int j=0; j<=s.length(); j++) {
                System.out.print(t[i]+" ");
//            }
//            System.out.println();
        }
        System.out.println();
        return res;
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
//        System.out.println(wb.wordBreak("catsandog", List.of("cats","dog","sand","and","cat")));
//        System.out.println(wb.wordBreak("leetcode", List.of("code","leet")));
        System.out.println(wb.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", List.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }

}
