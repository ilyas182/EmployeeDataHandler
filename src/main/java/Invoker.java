import java.util.Stack;

public class Invoker {
    // Hold commands list
    private Command[] cmdToExecute;

    // Client going pass in command list... store into the instance var cmdToExecute..l.
    public void setCommandsForExecution(Command[] commands) {
        // Maybe must do exception handling try catch here? if the lsit empty

        this.cmdToExecute = commands;
//        for (int i = 0; i < cmdToExecute.length; i++) {
//            System.out.println(cmdToExecute[i]);
//        }
    }

    // Execute the command from the var then just lo g them to history
    // When stack pass in, we pass in the reference of the Stack object so no need return the stack object can just use!
    public void executeCommand(Stack<Command> history){

        // Maybe got to do exception handling try catch here? if the list empty.
        // Stack have few methods - push, pop, peek, empty, search
        // Maybe can use peek before pop for undo??
        for (Command command : cmdToExecute) {

            try {
                command.execute();

                if (command.toString() != null) {
                    // Stack should only hold Add Update Delete commands no need to add List command cuz it does not support undo
                    if (command instanceof AddCommand || command instanceof UpdateCommand || command instanceof DeleteCommand) {
                        history.push(command);
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception Caught at Invoker");
            }
        }
    }
}
