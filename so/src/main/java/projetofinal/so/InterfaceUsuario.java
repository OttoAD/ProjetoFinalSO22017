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
			LeituraArquivoProcesso opa = new LeituraArquivoProcesso("teste.txt");
			ListaProcesso lis = opa.lerArquivo();
			System.out.println("Qtd de processos: "+opa.getTamanhoArquivo());
			dispatcher = Dispatcher.obterInstancia();
			dispatcher.novosProcessos(lis);
			dispatcher.executarProcessos();
			
		} catch (LeituraArquivoException e) {
			e.printStackTrace();
		}
    	
    	System.out.println("Fim da execução");    	
    }
	
}
