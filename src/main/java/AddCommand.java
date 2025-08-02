public class AddCommand implements Command {

    private Receiver receiver;
    private String firstName;
    private String lastName;
    private String email;

    public AddCommand(Receiver receiver, String firstName, String lastName, String email) {
        this.receiver = receiver;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public void execute() {
        receiver.addCommand(firstName, lastName, email);
        System.out.printf("%s added successfully\n", firstName + " " + lastName + " " + email);
    }

    @Override
    public String toString() {
        return "Add Command: " + firstName + " " + lastName + " " + email;
    }

}
