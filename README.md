# Halal Pairing Java Application

## Overview

Halal Pairing is a Java application that facilitates matchmaking and communication between users. The application provides features such as user profiles, event organization, real-time messaging, and more.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Database Setup](#database-setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

1. **User Management:**
   - Create and manage user profiles with personal information.
   - Handle user authentication and authorization.

2. **Matchmaking:**
   - Algorithm-based matchmaking to suggest potential matches.
   - Keep track of user matches and their similarity indices.

3. **Events:**
   - Organize and manage halal events.
   - Specify event details such as title, description, venue, and date.

4. **Real-Time Messaging:**
   - Enable real-time communication between users using a simple chatbox.

5. **Payment Processing:**
   - Implement payment features with multiple methods (Credit Card, Microfinance).

6. **Feedback and Complains:**
   - Allow users to provide feedback and file complaints.

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- Java SDK (version x.x.x)
- MySQL Database
- IDE (Eclipse, IntelliJ, etc.)
- Provide absolute paths for the following:
  - Images (update paths in `ImageLoader.java`).
  - Database connection settings (update username/password in `DatabaseHandler.java`).
  - Chat folder (update path in the chat-related code).

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Moeez-Muslim/Halal-Pairing.git
   ```
   
2. Open the project in your preferred IDE.

3. Configure database connection settings in the application.

## Database Setup

1. Create a MySQL database named `HALAL_PAIRING`.

2. Execute the SQL script provided in the `database` folder to set up tables.

## Usage

1. Run the application.

2. Register as a new user or log in with existing credentials.

3. Explore different features such as matchmaking, event organization, messaging, and more.

## Contributing

Contributions are welcome! Please follow the [Contributing Guidelines](CONTRIBUTING.md).

## License

This project is licensed under the [MIT License](LICENSE).
