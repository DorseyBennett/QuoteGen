package QuoteGen;

public class MarkovEntry {
	private int totalStars;//"stars" = favs + retweets
	private int numUses;
	public MarkovEntry() {
		totalStars = 10;//benefit of the doubt
		numUses = 5;
	}
	public double getWeight() {
		return 1.0 * totalStars/numUses;
	}
	public void addTweet(int starCount) {
		totalStars += starCount;
		numUses++;
	}
}
