package org.amupoti.api.dataprovider;

import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class ApiDataProvider {

  @DataProvider
  public Iterator<Object[]> getPetIds() {
    return loadCsvFile("pets");
  }

  /**
   * Loads a csv file with data for the tests.
   *
   * @param fileName the filename without the extension
   * @return an iterator for each line in the CSV which contains as many objects in the array as
   * entries per line
   */
  private Iterator<Object[]> loadCsvFile(String fileName) {
    return CSVDataProvider.loadFile(fileName);
  }
}
