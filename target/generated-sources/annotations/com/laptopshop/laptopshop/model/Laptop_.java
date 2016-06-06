package com.laptopshop.laptopshop.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Laptop.class)
public abstract class Laptop_ {

	public static volatile SingularAttribute<Laptop, String> lapName;
	public static volatile SingularAttribute<Laptop, String> numUsb;
	public static volatile SingularAttribute<Laptop, String> price;
	public static volatile SingularAttribute<Laptop, String> display;
	public static volatile SingularAttribute<Laptop, Integer> id;
	public static volatile SingularAttribute<Laptop, String> graphics;
	public static volatile SingularAttribute<Laptop, String> lapMem;
	public static volatile SingularAttribute<Laptop, String> brand;
	public static volatile SingularAttribute<Laptop, String> processor;
	public static volatile SingularAttribute<Laptop, String> picture;
	public static volatile SingularAttribute<Laptop, String> harddrive;

}

