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
        Command addCommand1 = new AddCommand("");

        // !!!!!!!!!!!!!!! We have to Organize our java files into appropriate folders, as per practicum requirements.
        // !!!!!!!!!!!!!!! Need to do javadocs

    }
}
