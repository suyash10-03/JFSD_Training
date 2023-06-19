package AssignmentDuringBreak;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/jdbc_casestudy";
        String username = "root";
        String password = "root123";

        Scanner scanner = new Scanner(System.in);

        try {
            // Establishing database connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Menu loop
            boolean exit = false;
            while (!exit) {
                System.out.println("----------- MENU -----------");
                System.out.println("a) Insert a new member");
                System.out.println("b) Update membership type");
                System.out.println("c) Update membership fees");
                System.out.println("d) Delete membership details");
                System.out.println("e) Display details of all members");
                System.out.println("f) Exit");
                System.out.println("----------------------------");

                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine();

                switch (choice.toLowerCase()) {
                    case "a":
                        insertMember(connection, scanner);
                        break;
                    case "b":
                        updateMemberType(connection, scanner);
                        break;
                    case "c":
                        updateMembershipFees(connection, scanner);
                        break;
                    case "d":
                        deleteMember(connection, scanner);
                        break;
                    case "e":
                        displayAllMembers(connection);
                        break;
                    case "f":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

            // Close the scanner and database connection
            scanner.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to insert a new member into the table
    public static void insertMember(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter Member ID: ");
            int memberID = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Member Name: ");
            String memberName = scanner.nextLine();

            System.out.print("Enter Member Type: ");
            String memberType = scanner.nextLine();

            System.out.print("Enter Membership Fees: ");
            float membershipFees = Float.parseFloat(scanner.nextLine());

            Member member = new Member(memberID, memberName, memberType, membershipFees);
            member.insertMember(connection);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for Member ID and Membership Fees.");
        }
    }

    // Function to update membership type
    public static void updateMemberType(Connection connection, Scanner scanner) {
        System.out.print("Enter Member ID: ");
        int memberID = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter New Member Type: ");
        String newMemberType = scanner.nextLine();

        Member member = new Member();
        member.setMemberID(memberID);
        member.updateMemberType(connection, newMemberType);
    }

    // Function to update membership fees
    public static void updateMembershipFees(Connection connection, Scanner scanner) {
        System.out.print("Enter Member ID: ");
        int memberID = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter New Membership Fees: ");
        float newMembershipFees = Float.parseFloat(scanner.nextLine());

        Member member = new Member();
        member.setMemberID(memberID);
        member.updateMembershipFees(connection, newMembershipFees);
    }

    // Function to delete membership details
    public static void deleteMember(Connection connection, Scanner scanner) {
        System.out.print("Enter Member ID: ");
        int memberID = Integer.parseInt(scanner.nextLine());

        Member member = new Member();
        member.setMemberID(memberID);
        member.deleteMember(connection);
    }

    // Function to display details of all members
    public static void displayAllMembers(Connection connection) {
        Member.displayAllMembers(connection);
    }
}
