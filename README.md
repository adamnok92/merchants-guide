# Merchant

## Build

Prerequiments:
 - Java 17+
 - Gradle 7.6+

We have opporunity to build two different type of application. One single-jar application and one standard application.

### Build single-jar application


We have to run the command below.

```
gradle bootJar
```
After this command, we have to find the generated jar in the *build/libs* folder. We are able to run this application
with the next command:

```
java -jar merchant-1.0.jar
```

### Build standard application

We have to run the command below.

```
gradle production
```

This command will generate a *production* folder under *build* folder. This *production* folder contains each necessary
component that we need to run the application (expect java).

 - To run the application on Windows environment, we are able to run `merchant.bat` command from the console or `merchant.ps1` powershell script.
 - To run the application on Windows environment, we are able to run `merchant.sh`.
