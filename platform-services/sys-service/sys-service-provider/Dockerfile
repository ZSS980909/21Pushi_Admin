FROM openjdk:8-jdk-alpine
ADD target/*.jar sys-service-provider.jar
ENTRYPOINT ["java","-jar","sys-service-provider.jar"]
EXPOSE 8052