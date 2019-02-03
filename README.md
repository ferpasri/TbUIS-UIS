# TbUIS-UIS

Defect free version UIS

## Building an application

To build a UIS-web application using Maven to WAR file that can be deployed on a web server with a servlet container (eg Tomcat), you can use the following Maven command in the root folder UIS-web project with POM file:

```
mvn clean install -DskipTests
```

After a successful build, the resulting WAR file will be located in the _target_ folder. The goal **-DskipTests** allows skipping tests that could end the assembly process when using beans with a deliberate error.

## Deployment of application
#### If you already have WAR file, you can continue with setting up a database - part: 2. a)
### 1. a) Running an application using the Maven plugin
The UIS-web application can be started using the Maven tomcat7 plugin, which allows manipulation of WAR projects within the built-in servlet container Tomcat version 7.x. To run a new application using the WAR file with clean assembly the following command can be used: 

```
mvn clean install -DskipTests tomcat7 :run -war
```

The goal _-DskipTests_ defines a test skip that could end the process assembly with intentionally inserted error version of bean.

### 1. b) Deploying UIS-Web on Tomcat server

The assembled WAR application can be deployed on a web server Servlet with a container such as Tomcat. War file is on Tomcat server can be deployed in two ways. The first is to insert the created WAR file to a folder with other web applications and run the server (or restart). Tomcat browses the folder and any new or updated web WAR archives unpack and access the web application. The second option is so-called. _Hot Deployment_, which allows you to deploy a separate web application at runtime server. On Tomcat, you can use _Hot Deployment_ using _Tomcat Manager_.

### 2. a) Setting up a database

You must create a new database before running the UIS-Web application for the first time, which application will use for persistence of data. To use this database UIS-web applications also need to set the user, password, database url (there are also other attributes) for the JDBC database connector in the file (path in WAR file) _WEB-INF/classes/META-INF/persistence.xml_.

Basic database settings:
- Name of database: 	**uis-web-db**
- Encoding: 	**UTF8_general_ci**
- Username: 	**uis-web**
- Password: 	**uis**

