package main;

import simuladores.Evento;
import simuladores.Evento1Usuario;
import simuladores.Evento_N_usuarios;

public class Principal {
	
	private int _CantIteracionesPedidas;
	private int _CantPaquetes_MejorCasoEncontrado;
	private int _cantidadUsuarios;
	private final int _tamanoAlbum;
	private int _CantPaquetes_Promedio;
	private int _CantPaquetes_PromedioPorUsuario;
	
	//conviene crear una interface de simulador para que decida en tiempo de ejecucion. Por ahora avanzo con esto
	private Evento _MejorEvento;
	private Evento _PeorEvento;
	
	public Principal(int tamanoAlbum, int CantIteracionesPedidas, int cantidadUsuarios) {
		
		_tamanoAlbum = tamanoAlbum;
		_CantIteracionesPedidas = CantIteracionesPedidas;
		_CantPaquetes_MejorCasoEncontrado =0;
		_cantidadUsuarios = cantidadUsuarios;
		_CantPaquetes_Promedio = 0;
		_CantPaquetes_PromedioPorUsuario=0;
		
	}
	
	public void motorDeSimulacion() {
		if(_cantidadUsuarios==1) {
			correSimulacion1Usuario();
		}
		else {
			correSimulacionNUsuariosGenerosos();
		}
	}
	
	public void correSimulacion1Usuario() {
		_MejorEvento = new Evento1Usuario(_tamanoAlbum);
		_PeorEvento = _MejorEvento;
		_MejorEvento.correSimulacion();
		
		int contador = _MejorEvento.getCantPaquetesComprados();
		System.out.println(contador);
		
		for(int i=0; i<_CantIteracionesPedidas-1; i++) {
			Evento1Usuario NuevoEvento = new Evento1Usuario(_tamanoAlbum);
			NuevoEvento.correSimulacion();
			System.out.println(NuevoEvento.getCantPaquetesComprados());
			contador= contador + NuevoEvento.getCantPaquetesComprados();
			
			if(NuevoEvento.getCantPaquetesComprados() < _MejorEvento.getCantPaquetesComprados()) {
				_MejorEvento = NuevoEvento;
			}
			if(NuevoEvento.getCantPaquetesComprados() > _PeorEvento.getCantPaquetesComprados()) {
				_PeorEvento = NuevoEvento;
			}
		}
		
		_CantPaquetes_Promedio = Math.round(contador/_CantIteracionesPedidas);
		_CantPaquetes_PromedioPorUsuario = _CantPaquetes_Promedio;
	}
	
	
	/*
	 * Implementar funcion con N usuarios Generosos (Dan repetidas sin pedir nada a cambio)
	 */
	public void correSimulacionNUsuariosGenerosos() {
		
		_MejorEvento = new Evento_N_usuarios(_cantidadUsuarios,_tamanoAlbum);
		_PeorEvento = _MejorEvento;
		
		_MejorEvento.correSimulacion();
		
		int contador = _MejorEvento.getCantPaquetesComprados();
		System.out.println(contador);
		
		for(int i=0; i<_CantIteracionesPedidas-1; i++) {
			Evento_N_usuarios NuevoEvento = new Evento_N_usuarios(_cantidadUsuarios,_tamanoAlbum);
			NuevoEvento.correSimulacion();
			System.out.println(NuevoEvento.getCantPaquetesComprados());
			contador= contador + NuevoEvento.getCantPaquetesComprados();
			
			if(NuevoEvento.getCantPaquetesComprados() < _MejorEvento.getCantPaquetesComprados()) {
				_MejorEvento = NuevoEvento;
			}
			if(NuevoEvento.getCantPaquetesComprados() > _PeorEvento.getCantPaquetesComprados()) {
				_PeorEvento = NuevoEvento;
			}
		}
		
		_CantPaquetes_Promedio = Math.round(contador/_CantIteracionesPedidas);
		_CantPaquetes_PromedioPorUsuario = _CantPaquetes_Promedio/_cantidadUsuarios;
		
	}
	
	/*
	 * Implementar funcion con N usuarios Egoistas (Intercambian figurita por figurita)
	 */
	public void correSimulacionNUsuariosEgoistas() {
		
	}
	
	
	public int CantPaquetesOptimo() {
		return _MejorEvento.getCantPaquetesComprados();
	}
	
	public int CantPaquetesPeor() {
		return _PeorEvento.getCantPaquetesComprados();
	}

	public int CantPaquetesPromedio() {
		return _CantPaquetes_Promedio;
	}
	
	public int CantPaquetesPromedioPorUsuario() {
		return _CantPaquetes_PromedioPorUsuario;
	}

}
