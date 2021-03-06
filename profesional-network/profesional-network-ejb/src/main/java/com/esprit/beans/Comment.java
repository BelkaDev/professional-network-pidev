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


import com.esprit.enums.POST_TYPE;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Comment implements Serializable { 
    
	
	private int id;
    private String content;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd,HH:00", timezone="CET")
    private Timestamp date;
    @JsonIgnore
    private Post commentedPost;
    @JsonIgnoreProperties({"whoclaims","messages","comments","reactions","password"})
    private User commentingUser;
    
    public Comment() {
    	super();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment that = (Comment) o;
        return id == that.id &&
                Objects.equals(content, that.content) &&
                Objects.equals(date, that.date) &&
                Objects.equals(commentedPost, that.commentedPost) && 
                Objects.equals(commentingUser, that.commentingUser) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, date, commentedPost, commentingUser);
    }
     
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
    
    // #----------------------Relations----------------------#
    
    // The post of the comment
    
	@ManyToOne
	@JoinColumn(name = "post" , referencedColumnName = "id")
	public Post getCommentedPost() {
		return commentedPost;
	}

	public void setCommentedPost(Post commentedPost) {
		this.commentedPost = commentedPost;
	}
    
	
	// The user who posted the comment
	
	@ManyToOne
	@JoinColumn(name = "commenter" , referencedColumnName = "id")
	public User getCommentingUser() {
		return commentingUser;
	}

	public void setCommentingUser(User commentingUser) {
		this.commentingUser = commentingUser;
	}       
    
    
}
