package model;

import java.time.*;
import org.json.JSONObject;

import persistence.Writable;

// This class is for representing family members.
// family member has a firstname, lastname, birthyear, deathyear, aliveStatus (whether they are alive or not), 
// description of the person.
// The age is only offered here as a getAge method, this is because the calculation of age varies based
// on whether they are dead or alive.
public class FamilyMember implements Writable {
    private String firstName; // Represents the first name
    private String lastName; // Represents the Last name
    private int birthYear; // Represents the year the person was born
    private int deathYear; // Represents the year the person died
    private boolean aliveStatus; // Represents the status of the person whether alive(true) or dead(false)
    private String description; // Represents the description on the person's life
    private int currentYear = Year.now().getValue(); // Using java library grabs current year

    // REQUIRES: birthYear <= deathYear
    // EFFECTS: Instatiates a family member with their full name, birth year, death
    // year, alive status, and description
    public FamilyMember(String firstName, String lastName, int birthYear, int deathYear, boolean aliveStatus,
            String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.aliveStatus = aliveStatus;
        this.description = description;
    }

    // EFFECTs: Calculates age in two different ways:
    // -if they're dead: deathYear-bornYear
    // -if they're alive: currentDate - bornYear
    public int getAge() {
        int age = 0;
        if (aliveStatus) {
            age = (currentYear - birthYear);
        } else {
            age = (deathYear - birthYear);
        }

        return age;
    }

    // MODIFIES: this
    // EFFECTS: Changes status to dead
    public void setStatusDead() {
        aliveStatus = false;
    }

    // MODIFIES: this
    // EFFECTS: Changes status to alive
    public void setStatusAlive() {
        aliveStatus = true;
    }

    public boolean isAlive() {
        return aliveStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public String getDescription() {
        return description;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // REQUIRES: birthYear <= deathYear
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    // REQUIRES: birthYear <= deathYear
    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Credit:
    // Method modeled/referenced from toJson:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/Thingy.java
    // EFFECTS: Returns JSON object with FamilyMember values
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("firstName", firstName);
        json.put("lastName", lastName);
        json.put("birthYear", birthYear);
        json.put("deathYear", deathYear);
        json.put("aliveStatus", aliveStatus);
        json.put("description", description);
        return json;
    }
}