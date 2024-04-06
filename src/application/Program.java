package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DB;
import DB.DBException;

public class Program {
    public static void main(String[] args) {
        /*
         * ***READ DATA***
         * 
         * Statement st = null;
         * ResultSet rs = null;
         * 
         * try {
         * 
         * conn = DB.getConnection();
         * 
         * st = conn.createStatement();
         * rs = st.executeQuery("select * from department");
         * 
         * while (rs.next()) {
         * System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
         * }
         * }
         * catch (SQLException e) {
         * e.printStackTrace();
         * }
         * finally{
         * DB.closeResultSet(rs);
         * DB.closeStatment(st);
         * DB.closeConnection();
         * }
         * }
         */

        /*
         * ***CREATE DATA***
         *
         * Connection conn = null;
         * PreparedStatement st = null;
         * SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
         * 
         * 
         * try{
         * conn = DB.getConnection();
         * 
         * st = conn.prepareStatement(
         * "INSERT INTO seller "
         * + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
         * + "VALUES "
         * + "(?, ?, ?, ?, ?)"
         * );
         * st.setString(1, "Jack Green");
         * st.setString(2, "jack@gmail.com");
         * st.setDate(3, new java.sql.Date(formatDate.parse("25/10/2000").getTime()));
         * st.setDouble(4, 3500.00);
         * st.setInt(5, 1);
         * 
         * st.executeUpdate();
         * }
         * catch(SQLException e){
         * e.printStackTrace();
         * }
         * catch(ParseException e){
         * e.printStackTrace();
         * }
         * finally{
         * DB.closeStatment(st);
         * DB.closeConnection();
         * }
         */

        /*
         * ***UPDATE DATA***
         * Connection conn = null;
         * PreparedStatement st = null;
         * 
         * try{
         * conn = DB.getConnection();
         * 
         * st = conn.prepareStatement(
         * "UPDATE seller "
         * + "SET BaseSalary = BaseSalary + ?"
         * + "WHERE (DepartmentID = ?)"
         * );
         * st.setDouble(1, 250.0);
         * st.setInt(2, 1);
         * 
         * int rowsAffected = st.executeUpdate();
         * System.out.println("DONE!\nROWS AFFECTED: " + rowsAffected);
         * }
         * catch(SQLException e){
         * e.printStackTrace();
         * }
         * finally{
         * DB.closeStatment(st);
         * DB.closeConnection();
         * }
         */

        /*
         * *** DELETE DATA (WITH CUSTOM EXCEPTION)
         * 
         * Connection conn = null;
         * PreparedStatement st = null;
         * 
         * try {
         * conn = DB.getConnection();
         * 
         * 
         * st = conn.prepareStatement(
         * "DELETE FROM department "
         * + "WHERE Id = ?"
         * );
         * st.setInt(1, 2);
         * 
         * 
         * st.executeUpdate();
         * } catch (SQLException e) {
         * throw new DBIntegrityException(e.getMessage());
         * }
         * finally {
         * DB.closeStatment(st);
         * DB.closeConnection();
         * }
         */

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 7800 WHERE DepartmentId = 1");

            int x = 1;
            if (x < 2) {
                throw new SQLException("FAKE ERROR");
            }

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 6500 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println("DONE");
            System.out.println("ROWS1: " + rows1);
            System.out.println("ROWS2: " + rows2);
        } 
        catch (SQLException e) {
            try {
                throw new DBException("FATAL ERROR!\nTransaction rolled back!\nCaused by " + e.getMessage());
            } 
            finally {
                DB.closeStatment(st);
                DB.closeConnection();
            }
        }
    }
}
