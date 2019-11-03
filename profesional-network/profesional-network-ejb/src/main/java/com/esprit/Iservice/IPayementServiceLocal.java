package com.esprit.Iservice;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import com.esprit.beans.Payement;
import com.esprit.beans.UserPack;

@Local
public interface IPayementServiceLocal {
	
	//Company and condidate 
	public boolean payPack(int idPack,String numCard,int cvv,Date expirationDate);
	public void cancelPayment(int idPayment);
	public List<Payement>  consultYourPaymentDetails();
	
	
	
	//admin
	public List<Payement> ConsultPayments();
	public void ValidateCanceledPayment(int idP);
	public void validatePayment(int idP);
	public boolean removePayment(int id);
	

}
