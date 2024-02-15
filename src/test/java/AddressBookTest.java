import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AddressBookTest {
    AddressBook testAddressBook;
    Contact testContact, testContact1, testContact2;

    @BeforeEach
    void setUp() {
        testAddressBook = new AddressBook();
        testContact = new Contact("John Smith", "12345678901", "john@gmail.com");
        testContact1 = new Contact("Beth Sanchez", "12345555555", "beth@gmail.com");
        testContact2 = new Contact("Nathan Sands", "22345555555", "nathan@gmail.com");
    }

    @Test
    @DisplayName("adds contact object to contactList")
    public void testContactCanGetAddedToAddressBookReturnsTrueIfContactListWithTheContact() {
        // Arrange
        // Act
        testAddressBook.addContact(testContact);

        // Assert
        assertEquals(1, testAddressBook.getContactList().size(), "testAddressBook should contain 1 object");
        assertTrue(testAddressBook.getContactList().contains(testContact), "testAddressBook should contain 1 object");
    }

    @Test
    @DisplayName("disallows duplicate phone numbers")
    public void testAddContactCannotAddContactWithExistingPhoneNumber() {
        //Arrange
        Contact testContact3 = new Contact("Anna Swift", "55555555555", "anna@gmail.com");
        Contact testContact4 = new Contact("Jane Jones", "55555555555", "jane@jane.com");
        testAddressBook.addContact(testContact3);
        testAddressBook.addContact(testContact4);

        //Act
        int actualResult = testAddressBook.getContactList().size();
        int expectedResult = 1;

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("disallows duplicate email addresses")
    public void testAddContactCannotAddContactWithExistingEmailAddress() {
        //Arrange
        Contact testContact3 = new Contact("Anna Swift", "55555555555", "anna@gmail.com");
        Contact testContact4 = new Contact("Jane Jones", "44444444444", "anna@gmail.com");
        testAddressBook.addContact(testContact3);
        testAddressBook.addContact(testContact4);

        //Act
        int actualResult = testAddressBook.getContactList().size();
        int expectedResult = 1;

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("removes contact from contactList")
    public void testRemovesContactRemovesContactFromAddressBookReturnsTrue() {
        //Arrange
        testAddressBook.addContact(testContact1);
        testAddressBook.addContact(testContact2);

        //Act
        testAddressBook.removeContact(testContact1);

        //Assert
        assertEquals(1, testAddressBook.getContactList().size(), "TestAddressBook should contain 1 object");
        assertTrue(testAddressBook.getContactList().contains(testContact2), "TestAddressBook should contain 1 object");
    }

    @Test
    @DisplayName("getcontact by id")
    public void testContactCanBeSelectedById() {
        // Arrange
        testAddressBook.addContact(testContact1);
        int id = testContact1.getId();

        // Act
        Optional<Contact> result = testAddressBook.getContactById(id);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(result.get(), testContact1);
    }

    @Test
    @DisplayName("searches contact by name")
    public void testSearchesContactByNameReturnsContact() {
        //Arrange
        testAddressBook.addContact(testContact1);
        testAddressBook.addContact(testContact2);

        //Act
        List<Contact> foundContacts = testAddressBook.searchByName("Beth Sanchez");

        //Assert
        assertEquals(foundContacts.get(0), testContact1);
        assertEquals(1, foundContacts.size(), "TestAddressBook should contain 1 object");
    }
    @Test
    @DisplayName("searches contact by phone number")
    public void testSearchesContactByPhoneNumberReturnsContact() {
        //Arrange
        testAddressBook.addContact(testContact1);
        testAddressBook.addContact(testContact2);

        //Act
        Optional<Contact> foundContact = testAddressBook.searchByPhoneNumber("12345555555");

        //Assert
        assertTrue(foundContact.isPresent(), "Contact should be found");
        assertEquals(testContact1, foundContact.get(), "Found contact should be equal to testContact1");
    }
    @Test
    @DisplayName("searches contact by phone number")
    public void testSearchesContactByEmailReturnsContact() {
        //Arrange
        testAddressBook.addContact(testContact1);
        testAddressBook.addContact(testContact2);

        //Act
        Optional<Contact> foundContact = testAddressBook.searchByEmail("beth@gmail.com");

        //Assert
        assertTrue(foundContact.isPresent(), "Contact should be found");
        assertEquals(testContact1, foundContact.get(), "Found contact should be equal to testContact1");
    }


    @Test
    @DisplayName("edits contact name")
    public void testEditContactOverridesAndSavesContactsName() {

        //Arrange
        Contact testContact3 = new Contact("Anna Swift","12345555555", "anna@gmail.com" );
        testAddressBook.addContact(testContact1);

        //Act
        testContact1.editContact("Anna Willow","66645555555", "anna@gmail.com" );
//        String expectedResult = "Contact{name='Anna Willow', phoneNumber='666455555', email='willow@gmail.com'}";
        Contact actualResult = testContact1;

        // Assert
        assertEquals("Anna Willow", actualResult.getName());
        assertEquals("66645555555", actualResult.getPhoneNumber());
        assertEquals("anna@gmail.com", actualResult.getEmail());
    }

    @Test
    @DisplayName("edits contact phone number")
    public void testEditContactOverridesAndSavesContactsPhoneNumber() {

        //Arrange
        Contact testContact3 = new Contact("Anna Swift","12345555555", "anna@gmail.com" );
        testAddressBook.addContact(testContact1);

        //Act
        testContact1.editContact("Anna Swift","66645555555", "anna@gmail.com" );
//        String expectedResult = "Contact{name='Anna Willow', phoneNumber='666455555', email='willow@gmail.com'}";
        Contact actualResult = testContact1;

        // Assert
        assertEquals("Anna Swift", actualResult.getName());
        assertEquals("66645555555", actualResult.getPhoneNumber());
        assertEquals("anna@gmail.com", actualResult.getEmail());
    }

    @Test
    @DisplayName("edits contact email")
    public void testEditContactOverridesAndSavesContactsEmail() {

        //Arrange
        Contact testContact3 = new Contact("Anna Swift","12345555555", "anna@gmail.com" );
        testAddressBook.addContact(testContact1);

        //Act
        testContact1.editContact("Anna Swift","12345555555", "swift@gmail.com" );
//        String expectedResult = "Contact{name='Anna Willow', phoneNumber='666455555', email='willow@gmail.com'}";
        Contact actualResult = testContact1;

        // Assert
        assertEquals("Anna Swift", actualResult.getName());
        assertEquals("12345555555", actualResult.getPhoneNumber());
        assertEquals("swift@gmail.com", actualResult.getEmail());
    }

    @Test
    @DisplayName("edits contact with null details")
    public void testEditContactReturnsNullWhenEditingWithNullDetails() {

        //Arrange
        Contact testContact3 = new Contact("Anna Swift","12345555555", "anna@gmail.com" );
        testAddressBook.addContact(testContact1);

        //Act
        Contact result = testAddressBook.editContact(null, "New Name", "New Phone", "New Email");

        // Assert
        assertNull(result, "Expected null when editing a null contact");
    }

    @Test
    @DisplayName("edits contact but disallows changing to telephone number that already exists")
    public void testEditContactWillNotSavesContactsIfPhoneNumberAlreadyExists() {
        //Arrange
        Contact testContact3 = new Contact("Anna Swift","55555555555", "anna@gmail.com");
        Contact testContact4 = new Contact("Jane Jones", "22222222222", "jane@jane.com");
        testAddressBook.addContact(testContact3);
        testAddressBook.addContact(testContact4);

        //Act
        testAddressBook.editContact(testContact3, "Anna Swift","22222222222", "anna@gmail.com");

        // Assert
        assertEquals("Anna Swift", testContact3.getName());
        assertEquals("55555555555", testContact3.getPhoneNumber());
        assertEquals("anna@gmail.com", testContact3.getEmail());
    }

    @Test
    @DisplayName("edits contact but disallows changing to email that already exists")
    public void testEditContactWillNotSavesContactsIfEmailAlreadyExists() {
        //Arrange
        Contact testContact3 = new Contact("Anna Swift","55555555555", "anna@gmail.com");
        Contact testContact4 = new Contact("Jane Jones", "22222222222", "jane@jane.com");
        testAddressBook.addContact(testContact3);
        testAddressBook.addContact(testContact4);

        //Act
        testAddressBook.editContact(testContact3, "Anna Swift","55555555555", "jane@jane.com");

        // Assert
        assertEquals("Anna Swift", testContact3.getName());
        assertEquals("55555555555", testContact3.getPhoneNumber());
        assertEquals("anna@gmail.com", testContact3.getEmail());
    }

    @Test
    @DisplayName("removes contact")
    public void testRemovesContactFromAddressBookReturns0ForAddressBookSize() {

        //Arrange
        Contact testContact3 = new Contact("Anna Swift","12345555555", "anna@gmail.com" );
        testAddressBook.addContact(testContact3);

        //Act
        boolean actualResult = testAddressBook.removeContact(testContact3);

        //Assert
        assertTrue(actualResult);
        assertEquals(0,  testAddressBook.getContactList().size(), "TestAddressBook should contain 0 objects");
    }

    @Test
    @DisplayName("delete all contacts")
    public void testDeleteAllContactsRemovesAllContactsFromAddressBook() {

        // Arrange
        testAddressBook.addContact(testContact);
        testAddressBook.addContact(testContact1);

        // Act
        testAddressBook.deleteAllContacts();

        // Assert
        assertEquals(0, testAddressBook.getContactList().size(), "testAddressBook should contain no objects");
    }


}





