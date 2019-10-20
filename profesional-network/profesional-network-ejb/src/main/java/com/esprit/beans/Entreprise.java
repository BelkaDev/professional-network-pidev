package com.esprit.beans;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Entreprise extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	public Entreprise() {
		super();
	}
	


}
