package album;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

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
		Album album = creaAlbum(1);
		
		album.procesaUnaFigurita(0);
				
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
		Paquete paquete = new Paquete(638);
		
		List<Integer> repetidas = album.procesaFiguritas(paquete);

		assertTrue(album.cualesFaltan().size()==(638-(5-repetidas.size())));

	}

	@Test
	public void testDevuelveRepetidas() {
		//creo album de 5 figuritas
		Album album = creaAlbum(5);
		
		//creo paquete al azar
		Paquete paquete = new Paquete(5);
		
		//lleno el album
		album.procesaUnaFigurita(0);
		album.procesaUnaFigurita(1);
		album.procesaUnaFigurita(2);
		album.procesaUnaFigurita(3);
		album.procesaUnaFigurita(4);
		
		//proceso el paquete y guardo la lista de repetidas (todas estan repetidas)
		List<Integer> repetidas = album.procesaFiguritas(paquete);

		assertTrue(repetidas.size()==5);

	}
	
	
	
	
	private Album creaAlbum(int cantEspacios) {
		return new Album(cantEspacios);
	}

}
