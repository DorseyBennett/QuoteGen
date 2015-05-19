/**
@author Jeremy McCulloch
@version 1.0
**/
package QuoteGen;
import java.util.ArrayList;
/** Class used to generate sentences, only used statically */
public class SentenceGenerator {
	/** flags to denote whether article and whether plural
	 * 0x1 = can be plural w article (the people are vs the happinesses are/ the they are), 
	 * 0x1<<1 can be sing w article (the heart is vs the happiness is)
	 * 0x1<<2 can be plural w/o article (people are vs happinesses are)
	 * 0x1<<3 can be sing w/o article (hope is vs person is)
	 */
	public static final int pluralArticle = 0x1, singularArticle = 0x1<<1, pluralNoArticle = 0x1 << 2, singularNoArticle = 0x1 << 3;	
	private static ArrayList<Word> listOfWords;
	/**
	 * Populates listOfWords with statically initialized words
	 */
	public static void setupListOfWords() {
		String[][] nouns = {{"person", "people"},
				{"soul", "souls"}, {"heart", "hearts"}, 
				{"friend", "friends"}, {"brother", "brothers"}, 
				{"sister", "sisters"} };//nx2
		int[] flags = { pluralArticle | singularArticle | pluralNoArticle, 
				pluralArticle | singularArticle | pluralNoArticle | singularNoArticle, pluralArticle | singularArticle | pluralNoArticle,
				pluralArticle | singularArticle | pluralNoArticle, pluralArticle | singularArticle | pluralNoArticle,
				pluralArticle | singularArticle | pluralNoArticle,
		};
		
		String[][] adjectives = {{"Shiny"}, {"Beautiful"}, {"Happy"}, 
				{"Hopeful"}, {"Rare"}, {"Determined"}, 
				{"Blessed"}, {"Important"}, {"Inspirational"},
				{"Inspirational"}, {"Smart"}, {"Lonely"}, {"Sparkly"}};
		String[][] adverbs = {{"Never"}, {"Always"}, {"Sometimes"}};
		String[][] prepositions = {{"In"}, {"On"}, {"Outside of"}, {"Inside of"},
				{"Besides"}, {"Towards"}, {"Away from"}, {"Without"}, {"With"}, {"Above"},
				{"Below"}, {"Out of"}};
		String[][] transVerbs = {{"Believe", "believes", "believed", "believed"}, 
				{"Control", "controls", "controlled", "controlled" },
				{"Are", "is", "were", "was"},
				{"Climb", "climbs", "climbed", "climbed"}, 
				{"run", "runs", "ran", "ran"},
				{"Take", "takes", "took", "took"}, 
				{"Leave", "leaves", "left", "left"},
				{"Have", "has", "had", "had"}};//nx4
		String[][] intransVerbs = {{}};//nx4
		String[][] prepVerbs = {{"go", "goes", "went", "went"}};//nx4
		String[][] articles = {{"the"}, {"a"}, {"some"}};//nx4

		listOfWords = new ArrayList<Word>();
		addWordsOfType(nouns, flags, Word.noun, 0);
		addWordsOfType(adjectives, flags, Word.adjective, 0);
		addWordsOfType(adverbs, flags, Word.adverb, 0);
		addWordsOfType(prepositions, flags, Word.preposition, 0);
//		addWordsOfType(transVerbs, flags, Word.verb, Verb.transitive);
		addWordsOfType(articles, flags, Word.article, 0);

//		addWordsOfType(intransVerbs, flags, Word.verb, Verb.intransitive);
		addWordsOfType(prepVerbs, flags, Word.verb, Verb.prepositional);

	}
	/**
	 * Adds given strings to listOfWords
	 * @param words Nx4 array of strings, words[i][0] = sing. present, 
	 * words[i][1] = plural present, words[i][2] = sing past, words[i][3] = plural past
	 * @param flags Only used with noun, array of flags representing behavior with articles
	 * @param type Type of word (see Word)
	 * @param verbType Type of verb (see Verb)
	 */
	private static void addWordsOfType (String[][] words, int[] flags, int type, int verbType) {
		for (int i = 0; i<words.length; i++) {
			String[] e = words[i];
			switch (type) {
			case Word.noun:
				listOfWords.add(new Noun(e[0], e[1], flags[i]));
				break;
			case Word.adjective:
				listOfWords.add(new Adjective(e[0]));
				break;
			case Word.adverb:
				listOfWords.add(new Adverb(e[0]));
				break;
			case Word.preposition:
				listOfWords.add(new Preposition(e[0]));
				break;
			case Word.interjection:
				listOfWords.add(new Interjection(e[0]));
				break;
			case Word.article:
				listOfWords.add(new Article(e[0]));
				break;
			case Word.verb:
				switch (verbType) {
				case Verb.transitive:
					listOfWords.add(new TransitiveVerb(e[1], e[0], e[3], e[2]));
					break;
				case Verb.prepositional:
					listOfWords.add(new PrepositionalVerb(e[1], e[0], e[3], e[2]));
					break;
				case Verb.intransitive:
					listOfWords.add(new IntransitiveVerb(e[1], e[0], e[3], e[2]));
					break;

				default:
					break;
				}
				break;

			default:
				break;
			}
		}
	}
	/**
	 * Generates inspirational sentences, currently only simple (1 independent clause)
	 * @return Formatted string containing the newly generated sentence
	 */
	public static String generateSentence() { 
		ArrayList<Word> sentence = new ArrayList<Word>();
		// only simple, present tense sentences
		boolean plural = appendIndependentClause(sentence);
		String res = "";
		//assume its present, positive for now
		boolean present = true;
		boolean negative = false;
		for (Word e : sentence) {
			if (e.getPartOfSpeech() == Word.verb) {//verbs need special treatment
				if (negative) {
					res += present ? (plural ? "do not " : "does not ") : "did not ";
					res += e.getWord(true) + " ";
				} else {
					Verb v = (Verb) e;
					res += v.getWord(plural, present) + " ";
				}
			} else {
				res += e.getWord(plural) + " ";
			}
		}
		//capitalization
		String firstChar = res.substring(0, 1);
		String restOfStr = res.substring(1, res.length()-1);//not include last space
		firstChar = firstChar.toUpperCase();
		restOfStr = restOfStr.toLowerCase();
		
		return firstChar + restOfStr + ".";
	}
	/**
	 * Appends an independent clause to end of given sentence
	 * @param sentence ArrayList of words to append independent clause to
	 * @return Boolean that indicated whether the subject of the sentence is plural 
	 * (true = plural, false = singular)
	 */
	private static boolean appendIndependentClause (ArrayList<Word> sentence) {
		//form = Subject Predicate (DO) (Prep phrase)
		boolean plural = appendDescriptiveNoun(sentence);//whether plural depends on subj.
		Verb predicate = (Verb) getWordOfType(Word.verb,sentence.get(sentence.size()-1));
			//get verb related to subj.
		sentence.add(predicate);
		if (predicate.isTransitive()) {//if transitive add DO
			appendDescriptiveNoun(sentence);
		}
		if (predicate.isUsedWithPrepPhrase()) {//add prep phrase if necessary
			appendPrepositionalPhrase(sentence);
		}
		return plural;
	}
	/**
	 * Appends prepositional phrase to end of given sentence
	 * @param sentence ArrayList of words to append prepositional phrase to
	 */
	private static void appendPrepositionalPhrase (ArrayList<Word> sentence) {
		sentence.add(getWordOfType(Word.preposition));
		appendDescriptiveNoun(sentence);
	}
	/**
	 * Appends noun, article (if needed), and related adjective to end of given sentence
	 * @param sentence ArrayList of words to append words to
	 */
	private static boolean appendDescriptiveNoun (ArrayList<Word> sentence) {
		Word lastWord = null;
		if (sentence.size()>0) {//if sentence is not empty, get noun related to last word
			lastWord = sentence.get(sentence.size()-1);
		}
		Noun noun = (Noun) getWordOfType(Word.noun, lastWord);
		boolean plural = noun.isPlural();
		if (noun.isUsedWithArticle(plural)) {
			sentence.add(getWordOfType(Word.article));
		}
		sentence.add(getWordOfType(Word.adjective, noun));
		sentence.add(noun);
		return plural;//needs to be fixed for words like "happiness"
	}
	/**
	 * Alias of getWordOfType that does not require related word (defaults to null)
	 */
	private static Word getWordOfType (int type) {
		return getWordOfType(type, null);
	}
	/**
	 * Used to get a word of a particular part of speech that is related to another
	 * @param type integer representing part of speech
	 * @param related word that return value should be similar to (null gives random word)
	 * @return word of specified type similar to specified word
	 */
	private static Word getWordOfType (int type, Word related) {
		//will be updated to use markov or related
		ArrayList<Word> temp = new ArrayList<Word>();
		for (Word e : listOfWords)
			if (e.getPartOfSpeech() == type)
				temp.add(e);
		int index = (int) (Math.random() * temp.size());
		return temp.get(index);
	}
}