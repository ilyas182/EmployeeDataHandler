import java.util.Stack;

public class Client {
    public static void main(String[] args) {

        // Create receiver as client needs to pass receiver instance to the command constructors (as per practicum instructions)
        Receiver receiver = new Receiver();

        // Assume that the stack is here because honestly where else can it be...
        // Based on the design pattern, the receiver should not hold the stack as it should only handle logic
        // Then if the client want to pass the stack into the Invoker (sender)'s method then the stack I think have to be here...
        Stack<Command> history = new Stack<>();  // Create the stack object in memory

        // Concrete Commands
        Command addCommand1 = new AddCommand(receiver, "ilyas", "effendi", "ilyas2104@gmail.com");
        Command addCommand2 = new AddCommand(receiver, "ben", "ng", "ben@yahoo.com");
        Command updateCommand3 = new UpdateCommand(receiver, 2, "supersuper");
        Command addCommand3 = new AddCommand(receiver, "hungryben", "benhungry", "benhungryt@gmail.com");
        Command listCommand1 = new ListCommand(receiver);
        Command updateCommand1 = new UpdateCommand(receiver, 1, "replacement1");
        Command listCommand2 = new ListCommand(receiver);
        Command updateCommand2 = new UpdateCommand(receiver, 3, "replacement2", "Harrorooo");
        Command listCommand3 = new ListCommand(receiver);
        Command deleteCommand1 = new DeleteCommand(receiver, 3);
        Command listCommand4 = new ListCommand(receiver);


        // Command List
        Command[] commands = new Command[]{
                addCommand1,
                addCommand2,
                updateCommand3,
                updateCommand3,
                addCommand3,
                listCommand1,
                updateCommand1,
                listCommand2,
                updateCommand2,
                listCommand3,
                deleteCommand1,
                listCommand4
        };

        System.out.println(history.toString());
        Invoker invoker = new Invoker();
//        invoker.setCommandsForExecution(history.toArray(new Command[0]));
        invoker.setCommandsForExecution(commands);

        invoker.executeCommand(history);
        // !!!!!!!!!!!!!!! We have to Organize our java files into appropriate folders, as per practicum requirements.
        // !!!!!!!!!!!!!!! Need to do javadocs

//        for (Command command : history) {
//            System.out.println("Printing history testing");
//            System.out.println(command);
//        }

        System.out.println(history.size());

        for (int i = 0; i < history.size(); i++) {
            System.out.println(history.get(i));
        }

    }
}
