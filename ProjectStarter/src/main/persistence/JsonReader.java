package persistence;

import model.FamilyMember;
import model.FamilyTree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Credit:
// The class below was modeled/referenced from JsonReader:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonReader.java
// Represents a reader that reads familyTree from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads familyTree from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FamilyTree read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFamilyTree(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses familyTree from JSON object and returns it
    private FamilyTree parseFamilyTree(JSONObject jsonObject) {
        String treeName = jsonObject.getString("name");
        FamilyTree familyTree = new FamilyTree(treeName);
        addFamilyMembers(familyTree, jsonObject);
        return familyTree;
    }

    // MODIFIES: familyTree
    // EFFECTS: parses familyMembers from JSON object and adds them to familyTree
    private void addFamilyMembers(FamilyTree familyTree, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("familyMembers");
        for (Object json : jsonArray) {
            JSONObject nextFamilyMember = (JSONObject) json;
            addFamilyMember(familyTree, nextFamilyMember);
        }
    }

    // MODIFIES: familyTree
    // EFFECTS: parses FamilyMember from JSON object and adds it to familyTree
    private void addFamilyMember(FamilyTree familyTree, JSONObject jsonObject) {
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        int birthYear = jsonObject.getInt("birthYear");
        int deathYear = jsonObject.getInt("deathYear");
        boolean aliveStatus = jsonObject.getBoolean("aliveStatus");
        String description = jsonObject.getString("description");
        FamilyMember familyMember = new FamilyMember(firstName, lastName, birthYear, deathYear, aliveStatus,
                description);
        familyTree.addFamilyMember(familyMember);
    }
}
