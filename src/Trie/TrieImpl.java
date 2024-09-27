package Trie;

import java.util.HashMap;
import java.util.Map;

public class TrieImpl {

    private Map<Character, TrieImpl> characters;
    private boolean isEnd;

    public TrieImpl() {
        characters = new HashMap<>();
        isEnd = false;
    }

    public void insert(String word) {
        TrieImpl temp = this;
        for (char i: word.toCharArray()) {
            if (temp.characters.containsKey(i)) {
                temp = temp.characters.get(i);
                continue;
            }
            temp.characters.put(i, new TrieImpl());
            temp = temp.characters.get(i);
        }
        temp.isEnd = true;
    }

    public boolean search(String wrd) {
        TrieImpl temp = this;
        for (char i: wrd.toCharArray()) {
            if (!temp.characters.containsKey(i))
                return false;
            temp = temp.characters.get(i);
        }
        return temp.isEnd;
    }

    public static void main(String[] args) {
        TrieImpl impl = new TrieImpl();
        impl.insert("this");
        impl.insert("that");
        impl.insert("geek");
        impl.insert("geeks");

        System.out.println(impl.search("this"));
        System.out.println(impl.search("thar"));
    }

}
