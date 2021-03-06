/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cab.booking.frames;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import cab.booking.frames.test;
import cab.booking.frames.LogIn;
import cab.booking.frames.FinishRide;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author PARTH KRISHNA SHARMA
 */
public class BkgCnf extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement pst=null;
    ResultSet rs;
    int t1, cID, driv, t2, clID, fr, ulID, dlID;
    public static int total_rides;
    String formattedDate;
    /**
     * Creates new form BkgCnf
     */
    public BkgCnf() {
        initComponents();
        total_rides += 1;
        conn = test.connectToDB();
        String ul="", ud="", dc="";
        String sql = "select src,dest,driver_assigned from users where userID=?";
        try{
            pst=conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(LogIn.current_id));
            rs= pst.executeQuery();
            ul=rs.getString("src");
            jLabel9.setText(ul);
            ud = rs.getString("dest");
            jLabel10.setText(ud);
            driv=rs.getInt("driver_assigned");
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        conn = test.connectToDB(); 
        sql = "select username,phone_num,rating,cloc_ID, driver_loc from drivers where driverID=?";
        try{
            pst=conn.prepareStatement(sql);
            pst.setInt(1, driv);
            rs= pst.executeQuery();
            dc = rs.getString("driver_loc");
            dlID = rs.getInt("cloc_ID");
            jLabel13.setText(rs.getString("username")+"");
            jLabel14.setText(rs.getInt("phone_num")+"");
            jLabel15.setText(rs.getFloat("rating")+"");
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        
        conn = test.connectToDB(); 
        sql = "select fare,time, dest_id,loc_id from locations where src_loc=? and dest_loc=?";
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, ul);
            pst.setString(2, ud);
            rs= pst.executeQuery();
            ulID= rs.getInt("loc_id");
            clID = rs.getInt("dest_id");
            fr = rs.getInt("fare");
            jLabel12.setText(rs.getInt("fare")+"");
            t1= rs.getInt("time");
            
            
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        if (ulID==dlID){
            t2=0;
        }
        else{
            conn = test.connectToDB(); 
            sql = "select time from locations where src_loc=? and dest_loc=?";
            try{
                pst=conn.prepareStatement(sql);
                pst.setString(1, dc);
                pst.setString(2, ul);
                rs= pst.executeQuery();
//                jLabel12.setText(rs.getInt("fare")+"");
                t2= rs.getInt("time");
                conn.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
//        Calendar calendar1 = Calendar.getInstance();
//        Date date1=calendar1.getTime();
//        DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
//        String formattedDate1=dateFormat1.format(date1);
//        JOptionPane.showMessageDialog(null,formattedDate1);
         
        Calendar calendar = Calendar.getInstance();  // gets a calendar using the default time zone and locale.
        calendar.add(Calendar.SECOND, t1+t2);      
        Date date=calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        formattedDate=dateFormat.format(date);
        
        jLabel11.setText(t1 + t2 + "");
    
        conn = test.connectToDB();
        sql ="update users set busy_till=?,fare=?, free_at=? where userID = ?" ;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, formattedDate);
            pst.setInt(2, fr);
            pst.setInt(3, 1);
            pst.setString(4, LogIn.current_id);
            pst.executeUpdate();
            conn.close();
          } catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
//        conn = test.connectToDB();
//        sql = "select busy_till from users where userID=?";
//        try{
//            pst=conn.prepareStatement(sql);
//            pst.setInt(1, Integer.parseInt(LogIn.current_id));
//            rs= pst.executeQuery();
//            //ul=rs.getString("src");
//            jLabel11.setText(rs.getString("busy_till"));
//            //ud = rs.getString("dest");
//            //jLabel10.setText(ud);
//            //driv=rs.getInt("driver_assigned");
//            conn.close();
//        }catch(SQLException e){
//            JOptionPane.showMessageDialog(null,e);
//        }
//        
        conn = test.connectToDB();
        sql ="update drivers set busy_till=?, cloc_ID=?, free=?, driver_loc=?  where driverID = ?" ;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, formattedDate);
            pst.setInt(2, clID);
            pst.setInt(3, 1);
            pst.setString(4, ud);
            pst.setInt(5, driv);
            pst.executeUpdate();
            conn.close();
          } catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }



    }
    
    
    
    //JOptionPane.showMessageDialog(null, formattedDate);
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ride Details");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Source");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Destination");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Trip Duration");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fare");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Driver Name");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Driver Mobile");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Driver Rating");

        jLabel9.setBackground(new java.awt.Color(0, 255, 255));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Source Name");
        jLabel9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 0)));

        jLabel10.setBackground(new java.awt.Color(0, 255, 255));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Destination Name");
        jLabel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 0)));

        jLabel11.setBackground(new java.awt.Color(0, 255, 255));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Duration");
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 0)));

        jLabel12.setBackground(new java.awt.Color(0, 255, 255));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Fare");
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 0)));

        jLabel13.setBackground(new java.awt.Color(0, 255, 255));
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Driver Name");
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 0)));

        jLabel14.setBackground(new java.awt.Color(0, 255, 255));
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Driver Mobile");
        jLabel14.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 0)));

        jLabel15.setBackground(new java.awt.Color(0, 255, 255));
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Driver Rating");
        jLabel15.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 0)));

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Calendar calendar1 = Calendar.getInstance();
        Date date1=calendar1.getTime();
        DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
        String formattedDate1=dateFormat1.format(date1);
//        JOptionPane.showMessageDialog(null,formattedDate);
        if(formattedDate1.compareTo(formattedDate)>=0){
//            JOptionPane.showMessageDialog(null,formattedDate1);
            FinishRide fr=new FinishRide(); 
            fr.setVisible(true);
        }
//        JOptionPane.showMessageDialog(null,formattedDate1);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BkgCnf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BkgCnf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BkgCnf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BkgCnf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BkgCnf().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
