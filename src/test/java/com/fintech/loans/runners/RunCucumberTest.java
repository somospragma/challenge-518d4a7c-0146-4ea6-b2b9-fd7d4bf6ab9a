package com.fintech.loans.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runner de Cucumber para ejecutar los escenarios de prueba definidos en Gherkin.
 * Este runner busca los archivos .feature en src/test/resources/features y
 * ejecuta los steps definidos en el paquete com.fintech.loans.steps.
 * 
 * Configuración:
 * - features: ruta donde se encuentran los archivos .feature
 * - glue: paquete donde están las definiciones de pasos
 * - plugin: formatos de reporte (pretty para salida legible, html para reporte visual)
 * - tags: filtros para ejecutar escenarios específicos
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.fintech.loans.steps"},
    plugin = {"pretty", "html:target/cucumber-reports/cucumber.html",
              "json:target/cucumber-reports/cucumber.json"},
    tags = "@smoke or @regression",
    monochrome = true,
    dryRun = false,
    strict = true
)
public class RunCucumberTest {
    
    /**
     * Constructor por defecto.
     * JUnit crea la instancia del runner automáticamente.
     * No es necesario implementar lógica adicional aquí.
     */
    public RunCucumberTest() {
        super();
    }
}