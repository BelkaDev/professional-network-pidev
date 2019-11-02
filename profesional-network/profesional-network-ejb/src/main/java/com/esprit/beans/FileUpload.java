package com.esprit.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.esprit.enums.FILE_TYPE;

@Entity
public class FileUpload implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
    private String path;
    private FILE_TYPE type;
    
    
    
    public FileUpload() {
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

	@Enumerated(EnumType.STRING)
	public FILE_TYPE getType() {
		return type;
	}

	public void setType(FILE_TYPE type) {
		this.type = type;
	}
	
    @Column(name = "path", nullable = false)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}