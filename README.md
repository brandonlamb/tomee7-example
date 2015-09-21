# tomee7-example
Apache TomEE 7.0 Example (TomEE 2)

What is it?
===========

This is a sample / example project with a single controller with two
JAX-RS (REST) methods (GET and POST), single model, and single service (provider).

The version numbering is a bit wonky for the latest version of TomEE. The 1.7.2 of TomEE relates to
the Tomcat 7/Java EE 6. TomEE 2 is essentially now TomEE 7.0.0-SNAPSHOT. This is the version that
is updated with new Java 7 APIs.

Running
=======

Simply run `mvn clean package install tomee:run`

This should start up and listen on http://localhost:8080/example/examples/123.
