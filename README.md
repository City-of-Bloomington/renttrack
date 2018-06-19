# Renttrack

## Introduction

Renttrack is a web application to handle rental records and inspections by organization housing authority to grant rental permits to citizen landlord who offer rental housing.

The system keeps track of rental records including owners and agents (if any), addresses, buildings, units, rooms and other related info.  

To grant a rental permit for a landlord, the landlord provides the information needed for rental units, contacts, agent etc.

A cycle inspection will be performed by one or more of housing authority to verify that all the units included in the permit comply with city rental codes set by the housing city counsel.

The inspection records are added to the system and the inspection report is sent to the owner/agent to fix any violation to the city code and a next inspection is scheduled to verify all the required modification required by the inspection report.

Some configuration is needed for example the database url and type, uploaded file location, inspection file location and othe parameters that are needed. Take a look at application.properties.example and db.properties.example in the docs folder. Provide the needed info in these files and save them without the .example extention. The location of the these files when configured need to be specified in the AppConf.java file in the java conf/ folder. 

The database tables script is in docs/tables.sql file, you will need to create the database and the tables. 

You will need to add users to the users tables so that the system can be accessed.

## Requirements

Rentrack requires Tomcat version 8.0.47 or above.  If you are using Ubuntu, this version is only provided with Ubuntu 17.10 or above.


## Development

renttrack uses Maven to build a WAR version.

```bash
mvn clean package
```

## Deployment

We use Ansible to deploy a previously built WAR file.  Ansible looks for the WAR file: `/target/renttrack.war`.  If you are using Ansible to deploy, your inventory will need to set all the variables in `/ansible/group_vars/all.yml`.


```bash
mvn clean package
cd ansible
ansible-playbook deploy.yml -i /path/to/inventory
```
# renttrack
