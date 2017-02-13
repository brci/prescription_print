/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.prescription.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.prescription.PrescriptionConfig;
import org.openmrs.module.prescription.Prescription;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.openmrs.Patient;

import org.openmrs.module.prescription.Drug;

/**
 * The main service of this module, which is exposed for other modules. See
 * moduleApplicationContext.xml on how it is wired up.
 */
public interface PrescriptionService extends OpenmrsService {
	
	/**
	 * Returns an item by uuid. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized()
	@Transactional(readOnly = true)
	Prescription getItemByUuid(String uuid) throws APIException;
	
	/**
	 * Saves an item. Sets the owner to superuser, if it is not set. It can be called by users with
	 * this module's privilege. It is executed in a transaction.
	 * 
	 * @param item
	 * @return
	 * @throws APIException
	 */
	
	/*
	 * not clear where to set this privilege - seems not to be visible in roles/admin view.
	 * that is why: not used.
	 */
	
	//@Authorized(PrescriptionConfig.MODIFY_PRESCRIPTION_PRIVILEGE)
	@Transactional
	Prescription saveItem(Prescription item) throws APIException;
	
	@Authorized()
	@Transactional(readOnly = true)
	Prescription getItemById(Integer pid) throws APIException;
	
	/*
	 * not clear where to set this privilege - seems not to be visible in roles/admin view.
	 * that is why: not used.
	 */
	
	//@Authorized(PrescriptionConfig.MODIFY_PRESCRIPTION_PRIVILEGE)
	@Transactional
	boolean deleteItem(Prescription item) throws APIException;
	
	@Authorized
	public List<Prescription> getAllPrescriptions(Patient var1) throws APIException, IllegalArgumentException;
	
	@Authorized
	public List<Prescription> getAllPrescriptionsByFile(String fileName) throws APIException;
	
	@Authorized
	public HashMap<Integer, Prescription> getAllPrescriptionsMap(Patient var1) throws APIException, IllegalArgumentException;
	
	@Authorized
	public List<String> getAllPrescriptionsGrouped(Patient var1) throws APIException, IllegalArgumentException;
	
	// following methods retrieve data of config-files
	// data are kept within the PrescriptionServiceImpl class
	// not a perfect solution .. however, decision: only 1 module to install, and: configurable
	
	public String[] getAddressToPrint() throws FileNotFoundException;
	
	public List<Drug> getAllDrugs() throws FileNotFoundException;
	
	public String getDrugDescriptionById(Integer id);
	
}
