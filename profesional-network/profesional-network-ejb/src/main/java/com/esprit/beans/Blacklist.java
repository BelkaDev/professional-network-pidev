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

import com.esprit.enums.NOTIFICATION_TARGET;
import com.esprit.enums.POST_TYPE;
import com.esprit.enums.POST_TYPE;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Blacklist implements Serializable { 
	
 
	private static final long serialVersionUID = 1L;
	
	private int id;
	private User blocking;
	private int blockedId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "blockedId" )
	public int getBlockedId() {
		return blockedId;
	}
	public void setBlockedId(int blockedId) {
		this.blockedId = blockedId;
	}
	
			/* relations */
		
		@JoinColumn(name = "blocking", referencedColumnName = "id")
		@ManyToOne(fetch=FetchType.EAGER)
		public User getBlocking() {
		return blocking;
		}
		
		public void setBlocking(User blocking) {
		this.blocking = blocking;
		}


}
