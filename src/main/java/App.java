import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
public class App {

    private AddressBook addressBook;

    public App() {
        addressBook = new AddressBook();
        loadContacts();
    }

    private void loadContacts() {
        Contact contact1 = new Contact("Agata Robertson", "07777777777", "agata@agata.com");
        Contact contact2 = new Contact("Sheldon Cooper", "06666666666", "sheldon@sheldon.com");
        Contact contact3 = new Contact("Kermit Frog", "05555555555", "kermit@kermit.com");

        addressBook.addContact(contact1);
        addressBook.addContact(contact2);
        addressBook.addContact(contact3);
    }

    public void start() throws IOException {
        boolean running = true;
        while (running) {

            printMainMenu();

            Scanner optionInput = new Scanner(System.in);
            int option = 10;

            try {
                option = optionInput.nextInt();
            }
            catch(Exception ignored){}

            switch (option) {
                case 1:
                    System.out.println("You selected Option 1.");
                    boolean addingUserComplete = false;
                    while (!addingUserComplete) {
                        System.out.println("Enter name: first name only or first name space surname");
                        Scanner userInputContactName = new Scanner(System.in);
                        String contactName = userInputContactName.nextLine();
                        System.out.println("Enter their phone number: 11 digits only");
                        Scanner userInputContactPhone = new Scanner(System.in);
                        String contactPhone = userInputContactPhone.nextLine();
                        System.out.println("Enter their email address");
                        Scanner userInputContactEmail = new Scanner(System.in);
                        String contactEmail = userInputContactEmail.nextLine();

                        try {
                            Contact newContact = new Contact(contactName, contactPhone, contactEmail);

                            addressBook.addContact(newContact);

                            addingUserComplete = true;
                            System.out.println("New contact has been added");
                        }
                        catch(Exception e) {
                            System.out.println(e.getMessage());
                        }
                        //System.out.println(ContactFormatter.formatAllContacts(addressBook.getContactList()));
                        pressEnterKeyToContinue();
                    };

                    break;
                case 2:
                    printContactSearchMenu();

                    Scanner userInputSecondOption = new Scanner(System.in);
                    int secondOption = 4;

                    try {
                        secondOption = userInputSecondOption.nextInt();
                    }
                    catch(Exception ignored){}

                    switch (secondOption) {
                        case 1:
                            System.out.println("Searching for contact by name. Please type contact's name... ");
                            Scanner userInputSearchName = new Scanner(System.in);
                            String searchedUserName = userInputSearchName.nextLine();

                            List<Contact> contactsByName = addressBook.searchByName(searchedUserName);

                            System.out.println(ContactFormatter.formatAllContacts(contactsByName));

                            pressEnterKeyToContinue();
                            break;
                        case 2:
                            System.out.println("Searching for contact by phone number. Please type contact's phone number... ");
                            Scanner userInputSearchByPhoneNumber = new Scanner(System.in);
                            String searchedUserByPhoneNumber = userInputSearchByPhoneNumber.nextLine();
                            Optional<Contact> contactByPhoneNumber = addressBook.searchByPhoneNumber(searchedUserByPhoneNumber);
                            if (!contactByPhoneNumber.isEmpty()){
                                System.out.println(ContactFormatter.formatContact(contactByPhoneNumber.get()));
                            }
                            else {
                                System.out.println("No contact found...");
                            }

                            pressEnterKeyToContinue();
                            break;
                        case 3:
                            System.out.println("Searching for contact by email address. Please type contact's email address... ");
                            Scanner userInputSearchByEmail = new Scanner(System.in);
                            String searchedUserByEmail = userInputSearchByEmail.nextLine();
                            Optional<Contact> contactByEmail = addressBook.searchByEmail(searchedUserByEmail);

                            if (!contactByEmail.isEmpty()){
                                System.out.println(ContactFormatter.formatContact(contactByEmail.get()));
                            }
                            else {
                                System.out.println("No contact found...");
                            }

                            pressEnterKeyToContinue();
                            break;
                        case 4:
                        default:
                            System.out.println("Returning to main menu.");
                            pressEnterKeyToContinue();
                            break;
                    }
                    break;
                case 3:
                    System.out.println("You selected Option 3. Choose id of the contact to edit");
                    System.out.println(ContactFormatter.formatAllContacts(addressBook.getContactList()));
                    Scanner userInputSearchContactToEdit = new Scanner(System.in);

                    try {
                        int searchedContactToEditId = userInputSearchContactToEdit.nextInt();

                        Optional<Contact> contactToEdit = addressBook.getContactById(searchedContactToEditId);

                        if(contactToEdit.isPresent()) {
                            Contact extractedContactToEdit = contactToEdit.get();

                            System.out.println("Enter new name: press enter to leave unchanged");
                            Scanner userInputNewName = new Scanner(System.in);
                            String newName = userInputNewName.nextLine();

                            // set to current name if blank
                            newName = newName.equalsIgnoreCase("") ? extractedContactToEdit.getName() : newName;

                            System.out.println("Enter new phone number: press enter to leave unchanged");
                            Scanner userInputNewPhoneNumber = new Scanner(System.in);
                            String newPhoneNumber = userInputNewName.nextLine();

                            // set to current phone number if blank
                            newPhoneNumber = newPhoneNumber.equalsIgnoreCase("") ? extractedContactToEdit.getPhoneNumber() : newPhoneNumber;

                            System.out.println("Enter new email address: press enter to leave unchanged");
                            Scanner userInputNewEmail = new Scanner(System.in);
                            String newEmail = userInputNewName.nextLine();

                            // set to current phone number if blank
                            newEmail = newEmail.equalsIgnoreCase("") ? extractedContactToEdit.getEmail() : newEmail;

                            try {
                                System.out.println("...Updating contact with id " + searchedContactToEditId);
                                addressBook.editContact(extractedContactToEdit, newName, newPhoneNumber, newEmail);
                                System.out.println("Your new list of contacts: ");
                                System.out.println(ContactFormatter.formatAllContacts(addressBook.getContactList()));
                            }
                            catch(Exception e) {
                                System.out.println(e.getMessage());
                            }

                        }
                        else {
                            System.out.println("No contact found with id: " + searchedContactToEditId);
                        }


                    }
                    catch(Exception e) {
                        System.out.println("Invalid id");
                    }

                    pressEnterKeyToContinue();
                    break;
                case 4:
                    System.out.println("You selected Option 4. Choose id of the contact to remove");
                    System.out.println(ContactFormatter.formatAllContacts(addressBook.getContactList()));

                    Scanner userInputSearchContactToRemove = new Scanner(System.in);

                    try {
                        int searchedContactToRemoveId = userInputSearchContactToRemove.nextInt();

                        Optional<Contact> contactToRemove = addressBook.getContactById(searchedContactToRemoveId);

                        if(contactToRemove.isPresent()) {
                            Contact extractedContact = contactToRemove.get();

                            System.out.println("...Removing contact with id " + searchedContactToRemoveId);
                            addressBook.removeContact(extractedContact);
                            System.out.println("Your new list of contacts: ");
                            System.out.println(ContactFormatter.formatAllContacts(addressBook.getContactList()));
                        }
                        else {
                            System.out.println("No contact found with id: " + searchedContactToRemoveId);
                        }
                    }
                    catch(Exception e) {
                        System.out.println("Invalid id");
                    }

                    pressEnterKeyToContinue();
                    break;
                case 5:
                    System.out.println("You selected Option 5. Showing all your contacts");

                    System.out.println(ContactFormatter.formatAllContacts(addressBook.getContactList()));

                    pressEnterKeyToContinue();
                    break;
                case 6:
                    System.out.println("You selected Option 6. Deleting all your contacts");
                    System.out.println("Are you sure you want to delete all your contacts? Enter: y/n");

                    Scanner userInputDeleteAllContactsOption = new Scanner(System.in);
                    String deleteAllContactsOption = userInputDeleteAllContactsOption.nextLine();

                    if(deleteAllContactsOption.equalsIgnoreCase("y")) {
                        addressBook.deleteAllContacts();
                        System.out.println("All contacts deleted");
                    }
                    else {
                        System.out.println("Cancelling contacts deletion.");
                    }

                    pressEnterKeyToContinue();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    pressEnterKeyToContinue();
            }

        }
    }

    private void printMainMenu() {
        System.out.println("1. Add a new contact ");
        System.out.println("2. Search contact");
        System.out.println("3. Edit contact");
        System.out.println("4. Remove a contact");
        System.out.println("5. Show all your contacts");
        System.out.println("6. Delete all contacts at once");
        System.out.println("7. Exit");
        System.out.println("");
    }

    private void printContactSearchMenu(){
        System.out.println("You selected Option 2.");
        System.out.println("1. Search for contact by name");
        System.out.println("2. Search for contact by phone number");
        System.out.println("3. Search for contact by email address");
        System.out.println("4. Search for contact by name, phone number or email address");
        System.out.println("5. Go back to main menu");
        System.out.println("");
    }

    private void pressEnterKeyToContinue() {
        System.out.println("Press Enter key to continue...");

        try {
            System.in.read();
        } catch (IOException ignored) {
        }
    }

    private void runDemo() {
        System.out.println("");
        System.out.println("*********************************************");
        System.out.println("Hello, welcome to your Address Book");
        System.out.println("*********************************************");
        System.out.println("");
        System.out.println("Let's start from adding a new contact to your Address Book!");
        System.out.println("");

        Contact contact1 = new Contact("Agata Robertson", "07777777777", "agata@agata.com");
        System.out.println("A new contact, Agata Robertson, has been created.");
        System.out.println("");

        addressBook.addContact(contact1);
        System.out.println("...and added to your Address Book");

        System.out.println("*********************************************");
        System.out.println("Your Address Book will now show you all the contacts it contains: ");
        Contact contact3 = new Contact("Luke Skywalker", "12345555566", "lukes@tatooine.com");
        Contact contact4 = new Contact("Harry Potter", "22345555566", "hp@gmail.com");
        addressBook.addContact(contact3);
        addressBook.addContact(contact4);
        System.out.println("*********************************************");
        System.out.println(ContactFormatter.formatAllContacts(addressBook.getContactList()));
        System.out.println("*********************************************");
        System.out.println("Your contact, Harry Potter, has changed his phone number and email address");
        contact4.editContact("Harry Potter", "44444444444", "harry@hogwarts.com");
        System.out.println("Here are his updated contact details: ");
        System.out.println(contact4);
        System.out.println("");
        System.out.println("And here is the updated contact list in the Address Book: ");
        System.out.println(ContactFormatter.formatAllContacts(addressBook.getContactList()));


        System.out.println("*********************************************");
        System.out.println("Luke Skywalker will be removed from the Address Book");
        addressBook.removeContact(contact3);
        System.out.println("Luke Skywalker has been removed from the Address Book and the Address Books contains the following contacts: ");
        System.out.println(ContactFormatter.formatAllContacts(addressBook.getContactList()));
        System.out.println("*********************************************");

        System.out.println("*********************************************");
        System.out.println("*********************************************");
        System.out.println("*********************************************");


        System.out.println("Welcome to your Address Book! Please select an option:");
    }

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.start();
    }

}



