public class UpdateCommand implements Command {

    private Receiver receiver;
    private Integer index;
    private String firstName;
    private String lastName;
    private String email;

    public UpdateCommand(Receiver receiver, Integer index, String firstName, String lastName, String email) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public void execute() {
        receiver.updateCommand(index, firstName, lastName, email);
    }
}
