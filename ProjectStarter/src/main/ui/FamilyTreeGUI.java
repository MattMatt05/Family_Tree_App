package ui;

import javax.swing.*;

import model.FamilyTree;
import model.FamilyMember;
import model.EventLog;
import model.Event;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// This class is used to display a GUI for the Family Tree
public class FamilyTreeGUI {
    private final int textSize = 18;
    private static final String JSON_STORE = "./data/familyTree.json";
    private Image iconImage;
    private JFrame window;
    private Toolkit toolkit;
    private JPanel panel;
    private JPanel displayPanel;

    private Button loadButton;
    private Button saveButton;
    private Button addButton;
    private Button viewButton;

    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JTextField birthYearInput;
    private JCheckBox aliveStatus;
    private JTextField deathYearInput;
    private JTextArea descriptionInput;

    private JPanel personTable;
    private JPanel optionsPanel;
    private JPanel addMemberPanel;
    private JPanel familyTreePanel;
    private JPanel personPanel;
    private JPanel treeCalculationPanel;

    private GridBagConstraints verticalStack;
    private GridBagConstraints personPanelStack;

    private FamilyTree familyTree;
    private FamilyMember familyMember;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private ImageIcon imageIcon;
    private JLabel image;

    // EFFECTS: Instantiates variables, and makes appropriate method calls for
    // functionality
    public FamilyTreeGUI() {
        // Setting up any variables
        window = new JFrame();
        toolkit = Toolkit.getDefaultToolkit();
        iconImage = toolkit.getImage("graphics/icon.png");
        panel = new JPanel();
        displayPanel = new JPanel();
        optionsPanel = new JPanel();
        addMemberPanel = new JPanel();
        familyTree = new FamilyTree("Family Tree");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        image = new JLabel();
        imageIcon = new ImageIcon("graphics/icon.png");

        setupWindow();
        setupDisplayPanel();
        setupVerticalStack();
        setPersonPanelStack();

        panel.setBackground(Color.decode("#06bcc1"));

        optionsPanel();
        setUpBorders();
        windowClosedLogging();
    }

    // MODIFIES: window
    // EFFECTS: Added colours to window border
    private void setUpBorders() {
        JPanel temp1 = new JPanel();
        temp1.setBackground(Color.decode("#06bcc1"));
        window.add(temp1, BorderLayout.EAST);
        JPanel temp2 = new JPanel();
        temp2.setBackground(Color.decode("#06bcc1"));
        window.add(temp2, BorderLayout.SOUTH);
    }

    // MODIFIES: window
    // EFFECTS: Sets up the basic for the window
    private void setupWindow() {
        window.setTitle("Family Tree Maker");
        window.setSize(800, 500);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setIconImage(iconImage);
        window.add(panel, BorderLayout.NORTH);
        window.setVisible(true);
    }

    // MODIFIES: window
    // EFFECTS: conducts setup for the display Panel
    private void setupDisplayPanel() {
        JPanel titlePanel = new JPanel();

        verticalStack = new GridBagConstraints();
        verticalStack.gridx = 0;
        verticalStack.anchor = GridBagConstraints.CENTER;
        verticalStack.ipady = 10;

        titlePanel.setLayout(new GridBagLayout());

        JLabel addMemberTitle = new JLabel("Family Tree Maker");
        addMemberTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        titlePanel.add(addMemberTitle, verticalStack);
        addMemberTitle = new JLabel("Welcome!");
        addMemberTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        titlePanel.add(addMemberTitle, verticalStack);
        titlePanel.add(new JPanel().add(new JLabel("Would you like to load data? ")), verticalStack);
        createNavButton(titlePanel, verticalStack, loadButton, "Load Data");

        image.setIcon(imageIcon);
        titlePanel.add(image, verticalStack);

        displayPanel.add(titlePanel);
        window.add(displayPanel);
        verticalStack = new GridBagConstraints();
    }

    // MODIFIES: verticalStack
    // EFFECTS: Setup for vertical stacking in a grid bag
    private void setupVerticalStack() {
        verticalStack = new GridBagConstraints();
        verticalStack.gridx = 0;
        verticalStack.anchor = GridBagConstraints.CENTER;
        verticalStack.insets = new Insets(10, 10, 10, 10);
    }

    // MODIFIES: window
    // EFFECTS: Added options panel to the left side of the window
    private void optionsPanel() {
        JLabel title = new JLabel("Options", SwingConstants.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        setPanelBackground(optionsPanel);

        JPanel optionsGrid = new JPanel();
        optionsGrid.setLayout(new GridLayout(6, 1, 0, 10));
        setPanelBackground(optionsGrid);
        optionsGrid.add(title);
        createNavButton(optionsGrid, null, viewButton, "View Tree");
        createNavButton(optionsGrid, null, addButton, "Add Member");
        createNavButton(optionsGrid, null, loadButton, "Load Data");
        createNavButton(optionsGrid, null, saveButton, "Save Data");

        optionsPanel.add(optionsGrid);

        window.add(optionsPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: Standardizes colour setting of panels
    private void setPanelBackground(JPanel panel) {
        panel.setBackground(Color.decode("#06bcc1"));
    }

    // MODIFIES: familyTree
    // EFFECTS: Loads data to the tree
    private void loadData() {
        try {
            familyTree = jsonReader.read();
            popupPanel("Data Loaded Successfully!");
            displayPanel.removeAll();
            viewTree();
            displayPanel.revalidate();
            displayPanel.repaint();
        } catch (IOException e) {
            System.out.println("\nUnable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: creates a popup window with a customized message
    private void popupPanel(String message) {
        JFrame popup = new JFrame();
        JPanel panel = new JPanel(new GridLayout(2, 1, 20, 20));
        JLabel loadMessage = new JLabel(message, SwingConstants.CENTER);
        Button okayButton = new Button("Okay");
        loadMessage.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        loadMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        popup.setIconImage(iconImage);
        popup.setVisible(true);
        popup.setTitle("Family Tree Maker");
        panel.setBackground(Color.decode("#06bcc1"));
        panel.add(loadMessage);
        panel.add(okayButton);
        popup.add(panel, BorderLayout.CENTER);
        popup.pack();
        popup.setLocationRelativeTo(null);
        okayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popup.dispose();
            }
        });
    }

    // MODIFIES: familyTree
    // EFFECTS: Saves data to the tree
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(familyTree);
            jsonWriter.close();
            popupPanel("Data Saved Successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: displayPanel
    // EFFECTS: refreshes view tree
    private void viewTree() {
        displayPanel.removeAll();
        viewTreePanel();
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    // EFFECTS: produces popup with oldest family member
    private void oldestMemberPopup() {
        Button oldestMemberButton = new Button("Oldest Member");

        oldestMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (familyTree.getFamilyTree().size() != 0) {
                    popupPanel("The oldest family member is "
                            + familyTree.oldestFamilyMember().getAge());
                }
            }
        });
        treeCalculationPanel.add(oldestMemberButton, verticalStack);
    }

    // EFFECTS: produces popup with the mean family lifespan
    private void lifespanPopup() {
        Button lifespanButton = new Button("Lifespan");

        lifespanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (familyTree.getFamilyTree().size() != 0) {
                    popupPanel("The mean lifespan is: " + familyTree.meanAgeAmongDeceased());
                }
            }
        });
        treeCalculationPanel.add(lifespanButton, verticalStack);
    }

    // MODIFIES: displayPanel
    // EFFECTS: updates display panel to display the family tree
    private void viewTreePanel() {
        familyTreePanel = new JPanel();
        familyTreePanel.setLayout(new GridBagLayout());
        familyTreePanel.add(pageTitle("Your Family Tree"), personPanelStack);
        treeCalculationPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        oldestMemberPopup();
        lifespanPopup();
        familyTreePanel.add(treeCalculationPanel, personPanelStack);
        addTreeBar(familyTreePanel);

        personTable = new JPanel(new GridBagLayout());

        for (FamilyMember familyMember : familyTree.getFamilyTree()) {
            formatPersonPanel(familyMember);
        }

        JScrollPane personScrollPane = new JScrollPane(personTable);

        personScrollPane.setPreferredSize(new Dimension(680, 322));

        familyTreePanel.add(personScrollPane, personPanelStack);
        displayPanel.add(familyTreePanel, verticalStack);
    }

    // EFFECTS: adds a tree bar to the panel that serves as a guide to the values in
    // the table
    private void addTreeBar(JPanel panel) {
        JPanel treeBar = new JPanel();
        treeBar.setLayout(new GridLayout(1, 5, 40, 0));
        treeBar.setPreferredSize(new Dimension(660, 50));
        addText("Remove", treeBar, 16);
        addText("Name", treeBar, 16);
        addText("Birth Year", treeBar, 16);
        addText("Age", treeBar, 16);
        addText("Description", treeBar, 16);
        panel.add(treeBar, personPanelStack);
    }

    // MODIFIES: personPanel
    // EFFECTS: Creates remove Button for person panel
    private void addRemoveButton(FamilyMember familyMember) {
        Button removeButton = new Button("Remove");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                displayPanel.removeAll();
                familyTree.removeFamilyMember(familyMember);
                viewTreePanel();
                displayPanel.revalidate();
                displayPanel.repaint();
            }
        });
        personPanel.add(removeButton, verticalStack);
    }

    // EFFECTS: Generates a colour background for name depending on alive status and
    // adds it to personPanel
    private void nameColour(FamilyMember familyMember) {
        JLabel name = new JLabel(familyMember.getFirstName() + " " + familyMember.getLastName(), SwingConstants.CENTER);
        name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        name.setOpaque(true);
        if (familyMember.isAlive()) {
            name.setBackground(Color.decode("#C0E38C"));
        } else {
            name.setBackground(Color.decode("#E38D97"));
        }
        personPanel.add(name);
    }

    // MODIFIES: personPanel
    // EFFECTS: formats and styles the person panel for family members.
    private void formatPersonPanel(FamilyMember familyMember) {
        personPanel = new JPanel();
        personPanel.setLayout(new GridLayout(1, 5, 40, 0));
        addRemoveButton(familyMember);
        nameColour(familyMember);

        addText(String.valueOf(familyMember.getBirthYear()), personPanel, 12);
        addText(String.valueOf(familyMember.getAge()), personPanel, 12);
        JTextArea description = new JTextArea(String.valueOf(familyMember.getDescription()));
        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        JScrollPane descriptionWrap = new JScrollPane(description);
        descriptionWrap.getViewport().setOpaque(false);
        descriptionWrap.setOpaque(false);
        personPanel.setPreferredSize(new Dimension(660, 50));
        personPanel.add(descriptionWrap);

        personPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        personTable.add(personPanel, personPanelStack);
    }

    // EFFECTS: sets up grid bag constraints for person panel
    public void setPersonPanelStack() {
        personPanelStack = new GridBagConstraints();
        personPanelStack.gridx = 0;
        personPanelStack.anchor = GridBagConstraints.NORTH;
    }

    // MODIFIES: panel
    // EFFECTS: Creates a button for one of the four give nagivation buttons: View,
    // Add, Load, and Save.
    private Button createNavButton(JPanel panel, GridBagConstraints constraints, Button button, String action) {
        button = new Button(action);
        panel.add(button, constraints);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (action == "View Tree") {
                    viewTree();
                } else if (action == "Add Member") {
                    addMemberPanel();
                } else if (action == "Load Data") {
                    loadData();
                } else if (action == "Save Data") {
                    saveData();
                }
            }
        });
        return button;
    }

    // MODIFIES: displayPanel
    // EFFECTS: displays the add member panel on the display Panel
    private void addMemberPanel() {
        displayPanel.removeAll();

        addMemberForm();
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    // MODIFIES: addMemberPanel
    // EFFECTS: creates a panel/contained related add member panels
    private void addMemberForm() {
        addMemberPanel.removeAll();

        addMemberPanel.setLayout(new GridBagLayout());
        addMemberPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        addMemberPanel.add(pageTitle("Add Member"), verticalStack);
        addMemberPanel.add(addMemberFieldsAndInput(), verticalStack);
        descriptionPanel(addMemberPanel);
        addMemberSubmitButton(addMemberPanel);
        addMemberPanel.revalidate();
        addMemberPanel.repaint();

        displayPanel.add(addMemberPanel);
    }

    // MODIFIES: this
    // EFFECTS: Generates a panel with given title. returns the JPanel with that
    // title
    private JPanel pageTitle(String title) {
        JPanel titlePanel = new JPanel();

        JLabel addMemberTitle = new JLabel(title);
        addMemberTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        titlePanel.add(addMemberTitle);

        return titlePanel;
    }

    // MODIFIES: addMemberPanel
    // EFFECTS: serves as a panel for the fields and input related to adding new
    // members, returns a JPanel
    private JPanel addMemberFieldsAndInput() {
        JPanel addMemberPanel = new JPanel();
        addMemberPanel.removeAll();
        addMemberPanel.setLayout(new GridLayout(5, 2, 10, 15));

        firstNameInput = new JTextField(20);
        lastNameInput = new JTextField(20);
        birthYearInput = new JTextField(20);
        deathYearInput = new JTextField(20);

        // Adding Labels
        addText("First Name:", addMemberPanel, textSize);
        addMemberPanel.add(firstNameInput);
        addText("Last Name:", addMemberPanel, textSize);
        addMemberPanel.add(lastNameInput);
        addText("Birth Year:", addMemberPanel, textSize);
        addMemberPanel.add(birthYearInput);
        addText("Alive?", addMemberPanel, textSize);
        aliveCheckBox(addMemberPanel, deathYearInput);
        addText("Death Year:", addMemberPanel, textSize);
        addMemberPanel.add(deathYearInput);

        addMemberPanel.revalidate();
        addMemberPanel.repaint();

        return addMemberPanel;
    }

    // MODIFIES: deathYearInput
    // EFFECTS: restricts and clears deathYearInput if the member is not alive
    private void aliveCheckBox(JPanel panel, JTextField deathInput) {
        aliveStatus = new JCheckBox();
        deathYearInput.setEnabled(true);

        aliveStatus.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (!aliveStatus.isSelected()) {
                    deathYearInput.setEnabled(true);
                } else {
                    deathYearInput.setText("");
                    deathYearInput.setEnabled(false);
                }
            }
        });

        panel.add(aliveStatus);
    }

    // MODIFIES: familyTree
    // EFFECTS: creates button for add member
    private void addMemberSubmitButton(JPanel panel) {
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                inputCheck();
            }
        });
        panel.add(submitButton, verticalStack);
    }

    // EFFECTS: catches NumberFormatException if birthYear or DeathYear are not
    // numbers, checks if all inputs are received, then
    private void inputCheck() {
        if (!firstNameInput.getText().equals("") & !lastNameInput.getText().equals("")
                & !descriptionInput.getText().equals("") & !birthYearInput.getText().equals("")) {
            try {
                Integer.parseInt(birthYearInput.getText());
                if (!birthYearInput.getText().equals("") & !aliveStatus.isSelected()) {
                    try {
                        setFamilyMember(Integer.parseInt(deathYearInput.getText()));
                        addMemberFamilyTree();
                    } catch (NumberFormatException e) {
                        popupPanel("Error: Death Year must be a number");
                    }
                } else if (aliveStatus.isSelected()) {
                    setFamilyMember(0);
                    addMemberFamilyTree();
                }
            } catch (NumberFormatException e) {
                popupPanel("Error: Birth Year must be a number");
            }
        } else {
            popupPanel("Invalid Input: Please enter value(s)");
        }
    }

    // MODIFIES: familyMember
    // EFFECTS: Sets data for family member, clears form.
    private void setFamilyMember(int deathYear) {
        familyMember = new FamilyMember(firstNameInput.getText(), lastNameInput.getText(),
                Integer.parseInt(birthYearInput.getText()), deathYear,
                aliveStatus.isSelected(),
                descriptionInput.getText());
        addMemberForm();
    }

    // MODIFIES: familyTree
    // EFFECTS: adds member to familyTree and creates a popup of the success
    private void addMemberFamilyTree() {
        familyTree.addFamilyMember(familyMember);
        popupPanel("Added " + firstNameInput.getText() + " " + lastNameInput.getText() + " successfully!");
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the description. This is done because
    // description requires a different size input area
    private void descriptionPanel(JPanel panel) {
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridLayout(1, 2, 10, 15));
        descriptionInput = new JTextArea(5, 20);
        descriptionInput.setLineWrap(true);
        descriptionInput.setWrapStyleWord(true);

        addText("Description:", descriptionPanel, textSize);
        wrapTextArea(descriptionPanel, descriptionInput);

        panel.add(descriptionPanel, verticalStack);

    }

    // MODIFIES: panel
    // EFFECTS: Wraps a JTextArea in a JScrollPane
    private void wrapTextArea(JPanel panel, JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, verticalStack);
    }

    // MODIFIES: this
    // EFFECTS: given String, it initializes it with given font size, and
    // assigns it to given panel.
    private void addText(String text, JPanel panel, int fontSize) {
        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        panel.add(textLabel);
    }

    // EFFECTS: prints logging data to console when the window is closed
    private void windowClosedLogging() {
        window.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent e) {
                EventLog eventLog = EventLog.getInstance();
                for (Event event : eventLog) {
                    System.out.println(event.getDate() + " " + event.getDescription());
                }
            }
        });
    }
}