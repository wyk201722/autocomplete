
import java.util.TreeMap;

/**
 * Prefix-Trie. Supports linear time find() and insert().
 * Should support determining whether a word is a full word in the
 * Trie or a prefix.
 *
 * @author
 */
public class Trie {
    private Node root = new Node();
    private static final int R = 128;


    public Trie() {
        root = new Node();
    }

    private class Node {

        private boolean exists;
        private TreeMap<Character, Node> links;

        Node() {
            exists = false;
            links = new TreeMap<Character, Node>();
        }

        public void setExists(boolean exists) {
            this.exists = exists;
        }

        public boolean isExists() {
            return exists;
        }

        public TreeMap<Character, Node> getLinks() {
            return links;
        }
      
    }
    public boolean find(String s, boolean isFullWord) {
        boolean judgement = true;
        Node currentNode = root;
        if (isFullWord) {
            for (int i = 0; i < s.length(); i++) {
                judgement = (judgement && currentNode.getLinks().containsKey(s.charAt(i)));
                if (judgement) {
                    currentNode = currentNode.getLinks().get(s.charAt(i));
                }
                if (i == s.length() - 1) {
                    judgement = currentNode.isExists();
                }
            }
            return judgement;
        } else {
            for (int i = 0; i < s.length(); i++) {
                judgement = (judgement && currentNode.getLinks().containsKey(s.charAt(i)));
                if (judgement) {
                    currentNode = currentNode.getLinks().get(s.charAt(i));
                }

            }
            return judgement;
        }
    }

    private Node put(Node x, String key, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.exists = true;
            return x;
        }
        char c = key.charAt(d);
        x.getLinks().put(c, put(x.getLinks().get(c), key, d + 1));
        return x;
    }
    public void insert(String s) {
        if (s.equals(null) || s.equals("")) {
            throw new IllegalArgumentException();
        }
        put(this.root, s, 0);
    }

    public void usePreTravsersal(String rule) {
        preTraversal(this.root, "", rule);
    }

    private void preTraversal(Node x, String prefixs, String rule) {
        if (x != null) {
            if (x.isExists()) {
                System.out.println(prefixs);
            }
            for (int i = 0; i < rule.length(); i++) {
                if (x.getLinks().containsKey(rule.charAt(i))) {
                    char c = rule.charAt(i);
                    String temp = prefixs + c;
                    preTraversal(x.getLinks().get(rule.charAt(i)), temp, rule);
                }
            }
        }
    }
}





