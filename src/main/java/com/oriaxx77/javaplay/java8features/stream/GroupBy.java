package com.oriaxx77.javaplay.java8features.stream;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class GroupBy {

	public static void main(String[] args) {
		List<Album> albums = Arrays.asList(
                new Album(
                        "Violator",
                		Arrays.asList(
                                new Artist("bob"),
                                new Artist("tom")
                        )
                ),
                new Album("Music for the masses",Arrays.asList(new Artist("bill"),new Artist("bob")))
        );

        Map<Artist, List<Album>> x = albums.stream()
                .flatMap(album -> album.getArtist().stream().map(artist -> pair(artist, album)))
                .collect(Collectors.groupingBy(Entry::getKey, Collectors.mapping(Entry::getValue, Collectors.toList())));

        x.entrySet().stream().forEach(System.out::println);
	}
	
//	 public static void mainX(String... args) {
//
//	        List<Album> albums = Arrays.asList(
//	                new Album(
//	                        Arrays.asList(
//	                                new Artist("bob"),
//	                                new Artist("tom")
//	                        )
//	                ),
//	                new Album(Arrays.asList(new Artist("bill")))
//	        );
//
//	        Map<Artist, List<Album>> x = albums.stream()
//	                .flatMap(album -> album.getArtist().stream().map(artist -> pair(artist, album)))
//	                .collect(Collectors.groupingBy(Entry::getKey, Collectors.mapping(Entry::getValue, Collectors.toList())));
//
//	        x.entrySet().stream().forEach(System.out::println);
//	    }

	    static class Artist {
	        private final String name;

	        Artist(String name) {
	            this.name = name;
	        }

	        public String toString() {return name;}

			@Override
			public int hashCode() {
				return name.hashCode();
			}

			@Override
			public boolean equals(Object obj) {
				if ( obj == null )
					return false;
				
				if ( obj == this )
					return true;
				
				if (obj instanceof Artist )
				{
					Artist other = (Artist)obj;
					return this.name.equals( other );
				}
				return false;
			}
	        
	        

	    }

	    static class Album {
	    	String name;
	        private List<Artist> artists;

	        Album(String name, List<Artist> artists) {
	        	this.name = name;
	            this.artists = artists;
	        }

	        List<Artist> getArtist() {
	            return artists;
	        }
	        
	        public String toString(){ return name; }

	    }

	    private static <T,U> AbstractMap.SimpleEntry<T,U> pair(T t, U u) {
	        return new AbstractMap.SimpleEntry<T,U>(t,u);
	    }

	
}
