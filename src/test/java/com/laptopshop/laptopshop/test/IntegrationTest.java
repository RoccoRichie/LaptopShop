package com.laptopshop.laptopshop.test;

import static org.junit.Assert.assertEquals;

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
		// laptop.setId(1);
		laptop.setName("Arquillian");
		laptop.setBrand("test");
		laptop.setDisplay("1");
		laptop.setProcessor("Athlone");
		laptop.setLapMem("2000");
		laptop.setHarddrive("arq");
		laptop.setGraphics("1234");
		laptop.setNumUsbs(99);
		laptop.setPrice(9999.99);
		;
		laptopDAO.save(laptop);
	}

	@Test
	public void testGetAllWines() {
		List<Laptop> laptopList = laptopDAO.getAllLaptops();
		assertEquals("Data fetch = data persisted", laptopList.size(), 1);
	}

}
