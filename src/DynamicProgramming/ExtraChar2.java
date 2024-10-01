package DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class ExtraChar2 {
    private int findMinRec(String s, int i, int[] min, Set<Character> letters, String[] words) {
        if (i >= s.length()) {
            return 0;  // Base case: reached the end of the string
        }

        int maxLn = 0;
        int minCnt = 0;

        // Check if the current character is in the letters set
        if (!letters.contains(s.charAt(i))) {
            minCnt = 1 + findMinRec(s, i + 1, min, letters, words); // Increment count if char not in dictionary
        } else {
            // Try to match the longest word from the current index
            for (String w : words) {
                if (i + w.length() <= s.length() && s.startsWith(w, i)) {
                    maxLn = Math.max(maxLn, w.length());
                }
            }
            if (maxLn == 0) {
                // No match found, increment index and check next
                minCnt = 1 + findMinRec(s, i + 1, min, letters, words);
            } else {
                // Match found, move index forward by the length of the matched word
                minCnt = findMinRec(s, i + maxLn, min, letters, words);
            }
        }
        return minCnt;
    }

    public int minExtraChar(String s, String[] dictionary) {
        var letters = new HashSet<Character>();
        for (String tmp : dictionary) {
            for (char ch : tmp.toCharArray()) {
                letters.add(ch); // Include all characters from dictionary
            }
        }
        int[] min = {0}; // This is still not utilized, could be removed
        return findMinRec(s, 0, min, letters, dictionary);
    }

    public static void main(String[] args) {
        ExtraChar2 e = new ExtraChar2();
//        System.out.println(e.minExtraChar("sayhelloworld", new String[]{"hello","world"}));
//        int i = e.minExtraChar("dwmodizxvvbosxxw", new String[]{"ox","lb","diz","gu","v","ksv","o","nuq","r","txhe","e","wmo","cehy","tskz","ds","kzbu"});
//        int i = e.minExtraChar("kevlplxozaizdhxoimmraiakbak", new String[]{"yv","bmab","hv","bnsll","mra","jjqf","g","aiyzi","ip","pfctr","flr","ybbcl","biu","ke","lpl","iak","pirua","ilhqd","zdhx","fux","xaw","pdfvt","xf","t","wq","r","cgmud","aokas","xv","jf","cyys","wcaz","rvegf","ysg","xo","uwb","lw","okgk","vbmi","v","mvo","fxyx","ad","e"});
        int i = e.minExtraChar("metzeaencgpgvsckjrqafkxgyzbe", new String[]{"zdzz","lgrhy","r","ohk","zkowk","g","zqpn","anoni","ka","qafkx","t","jr","xdye","mppc","bqqb","encgp","yf","vl","ctsxk","gn","cujh","ce","rwrpq","tze","zxhg","yzbe","c","o","hnk","gv","uzbc","xn","kk","ujjd","vv","mxhmv","ugn","at","kumr","ensv","x","uy","gb","ae","jljuo","xqkgj"});
        System.out.println(i); // m e n p s k
        // tzeaecggvcjrqafkxgyzbe
    }

}
