package businessLogic;


public class UserFactory 
{
	public static User createUser(String name, String cnic, int age, char gender, String pFpName)
	{
    	User U = new User(name, cnic, age, gender, pFpName);
    	
    	return U;
	}
}
