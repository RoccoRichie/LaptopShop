package com.laptopshop.laptopshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
// @Table(name = "laptops")
public class Laptop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// @Column(name = "lapName")
	private String lapName;

	// @Column(name = "brand")
	private String brand;

	private String display;

	private String processor;

	private String lapMem;

	private String harddrive;

	private String graphics;

	private int numUsb;

	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return lapName;
	}

	public void setName(String lapName) {
		this.lapName = lapName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getLapMem() {
		return lapMem;
	}

	public void setLapMem(String lapMem) {
		this.lapMem = lapMem;
	}

	public String getHarddrive() {
		return harddrive;
	}

	public void setHarddrive(String harddrive) {
		this.harddrive = harddrive;
	}

	public String getGraphics() {
		return graphics;
	}

	public void setGraphics(String graphics) {
		this.graphics = graphics;
	}

	public int getNumUsbs() {
		return numUsb;
	}

	public void setNumUsbs(int numUsbs) {
		this.numUsb = numUsbs;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
