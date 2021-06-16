[![N|Solid](https://i.imgur.com/Vlml12n.png)](https:nodesource.com/products/nsolid)

# INNOVA UFPS 
### Aplicacion Web que apoya el proceso de gestión de información de clientes para la unidad de emprendimiento INNOVA UFPS (SIU).  
***
## Índice
1. [Características](#características)
3. [Tecnologías](#tecnologías)
4. [IDE](#ide)
5. [Instalación](#instalación)
6. [Demo](#demo)
7. [Autor(es)](#autores)
8. [Institución Académica](#institución-académica)
***
### Características:

  - Registro de Contactos
  - Recuperación de contraseña
  - Enviar solicitud de acompañamiento profesional
  - Aprobar solicitud acompañamiento / Asignar asesor
  - Registro de asesores
  - Agendamiento de asesorias
  - Notificar agendamiento de asesorias
  - Registro de eventos
  - Inscripciones de contactos y/o clientes a eventos
  - Tomar asistencia de contactos y/o clientes inscritos a eventos
  - Reportes de horas de asesorias efectuadas por asesores
  - Reportes sobre clientes inscritos en la plataforma
  - Reportes de asistencias a eventos
***
### Tecnologías
  
  ##### BackEnd:

- Java v11.0.9
- spring-boot v2.4.5 
- spring-boot-starter-web v2.4.5
- spring-boot-starter-mail v2.4.5
- spring-boot-starter-security v2.4.5
- spring-boot-starter-data-jpa v2.4.5
- jjwt v0.9.1
- gson v2.8.7
- commons-text v1.9
- lombok v1.18.12
- mapstruct v1.3.1.Final
- springfox-swagger2 v2.9.2

 ##### FrontEnd
  - Node.js  v14.15.0
  - Angular v11.2.12
  - Angular material v11.2.11
  - TypeScript v4.1.5
  - Sass v11.2.12
  - file-saver v2.0.5
  - sweetalert2 v10.16.7
  - xlsx v0.17.0

 ##### Gestor Base de Datos
  - MySql

 ##### Herramienta de Diseño de Bases De Datos
  - WorkBench 8.0 [Descargar](https://www.mysql.com/products/workbench/)

 #### Herramientas de Despliegue
  - Firebase (WEB)
  - Heroku (API)
  - PhpMyAdmin (DB)

  
  ***

### IDE

- [Visual Studio Code](https://code.visualstudio.com/)

- [IntelliJ IDE](https://www.jetbrains.com/es-es/idea/)

***
### Instalación
- Node.js v14.15.0 [descargar](https://nodejs.org/es/).
- OpenJDK v11.0.9 [descargar](https://adoptopenjdk.net/).
- Gradle [instalar](https://gradle.org/install/).
- Base de Datos para pruebas [phpmyadmin](http://www.madarme.co/phpmyadmin/).

Para Base de Datos en local:
```sh
- Instalar Docker
- docker volume create mysql-db-data
- docker run -d -p 3306:3306 --name mysql-db  -e MYSQL_ROOT_PASSWORD=secret --mount src=mysql-db-data,dst=/var/lib/mysql mysql
- docker exec -it mysql-db mysql -p
- DROP SCHEMA IF EXISTS `innova` ;
- CREATE SCHEMA `innova` ;
```

Para API en local:
```sh
- Instalar Java y Gradle
- Descargar Proyecto o Clonar Proyecto [GitHub](https://github.com/Capsule-Corp-Cucuta/innova-api)
- gradle build
- java -jar -Xmx2048m -Dspring.profiles.active=dev build/libs/innova-1.0.0.jar
```

Para Front en local:
```sh
- Instalar Node.js
- Instalar Angular (npm install -g @angular/cli) 
- Descargar Proyecto o Clonar Proyecto [GitHub](https://github.com/Capsule-Corp-Cucuta/innova-web)
- npm install
- ng serve
```

***
### Demo

Para ver el demo de la aplicación puede dirigirse a:
  - [INNOVA WEB APP](https://innova-ufps.web.app)
  - [INNOVA SWAGGER](https://innova-ufps.herokuapp.com/innova/api/swagger-ui.html)

***
### Autor(es)

Proyecto desarrollado por:

- [Sergio Andrés Rodriguez Ramirez](<sergioandresrr@ufps.edu.co>).
- [José Guillermo Parada Corredor](<joseguillermopc@ufps.edu.co>).
- [Edwar Yesid Camargo Roa](<edwaryesidcr@ufps.edu.co>).

***
### Institución Académica 
[Universidad Francisco de Paula Santander](https://ww2.ufps.edu.co/)

Curso de Profundización Desarrollo de Software

[Programa de Ingeniería de Sistemas](https://ingsistemas.cloud.ufps.edu.co/)
