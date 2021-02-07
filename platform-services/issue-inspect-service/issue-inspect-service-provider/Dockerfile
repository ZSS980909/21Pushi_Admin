FROM openjdk:8-jdk-alpine
ADD target/*.jar issue-inspect-service-provider.jar
ENTRYPOINT ["java","-jar","issue-inspect-service-provider.jar"]
EXPOSE 8055