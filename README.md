# Problem


i defined a new configuration in root project called "bom" with a platform dependency.

````groovy
configurations{
    bom
    implementation.extendsFrom bom
}

dependencies {

    bom platform('org.springframework.boot:spring-boot-dependencies:2.1.2.RELEASE')
    implementation 'org.springframework.boot:spring-boot-test'
}

````

i will reuse the bom configuration in subproject lib1 with this construct 

````groovy
configurations{
    bom
    implementation.extendsFrom bom
}
dependencies {
    bom platform(project(path:":", configuration:"bom"))
    implementation 'org.springframework.boot:spring-boot-test'
}
````

will be broken by upgrading from 5.6.4 to 6.0 

````groovy
* Where:
Build file 'C:\Java\DaimlerMRS\gradle-6.8-bom-showcase\lib1\build.gradle' line: 17

* What went wrong:
A problem occurred evaluating project ':lib1'.
> Cannot add attributes or capabilities on a dependency that specifies artifacts or configuration information

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

````