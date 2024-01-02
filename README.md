# Music Application Backend

Welcome to the backend repository of our Music Application, powered by Spring Boot, MySQL, Redis, and integrated with Amazon S3 for cloud storage. This backend serves as the backbone of our music application, providing essential features such as user management, song addition, user ratings, and feedback.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Features](#features)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)
- [API Testing](#api-testing)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- **Spring Boot:** A powerful and flexible framework for building Java-based applications, facilitating rapid development and easy integration.

- **MySQL:** A relational database used to store and manage user data, song metadata, and application information.

- **Redis:** In-memory caching system employed to improve performance by reducing database queries.

- **Amazon S3:** Cloud storage service utilized to store media files, such as songs, album art, and user-generated content.

## Features

1. **User Management:**
   - Add new users with different roles (user, admin).
   - Secure authentication and authorization mechanisms.

2. **Song Management:**
   - Add new songs with associated metadata.
   - Rate and provide feedback for each song.

3. **Cloud Storage Integration:**
   - Seamless integration with Amazon S3 for storing and retrieving media files.

## API Endpoints

- **User Management:**
  - `POST /localhost:8080/v1/signup/user/` - Add a new user.
  - `POST /localhost:8080/v1/signup/admin/` - Add a new admin.

- **Song Management:**
  - `POST localhost:8080/v1/songs/addition/` - Add a new song.
  - `POST /api/songs/{id}/ratings` - Add a rating for a specific song.
  - `POST /api/songs/{id}/feedback` - Provide feedback for a specific song.

## Getting Started

1. Clone the repository:
   ```bash
    https://github.com/jyotiram7402/MusicApplication.git
1. Reach out to me:
   ```bash
   jyotiramkamble7402@gmail.com
