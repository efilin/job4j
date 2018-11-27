package ru.job4j.magnit;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class ConvertXSQT {

    //TODO  Допилить метод
    public void convert(File source, File dest, File scheme) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
                new StreamSource(scheme));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
        transformer.transform(new StreamSource(source),
                new StreamResult(dest)
        );
        /*<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
<entries>
	<xsl:for-each select="entries/entry">
		<entry>
			<xsl:attribute name="field">
				<xsl:value-of select="field"/>
			</xsl:attribute>
		</entry>
	</xsl:for-each>
</entries>
</xsl:template>
</xsl:stylesheet>*/
    }
}

