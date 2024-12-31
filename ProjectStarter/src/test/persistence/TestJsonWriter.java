package persistence;

import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Credit:
// This test class was referenced/modeled from JsonWriterTest:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonWriterTest.java
class TestJsonWriter extends TestJson {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFamilyTree() {
        try {
            FamilyTree familyTree = new FamilyTree("My Family Tree");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFamilyTree.json");
            writer.open();
            writer.write(familyTree);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFamilyTree.json");
            familyTree = reader.read();
            assertEquals("My Family Tree", familyTree.getTreeName());
            assertEquals(0, familyTree.getFamilyTree().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFamilyTree() {
        try {
            FamilyTree familyTree = new FamilyTree("My Family Tree");
            familyTree.addFamilyMember(new FamilyMember("Matt", "Molski", 2000, 0, true, "He was a really cool guy"));
            familyTree.addFamilyMember(new FamilyMember("Joe", "Swan", 1950, 2010, false, "Sad man"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFamilyTree.json");
            writer.open();
            writer.write(familyTree);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralfamilyTree.json");
            familyTree = reader.read();
            assertEquals("My Family Tree", familyTree.getTreeName());
            List<FamilyMember> familyMembers = familyTree.getFamilyTree();
            assertEquals(2, familyMembers.size());
            checkFamilyMember("Matt", "Molski", 2000, 0, true, "He was a really cool guy", familyMembers.get(0));
            checkFamilyMember("Joe", "Swan", 1950, 2010, false, "Sad man", familyMembers.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}