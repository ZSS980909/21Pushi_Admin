FROM openjdk:8-jdk-alpine
ADD target/*.jar student-app-service-provider.jar
ENTRYPOINT ["java","-jar","student-app-service-provider.jar"]
EXPOSE 8053