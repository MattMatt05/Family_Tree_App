package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Credit:
// This class is modeled/referenced from JsonTest:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonTest.java
public class TestJson {
    protected void checkFamilyMember(String firstName, String lastName, int birthYear, int deathYear,
            boolean aliveStatus, String description, FamilyMember familyMember) {
        assertEquals(firstName, familyMember.getFirstName());
        assertEquals(lastName, familyMember.getLastName());
        assertEquals(birthYear, familyMember.getBirthYear());
        assertEquals(deathYear, familyMember.getDeathYear());
        assertEquals(aliveStatus, familyMember.isAlive());
        assertEquals(description, familyMember.getDescription());
    }
}
