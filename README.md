#PROJETO FINAL - SISTEMAS OPERACIONAIS

----- ESPECIFICA��O -----

- Gerenciador de processos: agrupar em quatro n�veis de prioridade
- Gerenciador de mem�ria: garantir que um processo n�o acesse regi�o de mem�ria de outro
- Gerenciador de arquivos: garantir libera��o e aloca��o, assim como uso exclusivo dos dispositivos
- Gerenciador de E/S: criar e deletar arquivos de acordo com o modelo de aloca��o determinado

Filas: valores menores = maiores prioridades
- processos de tempo real (maior pioridade, FIFO sem preemp��o)
- processos de usu�rios (tr�s filas com prioridades distintas e realimenta��o, usar aging para evitar starvation)

- No m�ximo 1000 processos nas filas
- Recomenda-se usar uma filha global (ver figura 1)

Mem�ria: conjunto de blocos contiguos. N�o ser� utilizado swap ou p�ginas.
- fixa de 1024 blocos com 64
- cada bloco tem 1mb

Recursos de E/S:
- scanner
- 2 impressores
- 1 modem
- 2 SATA

- Sem preemp��o
- Processos de tempo real n�o fazem uso
- Cada recurso seja alocado para um proceso por vez

Arquivos:
- criar e deletar
- aloca��o cont�gua
- arquivo deve ser tratado como uma unidade de manipula��o
- first fit
- processos de tempo real podem fazer tudo, at� com processos de terceiros
- entrada � um TXT: cont�m a quantidade total de blocos no disco, a especifica��o dos segmentos ocupados por cada arquivo, as opera��es a serem realizadas por cada processo
- deve mostrar na tela do computador um mapa com a atual ocupa��o do disco, descrevendo quais arquivos est�o em cada bloco, e quais s�o os blocos vazios (identificados por 0)


ESTRUTURA:
- M�dulo de Processos � classes e estruturas de dados relativas ao processo. Basicamente, mant�m informa��es espec�ficas do processo.

- M�dulo de Filas � mant�m as interfaces e fun��es que operam sobre os processos
- M�dulo de Mem�ria � prov� uma interface de abstra��o de mem�ria RAM
- M�dulo de Recurso � trata a aloca��o e libera��o dos recursos de E/S para os processos
- M�dulo de Arquivos � trata as opera��es create e delete sobre os arquivos

INTERFACE
Entrada:

Ler os arquivos .txt: contendo as informa��es dos processos a serem criados e descri��o das opera��es a serem realizadas pelo sistema de arquivos

Processos
- cada linha cont�m as informa��es de um �nico processo
- linha: <tempo de inicializa��o>, <prioridade>, <tempo de processador>, <blocos em mem�ria>, <n�meroc�digo da impressora requisitada>, <requisi��o do scanner>, <requisi��o do modem>, <n�meroc�digo do disco>

Arquivos
- Haver� a quantidade total de blocos no disco, a quantidade de segmentos ocupados, a identifica��o de quais arquivos j� est�o gravados no disco, a localiza��o dos blocos usados por cada arquivo, a identifica��o de qual processo efetuar� cada opera��o, a identifica��o das opera��es, sendo c�digo 0 para criar um arquivo, e c�digo 1 para deletar um arquivo.
Al�m disso, para as opera��es de cria��o deve constar o nome do arquivo a ser criado, e a quantidade de blocos ocupada pelo arquivo. Por outro lado, na opera��o de deletar um arquivo, deve constar apenas o nome do arquivo a ser deletado.

- Linha 1: Quantidade de blocos do disco
- Linha 2: Quantidade de segmentos ocupados no disco (n);
- A partir da Linha 3 at� Linha n + 2: arquivo (a ser identificado por uma letra), n�mero do primeiro bloco gravado, quantidade de blocos ocupados por este arquivo;
- A partir da linha n + 3: cada linha representa uma opera��o a ser efetivada pelo sistema de arquivos do pseudo-SO. Para isso, essas linhas v�o conter: <ID_Processo>, <C�digo_Opera��o>, <Nome_arquivo>, <se_operacaoCriar_numero_blocos>.


Sa�da:
O processo principal � o �despachante", este deve exibir uma mensagem de identifica��o contendo as seguintes informa��es:

- PID (int)
- Prioridade do processo (int)
- Offset da mem�ria (int)
- Quantidade de blocos alocados na mem�ria (int)
- Utiliza��o de impressora (bool)
- Utiliza��o de scanner (bool)
- Utiliza��o de drivers (bool)
- O processo deve exibir alguma mensagem que indique sua execu��o

