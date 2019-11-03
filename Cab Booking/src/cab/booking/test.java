import java.sql.*;

public class test {
   public static void createTables(){
      Connection c = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Opened database successfully");

      try{
         String userTable = "CREATE TABLE users (" + "username TEXT," 
                                                   + "userID INTEGER PRIMARY KEY,"
                                                   + "phone_num INTEGER," 
                                                   + "emailID BLOB," 
                                                   + "pswrd BLOB," 
                                                   + "trip_status TEXT);" ; 
         String driversTable = "CREATE TABLE drivers (" + "name TEXT," 
                                                   + "phone_num INTEGER,"
                                                   + "rating INTEGER," 
                                                   + "trip_status TEXT);" ; 
         try{
            PreparedStatement stmt = c.prepareStatement(userTable);
             System.out.println(stmt.execute());
             stmt = c.prepareStatement(driversTable);
             System.out.println(stmt.execute());
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
      createTables();
   }
}