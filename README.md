# Problem


I've used a BOM (platform dependency) to define the version of a projects dependency:

````groovy
plugins {
    id 'java-library'
}

dependencies {
    implementation platform('org.springframework.boot:spring-boot-dependencies:2.1.2.RELEASE')

    implementation 'org.apache.commons:commons-lang3' // version 3.8.1 selected by spring boot pom
}
````
while this works for the `EjbModule`, the resolution of the transitive dependency in the `EarModule` is broken if I try to configure it like this:
```groovy
plugins {
    id 'ear'
}
dependencies {
    deploy project(path:":EjbModule")
    earlib project(path:":EjbModule") // add transitive dependencies of the EjbModule to the lib folder
}
```
adding the BOM again to any of the Ear Plugins configurations does not fix the following error:

````groovy
$ ./gradlew assemble
> Task :EarModule:ear FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':EarModule:ear'.
> Could not resolve all files for configuration ':EarModule:earlib'.
   > Could not find org.apache.commons:commons-lang3:.
     Required by:
         project :EarModule > project :EjbModule

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org
````

The strange thing is, adding the `java` plugin to the `ear` project:
```groovy
plugins {
    id 'ear'
    id 'java' // activating this will fix the resolution but why?
}
```
seems to solve the issue, but why?
Ultimately I'd like to not have to apply java to an ear project as java code does not make any sense in a packaging only project.

this still worked in gradle 6.4.1
```
./gradlew clean assemble --no-build-cache
Downloading https://services.gradle.org/distributions/gradle-6.4.1-bin.zip
.........10%..........20%..........30%..........40%.........50%..........60%..........70%..........80%.........90%..........100%

Welcome to Gradle 6.4.1!

Here are the highlights of this release:
 - Support for building, testing and running Java Modules
 - Precompiled script plugins for Groovy DSL
 - Single dependency lock file per project

For more details see https://docs.gradle.org/6.4.1/release-notes.html

Starting a Gradle Daemon (subsequent builds will be faster)

BUILD SUCCESSFUL in 35s
5 actionable tasks: 5 executed
```