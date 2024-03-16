<h1 style ="text-align:center"><samp> IS1201, Design of Global Applications </samp></h1>
<h2><samp>Recruitment Application</samp></h2>
<h3><samp>⇁ Overview</samp></h3>

<p><samp>This project aims to develop a web-based recruitment application for an amusement park. The current system suffers from instability and unresolved bugs, leading to significant maintenance costs. As the original developers are no longer available, there's a need to rebuild a robust and scalable system that can efficiently handle the recruitment process.</samp></p>


<h3><samp>⇁ Background</samp></h3>

<p><samp>The amusement park is gearing up for the upcoming season and expects around 15,000 applications within a two-week period. The existing application, while functional, lacks stability and has unresolved bugs. Consultants have been brought in to fix these issues, but the company recognizes the need for a new, upgraded system that can accommodate future requirements.</samp></p>


<h3><samp>⇁ System Overview</samp></h3>

<p><samp>The recruitment system serves two main user roles: applicants and recruiters. Applicants can register and submit job applications, while recruiters manage and review these applications. The system is divided into two main parts: the registration of job applications and the administration of applications.</samp></p>
<h3><samp>⇁ Tools</samp></h3>

<p><samp>-Version control (Git)
-Project management (Maven)
-Intelij(recommeded)
-Cloud runtime (Heroku)</samp></p>

<h3><samp>⇁ Frameworks</samp></h3>

<p><samp>-Spring core technologies, in particular the IoC container
-Spring Boot
-Spring Web MVC
-Thymeleaf
-Spring Data (Commons and JPA)</samp></p>
<h4><samp>Registration of Applications</samp></h4>

<p><samp>Applicants provide personal information, a competence profile, and availability using a web browser. Future functionality includes multilingual support for the application form.</samp></p>

<h4><samp>Administration of Job Applications</samp></h4>

<p><samp>Recruiters, employees of the company, can sort, accept, reject, or leave applications unhandled. Future enhancements include a mobile app for recruiters and an intelligent application selection system.</samp></p>

<h4>Existing Database Model</h4>

<p><samp>While the current system has an undocumented database, the data must be transferred to the new system without loss. A SQL script is available to generate the existing database.</samp></p>

<h4>Additional Requirements</h4>

<p><samp>The company has specific requirements for the new system, including comprehensive logging, cross-browser compatibility, and a smooth handover process. The system should be live on the cloud and easily modifiable by the company's IT developers. Additionally, provisions must be made for future development of a mobile application.</samp></p>

<h3><samp>⇁ Use Cases</samp></h3>

<p><samp>The project includes detailed use cases for creating accounts, logging in, applying for positions, listing applications, and viewing application details.</samp></p>

<p><samp><b>Note:</b> The project prioritizes addressing the tasks outlined in a provided document. While additional functionality is desirable, the primary goal is to deliver a stable, functional system within the given timeframe.</samp></p>

<h3><samp>⇁ Running the App</samp></h3>
<h4><samp>Requirements</samp></h4>
<p><samp>A running database is required for the application to run properly. One simple way, also the implementation used by the group for testing database interactions and functionalities, is to run a postgres database locally.</samp></p>

<p><samp>When having a running database the follwing lines in</samp></p>

`application.properties`

<p><samp>for connecting to the database with a running app</samp></p>

`spring.datasource.url=jdbc:postgresql://{HOST NAME}:{PORT NUMBER}/{DATABASE NAME}`

`spring.datasource.username={USER NAME}`

`spring.datasource.password={PASSWORD}`


<h4><samp>Recommendations</samp></h4>
<p><samp>For running the app locally, IntelliJ Ultimate is recommended since development was solely made on this IDE. Run the application by clicking the green play button on IntelliJ</samp></p>
<p><samp><b>Note:</b> Make sure "HireMeApplication" is selected before running the app.</samp></p>

### OR

<p><samp>Navigate to the root folder in your terminal of the project and start the application with the command</samp></p>
mvn spring-boot:run
<p><samp>The application can now be reached at the url:</samp></p>
http://localhost:8080/login

<p><samp>For database management, IntelliJ Ultimate provides support for connection to a database locally (or remotely) and altering data for simple testing of data value dependent functionalities such as being an applicant or a recruiter. A local database is recommended since other hidden requirements might apply with a remote database.</samp></p>
