package QuoteGen.words.verbs;

import QuoteGen.words.Word;

public abstract class Verb extends Word {
	protected String singularPresent, pluralPresent;
	protected String singularPast, pluralPast;
	public static final int transitive = 0, prepositional = 1, intransitive = 2;
	public Verb(String singularPresent, String pluralPresent, 
			String singularPast, String pluralPast) {
		
		this.singularPresent=singularPresent;
		this.pluralPresent=pluralPresent;
		this.singularPast=singularPast;
		this.pluralPast=pluralPast;
		
	}
	public Verb(String present, String past){
		this(present, present, past, past);
	}
	public String getWord(boolean isPlural, boolean isPresent) {
		return isPlural ? (isPresent ? pluralPresent : pluralPast) 
				: (isPresent ? singularPresent : singularPast);
	}
	public String getWord(boolean isPlural) {
		return getWord(isPlural, true);
	}
	public int getPartOfSpeech() {
		return Word.verb;
	}
	public abstract boolean isTransitive();
	public abstract boolean isUsedWithPrepPhrase();

}
