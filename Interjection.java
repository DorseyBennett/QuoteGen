package QuoteGen;

public class Interjection extends Word {
	private String inter;
	public Interjection(String inter) {
		this.inter = inter;
	}
	public int getPartOfSpeech() {
		return Word.interjection;
	}
	public String getWord(boolean plural) {
		return inter;
	}
}
