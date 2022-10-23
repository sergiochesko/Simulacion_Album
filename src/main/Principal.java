package main;

import simuladores.Evento1Usuario;

public class Principal {
	
	private int _CantIteracionesPedidas;
	private int _CantPaquetes_MejorCasoEncontrado;
	private int _cantidadUsuarios;
	private final int _cantFiguritas;
	private int _CantPaquetes_Promedio;
	
	//conviene crear una interface de simulador para que decida en tiempo de ejecucion. Por ahora avanzo con esto
	private Evento1Usuario _MejorEvento;
	private Evento1Usuario _PeorEvento;
	
	public Principal(int cantFiguritas, int CantIteracionesPedidas, int cantidadUsuarios) {
		
		_cantFiguritas = cantFiguritas;
		_CantIteracionesPedidas = CantIteracionesPedidas;
		_CantPaquetes_MejorCasoEncontrado =0;
		_cantidadUsuarios = cantidadUsuarios;
		_CantPaquetes_Promedio = 0;
		
	}
	
	public void correSimulacion1Usuario() {
		_MejorEvento = new Evento1Usuario(_cantFiguritas);
		_PeorEvento = _MejorEvento;
		_MejorEvento.correSimulacion();
		
		int contador = _MejorEvento.getCantPaquetesComprados();
		System.out.println(contador);
		
		for(int i=0; i<_CantIteracionesPedidas-1; i++) {
			Evento1Usuario NuevoEvento = new Evento1Usuario(_cantFiguritas);
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
	}
	
	
	/*
	 * Implementar funcion con N usuarios Generosos (Dan repetidas sin pedir nada a cambio)
	 */
	public void correSimulacionNUsuariosGenerosos() {
		
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

}
