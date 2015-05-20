package QuoteGen.words;

import QuoteGen.SentenceGenerator;

public class Noun extends Word{
	
	private String singular, plural;
	private int flag;
	public Noun(String singular, String plural, int flag){
		this.singular = singular;
		this.plural = plural;
		this.flag = flag;
	}
	public Noun(String singular, String plural){
		this(singular, plural, 0xf);
	}
	public Noun(String singular){
		this(singular, singular);
	}
	public int getPartOfSpeech() {
		return Word.noun;
	}
	public String getWord(boolean isPlural)
	{
		return isPlural ? plural : singular;
	}
	public String getWord(){
		return singular;
	}
	public boolean isPlural() {
		boolean canBePlural = ((SentenceGenerator.pluralArticle | SentenceGenerator.pluralNoArticle) & flag) > 0;
		boolean canBeSing = ((SentenceGenerator.singularArticle | SentenceGenerator.singularNoArticle) & flag) > 0;
		if (canBeSing && canBePlural) {
			return Math.random()>0.5;
		}
		return canBePlural;
	}
	public boolean isUsedWithArticle(boolean isPlural) {
		boolean canBeArticle = ((isPlural ? SentenceGenerator.pluralArticle : SentenceGenerator.singularArticle) & flag) > 0;
		boolean canBeNoArticle = ((isPlural ? SentenceGenerator.pluralNoArticle : SentenceGenerator.singularNoArticle) & flag) > 0;
		if (canBeArticle && canBeNoArticle) return Math.random()>0.5;
		return canBeArticle;
	}
}
