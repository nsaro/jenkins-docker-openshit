# Springboot, Jenkins and Openshift Skills
This project demonstrate deplying a simple Springboot Hello World application on Openshift cluster using Jenkins pipeline.

Prerequisite

- Local Openshift Installation : https://www.youtube.com/watch?v=3W4sGFHRgAQ&t=209s
- Local Jenkins Installation : https://www.jenkins.io/doc/book/installing/macos/

In jenkins dashboard, create a new item of type **Pipeline**.

![](img/Screenshot 2022-05-12 at 12.43.14 PM.png)

In pipeline configuration window, update the section in Pipeline tab as below. Dont forget to add Github credentials for smooth access to Github repository

![](img/Screenshot 2022-05-12 at 4.16.57 PM.png)

The pipe job configuration is picked from the Jenkinsfile of the project. Save the project

![](img/Screenshot 2022-05-12 at 4.18.29 PM.png)

- Make sure, your openshift code ready container is working and logged in as administrator.
Click on Build Now.

![](img/Screenshot 2022-05-12 at 4.21.44 PM.png)

After the pipeline success, you should see the below entry in Routes page of Openshift instance

![](img/Screenshot 2022-05-12 at 4.24.49 PM.png)

You should be able to see the application running on
http://route-service-spring-hello-world-default.apps-crc.testing/
