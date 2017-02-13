Prescription print
------------------

Add medications of a list, and mark them as 'to print' - onclick a pdf file is created.

# 1. Config

## 1.1.1 api

config.properties: name of medications-list csv-file, column of: id, 'description' = String to show

messages.properties: EN, FR, ES: for the UI

druglist[any name].csv: comma separated list of drug names, and id.

Only 1 value is taken for the prescription, that is why, the 'description' is composed of: drug name, dosage, dosage form (e.g. pill); or composition in case of a multi-drug.

The druglist can be created and added, with these 2 columns. The druglist is read into a table.

## 1.1.2 Change these files

a/ (rename to .jar) unzip the omod-file (e.g.: prescription-1.0.0.omod)

> extracted folder: prescription-1.0.0.omod_FILES

b/ change the files

c/ produce the .omod (jar): 

> jar -cvfM prescription-1.0.0.omod -C prescription-1.0.0.omod_FILES/ . 

// jar -cvfM output -C input space dot


## 1.2 runtime.properties

The runtime.properties is created when the app is run the first time.

The provider (e.g. clinic) address is set on top of a prescription.

Add this 3 lines to runtime.properties:

> prescription_address_1=ABC hospital

> prescription_address_2=21 health street

> prescription_address_3=Mytown, Mycountry

Then to re-start the app.

==============================================
# 2 roles, access rights

## 2.1 privileges

> Task: prescription modify privilege

can add a medication, can group medications to print them in a prescription

idea: that is a doctor

=======

> Task: prescription view privilege

can see the 'download link' of a prescription (i.e. the pdf is created on the fly)

idea: that is a receptionist who has additionally this privilege

=======

## 2.2 example: add privileges to existing roles

in: Admin view - Users - manage roles

find:

> Role: Registration clerk

add privilege:

> Task: Prescription view privilege

add inherited roles:

> Application: Uses Patient Summary

(Uses Patient Summary: to see the patient dashboard)

-----------

find:

> Role: Doctor

add privileges:

> Task: prescription modify privilege

> Task: prescription view privilege

(needs provider)

-----------

## 2.3 example: add users with those privileges

in: Admin view - Users - manage users

add:

> User: Receptionist1, Password1

set

> Role: Registration clerk

add:

> provider

-----------
add:

> User: Doctor1, Password1

set 

> Role: Doctor

