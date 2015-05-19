package QuoteGen;

public class PrepositionalVerb extends Verb{
	public PrepositionalVerb(String present, String past) {
		super(present, past);
	}
	public PrepositionalVerb(String singularPresent, String pluralPresent, 
			String singularPast, String pluralPast) {
		super(singularPresent, pluralPresent, singularPast, pluralPast);
	}
	@Override
	public boolean isTransitive() {
		return false;
	}
	@Override
	public boolean isUsedWithPrepPhrase() {
		return true;
	}
}
