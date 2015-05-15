package QuoteGen;

public abstract class Word {	
	public final static int verb = 0, noun = 1, adjective = 2,
			adverb = 3, preposition = 4, conjuction = 5,
			interjection = 6;
	public abstract String getWord (boolean plural);
	public abstract int getPartOfSpeech();
	
}
