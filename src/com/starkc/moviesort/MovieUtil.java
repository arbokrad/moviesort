package com.starkc.moviesort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.starkc.moviesort.dto.Movie;
import com.starkc.moviesort.dto.MoviePair;

public class MovieUtil {
	
	private static final String FIRST_SLOT = "1";
	private static final String SECOND_SLOT = "2";
	
	public static void rankMovies( List<MoviePair> pairList, Map<Movie,Integer> rankData ) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			// loop over the pair list and pick a winner in each unique pair
			int i = 1;
			for( Iterator<MoviePair> pairIter = pairList.iterator(); pairIter.hasNext(); i++ ) {
				
				MoviePair pair = pairIter.next();
				
				System.out.println( "Pair " + i + " of " + pairList.size() );
				
				// keep asking until they enter a 1 or a 2
				boolean valid = true;
				String choice = "";
				do {
					
					System.out.println( "(1) " + pair.getMovie1().getDisplayName() + " OR (2) " + pair.getMovie2().getDisplayName() );
					choice = br.readLine();
					
					//System.out.println( "PICKED: " + choice );
					if( !FIRST_SLOT.equals(choice) && !SECOND_SLOT.equals(choice) ) {
						System.out.println( "Invalid selection, select " + FIRST_SLOT + " or " + SECOND_SLOT );
						valid = false;
					} else {
						valid = true;
					}
				} while( !valid );
				
				// grab the chosen movie
				Movie chosenMovie;
				if( FIRST_SLOT.equals(choice) ) {
					chosenMovie = pair.getMovie1();
				} else {
					chosenMovie = pair.getMovie2();
				}

				// update voting totals
				if( !rankData.containsKey( chosenMovie ) ) {
					rankData.put( chosenMovie, 1 );
				} else {
					Integer voteCount = rankData.get( chosenMovie );
					voteCount++;
					rankData.put( chosenMovie, voteCount );
				}
			}
		} catch( IOException ioe ) {
			ioe.printStackTrace();
		}
		
	}
	
	public static Map<Movie,Integer> initializePairMap( List<Movie> movieList ) {
		
		Map<Movie,Integer> movieMap = new HashMap<Movie,Integer>();
		if( movieList != null && !movieList.isEmpty() ) {
			for( Movie pair : movieList ) {
				movieMap.put( pair, 0 );					
			}	
		}
		
		return movieMap;
	}
	
	public static List<Movie> getTestData() {
		List<Movie> testData = new ArrayList<Movie>();
		
		testData.add( new Movie("TEST1") );
		testData.add( new Movie("TEST2") );
		testData.add( new Movie("TEST3") );
		
		return testData;
	}
	
	public static List<Movie> loadData() {

		List<Movie> movieList = new ArrayList<Movie>();
		
		try {
			BufferedReader inputStream = new BufferedReader(new FileReader("res/movielist.txt"));
			
			String m = null;
			while( (m = inputStream.readLine()) != null ) {
				Movie mov = new Movie(m);
				movieList.add( mov );
			}
			
		} catch( IOException f ) {
			f.printStackTrace();
		}
		
		
		return movieList;
	}
	
	public static List<MoviePair> generateMoviePairs( List<Movie> movieList ) {

		System.out.println( "Generating movie pairs..." );
		
		// final list of pairs
		Set<MoviePair> pairSet = new HashSet<MoviePair>();

		// randomize the array
		Collections.shuffle( movieList );
		
		for( Movie first : movieList ) {
			for( Movie second : movieList ) {
				
				// dont compare a movie to itself
				if( !first.equals( second ) ) {
					
					MoviePair pair = new MoviePair( first, second );
					MoviePair reversePair = new MoviePair( second, first );
					
					if( !pairSet.contains( pair ) && !pairSet.contains( reversePair )  ) {
						pairSet.add( pair );
					}	
				} else {
					//System.out.println( "Skipping equivalent movies...\n\n" );
				}
			}
		}

		System.out.println( "Finished generating movie pairs..." );
		List<MoviePair> pairList = new ArrayList<MoviePair>( pairSet );
		
		return pairList;
	}
	
	public static void printFinalRanking( Map<Movie,Integer> masterData ) {
		
		Map<Movie,Integer> movies = sortByValue(masterData);
		
		System.out.println( "\n\n\n" );
		System.out.println( "RANK \t\t TITLE \t\t\t\t\t\t\t VOTES" );
		System.out.println( "---------------------------------------------------------------------------------------" );
		int i = 1;
		for( Iterator<Movie> it = movies.keySet().iterator(); it.hasNext(); i++  ) {
			Movie m = it.next();
			System.out.println( i + "\t\t" + m.getDisplayName() + "\t\t\t\t\t\t\t" + masterData.get( m ) );
		}
	}
	
	public static Map<Movie,Integer> sortByValue( Map<Movie,Integer> unsortedMap ) {
		List list = new LinkedList(unsortedMap.entrySet());
	 
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		
		Collections.reverse(list);
		
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
