/*GROUP NAMES NETWORK APPLICATION DEV'PT C/W
    
SSEBAALE PAUL                         15/U/12712/EVE
OKETTAYOT DERRICK                     15/U/12017/EVE
THON JOHN MALUAL                      15/X/2497/EVE
MUBOKI EMMANUEL                       15/U/7738/EVE
    */  

package mobilemoney;

import java.io.*;
import java.net.*;
import java.util.*;
public class Client{
  public static void main(String []t)throws IOException{
     Socket s=null;
  PrintWriter p = null;
String data=null;
 Scanner sc = null;
 Scanner ty =null;
  try{
       
        s= new Socket("localhost",8888);       
       p = new PrintWriter(s.getOutputStream(),true);
       sc = new Scanner(System.in);
       
while(true){    
 System.out.println("WELCOME TO JACK MOBILE MONEY SERVICES");
  
//capture and send username to server
 System.out.println("Please Enter your Username");
      String Username = sc.nextLine();
      p.println(Username);
      
       //capture and send password to server
   System.out.println("Please Enter your Password");
     String Password = sc.nextLine();
      p.println(Password);
      
      
      
      
      //capturre response
ty = new Scanner(s.getInputStream());
System.out.println(ty.nextLine());
System.out.println(ty.nextLine());

//option choosen
     String Command = sc.nextLine();
      p.println(Command);
      
      
      System.out.println(ty.nextLine());
      System.out.println(ty.nextLine());
       System.out.println(ty.nextLine());
           //cashmtn
        System.out.println(ty.nextLine());
       //float is
        System.out.println(ty.nextLine());
       
       
         // capture values
           //String Cnumber = sc.nextLine();
          // String Amount = sc.nextLine();
          // send them to server 
          //p.println(Cnumber);
         // p.println(Amount);
        //less float or transaction sucess
       System.out.println(ty.nextLine());
       
       
          
          
          
          
        
        
        
        
      
      
      
      
      
      
      
      
      
      
   }
  }finally{
   s.close();
}
 }
}//class