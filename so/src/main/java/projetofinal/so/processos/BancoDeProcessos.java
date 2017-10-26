package projetofinal.so.processos;

public interface BancoDeProcessos {
	
	/*
	 * Verifica se tem processos que ainda não foram criados
	 * Retorna true se sim,
	 * retorna false caso contrário.
	 * 
	 * OBS(Otávio): O método verifica se a PRIMEIRA prosição da lista possui algum processo ou não. Caso positivo,
	 * retorna true, indicando que há um processo a ser criado. Caso negativo, captura a exceção de processo
	 * inexistente no índice 0 e retorna falso, indicando que não há novos processos a serem criados.
	 * Por quê? A ideia é que ListaProcesso seja apenas uma lista de manipulação em memória após a leitura do arquivo,
	 * sendo assim, não há a necessidade de manter os processos após estes forem criados. Ou seja, é uma lista
	 * temporária que conforme os processos forem sendo criados, eles serão removidos da lista.
	 */
	public boolean temNovosProcessos();
	
	/*
	 * Verifica os processos cujo tempo de inicialização é
	 * menor ou igual ao clock.
	 * Retorna o processo com o menor tempo de inicialização,
	 * se não houverem processos retorna null.
	 * 
	 * OBS(Otávio): Se tem novos processos, então verifica na lista de processos lidos do arquivo qual é o próximo
	 * a ser criado de acordo com o tempo de chegada e prioridade (a depender do algoritmo de escalonamento).
	 * Lembrar que: serão implementados os estados de bloqueado, pronto e executando na outra classe (filas?).
	 */
	public Processo proximoProcesso(int clock);

	/*
	 * Marca o processo como excluído.
	 * 
	 * OBS(Otávio): A ideia é marcar o processo ou realmente excluir da lista de processos lidos do arquivo?
	 * Isso é importante porque muda a lógica dos outros métodos acima.
	 * Eu assumo que o processo é completamente removido. Dessa forma, MUITO CUIDADO,
	 * só tem dois jeitos de verificar se os processos são iguais:
	 * 1 - verificar o campo de identificador. Isso, no momento, não é possível. O identificador é sempre inicializado
	 * com o valor 0 e só é atribuído quando um processo for efetivamente criado. Dá pra mudar mas não sei se é uma boa ideia.
	 * 2 - Verificar se os OBJETOS são iguais (usando o operador ==). Assim, é necessária a garantia de que estamos
	 * sempre manipulando o MESMO objeto e não alguma cópia equivalente deste. 
	 */
	public void excluirProcesso(Processo processo);
	
}
