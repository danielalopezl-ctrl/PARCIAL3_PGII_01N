package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ServicioEntregaTest {

    private static final Logger LOG = Logger.getLogger(ServicioEntregaTest.class.getName());

    @Test
    @DisplayName("RapidoExpress: Debe aceptar recorridos hasta 12 km")
    public void testRapidoExpressAceptaRecorrido() {
        LOG.info("Iniciando prueba: testRapidoExpressAceptaRecorrido");
        RapidoExpress rapido = new RapidoExpress();

        assertTrue(rapido.aceptaRecorrido(5.0));
        assertTrue(rapido.aceptaRecorrido(12.0));
    }

    @Test
    @DisplayName("RapidoExpress: Debe rechazar recorridos mayores a 12 km o menores/iguales a 0")
    public void testRapidoExpressRechazaRecorrido() {
        LOG.info("Iniciando prueba: testRapidoExpressRechazaRecorrido");
        RapidoExpress rapido = new RapidoExpress();

        assertFalse(rapido.aceptaRecorrido(12.1));
        assertFalse(rapido.aceptaRecorrido(0.0));
        assertFalse(rapido.aceptaRecorrido(-3.0));
    }

    @Test
    @DisplayName("RapidoExpress: Debe calcular costo 5000 base + 1500 por km")
    public void testRapidoExpressCalculoCosto() {
        LOG.info("Iniciando prueba: testRapidoExpressCalculoCosto");
        RapidoExpress rapido = new RapidoExpress();


        assertEquals(11000.0, rapido.calcularCosto(4.0), 0.001);
    }

    @Test
    @DisplayName("EntregaSegura: Debe aceptar recorridos hasta 20 km")
    public void testEntregaSeguraAceptaRecorrido() {
        LOG.info("Iniciando prueba: testEntregaSeguraAceptaRecorrido");
        EntregaSegura segura = new EntregaSegura();

        assertTrue(segura.aceptaRecorrido(3.0));
        assertTrue(segura.aceptaRecorrido(20.0));
    }

    @Test
    @DisplayName("EntregaSegura: Debe rechazar recorridos mayores a 20 km o no positivos")
    public void testEntregaSeguraRechazaRecorrido() {
        LOG.info("Iniciando prueba: testEntregaSeguraRechazaRecorrido");
        EntregaSegura segura = new EntregaSegura();

        assertFalse(segura.aceptaRecorrido(20.5));
        assertFalse(segura.aceptaRecorrido(0.0));
        assertFalse(segura.aceptaRecorrido(1.0));
    }

    @Test
    @DisplayName("EntregaSegura: Costo sin recargo cuando la distancia es menor o igual a 5 km")
    public void testEntregaSeguraCostoSinRecargo() {
        LOG.info("Iniciando prueba: testEntregaSeguraCostoSinRecargo");
        EntregaSegura segura = new EntregaSegura();


        assertEquals(12000.0, segura.calcularCosto(5.0), 0.001);
    }

    @Test
    @DisplayName("EntregaSegura: Costo con recargo de $3000 cuando la distancia supera los 5 km")
    public void testEntregaSeguraCostoConRecargo() {
        LOG.info("Iniciando prueba: testEntregaSeguraCostoConRecargo");
        EntregaSegura segura = new EntregaSegura();


        assertEquals(18000.0, segura.calcularCosto(8.0), 0.001);
    }
}
