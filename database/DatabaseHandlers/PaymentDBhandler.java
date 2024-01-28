package database.DatabaseHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import businessLogic.*;

public class PaymentDBhandler 
{
    // Method to add a payment to the database
    public static void addPayment(String cnic, Payment payment) 
    {
        String query = "INSERT INTO PAYMENTS (CNIC, AMOUNT, DATE, METHOD) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            preparedStatement.setDouble(2, payment.getAmount());
            preparedStatement.setString(3, payment.getTimeInString());
            preparedStatement.setString(4, payment.getPaymentMethod());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + "row(s) affected: Add Payment");
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to get payments for a given CNIC
    public static List<Payment> getPaymentsByCNIC(String cnic) 
    {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT AMOUNT, DATE, METHOD FROM PAYMENTS WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    double amount = resultSet.getDouble("AMOUNT");
                    Date transactionTime = resultSet.getTimestamp("DATE");
                    String method = resultSet.getString("METHOD");

                    // Create CreditCard or MicroFinance object based on the payment method
                    Payment payment;
                    if ("Credit Card".equals(method)) 
                    {
                        payment = new CreditCard(amount, transactionTime);
                    } 
                    else if ("Microfinance".equals(method)) 
                    {
                        payment = new MicroFinance(amount, transactionTime);
                    } 
                    else 
                    {
                        // Handle other payment methods if needed
                        continue;
                    }

                    payments.add(payment);
                }
                
                System.out.println(payments.size() + "row(s) returned: Get Payments for " + cnic);
                resultSet.close();
            }
            
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return payments;
    }

    private static Connection getConnection() throws SQLException 
    {
        String jdbcUrl = "jdbc:mysql://localhost:3306/HALAL_PAIRING";
        String user = "root";
        String password = Password.DBpassword;
        return DriverManager.getConnection(jdbcUrl, user, password);
    }
}
