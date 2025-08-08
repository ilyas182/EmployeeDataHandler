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

    /**
     * Main Method for Application Initiation
     * @param args default main method args
     */
    public static void main(String[] args) {

        Stack<Command> history = new Stack<>();

        // Create receiver as client needs to pass receiver instance to the command constructors
        Receiver receiver = new Receiver();

        // Concrete Commands
        Command listCommand = new ListCommand(receiver);
        Command undoCommand = new UndoCommand(receiver, history);
        Command addCommand1 = new AddCommand(receiver, "first_name last_name email");
        Command addCommand2 = new AddCommand(receiver, "2 Doe simple@example.com");
        Command addCommand3 = new AddCommand(receiver, "hanna 5 tetter.tots@potatoesarelife.com");
        Command addCommand4 = new AddCommand(receiver, "Ah Boon green-tea@teaforlife.com");
        Command updateCommand1 = new UpdateCommand(receiver, "3 Adam");
        Command updateCommand2 = new UpdateCommand(receiver, "1 blue bell ice-cream@alaskaFields.org");
        Command deleteCommand1 = new DeleteCommand(receiver, "A");



        // command.Command List
        Command[] commands = new Command[]{
                addCommand1,
                addCommand3,
                listCommand,
                deleteCommand1
        };

//        receiver.loadFromFile();
        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(commands);
        invoker.executeCommand(history);

//        receiver.storeToFile();
    }
}
