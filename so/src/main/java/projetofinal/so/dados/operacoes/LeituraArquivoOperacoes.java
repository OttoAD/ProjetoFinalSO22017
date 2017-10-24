package projetofinal.so.dados.operacoes;

import java.io.File;
import java.io.IOException;

import projetofinal.so.dados.LeituraArquivoException;

public class LeituraArquivoOperacoes {

	private String nomeArquivoEntrada;
	
	public LeituraArquivoOperacoes(String nomeArquivoEntrada) throws LeituraArquivoException{
		this.nomeArquivoEntrada = nomeArquivoEntrada;
		File novoArquivo = new File(nomeArquivoEntrada);
		try {
			novoArquivo.createNewFile();
		}catch(IOException io) {
			throw new LeituraArquivoException("O arquivo " + nomeArquivoEntrada + " não pôde ser criado",io);
		}
	}
	
	
	
}
