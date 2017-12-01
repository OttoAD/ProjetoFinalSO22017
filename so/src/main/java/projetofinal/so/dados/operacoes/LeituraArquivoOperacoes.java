package projetofinal.so.dados.operacoes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import projetofinal.so.arquivos.ListaArquivos;
import projetofinal.so.dados.LeituraArquivoException;

public class LeituraArquivoOperacoes {

	private String nomeArquivoEntrada;
	
	public LeituraArquivoOperacoes(String nomeArquivoEntrada){
		this.nomeArquivoEntrada = nomeArquivoEntrada;
	}

	public int[] lerDadosDisco() throws LeituraArquivoException {
		FileReader leitorArquivo = null;
    	BufferedReader bufferArquivo = null;
		int[] dadosIniciais = new int[2];
		
		try {
			leitorArquivo = new FileReader(nomeArquivoEntrada);
			bufferArquivo = new BufferedReader(leitorArquivo);
			
			dadosIniciais[0] = Integer.parseInt(bufferArquivo.readLine());
			dadosIniciais[1] = Integer.parseInt(bufferArquivo.readLine());
			
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
		return dadosIniciais;
	}

	public ListaArquivos lerArquivos() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListaOperacoes lerOperacoes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
