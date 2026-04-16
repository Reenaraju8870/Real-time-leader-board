# Real-time-leader-board
A basic leader-board system using java as the backend and also spring boot (maven) and redis (sorted set) for score insertion and rank retrieval.
The system utilizes Spring Boot as the core framework, leveraging Redis Cloud and its specialized Sorted Sets (ZSET) data structure to achieve O(log N) time complexity for both score insertions and rank retrieval. 
To ensure data integrity and security, JSON Web Tokens (JWT) are implemented for stateless authentication, while an H2 Relational Database manages persistent user profiles. 
Backend: Spring Boot Framework
Database: Redis Cloud & H2
Security: JSON Web Token (JWT)
The application follows a Client-Server Architecture utilizing a decoupled frontend and backend. The system is divided into three primary layers:
1.	Presentation Layer (Frontend): Developed using HTML5, CSS3, and Vanilla JavaScript. It communicates with the backend via asynchronous fetch() API calls.
2.	Application Layer (Backend): Built with Spring Boot 3.3. This layer handles business logic, security filtering, and routing.
3.	Data Layer (Persistence): * H2 Database: Used for persistent storage of user profiles and hashed passwords.
                           Redis Cloud: Used as a high-performance, in-memory cache specifically for the leaderboard sorted sets.
The system implements Stateless Authentication using JSON Web Tokens (JWT). This eliminates the need for server-side session storage (JSESSIONID), making the system horizontally scalable.
The JWT Lifecycle:
  Login Request -> Validation -> Token Generation -> Client Storage -> Authorization


