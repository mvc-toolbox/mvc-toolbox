# MVC Toolbox [![Build Status](https://travis-ci.org/chkal/mvc-toolbox.svg?branch=master)](https://travis-ci.org/chkal/mvc-toolbox)

## What is it?

MVC Toolbox is a small extension library for [MVC 1.0](https://java.net/projects/mvc-spec/pages/Home)
(a.k.a. [JSR 371](https://jcp.org/en/jsr/detail?id=371)). It provides various utilities
which can be helpful when creating MVC applications and are not covered by the specification.

## Installation

Add the following dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>de.chkal.mvc-toolbox</groupId>
  <artifactId>mvc-toolbox-core</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

You will also have to add the [Sonatype OSS Repository](https://oss.sonatype.org/)
to your `pom.xml` to get the snapshots:

```xml
<repositories>
  <repository>
    <id>sonatype-oss-snapshots</id>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    <releases>
      <enabled>false</enabled>
    </releases>
    <snapshots>
      <enabled>true</enabled>
    </snapshots>
  </repository>
</repositories>
```

## Documentation

### Message support

Most applications need to display some kind of *messages* to the user. JSF for example
solves this by providing the `FacesMessage` class and methods to queue them for rendering.

To use the MVC Toolbox messages support, you will typically inject `Messages` into your
controller and then use the various `add()` methods to queue messages for rendering.

```java
@Path("/foobar")
@Controller
public class MyController {

    @Inject
    private Messages messages;

    public String someMethod() {

        // Add a simple info message
        messages.add("Awesome! This works!");

        // You can create errors like this
        messages.add(Message.Severity.ERROR, "Something went wrong");

        // Or you can construct the Message instance yourself
        messages.add(
            new Message(Message.Severity.ERROR, "Something went wrong")
        );

        return "view.jsp";

    }

}
```

**NOTE:** Please note that the `Messages` class is `@RedirectScoped`. So you can
redirect the user to a new URL from your controller. The messages will be preserved
in this case and will be rendered on the new page.

You can access the `Messages` class in your view using the EL expression
`#{toolbox.messages}`. There are various `get*()` methods to retrieve all
messages or only those with a specific severity.

The simplest way to render all messages would be something like this:

```html
<c:if test="${not empty toolbox.messages.all}">
  <ul>
    <c:forEach var="message" items="${toolbox.messages.all}">
      <li>${mvc.encoders.html(message.text)}</li>
    </c:forEach>
  </ul>
</c:if>
```

**IMPORTANT:** Please note that you should always use `#{mvc.encoders.html(...)}`
to do proper HTML escaping.

In real world applications you will typically use different CSS styles depending
on the severity of the messages. If you are using [Bootstrap](https://getbootstrap.com/),
you could for example use something like this to display info messages:

```html
<c:if test="${not empty toolbox.messages.infos}">
  <div class="alert alert-success" role="alert">
    <ul class="list-unstyled">
      <c:forEach var="message" items="${toolbox.messages.infos}">
        <li>${mvc.encoders.html(message.text)}</li>
      </c:forEach>
    </ul>
  </div>
</c:if>
```

More to come...