/**
@author Jeremy McCulloch
@version 1.0
 **/
package QuoteGen;
import java.util.ArrayList;

import QuoteGen.words.*;
import QuoteGen.words.verbs.*;
/** Class used to generate sentences, only used statically */
public class SentenceGenerator {
	/** flags to denote whether article and whether plural
	 * 0x1 = can be plural w article (the people are vs the happinesses are/ the they are), 
	 * 0x1<<1 can be sing w article (the heart is vs the happiness is)
	 * 0x1<<2 can be plural w/o article (people are vs happinesses are)
	 * 0x1<<3 can be sing w/o article (hope is vs person is)
	 */
	public static final int pluralArticle = 0x1, singularArticle = 0x1<<1, pluralNoArticle = 0x1 << 2, singularNoArticle = 0x1 << 3;
	private static final SubordinateConjuction that = new SubordinateConjuction("that");
	public static ArrayList<Word> listOfWords;
	/**
	 * Populates listOfWords with statically initialized words
	 */
	//TODO: helping + linking verbs, 
	public static void setupListOfWords() {
		String[][] nouns = {
				{"person", "people"},
				{"soul", "souls"}, {"heart", "hearts"},
				{"friend", "friends"}, {"hope", "hope"},
				{"love", "love"}, {"fear", "fears"}, {"friend", "friends"}, 
				{"success", "successes"},
				{"perfection", "perfection"}, {"excellence", "excellence"}, 
				{"world", "worlds"}, {"work", "work"}, {"mind", "minds"}, 
				{"light", "lights"}, {"opportunity", "opportunities"},
				{"destiny", "destinies"}, {"darkness", "darknesses"},
				{"envy", "envy"}, {"pride", "pride"}

		};//nx2
		int[] flags = { 
				pluralArticle | singularArticle | pluralNoArticle,
				pluralArticle | singularArticle | pluralNoArticle, pluralArticle | singularArticle | pluralNoArticle,
				pluralArticle | singularArticle | pluralNoArticle,  singularNoArticle,
				singularNoArticle, pluralNoArticle | singularNoArticle, pluralNoArticle | singularArticle | pluralArticle,
				singularNoArticle | pluralArticle | singularNoArticle, 
				singularNoArticle, singularNoArticle,
				singularArticle | pluralNoArticle, singularNoArticle, singularArticle | pluralNoArticle, 
				singularArticle | pluralNoArticle, singularNoArticle | pluralNoArticle,
				singularNoArticle | singularArticle, singularNoArticle, 
				singularNoArticle, singularNoArticle,

		};
		String[][] adjectives = {{"impossible"}, {"Beautiful"}, {"Happy"},
				{"Hopeful"}, {"Determined"},
				{"Blessed"}, {"Important"}, {"sad"},
				{"lovely"}, {"Lonely"}, {"foolish"}, {"dark"}
		};
		String[][] adverbs = {{"Never"}, {"Always"}, {"Sometimes"}, {"Often"}, {"Usually"}};

		String[][] prepositions = {{"In"}, {"On"}, {"Outside of"}, {"Inside of"},
				{"Beside"}, {"Towards"}, {"Away from"}, {"Without"}, {"With"}, {"Above"},
				{"Below"}, {"Out of"}};

		String[][] transVerbs = {
				{"find", "finds", "found", "found"},
				{"Believe", "believes", "believed", "believed"},
				{"chase", "chases", "chased", "chased"},
				{"change", "changes", "changed", "changed"},
				{"catch", "catches", "caught", "caught"},
				{"Control", "controls", "controlled", "controlled" },
				{"Climb", "climbs", "climbed", "climbed"},
				{"Take", "takes", "took", "took"},
				{"Leave", "leaves", "left", "left"},
				{"Have", "has", "had", "had"},
				{"build", "builds", "built", "built"},
				{"teach", "teaches", "taught", "taught"},
				{"see", "sees", "saw", "saw"},
		};//nx4

		String[][] intransVerbs = { 
				{"focus", "focuses", "focused", "focused"},
				{"live", "lives", "lived", "lived"},
				{"talk", "talks", "talked", "talked"},
				{"think", "thinks", "thought", "thought"},
				{"believe", "believes", "believed", "believed"},
				{"hate", "hates", "hated", "hated"}, 
				{"learn", "learns", "learned", "learned"},
		};//nx4
		String[][] linkingVerbs = {//to be handled separately
				{"seem", "seems", "seemed", "seemed"},
				{"remain", "remains", "remained", "remained"},
				{"become", "becomes", "became", "became"},
		};//nx4
		String[][] prepVerbs = {
				{"go", "goes", "went", "went"},
				{"fly", "flies","flew", "flew"},
				{"run", "runs", "ran", "ran"},
				{"walk", "walks", "walked", "walked"}
		};//nx4
		String[][] helpVerbs = {
				{"want", "wants", "wanted", "wanted"},
				{"need", "needs", "needed", "needed"},
				{"expect", "expects", "expected", "expected"}, 
				
		};//nx4
		String[][] articles = {
				{"the", "the"}, {"a", "some"},
				{"the", "the"}, {"a", "some"},
				{"the", "the"}, {"a", "some"}
		};// can be used multiple times
		String[][] subordinateConjuctions = {
				{"after"}, {"although"}, {"as"}, {"as soon as"}, {"because"}, {"before"}, 
				{"by the time"}, {"even if"}, {"even though"}, {"every time"}, {"if"}, {"in case"}, {"in the event that"},
				{"just in case"}, {"now that"}, {"once"}, {"only if"}, {"since"}, {"since"}, 
				{"though"}, {"unless"}, {"until"}, {"when"}, {"whenever"}, {"whereas"}, {"whether or not"}, 
				{"while"}
		};
		String[][] coordinatingConjuctions = {
				{"and"}, {"but"}, 
		};
		listOfWords = new ArrayList<Word>();
		addWordsOfType(nouns, flags, Word.noun, 0);
		addWordsOfType(adjectives, flags, Word.adjective, 0);
		addWordsOfType(adverbs, flags, Word.adverb, 0);
		addWordsOfType(prepositions, flags, Word.preposition, 0);
		addWordsOfType(transVerbs, flags, Word.verb, Verb.transitive);
		addWordsOfType(articles, flags, Word.article, 0);
		addWordsOfType(intransVerbs, flags, Word.verb, Verb.intransitive);
		addWordsOfType(linkingVerbs, flags, Word.verb, Verb.linking);
		addWordsOfType(prepVerbs, flags, Word.verb, Verb.prepositional);
		addWordsOfType(helpVerbs, flags, Word.verb, Verb.helping);
		addWordsOfType(subordinateConjuctions, flags, Word.subordinateConjuction, 0);
		addWordsOfType(coordinatingConjuctions, flags, Word.coordinatingConjunctions, 0);
		listOfWords.add(new LinkingVerb("is", "are", "was", "were", "to be"));
		
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
			case Word.coordinatingConjunctions: 
				listOfWords.add(new CoordinatingConjunction(e[0]));
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
				case Verb.linking:
					listOfWords.add(new LinkingVerb(e[1], e[0], e[3], e[2]));
					break;
				case Verb.helping:
					listOfWords.add(new HelpingVerb(e[1], e[0], e[3], e[2]));
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
		if (Math.random() > 0.7) {//30% chance of making compound sentence
			appendIndependentClause(sentence, plural, false);
			plural.add(true);
			sentence.add(null);
			plural.add(true);
			sentence.add(getWordOfType(Word.coordinatingConjunctions, null));
			appendIndependentClause(sentence, plural, false);
		} else {
			appendIndependentClause(sentence, plural, true);
		}
		String res = "";
		boolean present = Math.random() > 0.4;
		boolean negative = Math.random() > 0.5;
		for (Word e : sentence) // cannot be negative if includes adverb (they never do not climb)
			if (e != null && (e.getPartOfSpeech() == Word.adverb || e.getPartOfSpeech() == Word.subordinateConjuction))
				negative = false;

		int index = 0;
		boolean infinitive = false;
		for (Word e : sentence) {
			if (e != null && e.getWord(false).equals("but")) negative=!negative;
			if (e == null) {
				res = res.substring(0, res.length() - 1);
				res += ", ";
			} else if (e.getWord(plural.get(index)).equals("a")) {
				char nextC = sentence.get(index+1).getWord(false).toLowerCase().charAt(0);
				res += (nextC == 'a' || nextC == 'e' || nextC == 'i' || nextC == 'o' || nextC == 'u') ?
						"an " : "a ";
			} else if (e.getPartOfSpeech() == Word.verb) {//verbs need special treatment
				Verb v = (Verb) e;
				if (infinitive) {
					res += v.getInfinitive() + " ";
					infinitive = false;
				} else if (negative) {
					if (e.getWord(true).equals("are")) {
						res += present ? (plural.get(index) ? "are not " : "is not ") : (plural.get(index) ? "were not " : "was not ");
					} else {
					res += present ? (plural.get(index) ? "do not " : "does not ") : "would not ";
					res += e.getWord(true) + " ";
					}
				} else {
					res += v.getWord(plural.get(index), present) + " ";
				}
				if (v.isHelping()) {
					infinitive = true;
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

		String returnVal =  firstChar + restOfStr + ".";
		return returnVal + HashtagFunctions.generateRelevantHashtag(returnVal);
	}
	/**
	 * Appends an independent clause to end of given sentence
	 * @param sentence ArrayList of words to append independent clause to
	 * @param plural ArrayList of booleans that determines whether each word is plural
	 * @param addDepClause if true has possibility of inserting dependent clause
	 * (true = plural, false = singular)
	 */
	private static void appendIndependentClause (ArrayList<Word> sentence, ArrayList<Boolean> plural, boolean addDepClause) {
		//form = Subject Predicate (DO) (Prep phrase) (adv or sdv clause)
		double random  = Math.random();
		boolean nounClauseInSubject = addDepClause && random < 0.3;
		boolean nounClauseInObject = addDepClause && random < 0.5 && ! nounClauseInSubject;
		addDepClause = addDepClause && random > 0.5;
		
		int initLength = plural.size();
		int finLength = initLength;
		
		boolean plur = nounClauseInSubject ? appendNounClause(sentence, plural) : appendDescriptiveNoun(sentence);//whether plural depends on subj.
		
		Noun subj = null;
		for (int i = initLength; i<sentence.size(); i++)
			if (sentence.get(i) != null && sentence.get(i).getPartOfSpeech() == Word.noun) {
				subj = (Noun) sentence.get(i);
				break;
			}
		
		if (nounClauseInSubject) initLength = plural.size();//if noun clause, only make things after noun clause plural
		
		
		Verb predicate = (Verb) getWordOfType(Word.verb, subj);
		if (predicate.isHelping()) sentence.add(predicate);
		while (predicate.isHelping()) {
			predicate = (Verb) getWordOfType(Word.verb, subj);
		}
		
		//get verb related to subj.


		if (!predicate.isLinking() && !predicate.isTransitive() && !predicate.isUsedWithPrepPhrase() && addDepClause) {
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
			if (nounClauseInObject) {
				appendNounClause(sentence, plural);
			} else { 
				plur = appendDescriptiveNoun(sentence);
				finLength = sentence.size();
			}
		} else if (predicate.isUsedWithPrepPhrase()) {//add prep phrase if necessary
			plur = appendPrepositionalPhrase(sentence);
			finLength = sentence.size();
		} else if (predicate.isLinking()) {
			sentence.add(getWordOfType(Word.adjective, subj));
			finLength = sentence.size();

		}
		for (int i = initLength; i < finLength; i++) {
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
	 * Appends an noun clause to end of given sentence
	 * @param sentence ArrayList of words to append dependent clause to
	 * @param plural ArrayList of booleans that determines whether each word is plural
	 * (true = plural, false = singular)
	 */
	private static boolean appendNounClause (ArrayList<Word> sentence, ArrayList<Boolean> plural) {
		// (article) noun that verb
		int startSize = sentence.size();
		appendIndependentClause(sentence, plural, false);

		int posOfNoun = -1;//find subject
		for (int i = startSize; i < sentence.size(); i++) {
			if (sentence.get(i) != null && sentence.get(i).getPartOfSpeech() == Word.noun) {
				posOfNoun = i;
				break;
			}
		}
		//add that after noun
		sentence.add(posOfNoun + 1, that);
		plural.add(posOfNoun + 1, false);
		return plural.get(posOfNoun);
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
		double random = Math.random();
		int numAdjs = random < 0.3 ? 0 : (random < 0.95 ? 1 : 2);

		Word lastWord = null;
		if (sentence.size()>0) {//if sentence is not empty, get noun related to last word
			lastWord = sentence.get(sentence.size()-1);
		}

		Noun noun = (Noun) getWordOfType(Word.noun, lastWord);//gets noun related to previous thing in sentence
		boolean plural = noun.isPlural();

		Adjective[] adj = new Adjective[2];
		adj[0] = null;
		for (int i = 0; i<numAdjs; i++) {
			adj[i] = (Adjective) getWordOfType(Word.adjective, noun);//gets adjective(s) related to noun
		}
		if (noun.isUsedWithArticle(plural)) {
			sentence.add(getWordOfType(Word.article, adj[0]));//gets article related to adjective (dont say "a best")
		}
		for (int i = 0; i<numAdjs; i++) {
			sentence.add(adj[i]); 
			if (i < numAdjs - 1) {//add comma
				sentence.add(null);
			}
		}

		sentence.add(noun);

		return plural;
	}
	/**
	 * Used to get a word of a particular part of speech that is related to another
	 * @param type integer representing part of speech
	 * @param related word that return value should be similar to (null gives random word)
	 * @return word of specified type similar to specified word
	 */
	private static Word getWordOfType (int type) {
		//will be updated to use Markov or related
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < listOfWords.size(); i++)
			if (listOfWords.get(i).getPartOfSpeech() == type)
				temp.add(i);
		int index = temp.get((int)(Math.random() * temp.size()));
		return listOfWords.remove(index);
	}
	private static Word getWordOfType (int type, Word related) {
		if (related == null) {
			return getWordOfType(type);
		}
		return getWordOfType(type);//TODO: implement markov
	}
}