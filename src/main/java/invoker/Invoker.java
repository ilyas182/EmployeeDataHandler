package invoker;

import command.concretecommands.AddCommand;
import command.Command;
import command.concretecommands.DeleteCommand;
import command.concretecommands.UpdateCommand;
import exception.InvalidCommandException;

import java.util.Stack;

/**
 * The Invoker class is responsible for initiating the commands. It must have a field to store the reference to a
 * command object. The Invoker triggers the commands instead of sending the request directly to the receiver. The
 * invoker is not responsible for creating the command object, it usually gets a pre-created command from the client.
 */
public class Invoker {
    // Hold commands list
    private Command[] cmdToExecute;


    /**
     * Takes in list of {@code Commands} from Client class and sets to {@code cmdToExecute} instance variable
     * @param commands List of Command objects
     */
    public void setCommandsForExecution(Command[] commands) {
        this.cmdToExecute = commands;
    }

    /**
     * Takes in a stack of {@code Command} objects and tries to execute them.
     * @param history Stack of {@code Command} objects
     */
    public void executeCommand(Stack<Command> history){
        for (Command command : cmdToExecute) {
            try {
                command.execute();
                if (command instanceof AddCommand || command instanceof UpdateCommand || command instanceof DeleteCommand) {
                    history.push(command);
                }
            } catch (InvalidCommandException e) {
                System.out.println("Exception Caught at invoker.Invoker: Illegal Argument Exception");
                System.out.println(e.getMessage());
            }
        }
    }
}
