package QuoteGen;

public abstract class Word {

	protected String word;
	
	public Word(String word)
	{
		this.word = word;
	}
	
	public abstract String getPartOfSpeech();
	
}
