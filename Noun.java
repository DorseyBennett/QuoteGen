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
	
	
	public String getPartOfSpeech() {
		return "noun";
	}
	public String getWord(int type)
	{
		return (type==1) ? plural : singular;
	}
	
}
