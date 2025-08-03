import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Receiver {

    private List<String[]> dataStore = new ArrayList<>();

    public List<String[]> getDataStore() {
        return dataStore;
    }

    public void addCommand(String firstName, String lastName, String email) {
        System.out.println("Add");
        dataStore.add(new String[]{firstName, lastName, email});
//        System.out.println(dataStore.size() + " commands have been added to data store");
//        dataStore.toString();
//        System.out.printf("%s added successfully\n", firstName + " " + lastName + " " + email);
    }

    public void updateCommand(Integer index, String firstName) {
        System.out.println("Update");
        dataStore.get(index - 1)[0] = firstName;
    }

    public void updateCommand(Integer index, String firstName, String lastName) {
        System.out.println("Update");
        dataStore.get(index - 1)[0] = firstName;
        dataStore.get(index - 1)[1] = lastName;
    }

    public void updateCommand(Integer index, String firstName, String lastName, String email) {
        System.out.println("Update");
        dataStore.get(index - 1)[0] = firstName;
        dataStore.get(index - 1)[1] = lastName;
        dataStore.get(index - 1)[2] = email;
    }

    public void deleteCommand(Integer index) {
        dataStore.remove(index - 1);
        System.out.println("Delete");
    }

    public void listCommand() {
        System.out.println("List");
        for (int i = 0; i < dataStore.size(); i++) {
            System.out.printf("%02d. %s %s %s\n", i+1, dataStore.get(i)[0], dataStore.get(i)[1], dataStore.get(i)[2]);
//            System.out.printf("%02d. %s\n", i+1, Arrays.toString(dataStore.get(i)));
        }
    }

    public void undoCommand() {
        // Undo Add: Delete dataStore size() - 1
        // Undo Update:

        // Step 1: peek at stack
        // if add is at top: undo add
        // if update is at top: check datastore for

        if (Client.history.size() <= 0) {
            System.out.println("Nothing to undo");
            return;
        }

        Command lastCommand = Client.history.pop();
        System.out.println("Undo");

        if (lastCommand instanceof AddCommand) {
            this.deleteCommand(dataStore.size());
        } else if (lastCommand instanceof UpdateCommand) {
            String[] oldValues = ((UpdateCommand) lastCommand).getOldValues();
            Integer index = ((UpdateCommand) lastCommand).getIndex();
            this.updateCommand(index, oldValues[0], oldValues[1], oldValues[2]);
        } else { // Undo DeleteCommand
            String[] oldValues = ((DeleteCommand) lastCommand).getOldValues();
            Integer index = ((DeleteCommand) lastCommand).getIndex();
            this.addCommand(oldValues[0], oldValues[1], oldValues[2]);
        }
    }

    // Put this method here since it has to be in the class that handles the data store
    public void storeToFile() {
        try (BufferedWriter buff_writer = Files.newBufferedWriter(Path.of("./dataStore.txt"))) {
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
