package QuoteGen.words.verbs;



public class TransitiveVerb extends Verb{
	public TransitiveVerb(String present, String past) {
		super(present, past);
	}
	public TransitiveVerb(String singularPresent, String pluralPresent, 
			String singularPast, String pluralPast) {
		super(singularPresent, pluralPresent, singularPast, pluralPast);
	}
	@Override
	public boolean isTransitive() {
		return true;
	}
	@Override
	public boolean isUsedWithPrepPhrase() {
		return false;
	}
}
