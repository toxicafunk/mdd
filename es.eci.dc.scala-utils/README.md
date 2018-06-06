Data Centric - Scala Utils
==============================

Utilidades comunes de Data Centric para utilizar en otros repos

Build del proyecto - Maven & Eclipse
------------------
Para hacer el build del proyecto usamos Maven  basandonos en [Eclipse Tycho](https://eclipse.org/tycho/).  

Importar projecto en Eclipse
------------------
Importaremos el proyecto como "Existing Maven Project".

Uso de la librería
----------------
Para hacer uso de los elementos comunes que incluye la libería es necesario hacer lo siguiente:
- En caso de ser un proyecto Maven/Gradel, simplemente añadir la dependencias

**Maven**
~~~~
<dependency>
  <groupId>es.eci.dc</groupId>
  <artifactId>es.eci.dc.scala-utils</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
~~~~
