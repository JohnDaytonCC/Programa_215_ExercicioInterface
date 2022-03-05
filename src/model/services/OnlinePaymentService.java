package model.services;

// Interface entre as classes ContractService e PaypalService
public interface OnlinePaymentService {
	
	Double paymentFee(double amount);
	Double interest(double amount, Integer months);

}
