public class UpdateCommand implements Command {

    private Receiver receiver;
    private Integer index;
    private String firstName;
    private String lastName;
    private String email;


    public UpdateCommand(Receiver receiver, Integer index, String firstName) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName;
    }

    public UpdateCommand(Receiver receiver, Integer index, String firstName, String lastName) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UpdateCommand(Receiver receiver, Integer index, String firstName, String lastName, String email) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public void execute() {
        if (firstName != null && lastName == null && email == null) {
            receiver.updateCommand(index, firstName);
        } else if (firstName != null && lastName != null && email == null) {
            receiver.updateCommand(index, firstName, lastName);
        } else {
            receiver.updateCommand(index, firstName, lastName, email);
        }
    }

    @Override
    public String toString() {
        return "Update Command: " + index + " " + firstName + " " + lastName + " " + email;
    }
}
