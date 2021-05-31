# ipl-dashboard
Live Demo -> https://ipl-dashboard-of-amjad.herokuapp.com/

#Deployment on Heroku using CLI

1. mvn clean install
2. heroku login
3. heroku plugins:install java
3. heroku deploy:jar target/app.jar --app appName
