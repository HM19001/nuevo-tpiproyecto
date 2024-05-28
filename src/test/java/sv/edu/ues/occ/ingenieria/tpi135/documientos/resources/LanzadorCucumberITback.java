/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.documientos.resources;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

/**
 *
 * @author alexo
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource(value = "rest/tipoatributo/bdd")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "sv.edu.ues.occ.ingenieria.tpi135.documientos.resources.cucumber")
public class LanzadorCucumberITback {
    
}
