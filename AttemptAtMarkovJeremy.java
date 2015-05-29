package QuoteGen;

import java.util.ArrayList;
import java.util.HashMap;

import QuoteGen.words.Word;

public class AttemptAtMarkovJeremy {
	private ArrayList<String> tweets;
	private ArrayList<ArrayList<Word>> words;
	private HashMap<Word, HashMap<Word, MarkovEntry>> hash;
	public AttemptAtMarkovJeremy(String fileName) {
		tweets = new ArrayList<String>();
		words = new ArrayList<ArrayList<Word>>();
		if (fileName.length() > 0) {
			//TODO: read from file
		}
		setup();
	}
	public AttemptAtMarkovJeremy() {
		this("");
	}
	public void addQuote(String tweet, ArrayList<Word> sentence) {
		tweets.add(tweet);
		words.add(sentence);
	}
	public boolean save() {
		return true;//TODO: write to file
	}
	private void setup() {
		hash = new HashMap<Word, HashMap<Word, MarkovEntry>>();
		for (Word w : SentenceGenerator.listOfWords) {
			HashMap<Word, MarkovEntry> val = new HashMap<Word, MarkovEntry>();
			for (Word w2 : SentenceGenerator.listOfWords) {
				val.put(w2, new MarkovEntry());
			}
			hash.put(w, val);
		}
		int numAgo = 0;
		for (int i = tweets.size() - 1; i >= 0; i--) {
			
			while (!TweetFunctions.getTweet(numAgo).equals(tweets.get(i))) numAgo++;
			int favCount = TweetFunctions.favoriteCount(numAgo) + TweetFunctions.favoriteCount(numAgo);
			
			for (Word w1 : words.get(i)) {
				HashMap<Word, MarkovEntry> val = hash.get(w1);
				for (Word w2 : words.get(i)) {
					val.get(w2).addTweet(favCount);
				}
			}
		}
	}
	public Word getWordOfType (int type, Word related) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		HashMap<Word, MarkovEntry> val = null;
		if (related != null) 
			 val = hash.get(related);
		ArrayList<Double> weights = new ArrayList<Double>();
		double sumWeights = 0.0;
		for (int i = 0; i < SentenceGenerator.listOfWords.size(); i++) {
			Word word = SentenceGenerator.listOfWords.get(i);
			if (related == null) 
				val = hash.get(word);//word related to itself
			
			if (word.getPartOfSpeech() == type) {
				temp.add(i);//add to list of potential
				double w = val.get(word).getWeight();//get + add weight
				weights.add(w);
				sumWeights += w;//update sum
			}
		}
		//normalize arraylist
		weights.set(0, weights.get(0)/sumWeights);
		for (int i = 1; i < weights.size(); i++) {
			weights.set(i, weights.get(i)/sumWeights + weights.get(i-1));//prev + normalized weight
		}
		//gen random
		double rand = Math.random();
		for (int i = 0; i < weights.size(); i++) {
			if (rand > weights.get(i)) {//first value less than weight
				return SentenceGenerator.listOfWords.remove(i);
			}
		}
		return null;
	}
	public Word getWordOfType (int type) {
		return getWordOfType(type, null);
	}
}
