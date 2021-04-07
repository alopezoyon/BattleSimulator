package org.pmoo.proyectoBatalla;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObjetoAtaqueTest {
	
	private ObjetoAtaque obj1;
	private ObjetoAtaque obj2;
	private ObjetoAtaque obj3;

	@Before
	public void setUp() throws Exception {
		this.obj1 = new ObjetoAtaque (10);
		this.obj2 = new ObjetoAtaque (5);
		this.obj3 = new ObjetoAtaque (20);
	}

	@After
	public void tearDown() throws Exception {
		this.obj1 = null;
		this.obj2 = null;
		this.obj3 = null;
	}

	@Test
	public void testGetAtaque() {
		assertEquals(this.obj1.getAtaque(), 10);
		assertEquals(this.obj2.getAtaque(), 5);
		assertEquals(this.obj3.getAtaque(), 20);
	}

	@Test
	public void testImprimirObjeto() {
		this.obj1.imprimirObjeto();
		this.obj2.imprimirObjeto();
		this.obj3.imprimirObjeto();
	}

	@Test
	public void testObjetoAtaque() {
		fail("Not yet implemented");
	}

	@Test
	public void testObjeto() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNombre() {
		assertEquals(this.obj1.getNombre(), "Hacha");
		assertEquals(this.obj2.getNombre(), "Hacha");
		assertEquals(this.obj3.getNombre(), "Hacha");
	}

	@Test
	public void testGetDefensa() {
		assertEquals(this.obj1.getDefensa(), 0);
		assertEquals(this.obj2.getDefensa(), 0);
		assertEquals(this.obj3.getDefensa(), 0);
	}

	@Test
	public void testGetCuracion() {
		assertEquals(this.obj1.getCuracion(), 0);
		assertEquals(this.obj2.getCuracion(), 0);
		assertEquals(this.obj3.getCuracion(), 0);
	}

}
