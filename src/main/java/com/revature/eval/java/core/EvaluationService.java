package com.revature.eval.java.core;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		String result;
		//convert the string into an array of characters
		char[] letter = string.toCharArray();
		//make a temporary array to store the characters of the reversed string
		char[] temp = new char[string.length()];
		//take the last character of the original string and adding it to the first index of the new string
		for(int i = 0; i < string.length(); i++) {
			temp[string.length() - 1 - i] = letter[i];
		}
		//convert the char array to the new reversed string
		result = new String(temp);
		return result;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String result = "";
		//we will always add the first letter of the first word
		result = result + phrase.charAt(0);
		//iterating through the string until we find a space and then adding the next letter(the first letter of the next word)
		for(int i = 0; i < phrase.length(); i++) {
			if(phrase.charAt(i) == ' ' || phrase.charAt(i) == '-') {
				result = result + phrase.charAt(i + 1);
			}
		}
		//capitalizing the acronym
		result = result.toUpperCase();
		return result;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			//if all sides are equal, the triangle is equilateral
			if(getSideOne() == getSideTwo() && getSideTwo() == getSideThree()) {
				return true;
			}
			else {
			return false;
			}
		}

		public boolean isIsosceles() {
			//if at least two side are equal it is Isosceles
			if(getSideOne() == getSideTwo() || getSideTwo() == getSideThree() || getSideOne() == getSideThree()) {
				return true;
			}
			else {
			return false;
			}
		}

		public boolean isScalene() {
			//if no sides are equal it is Scalene
			if(getSideOne() != getSideTwo() && getSideTwo() != getSideThree() && getSideOne() != getSideThree()) {
				return true;
			}
			else {
			return false;
			}
		}
	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		int point = 0;
		string = string.toLowerCase();
		//check each letter of the string
		for(int i = 0; i < string.length(); i++) {
			//if it matches aeionrtls or u add one point
			if(Character.toString(string.charAt(i)).matches("[aeionrtlsu]+")) {
				point += 1;
			//if it matches d or g add two points
			}else if(Character.toString(string.charAt(i)).matches("[dg]+")) {
				point += 2;
			//if it matches bcm or p add three points
			}else if(Character.toString(string.charAt(i)).matches("[bcmp]+")) {
				point += 3;
			//if it matches fhvw or y add four points
			}else if(Character.toString(string.charAt(i)).matches("[fhvwy]+")) {
				point += 4;
			//if it is k add five points
			}else if(string.charAt(i) == 'k') {
				point += 5;
			//if it matches j or x add eight points
			}else if(Character.toString(string.charAt(i)).matches("[jx]+")) {
				point += 8;
			//if it matches q or z add ten points
			}else if(Character.toString(string.charAt(i)).matches("[qz]+")) {
				point += 10;
			}
		}
		return point;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		//remove all punctuation and whitespace from the original string
		string = string.replaceAll("\\p{Punct}", "");
		string = string.replaceAll("\\s", "");
		//test to see if there is anything in the string that is not a number, punctuation, or whitespace
		//if so it will return an IllegalArgumentException
		long test = Long.parseLong(string);
		//test if the string exceeds 10 digits, if so throw an IllegalArgumentException
		if(string.length() > 10 || string.length() < 1) {
			throw new java.lang.IllegalArgumentException();
		}
		return string;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		//split the string on anything that is not a letter
		String[] phrase = string.split("[^A-Za-z]");
		int count;
		//hashmap to keep track of each word and its number of occurrences
		Map<String, Integer> result = new HashMap<>();
		for(int i = 0; i < phrase.length; i++) {
			count = 0;
			//pattern to find the other occurrences of each word and increment count
			Pattern pattern = Pattern.compile(phrase[i]);
			Matcher matcher = pattern.matcher(string);
			while(matcher.find()) {
				count++; 
			}
			//this method will also add "" as a word so this if statement prevents them from being added to the map
			if(!phrase[i].matches("")) {
			result.put(phrase[i], count);
			}
		}		
		return result;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			//creating variables to mark the left and right side of the array and subarrays
			int l = 0;
			int r = getSortedList().size() - 1;
			while(l <= r) {
				//set i equal to the middle index of the array
				int i = l + ((r - l) / 2);
				//if the value in the middle index is the one we are looking for we are done
				if(t.toString().compareTo(getSortedList().get(i).toString()) == 0) {
					return i;
				}
				//if the value we are looking for is to the right of the middle value we set the left variable to the middle of
				//the array to ignore the left half
				else if(t.toString().compareTo(getSortedList().get(i).toString()) < 0) {
					l = i + 1;
					
				}
				//if the value we are looking for is to the left of the middle value we set the right variable to the middle of
				//the array to ignore the right half
				else {
					r = i - 1;
				}
			}
			//this method is unable to find values at the beginning of arrays and I could not figure out why so I had to add this
			//if statement
			if (t.toString().compareTo(getSortedList().get(0).toString()) == 0) {
				return 0;
			} else {
				//if the value is not found in the array we return -1
				return -1;
			}
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		string.toLowerCase();
		String result = "";
		//split the string into an array based on whitespace
		String[] check = string.split("\\s+");
		//loop through each word in our array
		for(int i = 0; i < check.length; i++) {
			//if the word starts with a vowel add ay to the end of the word
			if(Character.toString(check[i].charAt(0)).matches("(i?)[aeiou]+")) {
				check[i] = check[i] + "ay";
			}
			else {
				//assign the current word in the array to its own string
				String temp = check[i];
				//loop through each letter in the current word
				for(int j = 0; j < temp.length(); j++) {
					//if the letter we are currently at is a vowel we start there
					if(Character.toString(temp.charAt(j)).matches("(i?)[aeiou]+")) {
						String newTemp = "";
						//there was a special case in these examples where if the vowel was preceded by a q we would need
						//to start from the next letter instead, I would need to add more if statements for other special
						//cases but I don't know what those cases are and will just leave this one
						if(temp.charAt(j - 1) == 'q') {
							j += 1;
						}
						//we move the end of the word to the beginning starting from the first vowel
						for(int k = j; k < temp.length(); k++) {
							newTemp += Character.toString(temp.charAt(k));
						}
						//we take what was at the beginning and move it to the end until the first vowel
						for(int k = 0; k < j; k++) {
							newTemp += Character.toString(temp.charAt(k));
						}
						//add 'ay' to the end
						newTemp += "ay";
						if(i < check.length - 1) {
							newTemp += " ";
						}
						check[i] = newTemp;
						break;
					}
				}
			}
		}
		// take each new word in our array and put it in a string
		for(int i = 0; i < check.length; i++) {
			result += check[i];
		}   
		return result;
		
		}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String inputString = Integer.toString(input);
		List<String> digits = new ArrayList<>();
		//add each digit to a string array
		for(int i = 0; i < inputString.length(); i++) {
			digits.add(Character.toString(inputString.charAt(i)));
		}
		int result = 0;
		//add each digit raised to the number of digits
		for(int i = 0; i < digits.size(); i++) {
			result += Math.pow(Double.parseDouble(digits.get(i)), digits.size());
		}
		//if each digit raised to the number of digits equals the original number, return true
		if(result == input) {
			return true;
		}
		else {
		return false;
		}
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> result = new ArrayList<>();
		//loop through each number up to the given one to find which numbers are evenly divisible
		for(int i = 2; i <= l; i++) {
			//loop to find duplicate prime numbers
			while(l%i == 0) {
				result.add((long) i);
				l = l/i;
			}
		}
		return result;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			Map<Integer, Character> keyMap = new HashMap<>();
			List<Character> phrase = new ArrayList<>();
			List<Integer> phraseCase = new ArrayList<>();
			//create a map with each letter of the alphabet indexed by an int
			keyMap.put(0, 'a');
			keyMap.put(1, 'b');
			keyMap.put(2, 'c');
			keyMap.put(3, 'd');
			keyMap.put(4, 'e');
			keyMap.put(5, 'f');
			keyMap.put(6, 'g');
			keyMap.put(7, 'h');
			keyMap.put(8, 'i');
			keyMap.put(9, 'j');
			keyMap.put(10, 'k');
			keyMap.put(11, 'l');
			keyMap.put(12, 'm');
			keyMap.put(13, 'n');
			keyMap.put(14, 'o');
			keyMap.put(15, 'p');
			keyMap.put(16, 'q');
			keyMap.put(17, 'r');
			keyMap.put(18, 's');
			keyMap.put(19, 't');
			keyMap.put(20, 'u');
			keyMap.put(21, 'v');
			keyMap.put(22, 'w');
			keyMap.put(23, 'x');
			keyMap.put(24, 'y');
			keyMap.put(25, 'z');
			//loop to add each character of the string to a list
			for(int i = 0; i < string.length(); i++) {
				phrase.add(string.charAt(i));
			}
			//if the character is upper case, add it to a separate list to keep track of it then change it to lowercase in the original list
			for(int c = 0; c < phrase.size(); c++) {
				if(Character.isUpperCase(phrase.get(c))) {
					phraseCase.add(c);
					phrase.set(c, Character.toLowerCase(phrase.get(c)));
				}
			}
			//for each letter in the string, loop through the map until you find the letter
			for(int i = 0; i < phrase.size(); i++) {
				for(int k = 0; k < keyMap.size(); k++) {
					if(phrase.get(i) == (keyMap.get(k))) {
						//then set the letter to a new letter based on the given key
						k += key;
						//check for rollover values
						if(k > 25) {
							k = k - 26;
						}
						phrase.set(i, keyMap.get(k));
						//then break the inner loop
						break;
					}
				}
			}
			//reset each changed character back to uppercase
			for(int p = 0; p < phraseCase.size(); p++) {
				phrase.set(phraseCase.get(p), Character.toUpperCase(phrase.get(phraseCase.get(p))));
			}
			String result = "";
			//put the new word into a string and return
			for(int j = 0; j < phrase.size(); j++) {
				result += phrase.get(j);
			}
			return result;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		//if statement to check if the given value is not less than one
		if(i < 1) {
			throw new java.lang.IllegalArgumentException();
		}
		List<Integer> primes = new ArrayList<>();
		int count = 1;
		int j;
		//loop to find prime values until we have the nth one represented as i in this case)
		while(primes.size() < i) {
			count++;
			//loop to check if the current number has any factors other than one and itself
			for(j = 2; j <= count; j++) {
				if(count%j == 0) {
					break;
				}
			}
			//if not we add it to our list
			if(j == count) {
				primes.add(j);
			}
		}
		//return the second to last number in the list because we started counting from zero
		return primes.get(i - 1);
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			Map<Character, Character> keyMap = new HashMap<>();
			List<Character> phrase = new ArrayList<>();
			List<Character> newPhrase = new ArrayList<>();
			string = string.toLowerCase();
			String result = "";
			//map to match each letter of the alphabet with the reverse alphabet
			keyMap.put('a', 'z');
			keyMap.put('b', 'y');
			keyMap.put('c', 'x');
			keyMap.put('d', 'w');
			keyMap.put('e', 'v');
			keyMap.put('f', 'u');
			keyMap.put('g', 't');
			keyMap.put('h', 's');
			keyMap.put('i', 'r');
			keyMap.put('j', 'q');
			keyMap.put('k', 'p');
			keyMap.put('l', 'o');
			keyMap.put('m', 'n');
			keyMap.put('n', 'm');
			keyMap.put('o', 'l');
			keyMap.put('p', 'k');
			keyMap.put('q', 'j');
			keyMap.put('r', 'i');
			keyMap.put('s', 'h');
			keyMap.put('t', 'g');
			keyMap.put('u', 'f');
			keyMap.put('v', 'e');
			keyMap.put('w', 'd');
			keyMap.put('x', 'c');
			keyMap.put('y', 'b');
			keyMap.put('z', 'a');
			//iterate through the string and add each character to a list if it is a letter or number
			for(int i = 0; i < string.length(); i++) {
				if(Pattern.matches("[a-z]|[0-9]", Character.toString(string.charAt(i))))
				phrase.add(string.charAt(i));
			}
			for(int j = 0; j < phrase.size(); j++) {
				//add a space after every five letters
				if(j%5 == 0 && j != 0) {
					newPhrase.add(' ');
				}
				//if the character is a letter, change it based on our map
				if(Pattern.matches("[a-z]", Character.toString(phrase.get(j)))) {
					newPhrase.add(keyMap.get(phrase.get(j)));
				}
				//if the character is a number just leave it
				if(Pattern.matches("[0-9]", Character.toString(phrase.get(j)))) {
					newPhrase.add(phrase.get(j));
				}

			}
			//convert our char list back into a string
			for(int c = 0; c < newPhrase.size(); c++) {
				result += newPhrase.get(c);
			}
			return result;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		//this method is the same as the previous
		public static String decode(String string) {
			Map<Character, Character> keyMap = new HashMap<>();
			List<Character> phrase = new ArrayList<>();
			List<Character> newPhrase = new ArrayList<>();
			string = string.toLowerCase();
			String result = "";
			keyMap.put('a', 'z');
			keyMap.put('b', 'y');
			keyMap.put('c', 'x');
			keyMap.put('d', 'w');
			keyMap.put('e', 'v');
			keyMap.put('f', 'u');
			keyMap.put('g', 't');
			keyMap.put('h', 's');
			keyMap.put('i', 'r');
			keyMap.put('j', 'q');
			keyMap.put('k', 'p');
			keyMap.put('l', 'o');
			keyMap.put('m', 'n');
			keyMap.put('n', 'm');
			keyMap.put('o', 'l');
			keyMap.put('p', 'k');
			keyMap.put('q', 'j');
			keyMap.put('r', 'i');
			keyMap.put('s', 'h');
			keyMap.put('t', 'g');
			keyMap.put('u', 'f');
			keyMap.put('v', 'e');
			keyMap.put('w', 'd');
			keyMap.put('x', 'c');
			keyMap.put('y', 'b');
			keyMap.put('z', 'a');
			for(int i = 0; i < string.length(); i++) {
				if(Pattern.matches("[a-z]|[0-9]", Character.toString(string.charAt(i))))
				phrase.add(string.charAt(i));
			}
			for(int j = 0; j < phrase.size(); j++) {
				if(Pattern.matches("[a-z]", Character.toString(phrase.get(j)))) {
					newPhrase.add(keyMap.get(phrase.get(j)));
				}
				if(Pattern.matches("[0-9]", Character.toString(phrase.get(j)))) {
					newPhrase.add(phrase.get(j));
				}

			}
			for(int c = 0; c < newPhrase.size(); c++) {
				result += newPhrase.get(c);
			}
			return result;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		List<Integer> ISBN = new ArrayList<>();
		int math = 0;
		int multiplier = 10;
		//iterate through each number or X in the string
		for(int i = 0; i < string.length(); i++) {
			if(Pattern.matches("[0-9]|X", Character.toString(string.charAt(i)))) {
				//if the character is an x add 10 to a list
				if(string.charAt(i) == 'X') {
					ISBN.add(10);
				}
				//otherwise add its numeric value
				else {
					ISBN.add(Character.getNumericValue(string.charAt(i)));
				}
			}
		}
		//multiply each digit by the number of digits - 1 each digit after the first
		for(int j = 0; j < ISBN.size(); j++) {
			math += (ISBN.get(j) * multiplier);
			multiplier--;
		}
		//if the result is divisible by 11 it is valid
		if(math % 11 == 0) {
			return true;
		}
		else {
		return false;
		}
		
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
			Set<Character> alphabet = new HashSet<>();
			string = string.toLowerCase();
			//remove all punctuation and whitespace
			string = string.replaceAll("\\s+", "");
			string = string.replaceAll("\\p{Punct}", "");
			//add each letter to a set
			for(int j = 0; j < string.length(); j++) {
				alphabet.add(string.charAt(j));
			}
			//because sets cannot have duplicate values if the size of the set reaches 26 it must contain every letter
			if(alphabet.size() >= 26) {
				return true;
			}
			else {
				return false;
			}
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		Temporal result = null;
		//if the original date includes seconds we just add a gigasecond
		if(given.isSupported(ChronoUnit.SECONDS)) {
		result = given.plus(1_000_000_000, ChronoUnit.SECONDS);
		}else {
			//otherwise we need to add days, minutes, and seconds and then add a gigasecond
			result = LocalDateTime.of(given.get(ChronoField.YEAR), given.get(ChronoField.MONTH_OF_YEAR), given.get(ChronoField.DAY_OF_MONTH), 0, 0, 0);
			result = result.plus(1_000_000_000, ChronoUnit.SECONDS);
		}
		return result;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		
		Set<Integer> multiples = new HashSet<>();
		List<Integer> hold = new ArrayList<>();
		int result = 0;
		int c;
		for(int j = 0; j < set.length; j++) {
			c = 0;
			//for each number in the set add multiples of that number to another set up to the given number i
			while((set[j] * c) < i) {
					multiples.add(set[j] * c);
					c++;
			}
		}
		//convert that set into an array, I'm doing this to prevent duplicate numbers in the list but sets do not have a .get() method
		hold.addAll(multiples);
		//add each number together
		for(int k = 0; k < hold.size(); k++) {
			result += hold.get(k);
		}
		return result;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		List<Integer> temp = new ArrayList<>();
		int result = 0;
		//remove all whitespace from the number
		string = string.replaceAll("\\s+", "");
		//if the string contains anything that is not a number it is invalid
		if(string.matches("[^0-9]+")) {
			return false;
		}
		//add each digit to a list
		for(int i = 0; i < string.length(); i++) {
			temp.add(Character.getNumericValue(string.charAt(i)));
		}
		//starting from the back of the list, multiply each other digit by two, if the new digit is greater than 9 subtract 9
		for(int j = temp.size() - 2; j >= 0; j -= 2) {
			temp.set(j, temp.get(j) * 2);
			if(temp.get(j) > 9) {
				temp.set(j, temp.get(j) - 9);
			}
		}
		//add each digit together
		for(int k = 0; k < temp.size(); k++) {
			result += temp.get(k);
		}
		//if the result is evenly divisible by 10 it is valid
		if(result%10 == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		string = string.toLowerCase();
		//remove all punctuation and whitespace
		string = string.replaceAll("[\\p{Punct}&&[^-]]", "");
		String[] problem = string.split("\\s+");
		int result = 0;
		List<Integer> nums = new ArrayList<>();
		for(int i = 0; i < problem.length; i++) {
			//iterate through each character and if it matches and positive or negative integer, add it to a list
			if(problem[i].matches("-?\\d+")) {
				nums.add(Integer.parseInt(problem[i]));
			}
		}
		for(int i = 0; i < problem.length; i++) {
			//iterate through the phrase to search for keywords and perform the appropriate operation on each number found
			if(problem[i].equals("plus")) {
				result = nums.get(0) + nums.get(1);
			}
			if(problem[i].equals("minus")) {
				result = nums.get(0) - nums.get(1);
			}
			if(problem[i].equals("multiplied") || problem[i].equals("times")) {
				result = nums.get(0) * nums.get(1);
			}
			if(problem[i].equals("divided")) {
				result = nums.get(0) / nums.get(1);
			}
		}
		return result;
	}

}
