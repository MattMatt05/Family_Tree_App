package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class TestFamilyTree {
    FamilyMember familyMember1;
    FamilyMember familyMember2;
    FamilyTree familyTree;

    @BeforeEach
    void runBefore() {
        familyMember1 = new FamilyMember("Joe", "Mallard", 1900, 1945, false, "He was a good man");
        familyMember2 = new FamilyMember("Steph", "Mclow", 2005, 0, true, "She is a farmer");

        familyTree = new FamilyTree("");
    }

    @Test
    void testConstructor() {
        assertTrue(familyTree.getFamilyTree().isEmpty());
        assertEquals("", familyTree.getTreeName());
    }

    @Test
    void testSetFamilyTreeName() {
        familyTree.setTreeName("John's Family Tree");
        assertEquals("John's Family Tree", familyTree.getTreeName());
    }

    @Test
    void testAddFamilyMember() {
        familyTree.addFamilyMember(familyMember1);
        assertEquals(familyMember1, familyTree.getFamilyTree().get(0));
    }

    @Test
    void testRemoveFamilyMember() {
        familyTree.addFamilyMember(familyMember1);
        familyTree.addFamilyMember(familyMember2);
        assertEquals(familyMember1, familyTree.getFamilyTree().get(0));
        assertEquals(familyMember2, familyTree.getFamilyTree().get(1));

        familyTree.removeFamilyMember(familyMember1);
        assertEquals(familyMember2, familyTree.getFamilyTree().get(0));
        familyTree.removeFamilyMember(familyMember2);
        assertTrue(familyTree.getFamilyTree().isEmpty());
    }

    @Test
    void testOlderFamilyMember() {
        assertEquals(familyMember1, familyTree.olderFamilyMember(familyMember1, familyMember1));

        assertEquals(familyMember1, familyTree.olderFamilyMember(familyMember2, familyMember1));

        assertEquals(familyMember1, familyTree.olderFamilyMember(familyMember1, familyMember2));
    }

    @Test
    void testOldestFamilyMember() {
        familyTree.addFamilyMember(familyMember2);
        assertEquals(familyMember2, familyTree.oldestFamilyMember());

        familyTree.addFamilyMember(familyMember2);
        assertEquals(familyMember2, familyTree.oldestFamilyMember());

        familyTree.addFamilyMember(familyMember1);
        assertEquals(familyMember1, familyTree.oldestFamilyMember());
    }

    @Test
    void testMeanAgeAmongDeceased() {
        familyTree.addFamilyMember(familyMember1);
        assertEquals(45, familyTree.meanAgeAmongDeceased());

        familyTree.addFamilyMember(familyMember2);
        assertEquals(45, familyTree.meanAgeAmongDeceased());

        familyTree.addFamilyMember(familyMember1);
        assertEquals(45, familyTree.meanAgeAmongDeceased());

        familyTree = new FamilyTree("");
        familyTree.addFamilyMember(familyMember2);
        assertEquals(0, familyTree.meanAgeAmongDeceased());
    }

}
