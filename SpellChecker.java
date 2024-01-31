
public class SpellChecker {


	public static void main(String[] args) 
	{
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) 
	{
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) 
	{
		word1.toLowerCase();
		word2.toLowerCase();
		if ( word1.isEmpty())
		{
			return word2.length();
		}
		if ( word2.isEmpty())
		{
			return word1.length();
		}
		int num = 0;
        if(word1.charAt(0) == word2.charAt(0)) 
        {
            num = 0;
        } 
        else 
        {
            num = 1;
        }

        int insertion = levenshtein(word1, tail(word2)) + 1;
        int deletion = levenshtein(tail(word1), word2) + 1;
        int substitution = levenshtein(tail(word1), tail(word2)) + num;
        return Math.min(insertion, Math.min(deletion, substitution));
	}

	public static String[] readDictionary(String fileName) 
	{
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		String stringline = "";
		for ( int i = 0; i < 3000; i++)
		{
			stringline = in.readLine();
			dictionary[i] = stringline;
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) 
	{
		String str = "";
		int minDis = levenshtein(word, dictionary[0]);
		str = dictionary[0];
		for ( int i = 1; i < dictionary.length; i++)
		{
			if( levenshtein(word, dictionary[i]) < minDis)
			{
				str = dictionary[i];
				minDis = levenshtein(word, dictionary[i]);
			}
		}
		if(minDis <= threshold)
		{
			return str;
		}
		else
		{
			return word;
		}
	}

}
