/*GROUP NAMES NETWORK APPLICATION DEV'PT C/W
    
SSEBAALE PAUL                         15/U/12712/EVE
OKETTAYOT DERRICK                     15/U/12017/EVE
THON JOHN MALUAL                      15/X/2497/EVE
MUBOKI EMMANUEL                       15/U/7738/EVE
    */  

package mobilemoney;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.sql.*;
public class Server{
   public static void main(String op[])throws IOException{
   ServerSocket soc = null;
 Socket s=null;
  Scanner sc=null;
  PrintWriter uy =null;
     try{
      soc = new ServerSocket(8888,3);
       while(true){
       System.out.println("Listening ...");
     s = soc.accept();
      sc = new Scanner(s.getInputStream());
      uy = new PrintWriter(s.getOutputStream(),true);
      
    // String g = sc.nextLine();
   //System.out.println("We received "+g);
   //new
   try{
String user=sc.nextLine();
String pass=sc.nextLine();
 Class.forName("com.mysql.jdbc.Driver").newInstance();
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jack","root","");  
           Statement st=con.createStatement();
           ResultSet rs=st.executeQuery("select * from attendant where Ausername='"+user+"' and Password='"+pass+"'");
           int count=0;
           while(rs.next()){
           count++;
          }
          if(count>0){
           uy.println("Password is Correct");
           uy.println("Please Enter Command e.g mtnwithdraw,10000,0770005874");
           
           String result;
           String Cnumber;
           String  Amount1;
          String Command=sc.nextLine();
         String[] Command1 = Command.split(",");
          result = Command1[0];
          Amount1 = Command1[1];
           Cnumber=Command1[2];
           System.out.println(result);
            System.out.println(Amount1);
             System.out.println(Cnumber);
             
             int Amount5 = Integer.parseInt(Amount1);
             
             
                if(result.equals("mtndeposit")){
                   if(Amount5 > 4000000){
                    uy.println("the maximum amount is 5 million for mtn transactions");
                   }
                   
                   else{
                // deposit
                 uy.println("Mtn deposit ?");  
                  int Nucashmtn,Nufloatmtn,Amount;
                          try{
             Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/jack","root","");
             //statement
             Statement st1 = con1.createStatement();
             Statement st2 = con1.createStatement();
             Statement st3 = con1.createStatement();
             //PrintWriter uy1 = new PrintWriter(s.getOutputStream(),true);
             //querry
             ResultSet rs1= st1.executeQuery("select * from attendant where  attendant.Ausername ='"+user + "'" );
              uy.println("whats up"); 
              String Kn=null;
              String mtncash=null;
              String Knumber=null;
               while(rs1.next()){
                     Kn=rs1.getString("Kname");
                     mtncash=rs1.getString("Cashmtn");
                     
             }
             uy.println("Kname is...."+Kn);
             uy.println("cash is...."+mtncash);
             String mtn="MTN";
             ResultSet rs2= st2.executeQuery("select * from simcard where KNAME='"+Kn + "' and Simname='"+mtn+"' ");
             String simfloat=null;
                     while(rs2.next()){
                     simfloat = rs2.getString("Float");
                     Knumber = rs2.getString("Knumber");
                     }
                     uy.println("float is...."+simfloat);  
            int simfloat1 = Integer.parseInt(simfloat);
            int mtncash1 = Integer.parseInt(mtncash);
            
           System.out.println("number is...."+Cnumber);
           System.out.println("amount is...."+Amount1);
           
        
            Amount = Integer.parseInt(Amount1);
                     if(simfloat1<Amount){
                         uy.println("You donot have enough float to carry out this transaction..Forward it to another kiosk");  
                     }
                     else{
                         
                         //calculate commission for mtn deposit
                         
                         int Comm=0;
                          //calculate commission for mtn withdraw
            
            
                 if(Amount >= 5001 && Amount <= 60000){
                    
               Comm = 285;
            }
                else if(Amount >= 60001 && Amount <= 125000){
                    
               Comm = 440;
            }
                else if(Amount >= 125001 && Amount <= 250000){
                    
               Comm = 520;
            }
                else if(Amount >= 250001 && Amount <= 500000){
                    
               Comm = 850;
            }
                else if(Amount >= 5000001 && Amount <= 1000000){
                    
               Comm = 2500;
            }
                else if(Amount >= 10000001 && Amount <= 2000000){
                    
               Comm = 4500;
            }
                else if(Amount >= 20000001 && Amount <= 4000000){
                    
               Comm = 8000;
            }
            
            
             else if(Amount <= 5000){
                
                Comm = 0;
            }    

                         
                         
                         
                        
                        Nufloatmtn = simfloat1-Amount; 
                        Nucashmtn=mtncash1+Amount;
                     String sql = "UPDATE simcard "
                             + "SET simcard.Float ='"+Nufloatmtn+"' WHERE simcard.KNAME='"+Kn + "' and simcard.Simname='"+mtn+"'";
                     st3.executeUpdate(sql);
                     
                     String sql2 = "UPDATE attendant "
                             + "SET attendant.Cashmtn ='"+Nucashmtn+"' WHERE attendant.Ausername ='"+user + "'";
                     st3.executeUpdate(sql2);
                     
                     String sql3 = "INSERT INTO transaction(Tnumber,Knumber,Cnumber,Ausername,Amount,Type,Commission,KNAME,Linetype,Commissionstatus) " +
                "VALUES (NULL,'"+Knumber + "', '"+Cnumber + "', '"+user + "','"+Amount + "','Deposit','"+Comm + "','"+Kn + "','MTN','Notpaid')";
                     st3.executeUpdate(sql3);
                     
                    
                    
                    uy.println("Transaction was successful");
                    

      
                        
                     }
                     
                     
                     
                     
                     
                     
                     
             }catch(SQLException yu){
                 System.out.println("Error 2"+yu.getMessage());
             }
                 
               
             }}  
                
                
                
                
                
                
                
                
                
                
                
             else if(result.equals("mtnwithdraw")){
                 int Nucashmtn,Nufloatmtn,Amount;
                 if(Amount5 > 4000000){
                    uy.println("the maximum amount is 5 million for mtn transactions");
                   }
                 else{
                     // deposit
                 uy.println("Mtn with draw ?");  
                  
                          try{
             Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/jack","root","");
             //statement
             Statement st1 = con1.createStatement();
             Statement st2 = con1.createStatement();
             Statement st3 = con1.createStatement();
             //PrintWriter uy1 = new PrintWriter(s.getOutputStream(),true);
             //querry
             ResultSet rs1= st1.executeQuery("select * from attendant where  attendant.Ausername ='"+user + "'" );
              uy.println("whats up"); 
              String Kn=null;
              String mtncash=null;
              String Knumber=null;
               while(rs1.next()){
                     Kn=rs1.getString("Kname");
                     mtncash=rs1.getString("Cashmtn");
                     
             }
             uy.println("Kname is...."+Kn);
             uy.println("cash is...."+mtncash);
             String mtn="MTN";
             ResultSet rs2= st2.executeQuery("select * from simcard where KNAME='"+Kn + "' and Simname='"+mtn+"' ");
             String simfloat=null;
                     while(rs2.next()){
                     simfloat = rs2.getString("Float");
                     Knumber = rs2.getString("Knumber");
                     }
                     uy.println("float is...."+simfloat);  
            int simfloat1 = Integer.parseInt(simfloat);
            int mtncash1 = Integer.parseInt(mtncash);
            
           System.out.println("number is...."+Cnumber);
           System.out.println("amount is...."+Amount1);
           
        
            Amount = Integer.parseInt(Amount1);
                     if(mtncash1<Amount5){
                         uy.println("You donot have enough cash");  
                     }
                     else{
                         int Comm=0;
                          //calculate commission for mtn withdraw
               if(Amount >= 500 && Amount <= 2500){
                   
               Comm = 100;
            }
               else if(Amount >= 2501 && Amount <= 5000){
                    
               Comm = 125;
            }
                 else if(Amount >= 5001 && Amount <= 15000){
                    
               Comm = 450;
            }
                else if(Amount >= 15001 && Amount <= 30000){
                    
               Comm = 500;
            }
                 else if(Amount >= 30001 && Amount <= 45000){
                    
               Comm = 525;
            }
                else if(Amount >= 45001 && Amount <= 60000){
                    
               Comm =  575;
            }
                else if(Amount >= 60001 && Amount <= 125000){
                    
               Comm = 700;
            }
                else if(Amount >= 125001 && Amount <= 250000){
                    
               Comm = 1300;
            }
                else if(Amount >= 250001 && Amount <= 500000){
                    
               Comm = 2600;
            }
                else if(Amount >= 5000001 && Amount <= 1000000){
                    
               Comm = 5000;
            }
                else if(Amount >= 10000001 && Amount <= 2000000){
                    
               Comm = 7500;
            }
                else if(Amount >= 20000001 && Amount <= 4000000){
                    
               Comm = 12500;
            }
            else if(Amount <= 500){
                
                Comm = 0;
            }

                          
                          
                          
                        
                        Nufloatmtn = simfloat1+Amount; 
                        Nucashmtn=mtncash1-Amount;
                     String sql = "UPDATE simcard "
                             + "SET simcard.Float ='"+Nufloatmtn+"' WHERE simcard.KNAME='"+Kn + "' and simcard.Simname='"+mtn+"'";
                     st3.executeUpdate(sql);
                     
                     String sql2 = "UPDATE attendant "
                             + "SET attendant.Cashmtn ='"+Nucashmtn+"' WHERE attendant.Ausername ='"+user + "'";
                     st3.executeUpdate(sql2);
                     
                     String sql3 = "INSERT INTO transaction(Tnumber,Knumber,Cnumber,Ausername,Amount,Type,Commission,KNAME,Linetype,Commissionstatus) " +
                "VALUES (NULL,'"+Knumber + "', '"+Cnumber + "', '"+user + "','"+Amount + "','WithDraw','"+Comm + "','"+Kn + "','MTN','Notpaid')";
                     st3.executeUpdate(sql3);
                     
                    
                    
                    uy.println("Transaction was successful");
                    
                    

      
                        
                     }
                     
                     
                     
                     
                     
                     
                     
             }catch(SQLException yu){
                 System.out.println("Error 2"+yu.getMessage());
             }
                 
              
                    
                    
                    
                    
                    
                    
                 }}
             
             
             
             
             
             
             
             
             
               else if(result.equals("waridwithdraw")){
                   
                   int Nucashwarid,Nufloatwarid,Amount;
                   if(Amount5 > 5000000){
                    uy.println("the maximum amount is 5 million for Warid transactions");
                   }
                   else{
                      // deposit
                 uy.println("Warid with draw ?");  
                  
                          try{
             Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/jack","root","");
             //statement
             Statement st1 = con1.createStatement();
             Statement st2 = con1.createStatement();
             Statement st3 = con1.createStatement();
             //PrintWriter uy1 = new PrintWriter(s.getOutputStream(),true);
             //querry
             ResultSet rs1= st1.executeQuery("select * from attendant where  attendant.Ausername ='"+user + "'" );
              uy.println("whats up"); 
              String Kn=null;
              String waridcash=null;
              String Knumber=null;
               while(rs1.next()){
                     Kn=rs1.getString("Kname");
                     waridcash=rs1.getString("Cashwarid");
                     
             }
             uy.println("Kname is...."+Kn);
             uy.println("cash is...."+waridcash);
             String warid="WARID";
             ResultSet rs2= st2.executeQuery("select * from simcard where KNAME='"+Kn + "' and Simname='"+warid+"' ");
             String simfloat=null;
                     while(rs2.next()){
                     simfloat = rs2.getString("Float");
                     Knumber = rs2.getString("Knumber");
                     }
                     uy.println("float is...."+simfloat);  
            int simfloat1 = Integer.parseInt(simfloat);
            int waridcash1 = Integer.parseInt(waridcash);
            
           System.out.println("number is...."+Cnumber);
           System.out.println("amount is...."+Amount1);
           
        
            Amount = Integer.parseInt(Amount1);
                     if(waridcash1<Amount5){
                         uy.println("You donot have enough cash");  
                     }
                     else{
                         int Comm=0;
                          //calculate commission for warid withdraw
               if(Amount >= 500 && Amount <= 2500){
                   
               Comm = 100;
            }
                else if(Amount >= 2501 && Amount <= 5000){
                    
               Comm = 125;
            }
               if(Amount >= 5001 && Amount <= 15000){
                   
               Comm = 450;
            }
                else if(Amount >= 15001 && Amount <= 30000){
                    
               Comm = 500;
            }
                 else if(Amount >= 30001 && Amount <= 45000){
                    
               Comm = 525;
            }
                else if(Amount >= 45001 && Amount <= 60000){
                    
               Comm =  575;
            }
                else if(Amount >= 60001 && Amount <= 125000){
                    
               Comm = 700;
            }
                else if(Amount >= 125001 && Amount <= 250000){
                    
               Comm = 1300;
            }
                else if(Amount >= 250001 && Amount <= 500000){
                    
               Comm = 2600;
            }
                else if(Amount >= 5000001 && Amount <= 1000000){
                    
               Comm = 5000;
            }
                else if(Amount >= 10000001 && Amount <= 2000000){
                    
               Comm = 7500;
                }
                else if(Amount >= 20000001 && Amount <= 3000000){
                    
               Comm = 12500;
            }
            
                else if(Amount >= 30000001 && Amount <= 4000000){
                    
               Comm = 12500;
            }
                else if(Amount >= 40000001 && Amount <= 5000000){
                    
               Comm = 15000;
            }
            else if(Amount <= 500){
                
                Comm = 0;
            }

                          
                          
                          
                        
                        Nufloatwarid = simfloat1+Amount; 
                        Nucashwarid=waridcash1-Amount;
                     String sql = "UPDATE simcard "
                             + "SET simcard.Float ='"+Nufloatwarid+"' WHERE simcard.KNAME='"+Kn + "' and simcard.Simname='"+warid+"'";
                     st3.executeUpdate(sql);
                     
                     String sql2 = "UPDATE attendant "
                             + "SET attendant.Cashwarid ='"+Nucashwarid+"' WHERE attendant.Ausername ='"+user + "'";
                     st3.executeUpdate(sql2);
                     
                     String sql3 = "INSERT INTO transaction(Tnumber,Knumber,Cnumber,Ausername,Amount,Type,Commission,KNAME,Linetype,Commissionstatus) " +
                "VALUES (NULL,'"+Knumber + "', '"+Cnumber + "', '"+user + "','"+Amount + "','WithDraw','"+Comm + "','"+Kn + "','WARID','Notpaid')";
                     st3.executeUpdate(sql3);
                     
                    
                    
                    uy.println("Transaction was successful");
                    
                    

      
                        
                     }
                     
                     
                     
                     
                     
                     
                     
             }catch(SQLException yu){
                 System.out.println("Error 2"+yu.getMessage());
             }
                 
              
                    
                    
                    
                    
                    
                               
                   }   }
               
               
               
               
               
               
             else if(result.equals("wariddeposit")){
                    
                  int Nucashwarid,Nufloatwarid,Amount;
                  
                  if(Amount5 > 5000000){
                    uy.println("the maximum amount is 5 million for Warid transactions");
                   }
                  else {
                      // deposit
                 uy.println("Warid deposit ?");  
                  
                          try{
             Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/jack","root","");
             //statement
             Statement st1 = con1.createStatement();
             Statement st2 = con1.createStatement();
             Statement st3 = con1.createStatement();
             //PrintWriter uy1 = new PrintWriter(s.getOutputStream(),true);
             //querry
             ResultSet rs1= st1.executeQuery("select * from attendant where  attendant.Ausername ='"+user + "'" );
              uy.println("whats up"); 
              String Kn=null;
              String waridcash=null;
              String Knumber=null;
               while(rs1.next()){
                     Kn=rs1.getString("Kname");
                     waridcash=rs1.getString("Cashwarid");
                     
             }
             uy.println("Kname is...."+Kn);
             uy.println("cash is...."+waridcash);
             String warid="WARID";
             ResultSet rs2= st2.executeQuery("select * from simcard where KNAME='"+Kn + "' and Simname='"+warid+"' ");
             String simfloat=null;
                     while(rs2.next()){
                     simfloat = rs2.getString("Float");
                     Knumber = rs2.getString("Knumber");
                     }
                     uy.println("float is...."+simfloat);  
            int simfloat1 = Integer.parseInt(simfloat);
            int waridcash1 = Integer.parseInt(waridcash);
            
           System.out.println("number is...."+Cnumber);
           System.out.println("amount is...."+Amount1);
           
        
            Amount = Integer.parseInt(Amount1);
                     if(simfloat1<Amount5){
                         uy.println("You donot have enough Float Request another kiosk to perform transaction");  
                     }
                     else{
                         int Comm=0;
                          //calculate commission for warid withdraw
               if(Amount >= 500 && Amount <= 2500){
                   
               Comm = 150;
            }
                else if(Amount >= 2501 && Amount <= 5000){
                    
               Comm = 150;
            }
               if(Amount >= 5001 && Amount <= 15000){
                   
               Comm = 285;
            }
                else if(Amount >= 15001 && Amount <= 30000){
                    
               Comm = 285;
            }
                 else if(Amount >= 30001 && Amount <= 45000){
                    
               Comm = 285;
            }
                else if(Amount >= 45001 && Amount <= 60000){
                    
               Comm =  285;
            }
                else if(Amount >= 60001 && Amount <= 125000){
                    
               Comm = 440;
            }
                else if(Amount >= 125001 && Amount <= 250000){
                    
               Comm = 520;
            }
                else if(Amount >= 250001 && Amount <= 500000){
                    
               Comm = 850;
            }
                else if(Amount >= 5000001 && Amount <= 1000000){
                    
               Comm = 2500;
            }
                else if(Amount >= 10000001 && Amount <= 2000000){
                    
               Comm = 4500;
                }
                else if(Amount >= 20000001 && Amount <= 3000000){
                    
               Comm = 8000;
            }
            
                else if(Amount >= 30000001 && Amount <= 4000000){
                    
               Comm = 8000;
            }
                else if(Amount >= 40000001 && Amount <= 5000000){
                    
               Comm = 9000;
            }
            else if(Amount <= 500){
                
                Comm = 0;
            }

                          
                          
                          
                        
                        Nufloatwarid = simfloat1-Amount; 
                        Nucashwarid=waridcash1+Amount;
                     String sql = "UPDATE simcard "
                             + "SET simcard.Float ='"+Nufloatwarid+"' WHERE simcard.KNAME='"+Kn + "' and simcard.Simname='"+warid+"'";
                     st3.executeUpdate(sql);
                     
                     String sql2 = "UPDATE attendant "
                             + "SET attendant.Cashwarid ='"+Nucashwarid+"' WHERE attendant.Ausername ='"+user + "'";
                     st3.executeUpdate(sql2);
                     
                     String sql3 = "INSERT INTO transaction(Tnumber,Knumber,Cnumber,Ausername,Amount,Type,Commission,KNAME,Linetype,Commissionstatus) " +
                "VALUES (NULL,'"+Knumber + "', '"+Cnumber + "', '"+user + "','"+Amount + "','Deposit','"+Comm + "','"+Kn + "','WARID','Notpaid')";
                     st3.executeUpdate(sql3);
                     
                    
                    
                    uy.println("Transaction was successful");
                    
                    

      
                        
                     }
                     
                     
                     
                     
                     
                     
                     
             }catch(SQLException yu){
                 System.out.println("Error 2...."+yu.getMessage());
             }
                 
              
                    
                    
                 
                 
                 
                 
                  }}            
             else{
                uy.println("Invalid command."); 
            }          
           }
          else{
           uy.println("Password is Wrong");
          }
        }
    catch(Exception e){
    System.out.println(e);
}  
 }
   }finally{
   soc.close();
  }
 }
}//end 