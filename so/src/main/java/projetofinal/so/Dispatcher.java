package projetofinal.so;

import projetofinal.so.arquivos.GerenciaArquivo;
import projetofinal.so.filas.Escalonador;
import projetofinal.so.filas.GerenciaFila;
import projetofinal.so.memoria.GerenciaMemoria;
import projetofinal.so.memoria.MemoriaRAM;
import projetofinal.so.processos.BancoDeProcessos;
import projetofinal.so.processos.GerenciaProcesso;
import projetofinal.so.processos.Processo;
import projetofinal.so.recursos.GerenciaRecurso;

public class Dispatcher{

	private static Dispatcher instancia;
	
	private MemoriaRAM memoriaDoPC;
	private BancoDeProcessos meusProcessos;
	private GerenciaArquivo gerenciadorArquivo;
	private Escalonador escalonador;
	private GerenciaRecurso gerenciadorRecurso;
	
	private int clock;
	
	private void iniciaGerencia() {
		this.memoriaDoPC = new GerenciaMemoria();
		this.meusProcessos = new GerenciaProcesso(); //TODO: Modifiquei o construtor para receber a lista de processos lidos do arquivo.
		this.gerenciadorArquivo = new GerenciaArquivo();
		this.escalonador = new GerenciaFila();
		this.gerenciadorRecurso = new GerenciaRecurso();
		
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
		
		while(meusProcessos.temNovosProcessos() || !escalonador.vazio()) {
			//Verifica se tem processos para serem criados
			//ou processos no escalonador
			
			do { //Cria todos os processos de já surgiram
				processo = meusProcessos.proximoProcesso(clock);
				
				if (processo != null) {
					if (memoriaDoPC.reservarMemoria(processo.getID(), processo.getBlocosMemoria())) {
						if (!escalonador.escalonarProcesso(processo)) {
							//O Escalonador está cheio
							meusProcessos.excluirProcesso(processo);
						}
					} else {
						meusProcessos.excluirProcesso(processo);
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