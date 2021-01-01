# FreshworkAssigment-
A file-based key-value data store that supports the basic CRD (create, read, and delete) operations. This data store is meant to be used as a local storage for one single process on one laptop. The data store can be exposed as a library to clients that can instantiate a class and work with the data store
Use json,gson maven dependency.
The sample response of  application given below
========================CREATE ==============================

Create operation successful
Operation failed due to key already available
Operation failed due to key already available
Create operation successful
Operation failed due to key length exceeded the limit(32chars)
====================AFTER WAIT===============
Create operation successful
Operation failed due to key already available
Operation failed due to key already available
Operation failed due to key already available

==========================READ===============================

{"firstName":"John","lastName":"Britto","address":"Chennai","age":"25"}
{"firstName":"John","lastName":"Britto","address":"Chennai"}
Operation failed due to key not available
Operation failed due to key length exceeded the limit(32chars)
====================AFTER WAIT===============
Operation failed due to key not available
{"firstName":"John","lastName":"Britto","address":"Chennai"}

========================DELETE ==============================

Operation failed due to key not available
Record deletion successful
Operation failed due to key not available
Operation failed due to key not available
Operation failed due to key length exceeded the limit(32chars).
