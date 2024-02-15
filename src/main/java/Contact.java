public class Contact {

    private static int nextId = 1;
    private int id;
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        validateName(name);
        validatePhoneNumber(phoneNumber);
        validateEmail(email);
        this.id = nextId++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    // should allow name or name and surname format
        if (!name.trim().matches("^[a-zA-Z][a-zA-Z'-]*[a-zA-Z]([ ]?[a-zA-Z][a-zA-Z'-]*)?$")) {
            throw new IllegalArgumentException("Name must only contain letters, hyphens, apostrophes and spaces. It can consist of name or name and surname.");
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        // Remove any non-digit characters from the phone number
        String digitsOnly = phoneNumber.replaceAll("[^0-9]", "");

        if (digitsOnly.length() != 11) {
            throw new IllegalArgumentException("Telephone number must have 11 digits");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }

        if (!email.trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email address must have a valid email address format");
        }
    }

    public void editContact(String newName, String newPhoneNumber, String newEmail) {
        if (newName != null && !newName.isEmpty()) {
            this.name = newName;
        }
        if (newPhoneNumber != null && !newName.isEmpty()) {
            this.phoneNumber = newPhoneNumber;
        }
        if (newEmail != null && !newEmail.isEmpty()) {
            this.email = newEmail;
        }
    }
}
