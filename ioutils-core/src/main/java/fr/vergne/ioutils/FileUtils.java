package fr.vergne.ioutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class FileUtils {

	public static String readFileToString(File file) throws IOException {
		FileReader reader = new FileReader(file);
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[128];
		int read;
		while ((read = reader.read(buffer)) != -1) {
			builder.append(buffer, 0, read);
		}
		reader.close();
		return builder.toString();
	}

	public static void write(File file, String content)
			throws FileNotFoundException {
		PrintStream stream = new PrintStream(file);
		stream.print(content);
		stream.close();
	}
}
