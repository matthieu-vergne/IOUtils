package fr.vergne.ioutils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class FileUtilsTest {

	@Test
	public void testReadFileToStringProvidesCorrectSimpleContent()
			throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("Introduce some contents\n");
		builder.append("which should not provide\n");
		builder.append("any problem.");
		String expected = builder.toString();

		File file = File.createTempFile("test", ".txt");
		PrintStream stream = new PrintStream(file);
		stream.print(expected);
		stream.close();

		assertEquals(expected, FileUtils.readFileToString(file));
	}

	@Test
	public void testReadFileToStringProvidesCorrectUTF8Content()
			throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("Introduce some オンテント\n");
		builder.append("for which UTF-8 is a 必要な encoding.");
		String expected = builder.toString();

		File file = File.createTempFile("test", ".txt");
		PrintStream stream = new PrintStream(file, "UTF-8");
		stream.print(expected);
		stream.close();

		assertEquals(expected, FileUtils.readFileToString(file));
	}

	@Test
	public void testWriteProperlyFillTheFile() throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("Introduce some オンテント\n");
		builder.append("for which UTF-8 is a 必要な encoding.");
		String expected = builder.toString();

		File file = File.createTempFile("test", ".txt");
		FileUtils.write(file, expected);

		assertEquals(expected, FileUtils.readFileToString(file));
	}
}
