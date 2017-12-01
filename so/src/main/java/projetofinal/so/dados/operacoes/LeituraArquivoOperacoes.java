package projetofinal.so.dados.operacoes;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Paths;

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
		int contador = this.numeroSegmentos + 3;
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
	
	//TODO
	private Arquivo parseStringArquivo(String leituraArquivos) {
		Arquivo arq = new Arquivo();
				
		return arq;
	}
	
	//TODO
	private Operacao parseStringOperacao(String leituraOperacoes) {
		Operacao op = new Operacao();
				
		
		return op;
	}
	
	
	
}
