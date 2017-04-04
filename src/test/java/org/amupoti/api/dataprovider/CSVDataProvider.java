package org.amupoti.api.dataprovider;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper class which provides data from csv files in the classpath
 */
public class CSVDataProvider {

  public static final String CSV_FOLDER = "/dataprovider/";
  public static final String EXTENSION = ".csv";
  public static final String DEFAULT_CHARSET = "UTF-8";
  public static final char COMMENT_MARKER = '#';

  public static Iterator<Object[]> loadFile(String name) {
    String path = CSV_FOLDER + "/" + name + EXTENSION;
    try {
      InputStream inputStream = CSVDataProvider.class.getResourceAsStream(path);
      String content = IOUtils.toString(inputStream, DEFAULT_CHARSET);
      CSVParser parser = CSVParser.parse(content, CSVFormat.DEFAULT.withCommentMarker(COMMENT_MARKER));
      List<Object[]> iteratorStream = parser.getRecords().stream().map(r -> IteratorUtils.toArray(r.iterator()))
          .collect(Collectors.toList());
      return iteratorStream.iterator();
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not load file in path " + path, e);
    }
  }
}
