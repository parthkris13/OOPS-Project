import java.sql.*;

public class test {
   private static Connection c = null;
   private static Statement stmt = null;

   public static void connectToDB(){
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   }

   public static void createTableUsers(){
      connectToDB();
      try{
         String userTable = "CREATE TABLE users (" + "username TEXT," 
                                                   + "userID INTEGER PRIMARY KEY,"
                                                   + "phone_num INTEGER," 
                                                   + "emailID BLOB," 
                                                   + "pswrd BLOB," 
                                                   + "wallet INTEGER," 
                                                   + "trip_status TEXT);" ; 
        
         try{
            stmt = c.createStatement();
            stmt.executeUpdate(userTable);
         } catch (SQLException e){
            System.out.println("SQLException");
         }
         c.close();
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
                                                   + "rating INTEGER ,"
                                                   + "phone_num INTEGER," 
                                                   + "emailID BLOB,"  
                                                   + "status TEXT);" ; 
        
         try{
            stmt = c.createStatement();
            stmt.executeUpdate(userDrivers);
         } catch (SQLException e){
            System.out.println("SQLException");
         }
         c.close();
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
            stmt = c.createStatement();
            stmt.executeUpdate(locations);
         } catch (SQLException e){
            System.out.println("SQLException");
         }
         c.close();
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
