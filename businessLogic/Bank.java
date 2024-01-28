package businessLogic;

import java.util.LinkedList;

public class Bank
{
	LinkedList<Payment> paymentRecord;
	
	public Bank()
	{
		paymentRecord = new LinkedList<Payment>();
		
		// Load from databse
	}
	
	public boolean approvePayment(CreditCard C)
	{
		paymentRecord.add(C);
		// Update in database
		return true;
	}
	
	public boolean approvePayment(MicroFinance M)
	{
		paymentRecord.add(M);
		// Update in database
		return true;
	}
	
}
