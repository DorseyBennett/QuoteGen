package QuoteGen;

import java.util.ArrayList;
import java.util.HashMap;

public class Markov {
	private HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>(
			16);

	public static String getPopularWord() {
		String word = "";

		return word;
	}

	public ArrayList<String> parse(String x) {
		ArrayList<String> list = new ArrayList<String>();
		int place = 0;
		
		while(place<x.length())
			for(int y=0; y<=9; y++)
				if(x.indexOf(y, place)!=-1 && x.indexOf(y, place)<place + 300)
					list.add(x.substring(place, x.indexOf(y, place)));
		
		ArrayList<String> list2 = list;
		
		
		for(int y = 0; y<list.size(); y++)
			for(int z = 0; z<list.get(y).length();){
				list2.add(list.get(y).substring(y, list.get(y).indexOf(" ", y)));
				z+=list2.get(y).length();
			}
			
		
		return list;
	}

	public String HashIt() {
		String x = "";
		for (int i = 1; TweetFunctions.favoriteCount(i) != -1; i++) {
			x += TweetFunctions.getTweet(i);
		}
		return x;
//		ArrayList<String> list = parse(x);
//		return list;
	}

	public static double getAverage(String A, String B) {
		int totalFav = 0;
		int totalRT = 0;
		int totalTweets = 0;

		for (int i = 0; TweetFunctions.favoriteCount(i) != -1; i++) {
			if (TweetFunctions.getTweet(i).indexOf(A) != -1
					&& TweetFunctions.getTweet(i).indexOf(B) != -1) {
				totalFav += TweetFunctions.favoriteCount(i);
				totalRT += TweetFunctions.retweetCount(i);
				totalTweets++;
			}
		}

		return totalTweets / (totalFav + totalRT);
	}

	public static double getAverage(String A) {
		return getAverage(A, A);
	}

	public static double getAverage() {
		return getAverage("");
	}

}
