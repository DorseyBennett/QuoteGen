package QuoteGen;

public class Test {
	
	public static void main(String[] args) {
//		System.out.println(clauseGenerator.formClause());
		SentenceGenerator.setupListOfWords();
		System.out.println(SentenceGenerator.generateSentence());
	}
	
}
