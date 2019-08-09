# Account Manager is a demo application which allows an user to create a savings account.

# How to start it?
1) Open a cmd nd navigate to the root of the prooject
2) Run "./gradlew bootRun" - this will build the application with Gradle (Wrapper) and then will start a Tomcat instance running on port 8080, by default


# How to use it?
1) Navigate in your browser to "http://localhost:8080". Use "user" and "password" as credentials to login
2) Add a demo savings account. After the account is added, try to add one more - you will get an error saying that only one account can be added
3) By default validation of allowing the account to be opened only during business hours is deactivated. To activate it, use app.allow-non-business-hours property to flip it
