package QuoteGen;

public abstract class Verb extends Word {
	protected String singular, plural;
	public String getWord(boolean isPlural) {
		return isPlural ? plural : singular;
	}
	public int getPartOfSpeech() {
		return Word.verb;
	}
}
