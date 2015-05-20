package QuoteGen.words;


public class Adverb extends Word{
	public String word;
	public Adverb(String word) { }
	
	public int getPartOfSpeech() {
		return Word.adverb;
	}
	public String getWord(boolean isPlural){
		return word;
	}
}
