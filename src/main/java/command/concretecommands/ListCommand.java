package command.concretecommands;

import command.Command;
import exception.InvalidCommandException;
import receiver.Receiver;

/**
 * A concrete command responsible for listing records.
 */
public class ListCommand implements Command {

    private Receiver receiver;

    /**
     * Constructs a fully initialized ListCommand ready for execution.
     * @param receiver The receiver object that will execute the command.
     */
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Executes the primary action of the add command.
     */
    @Override
    public void execute() {
        receiver.listCommand();
    }
}
