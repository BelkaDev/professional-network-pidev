package com.esprit.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.Set;

import com.esprit.enums.Gender;
import com.esprit.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import javax.validation.constraints.AssertFalse;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Email
	private String email;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "password")
	private String password;
	@AssertFalse
	@Column(name = "recieveMailNotifs")
	private boolean recieveMailNotifs;
	@Enumerated(EnumType.STRING)
	private Gender gender;

	private Date birthDate;
	@Column(name = "enable")
	public boolean enable;
	@Column(name = "confirm")
	private String confirm;
	// address is complex type
	private Address address;
	
	@Column(name = "username")
	private String username;
	@Column(name = "token")
	private String token;
	@Enumerated(EnumType.STRING)
	@Column(name="role")
    private Role role;
	
	// ****************************

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Post> Posts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commentingUser", fetch = FetchType.EAGER)
	private Set<Comment> Comments;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "reactingUser", fetch = FetchType.EAGER)
	private Set<Reaction> Reactions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.EAGER)
	private Set<Message> Messages;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "reciever", fetch = FetchType.EAGER)
	private Set<Notification> Notifications;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "whoClaim")
	private Set<Claim> Whoclaim;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "claimsOn", fetch = FetchType.EAGER)
	private Set<Claim> claimOn;
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserPack> packs;

	// ****************************

	public User(int idUser) {
		super();
		this.id = idUser;
		this.gender = Gender.Other;
	}

	
	public User(String email, String firstName, String lastName, String password, Gender gender, Date birthDate,
			Address address, String username, Role role) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.gender = gender;
		this.birthDate = birthDate;
	
		this.address = address;
		this.username = username;
		
		this.role = role;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<Notification> getNotifications() {
		return Notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		Notifications = notifications;
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
	public Address getAddress() {
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

	public void addPostsOfThisUser(Set<Post> Posts) {
		this.setPosts(Posts);
		for (Post p : Posts) {
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

	public void addCommentsOfThisUser(Set<Comment> Comments) {
		this.setComments(Comments);
		for (Comment c : Comments) {
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

	public void addReactsOfThisUser(Set<Reaction> Reactions) {
		this.setReactions(Reactions);
		for (Reaction r : Reactions) {
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

	public boolean getRecieveMailNotifs() {
		return recieveMailNotifs;
	}

	public void setRecieveMailNotifs(boolean recieveMailNotifs) {
		this.recieveMailNotifs = recieveMailNotifs;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
