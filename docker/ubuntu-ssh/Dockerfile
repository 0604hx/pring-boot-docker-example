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

EXPOSE 22

CMD    ["/usr/sbin/sshd", "-D"]