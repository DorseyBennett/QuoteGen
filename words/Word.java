package QuoteGen.words;

public abstract class Word {
	public final static int verb = 0,   noun = 1,
        adjective = 2,                  adverb = 3,
        preposition = 4,                subordinateConjuction = 5,
        interjection = 6, 				article = 7;
	/*I know that article is not a separate part of speech (it is an adjective) 
		but since they are used differently, they should be in a different class
		*/
	public abstract String getWord (boolean plural);
	public abstract int getPartOfSpeech();

}
