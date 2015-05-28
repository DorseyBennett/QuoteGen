/**
@author Jeremy McCulloch
@version 1.0
**/
package QuoteGen;
import java.util.ArrayList;

import QuoteGen.words.Adjective;
import QuoteGen.words.Adverb;
import QuoteGen.words.Article;
import QuoteGen.words.Interjection;
import QuoteGen.words.Noun;
import QuoteGen.words.Preposition;
import QuoteGen.words.SubordinateConjuction;
import QuoteGen.words.Word;
import QuoteGen.words.verbs.IntransitiveVerb;
import QuoteGen.words.verbs.PrepositionalVerb;
import QuoteGen.words.verbs.TransitiveVerb;
import QuoteGen.words.verbs.Verb;
/** Class used to generate sentences, only used statically */
public class SentenceGenerator {
	/** flags to denote whether article and whether plural
	 * 0x1 = can be plural w article (the people are vs the happinesses are/ the they are), 
	 * 0x1<<1 can be sing w article (the heart is vs the happiness is)
	 * 0x1<<2 can be plural w/o article (people are vs happinesses are)
	 * 0x1<<3 can be sing w/o article (hope is vs person is)
	 */
	public static final int pluralArticle = 0x1, singularArticle = 0x1<<1, pluralNoArticle = 0x1 << 2, singularNoArticle = 0x1 << 3;	
	public static ArrayList<Word> listOfWords;
	/**
	 * Populates listOfWords with statically initialized words
	 */
	public static void setupListOfWords() {
		String[][] nouns = {{"person", "people"},
				{"soul", "souls"}, {"heart", "hearts"}, 
				{"friend", "friends"}, {"brother", "brothers"}, 
				{"sister", "sisters"}, {"hope", "hope"},
				{"love", "love"},
				
		};//nx2
		int[] flags = { pluralArticle | singularArticle | pluralNoArticle, 
				pluralArticle | singularArticle | pluralNoArticle, pluralArticle | singularArticle | pluralNoArticle,
				pluralArticle | singularArticle | pluralNoArticle, pluralArticle | singularArticle | pluralNoArticle,
				pluralArticle | singularArticle | pluralNoArticle, singularNoArticle, 
				singularNoArticle, singularArticle | pluralArticle | pluralNoArticle,
		};
		
		String[][] adjectives = {{"impossible"}, {"Beautiful"}, {"Happy"}, 
				{"Hopeful"}, {"Rare"}, {"Determined"}, 
				{"Blessed"}, {"Important"}, {"Inspirational"},
				{"best"}, {"lovely"}, {"Lonely"}, {"foolish"}
				};
				
		String[][] adverbs = {{"Never"}, {"Always"}, {"Sometimes"}};
		String[][] prepositions = {{"In"}, {"On"}, {"Outside of"}, {"Inside of"},
				{"Beside"}, {"Towards"}, {"Away from"}, {"Without"}, {"With"}, {"Above"},
				{"Below"}, {"Out of"}};
		String[][] transVerbs = {
				{"Believe", "believes", "believed", "believed"}, 
				{"Control", "controls", "controlled", "controlled" },
				{"Climb", "climbs", "climbed", "climbed"}, 
				{"Take", "takes", "took", "took"}, 
				{"Leave", "leaves", "left", "left"},
				{"Have", "has", "had", "had"}
		};//nx4
		String[][] intransVerbs = {
				{"talk", "talks", "talked", "talked"}, 
				{"think", "thinks", "thought", "thought"},
				{"believe", "believes", "believed", "believed"}
		};//nx4
		String[][] prepVerbs = {
				{"go", "goes", "went", "went"},
				{"fly", "flies","flew", "flew"}, 
				{"run", "runs", "ran", "ran"},
				{"walk", "walks", "walked", "walked"}
		};//nx4
		String[][] articles = {{"the", "the"}, {"a", "some"}};//nx4
		String[][] subordinateConjuctions = {
				{"after"}, {"although"}, {"as"}, {"as soon as"}, {"because"}, {"before"}, 
				{"by the time"}, {"even if"}, {"even though"}, {"every time"}, {"if"}, {"in case"}, {"in the event that"},
				{"just in case"}, {"now that"}, {"once"}, {"only if"}, {"since"}, {"since"}, {"the first time"}, 
				{"though"}, {"unless"}, {"until"}, {"when"}, {"whenever"}, {"whereas"}, {"whether or not"}, 
				{"while"}
		};
		listOfWords = new ArrayList<Word>();
		addWordsOfType(nouns, flags, Word.noun, 0);
		addWordsOfType(adjectives, flags, Word.adjective, 0);
		addWordsOfType(adverbs, flags, Word.adverb, 0);
		addWordsOfType(prepositions, flags, Word.preposition, 0);
		addWordsOfType(transVerbs, flags, Word.verb, Verb.transitive);
		addWordsOfType(articles, flags, Word.article, 0);
		addWordsOfType(intransVerbs, flags, Word.verb, Verb.intransitive);
		addWordsOfType(prepVerbs, flags, Word.verb, Verb.prepositional);
		addWordsOfType(subordinateConjuctions, flags, Word.subordinateConjuction, 0);
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
				listOfWords.add(new Article(e[0], e[1]));
				break;
			case Word.subordinateConjuction: 
				listOfWords.add(new SubordinateConjuction(e[0]));
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
		setupListOfWords();
		ArrayList<Word> sentence = new ArrayList<Word>();
		ArrayList<Boolean> plural = new ArrayList<Boolean>();

		// only simple sentences
		appendIndependentClause(sentence, plural, true);
		String res = "";
		boolean present = Math.random() > 0.4;
		boolean negative = Math.random() > 0.5;
		for (Word e : sentence) // cannot be negative if includes adverb (they never do not climb)
			if (e.getPartOfSpeech() == Word.adverb || e.getPartOfSpeech() == Word.subordinateConjuction)
				negative = false;
		
		int index = 0;
		for (Word e : sentence) {
			if (e.getWord(plural.get(index)).equals("a")) {
				char nextC = sentence.get(index+1).getWord(false).toLowerCase().charAt(0);
				res += (nextC == 'a' || nextC == 'e' || nextC == 'i' || nextC == 'o' || nextC == 'u') ?
						"an " : "a ";
			} else if (e.getPartOfSpeech() == Word.verb) {//verbs need special treatment
				if (negative) {
					res += present ? (plural.get(index) ? "do not " : "does not ") : "did not ";
					res += e.getWord(true) + " ";
				} else {
					Verb v = (Verb) e;
					res += v.getWord(plural.get(index), present) + " ";
				}
			} else {
				res += e.getWord(plural.get(index)) + " ";
			}
			index++;
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
	 * @param plural ArrayList of booleans that determines whether each word is plural
	 * @param addDepClause if true has possibility of inserting dependent clause
	 * (true = plural, false = singular)
	 */
	private static void appendIndependentClause (ArrayList<Word> sentence, ArrayList<Boolean> plural, boolean addDepClause) {
		//form = Subject Predicate (DO) (Prep phrase)
		int initLength = plural.size();
		int finLength = initLength;
		boolean plur = appendDescriptiveNoun(sentence);//whether plural depends on subj.
		Verb predicate = (Verb) getWordOfType(Word.verb, sentence.get(sentence.size()-1));
			//get verb related to subj.
		
		if (!predicate.isTransitive() && !predicate.isUsedWithPrepPhrase() && addDepClause) {
			if (Math.random() > 0.5) {
				sentence.add(predicate);
				finLength = sentence.size();
				appendAdverbialClause(sentence, plural);
			} else {
				sentence.add(getWordOfType(Word.adverb, predicate));
				sentence.add(predicate);
				finLength = sentence.size();

			}
		} else {
			sentence.add(predicate);
			finLength = sentence.size();
		}

		for (int i = initLength; i < finLength; i++) {
			plural.add(initLength, plur);
		}
		initLength = plural.size();
		if (predicate.isTransitive()) {//if transitive add DO
			plur = appendDescriptiveNoun(sentence);
		} else if (predicate.isUsedWithPrepPhrase()) {//add prep phrase if necessary
			plur = appendPrepositionalPhrase(sentence);
		}
		for (int i = initLength; i < sentence.size(); i++) {
			plural.add(plur);
		}
	}
	/**
	 * Appends an adverbial clause to end of given sentence
	 * @param sentence ArrayList of words to append dependent clause to
	 * @param plural ArrayList of booleans that determines whether each word is plural
	 * (true = plural, false = singular)
	 */
	private static void appendAdverbialClause (ArrayList<Word> sentence, ArrayList<Boolean> plural) {
		Word lastWord = null;
		if (sentence.size()>0) {//if sentence is not empty, get noun related to last word
			lastWord = sentence.get(sentence.size()-1);
		}
		
		sentence.add(getWordOfType(Word.subordinateConjuction, lastWord));
		plural.add(false);
		
		appendIndependentClause(sentence, plural, false);
	}
	/**
	 * Appends prepositional phrase to end of given sentence
	 * @param sentence ArrayList of words to append prepositional phrase to
	 */
	private static boolean appendPrepositionalPhrase (ArrayList<Word> sentence) {
		Word lastWord = null;
		if (sentence.size()>0) {//if sentence is not empty, get noun related to last word
			lastWord = sentence.get(sentence.size()-1);
		}
		
		sentence.add(getWordOfType(Word.preposition, lastWord));
		
		return appendDescriptiveNoun(sentence);
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
		
		Noun noun = (Noun) getWordOfType(Word.noun, lastWord);//gets noun related to previous thing in sentence
		boolean plural = noun.isPlural();
		
		Adjective adj = (Adjective) getWordOfType(Word.adjective, noun);//gets adjective related to noun
		
		if (noun.isUsedWithArticle(plural)) {
			sentence.add(getWordOfType(Word.article, adj));//gets article related to adjective (dont say "a best")
		}
		
		sentence.add(adj);
		sentence.add(noun);
		
		return plural;
	}
	/**
	 * Used to get a word of a particular part of speech that is related to another
	 * @param type integer representing part of speech
	 * @param related word that return value should be similar to (null gives random word)
	 * @return word of specified type similar to specified word
	 */
	private static Word getWordOfType (int type, Word related) {
		//will be updated to use Markov or related
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < listOfWords.size(); i++)
			if (listOfWords.get(i).getPartOfSpeech() == type)
				temp.add(i);
		int index = temp.get((int)(Math.random() * temp.size()));
		return listOfWords.remove(index);
	}
}