package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;
import model.service.PayPalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("Enter data:");
		System.out.print("Contract number: ");
		int contractNumber = sc.nextInt();
		System.out.print("Date start: (dd/MM/yyyy)");
		Date dateStart = sdf.parse(sc.next());
		System.out.print("Total value: ");
		double totalValue = sc.nextDouble();
		// Instancing new contract
		Contract contract = new Contract(contractNumber, dateStart, totalValue);

		System.out.print("Months number: ");
		int monthsNumber = sc.nextInt();
		// Instancing Contract service to PayPal
		ContractService contractService = new ContractService(new PayPalService());

		// Calculating Fees and interest month-to-month
		// Writing installments month-to-month 
		contractService.processContract(contract, monthsNumber);

		System.out.println("Installments: ");

		// reading Installment list of a contract and printing results
		for (Installment it : contract.getInstallments()) {
			System.out.println(it);
		}
		sc.close();
	}

}
