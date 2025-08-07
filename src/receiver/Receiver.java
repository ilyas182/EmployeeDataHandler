package receiver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * The Receiver class contains some business logic. Almost any object may act as a receiver.
 * Most commands only handle the details of how a request is passed to the receiver,
 * while the receiver itself does the actual work.
 * Concrete command classes will call the receiver methods with filtered legal parameters.
 */
public class Receiver {

    private List<String[]> dataStore = new ArrayList<>();

    public Receiver() {
        loadFromFile();
    }

    /**
     * get data store arraylist
     * @return dataStore an ArrayList of String Lists that stores the data.
     */
    public List<String[]> getDataStore() {
        return dataStore;
    }

    /**
     * addCommand method for adding an entry to data store.
     * @param firstName first name to be added.
     * @param lastName last name to be added.
     * @param email email to be added.
     */
    public void add(String firstName, String lastName, String email) {
        dataStore.add(new String[]{firstName, lastName, email});
    }

    /**
     * add to index method for adding an entry to a particular index
     * @param index
     * @param firstName
     * @param lastName
     * @param email
     */
    public void addAtIndex(Integer index, String firstName, String lastName, String email) {
        dataStore.add(index - 1, new String[]{firstName, lastName, email});
    }

    /**
     * updateCommand for updating an existing data entry.
     * @param index index to be updated.
     * @param firstName first name to be updated.
     */
    public void update(Integer index, String firstName) {
        dataStore.get(index - 1)[0] = firstName;
    }

    /**
     * updateCommand for updating an existing data entry.
     * @param index index to be updated.
     * @param firstName first name to be updated.
     * @param lastName last name to be udpated.
     */
    public void update(Integer index, String firstName, String lastName) {
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
    public void update(Integer index, String firstName, String lastName, String email) {
        dataStore.get(index - 1)[0] = firstName;
        dataStore.get(index - 1)[1] = lastName;
        dataStore.get(index - 1)[2] = email;
    }

    /**
     * deleteCommand for deleting an existing data entry at particular index.
     * @param index index to be deleted.
     */
    public void delete(Integer index) {
        dataStore.remove(index - 1);
    }

    /**
     * listCommand for listing out all entries in the data store.
     */
    public void list() {
        System.out.println("List");
        for (int i = 0; i < dataStore.size(); i++) {
            System.out.printf("%02d. %s %s %s\n", i+1, dataStore.get(i)[0], dataStore.get(i)[1], dataStore.get(i)[2]);
        }
    }


    /**
     * This method stores entries in Data Store into the dataStore.txt located in the src folder.
     * This method will either create a file if non exists or append to existing file.
     */
    public void storeToFile() {
        try (BufferedWriter buff_writer = Files.newBufferedWriter(Path.of("./src/dataStore.txt"), StandardOpenOption.CREATE)) {
            // writing text to the buffer
            for (int i = 0; i < dataStore.size(); i++) {
                String[] row = dataStore.get(i);
                buff_writer.write(String.format("%s %s %s\n", row[0], row[1], row[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to load data from existing file if it exists
     */
    public void loadFromFile() {
        Path path = Path.of("./src/dataStore.txt");
        if (!Files.exists(path)) {
            return;
        }
        else {
            try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
//                    System.out.println(line); // Process each line
                    dataStore.add(line.split(" "));
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }
    }

}
