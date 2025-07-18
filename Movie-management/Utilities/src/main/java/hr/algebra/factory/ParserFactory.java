/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.factory;

import java.io.InputStream;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Lovric
 */
public class ParserFactory {

    public static XMLEventReader getReader(InputStream is) throws XMLStreamException {
        return XMLInputFactory
                .newInstance()
                .createXMLEventReader(is);
    }

    private ParserFactory() {
    }

}
