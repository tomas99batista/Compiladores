package g01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradutorNumeros04 {

	public static void main(String[] args) {
		// Put all the numbers and translations to a Map
		// Map<Integer, String>
		Map<String, Integer> Mapa = MapTradutor("C:\\Users\\tomas\\Desktop\\UA\\C\\src\\g01\\numbers.txt");
		String p = "eight million";
		ArrayList<String> word = Translate(p, Mapa);
		System.out.println(Sum(Mapa, word));
	}
	
	
	// Translate the String numbers in the input to int
	public static int Sum(Map<String, Integer> numsMap, ArrayList<String> arrList) {
		int finalValue = 0;
		int lastValue = 0;
		for (String word : arrList) {
//			System.out.println(word);
			if(word.equals("1000000") || word.equals("1000") || word.equals("100")) {
				lastValue = lastValue * Integer.parseInt(word);
				finalValue += lastValue;
			}
			else {
				lastValue = Integer.parseInt(word);
			}
		}
		return finalValue;
	}
	
	static ArrayList<String> returnWords = new ArrayList<>();
	// Translate the String numbers in the input to int
	public static ArrayList<String> Translate(String rawInput, Map<String, Integer> numsMap) {
		String[] phrase = rawInput.split("-| ");
		for (String word : phrase) {
			if (numsMap.containsKey(word)) {
				returnWords.add(numsMap.get(word).toString());
			} else {
				returnWords.add(word);
			}
		}
		return returnWords;
	}

	// Load a map with all the words and respective String into a
	// Map<Integer,String>
	public static Map<String, Integer> MapTradutor(String filename) {
		Map<String, Integer> numsMap = new HashMap<>();
		Path p1 = Paths.get(filename);
		try {
			List<String> contents = Files.readAllLines(p1);
			for (String content : contents) {
				numsMap.put(content.split("- ")[1], Integer.parseInt(content.split(" - ")[0]));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return numsMap;
	}

}
