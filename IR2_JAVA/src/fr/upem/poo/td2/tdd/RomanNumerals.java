package fr.upem.poo.td2.tdd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumerals {
	private static final int[] VALUES = { 100, 90, 50, 40, 10, 9, 5, 4, 1 };
	private static final String[] DIGRAMS = { "C", "XC", "L", "XL", "X", "IX",
			"V", "IV", "I" };
	
//	public static int toInteger(String s) {
//		int size = s.length();
//		int result = 0;
//
//		for (int i = 0; i < size; i++) {
//			char c = s.charAt(i);
//			switch (c) {
//			case 'I':
//				result = 1;
//				break;
//			case 'V':
//				result = 5;
//				break;
//			case 'X':
//				result = 10;
//				break;
//			case 'L':
//				result = 50;
//				break;
//			case 'C':
//				result = 100;
//				break;
//
//			default:
//				throw new IllegalArgumentException("unexpected letter");
//			}
//		}
//
//		return result;
//	}

	public static int toInteger(String s) {
		int size = s.length();
		int result = 0;

		for (int i = 0; i < size; i++) {
			char c = s.charAt(i);
			switch (c) {
			case 'I':
				result += 1;
				break;
			case 'V':
				result += 5;
				break;
			case 'X':
				result += 10;
				break;
			case 'L':
				result += 50;
				break;
			case 'C':
				result += 100;
				break;

			default:
				throw new IllegalArgumentException("unexpected letter");
			}
		}

		return result;
	}
//
//	public static int toIntegerRegex(String s) {
//		if (s.isEmpty()) {
//			throw new IllegalArgumentException("empty string");
//		}
//
//		if (!s.matches("^(C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
//			throw new IllegalArgumentException("unexpected roman numerals");
//		}
//
//		StringBuilder builder = new StringBuilder();
//		for (int i = 0; i < DIGRAMS.length; i++) {
//			builder.append(DIGRAMS[i]);
//
//			if (i < DIGRAMS.length - 1) {
//				builder.append("|");
//			}
//		}
//
//		int result = 0;
//		Matcher matcher = Pattern.compile(builder.toString()).matcher(s); 
//
//		while (matcher.find()) {
//			for (int j = 0; j < DIGRAMS.length; j++) {
//				if (DIGRAMS[j].equals(matcher.group())) {
//					result += VALUES[j];
//				}
//			}
//		}
//
//		return result;
//	}
//

//	public static Object toRomanNumerals(int i) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public static Object toInteger(String string) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	public static String toRomanNumerals(int number) {
		if (number <= 0) {
			throw new IllegalArgumentException("number <= 0");
		}

		StringBuilder sb = new StringBuilder();

//		while (i >= 100) {
//			sb.append('C');
//			i -= 100;
//		}
//
//		if (i >= 50) {
//			sb.append('L');
//			i -= 50;
//		}
//
//		while (i >= 10) {
//			sb.append('X');
//			i -= 10;
//		}
//
//		if (i >= 5) {
//			sb.append('V');
//			i -= 5;
//		}
//
//		while (i >= 1) {
//			sb.append('I');
//			i -= 1;
//		}

		for (int i = 0; i < VALUES.length; i++) {
			int val = VALUES[i];
			while (number >= val) {
				sb.append(DIGRAMS[i]);
				number -= val;
			}
		}

		return sb.toString();
	}
}
