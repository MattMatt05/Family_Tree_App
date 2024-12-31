package persistence;

import org.json.JSONObject;

// Credit: 
// This interface is modeled/referenced from Writable: 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/Writable.java
public interface Writable {

    // EFFECTS: returns this as a JSON Object
    JSONObject toJson();
}
