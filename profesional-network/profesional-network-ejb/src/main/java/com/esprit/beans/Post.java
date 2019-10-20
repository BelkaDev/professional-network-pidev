package com.esprit.beans;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.esprit.enums.Posts;
import com.esprit.enums.Reactions;



@Entity
public class Post implements Serializable { 
	

	private int id;
    private String content;
    private Timestamp date;
    private User user;
    private Posts type;
	public List<Comment> Comments;
	public List<Reaction> Reactions;
	
     
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


    
    @Column(name = "content", nullable = false, length = 200)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	@Enumerated(EnumType.STRING)
	public Posts getType() {
		return type;
	}

	public void setType(Posts type) {
		this.type = type;
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
    
	@OneToMany(mappedBy = "commentedPost")
	public List<Comment> getComments() {
		return Comments;
	}
	public void setComments(List<Comment> Comments) {
		this.Comments = Comments;
	}

	public void addCommentsToThisPost(List<Comment> Comments)
	{
		this.setComments(Comments);
		for(Comment c : Comments)
		{
			c.setCommentedPost(this);
		}
	}
	
	
	// Reactions on the post
	
	@OneToMany(mappedBy = "reactedPost")
	public List<Reaction> getReactions() {
		return Reactions;
	}
	public void setReactions(List<Reaction> Reactions) {
		this.Reactions = Reactions;
	}

	public void addReactsToThisPost(List<Reaction> Reactions)
	{
		this.setReactions(Reactions);
		for(Reaction r : Reactions)
		{
			r.setReactedPost(this);
		}
	}
    
}
