package QuoteGen.words;


public class Preposition extends Word{
	private String prep;
	public Preposition (String prep) {
		this.prep = prep;
	}
	public int getPartOfSpeech() {
		return Word.preposition;
	}
	public String getWord(boolean plural) {
		return prep;
	}
}
