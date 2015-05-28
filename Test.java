package QuoteGen;

public class Test {
	
	public static void main(String[] args) {
//		
		int time;
		while(true)
		{
			try {
				String quote = SentenceGenerator.generateSentence();
				while(quote.length()>140)
				{
					 quote = SentenceGenerator.generateSentence();
				}
				TweetFunctions.tweet(quote);
				time = (int)(Math.random()*900000)+900000;
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}
			//TESTTESTETSTESTSETTTETTTESTTESTSET
			
		}
	}
	
}
