package QuoteGen.words.verbs;

public class LinkingVerb extends Verb {
	public LinkingVerb(String present, String past) {
		super(present, past);
	}
	public LinkingVerb(String singularPresent, String pluralPresent, 
			String singularPast, String pluralPast) {
		super(singularPresent, pluralPresent, singularPast, pluralPast);
	}
	@Override
	public boolean isTransitive() {
		return false;
	}
	@Override
	public boolean isUsedWithPrepPhrase() {
		return false;
	}
	public boolean isLinking() {
		return true;
	}
}