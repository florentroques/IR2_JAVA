package fr.upem.poo.td2.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class RomanNumeralsTest {

	@Test
	public void testToInteger1() {
		assertEquals(1, RomanNumerals.toInteger("I"));
		assertEquals(5, RomanNumerals.toInteger("V"));
		assertEquals(10, RomanNumerals.toInteger("X"));
		assertEquals(50, RomanNumerals.toInteger("L"));
		assertEquals(100, RomanNumerals.toInteger("C"));
	}

	@Test
	public void testToInteger2() {
		assertEquals(3, RomanNumerals.toInteger("III"));
		assertEquals(2, RomanNumerals.toInteger("II"));
		assertEquals(15, RomanNumerals.toInteger("XV"));
		assertEquals(6, RomanNumerals.toInteger("VI"));
		assertEquals(167, RomanNumerals.toInteger("CLXVII"));
		assertEquals(26, RomanNumerals.toInteger("XXVI"));
		assertEquals(102, RomanNumerals.toInteger("CII"));
		assertEquals(67, RomanNumerals.toInteger("LXVII"));
		assertEquals(50, RomanNumerals.toInteger("L"));
		assertEquals(102, RomanNumerals.toInteger("CII"));
	}

	@Test
	public void testToRomanNumerals1() {
		assertEquals("I", RomanNumerals.toRomanNumerals(1));
		assertEquals("V", RomanNumerals.toRomanNumerals(5));
		assertEquals("X", RomanNumerals.toRomanNumerals(10));
		assertEquals("L", RomanNumerals.toRomanNumerals(50));
		assertEquals("C", RomanNumerals.toRomanNumerals(100));
	}

	@Test
	public void testToRomanNumerals2() {
		assertEquals("III", RomanNumerals.toRomanNumerals(3));
		assertEquals("CLXVII", RomanNumerals.toRomanNumerals(167));
		assertEquals("XXVI", RomanNumerals.toRomanNumerals(26));
		assertEquals("CII", RomanNumerals.toRomanNumerals(102));
	}

//	@Test(expected = IllegalArgumentException.class)
//	public void testToRomanNumerals3() {
//		assertEquals("III", RomanNumerals.toRomanNumerals(-1));
//	}
//
//	@Test
//	public void testToRomanNumerals4() {
//		try {
//			assertEquals("III", RomanNumerals.toRomanNumerals(-1));
//			fail("toRomanNumerals used with parameter < 0");
//		} catch (Exception e) {
//			// fail("toRomanNumerals used with parameter < 0");
//		}
//	}

//	@Test
//	public void testToIntegerPrefix() {
//		assertEquals(4, RomanNumerals.toIntegerRegex("IV"));
//		assertEquals(90, RomanNumerals.toIntegerRegex("XC"));
//		assertEquals(40, RomanNumerals.toIntegerRegex("XL"));
//		assertEquals(9, RomanNumerals.toIntegerRegex("IX"));
//		assertEquals(299, RomanNumerals.toIntegerRegex("CCXCIX"));
//		assertEquals(3, RomanNumerals.toIntegerRegex("III"));
////		assertEquals(180, RomanNumerals.toIntegerRegex("XCXC"));
//		assertEquals(6, RomanNumerals.toIntegerRegex("VI"));
//		assertEquals(42, RomanNumerals.toIntegerRegex("XLII"));
//	}

//	@Test
//	public void testToRomanNumeralsPrefix() {
//		assertEquals("IV", RomanNumerals.toRomanNumerals(4));
//		assertEquals("XC", RomanNumerals.toRomanNumerals(90));
//		assertEquals("XL", RomanNumerals.toRomanNumerals(40));
//		assertEquals("IX", RomanNumerals.toRomanNumerals(9));
//	}
}
