package simuladores;

import static org.junit.Assert.*;

import org.junit.Test;

public class Evento_N_usuariosTest {

	@Test
	public void test() {
		Evento_N_usuarios evento = new Evento_N_usuarios(10,638);
		
		int cantPaquetesComprados = evento.correSimulacion();
		
		System.out.println(evento.todosCompletaron());
		System.out.println(evento.getCantPaquetesComprados());
	//	System.out.println(evento.getCantIntercambios());
		System.out.println(evento.getCantRepetidas());
		
		System.out.println("album completo");
		System.out.println(evento._ListaUsuarios.get(0).AlbumCompleto());
		System.out.println(evento._ListaUsuarios.get(1).AlbumCompleto());
		System.out.println(evento._ListaUsuarios.get(2).AlbumCompleto());
		System.out.println(evento._ListaUsuarios.get(3).AlbumCompleto());
		System.out.println(evento._ListaUsuarios.get(4).AlbumCompleto());
		
		System.out.println("cant repetidas");
		System.out.println(evento._ListaUsuarios.get(0).cantRepetidas());
		System.out.println(evento._ListaUsuarios.get(1).cantRepetidas());
		System.out.println(evento._ListaUsuarios.get(2).cantRepetidas());
		System.out.println(evento._ListaUsuarios.get(3).cantRepetidas());
		System.out.println(evento._ListaUsuarios.get(4).cantRepetidas());
		
		System.out.println("cant paquetes cada uno");
		System.out.println(evento._ListaUsuarios.get(0).CantSobresComprados());
		System.out.println(evento._ListaUsuarios.get(1).CantSobresComprados());
		System.out.println(evento._ListaUsuarios.get(2).CantSobresComprados());
		System.out.println(evento._ListaUsuarios.get(3).CantSobresComprados());
		System.out.println(evento._ListaUsuarios.get(4).CantSobresComprados());
		assertTrue(evento.todosCompletaron());
		
		//la cantidad total de figuritas compradas (cant paquetes*5) es igual a la cantidad de figuritas pegadas + repetidas)
		
		//assertTrue(cantPaquetesComprados*5 == 638*5 + evento.getCantRepetidas());
	}

}
