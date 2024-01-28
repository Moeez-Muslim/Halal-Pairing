package businessLogic;

import java.util.HashMap;

import database.DatabaseHandlers.FeedbackDBhandler;

public class Feedback 
{
	private String CNIC;
    private HashMap<String, String> feedbacks;

    // Constructor
    public Feedback(String cnic) 
    {
    	this.CNIC = cnic;
        this.feedbacks = FeedbackDBhandler.getReviewsByCNIC(cnic);
    }

    // Add a review to the feedbackMap
    public void addReview(String giverCNIC, String review) 
    {
    	if(feedbacks.containsKey(giverCNIC))
    	{
    		feedbacks.remove(giverCNIC);
    		feedbacks.put(giverCNIC, review);
    		FeedbackDBhandler.updateReview(CNIC, giverCNIC, review);
    	}
    	else 
    	{
    		feedbacks.put(giverCNIC, review);
            FeedbackDBhandler.addReview(this.CNIC, giverCNIC, review);
		}
    }

    // Get a review based on giverName
    public HashMap<String, String> getReviews() 
    {
        return feedbacks;
    }
}

