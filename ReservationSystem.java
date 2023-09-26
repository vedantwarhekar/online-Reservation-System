import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Reservation {
    private int pnr;
    private String name;
    private int trainNumber;
    private String classType;
    private String dateOfJourney;
    private String from;
    private String to;

    public Reservation(int pnr, String name, int trainNumber, String classType, String dateOfJourney, String from, String to) {
        this.pnr = pnr;
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    public int getPnr() {
        return pnr;
    }

    public void displayDetails() {
        System.out.println("PNR: " + pnr);
        System.out.println("Name: " + name);
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Class Type: " + classType);
        System.out.println("Date of Journey: " + dateOfJourney);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
    }
}

public class ReservationSystem {
    private static List<Reservation> reservations = new ArrayList<>();
    private static int pnrCounter = 1000;

public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    makeReservation(scanner);
                    break;
                case 2:
                    cancelReservation(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the Reservation System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter from (place): ");
        String from = scanner.nextLine();
        System.out.print("Enter to (destination): ");
        String to = scanner.nextLine();

        // Generate a new PNR
        int pnr = generatePNR();

        Reservation reservation = new Reservation(pnr, name, trainNumber, classType, dateOfJourney, from, to);
        reservations.add(reservation);

        System.out.println("Reservation successful. Your PNR is: " + pnr);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter your PNR number: ");
        int pnrToCancel = scanner.nextInt();

        boolean found = false;

        for (Reservation reservation : reservations) {
            if (reservation.getPnr() == pnrToCancel) {
                reservation.displayDetails();
                System.out.print("Confirm cancellation (OK/Cancel): ");
                String confirmation = scanner.next();

                if (confirmation.equalsIgnoreCase("OK")) {
                    reservations.remove(reservation);
                    System.out.println("Reservation with PNR " + pnrToCancel + " has been canceled.");
                } else {
                    System.out.println("Cancellation not confirmed.");
                }

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Reservation with PNR " + pnrToCancel + " not found.");
        }
    }

    private static int generatePNR() {
        return ++pnrCounter;
    }
}
