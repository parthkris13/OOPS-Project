package cab.booking.frames;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {
   public static Connection con = null;
   public static Statement stmt = null;
   public static PreparedStatement st = null;

   public static Connection connectToDB(){
      try {
         Class.forName("org.sqlite.JDBC");
         Connection con = DriverManager.getConnection("jdbc:sqlite:/Users/sheyril/Desktop/OOP/test.db");
//         JOptionPane.showMessageDialog(null,"Connected");
         return con;
      } catch ( Exception e ) {
//         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//         System.exit(0);
           JOptionPane.showMessageDialog(null,e);
           return null;
      }
//      System.out.println("Opened database successfully");
   }

   public static void createTableUsers(){
      connectToDB();
      try{
         String userTable = "CREATE TABLE users (" + "username TEXT," 
                                                   + "userID BLOB PRIMARY KEY,"
                                                   + "phone_num INTEGER," 
                                                   + "emailID BLOB," 
                                                   + "pswrd BLOB," 
                                                   + "wallet INTEGER," 
                                                   + "free_at INTEGER," 
                                                   + "trip_status TEXT);" ; 
        
         try{
            stmt = con.createStatement();
            stmt.executeUpdate(userTable);
         } catch (SQLException e){
            System.out.println("SQLException");
         }
         con.close();
      }catch ( Exception e ) {
         System.out.println("Error occured");
         System.exit(1);
      }
      System.out.println("Table created successfully");
   }

   public static void createTableDrivers(){
      connectToDB();
      try{
         String userDrivers = "CREATE TABLE drivers (" + "username TEXT,"
                                                   + "driverID INTEGER PRIMARY KEY,"
                                                   + "driver_loc TEXT,"
                                                   + "rating REAL,"
                                                   + "phone_num INTEGER," 
                                                   + "emailID BLOB,"
                                                   + "free INTEGER,"   
                                                   + "status INTEGER);" ; 
        
         try{
            stmt = con.createStatement();
            stmt.executeUpdate(userDrivers);
         } catch (SQLException e){
            System.out.println("SQLException");
         }
         con.close();
      }catch ( Exception e ) {
         System.out.println("Error occured");
         System.exit(1);
      }
      System.out.println("Table created successfully");
   }
 
   public static void createTableLocations(){
      connectToDB();
      try{
         String locations = "CREATE TABLE locations (" + "src_loc TEXT," 
                                                   + "dest_loc TEXT ,"
                                                   + "fare INTEGER,"
                                                   + "loc_id INTEGER,"
                                                   + "time INTEGER);" ; 
        
         try{
            stmt = con.createStatement();
            stmt.executeUpdate(locations);
         } catch (SQLException e){
            System.out.println("SQLException");
         }
         con.close();
      }catch ( Exception e ) {
         System.out.println("Error occured");
         System.exit(1);
      }
      System.out.println("Table created successfully");
   }
   
   public static void populate(){
      con=connectToDB();
      Object[][] loc_data = {
         {"Secunderabad Station", "BPHC", 250, 1, 40},
         {"Secunderabad Station", "Jubilee Hills", 250, 1, 40},
         {"Secunderabad Station", "Begumpet", 250, 1, 40},
         {"Secunderabad Station", "Airport", 250, 1, 40},
         {"Jubilee Hills", "BPHC", 250, 2, 40},
         {"Jubilee Hills", "Secunderabad Station", 250, 2, 40},
         {"Jubilee Hills", "Begumpet", 250, 2, 40},
         {"Jubilee Hills", "Airport", 250, 2, 40},
         {"BPHC", "Airport", 250, 3, 40},
         {"BPHC", "Secunderabad Station", 250, 3, 40},
         {"BPHC", "Jubilee Hills", 250, 3, 40},
         {"BPHC", "Begumpet", 250, 3, 40},
         {"Begumpet", "Airport", 250, 4, 40},
         {"Begumpet", "BPHC", 250, 4, 40},
         {"Begumpet", "Jubilee Hills", 250, 4, 40},
         {"Begumpet", "Secunderabad Station", 250, 4, 40},
         {"Airport", "Begumpet", 250, 5, 40},
         {"Airport", "BPHC", 250, 5, 40},
         {"Airport", "Secunderabad Station", 250, 5, 40},
         {"Airport", "Jubilee Hills", 250, 5, 40}
         };

         try{
            String sql = "insert into locations values (?,?,?,?,?)";
            st= con.prepareStatement(sql);
            for (Object[] a : loc_data){
                st.setString(1, (String)a[0]);
                st.setString(2, (String)a[1]);
                st.setInt(3, (Integer)a[2]);
                st.setInt(4, (Integer)a[3]);
                st.setInt(5, (Integer)a[4]);
                st.execute();
            }
//            st.executeBatch();
//            st.close();
         }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
         }
         
         Object[][] driver_data={
             {"Rahul", 1, "Begumpet", 4.3, 9985734216d, "rahul@gmail.com", 0, 0},
             {"Chetan", 2, "Jubilee Hills", 4.1, 9985254276d, "chetan@gmail.com", 0, 0},
             {"Raghav", 3, "Secunderabad Station", 4.0, 9865734216d, "raghav@gmail.com", 0, 0},
             {"Rakesh", 4, "BPHC", 3.8, 9185734317d, "rakesh@gmail.com", 0, 0},
             {"Priya", 5, "Begumpet", 4.7, 9985736244d, "priya@gmail.com", 0, 0},
             {"Jahnvi", 6, "BPHC", 4.3, 9985167216d, "jahnvi@gmail.com", 0, 0},
             {"Moolchand", 7, "Airport", 4.8, 9785734919d, "moolchand@gmail.com", 0, 0},
             {"Shaurya", 8, "Jubilee Hills", 3.5, 9685824210d, "shaurya@gmail.com", 0, 0},
             {"Nikita", 9, "Secunderabad Station", 4.3, 9763834216d, "nikita@gmail.com", 0, 0},
             {"Sohaib", 10, "Airport", 4.8, 9743634237d, "sohaib@gmail.com", 0, 0}
         };
         try{
            String sql = "insert into drivers values (?,?,?,?,?,?,?,?)";
            st= con.prepareStatement(sql);
            for (Object[] b : driver_data){
                st.setString(1, (String)b[0]);
                st.setInt(2, (Integer)b[1]);
                st.setString(3, (String)b[2]);
                st.setDouble(4, (Double)b[3]);
                st.setDouble(5, (Double)b[4]);
                st.setString(6, (String)b[5]);
                st.setInt(7, (Integer)b[6]);
                st.setInt(8, (Integer)b[7]);
                st.execute();
            }
//            st.executeBatch();
//            st.close();
         }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
         }
   }

   public static void main( String args[] ) {
//      createTableUsers();
//      createTableDrivers();
//      createTableLocations();
      populate();
   }
}
