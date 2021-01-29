FROM 10.60.105.39:8297/openjdk:11.0.1
ADD target/demo-1.0-SNAPSHOT.jar application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=GMT+7","-jar","/application.jar"]