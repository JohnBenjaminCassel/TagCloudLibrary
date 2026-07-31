package com.johnbenjamincassel.taglibs;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimilarityCalculator {

	public static <T> HashMap<T, Double> pathSimilarityThroughReciprocalDistance(Map<T, Integer> path_distances) {
		HashMap<T, Double> similarities = new HashMap<T, Double>();
		for(Map.Entry<T, Integer> path_entry : path_distances.entrySet()) {
			similarities.put(path_entry.getKey(), 1.0/path_entry.getValue());
		}
		return similarities;
	}

	public static <T> HashMap<T, Double> 
	pathSimilarityThroughReciprocalExpDistance(Map<T, Integer> path_distances, 
			int base) {
		HashMap<T, Double> similarities = new HashMap<T, Double>();
		for(Map.Entry<T, Integer> path_entry : path_distances.entrySet()) {
			similarities.put(path_entry.getKey(), 1.0/Math.pow(base, path_entry.getValue()));
		}
		
		return similarities;
	}
	
	public static <T> HashMap<T, Double> 
	getMaxSimilarities(HashMap<T, Double>[] candidate_similarities) {
		HashMap<T, Double> similarities = new HashMap<T, Double>();
		for(HashMap<T, Double> candidate_similarity : candidate_similarities) {
			for(Map.Entry<T, Double> candidate_entry : candidate_similarity.entrySet()) {
				T key = candidate_entry.getKey();
				if((! similarities.containsKey(key))
					||	(candidate_entry.getValue() > similarities.get(key))) {
					similarities.put(key, candidate_entry.getValue());
				}
			}
		}
		return similarities;
	}
	
	public static <T> double calculateRankedSimilarity(Map<T, Double> scored_tag_set_one,
			Map<T,Double> scored_tag_set_two, double alpha) {
		double normalization = alphaWeightMax(sumAbsValues(scored_tag_set_one.values()), 
				 sumAbsValues(scored_tag_set_two.values()), alpha);
		if(normalization == 0) { return 0; }
		double shared_tag_value = 0;
		for(Map.Entry<T, Double> tag_score_entry : scored_tag_set_one.entrySet()) {
			 if(scored_tag_set_two.containsKey(tag_score_entry.getKey())){
				 double value_one = tag_score_entry.getValue();
				 double value_two = scored_tag_set_two.get(tag_score_entry.getKey());
				 shared_tag_value +=
					 (minimumMagnitude(value_one,value_two) * sign(value_one,value_two));
			 }
		}
		return shared_tag_value/normalization;
	}
	
	private static double minimumMagnitude(double a, double b) {
		return Math.min(Math.abs(a), Math.abs(b));
	}
	
	private static int sign(double a, double b) {
		return (a * b >= 0) ? 1 : -1;
	}
	
	public static double alphaWeightMax(double x, double y, double alpha) {
		if(y > x) { return alphaWeightMax(y, x, alpha); }
		return (x * alpha) + (y * (1 - alpha));
	}
	
	public static double[] maxFirst(double x, double y) {
		if(y > x) { return new double[]{y, x}; }
		return new double[]{x,y};
	}
	
	private static double sumAbsValues(Collection<Double> values) {
		double sum = 0;
		for(double value : values) {
			sum += Math.abs(value);
		}
		return sum;
	}

	public static <T> double[] calculateSimpleSimilarity(final Set<T> tag_set_one, 
			final Set<T> tag_set_two) {
		HashSet<T> shared_set = new HashSet<T>(tag_set_one);
		shared_set.retainAll(tag_set_two); 
		double shared_size = shared_set.size();
		return new double[]{ shared_size/tag_set_one.size(), 
				shared_size/tag_set_two.size()};
	}
	
	public static <T> double calculateSimpleSimilarity(final Set<T> tag_set_one, 
			final Set<T> tag_set_two, double alpha) {
		HashSet<T> shared_set = new HashSet<T>(tag_set_one);
		shared_set.retainAll(tag_set_two); 
		return shared_set.size()/((double)alphaWeightMax(tag_set_one.size(), tag_set_two.size(), alpha));
	}
}
