package QuoteGen;

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
			clause += getRandomString(PartsOfSpeech.adverbs) + " ";

		clause += getRandomString(PartsOfSpeech.verbs[verbType]) + "";

		if (verbType == PartsOfSpeech.Prepositional) {
			return Grammar(clause + " that " + getPrepositionalPhrase() + ", "
					+ formClause());

		}

		if (verbType == PartsOfSpeech.StandAlone) {
			if (Math.random() < 0.3)
				return Grammar(clause);
			if (Math.random() < .7)
				clause += " " + getRandomString(PartsOfSpeech.conjunctions)
						+ " " + formClause();
			return Grammar(clause + " " + getPrepositionalPhrase());

		}
		if (verbType == PartsOfSpeech.DirectObject) {
			clause += " " + getRandomString(PartsOfSpeech.places);
			if (Math.random() < 0.3)
				return Grammar(clause);
			if (Math.random() < .7) 
				clause += " " + getRandomString(PartsOfSpeech.conjunctions)
						+ " " + getRandomString(PartsOfSpeech.places);
			if (Math.random() < .4)
				clause += " " + getRandomString(PartsOfSpeech.conjunctions)
						+ " " + formClause();
			if (Math.random() < 0.3)
				return Grammar(clause);
			return Grammar(clause + " " + getPrepositionalPhrase());
		}

		return "error";

	}

	public static String getPrepositionalPhrase() {
		String phrase = "";

		phrase += getRandomString(PartsOfSpeech.prepositions) + " ";

		phrase += getRandomString(PartsOfSpeech.places);

		return phrase;
	}

	public static String Grammar(String toLowerCase) {
		String end = toLowerCase.substring(1);
		String begin = toLowerCase.substring(0, 1);
		end = end.toLowerCase();
		String all = begin+end;
		if(all.indexOf(".")==-1)
			return all + ".";
		else if(all.indexOf(".")!=-1){
			begin = all.substring(0,all.indexOf("."));
			end = all.substring(all.indexOf(".")+1);
			return begin+end+".";
					
		}
		else return all;
	}

}
// Outside of hope, people sometimes walk but brothers sometimes run beside
// friendship.
// actually a kind of good one : "family are the joy and the soul inside the heart"
// only problem is that grammar;
