package command.concretecommands;

import command.Command;
import receiver.Receiver;

import java.util.Stack;

/**
 *  A concrete command responsible for undoing the last performed action.
 */
public class UndoCommand implements Command {

    private Receiver receiver;
    Stack<Command> history;

    /**
     * Constructs a fully initialized UndoCommand ready for execution.
     *
     * @param receiver The receiver object that will execute the command.
     */
    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        this.history = history;
    }

    /**
     * Executes the primary action of the add command.
     */
    @Override
    public void execute() {
        if (history.size() <= 0) {
            System.out.println("Nothing to undo");
            return;
        }
        Command lastCommand = history.pop();
        System.out.println("Undo");
        lastCommand.undo();
    }

    /**
     * Method to function name for checking if undoable
     * @return String
     */
    public boolean isUndoable() {
        return false;
    }

    /**
     * Calls the receiver undo method
     */
    @Override
    public void undo() {}
}

