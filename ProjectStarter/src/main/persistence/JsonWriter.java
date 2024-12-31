package persistence;

import model.FamilyTree;

import org.json.JSONObject;

import java.io.*;

// Credit: 
// This class is modeled/referenced from JsonWriter: 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonWriter.java

// This class is responsible for writing to the JSON file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String fileLocation; // Location of where to save file

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file
    // cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileLocation));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of familyTree to file
    public void write(FamilyTree familyTree) {
        JSONObject json = familyTree.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
