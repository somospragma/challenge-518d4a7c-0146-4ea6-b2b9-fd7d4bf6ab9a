package com.fintech.loans.steps;

import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Step Definitions para los escenarios de prueba en Gherkin.
 * Cada método en esta clase define la implementación de un paso del escenario.
 * Los pasos se mapean mediante expresiones regulares o sintaxis Gherkin.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XSteps implements En {

    private MockMvc mockMvc;
    private ResultActions lastResponse;
    private String lastRequestBody;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    /**
     * Constructor que inicializa los step definitions y configura el MockMvc.
     * Aquí se definen todos los pasos que los escenarios Gherkin pueden ejecutar.
     */
    public XSteps() {
        
        Before(() -> {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        });
        
        // Step: Dado que el cliente existe en el sistema
        Given("^el cliente con ID \"([^"]+)\" existe en el sistema$", (String customerId) -> {
            // TODO: Implementar verificación de existencia del cliente
            // Por ahora, el test asume que el cliente puede ser creado o ya existe
        });
        
        // Step: Cuando solicito un préstamo con los siguientes datos
        When("^solicito un préstamo con los siguientes datos:$", (io.cucumber.datatable.DataTable dataTable) -> {
            List<Map<String, String>> rows = dataTable.asMaps();
            Map<String, String> loanData = rows.get(0);
            
            String customerId = loanData.get("customerId");
            String amount = loanData.get("amount");
            String interestRate = loanData.get("interestRate");
            String termMonths = loanData.get("termMonths");
            
            lastRequestBody = String.format(
                "{\"customerId\":\"%s\",\"amount\":%s,\"interestRate\":%s,\"termMonths\":%s}",
                customerId, amount, interestRate, termMonths
            );
            
            lastResponse = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lastRequestBody));
        });
        
        // Step: Cuando solicito un préstamo con monto negativo
        When("^solicito un préstamo con monto negativo$", () -> {
            lastRequestBody = "{\"customerId\":\"CUST001\",\"amount\":-1000,\"interestRate\":0.05,\"termMonths\":12}";
            lastResponse = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lastRequestBody));
        });
        
        // Step: Cuando solicito un préstamo con tasa de interés inválida
        When("^solicito un préstamo con tasa de interés inválida \(mayor al 100%\)$", () -> {
            lastRequestBody = "{\"customerId\":\"CUST001\",\"amount\":10000,\"interestRate\":1.5,\"termMonths\":12}";
            lastResponse = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lastRequestBody));
        });
        
        // Step: Cuando solicito un plazo negativo
        When("^solicito un préstamo con plazo negativo$", () -> {
            lastRequestBody = "{\"customerId\":\"CUST001\",\"amount\":10000,\"interestRate\":0.05,\"termMonths\":-6}";
            lastResponse = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lastRequestBody));
        });
        
        // Step: Entonces el préstamo es creado exitosamente
        Then("^el préstamo es creado exitosamente$", () -> {
            lastResponse.andExpect(status().isCreated())
                .andExpect(jsonPath("$.loanId").exists())
                .andExpect(jsonPath("$.customerId").exists())
                .andExpect(jsonPath("$.status").value("ACTIVE"));
        });
        
        // Step: Y la respuesta contiene los datos del préstamo
        Then("^la respuesta contiene los datos del préstamo$", () -> {
            lastResponse.andExpect(jsonPath("$.amount").exists())
                .andExpect(jsonPath("$.interestRate").exists())
                .andExpect(jsonPath("$.termMonths").exists())
                .andExpect(jsonPath("$.monthlyPayment").exists());
        });
        
        // Step: Entonces recibo un error de solicitud inválida
        Then("^recibo un error de solicitud inválida con código 400$", () -> {
            lastResponse.andExpect(status().isBadRequest());
        });
        
        // Step: Y el mensaje de error indica que el monto debe ser positivo
        Then("^el mensaje de error indica que el monto debe ser positivo$", () -> {
            lastResponse.andExpect(jsonPath("$.error").exists());
        });
        
        // Step: Cuando consulto los préstamos del cliente
        When("^consulto los préstamos del cliente con ID \"([^"]+)\"$", (String customerId) -> {
            lastResponse = mockMvc.perform(get("/api/loans/customer/" + customerId)
                .accept(MediaType.APPLICATION_JSON));
        });
        
        // Step: Entonces obtengo una lista de préstamos
        Then("^obtengo una lista de préstamos$", () -> {
            lastResponse.andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").isArray());
        });
        
        // Step: Cuando consulto un préstamo por ID
        When("^consulto el préstamo con ID \"([^"]+)\"$", (String loanId) -> {
            lastResponse = mockMvc.perform(get("/api/loans/" + loanId)
                .accept(MediaType.APPLICATION_JSON));
        });
        
        // Step: Entonces obtengo los detalles del préstamo
        Then("^obtengo los detalles del préstamo$", () -> {
            lastResponse.andExpect(status().isOk())
                .andExpect(jsonPath("$.loanId").exists())
                .andExpect(jsonPath("$.amount").exists());
        });
        
        // Step: Cuando actualizo el estado del préstamo
        When("^actualizo el estado del préstamo \"([^"]+)\" a \"([^"]+)\"$", (String loanId, String newStatus) -> {
            lastResponse = mockMvc.perform(post("/api/loans/" + loanId + "/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\":\"" + newStatus + "\"}"));
        });
        
        // Step: Entonces el estado del préstamo es actualizado
        Then("^el estado del préstamo es actualizado a \"([^"]+)\"$", (String expectedStatus) -> {
            lastResponse.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(expectedStatus));
        });
        
        // Step: Cuando calculo la cuota mensual
        When("^calculo la cuota mensual del préstamo \"([^"]+)\"$", (String loanId) -> {
            lastResponse = mockMvc.perform(get("/api/loans/" + loanId + "/monthly-payment")
                .accept(MediaType.APPLICATION_JSON));
        });
        
        // Step: Entonces obtengo el valor de la cuota mensual
        Then("^obtengo el valor de la cuota mensual$", () -> {
            lastResponse.andExpect(status().isOk())
                .andExpect(jsonPath("$.monthlyPayment").exists());
        });
        
        // Step: Cuando solicito un préstamo sin datos requeridos
        When("^solicito un préstamo sin los datos requeridos$", () -> {
            lastRequestBody = "{}";
            lastResponse = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lastRequestBody));
        });
        
        // Step: Entonces el sistema responde con error de validación
        Then("^el sistema responde con error de validación$", () -> {
            lastResponse.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
        });
    }
}