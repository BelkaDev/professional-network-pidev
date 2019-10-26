package com.esprit.beans;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
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
import javax.persistence.OneToOne;

import com.esprit.enums.Reactions;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Reaction implements Serializable { 
	
    
	private int id;
    private Timestamp date;
    private Reactions type;
    private Post reactedPost;
    private User reactingUser;
     
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

	@Enumerated(EnumType.STRING)
	public Reactions getType() {
		return type;
	}

	public void setType(Reactions type) {
		this.type = type;
	}



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reaction reaction = (Reaction) o;
                return id == reaction.id &&
                Objects.equals(date, reaction.date) &&
        		Objects.equals(type, reaction.type); // check to compare 2 enums  
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, id, type);
    }
    
    
    
    // #----------------------Relations----------------------#
    
    
    // The post of the reaction
    
	@ManyToOne
	@JoinColumn(name = "postId" , referencedColumnName = "id")
	public Post getReactedPost() {
		return reactedPost;
	}

	public void setReactedPost(Post reactedPost) {
		this.reactedPost = reactedPost;
	}
	
	// The user who reacted
	
	@ManyToOne
	@JoinColumn(name = "userId" , referencedColumnName = "id")
	@JsonBackReference
	public User getReactingUser() {
		return reactingUser;
	}

	public void setReactingUser(User reactingUser) {
		this.reactingUser = reactingUser;
	}
    
}
