package auxiliares;

import java.util.Random;

public class GeneradorRandom implements Generador{

	private Random _random;
	
	public GeneradorRandom()
	{
		_random = new Random();
	}

	
	@Override
	public int nextInt(int rango) {

		return _random.nextInt(rango);
		
	}

}
