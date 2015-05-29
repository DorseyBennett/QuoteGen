package QuoteGen.words;

public class AdjAdverb extends Word {
	private String adjAdverb;
	public AdjAdverb (String adjAdverb) {
		this.adjAdverb = adjAdverb;
	}
	public int getPartOfSpeech() {
		return Word.adjAdverb;
	}
	public String getWord(boolean plural) {
		return adjAdverb;
	}
}
