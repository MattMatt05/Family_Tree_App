package ui;

import model.FamilyMember;
import model.FamilyTree;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// This is the UI for the FamilyTree class. 
// With the usage of the functions developed in FamilyTree this class is used to 
// Develop a clean UI to use those aforementioned functions.
// This is a text based UI
public class FamilyTreeUI {
    private static final String JSON_STORE = "./data/familyTree.json";
    private FamilyTree familyTree;
    private String response;
    private Scanner input; // Taken from java.util
    private boolean quit = false;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: Starts the program, throws if file not found
    public FamilyTreeUI() throws FileNotFoundException {
        familyTree = new FamilyTree("FamilyTree"); // Intialize familyTree
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        run();
    }

    // EFFECTS: runs the program by generating
    private void run() {
        System.out.println("\nWelcome to the Family Tree Software!");
        displayLoadQuestion();
        do {
            input = new Scanner(System.in); // Must reintialize to request input each time
            displayOptions();
            System.out.print("> ");
            response = input.nextLine();

            decision(response);
        } while (!quit);
        displaySaveQuestion();
        System.out.println("\nSee you next time!");
    }

    // EFFECTS: displays options for main menu
    private void displayOptions() {
        System.out.println("\nWhat would you like to do? (Enter a letter)");
        System.out.println("a) Show all family members in family tree");
        System.out.println("b) Modify family tree (add/remove family members)");
        System.out.println("c) Perform calculations or comparisons");
        System.out.println("l) Load data");
        System.out.println("s) Save data");
        System.out.println("q) quit");
    }

    // EFFECTS: Asks if user would like to load data
    // If "y", then loads data
    private void displayLoadQuestion() {
        input = new Scanner(System.in); // re-initializing each time
        System.out.println("\nWould you like to load data? (y|n)");
        System.out.print("> ");
        response = input.nextLine();

        if (response.equals("y")) {
            loadFamilyTree();
        } else {
            nameFamilyTree();
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets familyTree
    private void nameFamilyTree() {
        input = new Scanner(System.in); // re-initializing each time
        System.out.println("\nWhat would you like to name your family tree? ");
        System.out.print("> ");
        response = input.nextLine();

        familyTree.setTreeName(response);
    }

    // EFFECTS: Asks if user would like to save data
    // If "y", then save data
    private void displaySaveQuestion() {
        input = new Scanner(System.in); // re-initializing each time
        System.out.println("\nWould you like to save data? (y|n)");
        System.out.print("> ");
        response = input.nextLine();

        if (response.equals("y")) {
            saveFamilyTree();
        }
    }

    // EFFECTS: Impliments the response appropriately
    private void decision(String response) {
        switch (response) {
            case "a":
                showTree();
                break;
            case "b":
                modifyFamilyTree();
                break;
            case "c":
                performCalculations();
                break;
            case "l":
                loadFamilyTree();
                break;
            case "s":
                saveFamilyTree();
                break;
            case "q":
                quit = true;
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: Serves as an options menu for removing/adding family members
    private void modifyFamilyTree() {
        String choice;
        input = new Scanner(System.in); // re-initializing each time
        System.out.println("\nWhat would you like to do? (Enter a letter)");
        System.out.println("a) Add a family member");
        System.out.println("b) Remove a family member");
        System.out.println("e) exit");
        System.out.print("> ");
        choice = input.nextLine();

        switch (choice) {
            case "a":
                addMember();
                break;
            case "b":
                removeMember();
                break;
        }
    }

    // EFFECTS: Calculates statistics for different methods (ex: mean lifespan)
    private void performCalculations() {
        String choice;
        input = new Scanner(System.in); // re-initializing each time
        System.out.println("\nWhat would you like to do? (Enter a letter)");
        System.out.println("a) Find older of two family members");
        System.out.println("b) Find the oldest family member in the tree");
        System.out.println("c) Find the mean lifespan for deceased family members");
        System.out.println("e) exit");
        System.out.print("> ");
        choice = input.nextLine();

        switch (choice) {
            case "a":
                compareMemberAge();
                break;
            case "b":
                showOldestPerson();
                break;
            case "c":
                meanDeceasedAge();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates a form to add to FamilyTree
    private void addMember() {
        input = new Scanner(System.in);
        boolean alive = false; // Initializes variables
        int birthYear;
        int deathYear = 0;

        System.out.print("\nEnter first Name: ");
        String firstName = input.nextLine();
        System.out.print("Enter last Name: ");
        String lastName = input.nextLine();
        System.out.print("Enter their description: ");
        String description = input.nextLine();
        System.out.print("Are they currently alive (Enter y/n)? ");
        String choice = input.nextLine();

        input = new Scanner(System.in);
        System.out.print("What is their birth year (ex: 2000): ");
        birthYear = input.nextInt();

        if (choice.equals("y")) {
            alive = true;
        } else {
            System.out.print("What is their death year (ex: 2000): ");
            deathYear = input.nextInt();
        }

        addMemberToFamilyTree(firstName, lastName, birthYear, deathYear, alive, description);
    }

    // MODIFIES: this
    // EFFECTS: Adds given values to Family Tree.
    private void addMemberToFamilyTree(String firstName, String lastName, int birthYear, int deathYear, boolean alive,
            String description) {

        FamilyMember familyMember = new FamilyMember(firstName, lastName, birthYear, deathYear, alive, description);

        familyTree.addFamilyMember(familyMember);

        System.out.println("\nFamily Member added successfully");
    }

    // MODIFIES: this
    // EFFECTS: Removes a member from FamilyTree
    private void removeMember() {
        FamilyMember toRemove = new FamilyMember("", "", 0, 0, false, ""); // Temporary variables being intialized
        FamilyMember temp;
        temp = surveyMember(toRemove);

        System.out.println("\n" + temp.getFirstName() + " " + temp.getLastName()
                + " was removed successfully from the tree!");
        familyTree.removeFamilyMember(temp);
    }

    // EFFECTS: Displays the name of the person who is older out of
    // two given family members.
    private void compareMemberAge() {
        FamilyMember familyMember1 = new FamilyMember("", "", 0, 0, false, ""); // Temporary variables being intialized
        FamilyMember familyMember2 = new FamilyMember("", "", 0, 0, false, "");

        System.out.println();
        familyMember1 = surveyMember(familyMember1);

        if (!(familyMember1.getBirthYear() == 0)) {
            System.out.println();
            familyMember2 = surveyMember(familyMember2);
            if (!(familyMember2.getBirthYear() == 0)) {
                FamilyMember older;
                older = familyTree.olderFamilyMember(familyMember1, familyMember2);
                System.out.println("\nThe older family member is :"
                        + older.getFirstName() + " " + older.getLastName()
                        + " because they are " + older.getAge());
            } else {
                System.out.println("\nFamily Member not found");
            }
        } else {
            System.out.println("\nFamily Member not found");
        }
    }

    // EFFECTS: Collects information for search. Then assigns it to given
    // FamilyMember object.
    private FamilyMember surveyMember(FamilyMember familyMember) {
        input = new Scanner(System.in);
        System.out.print("Enter the first name for the member: ");
        String firstName = input.nextLine();
        System.out.print("Enter the last name for the member: ");
        String lastName = input.nextLine();
        System.out.print("Enter the birth year for the member: ");
        int birthYear = input.nextInt();

        familyMember = findMember(firstName, lastName, birthYear);
        return familyMember;
    }

    // EFFECTS: Basic search for member using first name, last name, and birthYear
    // (Some families have a parent and kid who share names hence three varibles for
    // certainty in the search)
    // Sets the returned FamilyMember to empty if member not found
    private FamilyMember findMember(String firstName, String lastName, int birthYear) {
        FamilyMember temp = new FamilyMember("", "", 0, 0, false, "");
        for (FamilyMember familyMember : familyTree.getFamilyTree()) {
            if (familyMember.getFirstName().equals(firstName) && familyMember.getLastName().equals(lastName)
                    && familyMember.getBirthYear() == birthYear) {
                temp = familyMember;
            }
        }
        return temp;
    }

    // REQUIRES: familyTree != empty
    // EFFECTS: Displays all members in the FamilyTree. (in no particular order)
    private void showTree() {
        System.out.println();
        for (FamilyMember familyMember : familyTree.getFamilyTree()) {
            System.out.print(familyMember.getFirstName() + " " + familyMember.getLastName() + " | born: "
                    + familyMember.getBirthYear() + " Age: " + familyMember.getAge() + " Description: "
                    + familyMember.getDescription() + "\n");
        }
    }

    // REQUIRES: familyTree != empty
    // EFFECTS: Displays the oldest person in the FamilyTree
    private void showOldestPerson() {
        System.out.println("\nThe oldest family member is " + familyTree.oldestFamilyMember().getFirstName() + " "
                + familyTree.oldestFamilyMember().getLastName() + " age: " + familyTree.oldestFamilyMember().getAge()
                + "\n");
    }

    // REQUIRES: familyTree != empty
    // EFFECTS: Displays the mean age for deceased family members.
    // (This is a feature because people can use it to estimate their own lifespan)
    private void meanDeceasedAge() {
        System.out.println("\nThe mean lifespan for deceased family members in this tree is: "
                + familyTree.meanAgeAmongDeceased() + " years");
    }

    // Credit:
    // This method was referenced/modeled from loadWorkRoom:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java

    // MODIFIES: this
    // EFFECTS: loads familyTree from file
    private void loadFamilyTree() {
        try {
            familyTree = jsonReader.read();
            System.out.println("\nLoaded " + familyTree.getTreeName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("\nUnable to read from file: " + JSON_STORE);
        }
    }

    // Credit:
    // This method was referenced/modeled from saveWorkRoom:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java

    // EFFECTS: saves the familyTree to file
    private void saveFamilyTree() {
        try {
            jsonWriter.open();
            jsonWriter.write(familyTree);
            jsonWriter.close();
            System.out.println("\nSaved " + familyTree.getTreeName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_STORE);
        }
    }

}
