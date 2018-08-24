# spring-amqp
This is solution for worker/job processing(with message listener) queue using spring amqp - rabbitmq 
Simple Example for Producer/Consumer using RabbitMQ - Spring AMQP.
2 Projects are added in the repository.
1. Producer - produces 1000 message - serialized Job object
2. Consumer - consumes all the serialized Job object and process it suing Message listeners with Manual ack.

Pre-quisite:
1. Installation of RabbitMQ server in 2 nodes in clustered mode with HA mirror enabled. Node - dev-vm-aqmp-master & dev-vm-aqmp-slave
2. Create queue, exchange and policy for HA.

VM version:
CentOS Linux release 7.5.1804 (Core)

3.10.0-514.el7.x86_64 #1 SMP Tue Nov 22 16:42:41 UTC 2016 x86_64 x86_64 x86_64 GNU/Linux

Steps for erlang and rabbitmq server installation:

1. yum -y update

# Add and enable relevant application repositories:
# Note: We are also enabling third party remi package repositories.
2. wget http://dl.fedoraproject.org/pub/epel/7Server/x86_64/Packages/e/epel-release-7-11.noarch.rpm
3. wget http://rpms.famillecollet.com/enterprise/remi-release-7.rpm

4. sudo rpm -Uvh remi-release-7*.rpm epel-release-7*.rpm

# Finally, download and install Erlang:
#wget http://packages.erlang-solutions.com/site/esl/esl-erlang/FLAVOUR_1_general/esl-erlang_21.0-1~centos~7_amd64.rpm
5. wget https://packages.erlang-solutions.com/erlang/esl-erlang/FLAVOUR_1_general/esl-erlang_16.b.3-1~centos~6_amd64.rpm

6. yum provides **/libpq.so.2
7. yum install unixODBC unixODBC-devel postgresql-libs
8. sudo rpm -Uvh esl-erlang_16*.rpm

9. yum install -y erlang

# Download the latest RabbitMQ package using wget:
10. wget http://www.rabbitmq.com/releases/rabbitmq-server/current/rabbitmq-server-3.6.15-1.el7.noarch.rpm

# Add the necessary keys for verification:
11. rpm --import http://www.rabbitmq.com/rabbitmq-signing-key-public.asc

# Install the .RPM package using YUM:
12. yum install rabbitmq-server-3.6.15-1.el7.noarch.rpm

Add user and define roles :

rabbitmqctl add_user admin adm1n
rabbitmqctl set_user_tags admin administrator
rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"

Steps to add HA policy:

sudo rabbitmqctl set_policy ha "" \ '{"ha-mode":"all","ha-sync-mode":"automatic"}' --priority 1

Steps to start/stop Rabbitmq server:

1. /sbin/service rabbitmq-server start
2. /sbin/service rabbitmq-server stop
