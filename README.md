# INTERNATIONAL PHONE NUMBER LOOKUP

## DESCRIPTION
The purpose of this project is to provide an application for reading customer phone numbers and extracting information about those phone numbers.
This information includes:

- the country code
- the country
- the state of the phone number(whether VALID or INVALID)

The data returned is split into pages (10 phone numbers per page by default) and can
be further filtered by:

- country code
- the state of the phone number(whether VALID or INVALID)
- phone number (this works using a 'contains' check not an equal to check. Eg phone number 55657338 contains 73)

The code works by reading country data from a file called <b>"countries (BE).json"</b> and using
the data to create a spring bean. Everytime the search function is called, the bean is fed phone numbers from the database
and returns the necessary data(country code, country name, phone number state/validity and phone number).

The file <b>"countries (BE).json"</b> can be updated at any time to add more countries, delete countries or update countries.
<u>If this ever happens, please also update <b>"countries (FE).json"</b> found in src/main/resources/static/assets/files.</u>
That file is used by the frontend and both countries files are meant to be identical (aside from their names).

The frontend was done using <b>Angular</b> and the backend using <b>Spring boot</b>.
The database is <b>MySQLite</b>.

## INSTALLATION
There is no need to install anything. The frontend has already been built and is bundled in the project.
The frontend files are located in src/main/resources/static directory.
