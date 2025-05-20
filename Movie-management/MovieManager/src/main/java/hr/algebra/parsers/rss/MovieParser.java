/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConFactory;
import hr.algebra.model.Movie;
import hr.algebra.utilities.FileUtils;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Lovric
 */
public class MovieParser {

    private static void uploadPicture(Movie movie, String source) {
        try {
            String ext = source.substring(source.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
            
            String name = UUID.randomUUID() + ext;
            String destination = DIR + File.separator + name;
            
            FileUtils.copyFromUrl(source,destination);
            
            movie.setPicturePath(destination);
        } catch (Exception ex) {
            Logger.getLogger(MovieParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private enum Tag {
        ITEM("item"),
        TITLE("title"),
        LINK("link"),
        DESC("description"),
        ENC("enclosure"),
        PUB_DATE("pubDate");

        private Tag(String value) {
            this.value = value;
        }
        
       private final String value;
       
       public static Optional<Tag> from(String value) {
           for (Tag tag : values()) {
               if (tag.value.equals(value)) {
                   return Optional.of(tag);
               }
           }
           return Optional.empty();
       }
    }
    
    private MovieParser() {
    }
    
    private static final String RSS_URL = "https://slobodnadalmacija.hr/feed/category/286";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    public static List<Movie> parse() throws Exception {
        List<Movie> movies = new ArrayList<>();
        
        HttpURLConnection con = UrlConFactory.getCon(RSS_URL);
        try(InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.getReader(is);
            
            Optional<Tag> tag = Optional.empty();
            Movie movie = null;
            StartElement startElement = null;
            
            while (reader.hasNext()) {                
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        startElement = event.asStartElement();
                        String name = startElement.getName().getLocalPart();
                        tag = Tag.from(name);
                        if (tag.isPresent() && tag.get().equals(Tag.ITEM)) {
                            movie = new Movie();
                            movies.add(movie);
                            
                        }
                    }
                         case XMLStreamConstants.CHARACTERS -> {
                             if (tag.isPresent() && movie != null) {
                                 String data = event
                                         .asCharacters()
                                         .getData()
                                         .trim();
                    switch(tag.get()) {
                        case TITLE -> {
                            if (!data.isEmpty()) {
                                movie.setTitle(data);
                            }
                        }
                        case LINK -> {
                             if (!data.isEmpty()) {
                                movie.setLink(data);
                            }
                        }
                        case DESC -> {
                             if (!data.isEmpty()) {
                                movie.setDescription(data);
                            }
                        } 
                        case ENC -> {
                            if (startElement != null && movie.getPicturePath() == null) {
                                Attribute att = startElement.getAttributeByName(new QName(ATTRIBUTE_URL));
                                if (att != null) {
                                    uploadPicture(movie,att.getValue());
                                }
                            }
                        }
                        case PUB_DATE -> {
                             if (!data.isEmpty()) {
                                 LocalDateTime publishedDate = LocalDateTime.parse(data,DateTimeFormatter.RFC_1123_DATE_TIME);
                                 movie.setStartDate(publishedDate);   
                            }
                        }
                    }
                }
            }
        }
    }
    return movies;   
    }
  }
}
