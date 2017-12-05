package projetofinal.so.dados.operacoes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import projetofinal.so.arquivos.Arquivo;
import projetofinal.so.arquivos.ListaArquivos;
import projetofinal.so.dados.LeituraArquivoException;

public class LeituraArquivoOperacoes {

	private String nomeArquivoEntrada;
	private int numeroSegmentos;
	
	public LeituraArquivoOperacoes(String nomeArquivoEntrada){
		this.nomeArquivoEntrada = nomeArquivoEntrada;
	}

	public int[] lerDadosDisco() throws LeituraArquivoException {
		int[] dadosIniciais = new int[2];
		
		try(BufferedReader bufferArquivo = new BufferedReader(new FileReader(nomeArquivoEntrada))){
	
			dadosIniciais[0] = Integer.parseInt(bufferArquivo.readLine());
			dadosIniciais[1] = Integer.parseInt(bufferArquivo.readLine());
			this.numeroSegmentos = dadosIniciais[1];
			
		} catch (FileNotFoundException fnf) {
			throw new LeituraArquivoException("O arquivo " + nomeArquivoEntrada + " não pôde ser encontrado",fnf);
		} catch (IOException io) {
			throw new LeituraArquivoException("O arquivo " + nomeArquivoEntrada + " não pôde ser lido",io);
		}
		
		return dadosIniciais;
	}

	public ListaArquivos lerArquivos() throws LeituraArquivoException { //tem que testar
		String leituraArquivos = null;
		int contador = 2;//assume que já pulou as duas primeiras linhas
		ListaArquivos listaTemporaria = new ListaArquivos();
		try(BufferedReader bufferArquivo = new BufferedReader(new FileReader(nomeArquivoEntrada))){
			
			bufferArquivo.readLine(); //ignora duas primeiras linhas
			bufferArquivo.readLine();
			leituraArquivos = bufferArquivo.readLine();//lê a terceira linha e começa o programa
			while((leituraArquivos != null) && (contador < (this.numeroSegmentos + 2))) {
				contador++;
				listaTemporaria.adicionarArquivo(parseStringArquivo(leituraArquivos));
				leituraArquivos = bufferArquivo.readLine();
			}

		}catch (FileNotFoundException fnf) {
			throw new LeituraArquivoException("O arquivo " + nomeArquivoEntrada + " não pôde ser encontrado",fnf);
		} catch (IOException io) {
			throw new LeituraArquivoException("O arquivo " + nomeArquivoEntrada + " não pôde ser lido",io);
		}
		return listaTemporaria;
	}



	public ListaOperacoes lerOperacoes() throws LeituraArquivoException { // tem que testar
		String leituraOperacoes = null;
		int contador = this.numeroSegmentos + 2;
		ListaOperacoes listaTemporaria = new ListaOperacoes();
		try(BufferedReader bufferArquivo = new BufferedReader(new FileReader(nomeArquivoEntrada))){
			
			for(int i = 0; i < contador;i++) {
				bufferArquivo.readLine();//pula todas as linhas até segmentos + 3
			}
			leituraOperacoes = bufferArquivo.readLine(); //tem que ler aqui ou o for já leu?
			while((leituraOperacoes != null)) {
				contador++;
				listaTemporaria.adicionarOperacao(parseStringOperacao(leituraOperacoes));
				leituraOperacoes = bufferArquivo.readLine(); //verificar se lê no inicio ou no final
			}
		}catch (FileNotFoundException fnf) {
			throw new LeituraArquivoException("O arquivo " + nomeArquivoEntrada + " não pôde ser encontrado",fnf);
		} catch (IOException io) {
			throw new LeituraArquivoException("O arquivo " + nomeArquivoEntrada + " não pôde ser lido",io);
		}
		return listaTemporaria;
	}
	

	private Arquivo parseStringArquivo(String leituraArquivos) throws LeituraArquivoException {
		if(leituraArquivos != null) {
			String[] elementosLeitura = leituraArquivos.split(", ");
			Arquivo arq = new Arquivo();
			
			arq.setNomeArquivo(elementosLeitura[0].charAt(0));
			arq.setNumeroBloco(Integer.parseInt(elementosLeitura[1]));
			arq.setTamanho(Integer.parseInt(elementosLeitura[2]));
			
			return arq;
		}else {
			throw new LeituraArquivoException("Não se pode realizar parsing de string nula");
		}
	}
	
	
	private Operacao parseStringOperacao(String leituraOperacoes) throws LeituraArquivoException {
		
		if(leituraOperacoes != null) {
			String[] elementosLeitura = leituraOperacoes.split(", ");
			Operacao op = new Operacao();
			
			op.setIdProcesso(Integer.parseInt(elementosLeitura[0]));
			int criarOuDeletar = Integer.parseInt(elementosLeitura[1]);
			op.setCodigoOperacao(criarOuDeletar);
			op.setNomeArquivo(elementosLeitura[2].charAt(0));
			if(criarOuDeletar == 0) {
				op.setTamanho(Integer.parseInt(elementosLeitura[3]));
			}
			return op;
		}else {
			throw new LeituraArquivoException("Não se pode realizar parsing de string nula");
		}
	}
	
	
	
}
