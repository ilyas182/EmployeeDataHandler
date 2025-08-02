public class DeleteCommand implements Command {

    private Receiver receiver;
    private Integer index;

    public DeleteCommand(Receiver receiver, Integer index) {
        this.receiver = receiver;
        this.index = index;
    }

    @Override
    public void execute() {
        receiver.deleteCommand(index);
    }


    @Override
    public String toString() {
        return "Delete Command: " + index + "Before" receiver.getDataStore().get(3);
    }

}
