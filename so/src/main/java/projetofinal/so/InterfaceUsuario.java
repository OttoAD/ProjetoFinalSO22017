package projetofinal.so;

import projetofinal.so.dados.LeituraArquivoException;
import projetofinal.so.dados.processo.LeituraArquivoProcesso;
import projetofinal.so.dados.processo.ListaProcesso;
import projetofinal.so.processos.ProcessoInexistenteException;

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
