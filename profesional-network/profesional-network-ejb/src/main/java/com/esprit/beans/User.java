package com.esprit.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.esprit.enums.Gender;
import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable {
	
    private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
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

  @OneToMany(mappedBy = "user")
	private List<Post> Posts;
  @OneToMany(mappedBy = "commentingUser")
	private List<Comment> Comments;
  @OneToMany(mappedBy = "reactingUser")
	private List<Reaction> Reactions;
  @OneToMany(mappedBy="sender",fetch = FetchType.LAZY)
	private List<Message> Messages;
  @OneToMany(cascade = CascadeType.ALL, mappedBy="whoClaim")
  private Set<Claim> Whoclaims;
  @OneToMany(cascade = CascadeType.ALL, mappedBy="claimsOn")
  private Set<Claim> claimsOn;
  @ManyToOne
	private Pack pack; 


	//****************************
	
	

	// TODO : add constructor, equals, toString

	public User(Integer idUser) {
		super();
		this.id = idUser;
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

	public Pack getPack() {
		return pack;
	}

	public void setPack(Pack pack) {
		this.pack = pack;
	}

	public Set<Claim> getWhoclaims() {
		return Whoclaims;
	}

	public void setWhoclaims(Set<Claim> whoclaims) {
		Whoclaims = whoclaims;
	}

	public Set<Claim> getClaimsOn() {
		return claimsOn;
	}

	public void setClaimsOn(Set<Claim> claimsOn) {
		this.claimsOn = claimsOn;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idUser) {
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
    
	@Enumerated(EnumType.STRING)
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
	
	
	public List<Post> getPosts() {
		return Posts;
	}
	public void setPosts(List<Post> Posts) {
		this.Posts = Posts;
	}

	public void addPostsOfThisUser(List<Post> Posts)
	{
		this.setPosts(Posts);
		for(Post p : Posts)
		{
			p.setUser(this);
		}
	}	
	
	// Comments of the user (on all posts)
	
	
	public List<Comment> getComments() {
		return Comments;
	}
	public void setComments(List<Comment> Comments) {
		this.Comments = Comments;
	}

	public void addCommentsOfThisUser(List<Comment> Comments)
	{
		this.setComments(Comments);
		for(Comment c : Comments)
		{
			c.setCommentingUser(this);
		}
	}
	
  // Reactions of the user (on all posts)
	
	
	
	public List<Reaction> getReactions() {
		return Reactions;
	}
	public void setReactions(List<Reaction> Reactions) {
		this.Reactions = Reactions;
	}

	public void addReactsOfThisUser(List<Reaction> Reactions)
	{
		this.setReactions(Reactions);
		for(Reaction r : Reactions)
		{
			r.setReactingUser(this);
		}
	}	
	
  // Messages sent by the user
	
	
	public List<Message> getMessages() {
		return Messages;
	}

	public void setMessages(List<Message> Messages) {
		this.Messages = Messages;
	}

}
