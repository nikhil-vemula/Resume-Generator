<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
<xsl:template match="resume">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body/>
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="simpleA4">
        <fo:flow flow-name="xsl-region-body">
          <fo:block font-size="16pt" font-weight="bold" space-after="3mm" text-align="center">
		  <xsl:value-of select="fullname"/>
          </fo:block>
		  <fo:block font-size="10pt" space-after="2mm" text-align="center">
		  Mobile:<xsl:value-of select="mobile"/>
          </fo:block>
		  <fo:block font-size="9pt" space-after="2mm" text-align="center">
		  Email:<xsl:value-of select="email"/>
          </fo:block>
		  <fo:block font-size="14pt" space-after="0mm" text-align="left">
		  Career Objective
			<fo:leader leader-pattern="rule" space-after="2mm" leader-length="100%" rule-style="solid" rule-thickness="2pt"/> 
			<fo:block font-size="11pt" space-after="2mm" text-align="left">
			<xsl:value-of select="careerobjective"/>
			</fo:block>		  
          </fo:block>
		  <fo:block font-size="14pt" space-after="0mm" text-align="left">
		  Skills
			<fo:leader leader-pattern="rule" space-after="2mm" leader-length="100%" rule-style="solid" rule-thickness="2pt"/> 
			<fo:block font-size="10pt">
			<fo:list-block>
			<xsl:apply-templates select="skill"/>
			</fo:list-block>
          </fo:block>
          </fo:block>
		  <fo:block font-size="14pt" space-after="0mm" text-align="left">
		  Education
			<fo:leader leader-pattern="rule" space-after="2mm" leader-length="100%" rule-style="solid" rule-thickness="2pt"/> 
			<xsl:apply-templates select="education"/>
          </fo:block>
		  <fo:block font-size="14pt" space-after="0mm" text-align="left">
		  Activities and Involvement
			<fo:leader leader-pattern="rule" space-after="2mm" leader-length="100%" rule-style="solid" rule-thickness="2pt"/> 
			<xsl:apply-templates select="activity"/>	
          </fo:block>
		  <fo:block font-size="14pt" space-after="0mm" text-align="left">
		  Achievement
			<fo:leader leader-pattern="rule" space-after="2mm" leader-length="100%" rule-style="solid" rule-thickness="2pt"/> 
			<xsl:apply-templates select="achievement"/>	
          </fo:block>
        </fo:flow>
      </fo:page-sequence>
     </fo:root>
</xsl:template>
<xsl:template match="education"> 
	<fo:block font-size="11pt" space-after="2mm" text-align="left">
      <xsl:value-of select="institute"/>
	</fo:block>	
	<fo:block font-size="10pt" space-after="2mm" text-align="left">
      <xsl:value-of select="course"/>
	</fo:block>	
	<fo:block font-size="9pt" space-after="2mm" text-align="left">
      <xsl:value-of select="grade"/>
	</fo:block>
</xsl:template>
<xsl:template match="skill">
    <fo:list-item>
		<fo:list-item-label>
			<fo:block>*</fo:block>
		</fo:list-item-label>
		<fo:list-item-body>
			<fo:block><xsl:value-of select="skillname"/></fo:block>
		</fo:list-item-body>
	</fo:list-item>
  </xsl:template>
  <xsl:template match="activity">
			<fo:block font-size="11pt" space-after="2mm" text-align="left">
			<xsl:value-of select="name"/>
			</fo:block>
  </xsl:template>
  <xsl:template match="achievement">
			<fo:block font-size="11pt" space-after="2mm" text-align="left">
			<xsl:value-of select="name"/>
			</fo:block>
  </xsl:template>
</xsl:stylesheet>