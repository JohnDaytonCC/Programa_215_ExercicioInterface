package model.services;

public class PaypalService implements OnlinePaymentService {
	
	private static final double FEE_PERCENTAGE = 0.02;
	private static final double MONTHLY_INTEREST = 0.01;
	
	// Função que irá determinar o valor da taxa de 2% a cada parcela.
	@Override
	public Double paymentFee(double amount) {
		return amount * FEE_PERCENTAGE;
	}
	
	// Função que irá efetuar o calculo juros simples de 1% cada parcela do pagamento
	@Override
	public Double interest(double amount, Integer months) {
		return amount * MONTHLY_INTEREST * months;
	}

}
