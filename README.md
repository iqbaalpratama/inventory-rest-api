# Product Inventory REST API Project

This project is REST API that using springboot 3.2.1 and use in-memory h2 database.

The following is a sequence of commands to run this program locally:
 - Download `inventory-1.0.0-SNAPSHOT.jar`  file from the target directory and 
 - Run the program using the command:  `java -j inventory-1.0.0-SNAPSHOT.jar` or filename of jar depends on your filename after download the file. Run it from cmd/powershell, wait until program run successfully.
 - After the program successfully running, you can access this documentation and try to access the resource: [Product Inventory Documentation](https://documenter.getpostman.com/view/14219981/2s9YsM9W8F)
- To see in-memory database schema and executing queries to check the data, open `http://localhost:8081/h2-console/` in web browser and connect with this settings: 
`JDBC URL: jdbc:h2:mem:testdb`
`User name: sa`
`Password: password`
- Default account for: 
    - Admin user:
        `email: admin123@gmail.com`
        `password: admin123`
    - Customer user:
        `email: customer123@gmail.com`
        `password: customer123`
- For the basic test and implementation test - backend(2) / query test, the file is located in basic-test folder

