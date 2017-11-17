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
	    	System.out.println("LEITURA DO ARQUIVO FEITA");
			System.out.println("Tempo de inicializacao do processo 1: "+lis.getProcessoPorIdentificador('1').getTempoInicializacao());
			System.out.println("Tempo de processador do processo 2: "+lis.getProcessoPorIdentificador('2').getTempoProcessador());
			System.out.println("Qtd de linhas do arquivo: "+opa.getTamanhoArquivo());
			dispatcher = Dispatcher.obterInstancia();
			dispatcher.novosProcessos(lis);
			dispatcher.executarProcessos();
			
		} catch (LeituraArquivoException e) {
			e.printStackTrace();
		} catch (ProcessoInexistenteException e) {
			e.printStackTrace();
		}
    	
    	System.out.println("Fim da execução");    	
    }
	
}
