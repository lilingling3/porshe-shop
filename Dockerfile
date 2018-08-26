FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
RUN mkdir -p /data-project/font
ADD PorscheNextTT-Regular.ttf /data-project/font/PorscheNextTT-Regular.ttf
ADD simhei.ttf /data-project/font/simhei.ttf
RUN chmod 777 -R /data-project/font
ADD porsche-*.jar app.jar
# RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENV PARAMS="-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod"
ENV CONFIG=""
ENV VARS=""
ENV TZ="Asia/Shanghai"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS $PARAMS $VARS -Duser.timezone=GMT+08 -jar /app.jar $CONFIG" ]