package model.service;

public class PayPalService implements OnLinePaymentService{

	public PayPalService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double paymentFee(double amount) {
		// TODO Auto-generated method stub
		return  amount * 0.02;
	}

	@Override
	public double interest(double amount, int months) {
		// TODO Auto-generated method stub
		return amount * 0.01 * months;
	}


}
