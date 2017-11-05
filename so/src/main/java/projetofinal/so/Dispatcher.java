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
		this.meusProcessos = new GerenciaProcesso();
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
			
			criarProcesso();
			
			executarProcesso();
		}
		
		memoriaDoPC.mostrarMemoria();
		
	}
	
	private void criarProcesso() {
		Processo processo;
		int indiceProcesso = 0;
		int posicaoMemoria = 0;
		
		do { //Cria todos os processos que já surgiram
			processo = meusProcessos.proximoProcesso(clock, indiceProcesso);
			
			if (processo != null) {
				
				//TODO: verificar se o processo exige um tamanho de memoria aceitavel
				//Prioridade 0: maximo 64
				//Outras prioridades: maximo 960
				try {
					posicaoMemoria = memoriaDoPC.encontraMemoria(processo.getBlocosMemoria(), processo.getPrioridade());					
				} catch (Exception e) {
					// TODO Definir exceção
					// Se o tamanho da memória não é suficiente
					try {
						meusProcessos.excluirProcesso(processo);						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
				//Não tinha memória disponível
				if (posicaoMemoria == -1) {
					indiceProcesso++;
					continue;					
				}
				
				memoriaDoPC.alocaMemoria(processo.getID(), posicaoMemoria, processo.getBlocosMemoria(), processo.getPrioridade());
					
				try {
					escalonador.escalonarProcesso(processo);
				} catch (Exception e) {
					// TODO: definir exception
					// Escalonador cheio
					meusProcessos.excluirProcesso(processo);
				}
			}
				
		} while (processo != null);
	}
	
	private void executarProcesso() {
		Processo processo;
		
		processo = escalonador.proximoProcesso();
		if (processo != null) {
			if (processo.getPrioridade() == 0) {
				//Executa até acabar
			} else {
				//Executa até acabar o quantum
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