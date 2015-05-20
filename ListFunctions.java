package QuoteGen;

public class ListFunctions {
	
	public static int getRandomIndex(String[] list) {
		int amount = list.length;

		int randomPos = (int) (Math.random() * amount);

		return randomPos;

	}
	
	public static int getSimilarIndex(String[] list, int index)
	{
		
		double offset = -list.length / 2;
		
		
		for(int i = 0; i < list.length; i++)
			offset += Math.random();
		
		index += (int) (offset + (Math.abs(offset)/offset));
		
		return (index + list.length)%list.length;
		
	}
	
}
