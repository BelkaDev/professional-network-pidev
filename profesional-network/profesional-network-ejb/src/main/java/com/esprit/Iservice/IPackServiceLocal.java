package com.esprit.Iservice;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import com.esprit.beans.Pack;

@Local
public interface IPackServiceLocal {
	public void ajouterPack(Pack p);
	public void ajouterReduction(Pack p,double reduction,Date debut,Date fin );
	public void modifierPack(Pack p);
	public void supprimerPack(Pack p);
	public List<Pack> afficherPacks();
	public Pack afficherPack(Pack p);
}
