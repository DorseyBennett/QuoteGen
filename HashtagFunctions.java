package QuoteGen;

public class HashtagFunctions {

	/**
	 * Returns relevant hashtags based on input String
	 * 
	 * @param String
	 *            to generate hashtags from
	 */
	public static String generateRelevantHashtag(String toGetFrom) {
		toGetFrom = toGetFrom.toLowerCase();

		String toAdd = "";

		if (toGetFrom.indexOf("teach") != -1
				|| toGetFrom.indexOf("taught") != -1
				|| toGetFrom.indexOf("learn") != -1)
			if (Math.random() < 0.25)
				toAdd += " #education";

		if (toGetFrom.indexOf("fail") != -1)
			if (Math.random() < 0.25)
				toAdd += " #fail";

		if (toGetFrom.indexOf("change") != -1)
			if (Math.random() < 0.25)
				toAdd += " #4change";

		if (toGetFrom.indexOf("follow") != -1)
			if (Math.random() < 0.25)
				toAdd += " #followme";

		if (toGetFrom.indexOf("love") != -1)
			if (Math.random() < 0.25)
				toAdd += " #love";

		if (toGetFrom.indexOf("hope") != -1)
			if (Math.random() < 0.25)
				toAdd += " #hope";
		
		if (toGetFrom.indexOf("fool") != -1)
			if (Math.random() < 0.25)
				toAdd += " #fools";

		if (toGetFrom.indexOf("brother") != -1)
			if (Math.random() < 0.25)
				toAdd += " #brothers";
		
		if (toGetFrom.indexOf("sister") != -1)
			if (Math.random() < 0.25)
				toAdd += " #sisters";
		
		if (toGetFrom.indexOf("friend") != -1)
			if (Math.random() < 0.25)
				toAdd += " #friends";
		
		if (toGetFrom.indexOf("hate") != -1)
			if (Math.random() < 0.25)
				toAdd += " #hate";
		
		if (toGetFrom.indexOf("climb") != -1)
			if (Math.random() < 0.25)
				toAdd += " #climb";
		
		if (toGetFrom.indexOf("bless") != -1)
			if (Math.random() < 0.25)
				toAdd += " #blessed";
		
		if (toGetFrom.indexOf("beau") != -1)
			if (Math.random() < 0.25)
				toAdd += " #beautiful";
		
		if (toGetFrom.indexOf("happ") != -1)
		{
			if (Math.random() < 0.2)
				toAdd += " #happiness";
		if (Math.random() < 0.2)
			toAdd += " #happy";
		}
		
		if (toGetFrom.indexOf("foll") != -1)
		{
			if (Math.random()<.2)
				toAdd += " #follow";
			else if (Math.random()<.2)
				toAdd += " #followme";
		}
		
		if (Math.random() < 0.05)
			toAdd += " #inspiration";

		if (Math.random() < 0.05)
			toAdd += " #inspired";

		if (Math.random() < 0.07)
			toAdd += " #inspire";

		if (Math.random() < 0.05)
			toAdd += " #quotes";

		if (Math.random() < 0.05)
			toAdd += " #quoteoftheday";
		else if (Math.random() < 0.05)
			toAdd += " #qotd";

		if (Math.random() < 0.05)
			toAdd += " #lifequotes";

		if (Math.random() < 0.05)
			toAdd += " #motivationalquotes";

		if (Math.random() < 0.05)
			toAdd += " #inspirationalquotes";

		return toAdd;
	}

}
