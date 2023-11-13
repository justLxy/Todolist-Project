package view;

import entity.Project;
import interface_adapter.add_project.AddProjectController;
import interface_adapter.add_project.AddProjectState;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.added_project.AddedProjectState;
import interface_adapter.added_project.AddedProjectViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;


public class AddProjectView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Add Project";
    private JList<String> projectList;
    private DefaultListModel<String> listModel;
    private final AddProjectViewModel addProjectViewModel;
    private final JTextField projectnameInputField = new JTextField(15);
    private final AddProjectController addProjectController;
    private final JButton addProject;
    private final JButton getProject;
    private final AddedProjectViewModel addedProjectViewModel;

    public AddProjectView(AddProjectViewModel addProjectViewModel, AddProjectController addProjectController, AddedProjectViewModel addedProjectViewModel) {
        this.addProjectViewModel = addProjectViewModel;
        this.addProjectController = addProjectController;
        this.addedProjectViewModel = addedProjectViewModel;
        addProjectViewModel.addPropertyChangeListener(this);
        addedProjectViewModel.addPropertyChangeListener(this);

        this.listModel = new DefaultListModel<>();
        this.projectList = new JList<>(listModel);

        JLabel title = new JLabel(AddProjectViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(Color.darkGray);

        LabelTextPanel projectnameInfo = new LabelTextPanel(
                new JLabel(AddProjectViewModel.PROJECT_NAME_LABEL), projectnameInputField);

        JPanel inputPanel = new JPanel();
        Font font = new Font("SansSerif", Font.PLAIN,15);
        addProject = new JButton(AddProjectViewModel.ADD_PROJECT_BUTTON_LABEL);
        addProject.setFont(font);

        inputPanel.add(projectnameInfo);
        inputPanel.add(addProject, BorderLayout.AFTER_LINE_ENDS);

        JPanel button = new JPanel();
        getProject = new JButton(AddProjectViewModel.GET_PROJECT_BUTTON_LABEL);
        getProject.setFont(font);
        button.add(getProject);

        getProject.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getProject)) {
                        }
                    }
                }
        );

        addProject.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addProject)) {
                            String projectName = projectnameInputField.getText();
                            if (!projectName.isEmpty()) {
                                try {
                                    AddProjectState currentState = addProjectViewModel.getState();
                                    addProjectController.execute(
                                            currentState.getProject_name()
                                    );
                                    projectnameInputField.setText("");

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
//                            JOptionPane.showMessageDialog(parent, currentState.getProject_name() +"successfully created");
                        }

                    }
                }
        );
        projectnameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        AddProjectState currentState = addProjectViewModel.getState();
                        String text = projectnameInputField.getText() + e.getKeyChar();
                        currentState.setProject_name(text);
                        addProjectViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );



        JScrollPane scrollPane = new JScrollPane(projectList);
        scrollPane.setPreferredSize(new Dimension(80,100));

        this.setPreferredSize(new Dimension(850, 300));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(inputPanel);
        this.setBackground(Color.ORANGE);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(button);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object state = evt.getNewValue();
        if (state instanceof AddProjectState) {
            AddProjectState addProjectState = (AddProjectState) state;
            if (addProjectState.getProject_nameError() != null) {
                JOptionPane.showMessageDialog(this, addProjectState.getProject_nameError());
        }
        } else if (state instanceof AddedProjectState) {
            AddedProjectState addedProjectState = (AddedProjectState) state;
            String projectNameWithCount = addedProjectState.getProjectname() + " (0)";
            listModel.addElement(projectNameWithCount);
            JOptionPane.showMessageDialog(this, addedProjectState.getProjectname() + " successfully created");
        }
    }
}
