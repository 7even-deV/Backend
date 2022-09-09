### `This is the specific order of execution of the applications for its correct use.`

***
> 1 - `DockerComposeDeployment`: Kafka and Zookeeper  
> 2 - `BusEurekaApplication_p8761`: [Eureka](http://localhost:8761)  
> 3 - `BusBalancerApplication_p8080`: Balancer  
> 4 - `BackWebApplication_p8081`: [BackWeb_busDB1](http://localhost:8081/h2-console)  
> 5 - `BackWebApplication_p8082`: [BackWeb_busDB2](http://localhost:8082/h2-console)  
> 6 - `BackWebApplication_p8083`: [BackWeb_busDB3](http://localhost:8083/h2-console)  
> 7 - `BackBusinessApplication_p8090`: [BackBusiness_busDB](http://localhost:8090/h2-console)  
> 8 - `Postman`: Import json  
***
