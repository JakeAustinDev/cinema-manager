import java.util.Scanner;

public class Cinema {

    public static void showSeats(String[][] seatGrid, int seats, int rows) {
        System.out.println("\nCinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                System.out.print(seatGrid[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        //Statistics Variables
        int totalSeats = rows * seats;
        int soldSeats = 0;
        int income = 0;
        int maxIncome;
        float ticketPercentage = 0;

        if (totalSeats <= 60) {
            maxIncome = totalSeats * 10;
        }
        else {
            maxIncome = (rows / 2) * seats * 10 + (rows - (rows / 2)) * seats * 8;

        }

        String[][] seatGrid = new String[rows + 1][seats + 1];

        for (int i = 0; i < seats + 1; i++) {
            if (i == 0) {
                seatGrid[0][i] = " ";
            } else {
                seatGrid[0][i] = Integer.toString(i);
            }

        }
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (j == 0) {
                    seatGrid[i][j] = Integer.toString(i);
                } else {
                    seatGrid[i][j] = "S";
                }
            }
        }
        while (true) {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            String userResponse = scanner.next();

            if (userResponse.equals( "1")) {
                showSeats(seatGrid, seats, rows);
            }

            else if (userResponse.equals("2")) {
                while (true) {
                System.out.println("Enter a row number:");
                int chosenRow = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                int chosenSeat = scanner.nextInt();
                    if (chosenSeat < 1 || chosenSeat > seats || chosenRow < 1 || chosenRow > rows) {
                        System.out.println("Wrong input!");
                        break;
                    }

                if (seatGrid[chosenRow][chosenSeat].equals("B")){
                    System.out.println("That ticket has already been purchased!");
                    System.out.println("Please select another seat");
                } else {
                    int price;
                    if (totalSeats <= 60 || chosenRow <= (rows / 2)) {
                        price = 10;
                    } else {
                        price = 8;
                    }
                    System.out.print("Ticket price: ");
                    System.out.printf("$%d",price);
                    System.out.println();

                    seatGrid[chosenRow][chosenSeat] = "B";
                    soldSeats += 1;
                    ticketPercentage = ((float)soldSeats / totalSeats) * 100;
                    income += price;
                    break;

                }
            }
            } else if (userResponse.equals("3")) {
                System.out.printf("Number of purchased tickets: %d",soldSeats);
                System.out.printf("\nPercentage: %.2f%%",ticketPercentage);
                System.out.printf("\nCurrent income: $%d",income);
                System.out.printf("\nTotal income: $%d\n",maxIncome);


            } else if (userResponse.equals( "0")) {
                break;
            }
            else {
                System.out.println("Invalid input");
            }
        }
    }
}