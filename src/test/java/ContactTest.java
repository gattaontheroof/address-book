import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ContactTest {

    @Test
    @DisplayName("Constructor sets expected values when valid")
    public void testConstructorSetsValuesWhenValid() {
        // Arrange
        String testName = "John Smith";
        String testPhoneNumber = "12345678901";
        String testEmail = "john@gmail.com";

        // Act
        Contact testContact = new Contact(testName, testPhoneNumber, testEmail);

        // Assert
        assertAll("Constructor sets values when valid",
                () -> assertEquals(testName, testContact.getName()),
                () -> assertEquals(testPhoneNumber, testContact.getPhoneNumber()),
                () -> assertEquals(testEmail, testContact.getEmail()));
    }
    // Name validation tests
    @Test
    @DisplayName("tests if name is valid")
    public void testValidName() {
        // Arrange
        String validName = "John Smith";

        // Act and Assert
        try {
            Contact.validateName(validName);
        } catch (IllegalArgumentException e) {
            fail("Valid name should not throw an exception: " + e.getMessage());
        }
    }
//    @Test
//    @DisplayName("tests if name consists of first name only")
//    public void testFistNameOnlyInput() {
//        // Arrange
//        String validName = "John";
//
//        // Act and Assert
//        try {
//            Contact.validateName(validName);
//            fail("Expected IllegalArgumentException to be thrown");
//        } catch (IllegalArgumentException e) {
//            assertEquals("Name format error: name must consist of first name followed by surname", e.getMessage());
//        }
//    }
    @Test
    @DisplayName("null input name tester")
    public void testThrowsExceptionForNullName()  {
        // Arrange
        String testName = null;

        // Act and Assert
        try {
            Contact.validateName(testName);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null or empty", e.getMessage());
        }
    }

    @Test
    @DisplayName("throws exception for empty name")
    public void testThrowsExceptionForEmptyName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validateName(""));
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("throws exception for name with special characters")
    public void testThrowsExceptionForNameWithSpecialCharacters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validateName("John@Doe"));
        assertEquals("Name must only contain letters, hyphens, apostrophes and spaces. It can consist of name or name and surname.", exception.getMessage());
    }


    // Phone number validation tests:
    @Test
    @DisplayName("validates phone number with 11 digits")
    public void testValidPhoneNumberWith11Digits() {
        assertDoesNotThrow(() -> Contact.validatePhoneNumber("12345678901"));
    }

    @Test
    @DisplayName("throws exception for null phone number")
    public void testThrowsExceptionForNullPhoneNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validatePhoneNumber(null));
        assertEquals("Phone number cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("throws exception for empty phone number")
    public void testThrowsExceptionForEmptyPhoneNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validatePhoneNumber(""));
        assertEquals("Phone number cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("throws exception for phone number with non-digit characters")
    public void testThrowsExceptionForPhoneNumberWithNonDigitCharacters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validatePhoneNumber("123-456-7890"));
        assertEquals("Telephone number must have 11 digits", exception.getMessage());
    }

    @Test
    @DisplayName("throws exception for phone number with less than 11 digits")
    public void testThrowsExceptionForPhoneNumberWithLessThan11Digits() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validatePhoneNumber("123456789"));
        assertEquals("Telephone number must have 11 digits", exception.getMessage());
    }

    @Test
    @DisplayName("throws exception for phone number with more than 11 digits")
    public void testThrowsExceptionForPhoneNumberWithMoreThan11Digits() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validatePhoneNumber("123456789012"));
        assertEquals("Telephone number must have 11 digits", exception.getMessage());
    }

    //Email address validation tests
    @Test
    @DisplayName("validates valid email address")
    public void testValidEmailAddress() {
        assertDoesNotThrow(() -> Contact.validateEmail("john.doe@example.com"));
    }

    @Test
    @DisplayName("throws exception for null email address")
    public void testThrowsExceptionForNullEmailAddress() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validateEmail(null));
        assertEquals("Email address cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("throws exception for empty email address")
    public void testThrowsExceptionForEmptyEmailAddress() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validateEmail(""));
        assertEquals("Email address cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("throws exception for invalid email address format")
    public void testThrowsExceptionForInvalidEmailAddressFormat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validateEmail("invalid_email"));
        assertEquals("Email address must have a valid email address format", exception.getMessage());
    }

    @Test
    @DisplayName("throws exception for missing domain in email address")
    public void testThrowsExceptionForMissingDomainInEmailAddress() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validateEmail("john.doe@"));
        assertEquals("Email address must have a valid email address format", exception.getMessage());
    }

    @Test
    @DisplayName("throws exception for missing top-level domain in email address")
    public void testThrowsExceptionForMissingTopLevelDomainInEmailAddress() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validateEmail("john.doe@example"));
        assertEquals("Email address must have a valid email address format", exception.getMessage());
    }

    @Test
    @DisplayName("throws exception for email address with only domain")
    public void testThrowsExceptionForEmailAddressWithOnlyDomain() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Contact.validateEmail("@example.com"));
        assertEquals("Email address must have a valid email address format", exception.getMessage());
    }
}

