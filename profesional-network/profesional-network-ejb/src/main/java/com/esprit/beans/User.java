package com.esprit.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Set;

import com.esprit.enums.Gender;
import javax.persistence.FetchType;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
    @Email
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	
	@Enumerated(EnumType.STRING)
    private Gender gender;

	private Date birthDate;
	
	// address is complex type
	private Address address;
	@Column(name="isPremimum")
	private boolean isPpremimum;
	@Column(name="dateDebutP")
	private Date dateDebutP;
	@Column(name="dateFinP")
	private Date dateFinP;
	
	//****************************


  @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.EAGER)
  private Set<Post> Posts;
  

  @OneToMany(cascade = CascadeType.ALL,mappedBy = "commentingUser",fetch = FetchType.EAGER)
	private Set<Comment> Comments;
  

  @OneToMany(cascade =CascadeType.ALL,mappedBy = "reactingUser",fetch = FetchType.EAGER) 
	private Set<Reaction> Reactions;


  @OneToMany(cascade =CascadeType.ALL,mappedBy="sender",fetch = FetchType.EAGER)
	private Set<Message> Messages;


  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "whoClaim")
	private Set<Claim> Whoclaim;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "claimsOn", fetch = FetchType.EAGER)
	private Set<Claim> claimOn;
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<UserPack> packs;


	//****************************
	

	public User(int idUser) {
		super();
		this.id = idUser;
		this.gender = Gender.Other;
	}
	

	public Set<Claim> getWhoclaim() {
		return Whoclaim;
	}


	public void setWhoclaim(Set<Claim> whoclaim) {
		Whoclaim = whoclaim;
	}


	public Set<Claim> getClaimOn() {
		return claimOn;
	}


	public void setClaimOn(Set<Claim> claimOn) {
		this.claimOn = claimOn;
	}


	public List<UserPack> getPacks() {
		return packs;
	}


	public void setPacks(List<UserPack> packs) {
		this.packs = packs;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public User() {
		super();
	}


	public boolean isPpremimum() {
		return isPpremimum;
	}

	public void setPpremimum(boolean isPpremimum) {
		this.isPpremimum = isPpremimum;
	}

	public Date getDateDebutP() {
		return dateDebutP;
	}

	public void setDateDebutP(Date dateDebutP) {
		this.dateDebutP = dateDebutP;
	}

	public Date getDateFinP() {
		return dateFinP;
	}

	public void setDateFinP(Date dateFinP) {
		this.dateFinP = dateFinP;
	}

	

	

	public int getId() {
		return id;
	}

	public void setId(int idUser) {
		this.id = idUser;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

  
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	@Embedded
	public Address getAddress()
	{
		return address;
	}


  // #----------------------Relations----------------------#

	
  // Posts of the user
	
	@JsonIgnore
	public Set<Post> getPosts() {
		return Posts;
	}
	public void setPosts(Set<Post> Posts) {
		this.Posts = Posts;
	}

	public void addPostsOfThisUser(Set<Post> Posts)
	{
		this.setPosts(Posts);
		for(Post p : Posts)
		{
			p.setUser(this);
		}
	}	
	
	// Comments of the user (on all posts)
	
	
	public Set<Comment> getComments() {
		return Comments;
	}
	public void setComments(Set<Comment> Comments) {
		this.Comments = Comments;
	}

	public void addCommentsOfThisUser(Set<Comment> Comments)
	{
		this.setComments(Comments);
		for(Comment c : Comments)
		{
			c.setCommentingUser(this);
		}
	}
	
  // Reactions of the user (on all posts)
	
	
	
	public Set<Reaction> getReactions() {
		return Reactions;
	}
	public void setReactions(Set<Reaction> Reactions) {
		this.Reactions = Reactions;
	}

	public void addReactsOfThisUser(Set<Reaction> Reactions)
	{
		this.setReactions(Reactions);
		for(Reaction r : Reactions)
		{
			r.setReactingUser(this);
		}
	}	
	
  // Messages sent by the user
	
	
	public Set<Message> getMessages() {
		return Messages;
	}

	public void setMessages(Set<Message> Messages) {
		this.Messages = Messages;
	}
	
    void addMessage(Message msg, boolean set) {
        if (msg != null) {
            getMessages().add(msg);
            if (set) {
                msg.setSender(this);
            }
        }
    }
     

    public void removeMessages(Message msg) {
        this.getMessages().remove(msg);
        msg.setSender(null);
    }   
    
    
 
}
