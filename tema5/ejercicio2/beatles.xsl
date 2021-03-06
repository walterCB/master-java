<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output
      omit-xml-declaration="no"
      encoding="UTF-8"/>

  <xsl:template match="/">
    <xsl:element name="html">
      <xsl:element name="body">
	<xsl:apply-templates select="./beatles"/>
      </xsl:element>
    </xsl:element>
  </xsl:template>

  <xsl:template match="beatles">
    <xsl:apply-templates select="discos"/>
  </xsl:template>

  <xsl:template match="discos">
    <xsl:element name="h1">
      <xsl:value-of select="@titulo"/>
    </xsl:element>

    <xsl:element name="table">
      <xsl:element name="thead">
	<xsl:element name="tr">
	  <xsl:element name="th">Título</xsl:element>
	  <xsl:element name="th">Autor</xsl:element>
	  <xsl:element name="th">Año</xsl:element>
	</xsl:element>
      </xsl:element>
      <xsl:element name="tbody">
	<xsl:apply-templates select=".//cancion"/>
      </xsl:element>
    </xsl:element>
  </xsl:template>

  <xsl:template match="cancion">
    <xsl:element name="tr">
      <xsl:element name="td"><xsl:value-of select="@titulo"/></xsl:element>
      <xsl:element name="td"><xsl:value-of select="@autor"/></xsl:element>
      <xsl:element name="td"><xsl:value-of select="@agno"/></xsl:element>
    </xsl:element>
  </xsl:template>

</xsl:stylesheet>
