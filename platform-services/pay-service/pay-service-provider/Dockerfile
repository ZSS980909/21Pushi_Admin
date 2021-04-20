FROM openjdk:8-jdk-alpine
ADD target/*.jar pay-service-provider.jar
ENTRYPOINT ["java","-jar","pay-service-provider.jar"]
COPY ./src/main/resources/static/parent/alipayCertPublicKey_RSA2.crt alipayCertPublicKey_RSA2.crt
COPY ./src/main/resources/static/parent/alipayRootCert.crt alipayRootCert.crt
COPY ./src/main/resources/static/parent/appCertPublicKey.crt appCertPublicKey.crt

COPY ./src/main/resources/static/student/alipayCertPublicKey_RSA21.crt  alipayCertPublicKey_RSA21.crt
COPY ./src/main/resources/static/student/alipayRootCert1.crt alipayRootCert1.crt
COPY ./src/main/resources/static/student/appCertPublicKey1.crt appCertPublicKey1.crt

EXPOSE 80