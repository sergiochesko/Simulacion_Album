package main;

import auxiliares.OpcionesDeSimulacion;
import simuladores.Evento;
import simuladores.Evento1Usuario;
import simuladores.Evento_N_Usuarios_NC;
import simuladores.Evento_N_usuarios;

public class Principal {
	
	private int _CantIteracionesPedidas;
	private int _cantidadUsuarios;
	private final int _tamanoAlbum;
	private int _CantPaquetes_Promedio;
	private int _CantPaquetes_PromedioPorUsuario;
	private OpcionesDeSimulacion _opcion;
	
	//conviene crear una interface de simulador para que decida en tiempo de ejecucion. Por ahora avanzo con esto
	private Evento _MejorEvento;
	private Evento _PeorEvento;
	
	public Principal(int tamanoAlbum, int CantIteracionesPedidas, int cantidadUsuarios, OpcionesDeSimulacion opcion) {
		
		_tamanoAlbum = tamanoAlbum;
		_CantIteracionesPedidas = CantIteracionesPedidas;
		_cantidadUsuarios = cantidadUsuarios;
		_CantPaquetes_Promedio = 0;
		_CantPaquetes_PromedioPorUsuario=0;
		_opcion = opcion;
		
	}
	
	
	public void correSimulacion() {
		
		_MejorEvento = creaEvento();
		_PeorEvento = _MejorEvento;
		_MejorEvento.correSimulacion();
		
		int contador = _MejorEvento.getCantPaquetesComprados();

		
		for(int i=0; i<_CantIteracionesPedidas-1; i++) {
			System.out.println("iteracion: " +i);
			Evento NuevoEvento = creaEvento();
			
			NuevoEvento.correSimulacion(); //el motor de la simulacion

			contador= contador + NuevoEvento.getCantPaquetesComprados();
			
			if(NuevoEvento.getCantPaquetesComprados() < _MejorEvento.getCantPaquetesComprados()) {
				_MejorEvento = NuevoEvento;
			}
			if(NuevoEvento.getCantPaquetesComprados() > _PeorEvento.getCantPaquetesComprados()) {
				_PeorEvento = NuevoEvento;
			}
		}
		
		_CantPaquetes_Promedio = Math.round(contador/_CantIteracionesPedidas);
		_CantPaquetes_PromedioPorUsuario = _CantPaquetes_Promedio / _cantidadUsuarios;
		System.out.println("corrio");
		
	}
		
	public int CantPaquetesOptimo() {
		return _MejorEvento.getCantPaquetesComprados();
	}
	
	public int CantPaquetesOptimoPorUsuario() {
		return _MejorEvento.getCantPaquetesComprados()/_cantidadUsuarios;
	}
	
	public int CantPaquetesPeor() {
		return _PeorEvento.getCantPaquetesComprados();
	}
	
	public int CantPaquetesPeorPorUsuario() {
		return _PeorEvento.getCantPaquetesComprados()/_cantidadUsuarios;
	}

	public int CantPaquetesPromedio() {
		return _CantPaquetes_Promedio;
	}
	
	public int CantPaquetesPromedioPorUsuario() {
		return _CantPaquetes_PromedioPorUsuario;
	}

	private Evento creaEvento() {
		Evento evento;
		if(_opcion == OpcionesDeSimulacion.UN_USUARIO) {
			evento = new Evento1Usuario(_tamanoAlbum);
		}
		else if(_opcion == OpcionesDeSimulacion.N_USUARIOS_COLABORATIVOS){
			evento = new Evento_N_usuarios(_cantidadUsuarios,_tamanoAlbum);
		}
		else {
			evento = new Evento_N_Usuarios_NC(_cantidadUsuarios,_tamanoAlbum);
		}
		
		return evento;
		
	}

	public String toString() {
		
		return "";
	}

}
