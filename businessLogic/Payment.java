package businessLogic;

import java.util.Date;
import java.text.SimpleDateFormat;

public abstract class Payment
{
	private double amount;
	Date transactionTime;
	// Define the desired date-time format
    String pattern = "yyyy-MM-dd HH:mm:ss";
	
	public Payment(double amount, Date transactionTime)
	{
		this.amount = amount;
		this.transactionTime = transactionTime;
	}
	
	public double getAmount()
	{
		return this.amount;
	}
	
	public String getTimeInString()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        // Convert the Date object to a formatted string
        return dateFormat.format(transactionTime);
	}
	
	public abstract String getPaymentMethod();
}
