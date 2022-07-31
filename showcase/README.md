# MVC Toolbox - Showcase

This module provides a showcase containing the additional features `mvc-toolbox` provides. There are currently
three ways to run it.

## Run with WildFly Bootable Jar

To run the showcase on an application server, the easiest way to test `mvc-toolbox` is to use the `wildfly` Maven 
profile located inside the `showcase` module. It creates an runs a ready-to-use environment based on 
[WildFly Bootable Jar](https://docs.wildfly.org/bootablejar/) and serves the showcase on `localhost:8080`. 
To use this approach, please follow these three steps:

1. Run `mvn clean install` on project root to build all dependencies.
2. Run `mvn -pl showcase -Pwildfly package wildfly-jar:package wildfly-jar:run` on project root. 
It creates server and showroom artifact, deploys it and starts the WildFly instance.
3. Open `localhost:8080/` on your browser and enjoy the showroom.

## Run with Tomcat and Krazo Jersey

To run the showcase on a simple servlet container, the `tomcat` profile can be used. This profile downloads and
starts a Tomcat 10 instance where the `showcase`, built upon [Jersey](https://github.com/eclipse-ee4j/jersey) as
Jakarta REST implementation, gets deployed. To use this profile, please follow these three steps:

1. Run `mvn clean install` on project root to build all dependencies.
2. Run `mvn -pl showcase -Ptomcat package org.codehaus.cargo:cargo-maven3-plugin:run` on project root.
   It creates server and showroom artifact, deploys it and starts the Tomcat instance.
3. Open `localhost:8080/` on your browser and enjoy the showroom.

## Build and deploy on preferred container

At the moment there are only two "up-and-running" solution. But anyway, you can always build and deploy the showroom
manually to your preferred server. Please use one the following Maven commands depending on your server's Jakarta REST
implementation.

- GlassFish / Payara: `mvn -pl showcase -P jersey package`
- WildFly / (OpenLiberty): `mvn -pl showcase -P resteasy`