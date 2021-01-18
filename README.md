# Problem


I've defined a new configuration in EjbModule called `bom` with a platform dependency.
This is used for convenience to not repeat the bom for every configuration that it should usually be used in.


````groovy
plugins {
    id 'java-library'
}

configurations {
    bom // convinience configuration to apply boms to more than one configuration
    implementation.extendsFrom bom
    ... // and various others
}

dependencies {
    bom platform('org.springframework.boot:spring-boot-dependencies:2.1.2.RELEASE')

    implementation 'org.apache.commons:commons-lang3' // version 3.8.1 selected by spring boot pom
}
````
while this works in the `EjbModule`, the resolution of the transitive dependency for the `EarModule` is broken if I try to configure it like this:
```groovy
plugins {
    id 'ear'
}
dependencies {
    deploy project(path:":EjbModule")
    earlib project(path:":EjbModule") // add transitive dependencies of the EjbModule too
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

The strange thing is, adding the `java` plugin to the `ear` project
```groovy
plugins {
    id 'ear'
    id 'java' // activating this will fix the resolution but why?
}
```
seems to solve the issue, but why?
Ultimately I'd like to not have to apply java to a ear project as java code does not make any sense in a packaging only project.