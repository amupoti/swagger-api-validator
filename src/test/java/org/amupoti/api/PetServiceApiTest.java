package org.amupoti.api;

import org.amupoti.api.config.ConfigurationHelper;
import org.amupoti.api.dataprovider.ApiDataProvider;
import com.atlassian.oai.validator.restassured.SwaggerValidationFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Validation of the request and responses for the
 *
 * @author marrufat
 */
public class PetServiceApiTest {

  private SwaggerValidationFilter validationFilter;

  @Test(testName = "PetStore - Get a pet",
      dataProviderClass = ApiDataProvider.class,
      dataProvider = "getPetIds")
  public void testPublication(String petId) {
    String urlTemplate = "http://petstore.swagger.io/v2/pet/%s";
    String url = String.format(urlTemplate, petId);

    testGetOkAndValidateSwagger(url);
  }

  @BeforeClass
  public void init() {
    validationFilter = ConfigurationHelper.loadValidationFilter();
  }

  protected void testGetOkAndValidateSwagger(String url) {
    //@formatter:off
    given().
        filter(validationFilter).
        when().
        get(url).
        then().
        assertThat().
        statusCode(200).
        log().ifValidationFails();
    //@formatter:on
  }

}
