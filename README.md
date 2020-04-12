# springboot-webapp-sample

## Preface
This sample project uses [Spring Boot](http://spring.io/projects/spring-boot) and [Hibernate](http://hibernate.org/). It provides only Web API. So, I recommend using a [vuejs-webapp-sample](https://github.com/ybkuroki/vuejs-webapp-sample) project as Web UI.

## Install
Perform the following steps:

1. Download and install 'Eclipse Pleiades All in One'(Mars or later) in MargeDoc Project.
1. Clone this repository.
1. Select **File > Import** in menu bar.
1. Select **Gradle > 'Existing Gradle Project'** and click Next button.
1. Select this project in 'Project root directory' and click Finish button.

## Starting Server
Perform the following steps:

1. Right-click Application.java in ``src/main/java/managesys`` and select **Run As > Java Application**.
1. When startup is complete, the console shows 'Started Application'.
1. Access [http://localhost:8080/api/health](http://localhost:8080/api/health) in your browser and confirm that this application has started.
    ```
    {"status":"UP","details":{"diskSpace":{"status":"UP","details":{"total":253796282368,"free":202889191424,"threshold":10485760}},"db":{"status":"UP","details":{"database":"H2","hello":1}}}}
    ```
1. Log in with the following username and password.
    - username : ``test``
    - password : ``test``

## Test
Perform the following steps:

1. Select **verification > test**  in Gradle Tasks tab and right-click 'test'.
1. Click 'Run Gradle Tasks'.

## Build jar
Perform the following steps:

1. Select **build > bootJar**  in Gradle Tasks tab and right-click 'bootJar'.
1. Click 'Run Gradle Tasks'.

## Project Map
The follwing figure is the map of this sample project.

```
- src/main/java
  + common                  … Provide a common service of this system.
  + configration            … Define configurations such as database connections, security, and swagger.
  + controller              … Define controllers.
  + model                   … Define models.
  + report                  … Provide a service of report output.
  + repository              … Provide a service of database access.
  + security                … Provide a service of security authentication.
  + service                 … Provide a service of book management.
  - Application.java        … Entry Point.

- src/main/resources        … Define configurations by environments.

- src/test/java
  + configration            … Define configurations for unit tests.
  + managesys               … Define unit tests.
```

## Services
This sample provides 3 services: book management, account management, and master management.
Web APIs of this sample can be confirmed from [Swagger](http://localhost:8080/swagger-ui.html).

### Book Management
There are the following services in the book management.

|Service Name|HTTP Method|URL|Parameter|Summary|
|:---|:---:|:---|:---|:---|
|List Service|GET|``/api/book/list``|Page|Get a list of books.|
|Regist Service|POST|``/api/book/new``|Book|Regist a book data.|
|Edit Service|POST|``/api/book/edit``|Book|Edit a book data.|
|Delete Service|POST|``/api/book/delete``|Book|Delete a book data.|
|Search Title Service|GET|``/api/book/search``|Keyword, Page|Search a title with  the specified keyword.|
|Report Service|GET|``/api/book/allListPdfReport``|Nothing|Output a list of books to the PDF file.|

### Account Management
There are the following services in the Account management.

|Service Name|HTTP Method|URL|Parameter|Summary|
|:---|:---:|:---|:---|:---|
|Login Service|POST|``/api/account/login``|Session ID, User Name, Password|Session authentication with username and password.|
|Logout Service|POST|``/api/account/logout``|Session ID|Logout a user.|
|Login Status Check Service|GET|``/api/account/loginStatus``|Session ID|Check if the user is logged in.|
|Login Username Service|GET|``/api/account/loginAccount``|Session ID|Get the login user's username.|

### Master Management
There are the following services in the Master management.

|Service Name|HTTP Method|URL|Parameter|Summary|
|:---|:---:|:---|:---|:---|
|Category List Service|GET|``/api/master/category``|Nothing|Get a list of categories.|
|Format List Service|GET|``/api/master/format``|Nothing|Get a list of formats.|

## Libraries
This sample uses the following libraries.

|Library Name|Version|
|:---|:---:|
|spring-boot-starter-web|2.2.0|
|spring-boot-starter-security|2.2.0|
|spring-boot-starter-data-jpa|2.2.0|
|spring-boot-starter-actuator|2.2.0|
|spring-boot-starter-logging|2.2.0|
|spring-boot-starter-aop|2.2.0|
|spring-boot-starter-test|2.2.0|
|pdfbox|2.0.14|
|springfox-swagger2|2.7.0|
|h2|1.4.+|

## License
The License of this sample is *MIT License*.
