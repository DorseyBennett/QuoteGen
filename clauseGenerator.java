public class clauseGenerator {

	public static String getRandomString(String[] list) {
		int amount = list.length;

		int randomPos = (int) (Math.random() * amount);

		return list[randomPos];

	}

	public static String formClause() {

		String clause = "";

		clause += getRandomString(PartsOfSpeech.nouns[0]) + " ";

		int verbType = (int) (Math.random() * 3);

		if (Math.random() < .6)
			clause += getRandomString(PartsOfSpeech.negations) + " ";

		clause += getRandomString(PartsOfSpeech.Verbs[verbType]) + "";

		if (verbType == PartsOfSpeech.Prepositional) {
			return clause + " that " + getPrepositionalPhrase() + ", "
					+ formClause();

		}

		if (verbType == PartsOfSpeech.StandAlone) {
			if (Math.random() < 0.3)
				return clause;
			if (Math.random() < .7)
				clause += " " + getRandomString(PartsOfSpeech.conjunctions)
						+ " " + formClause();
			return clause + " " + getPrepositionalPhrase();

		}
		if (verbType == PartsOfSpeech.DirectObject) {
			clause += " " + getRandomString(PartsOfSpeech.places);
			if (Math.random() < 0.3)
				return clause;
			if (Math.random() < .7)
				clause += " " + getRandomString(PartsOfSpeech.conjunctions)
						+ " " + getRandomString(PartsOfSpeech.places);
			if (Math.random() < .4)
				clause += " " + getRandomString(PartsOfSpeech.conjunctions)
						+ " " + formClause();
			if (Math.random() < 0.3)
				return clause;
			return clause + " " + getPrepositionalPhrase();
		}

		return "error";

	}

	public static String getPrepositionalPhrase() {
		String phrase = "";

		phrase += getRandomString(PartsOfSpeech.prepositions) + " ";

		phrase += getRandomString(PartsOfSpeech.places);

		return phrase;
	}

	public static String LowerCase(String toLowerCase) {
		String end = toLowerCase.substring(1);
		String begin = toLowerCase.substring(0, 1);
		end = end.toLowerCase();
		return begin + end + ".";
	}

}
// Outside of hope, people sometimes walk but brothers sometimes run beside
// friendship.

