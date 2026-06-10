/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author troyj
 */


import com.mycompany.poe_part3.QuickChat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickChatTest {
    private QuickChat qc;

    @BeforeEach
    public void setUp() {
        qc = new QuickChat();
        qc.addMessageRecord("000001", "0821112222", "Did you get the cake?", "Sent", "hash1");
        qc.addMessageRecord("000002", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Sent", "hash2");
        qc.addMessageRecord("000003", "+27838884567", "Testing message sequence three", "Sent", "hash3");
        qc.addMessageRecord("0838884567", "0838884567", "It is dinner time !", "Sent", "hash4");
        qc.addMessageRecord("000005", "+27838884567", "Ok, I am leaving without you.", "Stored", "hash5");
    }

    @Test
    public void testDisplayLongestMessage() {
        String expectedLongest = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expectedLongest, qc.getLongestMessage());
    }

    @Test
    public void testSearchByMessageID() {
        String expected = "\"It is dinner time !\"";
        assertEquals(expected, qc.searchByMessageID("0838884567"));
    }

    @Test
    public void testSearchByRecipient() {
        String result = qc.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late!"));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageByHash() {
        String deletionOutput = qc.deleteMessageByHash("hash2");
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", deletionOutput);
    }
}