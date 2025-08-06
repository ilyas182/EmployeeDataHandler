package command.concretecommands;

import command.Command;
import receiver.Receiver;
import exception.InvalidCommandException;


/**
 * A concrete command responsible for deleting a record.
 * This class contains info required to perform an delete operation, index to be deleted.
 */
public class DeleteCommand implements Command {
    private Receiver receiver;
    private Integer index;
    private String[] oldValues;

    /**
     * Constructs a partially initialized DeleteCommand.
     * If no index is passed set index to null
     *
     * @param receiver The receiver object that will execute the command.
     */
    public DeleteCommand(Receiver receiver) {
        this.receiver = receiver;
        this.index = null;
    }

    /**
     * Constructs a fully initialized DeleteCommand ready for execution.
     *
     * @param receiver The receiver object that will execute the command.
     * @param index The index to be deleted.
     */
    public DeleteCommand(Receiver receiver, String index) {
        this.receiver = receiver;
        this.index = Integer.parseInt(index);
    }

    /**
     * getter for old values
     * @return list of old values
     */
    public String[] getOldValues() {
        return this.oldValues;
    }

    /**
     * getter for index
     * @return index
     */
    public Integer getIndex() {
        return this.index;
    }


    /**
     * Executes the primary action of the delete command.
     * @throws InvalidCommandException if the command cannot be executed due to an invalid state or missing parameters.
     */
    @Override
    public void execute() throws InvalidCommandException {
        if (this.index == null) {
            throw new InvalidCommandException("No Index Provided: Thrown at Delete command.Command");
        }
        if (this.index > receiver.getDataStore().size() || this.index < 1) {
            throw new InvalidCommandException("Incorrect Index Provided: Thrown at Delete command.Command");
        }
        oldValues = new String[]{receiver.getDataStore().get(index - 1)[0], receiver.getDataStore().get(index - 1)[1], receiver.getDataStore().get(index-1)[2]};
        receiver.delete(index);
        System.out.println("Delete");
    }

    /**
     * Override to string method
     * @return return overridden tostring output.
     */
    @Override
    public String toString() {
        return "Delete command.Command: " + index;
    }

    /**
     * Method to function name for checking if undoable
     * @return String
     */
    public boolean isUndoable() {
        return true;
    }

    /**
     * This method calls the receiver {@code addCommand} to undo the deleteCommand object
     */
    @Override
    public void undo(){
        receiver.addAtIndex(index, oldValues[0], oldValues[1], oldValues[2]);
    }
}
