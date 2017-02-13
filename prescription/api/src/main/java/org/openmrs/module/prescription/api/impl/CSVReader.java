package org.openmrs.module.prescription.api.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

import org.openmrs.module.prescription.Drug;

public class CSVReader {
	
	private void checkDrugFileExist(String csvFile) throws FileNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();
		if (!new File(classLoader.getResource(csvFile).getFile()).exists())
			throw new FileNotFoundException("Druglist[...].csv (filename set in config.properties) does not exist");
	}
	
	public List<Drug> read(String csvFile, int cvsFileColId, int cvsFileColTxt) throws FileNotFoundException {
		
		checkDrugFileExist(csvFile);
		
		String line = "";
		String cvsSplitBy = ",";
		
		List<Drug> medications = null;
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(csvFile).getFile());
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			medications = new ArrayList<Drug>();
			
			while ((line = br.readLine()) != null) {
				
				// use comma as separator
				String[] medication = line.split(cvsSplitBy);
				
				// "drug_id","concept_id","name","combination","simple_strength","
				// "simple_name_EN","simple_dosage_form_EN","name_concatenated_EN 
				// "simple_name_FR","simple_dosage_form_FR","name_concatenated_FR
				
				try {
					Drug element = new Drug();
					element.setId(Integer.parseInt(medication[cvsFileColId]));
					
					//7 = EN, 10 = FR, valid for drug_list_20161107					
					element.setDrugConcatenated(medication[cvsFileColTxt].replaceAll("\"", ""));
					medications.add(element);
				}
				catch (Exception exc) {
					exc.printStackTrace();
				}
			}
			br.close();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return medications;
	}
	
}
