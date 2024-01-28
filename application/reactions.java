package application;

public enum reactions 
{
	NON(0,"Like",database.DatabaseHandlers.Path.imagesPath + "imgNoLike.png"),
	LIKE(1,"Like",database.DatabaseHandlers.Path.imagesPath + "imgLike.png"),
	LOVE(2,"Love",database.DatabaseHandlers.Path.imagesPath + "imgLove.png"),
	CARE(3,"Care",database.DatabaseHandlers.Path.imagesPath + "imgCare.png"),
	HAHA(4,"Haha",database.DatabaseHandlers.Path.imagesPath + "imgHaha.png"),
	WOW(5,"Wow",database.DatabaseHandlers.Path.imagesPath + "imgWow.png"),
	SAD(6,"Sad",database.DatabaseHandlers.Path.imagesPath + "imgSad.png");
	
	public int id;
	public String name;
	public String imgSrc;
	
	reactions(int id, String name, String imgSrc)
	{
		this.id = id;
		this.name = name;
		this.imgSrc = imgSrc;
				
	}
	
	void setReaction()
	{
		
	}
	
}
