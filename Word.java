package QuoteGen;

public abstract class Word {
	String word;
	public Word()
	{
		
	}
	
	public abstract String getPartOfSpeech();
	
	public String getWord(int type)
	{
		return word;
	}
	
}
