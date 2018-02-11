#
# 参考 https://github.com/rastasheep/ubuntu-sshd/blob/master/16.04/Dockerfile
# 预装 ifconfig、ping、nano 等程序
#

FROM       ubuntu:16.04
MAINTAINER Aleksandar Diklic "https://github.com/rastasheep"

RUN apt-get update

RUN apt install -y net-tools nano iputils-ping

#
# ==== install and init SSH-Server ====
#
RUN apt-get install -y openssh-server
RUN mkdir /var/run/sshd

ENV PWD="root"
RUN echo 'root:root' |chpasswd

RUN sed -ri 's/^PermitRootLogin\s+.*/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config

#
# ==== install JDK8 ====
#
RUN apt install -y python-software-properties software-properties-common \
	&& add-apt-repository ppa:webupd8team/java -y \
    && echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections \
    && apt update && apt install -y --force-yes --no-install-recommends oracle-java8-installer \
    && apt clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

EXPOSE 22

CMD    ["/usr/sbin/sshd", "-D"]