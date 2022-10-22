package simuladores;

import static org.junit.Assert.*;

import org.junit.Test;

public class Evento1Test {

	@Test
	public void testCorreSimulacion() {
		Evento1 evento = new Evento1(638);
		
		int cantPaquetesComprados = evento.correSimulacion();
		
		
		assertTrue(evento.getAlbumCompleto());
		
		//la cantidad total de figuritas compradas (cant paquetes*5) es igual a la cantidad de figuritas pegadas + repetidas)
		assertTrue(cantPaquetesComprados*5 == 638 + evento.getCantRepetidas());
		
		
	}

}
