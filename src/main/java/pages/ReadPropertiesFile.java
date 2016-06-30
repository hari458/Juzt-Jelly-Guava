package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		Properties prop=new Properties();
		
		prop.load(new FileInputStream
				(new File("./object.properties")));
		
		System.out.println(prop.getProperty("Login.UserName.Id"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
