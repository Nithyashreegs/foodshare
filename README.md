# 🍽️ SharePlate – Food Surplus Sharing System

## 📌 Project Overview

SharePlate is a web-based Food Surplus Sharing System developed to reduce food wastage by connecting food donors with people who need food.

Restaurants, hotels, event organizers, and other food providers can share information about surplus food through the platform. Receivers can view available food and request it.

The main aim of this project is to reduce food waste, promote food redistribution, and help people access surplus food.

## 🎯 Objectives

- To reduce food wastage by redistributing surplus food.
- To connect food donors with people in need.
- To provide an easy-to-use online food-sharing platform.
- To encourage social responsibility and community participation.
- To improve the management of surplus food donations.

## ✨ Key Features

- **User Registration and Login:** Users can register and securely log in to the application.
- **Donor Dashboard:** Donors can access their dashboard and manage food donations.
- **Food Donation Management:** Donors can provide details about available surplus food.
- **Food Listing:** Users can view available food donations.
- **Food Request Management:** Receivers can request available food.
- **Database Management:** User and food donation information is stored in a MySQL database.
- **Logout:** Users can securely log out of the application.

## 🛠️ Technologies Used

### Frontend
- HTML5
- CSS3
- JavaScript
- JSP (JavaServer Pages)

### Backend
- Java
- Java Servlets
- JDBC
- Apache Maven

### Database
- MySQL

### Development Tools
- Eclipse IDE
- Apache Tomcat Server
- MySQL Workbench / phpMyAdmin
- Git and GitHub

## 📂 Project Structure

```text
foodshare/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── (Java Servlet and backend files)
│       │
│       └── webapp/
│           ├── (JSP files)
│           ├── css/
│           ├── js/
│           └── WEB-INF/
│
├── sql/
│   └── (Database SQL files)
│
├── pom.xml
├── README.md
├── .project
└── .classpath
```

## ⚙️ Installation and Setup

### Prerequisites

Make sure the following software is installed:

- Java JDK
- Eclipse IDE
- Apache Tomcat Server
- MySQL Server
- Apache Maven

### Step 1: Clone the Repository

```bash
git clone https://github.com/Nithyashreegs/foodshare.git
```

### Step 2: Open the Project in Eclipse

1. Open Eclipse IDE.
2. Select File → Import.
3. Choose Existing Maven Projects.
4. Browse the downloaded foodshare folder.
5. Select the project and click Finish.

### Step 3: Configure the Database

1. Start MySQL Server.
2. Open your MySQL database management tool.
3. Import the SQL file available in the `sql` folder.
4. Configure the database connection details in the project.

### Step 4: Update Maven Dependencies

Right-click the project.

Select:

Maven → Update Project → Finish.

### Step 5: Configure Tomcat Server

1. Open the Servers tab in Eclipse.
2. Add Apache Tomcat Server.
3. Right-click the project.
4. Select Run As → Run on Server.
5. Select Tomcat Server and click Finish.

### Step 6: Access the Application

Open your browser and visit:

```text
http://localhost:8080/foodshare/
```

## 🌍 Problem Statement

Food wastage is a major social problem. Large quantities of edible food are wasted by restaurants, hotels, and event organizers, while many people struggle to access sufficient food.

SharePlate provides a digital platform to connect food donors and receivers, making surplus food redistribution easier and more organized.

## 🔮 Future Enhancements

- Location-based food donation search.
- Email and SMS notifications.
- Food expiry tracking.
- Online food donation tracking.
- Mobile application integration.
- Admin dashboard for better management.

## 🎓 Project Details

**Project Name:** SharePlate – Food Surplus Sharing System

**Project Type:** Full Stack Web Application

**Domain:** Web Development / Food Management

**Development Environment:** Eclipse IDE

**Backend:** Java, JSP, Servlets

**Database:** MySQL

**Server:** Apache Tomcat

**Build Tool:** Maven

## 👩‍💻 Developed By

**Nithyashree GS**

GitHub: https://github.com/Nithyashreegs

## 📄 License

This project is developed for educational purposes as a mini project.
