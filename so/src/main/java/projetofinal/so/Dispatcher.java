package projetofinal.so;

public class Dispatcher{

	private static Dispatcher instancia;

	private void iniciaGerencia() {

		
	}

	
	private Dispatcher() {		
		iniciaGerencia();
	}
	
	public static Dispatcher obterInstancia() {
		if (instancia == null) {
			instancia = new Dispatcher();
		}
		return instancia;
	}
	
}