package QuoteGen;

public class Adjective extends Word {
	private String adj;
	public Adjective (String adj) {
		this.adj = adj;
	}
	public int getPartOfSpeech() {
		return Word.adjective;
	}
	public String getWord(boolean plural) {
		return adj;
	}
}
