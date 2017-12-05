package projetofinal.so;

public class InterfaceUsuario {
	private static Dispatcher dispatcher;

    public static void main( String[] args ){
    	
    	System.out.println("INICIO DO TESTE");
			try {
				dispatcher = Dispatcher.obterInstancia();
			} catch (GerenciaException e) {
				e.printStackTrace();
			}
			dispatcher.executarProcessos();
	
    	
    	System.out.println("Fim da execução");    	
    }
	
}
