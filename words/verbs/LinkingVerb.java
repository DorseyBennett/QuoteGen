package QuoteGen.words.verbs;

public class LinkingVerb extends Verb {
	public LinkingVerb(String present, String past) {
		super(present, past);
	}
	public LinkingVerb(String singularPresent, String pluralPresent, 
			String singularPast, String pluralPast) {
		super(singularPresent, pluralPresent, singularPast, pluralPast);
	}
	public LinkingVerb(String singularPresent, String pluralPresent, 
			String singularPast, String pluralPast, String inf) {
		super(singularPresent, pluralPresent, singularPast, pluralPast, inf);
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
	public boolean isHelping() {
		return false;
	}
}