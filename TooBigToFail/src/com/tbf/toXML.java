/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a program that holds methods that converts the data of Person and Asset to XML.
 * 
 */

package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class toXML {
	
	//This writes the data into xml format for Persons.xml
	public static void personXML() {
		
		try {
			XStream xs = new XStream();			
			List<Person> pList = DataConverter.parsePersonFile();

			File outputPerson = new File("data/Persons.xml");
			PrintWriter pwP = new PrintWriter(outputPerson);

			for(Person p : pList) {
				String xml = xs.toXML(p);
				pwP.write(xml);
			}

			pwP.close();
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		} catch(NumberFormatException ex) {
			System.out.println("not a number");
		}
		
	}
	
	//This writes the data into xml format for Assets.xml
	public static void assetXML() {

		try {
			XStream xs = new XStream();			
			List<Asset> aList = DataConverter.parseAssetFile();

			File outputAsset = new File("data/Assets.xml");
			PrintWriter pwA = new PrintWriter(outputAsset);

			for(Asset a : aList) {
				String xml = xs.toXML(a);
				pwA.write(xml);
			}

			pwA.close();
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		} catch(NumberFormatException ex) {
			System.out.println("not a number");
		}
		
		
	}

}
