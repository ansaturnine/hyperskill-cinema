package cinema;
import java.util.Scanner;

public class Cinema {
    
    public static void showSeats(int seats, int rows, char[][] seatsMap) {
              
        System.out.println("Cinema:");
        System.out.print(" ");
        
        for (int i = 1; i <= seats; i ++) {
            System.out.print(" " + i);
        }
            System.out.println();
            
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seats; j++) {
                System.out.print(" " + seatsMap[i][j]);
            }
            System.out.println();
        }
    }
    public static void buyTicket(int seats, int rows, char[][] seatsMap) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int selectedRow = s.nextInt();
        System.out.println("Enter a seat number in that row:");
        int selectedSeat = s.nextInt();
        if (selectedRow > rows || selectedSeat > seats) {
            System.out.println("Wrong input!");
            buyTicket(seats, rows, seatsMap);
        }
        else if (seatsMap[selectedRow - 1][selectedSeat - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket(seats, rows, seatsMap);
        }
        else {
            int ticketPrice = (seats * rows) <= 60 || selectedRow <= rows / 2 ? 10 : 8;
            System.out.println("Ticket price: $" + ticketPrice);
            seatsMap[selectedRow - 1][selectedSeat - 1] = 'B';
        }       
    }
    
    public static void showStatistics(int seats, int rows, char[][] seatsMap) {
        int purchasedTickets = 0;
        int currentIncome = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                if (seatsMap[i][j] == 'B') {
                    purchasedTickets++;
                    int ticketPrice = (seats * rows) <= 60 || i < rows / 2 ? 10 : 8;
                    currentIncome += ticketPrice;
                }
            }
        }
        float persentage = (float) purchasedTickets / (seats * rows) * 100.00f;
        int totalIncome = (seats * rows) <= 60 ? (seats * rows) * 10 : (rows / 2 * seats) * 10 + ((rows - (rows / 2)) * seats * 8);
        String statistics = String.format("Number of purchased tickets: %d%nPercentage: %.2f%%%nCurrent income: $%d%nTotal income: $%d", purchasedTickets, persentage, currentIncome, totalIncome);
        System.out.println(statistics);
    }
             
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = s.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = s.nextInt();
        char[][] seatsMap = new char[rows][seats];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                seatsMap[i][j] = 'S';   
            }
        }
        
        while(true) {
        
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
        
            int userChoice = s.nextInt();
        
            switch (userChoice) {
                case 0:
                    return;
                case 1:
                    showSeats(seats, rows, seatsMap);
                    break;
                case 2: 
                    buyTicket(seats, rows, seatsMap);
                    break;
                case 3:
                    showStatistics(seats, rows, seatsMap);
            }
        }       
    }
}