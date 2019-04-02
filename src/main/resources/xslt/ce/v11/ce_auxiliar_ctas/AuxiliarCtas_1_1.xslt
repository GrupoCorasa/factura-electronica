<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:AuxiliarCtas="www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas"
                xmlns:AuxiliarCtasB="http://www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas" version="2.0">
    <!--
   En esta sección se define la inclusión de las plantillas de utilerías para colapsar espacios
   -->
    <xsl:include href="http://www.sat.gob.mx/esquemas/utilerias.xslt"/>
    <!--
    Con el siguiente método se establece que la salida deberá ser en texto
   -->
    <xsl:output method="text" version="1.0" encoding="UTF-8" indent="no"/>
    <!--
    Aquí iniciamos el procesamiento de la cadena original con su | inicial y el terminador ||
   -->
    <xsl:template match="/">
        |
        <xsl:apply-templates select="/AuxiliarCtas:AuxiliarCtas"/>
        <xsl:apply-templates select="/AuxiliarCtasB:AuxiliarCtas"/>
        ||
    </xsl:template>
    <!--
     Aquí iniciamos el procesamiento de los datos incluidos en el comprobante
   -->
    <xsl:template match="AuxiliarCtas:AuxiliarCtas">
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Version"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@RFC"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Mes"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Anio"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@TipoSolicitud"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@NumOrden"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@NumTramite"/>
        </xsl:call-template>
        <xsl:apply-templates select="./AuxiliarCtas:Cuenta"/>
    </xsl:template>
    <xsl:template match="AuxiliarCtas:Cuenta">
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@NumCta"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@DesCta"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@SaldoIni"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@SaldoFin"/>
        </xsl:call-template>
        <xsl:apply-templates select="./AuxiliarCtas:DetalleAux"/>
    </xsl:template>
    <xsl:template match="AuxiliarCtas:DetalleAux">
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Fecha"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@NumUnIdenPol"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Debe"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Haber"/>
        </xsl:call-template>
    </xsl:template>
    <!--  Sección B  -->
    <xsl:template match="AuxiliarCtasB:AuxiliarCtas">
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Version"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@RFC"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Mes"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Anio"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@TipoSolicitud"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@NumOrden"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@NumTramite"/>
        </xsl:call-template>
        <xsl:apply-templates select="./AuxiliarCtasB:Cuenta"/>
    </xsl:template>
    <xsl:template match="AuxiliarCtasB:Cuenta">
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@NumCta"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@DesCta"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@SaldoIni"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@SaldoFin"/>
        </xsl:call-template>
        <xsl:apply-templates select="./AuxiliarCtasB:DetalleAux"/>
    </xsl:template>
    <xsl:template match="AuxiliarCtasB:DetalleAux">
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Fecha"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@NumUnIdenPol"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Debe"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@Haber"/>
        </xsl:call-template>
    </xsl:template>
</xsl:stylesheet>