package persistence;

import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Credit:
// This class is referenced/modeled from JsonReaderTest:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonReaderTest.java
class TestJsonReader extends TestJson {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFamilyTree() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFamilyTree.json");
        try {
            FamilyTree familyTree = reader.read();
            assertEquals("My Family Tree", familyTree.getTreeName());
            assertEquals(0, familyTree.getFamilyTree().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFamilyTree() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFamilyTree.json");
        try {
            FamilyTree familyTree = reader.read();
            assertEquals("My Family Tree", familyTree.getTreeName());
            List<FamilyMember> familyMembers = familyTree.getFamilyTree();
            assertEquals(2, familyMembers.size());
            checkFamilyMember("Matt", "Molski", 2000, 0, true, "He was a really cool guy", familyMembers.get(0));
            checkFamilyMember("Joe", "Swan", 1950, 2010, false, "Sad man", familyMembers.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}