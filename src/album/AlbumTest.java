package album;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import auxiliares.GeneradorPrefijado;
import paquete.Paquete;

public class AlbumTest {

	@Test
	public void testAlbumComun() {
		Album album638 = creaAlbum(638);
		
		assertTrue(album638.cualesFaltan().size()==638);
		assertFalse(album638.albumCompleto());
	}
	
	@Test
	public void testAlbum1Figurita() {
		Album album1 = creaAlbum(1);
		
		assertTrue(album1.cualesFaltan().size()==1);
		assertFalse(album1.albumCompleto());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAlbum0Figurita() {
		
		@SuppressWarnings("unused")
		Album album638 = creaAlbum(0);
		
	}
	
	/*
	 * implementar test de agregar figuritas y llenar album
	 */
	
	@Test
	public void testPegaUnaFigurita() {
		Album album = creaAlbum(638);
		
		album.procesaUnaFigurita(10);
				
		assertTrue(album.YaTieneFigurita(10));
		assertFalse(album.albumCompleto());
		assertFalse(album.YaTieneFigurita(11));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConsultoSiTieneFiguritaFueraDeRango() {
		Album album = creaAlbum(638);
		
		album.procesaUnaFigurita(10);
				
		assertFalse(album.YaTieneFigurita(800));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPegaUnaFiguritaInexistente() {
		Album album = creaAlbum(638);
		
		album.procesaUnaFigurita(800);
	}
	
	@Test
	public void testCompletaAlbum() {
		Album album = creaAlbum(2);
		
		album.procesaUnaFigurita(0);
		album.procesaUnaFigurita(1);
				
		assertTrue(album.YaTieneFigurita(0));
		assertTrue(album.albumCompleto());
		assertTrue(album.cualesFaltan().size()==0);
	}
	
	@Test
	public void testCualesFaltan() {
		Album album = creaAlbum(638);
		
		album.procesaUnaFigurita(15);
		album.procesaUnaFigurita(125);
		
		assertTrue(album.cualesFaltan().size()==636);
		assertFalse(album.cualesFaltan().contains(15));
		assertFalse(album.cualesFaltan().contains(125));
		assertTrue(album.cualesFaltan().contains(100));

	}
	
	@Test
	public void testProcesaPaquete() {
		Album album = creaAlbum(638);
		int[] figus = {0,5,10,11,13};
		
		Paquete paquete = generarPaquetePrefijado(figus, 638);
		album.procesaFiguritas(paquete);
		
		assertTrue(CompruebaSiTieneFiguritas(figus, album));

	}

	@Test
	public void testDevuelveRepetidas() {
		
		Album album = creaAlbum(638);
		
		int[] figus = {0,5,10,11,13};
		Paquete paquete = generarPaquetePrefijado(figus, 638);
		
		album.procesaFiguritas(paquete);
		
		int[] figus2 = {1,5,10,11,12};
		paquete = generarPaquetePrefijado(figus2, 638);
		
		//proceso el paquete y guardo la lista de repetidas (todas estan repetidas)
		List<Integer> repetidas = album.procesaFiguritas(paquete);

		assertTrue(repetidas.size()==3);
		assertTrue(repetidas.get(0)==5);
		assertTrue(repetidas.get(1)==10);
		assertTrue(repetidas.get(2)==11);

	}
	
	
	
	
	private Album creaAlbum(int cantEspacios) {
		return new Album(cantEspacios);
	}
	
	private Paquete generarPaquetePrefijado(int[] figus, int tam_paquete) {
		
		Paquete.setGenerador(new GeneradorPrefijado(figus));
		return new Paquete(tam_paquete);
		
	}
	
	private boolean CompruebaSiTieneFiguritas(int[] figus, Album album) {
		
		for(int i=0;i<figus.length;i++) {
			
			if(!album.YaTieneFigurita(figus[i])) {
				return false;
			}
		}
		
		return true;
		
	}

}
