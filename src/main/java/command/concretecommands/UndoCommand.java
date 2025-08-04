package command.concretecommands;

import command.Command;
import exception.InvalidCommandException;
import receiver.Receiver;

/**
 *  A concrete command responsible for undoing the last performed action.
 */
public class UndoCommand implements Command {

    private Receiver receiver;

    /**
     * Constructs a fully initialized UndoCommand ready for execution.
     *
     * @param receiver The receiver object that will execute the command.
     */
    public UndoCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Executes the primary action of the add command.
     */
    @Override
    public void execute() {
        receiver.undoCommand();
    }

    /**
     * Method to function name for checking if undoable
     * @return String
     */
    public boolean isUndoable() {
        return false;
    }

    @Override
    public void undo() {
        receiver.undoCommand();
    }
}

