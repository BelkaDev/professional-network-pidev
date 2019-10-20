package com.esprit.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.esprit.enums.Gender;
import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable {
	
    private static final long serialVersionUID = 1L;	
	private Integer id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
    private Gender gender;
	private Date birthDate;
	
	//****************************

	private List<Post> Posts;
	private List<Comment> Comments;
	private List<Reaction> Reactions;
	private List<Message> Messages;

	//****************************
	
	

	// TODO : add constructor, equals, toString

	public User(Integer idUser) {
		super();
		this.id = id;
	}
	
	public User() {
		super();
	}

	@Id
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

  // #----------------------Relations----------------------#

	
  // Posts of the user
	
	@OneToMany(mappedBy = "user")
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
	
	@OneToMany(mappedBy = "commentingUser")
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
	
	
	@OneToMany(mappedBy = "reactingUser")
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
	
	@OneToMany(mappedBy="sender",fetch = FetchType.LAZY)
	public List<Message> getMessages() {
		return Messages;
	}

	public void setMessages(List<Message> Messages) {
		this.Messages = Messages;
	}

}
