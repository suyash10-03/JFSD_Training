package AssignmentDuringBreak;

import java.sql.*;

public class Member {
    private int memberID;
    private String memberName;
    private String memberType;
    private float membershipFees;

    public Member() {
    }

    public Member(int memberID, String memberName, String memberType, float membershipFees) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.memberType = memberType;
        this.membershipFees = membershipFees;
    }

    // Getters and Setters
    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public float getMembershipFees() {
        return membershipFees;
    }

    public void setMembershipFees(float membershipFees) {
        this.membershipFees = membershipFees;
    }

    // Insert member details into the table
    public void insertMember(Connection connection) {
        try {
            String insertQuery = "INSERT INTO Member (memberid, membername, membertype, membershipfees) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, memberID);
            preparedStatement.setString(2, memberName);
            preparedStatement.setString(3, memberType);
            preparedStatement.setFloat(4, membershipFees);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update member's membership type
    public void updateMemberType(Connection connection, String newMemberType) {
        try {
            String updateQuery = "UPDATE Member SET membertype = ? WHERE memberid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, newMemberType);
            preparedStatement.setInt(2, memberID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Membership type updated successfully.");
            } else {
                System.out.println("Failed to update membership type.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update member's membership fees
    public void updateMembershipFees(Connection connection, float newMembershipFees) {
        try {
            String updateQuery = "UPDATE Member SET membershipfees = ? WHERE memberid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setFloat(1, newMembershipFees);
            preparedStatement.setInt(2, memberID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Membership fees updated successfully.");
            } else {
                System.out.println("Failed to update membership fees.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete member details from the table
    public void deleteMember(Connection connection) {
        try {
            String deleteQuery = "DELETE FROM Member WHERE memberid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, memberID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Membership details deleted successfully.");
            } else {
                System.out.println("Failed to delete membership details.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve and display all member records
    public static void displayAllMembers(Connection connection) {
        try {
            String selectQuery = "SELECT * FROM Member";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                int memberID = resultSet.getInt("memberid");
                String memberName = resultSet.getString("membername");
                String memberType = resultSet.getString("membertype");
                float membershipFees = resultSet.getFloat("membershipfees");

                System.out.println("Member ID: " + memberID);
                System.out.println("Member Name: " + memberName);
                System.out.println("Member Type: " + memberType);
                System.out.println("Membership Fees: " + membershipFees);
                System.out.println("----------------------");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
