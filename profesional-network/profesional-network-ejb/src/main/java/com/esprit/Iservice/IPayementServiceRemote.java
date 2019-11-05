package com.esprit.Iservice;

import java.sql.Date;
import java.util.List;

import com.esprit.beans.Payement;

public interface IPayementServiceRemote {

	public boolean payPack(int idPack,String numCard,int cvv,Date ExpirationDate);
	public boolean cancelPayment(int idPayment);
	public List<Payement>  consultYourPaymentDetails();
	
	
	//admin
	public List<Payement> ConsultPayments();
	public boolean ValidateCanceledPayment(int idP);
	public boolean validatePayment(int idP);
	public boolean removePayment(int id);
	public boolean numCardvalid(String num);
	public boolean isPremium(int userId);
}
