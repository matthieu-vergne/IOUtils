package fr.vergne.ioutils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testJoinProperlyJoinStrings() {
		assertEquals("abc", StringUtils.join(Arrays.asList("a", "b", "c")));
		assertEquals("abc",
				StringUtils.join(Arrays.asList("", "a", "", "b", "", "c", "")));
	}

	@Test
	public void testJoinProperlyJoinUTF8Strings() {
		assertEquals("あいうえお",
				StringUtils.join(Arrays.asList("あ", "い", "う", "え", "お")));
	}

	@Test
	public void testJoinProperlyJoinObjects() {
		assertEquals("123", StringUtils.join(Arrays.asList(1, 2, 3)));
	}

	@Test
	public void testJoinProperlyJoinStringsWithSeparator() {
		assertEquals("a,b,c",
				StringUtils.join(Arrays.asList("a", "b", "c"), ","));
		assertEquals(",a,,b,,c,", StringUtils.join(
				Arrays.asList("", "a", "", "b", "", "c", ""), ","));
	}

	@Test
	public void testJoinProperlyJoinUTF8StringsWithSeparator() {
		assertEquals("あ、い、う、え、お",
				StringUtils.join(Arrays.asList("あ", "い", "う", "え", "お"), "、"));
	}

	@Test
	public void testJoinProperlyJoinObjectsWithSeparator() {
		assertEquals("1+2+3", StringUtils.join(Arrays.asList(1, 2, 3), "+"));
	}

	@Test
	public void testReadFromInputStreamReturnsCorrectSimpleString()
			throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("Introduce some contents\n");
		builder.append("which should not provide\n");
		builder.append("any problem.");
		String expected = builder.toString();

		File file = File.createTempFile("test", ".txt");
		FileUtils.write(file, expected);
		InputStream stream = new FileInputStream(file);
		assertEquals(expected, StringUtils.readFromInputStream(stream));
	}

	@Test
	public void testReadFromInputStreamReturnsCorrectUTF8String()
			throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("Introduce some オンテント\n");
		builder.append("for which UTF-8 is a 必要な encoding.");
		String expected = builder.toString();

		File file = File.createTempFile("test", ".txt");
		FileUtils.write(file, expected);
		InputStream stream = new FileInputStream(file);
		assertEquals(
				expected,
				StringUtils.readFromInputStream(stream,
						Charset.forName("UTF-8")));
	}
}
