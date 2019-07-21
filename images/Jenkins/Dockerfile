FROM jenkins/jenkins:lts

USER root

# Configure Jenkins
COPY files/00-csrf.groovy /var/jenkins_home/init.groovy.d/00-csrf.groovy
COPY files/01-enable-agent-master-access-control.groovy /var/jenkins_home/init.groovy.d/01-enable-agent-master-access-control.groovy
COPY files/02-basic-security.groovy /var/jenkins_home/init.groovy.d/02-basic-security.groovy

# Install Jenkins plugins
COPY files/plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/plugins.txt

# Install Docker CLI
RUN apt-get update && \
    apt-get -y install apt-transport-https \
      ca-certificates \
      curl \
      gnupg2 \
      software-properties-common && \
    curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
    add-apt-repository \
      "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
      $(lsb_release -cs) \
      stable" && \
    apt-get update && \
    apt-get -y install docker-ce-cli

RUN groupadd -g 999 docker && usermod -aG docker jenkins

# USER jenkins
