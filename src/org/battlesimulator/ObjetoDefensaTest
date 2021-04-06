package org.pmoo.proyectoBatalla;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObjetoDefensaTest {
	
	private ObjetoDefensa obj1;
	private ObjetoDefensa obj2;
	private ObjetoDefensa obj3;

	@Before
	public void setUp() throws Exception {
		this.obj1 = new ObjetoDefensa (10);
		this.obj2 = new ObjetoDefensa (5);
		this.obj3 = new ObjetoDefensa (20);
	}

	@After
	public void tearDown() throws Exception {
		this.obj1 = null;
		this.obj2 = null;
		this.obj3 = null;
	}

	@Test
	public void testGetDefensa() {
		assertEquals(this.obj1.getDefensa(), 10);
		assertEquals(this.obj2.getDefensa(), 5);
		assertEquals(this.obj3.getDefensa(), 20);
	}

	@Test
	public void testImprimirObjeto() {
		this.obj1.imprimirObjeto();
		this.obj2.imprimirObjeto();
		this.obj3.imprimirObjeto();
	}

	@Test
	public void testObjetoDefensa() {
		fail("Not yet implemented");
	}

	@Test
	public void testObjeto() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNombre() {
		assertEquals(this.obj1.getNombre(), "Casco");
		assertEquals(this.obj2.getNombre(), "Casco");
		assertEquals(this.obj3.getNombre(), "Casco");
	}

	@Test
	public void testGetAtaque() {
		assertEquals(this.obj1.getAtaque(), 0);
		assertEquals(this.obj2.getAtaque(), 0);
		assertEquals(this.obj3.getAtaque(), 0);
	}

	@Test
	public void testGetCuracion() {
		assertEquals(this.obj1.getCuracion(), 0);
		assertEquals(this.obj2.getCuracion(), 0);
		assertEquals(this.obj3.getCuracion(), 0);
	}

}
