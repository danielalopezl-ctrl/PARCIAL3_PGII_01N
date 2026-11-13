package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    private static final Logger LOG = Logger.getLogger(ProductoTest.class.getName());

    @Test
    @DisplayName("PlatoFuerte: Debe calcular el precio de venta con 20% de incremento")
    public void testPlatoFuertePrecioVenta() {
        LOG.info("Iniciando prueba: testPlatoFuertePrecioVenta");

        PlatoFuerte plato = new PlatoFuerte("PF01", "Churrasco", "Corte fino", 50000, 30, 500, TipoPreparacion.ASADO, true);
        assertEquals(60000.0, plato.calcularPrecioVenta(), 0.001);
    }

    @Test
    @DisplayName("PlatoFuerte: Cumple calidad con peso, tiempo y cocción dentro de los límites")
    public void testPlatoFuerteCumpleCalidad() {
        LOG.info("Iniciando prueba: testPlatoFuerteCumpleCalidad");

        PlatoFuerte plato = new PlatoFuerte("PF01", "Churrasco", "Corte fino", 50000, 30, 500, TipoPreparacion.ASADO, true);
        assertTrue(plato.cumpleCalidad());
    }

    @Test
    @DisplayName("PlatoFuerte: No cumple calidad si la cocción no fue aprobada")
    public void testPlatoFuerteFallaCoccion() {
        LOG.info("Iniciando prueba: testPlatoFuerteFallaCoccion");
        PlatoFuerte plato = new PlatoFuerte("PF02", "Carne", "Normal", 30000, 20, 300, TipoPreparacion.A_LA_PLANCHA, false);
        assertFalse(plato.cumpleCalidad());
    }

    @Test
    @DisplayName("PlatoFuerte: No cumple calidad si el peso es menor a 200g o mayor a 900g")
    public void testPlatoFuerteFallaPeso() {
        LOG.info("Iniciando prueba: testPlatoFuerteFallaPeso");
        PlatoFuerte platoMuyPequeno = new PlatoFuerte("PF03", "Porción mini", "Pequeña", 20000, 15, 150, TipoPreparacion.FRITO, true);
        PlatoFuerte platoMuyGrande = new PlatoFuerte("PF04", "Porción gigante", "Gigante", 80000, 45, 950, TipoPreparacion.ASADO, true);

        assertFalse(platoMuyPequeno.cumpleCalidad());
        assertFalse(platoMuyGrande.cumpleCalidad());
    }

    @Test
    @DisplayName("PlatoFuerte: No cumple calidad si el tiempo de preparación es menor a 10 min o mayor a 60 min")
    public void testPlatoFuerteFallaTiempo() {
        LOG.info("Iniciando prueba: testPlatoFuerteFallaTiempo");
        PlatoFuerte rapido = new PlatoFuerte("PF05", "Express", "Rápido", 20000, 5, 300, TipoPreparacion.FRITO, true);
        PlatoFuerte demorado = new PlatoFuerte("PF06", "Lento", "Lento", 20000, 70, 300, TipoPreparacion.HORNEADO, true);

        assertFalse(rapido.cumpleCalidad());
        assertFalse(demorado.cumpleCalidad());
    }

    @Test
    @DisplayName("Bebida: Debe calcular precio con 10% sin preparación especial")
    public void testBebidaPrecioVentaNormal() {
        LOG.info("Iniciando prueba: testBebidaPrecioVentaNormal");
        // Base 10000 + 10% = 11000
        Bebida bebida = new Bebida("BE01", "Gaseosa", "Natural", 10000, 5, 400, 4.0, TemperaturaServicio.FRIA, false);
        assertEquals(11000.0, bebida.calcularPrecioVenta(), 0.001);
    }

    @Test
    @DisplayName("Bebida: Debe sumar $3.000 si requiere preparación especial")
    public void testBebidaPrecioVentaConEspecial() {
        LOG.info("Iniciando prueba: testBebidaPrecioVentaConEspecial");

        Bebida bebida = new Bebida("BE02", "Cóctel Frappé", "Artesanal", 10000, 8, 400, 3.0, TemperaturaServicio.FRIA, true);
        assertEquals(14000.0, bebida.calcularPrecioVenta(), 0.001);
    }

    @Test
    @DisplayName("Bebida: Cumple calidad fría (0°C a 10°C) y volumen (150 a 1500 ml)")
    public void testBebidaCumpleCalidadFria() {
        LOG.info("Iniciando prueba: testBebidaCumpleCalidadFria");
        Bebida bebida = new Bebida("BE03", "Jugo Frío", "Mora", 6000, 5, 300, 4.0, TemperaturaServicio.FRIA, false);
        assertTrue(bebida.cumpleCalidad());
    }

    @Test
    @DisplayName("Bebida: Cumple calidad caliente (55°C a 85°C) y volumen (150 a 1500 ml)")
    public void testBebidaCumpleCalidadCaliente() {
        LOG.info("Iniciando prueba: testBebidaCumpleCalidadCaliente");
        Bebida cafe = new Bebida("BE04", "Café Americano", "Caliente", 4000, 5, 200, 65.0, TemperaturaServicio.CALIENTE, false);
        assertTrue(cafe.cumpleCalidad());
    }

    @Test
    @DisplayName("Bebida: Falla calidad si la temperatura no coincide con el rango permitido")
    public void testBebidaFallaTemperatura() {
        LOG.info("Iniciando prueba: testBebidaFallaTemperatura");

        Bebida friaTibia = new Bebida("BE05", "Jugo tibio", "Mora", 6000, 5, 300, 15.0, TemperaturaServicio.FRIA, false);

        Bebida cafeFrio = new Bebida("BE06", "Café tibio", "Caliente", 4000, 5, 200, 40.0, TemperaturaServicio.CALIENTE, false);

        assertFalse(friaTibia.cumpleCalidad());
        assertFalse(cafeFrio.cumpleCalidad());
    }

    @Test
    @DisplayName("Bebida: Falla calidad si el volumen es menor a 150 ml o mayor a 1500 ml")
    public void testBebidaFallaVolumen() {
        LOG.info("Iniciando prueba: testBebidaFallaVolumen");
        Bebida muyPoca = new Bebida("BE07", "Shot", "Poco", 3000, 3, 100, 4.0, TemperaturaServicio.FRIA, false);
        Bebida excesiva = new Bebida("BE08", "Galón", "Mucho", 20000, 5, 2000, 4.0, TemperaturaServicio.FRIA, false);

        assertFalse(muyPoca.cumpleCalidad());
        assertFalse(excesiva.cumpleCalidad());
    }

    @Test
    @DisplayName("Postre: Debe calcular precio con 15% sin refrigeración y 20% con refrigeración")
    public void testPostrePreciosVenta() {
        LOG.info("Iniciando prueba: testPostrePreciosVenta");

        Postre brownie = new Postre("PO01", "Brownie", "Chocolate", 10000, 10, 120, 20.0, false);
        assertEquals(11500.0, brownie.calcularPrecioVenta(), 0.001);


        Postre cheesecake = new Postre("PO02", "Cheesecake", "Frutos rojos", 10000, 10, 150, 4.0, true);
        assertEquals(12000.0, cheesecake.calcularPrecioVenta(), 0.001);
    }

    @Test
    @DisplayName("Postre: Cumple calidad con refrigeración (0°C a 6°C) y peso (80g a 500g)")
    public void testPostreCumpleCalidadRefrigerado() {
        LOG.info("Iniciando prueba: testPostreCumpleCalidadRefrigerado");
        Postre cheesecake = new Postre("PO01", "Cheesecake", "Fresa", 12000, 10, 150, 4.0, true);
        assertTrue(cheesecake.cumpleCalidad());
    }

    @Test
    @DisplayName("Postre: Cumple calidad sin refrigeración (15°C a 30°C) y peso (80g a 500g)")
    public void testPostreCumpleCalidadSinRefrigeracion() {
        LOG.info("Iniciando prueba: testPostreCumpleCalidadSinRefrigeracion");
        Postre galleta = new Postre("PO02", "Galleta", "Avena", 5000, 8, 100, 22.0, false);
        assertTrue(galleta.cumpleCalidad());
    }

    @Test
    @DisplayName("Postre: Falla calidad si la temperatura no es la adecuada para su condición")
    public void testPostreFallaTemperatura() {
        LOG.info("Iniciando prueba: testPostreFallaTemperatura");

        Postre cheesecakeCaliente = new Postre("PO03", "Cheesecake", "Fresa", 12000, 10, 150, 10.0, true);

        Postre galletaDerretida = new Postre("PO04", "Galleta", "Avena", 5000, 8, 100, 35.0, false);

        assertFalse(cheesecakeCaliente.cumpleCalidad());
        assertFalse(galletaDerretida.cumpleCalidad());
    }

    @Test
    @DisplayName("Postre: Falla calidad si el peso es menor a 80g o mayor a 500g")
    public void testPostreFallaPeso() {
        LOG.info("Iniciando prueba: testPostreFallaPeso");
        Postre muyLigero = new Postre("PO05", "Bocado", "Mini", 2000, 5, 50, 4.0, true);
        Postre muyPesado = new Postre("PO06", "Torta enorme", "Grande", 40000, 20, 600, 4.0, true);

        assertFalse(muyLigero.cumpleCalidad());
        assertFalse(muyPesado.cumpleCalidad());
    }
}
