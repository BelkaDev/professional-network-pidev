package com.esprit.services;


import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IFileServiceLocal;
import com.esprit.Iservice.IFileServiceRemote;
import com.esprit.beans.FileUpload;
import com.esprit.beans.Post;
import com.esprit.beans.User;
import com.esprit.enums.FILE_TYPE;
import com.esprit.enums.NOTIFICATION_TYPE;


@Stateless
@LocalBean
public class FileService implements IFileServiceLocal,IFileServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	

	@Override
	public boolean addFile(FileUpload file) {;
		em.persist(file);
		return true;
	}



}
