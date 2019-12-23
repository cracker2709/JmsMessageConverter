# JmsMessageConverter


Requirements JDK 11 Maven 3+ 

Azure Service Bus
--------------------

* Valid Azure service bus > jms.servicebus.connection-string to be
  adapted


Azure Service Bus
--------------------
* Internal ActiveMQ broker URL > Sample to send and receive jms json
  formatted messages using embedded spring boot activeMQ and camel

$ cd activemq

$ mvn test

Expected is


    For direct AMQ
    ----------------
     [           main] a.c.Sender : sending
      message='Person(name=GAP, cash=1000,
      hobbies=[guitar, swimming, netflix], birthDate=1981-09-27 01:23:41)'
      to destination='converter.q' [enerContainer-1] a.c.Receiver : received
      person='Person(name=GAP, cash=1000,
  hobbies=[guitar, swimming, netflix], birthDate=1981-09-27 01:23:41)'

    With Apache Camel Mock
    ----------------------
    2019-12-23 16:17:57.059  INFO 21785 [           main] a.PersonRouteBuilderTest                 : Received message : Person(name=GAP, cash=1000, hobbies=[guitar, swimming, netflix], birthDate=1981-09-27 01:23:41)
    2019-12-23 16:17:57.059  INFO 21785 [           main] o.a.c.c.m.MockEndpoint                   : Asserting: mock://out-queue.q is satisfied

* To launch with real activeMQ

I use this docker compose file
    version: "3"
    services:


      my-activemq:
        image: 'webcenter/activemq:5.14.3'
    
        restart: always
        hostname: "localhost"
        container_name: 'my-activemq'
        ports:
          - 8161:8161
          - 61616:61616

I launch it

    $ docker-compose up -d

And then I bootstrap my app

    mvn spring-boot:run


To launch basic AMQ I use this endpoint :

    http://localhost:8080/jms/sendPersonAndConsume

To lauch Routes with Camel, I use this one


    http://localhost:8080/camel/sendPersonInQueue


All can be monitored thanks to AMQ admin

    http://localhost:8161/admin/