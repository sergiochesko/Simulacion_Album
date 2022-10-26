package simuladores;

import static org.junit.Assert.*;

import org.junit.Test;

public class Evento_N_Usuarios_NCTest {

	@Test
	public void test() {
		Evento_N_Usuarios_NC evento = new Evento_N_Usuarios_NC(10,638);
		
		int cantPaquetesComprados = evento.correSimulacion();
		System.out.println("estadisticas");
		System.out.println(evento.getCantPaquetesComprados());
		System.out.println(evento.getCantRepetidas());
		System.out.println(evento.getCantPaquetesCompradosPromUsuario());
		System.out.println(evento.getCantIntercambios());
		
		System.out.println("repetidas por usuario");
		System.out.println(evento._ListaUsuarios.get(0).cantRepetidas());
		System.out.println(evento._ListaUsuarios.get(1).cantRepetidas());
		assertTrue(evento.todosCompletaron());
		
	}
	


}
