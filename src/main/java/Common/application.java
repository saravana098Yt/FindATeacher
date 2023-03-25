package Common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;

public class application {
  public static Connection dbConnection;
  public static File file=new File("/home/saravan-zstk298/FindATeacher/num.csv");
  public static FileReader re=null;
  public static BufferedReader rs=null;
  public static int readerfile() throws FileNotFoundException {
	  int num=0;
	try {
	  
	  re=new FileReader(file);
	   rs=new BufferedReader(re);
	   String str="";
	  if((str = rs.readLine()) != null) {
			  System.out.println("ssss"+str);
		  num = Integer.parseInt(str);
	  }
	  
	}
	  catch(Exception ex) {
		  System.out.println(ex.getMessage());
	  }
	 
	  return num;
  }
  public static void writeFile(int num) {
	  try {
		  
		  FileWriter rm=new FileWriter("/home/saravan-zstk298/FindATeacher/num.csv");
		  rm.write(Integer.toString(num));
		  rm.close();
	  }
	  catch(Exception ex) {
		  System.out.println(ex.getMessage());
	  }
  }
  public static java.sql.Date dateConvert(String date){
	  @SuppressWarnings("deprecation")
	java.sql.Date sqlDate=new java.sql.Date(new java.util.Date(date).getTime());
	  return sqlDate;
  }
}
