<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:cartaporte="http://www.sat.gob.mx/CartaPorte">

  <xsl:template match="cartaporte:CartaPorte">
    <!--Manejador de nodos tipo CartaPorte-->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Version" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@TranspInternac" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@EntradaSalidaMerc" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@ViaEntradaSalida" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@TotalDistRec" />
    </xsl:call-template>

    <!--  Iniciamos el manejo de los elementos hijo en la secuencia -->
    <xsl:for-each select="./cartaporte:Ubicaciones">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

    <xsl:for-each select="./cartaporte:Mercancias">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

    <xsl:for-each select="./cartaporte:FiguraTransporte">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

  </xsl:template>
  
  <xsl:template match="cartaporte:Ubicaciones">
  
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Ubicacion-->
    <xsl:for-each select="./cartaporte:Ubicacion">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

  </xsl:template>

  <xsl:template match="cartaporte:Ubicacion">
    <!--Manejador de nodos tipo cartaporte:Ubicacion-->
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@TipoEstacion" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@DistanciaRecorrida" />
    </xsl:call-template>

    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Origen-->
    <xsl:for-each select="./cartaporte:Origen">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
	
	<!--  Iniciamos el tratamiento de los atributos de cartaporte:Destino-->
	<xsl:for-each select="./cartaporte:Destino">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
	
	<!--  Iniciamos el tratamiento de los atributos de cartaporte:Domicilio-->
	<xsl:for-each select="./cartaporte:Domicilio">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

  </xsl:template>
  
    <!--  Iniciamos el manejo de los elementos hijo en la secuencia Origen-->
  <xsl:template match="cartaporte:Origen">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@IDOrigen" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@RFCRemitente" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreRemitente" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumRegIdTrib" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@ResidenciaFiscal" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumEstacion" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreEstacion" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NavegacionTrafico" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@FechaHoraSalida" />
    </xsl:call-template>

  </xsl:template>
  
  
      <!--  Iniciamos el manejo de los elementos hijo en la secuencia Destino-->
  <xsl:template match="cartaporte:Destino">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@IDDestino" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@RFCDestinatario" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreDestinatario" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumRegIdTrib" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@ResidenciaFiscal" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumEstacion" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreEstacion" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NavegacionTrafico" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@FechaHoraProgLlegada" />
    </xsl:call-template>

  </xsl:template>
  
  
        <!--  Iniciamos el manejo de los elementos hijo en la secuencia Domicilio-->
  <xsl:template match="cartaporte:Domicilio">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Calle" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumeroExterior" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumeroInterior" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Colonia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Localidad" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Referencia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Municipio" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Estado" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Pais" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@CodigoPostal" />
    </xsl:call-template>

  </xsl:template>


  <xsl:template match="cartaporte:Mercancias">
    <!--Manejador de nodos tipo cartaporte:Mercancias-->
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@PesoBrutoTotal" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@UnidadPeso" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@PesoNetoTotal" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@NumTotalMercancias" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@CargoPorTasacion" />
    </xsl:call-template>

    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Mercancia-->
    <xsl:for-each select="./cartaporte:Mercancia">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

    <!--  Iniciamos el tratamiento de los atributos de cartaporte:AutotransporteFederal-->
    <xsl:for-each select="./cartaporte:AutotransporteFederal">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

    <!--  Iniciamos el tratamiento de los atributos de cartaporte:TransporteMaritimo-->
    <xsl:for-each select="./cartaporte:TransporteMaritimo">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
	
	<!--  Iniciamos el tratamiento de los atributos de cartaporte:TransporteAereo-->
    <xsl:for-each select="./cartaporte:TransporteAereo">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
	
	<!--  Iniciamos el tratamiento de los atributos de cartaporte:TransporteFerroviario-->
    <xsl:for-each select="./cartaporte:TransporteFerroviario">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

  </xsl:template>
  
  
   <!--  Iniciamos el manejo de los elementos hijo en la secuencia Mercancia-->
  <xsl:template match="cartaporte:Mercancia">
    <!--Manejador de nodos tipo cartaporte:Mercancia-->
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@BienesTransp" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@ClaveSTCC" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Descripcion" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Cantidad" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@ClaveUnidad" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Unidad" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Dimensiones" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@MaterialPeligroso" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@CveMaterialPeligroso" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Embalaje" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@DescripEmbalaje" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@PesoEnKg" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@ValorMercancia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Moneda" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@FraccionArancelaria" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@UUIDComercioExt" />
    </xsl:call-template>

    <!--  Iniciamos el tratamiento de los atributos de cartaporte:CantidadTransporta-->
    <xsl:for-each select="./cartaporte:CantidadTransporta">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:DetalleMercancia-->
    <xsl:for-each select="./cartaporte:DetalleMercancia">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

  </xsl:template>

  <!--  Iniciamos el manejo de los elementos hijo en la secuencia CantidadTransporta-->
  <xsl:template match="cartaporte:CantidadTransporta">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Cantidad" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@IDOrigen" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@IDDestino" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@CvesTransporte" />
    </xsl:call-template>
  </xsl:template>

  <!--  Iniciamos el manejo de los elementos hijo en la secuencia DetalleMercancia-->
  <xsl:template match="cartaporte:DetalleMercancia">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@UnidadPeso" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@PesoBruto" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@PesoNeto" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@PesoTara" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumPiezas" />
    </xsl:call-template>
  </xsl:template>
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia AutotransporteFederal-->
  <xsl:template match="cartaporte:AutotransporteFederal">
    <!--Manejador de nodos tipo cartaporte:AutotransporteFederal-->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@PermSCT" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@NumPermisoSCT" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@NombreAseg" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@NumPolizaSeguro" />
    </xsl:call-template>
	
	
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:IdentificacionVehicular-->
    <xsl:for-each select="./cartaporte:IdentificacionVehicular">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Remolques-->
    <xsl:for-each select="./cartaporte:Remolques">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

  </xsl:template>
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia IdentificacionVehicular-->
  <xsl:template match="cartaporte:IdentificacionVehicular">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@ConfigVehicular" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@PlacaVM" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@AnioModeloVM" />
    </xsl:call-template>
  </xsl:template>
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia Remolques-->
  <xsl:template match="cartaporte:Remolques">
    
	<!--  Iniciamos el tratamiento de los atributos de cartaporte:Remolque-->
    <xsl:for-each select="./cartaporte:Remolque">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
  </xsl:template>
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia Remolque-->
  <xsl:template match="cartaporte:Remolque">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@SubTipoRem" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Placa" />
    </xsl:call-template>
  </xsl:template>
  
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia TransporteMaritimo-->
  <xsl:template match="cartaporte:TransporteMaritimo">
    <!--Manejador de nodos tipo cartaporte:TransporteMaritimo-->
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@PermSCT" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumPermisoSCT" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreAseg" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumPolizaSeguro" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@TipoEmbarcacion" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Matricula" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@NumeroOMI" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@AnioEmbarcacion" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreEmbarc" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@NacionalidadEmbarc" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@UnidadesDeArqBruto" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@TipoCarga" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@NumCertITC" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Eslora" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Manga" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Calado" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@LineaNaviera" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@NombreAgenteNaviero" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@NumAutorizacionNaviero" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumViaje" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumConocEmbarc" />
    </xsl:call-template>
	
	
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Contenedor-->
    <xsl:for-each select="./cartaporte:Contenedor">
    <!--  Iniciamos el manejo de los elementos hijo en la secuencia Contenedor-->
      <!--  Iniciamos el manejo de los nodos dependientes -->
      <xsl:call-template name="Requerido">
        <xsl:with-param name="valor" select="./@MatriculaContenedor" />
      </xsl:call-template>
      <xsl:call-template name="Requerido">
        <xsl:with-param name="valor" select="./@TipoContenedor" />
      </xsl:call-template>
  	<xsl:call-template name="Opcional">
        <xsl:with-param name="valor" select="./@NumPrecinto" />
      </xsl:call-template>
    </xsl:for-each>

  </xsl:template>
  
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia TransporteAereo-->
  <xsl:template match="cartaporte:TransporteAereo">
    <!--Manejador de nodos tipo cartaporte:TransporteAereo-->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@PermSCT" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@NumPermisoSCT" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@MatriculaAeronave" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreAseg" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumPolizaSeguro" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@NumeroGuia" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@LugarContrato" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@RFCTransportista" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@CodigoTransportista" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumRegIdTribTranspor" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@ResidenciaFiscalTranspor" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreTransportista" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@RFCEmbarcador" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumRegIdTribEmbarc" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@ResidenciaFiscalEmbarc" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreEmbarcador" />
    </xsl:call-template>

  </xsl:template>
    
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia TransporteFerroviario-->
  <xsl:template match="cartaporte:TransporteFerroviario">
    <!--Manejador de nodos tipo cartaporte:TransporteFerroviario-->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@TipoDeServicio" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreAseg" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumPolizaSeguro" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Concesionario" />
    </xsl:call-template>
	
	
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:DerechosDePaso-->
    <xsl:for-each select="./cartaporte:DerechosDePaso">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Carro-->
    <xsl:for-each select="./cartaporte:Carro">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

  </xsl:template>
  
   <!--  Iniciamos el manejo de los elementos hijo en la secuencia DerechosDePaso-->
  <xsl:template match="cartaporte:DerechosDePaso">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@TipoDerechoDePaso" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@KilometrajePagado" />
    </xsl:call-template>
  </xsl:template>
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia Carro-->
  <xsl:template match="cartaporte:Carro">
  <!--Manejador de nodos tipo cartaporte:Carro-->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@TipoCarro" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@MatriculaCarro" />
    </xsl:call-template>
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@GuiaCarro" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@ToneladasNetasCarro" />
    </xsl:call-template>
    
	<!--  Iniciamos el tratamiento de los atributos de cartaporte:Contenedor -->
    <xsl:for-each select="./cartaporte:Contenedor ">
      <!--  Iniciamos el manejo de los elementos hijo en la secuencia Contenedor-->
      

        <!--  Iniciamos el manejo de los nodos dependientes -->
        <xsl:call-template name="Requerido">
          <xsl:with-param name="valor" select="./@TipoContenedor" />
        </xsl:call-template>
        <xsl:call-template name="Requerido">
          <xsl:with-param name="valor" select="./@PesoContenedorVacio" />
        </xsl:call-template>
    	<xsl:call-template name="Requerido">
          <xsl:with-param name="valor" select="./@PesoNetoMercancia" />
        </xsl:call-template>

    </xsl:for-each>
	
  </xsl:template>
  
  
  
  <xsl:template match="cartaporte:FiguraTransporte">
    <!--Manejador de nodos tipo cartaporte:FiguraTransporte-->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@CveTransporte" />
    </xsl:call-template>
	
	
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Operadores -->
    <xsl:for-each select="./cartaporte:Operadores ">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
	
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Propietario -->
    <xsl:for-each select="./cartaporte:Propietario ">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
	
	<!--  Iniciamos el tratamiento de los atributos de cartaporte:Arrendatario -->
    <xsl:for-each select="./cartaporte:Arrendatario ">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
	
	<!--  Iniciamos el tratamiento de los atributos de cartaporte:Notificado -->
    <xsl:for-each select="./cartaporte:Notificado ">
      <xsl:apply-templates select="."/>
    </xsl:for-each>

  </xsl:template>
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia Operadores-->
  <xsl:template match="cartaporte:Operadores">
    
	<!--  Iniciamos el tratamiento de los atributos de cartaporte:Operador-->
    <xsl:for-each select="./cartaporte:Operador">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
  </xsl:template>
  
    <!--  Iniciamos el manejo de los elementos hijo en la secuencia Operador-->
  <xsl:template match="cartaporte:Operador">
    <!--Manejador de nodos tipo cartaporte:Operador-->
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@RFCOperador" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumLicencia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreOperador" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumRegIdTribOperador" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@ResidenciaFiscalOperador" />
    </xsl:call-template>
	
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Domicilio -->
    <xsl:for-each select="./cartaporte:Domicilio ">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
	
  </xsl:template>
  
         <!--  Iniciamos el manejo de los elementos hijo en la secuencia Domicilio-->
  <xsl:template match="cartaporte:Domicilio">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Calle" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumeroExterior" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumeroInterior" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Colonia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Localidad" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Referencia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Municipio" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Estado" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Pais" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@CodigoPostal" />
    </xsl:call-template>

  </xsl:template>
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia Propietario-->
  <xsl:template match="cartaporte:Propietario">
    <!--Manejador de nodos tipo cartaporte:Propietario-->
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@RFCPropietario" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombrePropietario" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumRegIdTribPropietario" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@ResidenciaFiscalPropietario" />
    </xsl:call-template>
	
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Domicilio -->
    <xsl:for-each select="./cartaporte:Domicilio ">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
	
  </xsl:template>
  
         <!--  Iniciamos el manejo de los elementos hijo en la secuencia Domicilio-->
  <xsl:template match="cartaporte:Domicilio">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Calle" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumeroExterior" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumeroInterior" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Colonia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Localidad" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Referencia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Municipio" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Estado" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Pais" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@CodigoPostal" />
    </xsl:call-template>

  </xsl:template>
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia Arrendatario-->
  <xsl:template match="cartaporte:Arrendatario">
    <!--Manejador de nodos tipo cartaporte:Arrendatario-->
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@RFCArrendatario" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreArrendatario" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumRegIdTribArrendatario" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@ResidenciaFiscalArrendatario" />
    </xsl:call-template>
	
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Domicilio -->
    <xsl:for-each select="./cartaporte:Domicilio ">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
	
  </xsl:template>
  
         <!--  Iniciamos el manejo de los elementos hijo en la secuencia Domicilio-->
  <xsl:template match="cartaporte:Domicilio">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Calle" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumeroExterior" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumeroInterior" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Colonia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Localidad" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Referencia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Municipio" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Estado" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Pais" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@CodigoPostal" />
    </xsl:call-template>

  </xsl:template>
  
  <!--  Iniciamos el manejo de los elementos hijo en la secuencia Notificado-->
  <xsl:template match="cartaporte:Notificado">
    <!--Manejador de nodos tipo cartaporte:Notificado-->
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@RFCNotificado" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NombreNotificado" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumRegIdTribNotificado" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@ResidenciaFiscalNotificado" />
    </xsl:call-template>
	
    <!--  Iniciamos el tratamiento de los atributos de cartaporte:Domicilio -->
    <xsl:for-each select="./cartaporte:Domicilio ">
      <xsl:apply-templates select="."/>
    </xsl:for-each>
	
  </xsl:template>
  
         <!--  Iniciamos el manejo de los elementos hijo en la secuencia Domicilio-->
  <xsl:template match="cartaporte:Domicilio">
    <!--  Iniciamos el manejo de los nodos dependientes -->
    <xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Calle" />
    </xsl:call-template>
    <xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumeroExterior" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@NumeroInterior" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Colonia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Localidad" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Referencia" />
    </xsl:call-template>
	<xsl:call-template name="Opcional">
      <xsl:with-param name="valor" select="./@Municipio" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Estado" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@Pais" />
    </xsl:call-template>
	<xsl:call-template name="Requerido">
      <xsl:with-param name="valor" select="./@CodigoPostal" />
    </xsl:call-template>

  </xsl:template>
</xsl:stylesheet>