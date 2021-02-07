FROM openjdk:8-jdk-alpine
ADD target/*.jar pay-service-provider.jar
ENTRYPOINT ["java","-jar","pay-service-provider.jar"]
EXPOSE 80