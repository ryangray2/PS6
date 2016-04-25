package base;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.text.ParseException;
import domain.PersonDomainModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Person_Test {
	
	private static PersonDomainModel p1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse("1966-11-22");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		p1 = new PersonDomainModel();
		p1.setFirstName("Omar");
		p1.setLastName("Little");
		p1.setBirthday(d);
		p1.setCity("Baltimore");
		p1.setPostalCode(21223);
		p1.setStreet("Hamsterdam");
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel p;
		PersonDAL.deletePerson(p1.getPersonID());
		p = PersonDAL.getPerson(p1.getPersonID());
		assertNull("Person not in database", p);
	}

	@Test
	public void testAdd() {
		PersonDomainModel p;
		p = PersonDAL.getPerson(p1.getPersonID());
		assertNull("Person not in the database", p);
		PersonDAL.addPerson(p1);
		
		p = PersonDAL.getPerson(p1.getPersonID());
		System.out.println(p1.getPersonID());
		assertNotNull("Person in the database", p);
	}
	@Test
	public void testUpdate() {
		PersonDomainModel p;
		final String newCity = "New York";
		
		p = PersonDAL.getPerson(p1.getPersonID());
		assertNull("Person not in the database", p);
		PersonDAL.addPerson(p1);
		
		p1.setCity(newCity);
		PersonDAL.updatePerson(p1);
		p = PersonDAL.getPerson(p1.getPersonID());
		assertTrue(p1.getCity() == newCity);
		
	}
	@Test
	public void testDelete() {
		PersonDomainModel p;
		p = PersonDAL.getPerson(p1.getPersonID());
		assertNull("Person not in the database", p);
		
		PersonDAL.addPerson(p1);
		p = PersonDAL.getPerson(p1.getPersonID());
		System.out.println(p1.getPersonID());
		assertNotNull("Person in the database", p);
		
		PersonDAL.deletePerson(p1.getPersonID());
		p = PersonDAL.getPerson(p1.getPersonID());
		assertNull("Person not in the database", p);
		
		
	}
	@Test 
	public void testGet() {
		PersonDomainModel p;
		ArrayList<PersonDomainModel> plist = new ArrayList<PersonDomainModel>();
		p = PersonDAL.getPerson(p1.getPersonID());
		assertNull("Person not in database", p);
		PersonDAL.addPerson(p1);
		p = PersonDAL.getPerson(p1.getPersonID());
		System.out.println(p1.getPersonID());
		assertNotNull("Person in the database", p);
		plist = PersonDAL.getPersons();
		assertTrue("People in the database", plist.size() == 1);
		
	}

}
