FROM jenkins/jenkins:lts

USER root

# Встановлення JDK 23 (Adoptium Temurin)
RUN apt-get update && \
    apt-get install -y wget gnupg software-properties-common && \
    mkdir -p /usr/share/keyrings && \
    wget -O- https://packages.adoptium.net/artifactory/api/gpg/key/public | gpg --dearmor > /usr/share/keyrings/adoptium.gpg && \
    echo "deb [signed-by=/usr/share/keyrings/adoptium.gpg] https://packages.adoptium.net/artifactory/deb $(. /etc/os-release && echo "$VERSION_CODENAME") main" | tee /etc/apt/sources.list.d/adoptium.list && \
    apt-get update && \
    apt-get install -y temurin-23-jdk && \
    apt-get clean

ENV JAVA_HOME=/usr/lib/jvm/temurin-23-jdk
ENV PATH=$JAVA_HOME/bin:$PATH

# Maven + Allure
RUN apt-get install -y maven unzip && \
    wget https://github.com/allure-framework/allure2/releases/download/2.27.0/allure-2.27.0.tgz && \
    tar -xvzf allure-2.27.0.tgz && \
    mv allure-2.27.0 /opt/allure && \
    ln -s /opt/allure/bin/allure /usr/bin/allure && \
    rm allure-2.27.0.tgz

# Встановлення потрібних Jenkins плагінів
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

USER jenkins
