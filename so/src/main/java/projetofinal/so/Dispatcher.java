package projetofinal.so;

import projetofinal.so.arquivos.GerenciaArquivo;
import projetofinal.so.filas.GerenciaFila;
import projetofinal.so.memoria.GerenciaMemoria;
import projetofinal.so.processos.GerenciaProcesso;
import projetofinal.so.processos.Processo;
import projetofinal.so.recursos.GerenciaRecurso;

public class Dispatcher{

	private static Dispatcher instancia;
	
	private GerenciaMemoria gerenciadorMemoria;
	private GerenciaProcesso gerenciadorProcesso;
	private GerenciaArquivo gerenciadorArquivo;
	private GerenciaFila escalonador;
	private GerenciaRecurso gerenciadorRecurso;
	
	private int clock;
	
	private void iniciaGerencia() {
		gerenciadorMemoria = new GerenciaMemoria();
		gerenciadorProcesso = new GerenciaProcesso();
		gerenciadorArquivo = new GerenciaArquivo();
		escalonador = new GerenciaFila();
		gerenciadorRecurso = new GerenciaRecurso();
		
	}
	
	private Dispatcher() {		
		iniciaGerencia();
	}
	
	public static Dispatcher obterInstancia() {
		if (instancia == null) {
			instancia = new Dispatcher();
		}
		return instancia;
	}
	
	public void executarProcessos() {
		Processo processo;
		clock = 0;
		
		while(gerenciadorProcesso.temNovosProcessos() || !escalonador.vazio()) {
			//Verifica se tem processos para serem criados
			//ou processos no escalonador
			
			do { //Cria todos os processos de já surgiram
				processo = gerenciadorProcesso.proximoProcesso(clock);
				
				if (processo != null) {
					if (gerenciadorMemoria.reservarMemoria(processo)) {
						escalonador.escalonarProcesso(processo);
					} else {
						gerenciadorProcesso.excluirProcesso(processo);
					}
				}
			} while (processo != null);
			
			processo = escalonador.proximoProcesso();
			if (processo != null) {
				if (processo.getPrioridade() == 0) {
					//Executa até acabar
				} else {
					//Executa até acabar o quantum
				}
			}
		}
		
	}
	
/*
	Dispatcher iniciado
	
	Ler arquivos txt {
		Se leu um processo {
			adiciona o processo na lista de processos
		}
	Leitura terminada
	}
	
	Iniciar contador do relógio
	
	loop do relógio {
		verifica na lista de processos se tem algum para ser criado
		se tem {
			pede para o gerenciador de memória reservar memória pro processo
			se tem memória {
				salva no processo o endereço da memória
				salva na memória o nome do processo
				//pensar sobre a melhor estratégia
				manda para o escalonador
			} se não tem memória {
				recusa a criação do processo
			}
		}
		
		pede pro escalonador o processo a ser executado
		se for de tempo real {
			Executa todas as instruções
		}
		se for de usuário {
			Executa até acabar o quantum
		}
		
	}
	Sai do loop quando não houverem processos a serem criados
	ou processos no escalonador
	
	
	
	
	
	
	
	
	
	



 */
	
}