# primenumbers

Steps:
------

1. https://github.com/rzconsulting/primenumbers.git
Connect using the uri, either by EGit plugin in Eclipse or command line Git, create local repository.
(under Eclipse 'Git Repositories' view, choose 'clone a Git repository or right click & choose 'Paste Repository Path or URI' option)

2. Eclipse -> File -> Import Project... -> Maven -> Existing Maven Projects
Browse to where the local git repository was created in step 1, e.g. C:\git\primenumbers
(the Maven POM xml should be in this directory)

3. Similarly checkout the 'Server' configuration project and import as Eclipse project to workspace.

4. Under 'Command prompt' - browse to the directory where you have checked out the repository, e.g. C:\git\primenumbers
and run 'mvn clean install'. Alternatively you could run the command via Maven plugin in Eclipse IDE.
Right click on 'PrimeNumbers' project -> Maven -> Update project... -> 'Force update of Snapshots/Releases', refresh project in Eclipse

5. Under Project -> Properties -> Target Runtimes -> Make sure a web server is included, here i am using Apache Tomcat.

6. Under 'Java EE' perspective -> Servers tab -> Add and Remove -> Add and configure 'PrimeNumbers' project.

7. Somtimes starting and restarting (e.g. Clean... option) the web server seems required, till you see the below lines in the output log:

`INFO: Scanning for root resource and provider classes in the Web app resource paths:`
  `/WEB-INF/lib`
  `/WEB-INF/classes`
`Mar 21, 2016 9:23:52 PM com.sun.jersey.api.core.ScanningResourceConfig logClasses`
`INFO: Root resource classes found:`
  `class primenumbers.PrimeNumberService`

Naive Search [link](http://localhost:8080/PrimeNumbers/primenumberservice/naivesearch/1000)

Eratosthenes Sieve [link](http://localhost:8080/PrimeNumbers/primenumberservice/filterbysieve/10000)

