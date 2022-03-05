package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		int number = input.nextInt();
		System.out.print("Date: ");
		input.nextLine();										// Consumo da quebra de linha pendente. 
		String stringDate = input.nextLine();					// Dado recebido como string
		Date date = sdf.parse(stringDate);						// Dados convertido para objeto do tipo Date.
		
		System.out.print("Contract Value: ");
		double totalValue = input.nextDouble();
		System.out.print("Enter number of installments: ");
		int meses = input.nextInt();
		
		//Installment installment = new Installment(date,totalValue);
		// Implementação dos dados coletados na classe Contract na variável contract
		Contract contract = new Contract(number,date,totalValue);
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, meses);	
		
		System.out.println("Installments:");
		for(Installment inst : contract.getInstallment()) {
			System.out.println(inst);
		}
		
		
		input.close();
	}

}
