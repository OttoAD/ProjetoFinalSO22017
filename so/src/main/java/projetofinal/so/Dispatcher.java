package projetofinal.so;

import projetofinal.so.arquivos.GerenciaArquivo;
import projetofinal.so.filas.GerenciaFila;
import projetofinal.so.memoria.GerenciaMemoria;
import projetofinal.so.recursos.GerenciaRecurso;

public class Dispatcher{

	private static Dispatcher instancia;
	/*
	private GerenciaMemoria gerenciadorMemoria;
	private GerenciaProcesso gerenciadorProcesso;
	private GerenciaArquivo gerenciadorArquivo;
	private GerenciaFila gerenciadorFila;
	private GerenciaRecurso gerenciadorRecurso;
	*/
	private void iniciaGerencia() {
		/*gerenciadorMemoria = new GerenciaMemoria();
		gerenciadorProcesso = new GerenciaProcesso();
		gerenciadorArquivo = new GerenciaArquivo();
		gerenciadorFila = new GerenciaFila();
		gerenciadorRecurso = new GerenciaRecurso();*/
		
	}
	//TODO revisar os as declarações acima, implementar interfaces
	
	private Dispatcher() {		
		iniciaGerencia();
	}
	
	public static Dispatcher obterInstancia() {
		if (instancia == null) {
			instancia = new Dispatcher();
		}
		return instancia;
	}
	
}