package com.esprit.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

	private String streetAddress;
	private String city;
	private int postalCode;

}
