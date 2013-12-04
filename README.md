game-profile
============

##Summary
This is a simple kitchen sink example showing a Spring MVC stack wired together.

Technologies in use are:
Spring MVC 4.0.0.RC1
Spring Java-based Configuration
Spring Security 3.2.0.RC2
Spring Security Java-based Configuration
JPA (Hibernate)
JSR 303 Bean Validation (Hibernate)
JSP / Tags / Taglib
HTML 5
CSS 3
Glassfish 4.0 (Embedded)
Groovy / JUnit / Mockito unit tests

##Versions
This was built and tested on Java 1.7.0_45-b18. At time of writing, it also builds and runs at source 1.6, but this is by no means guaranteed going forward.

##Installation
For the purposes of a demo project, this project is entirely embedded and requires no installation beyond Java and Maven.

##Run

The default perm gen size is slightly too small, and will memory error when Spring starts classloading. It's a small app, so 128m is fine.

###Linux:
MAVEN_OPTS="--XX:MaxPermSize=128m" mvn clean package embedded-glassfish:run

###Windows:
set MAVEN_OPTS=--XX:MaxPermSize=128m
mvn clean package embedded-glassfish:run

##Application
Once the Glassfish Server has started, there are three main pages in the application.

* https://localhost:9191/game-profile/test/profile
* https://localhost:9191/game-profile/test/login
* https://localhost:9191/game-profile/test/register

The application is also listening on http port 9090, but Spring Security will redirect those requests to https 9191.

###test/profile
This is a secured page, requiring the user to log in. Accessing this first will redirect to a login screen. This will display a list of the user's currently created characters. Characters are currently only created in the bootstrap for testing purposes.

###test/login
Allows a user to login. Currently, the user 'user' and the password 'password' are valid.

###test/register
Allows a new user to sign up to the system. Also demonstrates JSR303 bean validation if data is not filled in correctly. Will display an error message if trying to create an account with an existing username. Does not currently log the user in after registering.
