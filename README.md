# Problem

I defined a new configuration in root project called "bom" with a platform dependency.

````groovy
configurations {
    bom
}

dependencies {
    bom platform('org.springframework.boot:spring-boot-dependencies:2.1.2.RELEASE')
}

````

this shall be reused in the subproject 

````groovy
dependencies {
    implementation platform(project(path: ":", configuration: "bom"))

    implementation 'org.springframework.boot:spring-boot-test'
}
````

but then the build is broken when upgrading from 5.6.4 to 6.0 

````groovy
$ ./gradlew assemble

FAILURE: Build failed with an exception.

* Where:
Build file 'C:\Java\Sources\Temp\gradle-6.8-bom-showcase\lib1\build.gradle' line: 6

* What went wrong:
A problem occurred evaluating project ':lib1'.
> Cannot add attributes or capabilities on a dependency that specifies artifacts or configuration information

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 3s
````