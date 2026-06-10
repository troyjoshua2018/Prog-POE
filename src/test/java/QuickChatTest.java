/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author troyj
 */
import com.mycompany.poe_part2.QuickChat;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class QuickChatTest {

    @Test
    void testGenerateHash() throws Exception {

        Method method = QuickChat.class.getDeclaredMethod(
                "generateHash",
                String.class
        );

        method.setAccessible(true);

        String hash1 = (String) method.invoke(null, "Hello");
        String hash2 = (String) method.invoke(null, "Hello");

        assertNotNull(hash1);

        // SHA-256 hashes are 64 chars
        assertEquals(64, hash1.length());

        // Same input should produce same hash
        assertEquals(hash1, hash2);
    }

    @Test
    void testDifferentMessagesProduceDifferentHashes() throws Exception {

        Method method = QuickChat.class.getDeclaredMethod(
                "generateHash",
                String.class
        );

        method.setAccessible(true);

        String hash1 = (String) method.invoke(null, "Hello");
        String hash2 = (String) method.invoke(null, "World");

        assertNotEquals(hash1, hash2);
    }
}