package fr.vergne.ioutils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class StringUtils {

	public static String join(Collection<? extends Object> elements,
			String separator) {
		Iterator<? extends Object> iterator = elements.iterator();
		if (iterator.hasNext()) {
			StringBuilder builder = new StringBuilder();
			builder.append(iterator.next().toString());
			while (iterator.hasNext()) {
				builder.append(separator);
				builder.append(iterator.next().toString());
			}
			return builder.toString();
		} else {
			return "";
		}
	}

	public static String join(Collection<? extends Object> elements) {
		return join(elements, "");
	}

	public static String readFromFile(File file) throws IOException {
		return FileUtils.readFileToString(file);
	}

	public static String readFromInputStream(InputStream stream, Charset charset)
			throws IOException {
		List<Byte> bytes = new LinkedList<>();
		byte[] buffer = new byte[128];
		int read;
		while ((read = stream.read(buffer)) != -1) {
			for (int i = 0; i < read; i++) {
				bytes.add(buffer[i]);
			}
		}

		int total = bytes.size();
		buffer = new byte[total];
		for (int i = 0; i < total; i++) {
			buffer[i] = bytes.remove(0);
		}
		
		if (charset == null) {
			return new String(buffer);
		} else {
			return new String(buffer, charset);
		}
	}

	public static String readFromInputStream(InputStream stream)
			throws IOException {
		return readFromInputStream(stream, null);
	}
}
