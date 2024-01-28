package businessLogic;

import java.util.Date;

public class MicroFinance extends Payment
{
	private String method = "Microfinance";
	
	public MicroFinance(double amount, Date transactionTime)
	{
		super(amount, transactionTime);
	}

	@Override
	public String getPaymentMethod()
	{
		return method;
	}

}
