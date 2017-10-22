package projetofinal.so.dados.processo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import projetofinal.so.dados.LeituraArquivoException;
import projetofinal.so.processos.Processo;

public class LeituraArquivoProcesso {

	private String nomeArquivoEntrada;
	private int tamanhoArquivo;
	
	public LeituraArquivoProcesso(String nomeArquivoEntrada) throws LeituraArquivoException{
		this.nomeArquivoEntrada = nomeArquivoEntrada;
		tamanhoArquivo = 0;
		File novoArquivo = new File(nomeArquivoEntrada);
		try {
			if(!novoArquivo.createNewFile()){
				throw new LeituraArquivoException("O arquivo não pôde ser criado");
			}
		}catch(IOException io) {
			throw new LeituraArquivoException("O arquivo não pôde ser criado. Uma exceção fora capturada.",io);
		}

	}
	
	public int getTamanhoArquivo() {
		return this.tamanhoArquivo;
	}
	
	public ListaProcesso lerArquivo() throws LeituraArquivoException{
    	FileReader leitorArquivo = null;
    	BufferedReader bufferArquivo = null;
		String leitura = null;
		int contadorLinhas = 0;
		ListaProcesso lista = new ListaProcesso();
		
   		try {
			leitorArquivo = new FileReader(nomeArquivoEntrada);
			bufferArquivo = new BufferedReader(leitorArquivo);
			
			leitura = bufferArquivo.readLine();
			while(leitura!=null) {
				lista.adicionarProcesso(parseLeitura(leitura));
				leitura = bufferArquivo.readLine();
				contadorLinhas++;
			}
			
			this.tamanhoArquivo = contadorLinhas;
			
		} catch (FileNotFoundException fnf) {
			throw new LeituraArquivoException("O arquivo não pôde ser encontrado",fnf);
		} catch (IOException io) {
			throw new LeituraArquivoException("O arquivo não pôde ser lido",io);
		} catch (ProcessoInexistenteException pi) {
			throw new LeituraArquivoException("Processo nulo", pi);
		}finally{
			try {
				leitorArquivo.close();
				bufferArquivo.close();
			} catch (IOException io) {
				throw new LeituraArquivoException("O arquivo não pôde ser fechado",io);
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
			processoTemporario.setBlocosMemoria(Integer.parseInt(elementosLeitura[3]));
			processoTemporario.setNumeroCodigoImpressora(Integer.parseInt(elementosLeitura[4]));
			processoTemporario.setRequisicaoScanner(Integer.parseInt(elementosLeitura[5]));
			processoTemporario.setRequisicaoModem(Integer.parseInt(elementosLeitura[6]));
			processoTemporario.setNumeroCodigoDisco(Integer.parseInt(elementosLeitura[7]));
			
			return processoTemporario;
		}else {
			throw new LeituraArquivoException("Não se pode realizar parsing de string nula");
		}

	}
	
	
}