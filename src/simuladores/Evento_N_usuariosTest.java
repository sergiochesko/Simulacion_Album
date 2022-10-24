package simuladores;

import static org.junit.Assert.*;

import org.junit.Test;

public class Evento_N_usuariosTest {

	@Test
	public void test() {
		Evento_N_usuarios evento = new Evento_N_usuarios(2,50);
		
		int cantPaquetesComprados = evento.correSimulacion();
		
		assertTrue(evento.todosCompletaron());
		
		//la cantidad total de figuritas compradas (cant paquetes*5) es igual a la cantidad de figuritas pegadas + repetidas)
		//assertTrue(cantPaquetesComprados*5 == 638*10 + evento.getCantRepetidas());
		
		System.out.println(evento.todosCompletaron());
		System.out.println(evento.getCantPaquetesComprados());
		System.out.println(evento.getCantPaquetesCompradosPromUsuario());
		System.out.println(evento.getCantRepetidas());
		
		System.out.println("album completo");
		System.out.println(evento._ListaUsuarios.get(0).AlbumCompleto());
		System.out.println(evento._ListaUsuarios.get(1).AlbumCompleto());
		
		
		System.out.println("cant repetidas");
		System.out.println(evento._ListaUsuarios.get(0).cantRepetidas());
		System.out.println(evento._ListaUsuarios.get(1).cantRepetidas());
		
		
		System.out.println("cant paquetes cada uno");
		System.out.println(evento._ListaUsuarios.get(0).CantSobresComprados());
		System.out.println(evento._ListaUsuarios.get(1).CantSobresComprados());
		
		
		
		
	}

}
