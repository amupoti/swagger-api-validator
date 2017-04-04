package org.amupoti.api.config;

import com.atlassian.oai.validator.restassured.SwaggerValidationFilter;

public class ConfigurationHelper {

  public static SwaggerValidationFilter loadValidationFilter() {

    return new SwaggerValidationFilter("http://petstore.swagger.io/v2/swagger.json");

  }
}
