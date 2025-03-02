import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private String filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath Path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return ArrayList of Task objects.
     * @throws XiaoLongException If there's an error loading the file.
     */
    public ArrayList<Task> load() throws XiaoLongException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = Task.fromFileFormat(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            fileScanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new XiaoLongException("Error loading tasks from file: " + e.getMessage());
        }
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks ArrayList of Task objects to save.
     * @throws XiaoLongException If there's an error saving to the file.
     */
    public void save(ArrayList<Task> tasks) throws XiaoLongException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Ensure the directory exists

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new XiaoLongException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
