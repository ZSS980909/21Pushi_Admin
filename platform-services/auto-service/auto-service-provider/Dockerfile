FROM openjdk:8-jdk-alpine
ADD target/*.jar auto-service-provider.jar
ENTRYPOINT ["java","-jar","auto-service-provider.jar"]
EXPOSE 8051