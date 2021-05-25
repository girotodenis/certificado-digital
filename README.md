# certificado-digital

https://docs.oracle.com/en/java/javase/11/security/pkcs11-reference-guide1.html#GUID-C4ABFACB-B2C9-4E71-A313-79F881488BB9
	Tabela 5-1 Atributos no arquivo de configuração do provedor PKCS # 11


##allowSingleThreadedModules	
	Valor booleano, padrão: true	
	Se true, permite módulos que suportam apenas acesso de thread único. Módulos de thread único não podem ser usados ​​com segurança de vários consumidores PKCS # 11 no mesmo processo, por exemplo, ao usar Network Security Services (NSS) com SunPKCS11.

##attributes	
	Consulte Configuração de Atributos	
	Especifica atributos PKCS # 11 adicionais que devem ser definidos ao criar objetos-chave PKCS # 11. Isso torna possível acomodar tokens que requerem atributos específicos.
	
##description	
	Descrição desta instância do provedor	
	Especifica a string que o Provider.getInfo()método da instância do provedor retorna. Se nenhuma string for especificada, uma descrição padrão será retornada.
	
##disabledMechanisms	
	Lista entre chaves e espaços em branco separados de mecanismos PKCS # 11 para desativar	
	Especifica a lista de mecanismos PKCS # 11 que esta instância do provedor deve ignorar. O provedor ignora qualquer mecanismo listado, mesmo que seja compatível com o token e o provedor SunPKCS11. Especifique as strings SecureRandome KeyStoredesabilite esses serviços.
	No máximo, você pode especificar um enabledMechanismsou disabledMechanisms. Se você não especificar nenhum dos dois, os mecanismos habilitados serão aqueles com suporte tanto do provedor SunPKCS11 (consulte Algoritmos Suportados do Provedor SunPKCS11 ) e do token PKCS # 11.

##enabledMechanisms	
	Lista delimitada por chaves e separados por espaços em branco de mecanismos PKCS # 11 para ativar	
	Especifica a lista de mecanismos PKCS # 11 que esta instância do provedor deve usar, desde que sejam suportados pelo provedor SunPKCS11 e pelo token PKCS # 11. Todos os outros mecanismos são ignorados. Cada entrada na lista é o nome de um mecanismo PKCS # 11. Aqui está um exemplo que lista dois mecanismos PKCS # 11.
	enabledMechanisms = {  		CKM_RSA_PKCS 		CKM_RSA_PKCS_KEY_PAIR_GEN 	}
	No máximo, você pode especificar um enabledMechanismsou disabledMechanisms. Se você não especificar nenhum dos dois, os mecanismos habilitados serão aqueles com suporte tanto do provedor SunPKCS11 (consulte Algoritmos Suportados do Provedor SunPKCS11 ) e do token PKCS # 11.

##explicitCancel	
	Valor booleano, padrão: true	
	Se true, indica que você deve cancelar explicitamente as operações.

##functionList	
	Nome da função C que retorna a lista de funções PKCS # 11, padrão: C_GetFunctionList	
	Esta opção existe principalmente para os obsoletos Secmod.Module.getProvider() método.

##handleStartupErrors	
	Os valores possíveis: ignoreAll, ignoreMissingLibrart, ou halt; padrão: halt	
	Descreve como lidar com erros durante a inicialização.

##insertionCheckInterval
	Número inteiro em milissegundos, padrão 2000. 
	O valor deve ser maior que 100 ms.	Especifica com que freqüência testar a inserção de token, em milissegundos, se nenhum token estiver presente.

##keyStoreCompatibilityMode	
	Valor booleano, padrão: true	
	Se true, indica que P11Keystore é mais tolerante com os parâmetros de entrada.

##library	
	Nome do caminho da implementação do PKCS # 11	
	Especifica o nome do caminho completo (incluindo extensão) da implementação do PKCS # 11; o formato do nome do caminho depende da plataforma. Por exemplo, /opt/foo/lib/libpkcs11.sopode ser o nome do caminho de uma implementação PKCS # 11 no Linux, enquanto C:\foo\mypkcs11.dll pode ser o nome do caminho no Windows ou /opt/local/lib/libpkcs11.dylibno macOS.
	Nota :
		Para configurar o Mozilla Network Security Services (NSS) como a implementação PKCS # 11, defina este atributo para o caminho completo da biblioteca NSS softokn3.
		Dependendo de sua plataforma, pode ser necessário definir a variável de ambiente LD_LIBRARY_PATH(Linux), PATH (Windows) ou DYLD_LIBRARY_PATH(macOS) para incluir o diretório de fechamento para permitir que o sistema operacional localize as bibliotecas dependentes.

##name	
	Sufixo do nome desta instância do provedor	
	Especifica a string, que é concatenada com o prefixo SunPKCS11-para produzir o nome desta instância do provedor (ou seja, a string retornada por seu Provider.getName()método). Por exemplo, se o nameatributo for "FooAccelerator", o nome da instância do provedor será "SunPKCS11-FooAccelerator".

##nssArgs	
	String citada	
	Especifica uma string de argumento de inicialização especial para o token suave do NSS. Isso é usado ao usar o token de software NSS diretamente, sem o modo secmod.
	
##nssDbMode	Veja a Tabela 5-2	Veja a Tabela 5-2

##nssLibraryDirectory	Veja a Tabela 5-2	Veja a Tabela 5-2

##nssModule	Veja a Tabela 5-2	Veja a Tabela 5-2

##nssNetscapeDbWorkaround	Veja a Tabela 5-2	Veja a Tabela 5-2

##nssOptimizeSpace	Veja a Tabela 5-2	Veja a Tabela 5-2

##nssSecmodDirectory	Veja a Tabela 5-2	Veja a Tabela 5-2

##nssUseSecmod	Veja a Tabela 5-2	Veja a Tabela 5-2

##omitInitialize	
	Valor booleano, padrão: false	
	Se true, então, omita a chamada para oC_Initialize()função. Use apenas se a implementação do PKCS # 11 tiver sido inicializada anteriormente com um C_Initialize() ligar.

##showInfo	
	Valor booleano, padrão: false	
	Se true, então exiba as informações do provedor durante a inicialização. As informações do provedor incluem o nome do provedor e os mecanismos PKCS # 11 suportados.

##slot	
	ID de slot	
	Especifica o ID do slot ao qual esta instância do provedor deve ser associada. Por exemplo, você usaria 1para o slot com a id 1em PKCS # 11. No máximo, um de slotou slotListIndexpode ser especificado. Se nenhum for especificado, o padrão é slotListIndex de 0.

##slotListIndex	
	Índice de slot	
	Especifica o índice de slot ao qual esta instância do provedor deve ser associada. É o índice na lista de todos os slots retornados pela função PKCS # 11 C_GetSlotList. Por exemplo, 0indica o primeiro slot da lista. No máximo, um de slotou slotListIndexpode ser especificado. Se nenhum for especificado, o padrão é slotListIndexde 0.
	
##useEcX963Encoding	
	Valor booleano, padrão: false	
	Indica que a codificação X9.63 para pontos EC é usada ( true) ou que a codificação está envolvida em um ASN.1 OctetString ( false).