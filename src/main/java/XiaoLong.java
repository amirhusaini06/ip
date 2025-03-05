import java.util.ArrayList;

/**
 * A chatbot program that tracks different types of tasks.
 */

public class XiaoLong {
    private static final String FILE_PATH = "./data/xiaolong.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a XiaoLong chatbot instance and loads tasks from storage.
     */
    public XiaoLong(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (XiaoLongException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser();
    }

    /**
     * Starts the XiaoLong chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (XiaoLongException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new XiaoLong(FILE_PATH).run();
    }
}
