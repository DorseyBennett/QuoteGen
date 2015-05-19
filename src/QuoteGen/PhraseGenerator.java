package QuoteGen;

public class PhraseGenerator {

	static int transverb = ListFunctions.getRandomIndex(PartsOfSpeech.verbs[0]);
	static int intransverb = ListFunctions
			.getRandomIndex(PartsOfSpeech.verbs[1]);
	static int prepverb = ListFunctions.getRandomIndex(PartsOfSpeech.verbs[2]);
	static int prep = ListFunctions.getRandomIndex(PartsOfSpeech.prepositions);
	static int noun = ListFunctions.getRandomIndex(PartsOfSpeech.nouns[0]);
	static int place = ListFunctions.getRandomIndex(PartsOfSpeech.places);
	static int conj = ListFunctions.getRandomIndex(PartsOfSpeech.conjunctions);
	static int adv = ListFunctions.getRandomIndex(PartsOfSpeech.adverbs);

	public static String getPrepPhrase() {
		String phrase = "";
		int num = 0;
		
		int isNot = 0; //No "not"s
		
		if(Math.random()<0.3)
			isNot = -1; //"Not"s till last phrase
		else if(Math.random()<0.6)
			isNot = 1; //No "Not"s till last phrase

		while (Math.random() < 0.4)
			num++;

		if (num == 0) {

			if( isNot == 1)
				phrase += "Not ";
			place = ListFunctions.getSimilarIndex(PartsOfSpeech.places, place);
			prep = ListFunctions.getSimilarIndex(PartsOfSpeech.prepositions,
					prep);
			phrase += PartsOfSpeech.prepositions[prep];
			phrase += " " + PartsOfSpeech.places[place] + " ";

			return phrase;

		}
		for (int i = num; i >= 0; i--) {
			if (i == 0 && 0 != num) {
				conj = ListFunctions.getSimilarIndex(
						PartsOfSpeech.conjunctions, conj);
				phrase += PartsOfSpeech.conjunctions[conj] + " ";
			}
			if(isNot == -1 && i >=1)
				phrase += "Not ";
			if(isNot == 1 && i == 0)
				phrase += "Not ";
			place = ListFunctions.getSimilarIndex(PartsOfSpeech.places, place);
			prep = ListFunctions.getSimilarIndex(PartsOfSpeech.prepositions,
					prep);
			phrase += PartsOfSpeech.prepositions[prep];
			phrase += " " + PartsOfSpeech.places[place];
			if (i >= 1)
				phrase += ", ";

		}

		return phrase;

	}

	public static String toSentence(String toSentence) {
		String end = toSentence.substring(1);
		String begin = toSentence.substring(0, 1);
		end = end.toLowerCase();
		while (end.substring(end.length() - 1, end.length()).equals(" "))
			end = end.substring(0, end.length() - 1);
		return begin + end + ".";
	}
	
	public static String fixPunctuation(String toModify) {
		
		toModify = toSentence(toModify);
		for(int i = 0; i < toModify.length() - 2; i++)
		{
			if(toModify.charAt(i)==toModify.charAt(i+1) && toModify.charAt(i)==' ')
				toModify = toModify.substring(0, i+1) + toModify.substring(i+2);
		}
		
		return toModify;
	}

}
