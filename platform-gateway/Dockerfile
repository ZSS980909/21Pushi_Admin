FROM openjdk:8-jdk-alpine
ADD target/*.jar platform-gateway.jar
ENTRYPOINT ["java","-jar","platform-gateway.jar"]
EXPOSE 8888
RUN echo "Asia/Shanghai" > /etc/timezone