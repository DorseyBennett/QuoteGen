package QuoteGen.words;


public class Article extends Word {
	private String artSing, artPlural;
	public Article (String artSing, String artPlural) {
		this.artSing = artSing;
		this.artPlural = artPlural;
	}
	public int getPartOfSpeech() {
		return Word.article;
	}
	public String getWord(boolean plural) {
		return plural ? artPlural : artSing;
	}
}
