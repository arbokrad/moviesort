package com.starkc.moviesort.dto;

public class MoviePair {

	private Movie movie1;
	private Movie movie2;
	
	public MoviePair( Movie movie1, Movie movie2 ) {
		this.movie1 = movie1;
		this.movie2 = movie2;
	}
	
	public Movie getMovie1() {
		return movie1;
	}
	public void setMovie1(Movie movie1) {
		this.movie1 = movie1;
	}
	public Movie getMovie2() {
		return movie2;
	}
	public void setMovie2(Movie movie2) {
		this.movie2 = movie2;
	}
		
	public boolean equals(Object o) {
		
		if( o == null ) {
			return false;
		}
		
		if( !(o instanceof MoviePair) ) {
			return false;
		}
		
		final MoviePair pair = (MoviePair) o;
		if( this.getMovie1().equals( pair.getMovie1() ) && this.getMovie2().equals( pair.getMovie2() ) ) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb	.append( "FIRST MOVIE: " ).append( this.getMovie1().getDisplayName() ).append( "\n" )
			.append( "SECOND MOVIE: " ).append( this.getMovie2().getDisplayName() ).append( "\n" );
		
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movie1 == null) ? 0 : movie1.hashCode());
		result = prime * result + ((movie2 == null) ? 0 : movie2.hashCode());
		return result;
	}	
}
