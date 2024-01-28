package businessLogic;

import java.util.HashMap;
import java.util.Set;

import database.DatabaseHandlers.MatchDBhandler;
import database.DatabaseHandlers.UserDBhandler;


public class UserList 
{
    HashMap<String, User> users; // Using HashMap with CNIC as the key

    public UserList() 
    {
        users = UserDBhandler.getAllUsers();
    }

    // Function to add a user to the list
    public boolean addUser(User user) 
    {
        // Check if the user is already in the list
        if (!users.containsKey(user.getCNIC())) 
        {
            users.put(user.getCNIC(), user);
            UserDBhandler.addUser(user);
            return true; // User added successfully
        } 
        else 
        {
            return false; // User is already in the list
        }
    }

    // Function to remove a user from the list
    public boolean removeUser(String CNIC) 
    {
        // Check if the user is in the list before removing
        if (users.containsKey(CNIC) )
        {
            users.remove(CNIC);
            UserDBhandler.deleteUser(CNIC);
            return true; // User removed successfully
        } 
        else 
        {
            return false; // User not found in the list
        }
    }

    // Bridge function to call upgradeToPremium in User class
    public boolean upgradeUserToPremium(String userCNIC) 
    {
        User user = users.get(userCNIC);
        if (user instanceof User) 
        {
            return ((User) user).upgradeToPremium();
        }
        return false; // User not found or not an instance of User
    }

    public User getUser(String CNIC)
    {
    	return users.get(CNIC);
    }
    
    // Bridge function to call restrict in User class
    public void restrictUser(String userCNIC) 
    {
        User user = users.get(userCNIC);
        if (user instanceof User) 
        {
            ((User) user).restrict();
        }
        // No need to handle the case where user is not found because restrict method doesn't return anything
    }

    // Bridge function to call isRestricted in User class
    public boolean isUserRestricted(String userCNIC) 
    {
        User user = users.get(userCNIC);
        if (user instanceof User) 
        {
            return ((User) user).isRestricted();
        }
        return false; // User not found or not an instance of User
    }

    // Bridge function to call addComplain in User class
    public void addComplainToUser(String userCNIC, String complaineeCNIC, String complain) 
    {
        User user = users.get(userCNIC);
        if (user instanceof User) 
        {
            ((User) user).addComplain(complaineeCNIC ,complain);
        }
        // No need to handle the case where user is not found because addComplain method doesn't return anything
    }

    // Bridge function to call getWarningCount in User class
    public int getUserWarningCount(String userCNIC) 
    {
        User user = users.get(userCNIC);
        if (user instanceof User) 
        {
            return ((User) user).getWarningCount();
        }
        return 0; // User not found or not an instance of User
    }

    // Bridge function to call addWarning in User class
    public void addUserWarning(String userCNIC) 
    {
    	User user = users.get(userCNIC);
        if (user instanceof User) 
        {
            ((User) user).addWarning();
        }
        // No need to handle the case where user is not found because addWarning method doesn't return anything
    }

    // Bridge function to call addReview in User class
    public void addUserReview(String userCNIC, String giverName, String review) 
    {
    	User user = users.get(userCNIC);
        if (user instanceof User)
        {
            ((User) user).addReview(giverName, review);
        }
        // No need to handle the case where user is not found because addReview method doesn't return anything
    }

    // Bridge function to call getReview in User class
    public HashMap<String, String> getUserReviews(String userCNIC) 
    {
    	User user = users.get(userCNIC);
        if (user instanceof User) 
        {
            return ((User) user).getReviews();
        }
        return new HashMap<>(); // User not found or not an instance of User
    }

    // Bridge function to call addInterest in User class
    public void addUserInterest(String userCNIC, String interest) 
    {
    	User user = users.get(userCNIC);
        if (user instanceof User) 
        {
            ((User) user).addInterest(interest);
        }
        // No need to handle the case where user is not found because addInterest method doesn't return anything
    }

    // Bridge function to call removeInterest in User class
    public void removeUserInterest(String userCNIC, String interest) 
    {
    	User user = users.get(userCNIC);
        if (user instanceof User) 
        {
            ((User) user).removeInterest(interest);
        }
        // No need to handle the case where user is not found because removeInterest method doesn't return anything
    }

    // Bridge function to call addMatch in User class
    public void addUserMatch(String userCNIC, String partnerCNIC, double similarityIndex) 
    {
    	User user = users.get(userCNIC);
        if (user instanceof User) 
        {
            ((User) user).addMatch(partnerCNIC, similarityIndex);
            MatchDBhandler.addMatch(userCNIC, partnerCNIC, similarityIndex);
        }
        // No need to handle the case where user is not found because addMatch method doesn't return anything
    }

    // Bridge function to call getMatchMap in User class
    public HashMap<String, Double> getAllMatches(String userCNIC) 
    {
    	User user = users.get(userCNIC);
        if (user instanceof User) 
        {
            return ((User) user).getMatchMap();
        }
        return new HashMap<>(); // User not found or not an instance of User
    }
    
    public HashMap<String, User> getUsers()
    {
    	return users;
    }
    
 // Method to find and store matches for a user
    public void findAndStoreMatches(String cnic) 
    {
        User currentUser = users.get(cnic);

        if (currentUser != null) 
        {
            Set<String> currentUserInterests = currentUser.getAllInterests();

            // Iterate over other users
            for (User otherUser : users.values()) 
            {
                if (!otherUser.getCNIC().equals(cnic)) 
                {
                	System.out.println(otherUser.getGender() + '\n' + (currentUser.getGender()));
                	if(otherUser.getGender() != (currentUser.getGender()))
                	{
                		Set<String> otherUserInterests = otherUser.getAllInterests();

                        // Calculate similarity index
                        int commonInterests = countCommonInterests(currentUserInterests, otherUserInterests);
                        double similarityIndex = (double) commonInterests / 12.0;

                        // Add the match
                        if(commonInterests >= 1)
                    	{
                        	currentUser.addMatch(otherUser.getCNIC(), similarityIndex);
                        	currentUser.addReview(otherUser.getCNIC(), "");
                    	}
                	}
                }
            }
        }
    }

    // Helper method to count common interests
    private int countCommonInterests(Set<String> interests1, Set<String> interests2) 
    {
        int commonInterests = 0;
        for (String interest : interests1) 
        {
            if (interests2.contains(interest)) {
                commonInterests++;
            }
        }
        return commonInterests;
    }
    
    public Set<String> getAllInterests(String CNIC)
    {
    	return users.get(CNIC).getAllInterests();
    }
}
