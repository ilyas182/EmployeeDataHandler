import java.util.ArrayList;
import java.util.List;

public class Receiver {

    private List<String[]> dataStore = new ArrayList<>();

    public List<String[]> getDataStore() {
        return dataStore;
    }

    public void addCommand(String firstName, String lastName, String email) {
        dataStore.add(new String[]{firstName, lastName, email});
        System.out.println(dataStore.size() + " commands have been added to data store");
        dataStore.toString();
    }

    public void updateCommand(Integer index, String firstName) {
        dataStore.get(index - 1)[0] = firstName;
    }

    public void updateCommand(Integer index, String firstName, String lastName) {


        dataStore.get(index - 1)[0] = firstName;
        dataStore.get(index - 1)[1] = lastName;
    }

    public void updateCommand(Integer index, String firstName, String lastName, String email) {
        dataStore.get(index - 1)[0] = firstName;
        dataStore.get(index - 1)[1] = lastName;
        dataStore.get(index - 1)[2] = email;
    }

    public void deleteCommand(Integer index) {
        System.out.println("Delete");
        dataStore.remove(index - 1);
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

    }

    // Put this method here since it has to be in the class that handles the data store
    public void storeToFile() {

    }

}
