package DynamicProgramming;

import java.util.*;

public class ExtraCharsRevised {

    private int findMinRec(String s, int i, int[] min, Map<Character, List<String>> letters, String[] words) {
        if (i >= s.length()) {
            return 0;
        }
        int maxLn = 0;
        int minCnt = 0;
        if (!letters.containsKey(s.charAt(i))) {
            minCnt = 1 + findMinRec(s, i+1, min, letters, words);
        } else {
            var itr = letters.get(s.charAt(i));
            for (String w : itr) {
                if (i + w.length() <= s.length() && s.startsWith(w, i) && w.length() >= maxLn) {
                    maxLn = w.length();
                }
            }
            if (maxLn == 0) {
                minCnt = 1 + findMinRec(s, i+1, min, letters, words);
            }else {
                minCnt += findMinRec(s, i + maxLn, min, letters, words);
            }
        }
        return minCnt;
    }

    public int minExtraChar(String s, String[] dictionary) {
        var letters = new HashMap<Character, List<String>>();
        for (String tmp: dictionary) {
            if (!letters.containsKey(tmp.charAt(0))) {
                letters.put(tmp.charAt(0), new ArrayList<>());
            }
            letters.get(tmp.charAt(0)).add(tmp);
        }
        int[] min = {0};
        return findMinRec(s, 0, min, letters, dictionary);
    }

    public static void main(String[] args) {
        ExtraCharsRevised e = new ExtraCharsRevised();
//        System.out.println(e.minExtraChar("sayhelloworld", new String[]{"hello","world"}));
//        int i = e.minExtraChar("dwmodizxvvbosxxw", new String[]{"ox","lb","diz","gu","v","ksv","o","nuq","r","txhe","e","wmo","cehy","tskz","ds","kzbu"});
//        int i = e.minExtraChar("kevlplxozaizdhxoimmraiakbak", new String[]{"yv","bmab","hv","bnsll","mra","jjqf","g","aiyzi","ip","pfctr","flr","ybbcl","biu","ke","lpl","iak","pirua","ilhqd","zdhx","fux","xaw","pdfvt","xf","t","wq","r","cgmud","aokas","xv","jf","cyys","wcaz","rvegf","ysg","xo","uwb","lw","okgk","vbmi","v","mvo","fxyx","ad","e"});
        int i = e.minExtraChar("metzeaencgpgvsckjrqafkxgyzbe", new String[]{"zdzz","lgrhy","r","ohk","zkowk","g","zqpn","anoni","ka","qafkx","t","jr","xdye","mppc","bqqb","encgp","yf","vl","ctsxk","gn","cujh","ce","rwrpq","tze","zxhg","yzbe","c","o","hnk","gv","uzbc","xn","kk","ujjd","vv","mxhmv","ugn","at","kumr","ensv","x","uy","gb","ae","jljuo","xqkgj"});
        System.out.println(i); // m e n p s k
        // tzeaecggvcjrqafkxgyzbe
    }

}
