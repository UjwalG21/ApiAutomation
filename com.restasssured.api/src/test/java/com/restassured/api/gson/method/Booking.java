package com.restassured.api.gson.method;

public class Booking {
	String firstname;
	String lastname;
	Integer totalprice;
	boolean depositpaid;
	BookingDates bookingdates;
	String additionalneeds;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Integer totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public BookingDates getBookingdates() {
		return bookingdates;
	}

	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

}
