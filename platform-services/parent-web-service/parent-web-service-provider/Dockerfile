FROM openjdk:8-jdk-alpine
ADD target/*.jar parent-web-service-provider.jar
ENTRYPOINT ["java","-jar","parent-web-service-provider.jar"]
EXPOSE 8054
