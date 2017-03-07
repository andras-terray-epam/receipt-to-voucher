# Receipt To Voucher

RESTful service to let customers upload their receipts which are processed and scored with points based on their content.
The points can be changed to vouchers in the future.

## Getting Started

If you intended to run the project locally just clone it and run "gradlew clean run" in the root repository.
It will run a jetty server and wait for incoming request on port 9000. Example: localhost:9000/hello-world?name=Foo
Admin UI can be reached from browser on port 9000. Example: localhost:9001

### Prerequisites

To start the project you do not need any prerequisites as the built in gradle wrapper (gradlew) will download them at first run.


### Installing

Clone and call "gradlew clean run" in the root repository.

## Running the tests

Under development

### Break down into end to end tests

Available endpoints:

Hello World test endpoint

```
localhost:9000/hello-world?name=Foo
```

## Deployment

Under development

## Built With
Versions can be found in build.gradle

* [Dropwizard](http://www.dropwizard.io/1.0.6/docs/getting-started.html) - The web framework used
* [Gradle](https://docs.gradle.org/3.1/release-notes.html) - Build automation tool
* [Guice](https://github.com/google/guice) - Dependency injecion framework.

## Versioning

Under development

## Authors

* **Andras Terray** - *Initial work*

## License

-
