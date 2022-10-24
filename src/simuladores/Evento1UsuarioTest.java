package simuladores;

import static org.junit.Assert.*;

import org.junit.Test;

public class Evento1UsuarioTest {

	@Test
	public void testCorreSimulacion() {
		Evento1Usuario evento = new Evento1Usuario(638);
		
		int cantPaquetesComprados = evento.correSimulacion();
		
		
		assertTrue(evento.getAlbumCompleto());
		
		//la cantidad total de figuritas compradas (cant paquetes*5) es igual a la cantidad de figuritas pegadas + repetidas)
		assertTrue(cantPaquetesComprados*5 == 638 + evento.getCantRepetidas());
		
		System.out.println(evento.getCantPaquetesComprados());
		
		
	}

}
