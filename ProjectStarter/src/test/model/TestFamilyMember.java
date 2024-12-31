package model;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;

import org.junit.jupiter.api.*;

public class TestFamilyMember {
    private FamilyMember familyMember1;
    private FamilyMember familyMember2;
    private int currentYear;

    @BeforeEach
    void runBefore() {
        currentYear = Year.now().getValue();
        familyMember1 = new FamilyMember("Joe", "Mallard", 1900, 1945, false, "He was a good man");
        familyMember2 = new FamilyMember("Steph", "Mclow", 2005, 0, true, "She is a farmer");
    }

    @Test
    void testConstructor() {
        assertEquals("Joe", familyMember1.getFirstName());
        assertEquals("Mallard", familyMember1.getLastName());
        assertEquals(1900, familyMember1.getBirthYear());
        assertEquals(1945, familyMember1.getDeathYear());
        assertFalse(familyMember1.isAlive());
        assertEquals("He was a good man", familyMember1.getDescription());

        assertEquals("Steph", familyMember2.getFirstName());
        assertEquals("Mclow", familyMember2.getLastName());
        assertEquals(2005, familyMember2.getBirthYear());
        assertEquals(0, familyMember2.getDeathYear());
        assertTrue(familyMember2.isAlive());
        assertEquals("She is a farmer", familyMember2.getDescription());
    }

    @Test
    void testGetAge() {
        assertEquals(45, familyMember1.getAge());

        familyMember1.setDeathYear(1950);
        familyMember1.setDeathYear(1970);
        assertEquals(70, familyMember1.getAge());

        // I used currentYear as part of the test because the year will be changing. If
        // someone used this program next year
        // The test must still pass.
        assertEquals((currentYear - familyMember2.getBirthYear()), familyMember2.getAge());

        familyMember2.setBirthYear(2020);
        assertEquals((currentYear - familyMember2.getBirthYear()), familyMember2.getAge());

        familyMember2.setBirthYear(2024);
        familyMember2.setDeathYear(2024);
        assertEquals((currentYear - familyMember2.getBirthYear()), familyMember2.getAge());
    }

    @Test
    void testSetStatusDead() {
        assertFalse(familyMember1.isAlive());
        assertTrue(familyMember2.isAlive());

        familyMember1.setStatusAlive();
        familyMember2.setStatusDead();
        assertTrue(familyMember1.isAlive());
        assertFalse(familyMember2.isAlive());
    }

    @Test
    void testSetFirstName() {
        assertEquals("Joe", familyMember1.getFirstName());

        familyMember1.setFirstName("Smith");
        assertEquals("Smith", familyMember1.getFirstName());
    }

    @Test
    void testSetLastName() {
        assertEquals("Mallard", familyMember1.getLastName());

        familyMember1.setLastName("Cliff");
        assertEquals("Cliff", familyMember1.getLastName());
    }

    @Test
    void testSetDescription() {
        assertEquals("He was a good man", familyMember1.getDescription());

        familyMember1.setDescription("He was crazy his whole life");
        assertEquals("He was crazy his whole life", familyMember1.getDescription());
    }

}
