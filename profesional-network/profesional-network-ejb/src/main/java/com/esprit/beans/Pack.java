package com.esprit.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.esprit.enums.PackType;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Pack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "price")
	private double price;
	@Column(name="description")
	private String description;
	@Column(name="title")
	private String title;
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private PackType type;	
	@Column(name="startDate")
	private Date startDate;
	@Column(name="endDate")
	private Date endDate;
	@Column(name="reduction")
	private double reduction;
	@JsonIgnore
	@OneToMany(mappedBy="pack",cascade = CascadeType.ALL)
	private List<UserPack> users;
	
	
	
	
	public Pack() {
		super();
	}


	public Pack(int id, double price, String description, String title, PackType type, Date startDate, Date endDate,
			double reduction) {
		super();
		this.id = id;
		this.price = price;
		this.description = description;
		this.title = title;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reduction = reduction;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public PackType getType() {
		return type;
	}
	public void setType(PackType type) {
		this.type = type;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getReduction() {
		return reduction;
	}
	public void setReduction(double reduction) {
		this.reduction = reduction;
	}
	public List<UserPack> getUsers() {
		return users;
	}
	public void setUsers(List<UserPack> users) {
		this.users = users;
	}
	
	
	

	
}
