package lists;

import java.util.Map;
import java.util.Stack;

public class MinAddsToMakeParanthesisValid {
    public int minAddToMakeValid(String s) {
        Stack<Character> stk = new Stack<>();
        char opening = '(';
        char closing = ')';
        int minNum = 0;
        for (var ch: s.toCharArray()) {
            if (ch == opening) {
                stk.add(ch);
                continue;
            }
            if (ch == closing && !stk.isEmpty() && stk.peek() == opening) {
                stk.pop();
            }else {
                minNum++;
            }
        }
        return minNum+ stk.size();
    }
}
