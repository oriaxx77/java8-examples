package com.oriaxx77.javaplay.java8features.datetime;

import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTimeExamples {

	void currentDateTimeWithDefaultZone(){
		LocalDateTime.now();
	}
	
	void currentDateTimeWithClock(){
		Clock clock = Clock.system( ZoneId.of( ZoneId.SHORT_IDS.get( "ECT" ) ) );
		LocalDateTime.now( clock );
	}
	
	void currentDateTimeInEuropeWarshaw(){
		LocalDateTime.now( ZoneId.of( "Europe/Warsaw" ) );
	}
	
	void dateFromYearMonthDayValues(){
		LocalDateTime.of( Year.now().getValue(), Month.APRIL, DayOfWeek.FRIDAY.getValue(), 1, 1, 1 );
	}
	
	void dateWithEpoch(){
		LocalDate.ofEpochDay( 150 );
	}
	
	void nextFridayWithTemporalAdjuster(){
		LocalDate.now().with( TemporalAdjusters.next( DayOfWeek.FRIDAY) );
	}
	
	void oneMonthEarlierWithTemporalAdjuster(){
		LocalDate.now().with( (temporal) ->  temporal.minus( 1, ChronoUnit.MONTHS ) );
	}
	
	
	
	public static void main(String[] args) {
		LocalDateTimeExamples examples = new LocalDateTimeExamples();
		examples.currentDateTimeWithClock();
		examples.currentDateTimeWithDefaultZone();
		examples.dateFromYearMonthDayValues();
		examples.nextFridayWithTemporalAdjuster();
		examples.dateWithEpoch();
		examples.oneMonthEarlierWithTemporalAdjuster();
		System.out.println( ZoneId.getAvailableZoneIds() );
	}
}

