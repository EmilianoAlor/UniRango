package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scr.Rango;

class TestRangos {

	@Test
	public void PreguntarPunto() {
		Rango rango1 = Rango.RangoCerradoCerrado(2, 8);

		assertTrue(rango1.valorEstaEnRango(6));
	}

	@Test
	public void PreguntarRango() {
		Rango rango1 = Rango.RangoCerradoCerrado(2, 8);
		Rango rango2 = Rango.RangoCerradoCerrado(3, 7);
		Rango rango3 = Rango.RangoAbiertoAbierto(3, 7);
		Rango rango4 = Rango.RangoAbiertoCerrado(3, 7);

		assertTrue(rango1.rangoNuevoEstaAdentro(rango2));

		assertFalse(rango3.rangoNuevoEstaAdentro(rango2));
		assertFalse(rango4.rangoNuevoEstaAdentro(rango2));
	}

	@Test
	public void PreguntarInterseccion() {
		Rango rango1 = Rango.RangoCerradoCerrado(3, 8);
		Rango rango2 = Rango.RangoAbiertoAbierto(1, 2);

		assertFalse(rango1.hayInterseccion(rango2));

		Rango rango3 = Rango.RangoAbiertoAbierto(9, 10);

		assertFalse(rango1.hayInterseccion(rango3));

		Rango rango4 = Rango.RangoAbiertoAbierto(1, 4);

		assertTrue(rango1.hayInterseccion(rango4));

		Rango rango5 = Rango.RangoAbiertoAbierto(6, 7);

		assertTrue(rango1.hayInterseccion(rango5));

		Rango rango6 = Rango.RangoCerradoCerrado(3, 8);

		assertTrue(rango1.hayInterseccion(rango6));

		Rango rango7 = Rango.RangoCerradoCerrado(2, 3);

		assertTrue(rango1.hayInterseccion(rango7));
	}

	@Test
	public void ComprobarIgualdad() {
		Rango rango1 = Rango.RangoCerradoCerrado(1, 2);
		Rango rango2 = Rango.RangoCerradoCerrado(1, 2);
		Rango rango3 = Rango.RangoAbiertoAbierto(1, 2);

		assertTrue(rango1.equals(rango2));
		assertFalse(rango1.equals(rango3));
	}

	@Test
	public void ComprobarString() {
		Rango rango1 = Rango.RangoAbiertoAbierto(5, 7);
		Rango rango2 = Rango.RangoCerradoCerrado(5, 7);

		assertEquals("(5.0 7.0)", rango1.toString());
		assertEquals("[5.0 7.0]", rango2.toString());
	}

	@Test
	public void rangoTotal() {
		Rango rango1 = Rango.RangoAbiertoAbierto(1, 4);
		assertEquals("(1.0 4.0)", rango1.toString());
		
		Rango total1 = Rango.DevolverRangoTotal();
		assertEquals("(1.0 4.0)", total1.toString());
		
		Rango rango2 = Rango.RangoCerradoCerrado(5, 7);
		assertEquals("[5.0 7.0]", rango2.toString());
		Rango total2 = Rango.DevolverRangoTotal();
		assertEquals("(1.0 7.0]", total2.toString());
		
	}
	
	@Test
	public void probarInterseccion() {
		Rango rango1 = Rango.RangoAbiertoAbierto(1, 4);
		Rango rango2 = Rango.RangoCerradoAbierto(2, 6);
		
		Rango rango3 = rango1.DevolverIntercepsion(rango2);
		
		assertEquals("[2.0 4.0)", rango3.toString());
		
	}
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
