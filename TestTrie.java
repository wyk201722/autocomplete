import ucb.junit.textui;
import org.junit.Test;
import static org.junit.Assert.*;

/** The suite of all JUnit tests for the Trie class.
 *  @author
 */
public class TestTrie {

    /** A dummy test to avoid complaint. */
    @Test
    public void placeholderTest() {
        Trie newtrie = new Trie();
        newtrie.insert("hello");
        newtrie.insert("hope");
        newtrie.insert("you");
        newtrie.insert("are");
        newtrie.insert("having");
        newtrie.insert("a");
        newtrie.insert("good");
        newtrie.insert("day");
        System.out.println(newtrie.find("r", true));
        assertTrue(newtrie.find("hello", true));
        assertTrue(newtrie.find("having", true));
        assertTrue(newtrie.find("a", true));
        assertTrue(newtrie.find("good", false));
        assertTrue(newtrie.find("having", true));
        assertFalse(newtrie.find("r", false));
        assertFalse(newtrie.find("hell", true));
        assertFalse(newtrie.find("goo", true));
        try {
            newtrie.insert(null);
        } catch (IllegalArgumentException n) {
            //Input can't be null
        }
        try {
            newtrie.insert("");
        } catch (IllegalArgumentException x) {
            //Input can't be an empty string
        }
    }

    /** Run the JUnit tests above. */
    public static void main(String[] ignored) {
        textui.runClasses(TestTrie.class);
    }
}
