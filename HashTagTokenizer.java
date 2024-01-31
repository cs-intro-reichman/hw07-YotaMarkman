

public class HashTagTokenizer
 {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}
	//*  This function, public static String[] readDictionary(String fileName), is designed
//to read a local file of words, and store them in an array. You have been provided with a file named//
//“dictionary.txt”, which contains the 3,000 most frequently used words in English. The structure
//of the file is straightforward, with each line representing a single word. The function starts by building
//an array of 3,000 strings. It then reads each line from the file, and stores it in the array (one after the
//other, in the order in which they are read). To implement the reading, use the In class, as we did in
//previous homeworks and lectures. The given HashTagTokenizer.java file contains a partial
//implementation of this function. Please complete the implementation.

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
	//* The function public static boolean existInDictionary(String word, String[]
	//dictionary) is designed to take a string as an input and determine its presence in the dictionary. It
	//returns true if the word is found within the dictionary array, and false if it is not. 
	public static boolean existInDictionary(String word, String[] dictionary) 
	{
		boolean isword = false;
		for ( int i  = 0; i < dictionary.length; i++)
		{
			if ( word.equals(dictionary[i]))
			{
				isword = true;
				break;
			}
		}
		return isword;
	}
	//This function receives two inputs: a hashtag (as a String) and a dictionary (an array of String). Its
	//purpose is to print each word embedded within the hashtag on a separate line. The primary approach
	//taken here is to incrementally analyze prefixes of the hashtag, starting with the first character (which
	//can be obtained using hashtag.substring(0,1)) and gradually extending the length of the prefix.
	//Initially, the function checks if the single-character prefix is a valid word in the dictionary, using the
	//existInDictionary method. If this prefix is indeed a word, it is printed, and the function
	//recursively calls itself, this time with the hashtag minus its first character. If the prefix is not a valid
	//word, the prefix length is increased by one character (now analyzing hashtag.substring(0, 2),
	//and the process repeats
	public static void breakHashTag(String hashtag, String[] dictionary) 
	{

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) 
		{
            return;
        }
		hashtag.toLowerCase();
        int N = hashtag.length();
		String word = ""; 
        for (int i = 1; i <= N; i++) 
		{
			word = "";
			if ( existInDictionary(hashtag.substring(0,i), dictionary) == true)
			{
				word = hashtag.substring(0,i);
				System.out.println(word);
				breakHashTag(hashtag.substring(i), dictionary);
				return;
			}
        }
    }

}
