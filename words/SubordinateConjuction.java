package QuoteGen.words;

public class SubordinateConjuction extends Word {
	private String conj;
	public SubordinateConjuction(String conj) {
		this.conj = conj;
	}
	public int getPartOfSpeech() {
		return Word.subordinateConjuction;
	}
	public String getWord(boolean plural) {
		return conj;
	}
}
