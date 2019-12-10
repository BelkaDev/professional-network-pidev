package com.esprit.Iservice;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import com.esprit.beans.Pack;
import com.esprit.beans.User;
import com.esprit.beans.UserPack;
import com.esprit.enums.PackType;

@Local
public interface IPackServiceLocal {
	public void addPack(String titre,String description,double prix,PackType type);
	public void addReduction(int id,double reduction,Date debut,Date fin );
	public void updatePack(int id,String titre,PackType type,double prix,Date datedebut,Date datefin,double reduction );
	public void deletePack(int id);
	public List<Pack> allpacks();
	public Pack getPack(int id);
	public void addPackToPayIt(int packId);
	public Pack findPackById(int id);
	public void bonusPack(UserPack up);
	public List<UserPack> getUsersByPack(int id);
	
	
}
