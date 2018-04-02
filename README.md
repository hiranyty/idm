# idm
User Registration Application

1) Download the project from repository. 

     git clone https://github.com/hiranyty/idm.git

2) Create the .jar file of the project.

     Run mvn -Dhost=localhost package

     host - IP address of the mongo database server.
  
3) Create docker image from .jar file.

    docker build -f Dockerfile -t idm-service


4) Run the container on created image from jar file. 

    docker run -p 8080:8080 e host=192.168.99.100 idm-service
    
    host - IP address of the mongo database server

5) Get all the rest end points.

    http://docker_ip_address:8080/swagger-ui.html#/ 

Note : 
 
 ADMIN user will be created when Spring boot application startup.
