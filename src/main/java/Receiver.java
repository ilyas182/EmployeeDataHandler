import java.util.ArrayList;
import java.util.List;

public class Receiver {

    private List<String[]> dataStore = new ArrayList<>();

    public void addCommand(String firstName, String lastName, String email) {
        dataStore.add(new String[]{firstName, lastName, email});
        System.out.println(dataStore.size() + " commands have been added to data store");
        dataStore.toString();
    }

    public void updateCommand(Integer index, String firstName, String lastName, String email) {

    }

    public void deleteCommand() {

    }

    public void listCommand() {

    }

    public void undoCommand() {

    }

    // Put this method here since it has to be in the class that handles the data store
    public void storeToFile() {

    }

}
