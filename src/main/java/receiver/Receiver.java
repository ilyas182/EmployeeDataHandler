package receiver;

import command.concretecommands.AddCommand;
import command.Command;
import command.concretecommands.DeleteCommand;
import command.concretecommands.UpdateCommand;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The Receiver class contains some business logic. Almost any object may act as a receiver.
 * Most commands only handle the details of how a request is passed to the receiver,
 * while the receiver itself does the actual work.
 * Concrete command classes will call the receiver methods with filtered legal parameters.
 */
public class Receiver {
    protected static final Stack<Command> history = new Stack<>();
    private List<String[]> dataStore = new ArrayList<>();

    /**
     * get data store arraylist
     * @return dataStore an ArrayList of String Lists that stores the data.
     */
    public List<String[]> getDataStore() {
        return dataStore;
    }

    /**
     * get history stack
     * @return history a stack of Command objects (add, update, delete)
     */
    public Stack<Command> getHistory() {
        return history;
    }

    /**
     * addCommand method for adding an entry to data store.
     * @param firstName first name to be added.
     * @param lastName last name to be added.
     * @param email email to be added.
     */
    public void addCommand(String firstName, String lastName, String email) {
        System.out.println("Add");
        dataStore.add(new String[]{firstName, lastName, email});
    }

    /**
     * updateCommand for updating an existing data entry.
     * @param index index to be updated.
     * @param firstName first name to be updated.
     */
    public void updateCommand(Integer index, String firstName) {
        System.out.println("Update");
        dataStore.get(index - 1)[0] = firstName;
    }

    /**
     * updateCommand for updating an existing data entry.
     * @param index index to be updated.
     * @param firstName first name to be updated.
     * @param lastName last name to be udpated.
     */
    public void updateCommand(Integer index, String firstName, String lastName) {
        System.out.println("Update");
        dataStore.get(index - 1)[0] = firstName;
        dataStore.get(index - 1)[1] = lastName;
    }

    /**
     * updateCommand for updating an existing data entry.
     * @param index index to be updated.
     * @param firstName first name to be updated.
     * @param lastName last name to be updated.
     * @param email email to be updated.
     */
    public void updateCommand(Integer index, String firstName, String lastName, String email) {
        System.out.println("Update");
        dataStore.get(index - 1)[0] = firstName;
        dataStore.get(index - 1)[1] = lastName;
        dataStore.get(index - 1)[2] = email;
    }

    /**
     * deleteCommand for deleting an existing data entry at particular index.
     * @param index index to be deleted.
     */
    public void deleteCommand(Integer index) {
        dataStore.remove(index - 1);
        System.out.println("Delete");
    }

    /**
     * listCommand for listing out all entries in the data store.
     */
    public void listCommand() {
        System.out.println("List");
        for (int i = 0; i < dataStore.size(); i++) {
            System.out.printf("%02d. %s %s %s\n", i+1, dataStore.get(i)[0], dataStore.get(i)[1], dataStore.get(i)[2]);
        }
    }

    /**
     * undoCommand for undoing an action.
     * This method is called by the respective Concrete Command instances.
     */
    public void undoCommand() {
        if (history.size() <= 0) {
            System.out.println("Nothing to undo");
            return;
        }

        Command lastCommand = history.pop();
        System.out.println("Undo");

        if (lastCommand.isUndoable().equals("add")) {
            this.deleteCommand(dataStore.size());
        } else if (lastCommand.isUndoable().equals("update")) {
            String[] oldValues = ((UpdateCommand) lastCommand).getOldValues();
            Integer index = ((UpdateCommand) lastCommand).getIndex();
            this.updateCommand(index, oldValues[0], oldValues[1], oldValues[2]);
        } else { // Undo command.concretecommands.DeleteCommand
            String[] oldValues = ((DeleteCommand) lastCommand).getOldValues();
            Integer index = ((DeleteCommand) lastCommand).getIndex();
            this.addCommand(oldValues[0], oldValues[1], oldValues[2]);
        }
    }


    /**
     * This method stores entries in Data Store into the dataStore.txt located in the src folder.
     * This method will either create a file if non exists or append to existing file.
     */
    public void storeToFile() {
        try (BufferedWriter buff_writer = Files.newBufferedWriter(Path.of("./src/dataStore.txt"), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            // writing text to the buffer
            for (int i = 0; i < dataStore.size(); i++) {
                String[] row = dataStore.get(i);
                buff_writer.write(String.format("%02d. %s %s %s\n", i+1, row[0], row[1], row[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
