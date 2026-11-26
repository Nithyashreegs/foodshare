# SharePlate - Food Surplus Sharing System
Java Servlets + JSP + JSTL + JDBC + MySQL (MVC), Tomcat 9.

## Setup
1. Run `sql/schema.sql` in MySQL (Workbench or `mysql -u root -p < sql/schema.sql`).
2. Edit USER / PASSWORD in `src/main/java/com/foodshare/dao/DBConnection.java`.
3. Open the project as a Maven project (Eclipse: File > Import > Existing Maven Projects; IntelliJ: Open pom.xml).
4. Add Apache Tomcat 9 and run on server. Open http://localhost:8080/foodshare/
   (use Tomcat 9, not 10: this code uses `javax.servlet`).

## Demo flow
1. Register a DONOR and a RECEIVER (use two browsers or one normal + one private window).
2. Donor: Post surplus food.
3. Receiver: Request this food.
4. Donor: Accept, then Mark picked up.
