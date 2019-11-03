package com.esprit.Iservice;

import java.sql.Date;
import java.util.List;

import com.esprit.beans.Payement;

public interface IPayementServiceRemote {

	public boolean payPack(int idPack,String numCard,int cvv,Date ExpirationDate);
	public void cancelPayment(int idPayment);
	public List<Payement>  consultYourPaymentDetails();
	
	
	//admin
	public List<Payement> ConsultPayments();
	public void ValidateCanceledPayment(int idP);
	public void validatePayment(int idP);
	public boolean removePayment(int id);
}
