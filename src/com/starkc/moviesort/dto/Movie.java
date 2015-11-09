package com.starkc.moviesort.dto;

public class Movie implements Comparable<Movie> {

	private String id;
	private String displayName;
	private Integer votes;
	
	public Movie( String displayName ) {
		setDisplayName(displayName);
		setId(displayName.replaceAll( "\',\" ", "_") );
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void addVote() {
		if( votes == null ) {
			votes = new Integer(1);
		} else {
			votes++;
		}
	}

	public Integer getVotes() {
		return votes;
	}
	
	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public int compareTo(Movie o) {
		return this.getId().compareTo( o.getId() );
	}

	public boolean equals(Movie o) {
		return this.getId().equals( o.getId() );
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb	.append( "Movie[ id=" ).append( getId() ).append( "\n")
			.append( "\t name=" ).append( getDisplayName() ).append( "]");
		
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}	
}
