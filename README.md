# README #

Web Service utilizando a integração com Drools

### Bibliotecas utilizadas ###

#### Drools V 7.6 #### 
* droolsjbpm-tools-distribution-7.6.0.Final.zip [Download zip](http://download.jboss.org/drools/release/7.6.0.Final/).

#### Jackson V 2.8.11 ####
* jackson-annotations-2.8.11 [Download jar](http://central.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.8.11/jackson-annotations-2.8.11.jar)
* jackson-core-2.8.11 [Download jar](http://central.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.8.11/jackson-core-2.8.11.jar)
* jackson-databind-2.8.11 [Download jar](http://central.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.8.11/jackson-databind-2.8.11.jar)
* jackson-jaxrs-base-2.2.3 [Download jar](http://central.maven.org/maven2/com/fasterxml/jackson/jaxrs/jackson-jaxrs-base/2.2.3/jackson-jaxrs-base-2.2.3.jar)
* jackson-jaxrs-base-2.8.11 [Download jar](http://central.maven.org/maven2/com/fasterxml/jackson/jaxrs/jackson-jaxrs-base/2.8.11/jackson-jaxrs-base-2.8.11.jar)
* jackson-jaxrs-json-provider-2.8.11 [Download jar](http://central.maven.org/maven2/com/fasterxml/jackson/jaxrs/jackson-jaxrs-json-provider/2.8.11/jackson-jaxrs-json-provider-2.8.11.jar)
* jackson-module-jaxb-annotations-2.8.11 [Download jar](http://central.maven.org/maven2/com/fasterxml/jackson/module/jackson-module-jaxb-annotations/2.8.11/jackson-module-jaxb-annotations-2.8.11.jar)
* jersey-media-json-jackson-2.3.1 [Download jar](http://central.maven.org/maven2/org/glassfish/jersey/media/jersey-media-json-jackson/2.3.1/jersey-media-json-jackson-2.3.1.jar)

#### JAX-RS 2.1 / Jersey 2.26+ ####
* jaxrs-ri-2.26 [Download zip](http://repo1.maven.org/maven2/org/glassfish/jersey/bundles/jaxrs-ri/2.26/jaxrs-ri-2.26.zip)

#### MySQL Connector/J ####
* mysql-connector-java-5.1.45 [Download zip. Necessário autenticação](https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.45.zip)

#### Configuração das bibliotecas ####

**Criar as seguintes User Libraries no Eclipse**

* Mysql-J
* Jackson-Parser-API
* JAX-RS-Jersey-API
* Drools


* **Drools:** Descompactar droolsjbpm-tools-distribution-7.6.0.Final.zip, navegar até a pasta droolsjbpm-tools-distribution-7.6.0.Final\binaries, copiar todos os jar's para a pasta [drools-7.6.0](libs/drools-7.6.0)

* **Mysql:** Descompactar mysql-connector-java-5.1.45.zip, navegar na raiz e copiar mysql-connector-java-5.1.45-bin.jar para a pasta [mysql](libs/mysql)

* **Jersey:** Descompactar jaxrs-ri-2.26.zip, navegar em jaxrs-ri-2.26, copiar todos os jar's que estão nas pastas api, ext e lib, e junta-los na pasta [jax-rs](libs/jax-rs)

* **Jackson:** Baixar todos os jar's e junta-los na pasta [jackson](libs/jackson)

**Adicionar os jar's nas User Libraries**

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact