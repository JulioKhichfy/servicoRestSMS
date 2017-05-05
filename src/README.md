== README.md ==

Esse Módulo esta configurado para servir na porta 8080, contudo pode ser alterado em application.properties.
Sua responsabilidade é receber/enviar requisições do módulo Client para processar tais dados e envia-los ao módulo que simula a Operadora Móvel de Celular.
O retorno da operadora movel é capturado, e por sua vez é enviado de volta ao Client com o status da sua ação pretendida ( Enviar 1(um) ou mais SMS´s ).
 
responses:
 
"201": description: SMS sent 
"500": description: Internal Server Error 
"405": description: Validation exception 
"404": description: Mobile User not found


## 1: Instalacao ##

	+JAVA
 	
 	O projeto foi desenvolvido e testado com JAVA 1.8.0_131.
 	Caso tenha mais de um JAVA instalada em sua máquina, por favor
 	utilize a ultima versão do java8.
 	
	+ Maven
	
 	O projeto foi desenvolvido com o maven version 3.5.0
 	Certifique-se que tenha o Maven instalado. https://maven.apache.org/install.html
 	  
	+ H2
	
	A versão embarcada no projeto é 1.4.192
	Certifique-se que tenha o banco de dados H2 instalado. http://www.h2database.com/html/download.html
	
 
## 2: Execucao ##

	# Utilizando o MAVEN #
	1- Abra um prompt/terminal e realize o clone do projeto [ git clone https://github.com/JulioKhichfy/servicoRestSMS.git ]
	2- Depois de ter realizado o clone do projeto em sua pasta de sua preferência, acesse a pasta raiz.
	3- Digite mvn package
	4- Acesse a pasta target recem gerada
	5- Digite java -jar servicoSMS-0.0.1-SNAPSHOT.jar
	6- Para certificar que o serviço esta no ar acesse http://localhost:8080 e verifique se exibe "Servico SMS - L I G A D O"
	
	# Utilizando o ECLIPSE #
	1-	Importe o projeto como "Existing Maven Project"
	2- Acesse a pasta raiz do projeto e clique em next.
	3- Procure pela Main do projeto(ServicoSMSApplication) 
	4- Configure uma configuração (Run Configurations)
	5- Na aba "Main" Escolha o 'Project' servicoSMS assim como sua 'Main Class' (br.com.sms.configuracao.ServicoSMSApplication)
	6- Na aba "JRE" Aproveite para verificar se a JRE estará na versão 1.8
	7- Por fim clique na classe Main e execute em 'Run As' como Java Application.
	8- Para certificar que o serviço esta no ar acesse http://localhost:8080 e verifique se exibe "Servico SMS - L I G A D O" 

## 3: Testes unitários ## 
	
	1-É necessário estar 

