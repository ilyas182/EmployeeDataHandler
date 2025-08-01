public class AddCommand implements Command {

    private String firstName;
    private String lastName;
    private String email;

    public Command(Receiver receiver, String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void execute() {
        Receiver.addCommand();
    }

}
