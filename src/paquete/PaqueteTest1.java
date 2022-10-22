package paquete;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class PaqueteTest1 {

	@Test
	public void testPaqueteAlbumComun() {
		
		Paquete paquete = creaPaquete(638);
		
		assertTrue(todasMenoresAlNro(637,paquete.dameFiguritasEnPaquete()));
		assertTrue(paquete.dameFiguritasEnPaquete().length==5);
		
	}
	
	@Test
	public void testPaqueteAlbumUnaFigurita() {
		
		Paquete paquete = creaPaquete(1);
		
		assertTrue(paquete.dameFiguritasEnPaquete()[0]==0);
		assertTrue(paquete.dameFiguritasEnPaquete()[3]==0);
		assertTrue(paquete.dameFiguritasEnPaquete().length==5);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPaqueteAlbumSinFiguritas() {
		
		@SuppressWarnings("unused")
		Paquete paquete = creaPaquete(0);
		
	}


	
	private Paquete creaPaquete(int canfFiguritasAlbum) {
		return new Paquete(canfFiguritasAlbum);
	}
	
	private boolean todasMenoresAlNro(int figuMasAlta, int[] arrayFigu) {
		for(int i=0;i<arrayFigu.length;i++) {
			if(arrayFigu[i]>figuMasAlta) {
				return false;
			}
		}
		return true;
	}
}
