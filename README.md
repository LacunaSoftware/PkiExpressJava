PKI Express package for Java
====================================
**Standard version for Java 7 and greater**

This package contains classes that encapsulate the calls to the PKI Express.

The **PKI Express package** is distributed by [Bintray](https://bintray.com/lacunasoftware/maven/pki-express).

The recommended way to install it is with Gradle:
    
    repositories {
        mavenCentral()
        maven {
            url  "http://dl.bintray.com/lacunasoftware/maven"
        }
    }
    
    dependencies {
        compile("com.lacunasoftware.pkiexpress:pki-express:1.16.0")
        ...
    }
        
Or with Maven:
         
    <dependency>
      <groupId>com.lacunasoftware.pkiexpress</groupId>
      <artifactId>pki-express</artifactId>
      <version>1.16.0/version>
      <type>pom</type>
    </dependency>
      
    
Samples
-------

Please visit the [PKI Express samples repository](https://github.com/LacunaSoftware/PkiExpressSamples/tree/master/Java)
for examples on how to use this package.
