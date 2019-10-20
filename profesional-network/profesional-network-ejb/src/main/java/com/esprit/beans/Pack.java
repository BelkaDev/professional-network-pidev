package com.esprit.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Pack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	
	private int id;
	@Column(name = "prix")
	private double prix;
	@Column(name="description")
	private String description;
	@Column(name="titre")
	private String titre;
	@Column(name="type")
	private TypePack type;	
	@Column(name="datedebut")
	private Date datedebut;
	@Column(name="datefin")
	private Date datefin;
	@Column(name="reduction")
	private double reduction;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="pack")
	  private Set<User> listUser ;
	
	
	@XmlElement(name="listUser")
	public Set<User> getListUser() {
		return listUser;
	}



	public void setListUser(Set<User> listUser) {
		this.listUser = listUser;
	}



	public Pack() {
		super();
	}
	
	

	public Pack(int id, double prix, String description, String titre, TypePack type, Date datedebut, Date datefin,
			double reduction) {
		super();
		this.id = id;
		this.prix = prix;
		this.description = description;
		this.titre = titre;
		this.type = type;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.reduction = reduction;
	}


	@XmlAttribute(name="id",required=true)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name="prix")
	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}

	@XmlElement(name="description")
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name="titre")
	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}

	@XmlElement(name="type")
	public TypePack getType() {
		return type;
	}


	public void setType(TypePack type) {
		this.type = type;
	}

	@XmlElement(name="datedebut")
	public Date getDatedebut() {
		return datedebut;
	}


	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	@XmlElement(name="datefin")
	public Date getDatefin() {
		return datefin;
	}


	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	@XmlElement(name="reduction")
	public double getReduction() {
		return reduction;
	}


	public void setReduction(double reduction) {
		this.reduction = reduction;
	}
	

	
}
