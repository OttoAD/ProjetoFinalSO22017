package projetofinal.so;

import java.util.logging.Logger;

import projetofinal.so.arquivos.GerenciaArquivo;
import projetofinal.so.dados.processo.ListaProcesso;
import projetofinal.so.filas.Escalonador;
import projetofinal.so.filas.GerenciaFila;
import projetofinal.so.memoria.GerenciaMemoria;
import projetofinal.so.memoria.MemoriaInsuficienteException;
import projetofinal.so.memoria.MemoriaRAM;
import projetofinal.so.processos.BancoDeProcessos;
import projetofinal.so.processos.GerenciaProcesso;
import projetofinal.so.processos.Processo;
import projetofinal.so.processos.ProcessoInexistenteException;
import projetofinal.so.recursos.GerenciaRecurso;

public class Dispatcher{

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
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
	
	public void novosProcessos(ListaProcesso processos) {
		this.meusProcessos = new GerenciaProcesso(processos);
	}
	
	public void executarProcessos() {
		clock = 0;
		
		LOGGER.info("Cheguei aqui");
		
		while(meusProcessos.temNovosProcessos() || !escalonador.vazio()) {
			//Verifica se tem processos para serem criados
			//ou processos no escalonador
			LOGGER.info("Clock " + clock);
			criarProcesso();
			
			executarProcesso();
			
		}
		
		//memoriaDoPC.mostrarMemoria();
		
	}
	
	private void criarProcesso() {
		Processo processo = null;
		int indiceProcesso = 0;
		int posicaoMemoria = 0;
		
		do { //Cria todos os processos que já surgiram
			
			processo = meusProcessos.proximoProcesso(clock, indiceProcesso);
			
			if (processo != null) {
				
				LOGGER.info("Criando o processo " + processo.getID());
				try {
					posicaoMemoria = memoriaDoPC.encontraMemoria(processo.getBlocosMemoria(), processo.getPrioridade());
				} catch (MemoriaInsuficienteException e) {
					e.printStackTrace();
					try {
						meusProcessos.excluirProcesso(processo);						
					} catch (ProcessoInexistenteException e2) {
						e2.printStackTrace();
					}
				}
				
				if (posicaoMemoria == -1) { //Não há memória disponível no momento
					indiceProcesso++;
					LOGGER.info("Memoria insuficiente para o processo " + processo.getID());
					continue;
				}
				
				LOGGER.info("Memoria disponivel na posicao " + posicaoMemoria);
				memoriaDoPC.alocaMemoria(processo.getID(), posicaoMemoria, processo.getBlocosMemoria(), processo.getPrioridade());
				try {
					meusProcessos.moverProcesso(processo);
				} catch (ProcessoInexistenteException e2) {
					e2.printStackTrace();
				}
					
				try {
					escalonador.escalonarProcesso(processo);
				} catch (Exception e) {
					// TODO: definir exception
					// Escalonador cheio
					try {
						meusProcessos.excluirProcesso(processo);
					} catch (ProcessoInexistenteException e1) {
						e1.printStackTrace();
					}
				}
			}
				
		} while (processo != null);
	}
	
	private void executarProcesso() {
		Processo processo;
		
		processo = escalonador.proximoProcesso();
		if (processo != null) {
			if (processo.getPrioridade() == 0) { //JONAS FEZ ESSE BLOCO DE CODIGO
				clock += escalonador.executarProcesso(processo); //executar processo ate o fim
			} else { 
				clock += escalonador.executarQuantum(processo);
			}
			
			if (processo.getTempoProcessador() == 0) { //esgotou o processo
				memoriaDoPC.desalocarProcesso(processo.getID(), processo.getPrioridade()); //desaloca processo da memoria
				LOGGER.info("Processo "+processo.getID()+" finalizou no clock " +clock);
			}
			else { //mais 'Quantum's serão necessarios
				//TODO: DIMINUIR A PRIORIDADE DO PROCESSO
				escalonador.escalonarProcesso(processo);
			}
		}
		else { //nenhum processo a ser executado
			clock++;
		}
	}	
}