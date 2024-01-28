package businessLogic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Content
{
	private String CNIC;
	private String picturePath;
	private String text;
	private Date uploadDate;
	
	public Content(String CNIC, String picturePath, String text, Date dateUploaded)
	{
		this.setCNIC(CNIC);
		this.setPicturePath(picturePath);
		this.setText(text);
		this.uploadDate = dateUploaded;
	}

	public String getCNIC()
	{
		return CNIC;
	}

	public void setCNIC(String cNIC)
	{
		CNIC = cNIC;
	}

	public String getPicturePath()
	{
		return picturePath;
	}

	public void setPicturePath(String picturePath)
	{
		this.picturePath = picturePath;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getUploadDateAsString() 
	{
        if (uploadDate != null) 
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(uploadDate);
        } 
        else 
        {
            return null; // or handle null case as needed
        }
    }

	public void setUploadDate(Date uploadDate)
	{
		this.uploadDate = uploadDate;
	}
}
