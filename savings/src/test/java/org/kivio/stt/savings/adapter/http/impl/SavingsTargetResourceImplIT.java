package org.kivio.stt.savings.adapter.http.impl;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.meecrowave.Meecrowave;
import org.apache.meecrowave.junit5.MonoMeecrowaveConfig;
import org.apache.meecrowave.testing.ConfigurationInject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.kivio.stt.savings.domain.SavingsTarget;
import org.kivio.stt.savings.domain.cmd.AddSavingsTargetCmd;
import org.kivio.stt.savings.domain.payment.Deposit;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@MonoMeecrowaveConfig
@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SavingsTargetResourceImplIT {
    private static final String BASE_URL = "http://localhost:%d/savings";

    @ConfigurationInject
    private Meecrowave.Builder config;

    private URI savingsTargetUri;

    @Test
    @Order(1)
    @DisplayName("Create Savings Target: Should successfully create a savings target with valid input")
    void testCreateSavingsTarget() {
        AddSavingsTargetCmd savingsTarget = new AddSavingsTargetCmd("user1", "Vacation Fund",
                BigDecimal.valueOf(10000), LocalDate.now().plusMonths(22), BigDecimal.valueOf(500));

        final String baseUrl = String.format(BASE_URL, config.getHttpPort());
        final WebClient client = WebClient.create(baseUrl);
        try (Response response = client.header("Content-Type", MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(savingsTarget))) {
            assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
            savingsTargetUri = response.getLocation();
        }
        assertNotNull(savingsTargetUri);
    }

    @Test
    @Order(2)
    @DisplayName("Deposit: Should successfully add multiple transactions to a savings target")
    void testAddTransactions() {
        // Now, add transactions to the created target
        final Deposit deposit1 = new Deposit(BigDecimal.valueOf(2000));
        final Deposit deposit2 = new Deposit(BigDecimal.valueOf(2500));
        final WebClient client = WebClient.create(savingsTargetUri + "/deposit");

        try (Response response1 = client
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.json(deposit1))) {
            assertEquals(Response.Status.ACCEPTED.getStatusCode(), response1.getStatus());
        }

        try (Response response2 = client
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.json(deposit2))) {
            assertEquals(Response.Status.ACCEPTED.getStatusCode(), response2.getStatus());
        }

        // Verify that the transactions have been added
        final WebClient client2 = WebClient.create(savingsTargetUri);
        SavingsTarget updatedTarget = client2.get(SavingsTarget.class);
        assertEquals(BigDecimal.valueOf(4500), updatedTarget.getCurrentSavings());
    }

    @Test
    @Order(3)
    @DisplayName("Edge Case: Invalid Transaction Amount should return 400")
    void testInvalidTransactionAmount() {
        // Attempt to add an invalid transaction (negative amount)
        final Deposit deposit = new Deposit(BigDecimal.valueOf(-100));
        final WebClient client = WebClient.create(savingsTargetUri + "/deposit");

        try (Response invalidTransactionResponse = client
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.json(deposit))) {

            assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), invalidTransactionResponse.getStatus());
        }
    }

    @Test
    @DisplayName("Edge Case: Invalid Savings Target should return 400")
    void testInvalidSavingsTarget() {
        // Try creating a savings target with an invalid target amount (zero)
        AddSavingsTargetCmd invalidTarget = new AddSavingsTargetCmd("user1", "Invalid Fund",
                BigDecimal.ZERO, LocalDate.now().plusMonths(12), BigDecimal.valueOf(500));

        final String baseUrl = String.format(BASE_URL, config.getHttpPort());
        final WebClient client = WebClient.create(baseUrl);
        try (Response invalidResponse = client
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(invalidTarget))) {

            assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), invalidResponse.getStatus());
        }
    }
}