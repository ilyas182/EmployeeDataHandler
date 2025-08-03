import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

    protected static final Stack<Command> history = new Stack<>();

    public static void main(String[] args) {

        // Create receiver as client needs to pass receiver instance to the command constructors (as per practicum instructions)
        Receiver receiver = new Receiver();

        // Assume that the stack is here because honestly where else can it be...
        // Based on the design pattern, the receiver should not hold the stack as it should only handle logic
        // Then if the client want to pass the stack into the Invoker (sender)'s method then the stack I think have to be here...
//        Stack<Command> history = new Stack<>();  // Create the stack object in memory

        // Concrete Commands
        Command listCommand = new ListCommand(receiver);
        Command undoCommand = new UndoCommand(receiver);
        Command addCommand1 = new AddCommand(receiver, "Ben", "Ng", "...@gmail.com");
        Command addCommand2 = new AddCommand(receiver, "Benben", "NgNg", "ben@gmail.com");
        Command deleteCommand1 = new DeleteCommand(receiver, 10);
        Command updateCommand1 = new UpdateCommand(receiver, 1, "test", "testing", "...@gmail.com");

        // Command List
        Command[] commands = new Command[]{
//                addCommand1,
                undoCommand,
//                listCommand,
//                addCommand2,
//                listCommand,
//                deleteCommand1,
//                listCommand,
//                updateCommand1,
        };

        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(commands);

        invoker.executeCommand(history);
        // !!!!!!!!!!!!!!! We have to Organize our java files into appropriate folders, as per practicum requirements.
        // !!!!!!!!!!!!!!! Need to do javadocs


        System.out.println("----------- Stack Info ------------");
        System.out.println("Stack Size Left: " + history.size());

        for (int i = 0; i < history.size(); i++) {
            System.out.println(history.get(i));
        }

        receiver.storeToFile();
    }
}
