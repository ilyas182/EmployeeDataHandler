public class DeleteCommand implements Command {

    private Receiver receiver;
    private Integer index;
    private String[] oldValues;

    public DeleteCommand(Receiver receiver, Integer index) {
        this.receiver = receiver;
        this.index = index;
    }

    public String[] getOldValues() {
        return this.oldValues;
    }

    public Integer getIndex() {
        return this.index;
    }

    @Override
    public void execute() {
        oldValues = new String[]{receiver.getDataStore().get(index - 1)[0], receiver.getDataStore().get(index - 1)[1], receiver.getDataStore().get(index-1)[2]};
        receiver.deleteCommand(index);
    }

    @Override
    public String toString() {
//        return "Delete Command: " + index + "Before" receiver.getDataStore().get(3);
        return " ";
    }
}
