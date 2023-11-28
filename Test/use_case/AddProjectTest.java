package use_case;

import api.TodoistDB;
import org.junit.Test;
import use_case.add_project.*;
import use_case.delete_project.DeleteProjectInputBoundary;
import use_case.delete_project.DeleteProjectInputData;
import use_case.delete_project.DeleteProjectInteractor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class AddProjectTest {
        static String message = "";
        static boolean popUpDiscovered = false;

        @Test
        public void successTest() {
            AddProjectDataAccessInterface dataAccessObject = new TodoistDB();
            AddProjectOutputBoundary successPresenter = new AddProjectOutputBoundary() {
                @Override
                public void prepareSuccessView(AddProjectOutputData response) {
                    assertEquals("207", response.getName());
                }
                @Override
                public void prepareFailView(String error) {
                    fail("Use case failure is unexpected.");
                }
            };
            AddProjectInputData inputData = new AddProjectInputData("207");
            AddProjectInputBoundary interactor = new AddProjectInteractor(successPresenter,dataAccessObject);
            interactor.execute(inputData);
            DeleteProjectInputData deleteProject = new DeleteProjectInputData("207");

        }

    }

//    public void addTwoUsers() {
//        ProjectFactory pf = new ProjectFactory();
//        TodoistDB api = new TodoistDB();
//        try {
//            fudao = new FileUserDataAccessObject("./users.csv", uf);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        fudao.save(uf.create("user1", "password1", LocalDateTime.now()));
//        fudao.save(uf.create("user2", "password2", LocalDateTime.now()));


//    public JButton getButton() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app); // found the window?
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        SignupView sv = (SignupView) jp2.getComponent(0);
//
//        JPanel buttons = (JPanel) sv.getComponent(4);
//
//        return (JButton) buttons.getComponent(2); // this should be the clear button
//    }
//
//    /**
//     *
//     * Test that the Clear button is present and where it is expected to be
//     */
//    @org.junit.Test
//    public void testAddProjectPresent() {
//        Main.main(null);
//        JButton button = getButton();
//        assert(button.getText().equals("Add Project"));
//    }
//
//    /**
//     *
//     * Test that pressing the Clear button deletes all users. This test first
//     * adds two users to the CSV file, then starts the program, then clicks the Clear button,
//     * and then checks that the file's length has decreased.
//     */
//    @org.junit.Test
//    public void testClearUsersDeletedUsersFromFile() {
//
//        addTwoUsers();
//        Main.main(null);
//        JButton button = getButton();
//
//        // since clicking the button should end up displaying a JDialog to the user to report the
//        // result, we set a timer, which will execute code necessary to complete the testing.
//        createCloseTimer().start();
//
//        button.doClick();
//
//        // will continue execution here after the JDialog is closed
//
//        // users.csv format
//        //username, password,timestamp (in format returned by a call like LocalDateTime.now())
//        //example
//        //user1,pass1,2023-10-12T14:46:26.733518
//
//        //check the users.csv file to ensure the users are gone
//        try {
//            int lines = countLines();
//            System.out.println("lines left = " + lines);
//            assert(lines <= 1);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//    /**
//     *
//     * This test is the same as above, but it additionally checks that the JDialog contains the names of
//     * all users deleted from the file.
//     */
//    @org.junit.Test
//    public void testClearUsersPopUpShown() {
//
//        addTwoUsers();
//        popUpDiscovered = false;
//
//        Main.main(null);
//        JFrame app = null;
//
//        JButton button = getButton();
//
//
//        // since clicking the button should end up displaying a JDialog to the user to report the
//        // result, we set a timer, which will execute code necessary to complete the testing.
//        createCloseTimer().start();
//
//        //click the button
//        button.doClick();
//
//        // will continue execution here after the JDialog is closed
//
//        // confirm a popUp was discovered
//        assert(popUpDiscovered);
//        System.out.println("popup was detected successfully.");
//
//    }
//
//    @org.junit.Test
//    public void testClearUsersReturnedUsersDeleted() throws InterruptedException {
//
//        addTwoUsers();
//        message = "";
//
//        Main.main(null);
//
//        JButton button = getButton();
//
//        // since clicking the button should end up displaying a JDialog to the user to report the
//        // result, we set a timer, which will execute code necessary to complete the testing.
//        createCloseTimer().start();
//
//        //click the button
//        button.doClick();
//
//        // will continue execution here after the JDialog is closed
//
//        // check the message
//        assert(message.contains("user1") && message.contains("user2"));
//    }
//
//    private Timer createCloseTimer() {
//        ActionListener close = new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                Window[] windows = Window.getWindows();
//                for (Window window : windows) {
//
//                    if (window instanceof JDialog) {
//
//                        JDialog dialog = (JDialog)window;
//
//                        // this ignores old dialogs
//                        if (dialog.isVisible()) {
//                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
//                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
//                            System.out.println("message = " + s);
//
//                            // store the information we got from the JDialog
//                            ClearUsersTest.message = s;
//                            ClearUsersTest.popUpDiscovered = true;
//
//                            System.out.println("disposing of..." + window.getClass());
//                            window.dispose();
//                        }
//                    }
//                }
//            }
//
//        };
//
//        Timer t = new Timer(1000, close);
//        t.setRepeats(false);
//        return t;
//    }
//
//    private static int countLines() throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader("users.csv"));
//        int lineCount = 0;
//        while (reader.readLine() != null) {
//            lineCount++;
//        }
//        return lineCount;
//    }
//
//}
//
//}


