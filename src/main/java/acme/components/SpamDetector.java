package acme.components;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import acme.entities.systemConfiguration.SystemConfiguration;

public class SpamDetector {

	public static Boolean isSpam(final String text, final SystemConfiguration systemConfiguration) {
		boolean result = false;

		final List<String> wordsChecking = SpamDetector.getWords(text);
//		SpamRecord: Map<Término spam, término booster>
		final Map<String, String> enRecords = SpamDetector.getSpamRecords(systemConfiguration.getSpamRecordsEn());		
		final Map<String, String> esRecords = SpamDetector.getSpamRecords(systemConfiguration.getSpamRecordsEs());
		final Map<String, String> spamRecords = new HashMap<>();
		spamRecords.putAll(enRecords);
		spamRecords.putAll(esRecords);

//		SpamWord: Map<Término spam, threshold>
		final Map<String, Double> enWords = SpamDetector.getSpamWords(systemConfiguration.getSpamRecordsEn());		
		final Map<String, Double> esWords = SpamDetector.getSpamWords(systemConfiguration.getSpamRecordsEs());	
		final Map<String, Double> spamWords = new HashMap<>();
		spamWords.putAll(enWords);
		spamWords.putAll(esWords);
		
		final Double spamThreshold = systemConfiguration.getSpamThreshold();
		final Double spamBooster = systemConfiguration.getSpamBooster();

		final Double spamRatio = SpamDetector.spam(wordsChecking, spamRecords, spamWords, spamBooster);

		if(spamRatio >= spamThreshold) {
			result = true;
		}

		return result;
	}

	private static List<String> getWords(final String originalText){
		return Arrays.asList(originalText.replaceAll("[.,:;/*=|()�!�?{}`�<>]"," ").replace("\""," ").replace("\\"," ")
			.trim().split("\\s+"));		
	}
	
	private static Map<String, Double> getSpamWords(final String spamRecords){
		final Map<String, Double> spamWords = new HashMap<>();
		
		for(final String keyValue : spamRecords.split(",")) {
			final String[] pair = keyValue.replace("("," ").replace(")"," ").replace("'", "").trim().split(":");
			spamWords.put(pair[0], Double.valueOf(pair[1]));
		}

		return spamWords;
	}

	private static Map<String, String> getSpamRecords(final String spamRecords){
		final Map<String, String> spamWords = new HashMap<>();
		
		for(final String keyValue : spamRecords.split(",")) {
			final String[] tuple = keyValue.replace("("," ").replace(")"," ").replace("'", "").trim().split(":");
			if(!tuple[2].isEmpty()) {
				spamWords.put(tuple[0], tuple[2]);
			}
		}

		return spamWords;
	}

	private static Double spam(final List<String> words, final Map<String, String> spamRecords, 
		final Map<String, Double> spamWords, final Double spamBooster) {
		Double spamWeight = 0.;
		Integer palabrasDobles = 0;
		String palabraAnterior= "";
		
		for(final String word: words) {
			
			if(spamRecords.keySet().contains(word.toLowerCase()) && words.contains(spamRecords.get(word.toLowerCase()))) {
				spamWeight += spamWords.get(word.toLowerCase()) * spamBooster;
			} else if(spamRecords.keySet().contains(palabraAnterior.toLowerCase() + " " + word.toLowerCase())
				&& words.contains(spamRecords.get(palabraAnterior.toLowerCase() + " " + word.toLowerCase()))) {
				spamWeight += spamWords.get(palabraAnterior.toLowerCase() + " " + word.toLowerCase()) * spamBooster;
				palabrasDobles += 1;
			}  
			
			if(spamWords.keySet().contains(word.toLowerCase())){
				spamWeight += spamWords.get(word.toLowerCase());
			} else if(spamWords.keySet().contains(palabraAnterior.toLowerCase() + " " + word.toLowerCase())) {
				spamWeight += spamWords.get(palabraAnterior.toLowerCase() + " " + word.toLowerCase());
				palabrasDobles += 1;
			}
			palabraAnterior = word;
		}
		
		return (spamWeight/(words.size() - 1 * palabrasDobles)) * 10;
	}	
}