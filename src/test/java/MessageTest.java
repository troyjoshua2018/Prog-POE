/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author troyj
 */
import com.mycompany.poe_part2.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    void testMessageConstructorAndGetters() {

        Message msg = new Message(
                101,
                "+27111111111",
                "+27222222222",
                "Hello",
                "abc123"
        );

        assertEquals(101, msg.getMessageID());
        assertEquals("+27111111111", msg.getSenderPhone());
        assertEquals("+27222222222", msg.getRecipientPhone());
        assertEquals("Hello", msg.getMessageText());
        assertEquals("abc123", msg.getMessageHash());
    }
}