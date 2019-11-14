package cab.booking.frames;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.*;

public class AssignRandomLocationToDrivers
{  
	 static Connection con=null;
	 static ResultSet rs= null;
	 static PreparedStatement pst = null;
     
     public static void alloc(String n, Integer m){
         con=test.connectToDB();
         
         String query="update drivers set cloc_ID = ? where username = ?";
         try{
             con.setAutoCommit(false);
             pst = con.prepareStatement(query);
             pst.setInt(1,m);
             pst.setString(2,n);
             pst.executeUpdate();
             con.commit();
             con.close();
         }catch(SQLException se){
             JOptionPane.showMessageDialog(null, se);
         }
     }    
         
         
    public static void allocate()
    {  
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        con= test.connectToDB();
        try{
            String st = "select * from drivers where free=?";
            pst=con.prepareStatement(st);
            pst.setInt(1,0);
            rs= pst.executeQuery();
            List<String> driver = new ArrayList();
            while(rs.next()){
                driver.add(rs.getString("username"));
            }
            Collections.shuffle(driver);
            con.close();
            
            String[] driverArr = new String[driver.size()];
            driverArr = driver.toArray(driverArr);
            int location = 1;													//initializing location to 1
            for (int i=0; i<driverArr.length; i++)							//to assign location to each driver
            {
                    if (location<=5)												//assigning location in ordered manner 1-5
                    {
                        hm.put(driverArr[i],new Integer(location));
                        location++;
                        if (location >5)
                        {
                                location =1;
                        }
                    }
            } 
            Iterator<Map.Entry<String,Integer>> itr = hm.entrySet().iterator(); 
          
        while(itr.hasNext()) 
        { 
             Map.Entry<String, Integer> entry = itr.next(); 
//             JOptionPane.showMessageDialog(null, "Key = " + entry.getKey());  
//             JOptionPane.showMessageDialog(null, "value = " + entry.getValue());
               alloc(entry.getKey(), entry.getValue());
        }             
        }catch(SQLException e){
        }
    } 
}
//if driver is free - allot 1-5 in order
//for rest of drivers alot 