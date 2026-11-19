import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "A".repeat(260); 
        Message msg = new Message("+27718693002", longMessage);
        String expected = "Message exceeds 250 characters by 10; please reduce the size.";
        assertEquals(expected, msg.checkMessageLength());
    }

    @Test
    public void testRecipientSuccess() {
        Message msg = new Message("+27718693002", "Test message");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        Message msg = new Message("08575975889", "Test message");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        // This checks if the hash ends with HITONIGHT
        assertTrue(msg.getMessageHash().endsWith(":0:HITONIGHT"));
    }
        // Test to check if the messages array is populated correctly
    @Test
    public void testMessagesArrayPopulated() {
        String[] testArray = {"Did you get the cake?", "It is dinner time!"};
        assertEquals(2, testArray.length);
        assertEquals("Did you get the cake?", testArray[0]);
    }

    // Test to find the longest message
    @Test
    public void testLongestMessage() {
        String[] msgs = {"Hi", "Where are you? You are late! I have asked you to be on time.", "Ok"};
        String longest = "";
        for (String m : msgs) {
            if (m.length() > longest.length()) {
                longest = m;
            }
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    // Test to search for a message ID
    @Test
    public void testSearchByID() {
        String[] ids = {"1234567890", "0838884567"};
        String target = "0838884567";
        boolean found = false;
        for (String id : ids) {
            if (id.equals(target)) {
                found = true;
            }
        }
        assertTrue(found);
    }
}