package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrincipalTest {

	@Test
	public void testCorreSimulacion1Usuario() {
		Principal principal = new Principal(638,1000,100);
		long T1 = System.currentTimeMillis();
		principal.motorDeSimulacion();
		long T2 = System.currentTimeMillis();
		
		System.out.println(T1);
		System.out.println((double)(T2-T1)/1000);
		
		assertTrue(principal.CantPaquetesOptimo() <= principal.CantPaquetesPromedio());
		assertTrue(principal.CantPaquetesPeor() >= principal.CantPaquetesPromedio());
		System.out.println(principal.CantPaquetesPromedio());
		System.out.println(principal.CantPaquetesPromedioPorUsuario());
	}
	
	@Test
	public void testCorreSimulacion1UsuarioAlbum1Figu() {
		Principal principal = new Principal(1,100,1);
		
		principal.correSimulacion1Usuario();
		
		assertTrue(principal.CantPaquetesOptimo() <= principal.CantPaquetesPromedio());
		assertTrue(principal.CantPaquetesPeor() >= principal.CantPaquetesPromedio());
		assertTrue(principal.CantPaquetesPromedio() == 1);

	}
	
	
}
