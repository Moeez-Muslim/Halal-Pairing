package businessLogic;

public class Message 
{
    private String senderName;
    private String receiverName;
    private String content;
    private String timeSent;

    public Message(String senderName, String receiverName, String content, String timeSent) 
    {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.content = content;
        this.timeSent = timeSent;
    }

    public String getSenderName() 
    {
        return senderName;
    }

    public void setSenderName(String senderName) 
    {
        this.senderName = senderName;
    }

    public String getReceiverName() 
    {
        return receiverName;
    }

    public void setReceiverName(String receiverName) 
    {
        this.receiverName = receiverName;
    }

    public String getContent() 
    {
        return content;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getTimeSent() 
    {
        return timeSent;
    }

    public void setTimeSent(String timeSent) 
    {
        this.timeSent = timeSent;
    }
}
