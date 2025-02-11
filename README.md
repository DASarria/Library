# Library
#### Integrantes: David Sarria y Santiago Amador







### Cobertura

Insertamos Jacoco a el Pom
![Image](assets/pluginJacoco.png)


Y ejecutamos  

![Image](assets/packagingJacoco.png)

Ahora abrimos el index.html ubicado en target/site  
![Image](assets/JacocoReport.png)  

Descargando Docker

![Image](assets/DockerDownload.png)     

Arrancando el servicio de SonarQube   

![Image](assets/SonarQubeStart.png)     

Validamos el funcionamiento de SonarQube  
![Image](assets/ValidatingSonarQube.png)  

iniciamos sesion en SonarQube  
![Image](assets/SonarQubeLogin.png)  
![Image](assets/login.png)    

Generamos el token   
![Image](assets/token.png)   

Agregamos Sonar y las propiedades a el Pom  
![Image](assets/SonarPlugin.png)
![Image](assets/SonarProperties.png)  

Resolvemos conflictos para que el proyecto se consruya correctamente y ejecutamos **mvn verify sonar:sonar -D sonar.token=[TOKEN_GENERADO]**  

![Image](assets/FinalConstruction.png)   

Finalmente en la pagina de Sonar podemos ver como se esta ejecutando  

![Image](assets/SonarFinalCoverage.png)   

![Image](assets/FinalImage.png) 
