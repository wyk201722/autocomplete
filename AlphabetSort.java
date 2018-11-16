import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * AlphabetSort takes input from stdin and prints to stdout.
 * The first line of input is the alphabet permutation.
 * The the remaining lines are the words to be sorted.
 *
 * The output should be the sorted words, each on its own line,
 * printed to std out.
 */
public class AlphabetSort {

    /**
     * Reads input from standard input and prints out the input words in
     * alphabetical order.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        boolean judgement = true;
        boolean isEmpty = true;
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        Trie dictionary = new Trie();
        String rule = "";
        String s;
        try {
            s = bufferedReader.readLine();
            while (s != null) {
                if (judgement) {
                    rule = s;
                } else {
                    isEmpty = false;
                    dictionary.insert(s);
                }
                s = bufferedReader.readLine();
                judgement = false;
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isEmpty || rule.equals("") || !notDuplicate(rule)) {
            throw new IllegalArgumentException();
        } else {
            dictionary.usePreTravsersal(rule);
        }
    }

    private static boolean notDuplicate(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && i != j) {
                    return false;
                }
            }
        }
        return true;
    }

}
