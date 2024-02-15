import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressBook {
    private List<Contact> contactList;

    public AddressBook() {
        contactList = new ArrayList<>();
    }

    public List<Contact> getContactList() {

        return contactList
                .stream()
                .sorted(Comparator.comparing(Contact::getName))
                .collect(Collectors.toList());
    }

    public Optional<Contact> getContactById(int id) {
        return contactList.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst();
    }

    public boolean addContact(Contact contact) {
        if (phoneNumberAlreadyExists(contactList, contact.getPhoneNumber()) || emailAlreadyExists(contactList, contact.getEmail())) {
            return false;
        }
//        if (!isValidName(contact.getName()) || !isValidPhoneNumber(contact.getPhoneNumber()) || !isValidEmail(contact.getEmail())) {
//            return false; // Contact is considered invalid if the name, phone number, or email is not valid
//        }
        return contactList.add(contact);
    }

    public boolean removeContact(Contact contact) {
        return contactList.remove(contact);
    }

    public List<Contact> searchByName(String name) {
        return contactList.stream()
                .filter(contact -> contact.getName().equalsIgnoreCase(name))    //case-insensitive matching
                .collect(Collectors.toList());
    }
    public Optional<Contact> searchByPhoneNumber(String phoneNumber) {
        return contactList.stream()
                .filter(contact -> contact.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                .findFirst();
    }

    public Optional<Contact> searchByEmail(String email) {
        return contactList.stream()
                .filter(contact -> contact.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Contact editContact(Contact contact, String newName, String newPhoneNumber, String newEmail) {

        if (contact == null)
            return null;

        // create temp list to validate if email or phone number already exist
        List<Contact> tempContacts = new ArrayList<>(contactList);
        tempContacts.remove(contact);

        if (!phoneNumberAlreadyExists(tempContacts, newPhoneNumber)) {
            contact.setPhoneNumber(newPhoneNumber);
        }
        else {
            System.out.println("This phone number already exists in the address book.");
        }

        if (!emailAlreadyExists(tempContacts, newEmail)) {
            contact.setEmail(newEmail);
        }
        else {
            System.out.println("This email already exists in the address book.");
        }

        contact.setName(newName);

        return contact;
    }

    boolean phoneNumberAlreadyExists(List<Contact> contactList, String phoneNumber) {
        for (Contact c : contactList) {
            if (c.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    boolean emailAlreadyExists(List<Contact> contactList, String email) {
        for (Contact c : contactList) {
            if (email.equals(c.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public void deleteAllContacts() {
        contactList.clear();
    }
}
