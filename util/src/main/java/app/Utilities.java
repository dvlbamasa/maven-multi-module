package app;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;

public class Utilities {
	private static Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
	private static Random random = new Random();	

	public static void printArray(ArrayList<Element> arrayListOfElements) {
		System.out.println("\n\n*******ASCII TABLE*********\n\n");
		for (Element element : arrayListOfElements) {
			if (element.getColumn() == 0) {
				if (element.getRow() != 0) {
					System.out.println("");
				}
			}
			else {
				System.out.print(" || ");
			}
			System.out.print(element.getKey() + ":" + element.getValue());
		}
	}

	public static void search(ArrayList<Element> arrayListOfElements) {
		boolean stringFound = false;
		String keyValueString, stringPattern;
		System.out.print("Enter the ASCII characters to be searched: ");
		stringPattern = scanner.nextLine();
		for (Element element : arrayListOfElements) {
			keyValueString = "".concat(element.getKey()).concat(element.getValue());
			if (keyValueString.contains(stringPattern)) {
				System.out.println("\n\n\n***\"" + stringPattern + "\" can be found at row " + element.getRow() + ", column " + element.getColumn() + ".");
				System.out.println("   \"" + stringPattern + "\" has " + StringUtils.countMatches(keyValueString, stringPattern) + (StringUtils.countMatches(keyValueString, stringPattern) > 1 ? " matches" : " match") + " on the searched string.");
				stringFound = true;
			}
		}
		if (!stringFound) {
			System.out.println("\n\n\n***\"" + stringPattern + "\" was not found on the array.");
		}
	}

	public static void edit(ArrayList<Element> arrayListOfElements) {
		boolean elementDetected = false;
		try {
			System.out.print("Enter the row number to be edited: ");
			int row = scanner.nextInt();
			System.out.print("Enter the column number to be edited: ");
			int column = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Enter the new ASCII characters for its key: ");
			String newKey = scanner.nextLine();
			System.out.print("Enter the new ASCII characters for its value: ");
			String newValue = scanner.nextLine();
			if (StringUtils.length(newKey) == 3 && StringUtils.length(newValue) == 3) {
				for (Element element : arrayListOfElements) {
					if (element.getRow() == row && element.getColumn() == column) {
						element.setKey(newKey);
						element.setValue(newValue);
						elementDetected = true;
					}
				}
				if (elementDetected) {
					System.out.println("\n\n\n***Array element successfully edited.");
				}
				else {
					System.out.println("\n\n\n***Wrong array index!");
				}
			}
			else {
				System.out.println("\n\n\n***The new ASCII characters should be equal to 3.");		
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("\n\n\n***Wrong array index!");
		} catch (java.util.InputMismatchException e) {
			System.out.println("\n\n\n***Wrong input!");
			scanner.nextLine();
		}
	}

	public static void addRow(ArrayList<Element> arrayListOfElements) {
		boolean inputInvalid = true;
		int maxRow = getMaxRow(arrayListOfElements) + 1;
		for (int col = 0; col <= getMaxColumn(arrayListOfElements); col++, inputInvalid = true) {
			while (inputInvalid) {
				System.out.print("Enter the new ASCII characters for column " + col + " key: ");
				String newKey = scanner.nextLine();
				System.out.print("Enter the new ASCII characters for column " + col + " value: ");
				String newValue = scanner.nextLine();
				if (StringUtils.length(newKey) == 3 && StringUtils.length(newValue) == 3) {
					arrayListOfElements.add(new Element(newKey, newValue, maxRow, col));
					inputInvalid = false;
				}
				else {
					System.out.println("\n\n\n***The new ASCII characters should be equal to 3.");
					inputInvalid = true;		
				}
			}
		}
		System.out.println("\n\n\n***Successfully added a new row!");
	}

	public static ArrayList<Element> sort(ArrayList<Element> arrayListOfElements) {
		String keyValueStringA = "";
		String keyValueStringB = "";
 		LinkedList<Element> linkedList = new LinkedList<Element>();
 		int j = 0;
 		linkedList.add(0, arrayListOfElements.get(0));
 		for (int i = 1; i < arrayListOfElements.size(); i++) {
			keyValueStringA = linkedList.getLast().getKey().concat(linkedList.getLast().getValue());
			keyValueStringB = arrayListOfElements.get(i).getKey().concat(arrayListOfElements.get(i).getValue());
			for (j = linkedList.size(); j > 0 && keyValueStringA.compareTo(keyValueStringB) > 0; j--) {
				keyValueStringA = linkedList.get(j - 1 + (j > 1 && i != 1 ? -1 : 0)).getKey().concat(linkedList.get(j - 1 + ( j > 1 && i != 1 ? -1 : 0)).getValue());
			}
			linkedList.add(j, arrayListOfElements.get(i));
		}
		arrayListOfElements.clear();
		System.out.println("\n\n\n***Successfully sorted the data!");
		return new ArrayList<Element>(linkedList);
	}

	public static void printMenu() {
		System.out.print("\n\n\n*****RANDOMIZER APP 2 MENU*****\n" + 
						"1. Search" + "\n" +
						"2. Edit" +  "\n" +
						"3. Print" + "\n" +
						"4. Add Row" + "\n" +
						"5. Sort" + "\n" +
						"6. Reset" + "\n" +
						"7. Exit" + "\n" + 
						"Enter the number of your choice: ");
	}

	public static void randomizeArrayContent(ArrayList<Element> arrayListOfElements) {
		String keyString = "", valueString = "";
		char randomChar;		
		boolean inputInvalid = true;
		int maxRow = 0, maxColumn = 0;
		while (inputInvalid) {
			try {
				System.out.print("Enter the new number of rows: ");
				maxRow = scanner.nextInt();
				System.out.print("Enter the new number of columns: ");
				maxColumn = scanner.nextInt();
				scanner.nextLine();
				if (maxRow < 1 || maxColumn < 1) {
					System.out.println("***Enter a number higher than 0!");
					inputInvalid = true;
				}
				else {
					inputInvalid = false;
					for (int row = 0; row < maxRow; row++) {
						for (int column = 0; column < maxColumn; column++) {
							keyString = "";
							valueString = "";
							for (int keyChar = 0; keyChar < 3; keyChar++) {
								randomChar = (char)(random.nextInt(94) + 32);
								keyString = keyString.concat(randomChar+"");
								randomChar = (char)(random.nextInt(94) + 32);
								valueString = valueString.concat(randomChar+"");
							}
							arrayListOfElements.add(new Element(keyString, valueString, row, column));
						}
					}
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("***Wrong input! ");
				scanner.nextLine();
			}
		}
	}

	public static void setRowAndColumn(ArrayList<Element> arrayListOfElements, int maxRow, int maxColumn) {
		for (int i = 0, col = 0, row = 0; i < arrayListOfElements.size(); i++, col++) {
			if (col > maxColumn) {
				col = 0;
				row++;
			}	
			arrayListOfElements.get(i).setColumn(col);
			arrayListOfElements.get(i).setRow(row);
		}
	}

	public static int getMaxColumn(ArrayList<Element> arrayListOfElements) {
		int maxColumn = 0;
		for (Element element : arrayListOfElements) {
			if (maxColumn < element.getColumn()) {
				maxColumn = element.getColumn();
			}
		}
		return maxColumn;
	}

	public static int getMaxRow(ArrayList<Element> arrayListOfElements) {
		int maxRow = 0;
		for (Element element : arrayListOfElements) {
			if (maxRow < element.getRow()) {
				maxRow = element.getRow();
			}
		}
		return maxRow;
	}
}