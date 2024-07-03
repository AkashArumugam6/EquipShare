# EquipShare

## Project Title
EquipShare: IT Hardware Lending Web Application for University

## Objective
EquipShare aims to develop a centralized IT hardware lending platform for the university. This platform will enable administrators to manage the inventory by adding, updating, and deleting hardware items, and handling the approval of lending requests. Students will be able to request, borrow, and return hardware online through the website.

## Features

### Administrator Console
- **Add/Delete/Update Hardware Items**: Administrators can manage the inventory by adding new hardware items, updating the count of existing items, and deleting items from the inventory.
- **Manage Inventory**: Keep track of all hardware items and their availability.
- **Approve Lending Requests**: Administrators have the authority to approve or deny lending requests made by students.

### Student Portal
- **Browse Hardware**: Students can browse the available hardware inventory.
- **View Technical Specifications**: Detailed specifications of each hardware item are available for students to review.
- **Submit Lending Requests**: Students can submit requests to borrow hardware.
- **Track Request Status and Lending History**: Students can track the status of their requests and view their lending history.

### Notifications
- Once a lending request is approved by the administrator, the student will receive an email notification with further directions.

## Technology Stack
- **Backend**: Spring Boot and Hibernate are used to build a robust backend, ensuring smooth operation and seamless database interaction.
- **Architecture**: The application embraces the MVC architecture and DAO pattern, promoting a structured development approach. This simplifies future enhancements and maintenance while prioritizing a seamless user experience.

## Getting Started

### Prerequisites
- Java JDK 11+
- Apache Maven
- MySQL 

### Installation

1. **Clone the Repository**
   ```sh
   git clone https://github.com/your-repo/equipshare.git
   cd equipshare
