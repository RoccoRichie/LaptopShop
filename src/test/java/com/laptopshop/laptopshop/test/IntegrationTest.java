package com.laptopshop.laptopshop.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.laptopshop.laptopshop.data.LaptopDAO;
import com.laptopshop.laptopshop.model.Laptop;
import com.laptopshop.laptopshop.rest.JaxRsActivator;
import com.laptopshop.laptopshop.rest.LaptopWS;
import com.laptopshop.laptopshop.test.utils.UtilsDAO;

//	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class IntegrationTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "Test.jar")
				.addClasses(LaptopDAO.class, Laptop.class, JaxRsActivator.class, LaptopWS.class, UtilsDAO.class)
				// .addPackage(EventCause.class.getPackage())
				// .addPackage(EventCauseDAO.class.getPackage())
				// this line will pick up the production db
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	private static boolean setUpIsDone = false;

	@EJB
	private LaptopWS laptopWS;

	@EJB
	private LaptopDAO laptopDAO;

	@EJB
	private UtilsDAO utilsDAO;

	@Before
	public void setUp() {
		// this function means that we start with an empty table
		// And add one wine
		// it should be possible to test with an in memory db for efficiency
		utilsDAO.deleteTable();
		Laptop laptop = new Laptop();
		laptop.setId(1);
		laptop.setName("Arquillian");
		laptop.setBrand("test");
		laptop.setDisplay("20");
		laptop.setProcessor("Athlone");
		laptop.setLapMem("2000");
		laptop.setHarddrive("Big");
		laptop.setGraphics("1234");
		laptop.setNumUsbs(99);
		laptop.setPrice(9999.99);

		laptopDAO.save(laptop);
	}

	@Test
	public void testGetAllLaptops() {
		List<Laptop> laptopList = laptopDAO.findAllLaptops();
		assertEquals("Data fetch = data persisted", laptopList.size(), 1);
	}

	@Test
	public void testGetLaptopByName() {
		List<Laptop> laptopList = laptopDAO.findLaptopsByName("Arquillian");
		assertTrue(laptopList.get(0).getName().equals("Arquillian"));
	}

	@Test
	public void testGetLaptopById() {
		Laptop laptop = laptopDAO.findLaptopById(1);
		assertTrue(laptop.getName().equals("Arquillian"));
	}

	@Test
	public void testSaveMethod() {
		Laptop laptop2 = new Laptop();
		laptop2.setId(2);
		laptop2.setName("Richie");
		laptop2.setBrand("test");
		laptop2.setDisplay("30");
		laptop2.setProcessor("Longford");
		laptop2.setLapMem("4000");
		laptop2.setHarddrive("Small");
		laptop2.setGraphics("4321");
		laptop2.setNumUsbs(9);
		laptop2.setPrice(8888.99);

		laptopDAO.save(laptop2);
		List<Laptop> laptopList = laptopDAO.findAllLaptops();
		assertEquals("Data fetch = data persisted", laptopList.size(), 2);

	}

	@Test
	public void testUpdate() {
		Laptop laptop3 = new Laptop();
		laptop3.setId(3);
		laptop3.setName("Richie");
		laptop3.setBrand("test");
		laptop3.setDisplay("30");
		laptop3.setProcessor("Longford");
		laptop3.setLapMem("4000");
		laptop3.setHarddrive("Small");
		laptop3.setGraphics("4321");
		laptop3.setNumUsbs(9);
		laptop3.setPrice(8888.99);

		laptopDAO.save(laptop3);

		laptop3.setName("Peter Andre");
		laptopDAO.update(laptop3);

		assertTrue(laptop3.getName().equals("Peter Andre"));

	}

	@Test
	public void testDelete() {
		laptopDAO.delete(1);
		List<Laptop> laptopList = laptopDAO.findAllLaptops();
		assertEquals("Laptop Deleted", laptopList.size(), 0);
	}

	// @After
	// public void teardown() {
	// utilsDAO.deleteTable();
	// utilsDAO.reBuildTable();
	// }

}
