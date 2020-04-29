![Java CI with Maven](https://github.com/Brest-Java-Course-2020/skononovich-warehouse-management/workflows/Java%20CI%20with%20Maven/badge.svg)
# skononovich-warehouse-management
Warehouse management demo application.

### Prerequisites

        install jdk8

        install maven3+
        
        install tomcat

        install git

Apache Maven 3.6.3, Apache Tomcat, java 1.8, and local git required for build,
you can get it at:

        https://www.oracle.com

        http://maven.apache.org

        https://tomcat.apache.org
        
#### Check environment configuration

        $ java -version

        $ mvn -version        

### Installing
Choose directory for project, download project from github:
 
       $ git clone https://github.com/Brest-Java-Course-2020/skononovich-warehouse-management.git

#### Build project
Run terminal command in project directory:

        $ mvn clean install

#### Preparing reports

        $ mvn site

        $ mvn site:site        

check for reports: 
        
        ../<project>/target/site/index.html

#### Use tomcat server for WEB RESTful application test
   After project was build you can go to:
   
        http://localhost:8080/manager/html/
        
   and choose .war files for deploy web-app, rest-app in deploy section. 
   
   Another way to do this for web-app go to: 
        
        ../<project>/web-app/target/
        
   and copy "warehouse.war" to tomcat /webapps directory
        
        ../tomcat/webapps/
        
   for rest-app go to:
          
        ../<project>/rest/target/
          
   and copy "rest-warehouse-management.war" to tomcat /webapps directory
          
        ../tomcat/webapps/
        
   the web application should be available at:
        
         http://localhost:8080/warehouse/
         
   if you wanna shutdown or stop tomcat server go to:
   
        http://localhost:8080/manager/html/
   
   choose app click to "undeploy" for shutdown and removing, or click to "stop" for stopping app.
   Also you can remove rest-warehouse-management.war, warehouse.war files from:
        
        ../tomcat/webapps/
        
   for shutdown and removing this apps from tomcat server.

#### Use embedded jetty server for REST application test
   Run terminal command in project directory:

        $ mvn -pl warehouse-management-rest/ jetty:run

        Once started, the REST server should be available at:

        http://localhost:8088        

Try CURL:
- Product Controller

        Get all products:
        $ curl -X GET "http://localhost:8088/products" | json_pp
        
        Create product:
        curl -X POST "http://localhost:8088/products" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"productId\": 1, \"productName\": \"ProductName\"}"
        
        Update product:
        curl -X PUT "http://localhost:8088/products" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"productId\": 1, \"productName\": \"newProductName\"}"
        
        Find product by id:
        curl -X GET "http://localhost:8088/products/1" -H "accept: */*"
        
        Delete product:
        curl -X DELETE "http://localhost:8088/products/1" -H "accept: application/json"

- ProductDto Controller
        
        Get all products with product quantity:
        curl -X GET "http://localhost:8088/products_dtos" -H "accept: */*" | json_pp
        
- ProductRecord Controller
        
        Create ProductRecord:
        curl -X POST "http://localhost:8088/records" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"dealType\": \"GETTING\", \"productId\": 1, \"productRecordDate\": \"2020-04-28T23:52:10.722Z\", \"productRecordId\": 0, \"quantityOfProduct\": 10}"
        
- ProductRecord Dto Controller
        
        Get all product records with product name:
        curl -X GET "http://localhost:8088/records_dtos" -H "accept: */*" | json_pp
        
        Get all product records with product name in date interval:
        curl -s -X GET 'http://localhost:8088/records_dtos?startDate=2019-01-01&endDate=2020-01-01' | json_pp
        
#### Use embedded jetty server for WEB RESTful application test
You need to run these commands in different tabs or terminal windows:
        
        $ mvn -pl warehouse-management-rest/ jetty:run

        $ mvn -pl warehouse-management-web-app/ jetty:run

   Once started, the application should be available at:

        http://localhost:8090

   if you wanna shutdown jetty server, go to terminal tab or window
   you wanna stop and press "CTRL+C".