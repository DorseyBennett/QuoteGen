package QuoteGen.words.verbs;

public class HelpingVerb extends Verb {
	public HelpingVerb(String present, String past) {
		super(present, past);
	}
	public HelpingVerb(String singularPresent, String pluralPresent, 
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
		return false;
	}
	public boolean isHelping() {
		return true;
	}
}
