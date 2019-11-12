import cab.booking.frames.test;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class AssignRandomLocationToDrivers
{  
	Connection con=null;
	ResultSet rs= null;
	PreparedStatement pst = null;

    public void allocate(String[] args)
    {  
    try
    {
        con = test.connectToDB();
        HashMap<String,Integer> hm = new HashMap<String,Integer>();			//creating a HashMap to assign location to each driver

        String query = "select username from drivers where free=0";
        pst= con.prepareStatement(query);
        rs=pst.executeQuery();

        List<String> driver = new ArrayList();	//input free driver list here
        if(rs != null) driver.add(rs.getString("username"));
        while (rs.next())
        {

        	driver.add(rs.getString("username"));
        }

        Collections.shuffle(driver);										//Shuffle the list of drivers

        String[] driverArr = new String[driver.size()];				//creating a character array to store driver list
        driverArr = driver.toArray(driverArr);								//converting driver list to driverArr array
    
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

        Set<Map.Entry<String,Integer>> set = hm.entrySet();					//Set is collection of distinct objects
        for (Map.Entry<String,Integer> me: set)								//display key and values stored in HashMap
        {
            String query2 = "update drivers set loc_id =  ? where username = ?";
            pst = con.prepareStatement(query2);
            pst.setInt(1, me.getValue());
            pst.setString(1, me.getKey());
//            System.out.print(me.getKey()+":");
//        	System.out.println(me.getValue());
        }
        con.close();
    }
    catch (Exception e)
    {
    	JOptionPane.showMessageDialog(null,e);
    }
    }  
}  
//if driver is free - allot 1-5 in order
//for rest of drivers alot 