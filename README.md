factura-electronica
====================

Librería de componentes Java para el desarrollo de aplicaciones de Factura Electrónica (CFDI)

[![Build Status](https://secure.travis-ci.org/GrupoCorasa/factura-electronica.png)](http://travis-ci.org/GrupoCorasa/factura-electronica)

## Librería de componentes

La librería presenta una interfaz muy simple centrada en el Comprobante Fiscal
Digital (CFD), la clase principal es `CFDv33` que tiene la lógica 
de la version 3.3 del CFDi.

Este elemento tiene la funcionalidad necesaria para: validar, firmar, verificar y serializar CFDs.

## Versión ligera

La librería presenta una alternativa debido al alto consumo de memoria que requiere la versión estandar causada por los catálogos publicados por el SAT.
La versión ligera, permite generar el XML capturando los campos como cadenas simples, y deshabilita la validación de los catálogos, por lo que si no tenemos extrema precaución en este sentido el PAC nos rechazará el archivo debido a que no es un valor del catálogo.

### Comprobante Fiscal Digital  (CFDv33):

```java
    CFDv33 cfd = new CFDv33(new FileInputStream(file)); // Crea un CFD a partir de un InputStream
    Key key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream(keyfile),  password);
    Certificate cert = KeyLoader.loadX509Certificate(new FileInputStream(certFile));
    Comprobante sellado = cfd.sellarComprobante(key, cert); // Firma el CFD y obtiene un Comprobante sellado
    cfd.validar(); // Valida el XML, que todos los elementos estén presentes
    cfd.verificar(); // Verifica un CFD ya firmado
    cfd.guardar(System.out); // Serializa el CFD a un OutputStream
```

### Timbre Fiscal Digital (TFDv11):

```java
    CFDv33 cfd = new CFDv33(new FileInputStream(file));// Crea un CFD a partir de un InputStream
    TFDv11 tfd = new TFDv11(cfd); // Crea un TDF a partir del CDF
    PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream(keyfile), password);
    tfd.timbrar(key); // Timbra el CDF
    tfd.verificar(cert); // Verifica el TDF
    tfd.guardar(System.out); // Serializa el CFD timbrado a un OutputStream
```

## Instalación

### Utiliza maven
```
<dependency>
  <groupId>mx.grupocorasa.cfdi</groupId>
  <artifactId>cfdi-base</artifactId>
  <version>0.3.0</version>
</dependency>
```

```
<dependency>
  <groupId>mx.grupocorasa.cfdi</groupId>
  <artifactId>cfdi-base-lightweight</artifactId>
  <version>0.3.0</version>
</dependency>
```

### Descarga las dependencias

 1. Descarga la última versión de las librerías
 2. Descomprime el archivo cfdi-base-0.3.0-bin.zip
 3. Agrega todos los archivos jar al classpath de tu aplicación.

## Documentos

Encuentra más información sobre los CFDI en las siguientes referencias las siguientes referencias:

* [Anexo 20](http://www.sat.gob.mx/informacion_fiscal/factura_electronica/Paginas/Anexo_20_version3.3.aspx)
* [Complementos SAT](http://www.sat.gob.mx/informacion_fiscal/factura_electronica/Paginas/complementos_factura_cfdi.aspx)

### Dudas y comentarios
¿Tienes algún problema o sugerencia de mejora?

Busca la respuesta en la sección de [seguimiento](https://github.com/GrupoCorasa/factura-electronica/issues?state=open). Si no encuentras la respuesta, crea una nueva entrada utilizando 
la liga de New Issue y haremos todo lo posible por solucionarlo.

### Proyecto Original de:
[elmer-garduno](https://github.com/elmer-garduno)

Comenzó el proyecto pero eventualmente fue abandonado debido a su falta de tiempo. 

La empresa Comercializadora Ortega y Accionistas, S.A. de C.V. tomó el proyecto ya iniciado por elmer para continuar con su mantenimiento y mejora.

El proyecto se mantendrá OpenSource para poder continuar con la mejora colectiva del mismo.