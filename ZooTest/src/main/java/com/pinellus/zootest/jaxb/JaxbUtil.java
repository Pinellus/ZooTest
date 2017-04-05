package com.pinellus.zootest.jaxb;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.pinellus.zootest.domain.TestZoo;

public class JaxbUtil {
	
	public static void Marshal(TestZoo testZoo) {
		
		try{
		    //creating the JAXB context
		    JAXBContext jContext = JAXBContext.newInstance(TestZoo.class);
		    //creating the marshaller object
		    Marshaller marshallObj = jContext.createMarshaller();
		    //setting the property to show xml format output
		    marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    
		    System.out.println(testZoo.getPath());
		    
		    //calling the marshall method
		    marshallObj.marshal(testZoo, new FileOutputStream(new File(testZoo.getPath())));
		    
		} catch(Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static TestZoo Unmarshal(String pathFile) {
		
		TestZoo testZoo = new TestZoo();
		
		try{
		    //getting the xml file to read
		    File file = new File(pathFile);
		    //creating the JAXB context
		    JAXBContext jContext = JAXBContext.newInstance(TestZoo.class);
		    //creating the unmarshall object
		    Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
		    //calling the unmarshall method
		    testZoo=(TestZoo) unmarshallerObj.unmarshal(file);
		    
		}catch(Exception e){
		    e.printStackTrace();
		}
		
		return testZoo;
	}
}
