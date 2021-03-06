package projetofinal.so.dados.processo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import projetofinal.so.dados.LeituraArquivoException;
import projetofinal.so.processos.Processo;

public class LeituraArquivoProcesso {

	private String nomeArquivoEntrada;
	private int tamanhoArquivo;
	
	public LeituraArquivoProcesso(String nomeArquivoEntrada){
		this.nomeArquivoEntrada = nomeArquivoEntrada;
		tamanhoArquivo = 0;

	}
	
	public int getTamanhoArquivo() {
		return this.tamanhoArquivo;
	}
	
	/*Agora adiciona ordenado*/
	public ListaProcesso lerArquivo() throws LeituraArquivoException{
    	FileReader leitorArquivo = null;
    	BufferedReader bufferArquivo = null;
		String leitura = null;
		int contadorLinhas = 0; //Jonas alterou para char pois o valor 'X' deve representar memoria livre
		ListaProcesso lista = new ListaProcesso();
		Processo proc = null;
   		try {
			leitorArquivo = new FileReader(nomeArquivoEntrada);
			bufferArquivo = new BufferedReader(leitorArquivo);
			
			leitura = bufferArquivo.readLine();
			while(leitura!=null) {
				proc = parseLeitura(leitura);
				proc.setID(contadorLinhas);
				lista.adicionarProcessoListaOrdenado(proc);
				leitura = bufferArquivo.readLine();
				contadorLinhas++;
			}
			
			this.tamanhoArquivo = contadorLinhas;
			//System.out.println("Tamanho final do arquivo de processos: "+this.tamanhoArquivo);
			
		} catch (FileNotFoundException fnf) {
			throw new LeituraArquivoException("O arquivo " + nomeArquivoEntrada + " não pôde ser encontrado",fnf);
		} catch (IOException io) {
			throw new LeituraArquivoException("O arquivo " + nomeArquivoEntrada + " não pôde ser lido",io);
		}finally{
			try {
				leitorArquivo.close();
				bufferArquivo.close();
			} catch (IOException io) {
				throw new LeituraArquivoException("O arquivo " + nomeArquivoEntrada + " não pôde ser fechado",io);
			}

		}
    	return lista;
	}
	
	/*<tempo de inicialização>, <prioridade>, <tempo de processador>, <blocos em memória>, <númerocódigo
	da impressora requisitada>, <requisição do scanner>, <requisição do modem>, <númerocódigo
	do disco>
	Exemplo: 2, 0, 3, 64, 0, 0, 0, 0
	*/
	private Processo parseLeitura(String leitura) throws LeituraArquivoException{
		if(leitura != null) {
			String[] elementosLeitura = leitura.split(", ");
			Processo processoTemporario = new Processo();
			
			processoTemporario.setTempoInicializacao(Integer.parseInt(elementosLeitura[0]));
			processoTemporario.setPrioridade(Integer.parseInt(elementosLeitura[1]));
			processoTemporario.setTempoProcessador(Integer.parseInt(elementosLeitura[2]));
			processoTemporario.setTempoRestante(processoTemporario.getTempoProcessador());
			processoTemporario.setBlocosMemoria(Integer.parseInt(elementosLeitura[3]));
			processoTemporario.setNumeroCodigoImpressora(Integer.parseInt(elementosLeitura[4]));
			processoTemporario.setRequisicaoScanner(Integer.parseInt(elementosLeitura[5]) == 0 ? false : true);
			processoTemporario.setRequisicaoModem(Integer.parseInt(elementosLeitura[6]) == 0 ? false : true);
			processoTemporario.setNumeroCodigoDisco(Integer.parseInt(elementosLeitura[7]));
			
			return processoTemporario;
		}else {
			throw new LeituraArquivoException("Não se pode realizar parsing de string nula");
		}

	}
	
	
}
