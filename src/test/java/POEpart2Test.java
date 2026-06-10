/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author troyj
 */
import com.mycompany.poe_part2.POE_Part2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class POEpart2Test {

    // ================= USERNAME TESTS =================

    @Test
    void testValidUsername() {
        assertTrue(POE_Part2.isValidUsername("ab_cd"));
    }

    @Test
    void testUsernameTooLong() {
        assertFalse(POE_Part2.isValidUsername("abcdef_"));
    }

    @Test
    void testUsernameWithoutUnderscore() {
        assertFalse(POE_Part2.isValidUsername("abcde"));
    }

    // ================= PASSWORD TESTS =================

    @Test
    void testValidPassword() {
        assertTrue(POE_Part2.isValidPassword("Password1!"));
    }

    @Test
    void testPasswordTooShort() {
        assertFalse(POE_Part2.isValidPassword("P1!a"));
    }

    @Test
    void testPasswordWithoutUppercase() {
        assertFalse(POE_Part2.isValidPassword("password1!"));
    }

    @Test
    void testPasswordWithoutNumber() {
        assertFalse(POE_Part2.isValidPassword("Password!"));
    }

    @Test
    void testPasswordWithoutSpecialCharacter() {
        assertFalse(POE_Part2.isValidPassword("Password1"));
    }

    // ================= PHONE NUMBER TESTS =================

    @Test
    void testValidPhoneNumber() {
        assertTrue(POE_Part2.isValidPhoneNumber("+27123456789"));
    }

    @Test
    void testInvalidPhoneNumberMissingPlus() {
        assertFalse(POE_Part2.isValidPhoneNumber("27123456789"));
    }

    @Test
    void testInvalidPhoneNumberWrongLength() {
        assertFalse(POE_Part2.isValidPhoneNumber("+271234"));
    }
}