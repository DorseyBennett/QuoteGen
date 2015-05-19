package QuoteGen;

public class Article extends Word {
	private String art;
	public Article (String art) {
		this.art = art;
	}
	public int getPartOfSpeech() {
		return Word.article;
	}
	public String getWord(boolean plural) {
		return art;
	}
}
