package org.pmoo.proyectoBatalla;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObjetoCuracionTest {
	
	private ObjetoCuracion obj1;
	private ObjetoCuracion obj2;
	private ObjetoCuracion obj3;
	
	@Before
	public void setUp() throws Exception {
		this.obj1 = new ObjetoCuracion (10);
		this.obj2 = new ObjetoCuracion (5);
		this.obj3 = new ObjetoCuracion (20);
	}

	@After
	public void tearDown() throws Exception {
		this.obj1 = null;
		this.obj2 = null;
		this.obj3 = null;
	}

	@Test
	public void testGetCuracion() {
		assertEquals(this.obj1.getCuracion(), 10);
		assertEquals(this.obj2.getCuracion(), 5);
		assertEquals(this.obj3.getCuracion(), 20);
	}

	@Test
	public void testImprimirObjeto() {
		this.obj1.imprimirObjeto();
		this.obj2.imprimirObjeto();
		this.obj3.imprimirObjeto();
	}

	@Test
	public void testObjetoCuracion() {
		fail("Not yet implemented");
	}

	@Test
	public void testObjeto() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNombre() {
		assertEquals(this.obj1.getNombre(), "Venda");
		assertEquals(this.obj2.getNombre(), "Venda");
		assertEquals(this.obj3.getNombre(), "Venda");
	}

	@Test
	public void testGetAtaque() {
		assertEquals(this.obj1.getAtaque(), 0);
		assertEquals(this.obj2.getAtaque(), 0);
		assertEquals(this.obj3.getAtaque(), 0);
	}

	@Test
	public void testGetDefensa() {
		assertEquals(this.obj1.getDefensa(), 0);
		assertEquals(this.obj2.getDefensa(), 0);
		assertEquals(this.obj3.getDefensa(), 0);
	}

}
