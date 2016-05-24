package com.laptopshop.laptopshop.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.laptopshop.laptopshop.data.LaptopDAO;
import com.laptopshop.laptopshop.model.Laptop;

@Path("/inventory")
@Stateless
@LocalBean
public class LaptopWS {

	@EJB
	private LaptopDAO laptopDAO;

	// this works
	// @GET
	// @Path("/find/{lapName}")
	// @Produces({ MediaType.APPLICATION_JSON })
	// public Response findByName(@PathParam("lapName") String lapName) {
	// System.out.println("Get Laptop by name");
	// // lapName = lapName.replaceAll("_", " ");
	// Laptop laptops = laptopDAO.getLaptopByName(lapName);
	// return Response.status(200).entity(laptops).build();
	// }

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAllLaptops() {
		System.out.println("Get all Laptops");
		List<Laptop> laptops = laptopDAO.getAllLaptops();
		System.out.println("...got laptops...");
		System.out.println(laptops.size());
		return Response.status(200).entity(laptops).build();
	}

	@GET
	@Path("/find/{query}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByName(@PathParam("query") String query) {
		System.out.println("findByName: " + query);
		List<Laptop> laptops = laptopDAO.getLaptopsByName(query);
		return Response.status(200).entity(laptops).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response findLaptopById(@PathParam("id") int id) {
		Laptop laptop = laptopDAO.getLaptopById(id);
		return Response.status(200).entity(laptop).build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveLaptop(Laptop laptop) {
		laptopDAO.save(laptop);
		return Response.status(200).entity(laptop).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateLaptop(Laptop laptop) {
		laptopDAO.update(laptop);
		return Response.status(200).entity(laptop).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteLaptop(@PathParam("id") int id) {
		laptopDAO.delete(id);
		return Response.status(204).build();
	}

}
