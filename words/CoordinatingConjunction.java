package QuoteGen.words;

public class CoordinatingConjunction extends Word {
	private String conj;
	public CoordinatingConjunction(String conj) {
		this.conj = conj;
	}
	public int getPartOfSpeech() {
		return Word.coordinatingConjunction;
	}
	public String getWord(boolean plural) {
		return conj;
	}
}
