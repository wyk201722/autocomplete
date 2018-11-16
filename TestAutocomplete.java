import ucb.junit.textui;
import org.junit.Test;
import static org.junit.Assert.*;

/** The suite of all JUnit tests for the Autocomplete class.
 *  @author
 */
public class TestAutocomplete {

    /** A dummy test to avoid complaint. */
    @Test
    public void placeholderTest() {
        String[] newstring = new String[] {"today", "tomorrow", "thus", "tone", "thy",
                                           "thigh", "town", "towel", "the", "throne"};
        double[] weightsof = new double[] {10.00, 1.00, 2.00, 15.00, 12.00,
                                           29.00, 3.00, 9.00, 25.00, 30.00};
        Autocomplete newauto = new Autocomplete(newstring, weightsof);
        System.out.println(newauto.topMatch("t")); // throne:30.00
        System.out.println(newauto.topMatches("t", 3)); // throne: 30.00, thigh: 29.00, the: 25.00
        System.out.println(newauto.weightOf("throne"));
        System.out.println(newauto.weightOf("thigh"));
        System.out.println(newauto.weightOf("the"));

        String[] newstring2 = new String[] {"morning", "money", "monkey", "mountain",
                                            "mole", "moles", "mouse", "movies", "moana",
                                            "mountains", "mental", "mice", "mike", "mind", "matter",
                                            "matte", "mankey", "mic", "mile", "miles",
                                            "man", "mattel"};
        double[] weightsof2 = new double[] {5.0, 10.0, 15.0, 11.0, 1.0, 22.0, 4.0, 6.0, 25.0,
                                            17.0, 13.0, 100.0, 50.0, 21.0, 30.0, 30.5, 34.0,
                                            40.0, 40.5, 27.0, 14.0, 14.5};
        double[] notweightsof = new double[] {5.0, 2.0, -1.0};
        Autocomplete newauto2 = new Autocomplete(newstring2, weightsof2);
        System.out.println(newauto2.topMatch("m"));
        System.out.println(newauto2.topMatch("mo"));
        System.out.println(newauto2.topMatch("ma"));
        double moun = newauto2.weightOf("mountains");
        System.out.println(moun);
        /*mice:100.00, mike:50.0, mile:40.5, mic:40.0, mankey:3.0, for the call below*/
        System.out.println(newauto2.topMatches("m", 5));
        /*moana:25.0, moles:22.0, mountains:17.0*/
        System.out.println(newauto2.
                topMatches("mo", 3));
        /*"mankey:34.0, matte:30.5, matter:30.0*/
        System.out.println(newauto2.
                topMatches("ma", 3));
        //Should print out an empty list"
        System.out.println(newauto2.
                topMatches("mu", 3));
        System.out.println(newauto2.
                topMatches("m", -1)); /*should return an error*/
        try {
            newauto2.topMatches("m", -1);
        } catch (IllegalArgumentException x) {
            //K can't be less than zero
        }
        try {
            Autocomplete notauto = new Autocomplete(newstring2, notweightsof);
        } catch (IllegalArgumentException n) {
            /*Lengths of String array and weight are not equal
            * or an item in the weight array is negative*/
        }
        /*Suppose to throw out an IllegalArgumentException
        because length of newstrings2 does not equal length
        of notweightsof, to see the error, comment out the previous test*/
    }

    /** Run the JUnit tests above. */
    public static void main(String[] ignored) {
        textui.runClasses(TestAutocomplete.class);
    }
}
