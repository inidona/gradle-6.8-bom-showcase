# Solution

Changing the parent project to a `java-platform` and using the `api` dependency to reference the external BOM:

````groovy
plugins { id 'java-plaform' }
javaPlatform { allowDependencies() }
dependencies {
    api platform('org.springframework.boot:spring-boot-dependencies:2.1.2.RELEASE')
}

````

this can be reused in the subproject:
````groovy
dependencies {
    implementation project(path: ":")

    implementation 'org.apache.commons:commons-lang3'
}
````

then the build is successful:
````
$ ./gradlew clean assemble --no-build-cache

BUILD SUCCESSFUL in 4s
4 actionable tasks: 4 executed
````

# links
Basics:
https://docs.gradle.org/current/userguide/platforms.html

very close UseCase:
https://docs.gradle.org/current/userguide/dependency_version_alignment.html
