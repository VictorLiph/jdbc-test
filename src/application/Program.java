package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DB.DB;
import DB.DBIntegrityException;

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

        // *** DELETE DATA (WITH CUSTOM EXCEPTION)
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            
            st = conn.prepareStatement(
                    "DELETE FROM department "
                    + "WHERE Id = ?"
                    );
            st.setInt(1, 2);
                    

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DBIntegrityException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeConnection();
        }
    }
}
