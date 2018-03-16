package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.StringReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class FileParser {

	public FileParser(File inFile, ArrayList<Element> arrayListOfElements) {
		parseFileContent(inFile, arrayListOfElements);
	}

	public static void parseFileContent(File file, ArrayList<Element> arrayListOfElements) {
		BufferedReader bufferReader = null;
		int maxCol = 0;
        try {
            String currentLine = "";
            bufferReader = new BufferedReader(new FileReader(file));
            int row = 0, column = 0;
            while ((currentLine = bufferReader.readLine()) != null) {
            	validateLine(currentLine, row);
            	while (StringUtils.contains(currentLine, " || ")) {
            		arrayListOfElements.add(new Element(StringUtils.substring(currentLine,0,3), StringUtils.substring(currentLine,4,7), row, column));
            		currentLine = StringUtils.substring(currentLine, 11);
            		column++;
            	}
            	if (row == 0) {
            		maxCol = column;
            	}
            	validateColumnNumber(maxCol, column);
            	arrayListOfElements.add(new Element(StringUtils.substring(currentLine,0,3), StringUtils.substring(currentLine,4,7), row, column));
            	row++;
            	column = 0;
            }
        } catch (IOException | InvalidFileContentException | InvalidColumnNumberException e) {
            System.out.println(e);
            System.exit(0);
        } finally {
            try {
                if (bufferReader != null) {
                	bufferReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	public static void validateFile(String fileName) throws InvalidFileInputException {
		if (!(StringUtils.indexOf(fileName, ".txt") == fileName.length()-4)) {
			throw new InvalidFileInputException(" File is not valid.");
		}
		else {
			System.out.println("File format is valid.");
		}
	}

	public static void validateColumnNumber(int maxCol, int column) throws InvalidColumnNumberException {
		if (maxCol != column) {
			throw new InvalidColumnNumberException("Column numbers are not the same.");
		}
	}

	public static void validateLine(String line, int row) throws InvalidFileContentException {
		// Verifies if the line contains an element separator " || "
		while (StringUtils.length(line) > 11 && StringUtils.indexOf(line, " || ") == 7 && line.charAt(3) == ':') {
			line = StringUtils.substring(line,11);
		}
		// Verifies if the line has contains a key value separator ' : '
		if (StringUtils.length(line) == 7 && line.charAt(3) == ':') {
			System.out.println("Line " + (row + 1) + " valid");
		}
		// Throws an exception
		else {
			throw new InvalidFileContentException("Line " + (row + 1) +" is not valid");
		}
	}

	public static void updateFile(File file, ArrayList<Element> arrayListOfElements) {
		BufferedWriter bufferWriter = null;
        try {
            String currentLine = "";
            bufferWriter = new BufferedWriter(new FileWriter(file));
            for (Element element : arrayListOfElements) {
				if (element.getColumn() == 0) {
					if (element.getRow() != 0) {
						bufferWriter.write("\n");
					}
				}
				else {
					bufferWriter.write(" || ");
				}
				bufferWriter.write(element.getKey() + ":" + element.getValue());
			}

        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        } finally {
            try {
                if (bufferWriter != null) {
                	bufferWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}