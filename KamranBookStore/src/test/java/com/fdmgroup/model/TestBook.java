package com.fdmgroup.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.entities.Book;

public class TestBook {
	
	Book book;
	
	@Before
	public void setUp(){
		book = new Book();
	}

	@Test
	public void test_AddingACustomerRatingOfFive_ToABookWithNoRatings_ChangesAvgCustomerRatingToFive() {
		book.addCustomerRating(5);
		
		assertEquals(5, book.getAvgCustomerRating(), 0.01);
	}
	
	@Test
	public void test_AddingCustomerRatingOfFive_ToBookWithAvgCustomerRatingFour_ChangesAvgCustomerRatingToFourPointFive(){
		book.addCustomerRating(4);
		book.addCustomerRating(5);
		
		assertEquals(4.5, book.getAvgCustomerRating(), 0.01);
		
	}

}
