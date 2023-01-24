<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="/">
        <html>
            <body>
                <h1>Relevé bancaire</h1>
                <table border="1">
                    <tr>
                        <th>Date</th>
                        <th>Type</th>
                        <th>Montant</th>
                        <th>Description</th>
                    </tr>
                    <xsl:for-each select="releve/operations/operation">
                        <tr>
                            <td><xsl:value-of select="@date"/></td>
                            <td><xsl:value-of select="@type"/></td>
                            <td><xsl:value-of select="@montant"/></td>
                            <td><xsl:value-of select="@description"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
                <h2>Totaux</h2>
                <p>
                    <xsl:text>Total débits: </xsl:text>
                    <xsl:value-of select="sum(releve/operations/operation[@type='DEBIT']/@montant)"/>
                </p>
                <p>
                    <xsl:text>Total crédits: </xsl:text>
                    <xsl:value-of select="sum(releve/operations/operation[@type='CREDIT']/@montant)"/>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
