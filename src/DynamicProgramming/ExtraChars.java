package DynamicProgramming;

import java.util.*;

public class ExtraChars {

    Map<Character, Integer> mp = new HashMap<>();

    public static void main(String[] args) {
        ExtraChars e = new ExtraChars();
        char b = 'b';
        e.mp.put('a', 1);
        e.mp.put(b, 2);
        for (var k: e.mp.entrySet()) {
            System.out.println(k.getKey()+" "+k.getValue());
        }
        e.mp.put(b, 3);
    }

}
