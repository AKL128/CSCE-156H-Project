package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class toJson {
	
	public static void personJson() {
		Gson gsonP = new GsonBuilder().setPrettyPrinting().create();

		List<Person> pList = DataConverter.parsePersonFile();

		//This writes the data into json format for Persons.json
		try {

			File outputPerson = new File("data/Persons.json");
			PrintWriter pwP = new PrintWriter(outputPerson);

			int size = pList.size();
			int counter = 0;
			for(Person p : pList) {
				if(counter == 0) {
					pwP.printf("{\n\"persons\": [");
				}
				String json2 = gsonP.toJson(p);
				pwP.printf("%s", json2);
				counter++;
				if(counter != size) {
					pwP.printf(",\n");
				} else {
					pwP.printf("\n]}");
				}
			}

			pwP.close();
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		} catch(NumberFormatException ex) {
			System.out.println("not a number");
		}
	}
	
	public static void assetJson() {
		Gson gsonA = new GsonBuilder().setPrettyPrinting().create();

		List<Asset> aList = DataConverter.parseAssetFile();

		//This writes the data into json format for Assets.json
		try {
			File outputAsset = new File("data/Assets.json");

			PrintWriter pwA = new PrintWriter(outputAsset);
			int size = aList.size();
			int counter = 0;
			for(Asset a : aList) {
				if(counter == 0) {
					pwA.printf("{\n\"assets\": [");
				}
				String jsonA = gsonA.toJson(a);
				pwA.printf("%s", jsonA);
				counter++;
				if(counter != size) {
					pwA.printf(",\n");
				} else {
					pwA.printf("\n]}");
				}
			}

			pwA.close();
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
	}

}
