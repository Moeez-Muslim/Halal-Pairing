package businessLogic;

import java.util.HashMap;

import database.DatabaseHandlers.MatchDBhandler;

public class Match 
{
	private String CNIC;
    private HashMap<String, Double> matchMap;  // Handle new ones separately

    // Constructor
    public Match(String cnic) 
    {
    	this.CNIC = cnic;
        this.matchMap = MatchDBhandler.getMatchesByCNIC(cnic);
    }

    // Function to add a match to the map
    public void addMatch(String partnerCNIC, double similarityIndex) 
    {
        matchMap.put(partnerCNIC, similarityIndex);
        MatchDBhandler.addMatch(CNIC, partnerCNIC, similarityIndex);
    }
    

    // Getter for matchMap
    public HashMap<String, Double> getMatchMap() 
    {
        return matchMap;
    }
}
