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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import com.esprit.enums.NOTIFICATION_TARGET;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Notification implements Serializable { 
    
	private static final long serialVersionUID = 1L;
	private Integer id;	
    private String message;
    private User reciever;
    @AssertFalse
    private boolean seen;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd,HH:00", timezone="CET")
    private Timestamp date;
    private Integer targetId;
	private NOTIFICATION_TARGET target;

    
	public Notification() {
    	super();
    }
    @Override
	public String toString() {
		return "Notification [id=" + id + ", message=" + message + "]";
	}

    

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    @Column(name = "message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}    
    @Column(name = "seen")
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	

    @Column(name = "date")
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	

    @Column(name = "targetId" )
    public Integer getTargetId() {
		return targetId;
	}
	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}
	
	
	@Enumerated(EnumType.STRING)
	public NOTIFICATION_TARGET getTarget() {
		return target;
	}
	public void setTarget(NOTIFICATION_TARGET target) {
		this.target = target;
	}
    
			/* relations */

	@JoinColumn(name = "Reciever", referencedColumnName = "id")
	@ManyToOne(fetch=FetchType.EAGER)
	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}
	

}
