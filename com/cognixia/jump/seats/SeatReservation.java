/*
 * Seat Reservation Mini Project
 * by Andrew Vieira
 */

package com.cognixia.jump.seats;

public class SeatReservation {

	/*
	 * Seat reservations are stored in a 2D array.
	 * The 1st index references the seat row (indices 0-4 -> rows A-E).
	 * The 2nd index references the sea column (indices 0-4 -> columns 1-5).
	 * seats[row][col] contains the name of the person who reserved the seat at row, col.
	 * A null entry means the seat is not reserved.
	 */
	private static String[][] seats = new String[5][5];

	/*
	 * Prints the seating arrangement of the theater
	 * A row and column number in a seat means it is not reserved.
	 * "--" means the seat is reserved.
	 */
	public void displaySeats() {
		System.out.println("============================");
		System.out.println("        SEATING PLAN        ");
		System.out.println("============================");
		System.out.println();
		System.out.println("    1    2    3    4    5   ");	// column numbers
		for (int i = 0; i < 5; i++) {
			System.out.println("   ---- ---- ---- ---- ---- ");
			System.out.println("  |    |    |    |    |    |");
			System.out.print((char)('A' + i) + " |");	// row letter
			for (int j = 0; j < 5; j++) {
				if (seats[i][j] == null)	// seat is not reserved
					System.out.print(" " + (char)('A' + i) + (j + 1) + " |");
				else	// seat is reserved
					System.out.print(" -- |");
			}
			System.out.println();
			System.out.println("  |    |    |    |    |    |");
		}
		System.out.println("   ---- ---- ---- ---- ---- ");
	}
	
	/*
	 * Returns true if input seat is a legitimate seat number
	 * Returns false if input seat is not a legitimate seat number
	 */
	private boolean validateSeat(String seat) {
		// seat must have exactly two characters.
		if (seat.length() != 2)
			return false;
		// The first character of seat must be a letter between A and E.
		char row = Character.toUpperCase(seat.charAt(0));
		if (row < 'A' || row > 'E')
			return false;
		// The second character of seat must be a digit between 1 and 5.
		char col = Character.toUpperCase(seat.charAt(1));
		if (col < '1' || col > '5')
			return false;
		// If code reaches here, then seat is legitimate.
		return true;
	}
	
	/*
	 * Stores input name in seats[][] indexed by input seat
	 * Conditions:
	 * 		seat is a legitimate seat number.
	 * 		name is not an empty String.
	 * 		seat is not already reserved.
	 */
	// 
	public void reserveSeat(String seat, String name) {
		// Do not reserve seat if it is not a legitimate seat number
		if (!validateSeat(seat)) {
			System.out.println(seat + " is not a valid seat number.");
		// Do not reserve seat if a name is not given
		} else if (name.equals("")) {
			System.out.println("You did not provide a name.");
		} else {
			// Convert row to array index
			int row = Character.toUpperCase(seat.charAt(0)) - 'A';
			// Convert column to array index
			int col = seat.charAt(1) - '1';
			// Do not reserve seat if it is already reserved
			if (seats[row][col] != null) {
				System.out.println("Sorry, seat number " + seat + " is already reserved.");
			// Store name in seats[row][col] if it is available (i.e., it is null)
			} else {
				seats[row][col] = name;
				System.out.println("You have successfully reserved seat number " + seat + ".");
			}
		}
	}

	/*
	 * Lists names of people who have reserved seats
	 * Unreserved seats are not listed.
	 */
	public void listReservations() {
		System.out.println("Seat Reservations:");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				char row = (char)('A' + i);
				int col = j + 1;
				String name = seats[i][j];
				if (name != null)	// Only list reserved seats
					System.out.println("Seat " + row + col + ": " + seats[i][j]);
			}
		}
	}
	
	/*
	 * Deletes seat reservation by setting seat referenced at input seat to null
	 * Conditions:
	 * 		seat is a valid seat number.
	 * 		seat is already reserved.
	 */
	public void deleteReservation(String seat) {
		if (!validateSeat(seat)) {
			System.out.println(seat + " is not a valid seat number.");
		} else {
			int row = Character.toUpperCase(seat.charAt(0)) - 'A';
			int col = seat.charAt(1) - '1';
			if (seats[row][col] == null) {
				System.out.println("Seat number " + seat + " is not reserved.");
			} else {
				seats[row][col] = null;
				System.out.println("Seat number " + seat + " is no longer reserved.");
			}
		}
	}
	
	/*
	 * Changes seat reservation by putting name indexed in input oldSeat
	 * 	to the seat indexed in input newSeat, and setting oldSeat to null
	 * Conditions:
	 * 		oldSeat and newSeat are legitimate seat numbers.
	 * 		oldSeat is already reserved.
	 * 		newSeat is not already reserved.
	 * 		oldSeat and newSeat are not equal (i.e., they are different seats).
	 */
	public void moveSeat(String oldSeat, String newSeat) {
		if (!validateSeat(oldSeat)) {
			System.out.println(oldSeat + " is not a valid seat number.");
		} else if (!validateSeat(newSeat)) {
			System.out.println(newSeat + " is not a valid seat number.");
		} else if (oldSeat.equals(newSeat)) {
			System.out.println("Seat reservation remains unchanged.");
		} else {
			// Convert rows and columns to array indices
			int oldRow = Character.toUpperCase(oldSeat.charAt(0)) - 'A';
			int oldCol = oldSeat.charAt(1) - '1';
			int newRow = Character.toUpperCase(newSeat.charAt(0)) - 'A';
			int newCol = newSeat.charAt(1) - '1';
			if (seats[oldRow][oldCol] == null) {
				System.out.println("Seat number " + oldSeat + " is not reserved.");
			} else if (seats[newRow][newCol] != null) {
				System.out.println("Sorry, seat number " + newSeat + " is already reserved.");
			} else {
				seats[newRow][newCol] = seats[oldRow][oldCol];
				seats[oldRow][oldCol] = null;
				System.out.println("You have successfully switched your seat from " + oldSeat + " to " + newSeat + ".");
			}
		}
	}
}
