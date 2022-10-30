package auxiliares;

public class GeneradorPrefijado implements Generador{

	private int[] _figuritas;
	private int _indice;
	

	public GeneradorPrefijado(int[] figuritas)
	{
		_figuritas = figuritas;
		_indice = 0;
		
	}

	
	@Override
	public int nextInt(int rango)
	{
		return _figuritas[_indice++];
	}



}
