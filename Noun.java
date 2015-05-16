package QuoteGen;

public class Noun extends Word{
	
	public String singular, plural;
	
	public Noun(String singular, String plural){
		
		this.singular=singular;
		this.plural=plural;
	}
public Noun(String singular){
		
		this.singular=singular;
		this.plural=singular;
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
	
}
