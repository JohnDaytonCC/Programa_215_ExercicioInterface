package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;		// Declaração da variável que determina a relação de dependência com a classe OnlinePaymentService

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer months) {
		
		double quotaBasica = contract.getTotalValue() / months;
		
		for(int i=1; i <= months; i++) {
			Date date = addMonths(contract.getDate(), i);
			// Calculo do valor mensal i referente ao juros simples
			double updateQuota = quotaBasica + onlinePaymentService.interest(quotaBasica, i);
			// Calculo do valor mensal i referente ao juros de 2% por parcela.
			double fullQuota = updateQuota + onlinePaymentService.paymentFee(updateQuota);
			
			// Inclusão dos resultados no vetor Installment.
			// Installment installment = new Installment(date,fullQuota);
			// contract.addInstallment(installment);
			contract.addInstallment(new Installment(date, fullQuota));
		}
		
	}
	
	// Função que irá adicionar a quantidade de meses passado pela variável inteira n.
	private Date addMonths(Date date, int n) {
		Calendar cal = Calendar.getInstance();			// Declaração de uma variável para utilização de operações com datas.
		cal.setTime(date);								// Repassa a variável recebida pela função para a variável de operações com datas
		cal.add(Calendar.MONTH, n);						// Incremeta o valor de n meses à variável de entrada.
		return cal.getTime();							// Retorna o valor adicionado a quantidade de meses passado pela variável n.
	}
}
