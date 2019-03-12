package g01;

import java.util.Scanner;
import java.util.Stack;

public class CalculadoraRPN {
	// Calculator with + - * / operators
	// Way it works: <number> <operator> <number>
	// Sempre que aparece um operando (numero) ele e carregado para a pilha.
	// Sempre que aparece um operador (binario), sao retirados os dois ultimos
	// numeros da pilha
	// (se nao existirem temos um erro sintactico na expressao) e o resultado da
	// operacao e
	// colocada na PILHA.

	static int result = 0;

	public static boolean isNumeric(String strNum) {
		try {
			@SuppressWarnings("unused")
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}

	static Stack<Integer> stack = new Stack<Integer>();

	public static float resultCalculate(String thingsPassed) {
		// If it is a number add to the stack
		if (isNumeric(thingsPassed) == true) {
			Integer n1 = Integer.parseInt(thingsPassed);
			stack.push(n1);
		}
		// If it is an operation, pop the 2 top numbers, do the operation and then store
		// the result value on the stack
		else {
			int n1 = 0;
			int n2 = 0;
			try {
				n1 = stack.pop();
			} catch (Exception e) {
				System.err.println("ERROR! You don't have enough numbers on the stack");
				System.exit(0);
			}
			try {
				n2 = stack.pop();
			} catch (Exception e) {
				System.err.println("ERROR! You don't have enough numbers on the stack");
				System.exit(1);
			}
			switch (thingsPassed) {
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
			stack.add(result);
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Calculator (<numbers> <operator(s)>): ");
		String inputs = scanner.nextLine();
		String things[] = inputs.split(" ");
		for (String word : things) {
			resultCalculate(word);
		}
		System.out.println("Final Value: " + result);
		scanner.close();
	}
}
