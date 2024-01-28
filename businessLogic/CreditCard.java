package businessLogic;

import java.util.Date;

public class CreditCard extends Payment
{
	private String method = "Credit Card";
	
	public CreditCard(double amount, Date transactionTime)
	{
		super(amount, transactionTime);
	}
	
	@Override
	public String getPaymentMethod()
	{
		return method;
	}
}
