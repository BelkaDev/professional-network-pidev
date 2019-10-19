package com.esprit.beans;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.esprit.enums.Relations;



@Entity
public class Relation implements Serializable { 
    
	private int id;
    private long userid;
    private long friendid;
    private Timestamp date;
    private Relations type;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "userid", nullable = false)
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }


    @Column(name = "friendid", nullable = false)
    public long getFriendid() {
        return friendid;
    }

    public void setFriendid(long friendid) {
        this.friendid = friendid;
    }
    
	@Column(name = "date")
	public Timestamp getTimestamp() {
		return date;
	}

	
	public void setTimestamp(Timestamp date) {
		this.date = date;
	}
	
	@Enumerated(EnumType.STRING)
	public Relations getType() {
		return type;
	}

	public void setType(Relations type) {
		this.type = type;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation = (Relation) o;
        return id == relation.id &&
                userid == relation.userid &&
                friendid == relation.friendid &&
                Objects.equals(date, relation.date) &&
                Objects.equals(type, relation.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, friendid,date,type);
    }
}
