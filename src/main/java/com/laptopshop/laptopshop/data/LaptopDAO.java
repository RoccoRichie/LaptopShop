package com.laptopshop.laptopshop.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.laptopshop.laptopshop.model.Laptop;

@Stateless
@LocalBean
public class LaptopDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Laptop> getAllLaptops() {
		Query query = em.createQuery("Select l from Laptop l");
		return query.getResultList();
	}

	public Laptop getLaptop(int id) {
		return em.find(Laptop.class, id);
	}

	public void save(Laptop laptop) {
		em.persist(laptop);
	}

	public void update(Laptop laptop) {
		System.out.println("Laptop with id: " + laptop.getId() + " was updated");
		em.merge(laptop);
	}

	public void delete(int id) {
		System.out.println("Laptop with id: " + id + " was deleted.");
		em.remove(getLaptop(id));
	}

	// public List<Laptop> getLaptopByName(String lapName) {
	// // EntityManager em = ... // get the entity manager
	// Query query = em.createNativeQuery("SELECT n.Name FROM Laptops n where
	// Name = '" + lapName + "'", Laptop.class);
	// // Query query = em.createQuery("SELECT n FROM Laptop n where n.Name =
	// // '" + lapName + "'");
	// // Query query = em.createQuery("SELECT me FROM MyEntity me WHERE
	// // me.name = :name");
	// return query.getResultList();
	// }
	// public Laptop getLaptopByName(String lapName) {
	// // EntityManager em = ... // get the entity manager
	// Query query = em.createQuery("SELECT n FROM Laptop n where Name = '" +
	// lapName + "'", Laptop.class);
	// // Query query = em.createQuery("SELECT me FROM MyEntity me WHERE
	// // me.name = :name");
	// Collection<Laptop> name = query.getResultList();
	// for (Laptop lap : name) {
	// return lap;
	// }
	// return null;
	// }1

	// This Works!!!!!!
	public Laptop getLaptopByName(String lapName) {
		Query query = em.createQuery("SELECT n FROM Laptop n where n.lapName= '" + lapName + "'");

		return (Laptop) query.getSingleResult();
	}

}
