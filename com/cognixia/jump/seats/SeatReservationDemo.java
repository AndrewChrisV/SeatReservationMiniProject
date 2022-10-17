/*
 * Seat Reservation Mini Project
 * by Andrew Vieira
 */

package com.cognixia.jump.seats;

import java.util.Scanner;

public class SeatReservationDemo {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		SeatReservation sr = new SeatReservation();
		
		System.out.println("Hello! Welcome to our seat reservation site.");
		char option = '1';
		while (option != '6') {
			switch (option) {
			case '1':	// Display seating plan
				System.out.println();
				sr.displaySeats();
				System.out.println();
				break;
			case '2':	// Reserve a seat
				System.out.println("Which seat number would you like to reserve?");
				String seat = input.nextLine();
				System.out.println("Who will sit here?");
				String name = input.nextLine();
				sr.reserveSeat(seat, name);
				break;
			case '3':	// List all reserved seats
				sr.listReservations();
				break;
			case '4':	// Move a reservation to a different seat
				System.out.println("What is your original seat?");
				String oldSeat = input.nextLine();
				System.out.println("To which seat would you like to move?");
				String newSeat = input.nextLine();
				sr.moveSeat(oldSeat, newSeat);
				break;
			case '5':	// Delete a reservation
				System.out.println("What is your seat number?");
				String seatToDelete = input.nextLine();
				sr.deleteReservation(seatToDelete);
				break;
			case '6':	// End demo
				continue;
			default:	// Invalid options
				System.out.println("That is not a valid option.");
			}
			
			System.out.println("Please select an option:");
			System.out.println("1. Display seating plan");
			System.out.println("2. Reserve a seat");
			System.out.println("3. List reserved seats");
			System.out.println("4. Move reserved seat");
			System.out.println("5. Delete seat reservation");
			System.out.println("6. Exit option menu");
			String opt = input.nextLine();
			if (opt.length() != 1)
				option = '0';
			else
				option = opt.charAt(0);
		}
		
		System.out.println("Thank you for choosing our theater. Good bye!");

		input.close();
	}

}
