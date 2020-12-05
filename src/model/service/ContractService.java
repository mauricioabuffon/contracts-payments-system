package model.service;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnLinePaymentService onLinePaymentService;

	public ContractService(OnLinePaymentService onLinePaymentService) {
		this.onLinePaymentService = onLinePaymentService;
	}

	public void processContract(Contract contract, int months) {
		double basicQuote = contract.getContractValue() / months;
		// give the results to payment monthly
		for (int i = 1; i <= months; i++) {
			double updatedQuote = basicQuote + onLinePaymentService.interest(basicQuote, i);
			double fullQuote = updatedQuote + onLinePaymentService.paymentFee(updatedQuote);
	
			Date dueDate = addMonths(contract.getDate(), i);
			contract.getInstallments().add(new Installment(dueDate, fullQuote));
		}
		
	}
	private Date addMonths(Date date, int N) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, N);
		return calendar.getTime();
	}
}
