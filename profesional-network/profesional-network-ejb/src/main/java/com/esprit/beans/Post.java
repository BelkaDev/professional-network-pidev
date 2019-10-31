package com.esprit.beans;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Set;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import com.esprit.enums.POST_TYPE;
import com.esprit.enums.POST_TYPE;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity

public class Post implements Serializable { 
	
    
	private int id;
    private String content;
    private Timestamp date;
    @JsonIgnoreProperties({"whoclaims","messages","comments","reactions"})
    private User user;
    private POST_TYPE type;
    @JsonIgnore
	public Set<Comment> Comments;
    @JsonIgnore
	public Set<Reaction> Reactions;
	
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    
    
    
 // #----------------------Relations----------------------#
    
    
    // User who posted the post
    
	
 	@ManyToOne
 	@JoinColumn(name = "userId" , referencedColumnName = "id")
 	public User getUser() {
 		return user;
 	}

 	public void setUser(User user) {
 		this.user = user;
 	}
     
 	
 	// Comments on the post
 	
 	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "commentedPost",fetch = FetchType.EAGER)
	public Set<Comment> getComments() {
		return Comments;
	}
	public void setComments(Set<Comment> Comments) {
		this.Comments = Comments;
	}

	public void addCommentsToThisPost(Set<Comment> Comments)
	{
		this.setComments(Comments);
		for(Comment c : Comments)
		{
			c.setCommentedPost(this);
		}
	}
	
	
	// Reactions on the post
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "reactedPost",fetch = FetchType.EAGER)
	public Set<Reaction> getReactions() {
		return Reactions;
	}
	public void setReactions(Set<Reaction> Reactions) {
		this.Reactions = Reactions;
	}

	public void addReactsToThisPost(Set<Reaction> Reactions)
	{
		this.setReactions(Reactions);
		for(Reaction r : Reactions)
		{
			r.setReactedPost(this);
		}
	}
    
    
    @Column(name = "content", nullable = false, length = 200)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	@Enumerated(EnumType.STRING)
	public POST_TYPE getType() {
		return type;
	}

	public void setType(POST_TYPE type) {
		this.type = type;
	}
	
	public Post() {
		super();
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post that = (Post) o;
        return id == that.id &&
                Objects.equals(content, that.content) &&
                Objects.equals(date, that.date) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, date,type);
    }

}
