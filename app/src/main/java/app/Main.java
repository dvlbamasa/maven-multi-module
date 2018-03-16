package app;

import java.io.File;

public class Main {

     public static void main(String[] args) {
		try {
			FileParser.validateFile(args[0]);
	  		new RandomizerApp(new File(args[0])); 
		} catch (InvalidFileInputException | ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}
    }
}

