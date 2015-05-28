package QuoteGen.twitter;

import java.util.HashMap;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.*;

@SuppressWarnings("deprecation");

public class TweetFunctions {
	
	public static String tweetsJSON;
	
	public static int numTweets;
	
	public static final long UserID = 3189101448l;

//	private static ConfigurationBuilder getCB() {
//		ConfigurationBuilder cb = new ConfigurationBuilder();
//		cb.setDebugEnabled(true)
//		  .setOAuthConsumerKey("R0rX5c2rsDHA6BMK0b7CAegGR")
//		  .setOAuthConsumerSecret("mrFMBzujuBBro9ArebUPnIu523t4yMUSc3j4AET5Eqm73b2KYb")
//		  .setOAuthAccessToken("3189101448-XrTbHc6clsq2OoTJYnB6x47r1TJ1t7IOnV4R6h1")
//		  .setOAuthAccessTokenSecret("5mvjOPMxjKx4OsC4xdPyuz1Q6mqGAmnwSLL2xGbTYL9iI")
//		  .setJSONStoreEnabled(true);
//		
//		return cb;
//	}

//	public static String getTweetsJSON()
//	{
//		ConfigurationBuilder cb = getCB();
//		
//		TwitterFactory tf = new TwitterFactory(cb.build());
//		Twitter twitter = tf.getInstance();
//		
//		String tweetsJSON = null;
//		
//		int numTweets;
//		
//		try {
//			
//			numTweets = twitter.showUser(UserID).getStatusesCount();
//			
//		    Paging paging = new Paging(1, numTweets);
//		    
//		    ResponseList<Status> statuses = twitter.getHomeTimeline(paging);
//
//		    tweetsJSON = DataObjectFactory.getRawJSON(statuses);
//		    
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return tweetsJSON;
//	}
	
	public static HashMap <String, Integer> getTweetHashMap(String tweetsJSON) {
		HashMap <String, Integer> hashMap = new HashMap<String, Integer>();
		
		int startPos = 0;
		
		while (startPos > -1) {
			startPos = tweetsJSON.indexOf("\"text\":");
			
			int endPos = tweetsJSON.indexOf(",\"geo\"");
			
			if (startPos < 0) {
				break;
			}
			
			String tweet = tweetsJSON.substring(startPos + 8, endPos - 1);
			
			startPos = tweetsJSON.indexOf("\"favorite_count\":");
			
			int numFavorites = Integer.parseInt(tweetsJSON.substring(startPos + 17, startPos + 18));
			
			System.out.println(tweet + " " + numFavorites);
			
			startPos = tweetsJSON.indexOf("\"retweet_count\":");
			
			int numRetweets = Integer.parseInt(tweetsJSON.substring(startPos + 16, startPos + 17));
			
			hashMap.put(tweet, numFavorites + numRetweets);
			
			tweetsJSON = tweetsJSON.substring(startPos + 1);
		}
		
		return hashMap;
	}

	
	public static void setupTweets()
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
		try {
			
			numTweets = twitter.showUser(UserID).getStatusesCount();
			
		    Paging paging = new Paging(1, numTweets);
		    
		    ResponseList<Status> statuses = twitter.getHomeTimeline(paging);

		    tweetsJSON = DataObjectFactory.getRawJSON(statuses);
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int favoriteCount(int numberAgo)  {

		
		String strTweets = tweetsJSON;
		
		for(int i = 1; i < numberAgo; i++)
		{
			strTweets = strTweets.substring(strTweets.indexOf("favorite_count") + 16);
		    
			
		}
		
		strTweets = strTweets.substring(strTweets.indexOf("favorite_count") + 16);
		
		return Integer.parseInt(strTweets.substring(0, strTweets.indexOf("\"")-1));

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
			System.exit(-1);
		}
	}
	
	public static String getTweet(int numberAgo)
	{
		
		String strTweets = tweetsJSON;
		
		for(int i = 1; i < numberAgo; i++)
		{
			strTweets = strTweets.substring(strTweets.indexOf("text\":\"") + 7);
		    
			
		}
		
		strTweets = strTweets.substring(strTweets.indexOf("text\":\"") + 7);
		
		return strTweets.substring(0, strTweets.indexOf("\""));
		
	}
	
	public static int retweetCount(int numberAgo)
	{
		
		String strTweets = tweetsJSON;
		
		for(int i = 1; i < numberAgo; i++)
		{
			strTweets = strTweets.substring(strTweets.indexOf("retweet_count") + 15);
			
		}
		
		strTweets = strTweets.substring(strTweets.indexOf("retweet_count") + 15);
		
		return Integer.parseInt(strTweets.substring(0, strTweets.indexOf("\"")-1));
		
	}
	
	public static void deleteTweet(int numberAgo)
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
		String removed = getTweet(numberAgo);
		
		try {
			
			twitter.destroyStatus(numberAgo);
            System.out.println("Successfully deleted status [" + removed + "].");
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

