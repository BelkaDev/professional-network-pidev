package com.esprit.beans;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Message implements Serializable { 

    
	private int id;
    private Timestamp date;
	private User sender;
    private int recipient;
    private String body;

    
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


    @Column(name = "recipient", nullable = false)
    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    
    @Column(name = "body", nullable = false, length = 1000)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

	@Override
	public String toString() {
		return "Message [id=" + id + ", date=" + date + ", body=" + body
				+ ", recipient=" + recipient + "]";
	}

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                sender == message.sender &&
                recipient == message.recipient &&
                Objects.equals(date, message.date) &&
                Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, sender, recipient, body);
    }
    
    
    
    // #----------------------Relations----------------------#
    

	@JoinColumn(name = "User", referencedColumnName = "id")
	@ManyToOne(fetch=FetchType.EAGER)
	
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
}
