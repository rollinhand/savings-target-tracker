package org.kivio.stt.user.adapter.http;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.meecrowave.Meecrowave;
import org.apache.meecrowave.junit5.MonoMeecrowaveConfig;
import org.apache.meecrowave.testing.ConfigurationInject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@MonoMeecrowaveConfig
@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserResourceIT {
    private static final String BASE_URL = "http://localhost:%d/users";

    @ConfigurationInject
    private Meecrowave.Builder config;

    private URI userUri;

    @Test
    @Order(1)
    void testAddUser() {
        final String baseUrl = String.format(BASE_URL, config.getHttpPort());

        // Testdaten für einen neuen Benutzer
        String jsonBody = "{ \"firstname\": \"Homer\", \"lastname\": \"Simpson\", \"birthdate\": \"1956-05-12\", \"email\": \"homer.simpson@springfield.com\" }";

        WebClient client = WebClient.create(baseUrl);
        Response response = client
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(jsonBody);

        assertNotNull(response);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        userUri = response.getLocation();
        assertNotNull(userUri);
    }

    @Test
    @Order(2)
    void testGetUser() {
        assertNotNull(userUri);

        WebClient client = WebClient.create(userUri);
        Response response = client.get();

        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        // TODO: maybe some more validations
    }

    @Test
    @Order(3)
    void testUpdateUser() {
        assertNotNull(userUri);
        String jsonBody = "{ \"firstname\": \"Marge\", \"lastname\": \"Simpson\", \"birthdate\": \"1956-05-12\", \"email\": \"homer.simpson@springfield.com\" }";

        WebClient client = WebClient.create(userUri);
        Response response = client
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(jsonBody);

        assertNotNull(response);
        assertEquals(Response.Status.ACCEPTED.getStatusCode(), response.getStatus());
        // TODO: maybe some more validations
    }

    @Test
    @Order(4)
    void testDeleteUser() {
        assertNotNull(userUri);

        WebClient client = WebClient.create(userUri);
        Response response = client.delete();

        assertNotNull(response);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    @Order(5)
    void testGetNonExistingUser() {
        assertNotNull(userUri);

        WebClient client = WebClient.create(userUri);
        Response response = client.get();

        assertNotNull(response);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    @Order(5)
    void testDeleteNonExistingUser() {
        assertNotNull(userUri);

        WebClient client = WebClient.create(userUri);
        Response response = client.delete();

        assertNotNull(response);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
}