package bg.uni_sofia.fmi.rsa;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
	static BigDecimal e = new BigDecimal(0.0);
	public int maxThreadNumber = 1;
	public int precision;
	public String filename = "e-number.txt";
	public boolean isQuiet = false;
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		long startRunTime = System.currentTimeMillis();
		
		CommandLineParser parser = new GnuParser();
		CommandLine cmd = null;
		Options options = setOptions();
		Main mainObject = new Main();

		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("t") || cmd.hasOption("tasks")) {
				mainObject.maxThreadNumber = Integer.parseInt(cmd.getOptionValue("t"));
			}
			
			if (cmd.hasOption("q")) {
				mainObject.isQuiet = true;
			}

			if (cmd.hasOption("o")) {
				mainObject.filename = cmd.getOptionValue("o");
			}
			
			if (cmd.hasOption("p")) {
				mainObject.precision = Integer.parseInt(cmd.getOptionValue("p"));
				
				ThreadExecutor calculator = new ThreadExecutor(mainObject.maxThreadNumber, mainObject.precision);
				calculator.calculateE(mainObject.isQuiet);
				
				Writer writer = new Writer(mainObject.filename);
				writer.writeToFile();
				
			} else {
				throw new IllegalArgumentException();
			}
			
		} catch (ParseException e) {
			System.out.println("Not valid arguments" + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Enter p argument" + e.getMessage());
		}
		
		long endRunTime = System.currentTimeMillis();
		System.out.println("Total execution time for current run (millis): "+(endRunTime-startRunTime));
		
		if (!mainObject.isQuiet) {
			System.out.println("Threads used in current run: "+mainObject.maxThreadNumber);
		}
//		System.out.println(e);
		
	}

	public static Options setOptions() {
		Options option = new Options();

		option.addOption("t", "tasks", true, "Number of threads")
		.addOption("p", true, "Set prescision")
		.addOption("q", false, "Quite mode")
		.addOption("o", true, "File with the result");

		return option;
	}

}
