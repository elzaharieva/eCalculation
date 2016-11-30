package bg.uni_sofia.fmi.rsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Writer {
	private File file;
	
	Writer(String filename) {
		this.file = new File(filename);
	}
	
	public void writeToFile() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		writer.println(Main.e);
		writer.close();
	}
	
}
