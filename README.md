# Problem


i defined a new configuration in WebshereEAR project called "bom" with a platform dependency.

````groovy

configurations{
    bom
    implementation.extendsFrom bom
    earlib.extendsFrom bom
}
dependencies {
    bom platform('org.springframework.boot:spring-boot-dependencies:2.1.2.RELEASE')
    //earlib 'org.junit.jupiter:junit-jupiter-api:5.6.0'
    //deploy 'org.junit.jupiter:junit-jupiter-engine:5.6.0'
    deploy project(path:":lib1")
    earlib project(path:":lib1")
    earlib 'org.springframework.boot:spring-boot-test'
}


}

````

i will use the versions defined in bom file but they can not be resolved

````groovy
Execution failed for task ':WebsphereEAR:ear'.
> Could not resolve all files for configuration ':WebsphereEAR:earlib'.
> Could not find org.springframework.boot:spring-boot-test:.
Required by:
project :WebsphereEAR
project :WebsphereEAR > project :lib1

Possible solution:
- Declare repository providing the artifact, see the documentation at https://docs.gradle.org/current/userguide/declaring_repositories.html
}
````
if i also apply the java plugin to the EAR project it works as excepted, but why ?