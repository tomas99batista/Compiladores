package g01;

import java.util.Scanner;

public class Calculadora1 {
	// Calculator with + - * / operators
	// Way it works: <number> <operator> <number>

	public static float resultCalculate(String things, String things2, String operator) {
		Float n1 = Float.parseFloat(things);
		Float n2 = Float.parseFloat(things2);
		float result = 0;
		switch (operator) {
		case "+":
			result = n1 + n2;
			break;
		case "-":
			result = n1 - n2;
			break;
		case "*":
			result = n1 * n2;
			break;
		case "/":
			result = n1 / n2;
			break;
		default:
			System.err.println("Operation not defined");
			System.exit(1);
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		System.out.print("Calculator (<number> <operator> <number>): ");
		String inputs = scanner.nextLine();
		String things[] = inputs.split(" ");
		System.out.println("Valor: "+ resultCalculate(things[0], things[2], things[1]));
		scanner.close();
	}

}
