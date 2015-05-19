package QuoteGen;

public class Noun extends Word{
	
	private String singular, plural;
	private boolean usedWithArticle;
	public Noun(String singular, String plural, boolean usedWithArticle){
		this.singular=singular;
		this.plural=plural;
		this.usedWithArticle=usedWithArticle;
	}
	public Noun(String singular, String plural){
		this(singular, plural, false);
	}
	public Noun(String singular){
		this(singular, singular);
	}
	public int getPartOfSpeech() {
		return Word.noun;
	}
	public String getWord(boolean isPlural)
	{
		return isPlural ? plural : singular;
	}
	public String getWord(){
		return singular;
	}
	public boolean isUsedWithArticle() {
		return usedWithArticle;
	}
	
}
