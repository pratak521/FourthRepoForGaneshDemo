package com.pat;



import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.SaslException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {
	private List<Employee> emplist=null;
	private Employee emp=null;
	private StringBuilder data=null;
	 public List<Employee> getEmpList()
	 {
		 return emplist;
	 }
	 boolean bAge=false;
	 boolean bName=false;
	boolean bGender=false;
	boolean bRole=false;
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException
	{
		if(qName.equalsIgnoreCase("employee"))
		{
			//Create a new employee and put it in map
			String id=attributes.getValue("id");
			//initialize object and set id attribute
			emp=new Employee();
			emp.setId(Integer.parseInt(id));
			// initialize list
			if(emplist==null)
				emplist=new ArrayList<>();
		} else if (qName.equalsIgnoreCase("Name")) {
			//set boolean values for fields , will be ussed in setting Employee Varibale
			bName=true;
		}else if (qName.equalsIgnoreCase("age")) {
			bAge=true;
			
		}else if (qName.equalsIgnoreCase("gender")) {
			bGender=true;
			
		}else if (qName.equalsIgnoreCase("role")) {
			bRole=true;
			
		}
		// Create Data Container
		data=new StringBuilder();
	}
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(bAge)
		{
			// Age element set Employee age
			emp.setAge(Integer.parseInt(data.toString()));
			bAge=false;
		}else if (bName) {
			emp.setName(data.toString());
			bName=false;
		} else if (bRole) {
			emp.setRole(data.toString());
			bRole=false;
		}else if (bGender) {
			emp.setGender(data.toString());
			bGender=false;
		}
		if(qName.equalsIgnoreCase("Employee")) {
			// add employee object to list
			emplist.add(emp);
			
		}
		
		
	}
	public void characters(char ch[], int start, int length) throws SAXException{
		data.append(new String(ch,start,length));
	}
	

}
