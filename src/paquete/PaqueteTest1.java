package paquete;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import auxiliares.GeneradorPrefijado;
import auxiliares.GeneradorRandom;

public class PaqueteTest1 {

	
	@Test
	public void testPaqueteAdmiteRepetidas() {
		
		int[] figus = {0,1,2,2,1};
		Paquete paquete = generarPaquetePrefijado(figus, 638);
		
		assertEquals(paquete.dameFiguritasEnPaquete()[2],paquete.dameFiguritasEnPaquete()[3]);
		
	}
	
	@Test
	public void testPaqueteAlbumComun() {
		
		Paquete paquete = generarPaqueteAleatorio(638);
		
		assertTrue(todasMenoresAlNro(637,paquete.dameFiguritasEnPaquete()));
		assertTrue(paquete.dameFiguritasEnPaquete().length==5);
		
	}
	
	@Test
	public void testPaqueteAlbumUnaFigurita() {
		
		Paquete paquete = generarPaqueteAleatorio(1);
		
		assertTrue(paquete.dameFiguritasEnPaquete()[0]==0);
		assertTrue(paquete.dameFiguritasEnPaquete()[3]==0);
		assertTrue(paquete.dameFiguritasEnPaquete().length==5);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPaqueteAlbumSinFiguritas() {
		
		@SuppressWarnings("unused")
		Paquete paquete = generarPaqueteAleatorio(0);
		
	}

	
	private boolean todasMenoresAlNro(int figuMasAlta, int[] arrayFigu) {
		for(int i=0;i<arrayFigu.length;i++) {
			if(arrayFigu[i]>figuMasAlta) {
				return false;
			}
		}
		return true;
	}
	
	private Paquete generarPaquetePrefijado(int[] figus, int tam_paquete) {
		
		Paquete.setGenerador(new GeneradorPrefijado(figus));
		return new Paquete(tam_paquete);
		
	}
	
	private Paquete generarPaqueteAleatorio(int tam_paquete) {
		
		Paquete.setGenerador(new GeneradorRandom());
		return new Paquete(tam_paquete);
		
	}
}
