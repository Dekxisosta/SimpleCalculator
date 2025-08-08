package app;

import java.util.Scanner;
/** =============================
 * APPLICATION                                                                                                                                                                                                                                                                                                                                                    
 ==============================*/
public class Main{
	static boolean continueProgram = true;
	public static void main(String[] args) {
		do {
			Values numbers = new Values();
			
			int userChoice = Console.runOperatorMenu();
			
			processUserInputs(numbers, userChoice);
			
			continueProgram = shouldContinueProgram(userChoice);
			
		}while(continueProgram);
	}
	private static void processUserInputs(Values numbers, int userChoice) {
		switch(userChoice) {
		case 0: 
			Console.printEquation(numbers.a, numbers.b, '+', numbers.getSum()); 
			break;
		case 1: 
			Console.printEquation(numbers.a, numbers.b, '-', numbers.getDifference()); 
			break;
		case 2: 
			Console.printEquation(numbers.a,numbers.b,'*', numbers.getProduct()); 
			break;
		case 3: 
			Console.printEquation(numbers.a,numbers.b,'/', numbers.getQuotient()); 
			break;
		}
	}
	private static boolean shouldContinueProgram(int userChoice) {
		if (userChoice == -1) {
			System.out.println("Thank you for using the program! \n- Dekxi");
			return false;
		}
		return true;
	}
}

/** =============================
 * HELPER CLASSES                                                                                                                                                                                                                                                                                                                                                       
 ==============================*/
class Values{
	static Scanner scInt = new Scanner(System.in);
	/** =============================
	 * VARIABLES                                                                                                                                                                                                                                                                                                                                                     
	 ==============================*/
	int a;
	int b;
	
	/** =============================
	 * CONSTRUCTOR                                                                                                                                                                                                                                                                                                                                                      
	 ==============================*/
	public Values () {
		this.a = Console.askNum(1);
		this.b = Console.askNum(2);
		System.out.println();
	}
	/** =============================
	 * PUBLIC FUNCTIONS / EXPOSED API                                                                                                                                                                                                                                                                                                                                                    
	 ==============================*/
	public int getSum() {
		return this.a + this.b;
	}
	public int getDifference() {
		return this.a - this.b;
	}
	public int getProduct() {
		return this.a * this.b;
	}
	public int getQuotient() {
		return this.a / this.b;
	}
}
class Console{
	/** =============================
	 * STATIC VARIABLES                                                                                                                                                                                                                                                                                                                                                       
	 ==============================*/
	private static final String APP_TITLE = "DEKXI'S CALCULATOR";
	private static final String[] OPERATOR_OPTIONS = {
			"ADDITION",
			"SUBTRACTION",
			"MULTIPLICATION",
			"DIVISION"
		};
	
	static Scanner scInt = new Scanner(System.in);
	
	/** =============================
	 * PUBLIC FUNCTIONS / EXPOSED API                                                                                                                                                                                                                                                                                                                                                        
	 ==============================*/
	public static int runOperatorMenu() {
		printMenu();
		int userChoice = askUserInput("Operator");
		
		return userChoice;
	}
	public static int askNum(int orderOfNum) {
		if(orderOfNum==1) {
			printBanner();
		}
		System.out.printf("Enter num %s: ", orderOfNum);
		int scannedInput = getValidInt();
		
		return scannedInput;
	}
	public static void printEquation(int a, int b, char operator, int result) {
		String equation = "";
		equation += String.valueOf(a) + " ";
		equation += String.valueOf(operator) + " ";
		equation += String.valueOf(b) + " ";
		equation += "= ";
		equation += result;
		
		System.out.println(equation);
		System.out.println();
	}
	/** =============================
	 * PRIVATE FUNCTIONS / UTILITY                                                                                                                                                                                                                                                                                                                                                        
	 ==============================*/
	private static void printMenu() {
		printBanner();
		printNumberedOptions(OPERATOR_OPTIONS);
		System.out.println();
	}
	private static void printBanner() {
		printBorder(APP_TITLE.length(), '=');
		System.out.println(APP_TITLE);
		printBorder(APP_TITLE.length(), '=');
		System.out.println();
	}
	private static void printBorder(int num, char symbol) {
		String border = "";
		String convertedChar = String.valueOf(symbol);
		for(int i =0; i < num; i++) {
			border += convertedChar;
		}
		System.out.println(border);
	}
	private static void printNumberedOptions(String[] numberedOptions) {
		for (int i = 0; i < numberedOptions.length; i++) {
			System.out.printf("[%s]", String.valueOf(i+1));
			System.out.print(" " + numberedOptions[i]);
			System.out.println();
		}
		System.out.println("[0] End Program");
	}
	private static int askUserInput(String value) {
		System.out.printf("Enter %s: ", value);
		int userChoice = getValidUserChoice();
		
		return userChoice;
	}
	private static int getValidUserChoice() {
		boolean isValid = false;
		int num=0;
		do {
			try {
				num = scInt.nextInt();
				isValid = checkIfWithinSetOfOptions(num);
				
			}catch(Exception e) {
				invalidUserChoiceFallback();
			}
		}while(!isValid);
		return num-1;
	}
	
	private static int getValidInt() {
		boolean isValid = false;
		int num = 0;
		do {
			try {
				num = scInt.nextInt();
				isValid = true;
			}catch(Exception e) {
				invalidIntegerFallback();
			}
		}while(!isValid);
		
		return num;
	}
	private static boolean checkIfWithinSetOfOptions(int num) {
		if (num>-2 && num<=OPERATOR_OPTIONS.length) {
			return true;
		}
		System.out.println("[!] ERROR: Invalid UserChoice");
		return false;
	}
	private static void invalidIntegerFallback() {
		scInt.next();
		System.out.println("[!] ERROR: Invalid integer");
		System.out.print("Enter: ");
	}
	private static void invalidUserChoiceFallback() {
		scInt.next();
		System.out.println("[!] ERROR: Invalid UserChoice");
		System.out.print("Enter: ");
	}
}
