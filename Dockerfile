#import ubuntu 
FROM ubuntu

#install dependecies
RUN apt-get update
RUN apt-get -y install nginx

#config nginxs
COPY default.conf /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/nginx.conf
COPY e-pharmacy-frontend/dist /usr/share/nginx/html


#ENABLE WHEN BACKEND IS READY

#install dependencies
#RUN apt-get -y install openjdk-17-jre
#RUN apt-get -y install postgresql

#get run file
COPY run.sh .
RUN chmod +x run.sh

#run api
#ARG JAR_FILE=e-pharmacy-backend/target/*.jar
#ADD ${JAR_FILE} app.jar
#RUN java -jar app.jar
ENTRYPOINT ["./run.sh"]