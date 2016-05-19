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

	public List<Laptop> getLaptopsByName(String lapName) {
		Query query = em.createQuery("SELECT n FROM Laptop AS n " + "WHERE n.lapName LIKE ?1");
		query.setParameter(1, "%" + lapName.toUpperCase() + "%");
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

}
