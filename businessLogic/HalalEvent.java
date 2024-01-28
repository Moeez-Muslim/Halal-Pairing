package businessLogic;

import java.util.Date;

public class HalalEvent 
{
    private String venue;
    private String Title;
    private String Description;
    private Date eventDate;
    private int capacity;
    private User organizer;
    private char gender;

    // Parameterized constructor
    public HalalEvent(String Title, String description, char gender, String venue, Date eventDate, int capacity, User organizer) 
    {
    	this.Title = Title;
    	this.Description = description;
    	this. gender = gender;
        this.venue = venue;
        this.eventDate = eventDate;
        this.capacity = capacity;
        this.organizer = organizer;
    }

    // Getter for venue
    public String getVenue() 
    {
        return venue;
    }

    // Setter for venue
    public void setVenue(String venue) 
    {
        this.venue = venue;
    }

    // Getter for eventDate
    public Date getEventDate() 
    {
        return eventDate;
    }

    // Setter for eventDate
    public void setEventDate(Date eventDate) 
    {
        this.eventDate = eventDate;
    }

    // Getter for capacity
    public int getCapacity() 
    {
        return capacity;
    }

    // Setter for capacity
    public void setCapacity(int capacity) 
    {
        this.capacity = capacity;
    }

    // Getter for organizer
    public User getOrganizer() 
    {
        return organizer;
    }

    // Setter for organizer
    public void setOrganizer(User organizer) 
    {
        this.organizer = organizer;
    }

	public String getTitle()
	{
		return Title;
	}

	public void setTitle(String title)
	{
		Title = title;
	}

	public String getDescription()
	{
		return Description;
	}

	public void setDescription(String description)
	{
		Description = description;
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


