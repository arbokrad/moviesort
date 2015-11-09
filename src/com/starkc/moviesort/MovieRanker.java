package com.starkc.moviesort;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.starkc.moviesort.dto.Movie;
import com.starkc.moviesort.dto.MoviePair;

// TODO: statistics?

public class MovieRanker {

	public static void main(String[] args) {
		
		System.out.println( "--- Initializing MovieRanker ---" );
		
		// retrieve data
		//List<Movie> movieList = MovieUtil.getTestData();
		List<Movie> movieList = MovieUtil.loadData();
		
		// generate unique pairs to go head-to-head with one another
		List<MoviePair> pairList = MovieUtil.generateMoviePairs(movieList);
		Collections.shuffle( pairList );
		
		// initialize the rankings map (make sure every movie is at least represented, even if it receives no votes)
		Map<Movie,Integer> rankData = MovieUtil.initializePairMap( movieList );
		
		// present and gather votes
		MovieUtil.rankMovies(pairList, rankData);
		
		// sort and print final rankings
		MovieUtil.printFinalRanking(rankData);
		
		System.out.println( "--- Ending MovieRanker ---" );
	}	
}
