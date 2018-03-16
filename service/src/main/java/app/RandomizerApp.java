package app;

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.util.ArrayList;

public class RandomizerApp {

	private Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

	public int userChoice;

	private static final int SEARCH  = 1;
	private static final int EDIT = 2;
	private static final int PRINT = 3;
	private static final int ADD_ROW = 4;
	private static final int SORT = 5;
	private static final int RESET = 6;
	private static final int EXIT = 7;

	private ArrayList<Element> arrayListOfElements;
	private File file;

	public RandomizerApp(File file) {
		this.file = file;
		initialize();
		new FileParser(getFile(), getArrayList());
		if (arrayListOfElements.isEmpty()) {
			System.out.println("The file is empty.");
			reset();
		}
		else {
			System.out.println("***File parsed successfully!");
			Utilities.printArray(getArrayList());			
		}
		startApp();	
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setArrayList(ArrayList<Element> arrayListOfElements) {
		this.arrayListOfElements = arrayListOfElements;
	}

	public ArrayList<Element> getArrayList() {
		return arrayListOfElements;
	}


	public void initialize() {
		setArrayList(new ArrayList<Element>());
		userChoice = 0;
	}

	public void reset() {
		arrayListOfElements.clear();
		Utilities.randomizeArrayContent(getArrayList());
		Utilities.printArray(getArrayList());
		FileParser.updateFile(getFile(), getArrayList());
	}

	public void startApp() {
		while (userChoice != EXIT) {
			try {
				Utilities.printMenu();
				userChoice = scanner.nextInt();
				if (userChoice == SEARCH) {
					Utilities.search(getArrayList());
				}
				else if (userChoice == EDIT) {
					Utilities.edit(getArrayList());
					FileParser.updateFile(getFile(), getArrayList());
				}
				else if (userChoice == PRINT) {
					Utilities.printArray(getArrayList());
				}
				else if (userChoice == ADD_ROW) {
					Utilities.addRow(getArrayList());	
					FileParser.updateFile(getFile(), getArrayList());
					Utilities.printArray(getArrayList());
				}
				else if (userChoice == SORT) {
					arrayListOfElements = Utilities.sort(getArrayList());
					Utilities.setRowAndColumn(getArrayList(), Utilities.getMaxRow(getArrayList()), Utilities.getMaxColumn(getArrayList()));
					FileParser.updateFile(getFile(), getArrayList());
					Utilities.printArray(getArrayList());
				}
				else if (userChoice == RESET) {
					reset();
				}
				else if (userChoice == EXIT) {
					System.out.println("***App terminated.");
				}
				else {
					System.out.println("***Wrong input choice!");
				} 
			} catch (java.util.InputMismatchException e) {
				System.out.println("***Wrong input choice!");
				scanner.nextLine();
			}
		}
		System.exit(0);
	}
}