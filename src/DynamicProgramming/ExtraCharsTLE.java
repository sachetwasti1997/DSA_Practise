package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtraCharsTLE {

    static class TrieNode {
        private Map<Character, TrieNode> child;
        private boolean isEnd;

        public TrieNode() {
            child = new HashMap<>();
        }

        public void insert(String word) {
            var temp = this;
            for (char i: word.toCharArray()) {
                if (!temp.child.containsKey(i)) {
                    temp.child.put(i, new TrieNode());
                }
                temp = temp.child.get(i);
            }
            temp.isEnd = true;
        }

        public boolean find(String word) {
            var temp = this;
            for (char i: word.toCharArray()) {
                if (!temp.child.containsKey(i))
                    return false;
                temp = temp.child.get(i);
            }
            return temp.isEnd;
        }

        public boolean findPrefix(String word) {
            var temp = this;
            for (char i: word.toCharArray()) {
                if (!temp.child.containsKey(i))
                    return false;
                temp = temp.child.get(i);
            }
            return true;
        }
    }

    private void searchSegments(String s, int i, int j, TrieNode root, Map<String, int[]> dp) {
        if (i > j)
            return;
        if (dp.containsKey(i+""+j)) return;
        if (root.findPrefix(s.substring(i, i+1))) {
            for (int k=i; k<=j; k++) {
                if (root.find(s.substring(i, k+1))) {
                    dp.put(i+""+k, new int[]{i,k});
                    searchSegments(s, k+1, j, root, dp);
                }
            }
        }
        searchSegments(s, i+1, j, root, dp);
    }

    private List<int[]> mergeRanges(List<int[]> arr) {
        var merged = new ArrayList<int[]>();
        merged.add(arr.get(0));
        for (int i=1; i<arr.size(); i++) {
            var prev = merged.get(merged.size() - 1);
            var curr = arr.get(i);
            if (curr[0] <= prev[1]) {
                if ((curr[1] - curr[0]) > (prev[1] - prev[0])) {
                    prev[1] = curr[1];
                    prev[0] = curr[0];
                }
            }else {
                merged.add(curr);
            }
        }
        return merged;
    }

    public int minExtraChar(String s, String[] dictionary) {
        TrieNode root = new TrieNode();
        for (String tmp: dictionary) {
            root.insert(tmp);
        }

        var dp = new HashMap<String, int[]>();
        searchSegments(s, 0, s.length()-1, root, dp);
        int lnt = 0;
        List<int[]> ranges = new ArrayList<int[]>();
        for (var k: dp.entrySet()) {
            ranges.add(new int[]{k.getValue()[0], k.getValue()[1]});
        }
        ranges.sort((a,b) -> a[0] - b[0]);
//        for (int[] i: ranges){
//            System.out.print(i[0]+", "+i[1]+"          ");
//        }
        ranges = mergeRanges(ranges);
//        System.out.println();
        for (int[] i: ranges){
            System.out.print(i[0]+", "+i[1]+"    ");
            lnt += i[1] - i[0] + 1;
        }
//        System.out.println();
//        System.out.println(s.length());
        return s.length() - lnt;
    }

    public static void main(String[] args) {
        ExtraCharsTLE e = new ExtraCharsTLE();

//        int i = e.minExtraChar("kevlplxozaizdhxoimmraiakbak", new String[]{"yv","bmab","hv","bnsll","mra","jjqf","g",
//                "aiyzi","ip","pfctr","flr","ybbcl","biu","ke","lpl","iak","pirua","ilhqd","zdhx","fux","xaw","pdfvt",
//                "xf","t","wq","r","cgmud","aokas","xv","jf","cyys","wcaz","rvegf","ysg","xo","uwb","lw","okgk","vbmi",
//                "v","mvo","fxyx","ad","e"});
//        int i = e.minExtraChar("dwmodizxvvbosxxw", new String[]{"ox","lb","diz","gu","v","ksv","o","nuq","r","txhe","e","wmo","cehy","tskz","ds","kzbu"});
        int i = e.minExtraChar("leetscode", new String[]{"leet", "code"});
//        int i = e.minExtraChar("qsvwvkzfkuinvbfdpxckolrdesoifkkdwsnykzzwzrmh", new String[]{"ykz","c","s","x","m","yqximu","mgwls","jj","px","xyije","axfkp","dvnty","invbfd","qs","v","zw","d","hlsq","sltsu","desoi","kkdwsn","ol","zr","f","tpuc"});

        System.out.println(i);
    }

}
