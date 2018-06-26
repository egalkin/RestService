# RestService
Rest Service.
# Build with Maven:
mvnw clean package.
# CONTACT_IDs presented in DataBase from start:
25, 26, 46, 99.
# Supported commands:
## GET: 
/contacts - returns list of Contacts presented in DB. \
/contacts/{contactId} - returns last contacts application if presented. \
/contacts/{contactId}/applications - returns Set of contacts applications presented in DB. \
/contacts/{contactId}/applications/{applicationId} - returns applications with given id if presented.
## POST:
/contacts - post Contact. \
/contacts/{contactId}/applications - post Applications.
## PUT: 
/contacts/{contactId}/applications/{applicationId} - updates Application with given ID. 
## DELETE:
/contacts/{contactId} - delete Contact with given ID. \
/contacts/{contactId}/applications/{applicationId} - delete Application with given ID. 
# Contact POST body:
{"CONTACT_ID" : {id}}
# Application POST body:
{"APPLICATION_ID" : {id}, "PRODUCT_NAME" : {name}}
