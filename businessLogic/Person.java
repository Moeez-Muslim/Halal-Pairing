package businessLogic;

public abstract class Person 
{
    protected String name;
    protected String cnic; 
    protected int age;
    protected char gender;
    
    // Default constructor
    public Person() 
    {
    	
    }

    // Parameterized constructor
    public Person(String name, String cnic, int age, char gender) 
    {
        this.name = name;
        this.cnic = cnic;
        this.age = age;
        this.setGender(gender);
    }

    // Getter for name
    public String getName() 
    {
        return name;
    }

    // Setter for name
    public void setName(String name) 
    {
        this.name = name;
    }

    // Getter for CNIC
    public String getCNIC() 
    {
        return cnic;
    }

    // Setter for CNIC
    public void setCnic(String cnic) 
    {
        this.cnic = cnic;
    }

    // Getter for age
    public int getAge() 
    {
        return age;
    }

    // Setter for age
    public void setAge(int age) 
    {
        this.age = age;
    }

	public char getGender()
	{
		return gender;
	}

	public void setGender(char gender)
	{
		this.gender = gender;
	}
    
}

