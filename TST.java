/**
 * Created by wyk on 2017/8/5.
 */
import java.util.PriorityQueue;
import java.util.ArrayList;
/*The following code was taken from the following link
* http://algs4.cs.princeton.edu/52trie/TST.java.html
* there were some modifications to the code in order to
* make it work with our project and some of the methods
* that were originally in the TST class were removed
* because they had no use in the way that we used
* the class.*/

public class TST {

    private static class Node {
        private char c;                        // character
        private Node left, mid, right;  // left, middle, and right subtries
        private Double val;
        private Double max; // value associated with stringt
        private String word;

        Node(char c, Double val, Double max, String word, Node left, Node mid, Node right) {
            this.c = c;
            this.left = left;
            this.right = right;
            this.mid  = mid;
            this.val = val;
            this.max = max;
            this.word = word;

        }
    }

    private int n;              // size
    private Node root;   // root of TST

    public TST() {
        root = null;
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key from the symbol table.
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */

    public void put(String key, Double val) {
        root = put(root, key, val, 0);
    }
    private Node put(Node x, String key, double val, int d) {

        if (x == null) {
            x = new Node(key.charAt(d), null, val, null, null, null, null);
        }
        char c = key.charAt(d);
        if (x.max < val) {
            x.max = val;
        }

        if (x.c == c) {
            if (d == key.length() - 1) {
                if (x.val != null) {
                    throw new IllegalArgumentException("Oh my God");
                } else {
                    x.val = val;
                    x.word = key;
                    return x;
                }
            }
            x.mid  = put(x.mid, key, val, d + 1);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);

        } else {
            x.left = put(x.left, key, val, d);
        }
        return x;
    }

    /**
     * Returns the string in the symbol table that is the longest prefix of {@code query},
     * or {@code null}, if no such string.
     * @param query the query string
     * @return the string in the symbol table that is the longest prefix of {@code query},
     *     or {@code null} if no such string
     * @throws IllegalArgumentException if {@code query} is {@code null}
     */
    public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
        }
        if (query.length() == 0) {
            return null;
        }
        int length = 0;
        Node x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if (c < x.c) {
                x = x.left;
            } else if (c > x.c) {
                x = x.right;
            } else {
                i++;
                if (x.val != null) {
                    length = i;
                }
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    public Double getweight(String prefix) {
        Node x = searchforPrefix(prefix);
        if (x == null) {
            return 0.0;
        } else {
            return x.val;
        }

    }

    private Node searchforPrefix(String prefix) {
        if (root == null) {
            return null;
        } else {
            return searchPrefixhelp(root, prefix, 0);
        }
    }

    private Node searchPrefixhelp(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        char c  = key.charAt(d);
        if (c == x.c) {
            if (d == key.length() - 1) {
                return x;
            }
            return searchPrefixhelp(x.mid, key, d + 1);
        } else {
            if (c < x.c) {
                return searchPrefixhelp(x.left, key, d);
            } else {
                return searchPrefixhelp(x.right,  key, d);
            }
        }

    }

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * @return all keys in the symbol table as an {@code Iterable}
     */


    /**
     * Returns all of the keys in the set that start with {@code prefix}.
     * @param prefix the prefix
     * @return all of the keys in the set that start with {@code prefix},
     *     as an iterable
     * @throws IllegalArgumentException if {@code prefix} is {@code null}
     */

    public Iterable<String> keysWithPrefix(String prefix, int k) {
        Node x;
        ArrayList<String> arr = new ArrayList<>();
        PriorityQueue<Node> fringe =
                new PriorityQueue<>((o1, o2) -> (java.lang.Double.compare(o2.max, o1.max)));
        PriorityQueue<Node> queue =
                new PriorityQueue<>((o1, o2) -> (java.lang.Double.compare(o1.val, o2.val)));

        if (prefix.isEmpty()) {
            x = root;
        } else {
            x = searchforPrefix(prefix);
            if (x == null) {
                return new ArrayList<>();
            }
            if (x.val != null) {
                queue.add(x);
            }
            x = x.mid;
        }
        if (x == null) {
            ArrayList value = new ArrayList();
            while (!queue.isEmpty()) {
                value.add(0, queue.poll().word);
            }
            return value;
//            return queue.stream().map(node -> node.word).collect(Collectors.toList());
        }
        fringe.add(x);
        while (!fringe.isEmpty()) {
            Node topNode = fringe.poll();
            if (topNode.val != null) {
                if (queue.size() < k) {
                    queue.add(topNode);
                } else if (topNode.val > queue.peek().val) {
                    queue.poll();
                    queue.add(topNode);
                }
            }
            if (queue.size() == k && queue.peek().val >= topNode.max) {
                break;
            }
            if (topNode.mid != null) {
                fringe.add(topNode.mid);
            }
            if (topNode.left != null) {
                fringe.add(topNode.left);
            }
            if (topNode.right != null) {
                fringe.add((topNode.right));
            }
        }

        while (!queue.isEmpty()) {
            arr.add(0, queue.poll().word);
        }
        return arr;
    }


    /**
     * Unit tests the {@code TST} data type.
     *
     * @param args the command-line arguments
     */
}

