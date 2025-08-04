package client;

import command.*;
import command.concretecommands.*;
import invoker.Invoker;
import receiver.Receiver;

import java.util.Stack;

/**
 * The Client creates and configures the concrete command objects. The client must pass all of the command parameters,
 * including a receiver instance, into the command’s constructor.
 * After that, the resulting command may be associated with one or multiple senders.
 */
public class Client {

    public static final Stack<Command> history = new Stack<>();
    /**
     * Main Method for Application Initiation
     * @param args default main method args
     */
    public static void main(String[] args) {


        // Create receiver as client needs to pass receiver instance to the command constructors
        Receiver receiver = new Receiver();

        // Concrete Commands
        Command listCommand = new ListCommand(receiver);
        Command undoCommand = new UndoCommand(receiver);
        Command addCommand1 = new AddCommand(receiver, "Ben Ng ...@gmail.com");
        Command addCommand2 = new AddCommand(receiver, "Benben NgNg ben@gmail.com");
        Command addCommand3 = new AddCommand(receiver, "ben super hello@gmail.com");
        Command deleteCommand1 = new DeleteCommand(receiver, 10);
        Command deleteCommand2 = new DeleteCommand(receiver, 1);
        Command updateCommand1 = new UpdateCommand(receiver, "1 test testing ...@gmail.com");
        Command updateCommand2 = new UpdateCommand(receiver, "10");
        Command updateCommand3 = new UpdateCommand(receiver, "1 ilyas");
        Command updateCommand4 = new UpdateCommand(receiver, "2 taufiq rahmat");
        Command updateCommand5 = new UpdateCommand(receiver, "2 wei long weilong@gmail.com");

        // command.Command List
        Command[] commands = new Command[]{
//                addCommand1,
//                undoCommand,
//                listCommand,
//                addCommand2,
//                listCommand,
//                deleteCommand1,
//                listCommand,
//                deleteCommand2,

//                listCommand,
//                undoCommand,
//                addCommand1,
                addCommand2,
                addCommand3,
                updateCommand1,
                updateCommand2,
                listCommand,
                updateCommand3,
                updateCommand4,
                listCommand,
                updateCommand5,
                listCommand,
                undoCommand,
//                listCommand,
                listCommand,
        };

        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(commands);

        invoker.executeCommand(history);

        System.out.println("----------- Stack Info ------------");
        System.out.println("Stack Size Left: " + history.size());

        for (int i = 0; i < history.size(); i++) {
            System.out.println(history.get(i));
        }

        receiver.storeToFile();
    }
}
