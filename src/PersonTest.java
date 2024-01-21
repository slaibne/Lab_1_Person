import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person p1, p2, p3, p4, p5, p6;

    @BeforeEach
    void setUp()
    {
        p1 = new Person("00000A", "Bob", "Tester1", "Mr.", 1955);
        p2 = new Person("00000B", "Sally", "Tester2","Mrs.", 1975);
        Person.setIDSeed(0);
        p3 = new Person("Bob", "Tester3", 1960);
        p4 = new Person( "Sally", "Tester4", 1965);
        p5 = new Person( "Fred", "Tester5", 1970);
        p6 = new Person( "Cindy", "Tester6", 1975);


    }

    @Test
    /** tests the seed generator after 4 calls, should be 4*/
    void getIDSeed() {
        assertEquals(4, Person.getIDSeed());
    }

    /** Test the constructor with the first instance  p1 */

    @Test
    void getIDNum() {
        assertEquals("00000A", p1.getIDNum());
    }
    @Test
    void getFirstName() {
        assertEquals("Bob", p1.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Tester1", p1.getLastName());
    }

    @Test
    void getYOB() {
        assertEquals(1955, p1.getYOB());
    }

    /** now we go though all the setters and change P1 to P2 oo we can test the equals with P1 and P2 */
    @Test
    void setIDNum() {
        p1.setIDNum("00000B");
        assertEquals("00000B", p1.getIDNum());
    }

    @Test
    void setFirstName() {
        p1.setFirstName("Sally");
        assertEquals("Sally", p1.getFirstName());
    }

    @Test
    void setLastName() {
        p1.setLastName("Tester2");
        assertEquals("Tester2", p1.getLastName());
    }

    @Test
    void setYOB() {
        p1.setYOB(1975);
        assertEquals(1975, p1.getYOB());
    }

    @Test
    void equals() {
        p1.setIDNum("00000B");
        p1.setFirstName("Sally");
        p1.setLastName("Tester2");
        p1.setYOB(1975);
        assertEquals(true, p1.equals(p2));
    }


}