package DynamicProgramming;

import java.util.*;

public class ExtraChars {

    static class TrieNode {
        private Map<Character, TrieNode> map = new HashMap<>();
        private boolean isEnd;
        private boolean isPrefix;

        public void insert(String word) {
            var tmp = this;
            for (char i: word.toCharArray()) {
                if (tmp.map.get(i) == null) {
                    tmp.map.put(i, new TrieNode());
                }
                tmp.isPrefix = true;
                tmp = tmp.map.get(i);
            }
            tmp.isEnd = true;
        }

        public boolean[] find(String word) {
            var temp = this;
            for (char i: word.toCharArray()) {
                if (!temp.map.containsKey(i)) {
                    return new boolean[]{false, false};
                }
                temp = temp.map.get(i);
            }
            return new boolean[]{temp.isEnd, temp.isPrefix};
        }

        public boolean findLetter(char str) {
            return this.map.containsKey(str);
        }
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
        for (String i: dictionary) {
            root.insert(i);
        }
        List<int[]> ranges = new ArrayList<int[]>();
        int i = 0;
        while (i<s.length()) {
            if (root.findLetter(s.charAt(i))) {
                int beginIndex = i;
                ranges.add(new int[]{i, -1});
                boolean[] findRes = new boolean[]{false,false};
                while(beginIndex < s.length()) {
                    findRes = root.find(s.substring(i, beginIndex+1));
                    if (findRes[0]){
                        ranges.get(ranges.size() - 1)[1] = beginIndex;
                        break;
                    }if (!findRes[1]) {
                        break;
                    }
                    beginIndex++;
                }
                if (ranges.get(ranges.size() - 1)[1] == -1) {
                    ranges.remove(ranges.size() - 1);
                }else {
                    if (!findRes[1])
                        i = beginIndex+1;
                    else
                        i = beginIndex;
                    continue;
                }
            }
            i++;
        }
        ranges.sort((a,b)-> a[0]-b[0]);
        for (var r: ranges) {
            System.out.print(r[0]+", "+r[1]+"    ");
        }
        System.out.println();
        ranges = mergeRanges(ranges);
        int lnt = 0;
        for (var r: ranges) {
            System.out.print(r[0]+", "+r[1]+"    ");
            lnt += (r[1] - r[0] + 1);
        }

        return s.length() - lnt;
    }

    public static void main(String[] args) {
        ExtraChars e = new ExtraChars();
//        int i = e.minExtraChar("kevlplxozaizdhxoimmraiakbak", new String[]{"yv","bmab","hv","bnsll","mra","jjqf","g",
//                "aiyzi","ip","pfctr","flr","ybbcl","biu","ke","lpl","iak","pirua","ilhqd","zdhx","fux","xaw","pdfvt",
//                "xf","t","wq","r","cgmud","aokas","xv","jf","cyys","wcaz","rvegf","ysg","xo","uwb","lw","okgk","vbmi",
//                "v","mvo","fxyx","ad","e"});
//        int i = e.minExtraChar("qsvwvkzfkuinvbfdpxckolrdesoifkkdwsnykzzwzrmh", new String[]{"ykz","c","s","x","m","yqximu","mgwls","jj","px","xyije","axfkp","dvnty","invbfd","qs","v","zw","d","hlsq","sltsu","desoi","kkdwsn","ol","zr","f","tpuc"});
        int i = e.minExtraChar("dwmodizxvvbosxxw", new String[]{"ox","lb","diz","gu","v","ksv","o","nuq","r","txhe","e","wmo","cehy","tskz","ds","kzbu"});
//        int i = e.minExtraChar("leetscode", new String[]{"leet", "code"});
        System.out.println(i);
    }

}
