/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.documientos.resources.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.MountableFile;

/**
 *
 * @author alexo
 */
public class TipoAtributoBDD {

    static Network red = Network.newNetwork();

    static MountableFile war = MountableFile
            .forHostPath(Paths.get("target/Documientos-1.0-SNAPSHOT.war").toAbsolutePath(), 0777);

    static GenericContainer postgres = new PostgreSQLContainer("postgres:13-alpine")
            .withDatabaseName("documentostpi135")
            .withPassword("1234")
            .withUsername("postgres")
            .withInitScript("documientos.sql")
            .withNetwork(red)
            .withNetworkAliases("db");

    //payara/full_pg:6/2024.2
    static GenericContainer payara = new GenericContainer("payara/full_pg:6.2024.2")
            .waitingFor(Wait.forLogMessage(".*deploy AdminCommandApplication deployed with name aplicacion.*", 1))
            .withEnv("POSTGRES_USER", "postgres")
            .withEnv("POSTGRES_PASSWORD", "1234")
            .withEnv("POSTGRES_PORT", "5432")
            .withEnv("POSTGRES_DBNAME", "documentostpi135")
            .dependsOn(postgres)
            .withExposedPorts(8080)
            .withNetwork(red)
            .withCopyToContainer(war, "/opt/payara/aplicacion.war");

    Integer idTipoAtributo;
    Client cliente;
    WebTarget target;

    @When("Se tiene un servidor con la aplicacion desplegada")
    public void inicializar() {
        System.out.println("Inicializar scenario");
        Startables.deepStart(Stream.of(postgres, payara)).join();
        Assertions.assertTrue(payara.isRunning());
        cliente = ClientBuilder.newClient();
        cliente.target(String.format("http://localhost:%d/aplicacion/resources/tipoatributo", payara.getMappedPort(8080)));

    }

    @Then("los usuarios hacen POST enviando un TipoAtributo en formato JSON como payload, y el servidor deberia crear un recurso nuevo y devolver como respuesta un estado {int} con una cabecera location apuntando al recurso recion creado.")
    public void crear(int esperado) {
        System.out.println("crear");
        System.out.println("Esperado " + esperado);

    }
}
