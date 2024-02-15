import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactFormatterTest {
    List<Contact> testListOfContacts;
    Contact testContact1;
    Contact testContact2;
    @BeforeEach
    void setUp() {
        testListOfContacts = new ArrayList<>();
        testContact1 = new Contact("John Smith", "12345678901", "john@gmail.com");
        testContact2 = new Contact("Beth Sanchez", "12345555555", "beth@gmail.com");
    }

    @Test
    @DisplayName("displays contact details")
    public void testDisplayContactDetails() {

        //Act
        String result = ContactFormatter.formatContact(testContact1);
        //Assert

        assertThat(result, containsString("John Smith"));
    }

    @Test
    @DisplayName("displays all contacts from contactList")
    public void testDisplaysAllContactsFromContactList() {
        //Arrange
        testListOfContacts.add(testContact1);
        testListOfContacts.add(testContact2);

        //Act
        String result = ContactFormatter.formatAllContacts(testListOfContacts);

        //Assert
        assertThat(result, containsString("John Smith"));
        assertThat(result, containsString("Beth Sanchez"));
    }
}