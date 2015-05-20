package QuoteGen;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.*;

public class tweetFunctions {
	
	@SuppressWarnings("deprecation")
	public static void getFavourites(int numberAgo)  {

		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("R0rX5c2rsDHA6BMK0b7CAegGR")
		  .setOAuthConsumerSecret("mrFMBzujuBBro9ArebUPnIu523t4yMUSc3j4AET5Eqm73b2KYb")
		  .setOAuthAccessToken("3189101448-XrTbHc6clsq2OoTJYnB6x47r1TJ1t7IOnV4R6h1")
		  .setOAuthAccessTokenSecret("5mvjOPMxjKx4OsC4xdPyuz1Q6mqGAmnwSLL2xGbTYL9iI")
		  .setJSONStoreEnabled(true);
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		try {
		    Paging paging = new Paging(numberAgo, numberAgo);
		    ResponseList<Status> statuses = twitter.getHomeTimeline(paging);

		    String strTweets = DataObjectFactory.getRawJSON(statuses);
		    
		    strTweets = strTweets.substring(strTweets.indexOf("favourites_count"));
		    
		    strTweets = strTweets.substring(strTweets.indexOf(":")+1);
		    
		    strTweets = strTweets.substring(0, strTweets.indexOf(","));
		    
		    System.out.println(strTweets);
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}

	}
	
	public static void tweet(String toTweet)
	{
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("R0rX5c2rsDHA6BMK0b7CAegGR")
		  .setOAuthConsumerSecret("mrFMBzujuBBro9ArebUPnIu523t4yMUSc3j4AET5Eqm73b2KYb")
		  .setOAuthAccessToken("3189101448-XrTbHc6clsq2OoTJYnB6x47r1TJ1t7IOnV4R6h1")
		  .setOAuthAccessTokenSecret("5mvjOPMxjKx4OsC4xdPyuz1Q6mqGAmnwSLL2xGbTYL9iI")
		  .setJSONStoreEnabled(true);
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		try
		{
			Status status = twitter.updateStatus(toTweet);
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
