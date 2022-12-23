FROM openjdk:8
EXPOSE 9922
ADD target/api-transaction.jar api-transaction.jar
ENTRYPOINT ["java","-jar","/transactionapi/api-transaction.jar"]