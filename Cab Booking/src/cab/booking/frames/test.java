package cab.booking.frames;
import javax.swing.JOptionPane;
import java.sql.*;

public class test {
   public static Connection con = null;
   public static Statement stmt = null;

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
                                                   + "userID INTEGER PRIMARY KEY,"
                                                   + "phone_num INTEGER," 
                                                   + "emailID BLOB," 
                                                   + "pswrd BLOB," 
                                                   + "wallet INTEGER DEFAULT 0," 
                                                   + "free_at INTEGER DEFAULT 0," 
                                                   + "trip_status TEXT DEFAULT 'F');" ; 
        
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
                                                   + "rating INTEGER ,"
                                                   + "phone_num INTEGER," 
                                                   + "emailID BLOB,"
                                                   + "free INTEGER,"   
                                                   + "status TEXT);" ; 
        
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
                                                   + "num_drivers INTEGER,"  
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

   public static void main( String args[] ) {
      createTableUsers();
      createTableDrivers();
      createTableLocations();
   }
}
