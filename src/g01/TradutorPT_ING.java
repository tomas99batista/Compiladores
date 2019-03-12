package g01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradutorPT_ING {

	public static void main(String[] args) {
		Map<String, String> map = putDic("C:\\Users\\tomas\\Desktop\\UA\\C\\src\\g01\\dic1.txt");
		String input = "words to add";
		translator(input, map);
		for(String word:translatedPhrase) {
			System.out.println(word);
		}
	}
	
	static ArrayList<String> translatedPhrase = new ArrayList<String>();
	private static void translator(String phrase, Map<String, String> mapWords) {
		// Phrase to translate -> String phrase
		String[] splitString = phrase.split(" | , | - |; | .");
		for (int i = 0; i < phrase.length(); i++) {
			System.out.println(splitString[i]);
			if (mapWords.containsKey(splitString[i])) {
				translatedPhrase.add(mapWords.get(splitString[i]));
			}
			else {
//				System.out.println("aqui");
				translatedPhrase.add(splitString[i]);
			}
		}
	}

	private static Map<String, String> putDic(String filename) {
		Map<String, String> translateMap = new HashMap<>();
		try {
			Path filePath = Paths.get(filename);
			List<String> lines = Files.readAllLines(filePath);
			for (String line : lines) {
				String[] line2 = line.split(" ");
				translateMap.put(line2[0], line2[1]);
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		return translateMap;
	}

}
