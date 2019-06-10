cd app-client

mvn clean

mvn install

cd target

clear

java -jar app-client-1.0.jar 127.0.0.1 5656 "Mensagem do cliente"