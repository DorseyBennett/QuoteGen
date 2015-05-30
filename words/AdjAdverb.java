package QuoteGen.words;


public class AdjAdverb extends Word{
	public String word;
	public AdjAdverb(String word) { 
		this.word = word;
	}
	
	public int getPartOfSpeech() {
		return Word.adjAdverb;
	}
	public String getWord(boolean isPlural){
		return word;
	}
}
