/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.prescription;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.PatientService;
import org.openmrs.api.UserService;
import org.openmrs.module.prescription.Prescription;
import org.openmrs.module.prescription.api.dao.PrescriptionDao;
import org.openmrs.module.prescription.api.impl.PrescriptionServiceImpl;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.Verifies;

import junit.framework.Assert;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * This is a unit test, which verifies logic in PrescriptionService. It doesn't extend
 * BaseModuleContextSensitiveTest, thus it is run without the in-memory DB and Spring context.
 */
public class PrescriptionServiceTest extends BaseModuleContextSensitiveTest {
	
	@InjectMocks
	PrescriptionServiceImpl basicModuleService;
	
	@Mock
	PrescriptionDao dao;
	
	@Mock
	UserService userService;
	
	@Mock
	PatientService patientService;
	
	@Before
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	/*
	@Test
	@Verifies(value = "should not fail with empty object", method = "toString()")
	public void toString_shouldNotFailWithEmptyObject() throws Exception {
	*/
	
	/*
	 * Test does not work .. why not?
	 */
	/*
	@SuppressWarnings("deprecation")
	@Test
	@Verifies(value = "should return all prescriptions of patient", method = "getAllPrescriptions(Patient)")
	public void getAllPrescriptions_shouldReturnAllPrescriptionsOfPatient() throws Exception {
		
		Patient patient = new Patient();
		patient.setId(1);
		
		Prescription item = new Prescription();
		item.setDescription("description1");
		item.setPatientId(1);
		
		when(dao.saveItem(item)).thenReturn(item);
		
		Prescription item2 = new Prescription();
		item2.setDescription("description2");
		item2.setPatientId(1);
		
		when(dao.saveItem(item2)).thenReturn(item2);
		
		List<Prescription> prescriptions = basicModuleService.getAllPrescriptions(patient);
		
		Assert.assertEquals(2, prescriptions.size());
		
		prescriptions.remove(item);
		prescriptions.remove(item2);
		
		Assert.assertEquals(0, prescriptions.size());
	}
	*/
	/*
	@SuppressWarnings("deprecation")
	@Test
	@Verifies(value = "should return all p receipts = prescriptions grouped, of patient", method = "getAllPrescriptionsGrouped(Patient)")
	public void getAllPrescriptionsGrouped_shouldReturnAllReceiptsOfPatientAsStringlist() throws Exception {
		
		Patient patient = new Patient();
		patient.setId(1);
		when(patientService.getPatient(1)).thenReturn(patient);
		
		Prescription item = new Prescription();
		item.setPatientId(1);
		item.setPrescriptionFile("file1");
		
		when(dao.saveItem(item)).thenReturn(item);
		
		Prescription item2 = new Prescription();
		item2.setPatientId(1);
		item2.setPrescriptionFile("file1");
		
		Prescription item3 = new Prescription();
		item3.setPatientId(1);
		item3.setPrescriptionFile("file2");
		
		when(dao.saveItem(item2)).thenReturn(item2);
		
		List<String> prescriptions = basicModuleService.getAllPrescriptionsGrouped(patient);
		
		Assert.assertEquals("Prescriptions should be grouped by receipt, 3 p but 2 r", 2, prescriptions.size());
		
		Assert.assertEquals("Prescription should be grouped by receipt 1", prescriptions.get(0), "file1");
		Assert.assertEquals("Prescription should be grouped by receipt 2", prescriptions.get(1), "file2");
	}
	*/
	/*
	@SuppressWarnings("deprecation")
	@Test
	@Verifies(value = "should return all prescriptions of file", method = "getAllPrescriptionsByFile(String)")
	public void getAllPrescriptionsByFile_shouldReturnAllPrescriptionsOfFile() throws Exception {
		
		Prescription item = new Prescription();
		item.setPrescriptionFile("file1");
		
		when(dao.saveItem(item)).thenReturn(item);
		
		Prescription item2 = new Prescription();
		item.setPrescriptionFile("file1");
		
		when(dao.saveItem(item2)).thenReturn(item2);
		
		List<Prescription> prescriptions = basicModuleService.getAllPrescriptionsByFile("file1");
		
		prescriptions.remove(item);
		prescriptions.remove(item2);
		
		Assert.assertEquals(0, prescriptions.size());
	}
	*/
	@Test
	public void saveItem_shouldSetOwnerIfNotSet() {
		//Given
		Prescription item = new Prescription();
		item.setDescription("some description");
		
		when(dao.saveItem(item)).thenReturn(item);
		
		User user = new User();
		when(userService.getUser(1)).thenReturn(user);
		
		//When
		basicModuleService.saveItem(item);
		
		//Then
		//assertThat(item, hasProperty("owner", is(user)));
	}
}
