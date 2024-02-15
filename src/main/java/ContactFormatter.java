import java.util.List;

public class ContactFormatter {

    public static String formatContact(Contact contact) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*************************\n");
        stringBuilder.append(contact.toString());
        stringBuilder.append("*************************\n");
        return stringBuilder.toString();
    }

    public static String formatAllContacts(List<Contact> contactList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-------------------------\n");
        for (Contact contact : contactList) {
            stringBuilder.append(contact).append("\n");
        }
        stringBuilder.append("-------------------------\n");
        return stringBuilder.toString();

    }
}
