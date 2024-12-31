package model;

import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// The FamilyTree class contains a list of FamilyMembers.
// This class allows for calculations to be made using the data from FamilyMembers. 
// These calculations include: comparing ages between family members, 
// finding oldest deceased relative, finding mean oldest age.
public class FamilyTree implements Writable {
    private List<FamilyMember> familyTree;
    private String treeName;

    // EFFECTS: Instatiates a empty FamilyTree
    public FamilyTree(String treeName) {
        this.treeName = treeName;
        familyTree = new ArrayList<FamilyMember>();
    }

    // MODIFIES: this
    // EFFECTS: Adds familyMember to the FamilyTree
    public void addFamilyMember(FamilyMember familyMember) {
        familyTree.add(familyMember);
        EventLog.getInstance().logEvent(new Event("Added family Member: " + familyMember.getFirstName() + " "
                + familyMember.getLastName() + " to family tree"));
    }

    // MODIFIES: this
    // EFFECTS: Removes familyMember from the FamilyTree
    public void removeFamilyMember(FamilyMember familyMember) {
        familyTree.remove(familyMember);
        EventLog.getInstance().logEvent(new Event("Removed family Member: " + familyMember.getFirstName() + " "
                + familyMember.getLastName() + " from family tree"));
    }

    // EFFECTS: Returns the familyTree list
    public List<FamilyMember> getFamilyTree() {
        // Not required to log
        return familyTree;
    }

    // EFFECTS: Returns the FamilyMember with a higher age.
    // (If same age returns first entered FamilyMember)
    // (eg. (bob age 41, john age 41) returns bob)
    // The function is more useful with these properties.
    public FamilyMember olderFamilyMember(FamilyMember familyMember1, FamilyMember familyMember2) {
        FamilyMember older;
        if (familyMember1.getAge() < familyMember2.getAge()) {
            older = familyMember2;
        } else {
            older = familyMember1;
        }
        // Didn't add a event listener as this was not used in GUI
        return older;
    }

    // REQUIRES: familyTree != Empty
    // EFFECTS: Returns the oldest family member by comparing against all members of
    // list
    public FamilyMember oldestFamilyMember() {
        FamilyMember oldest = new FamilyMember(null, null, 0, 0, false, null);
        int temp = 0;
        for (FamilyMember familyMember : familyTree) {
            if (familyMember.getAge() > temp) {
                oldest = familyMember;
                temp = familyMember.getAge();
            }
        }
        EventLog.getInstance().logEvent(new Event("Calculated oldest family member age " + oldest.getAge()));
        return oldest;
    }

    // REQUIRES: familyTree != Empty
    // EFFECTS: Returns the mean(average) age of the deceased Family Members.
    public int meanAgeAmongDeceased() {
        int deceasedAgeTotal = 0; // Used to total deceased total age.
        int counter = 0; // Keeps track of count of deceased.
        for (FamilyMember familyMember : familyTree) {
            if (!familyMember.isAlive()) {
                deceasedAgeTotal += familyMember.getAge();
                counter++;
            }
        }

        if (counter == 0) {
            EventLog.getInstance().logEvent(new Event("Calculated lifespan of " + 0 + " years for family tree"));
            return 0;
        } else {
            EventLog.getInstance().logEvent(
                    new Event("Calculated lifespan of " + (deceasedAgeTotal / counter) + " years for family tree"));
            return (deceasedAgeTotal / counter);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets new treeName
    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    // EFFECTS: Returns the familyTree name
    public String getTreeName() {
        return treeName;
    }

    // Credit:
    // toJSON and familyMembersToJson methods modeled/referenced from WorkRoom:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/WorkRoom.java
    // EFFECTS: Generates and returns JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", treeName);
        json.put("familyMembers", familyMembersToJson());
        return json;
    }

    // EFFECTS: returns familyMembers in this familyTree as a JSON array
    private JSONArray familyMembersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FamilyMember familyMember : familyTree) {
            jsonArray.put(familyMember.toJson());
        }
        return jsonArray;
    }

}
