# Gerenciamento-Unidades
#### Contextualização:
- Desenvolva uma aplicação para gerenciamento de unidades financeiras. A interação com o
usuário deverá ser através de interfaces gráficas. Programe diretamente com a API Swing do
Java, sem utilizar ferramentas para geração automática do código da interface gráfica com o
usuário. A aplicação deverá ser capaz de gerenciar diferentes unidades financeiras. O usuário
poderá selecionar a unidade com a qual deseja trabalhar. Os dados deverão ser gravados em
um sistema de armazenamento para que possam ser preservados entre várias execuções da
aplicação.
#### Você deve implementar duas funcionalidades, a saber:
- 1. Administrativo - executado pelo funcionário da unidade, inclui o gerenciamento do
cadastro de clientes e das contas. O controle de acesso a estas funcionalidades deverá
ser habilitado mediante uma senha. O sistema deverá permitir:
- a. Cadastrar uma nova unidade
- b. Selecionar uma unidade já cadastrada para gerenciamento
- c. Uma unidade deverá ter pelo menos as seguintes informações:
- I. Nome da unidade
- II. Senha de acesso
- III. Cadastro de clientes
- IV. Cadastro de contas dos clientes
- d. Deverá existir uma funcionalidade para manutenção dos clientes:
- I. Lista de clientes exibindo os seus dados cadastrais
- II. Cadastrar novos clientes
- II. Excluir clientes, desde que o cliente não seja vinculado a alguma conta
- e. Um cliente deverá ter pelo menos as seguintes informações:
- I. Nome do cliente
- II. CPF
- III. Endereço
- f. O gerenciamento de contas deverá permitir:
- I. Lista de contas exibindo os seus dados cadastrais
- II. Cadastrar uma nova conta
- III. Excluir uma conta
- IV. Alterar uma conta

- 2. Cliente - permitindo a manutenção de contas. As seguintes operações devem ser
suportadas:
- a. Consultar saldo
- b. Realizar saque/deposito
- c. Estas operações poderão ser realizadas pelo cliente mediante o uso de uma
senha de acesso, associada com a conta
- d. A aplicação deverá suportar diferentes tipos de contas corrente:
- I. Conta simples, cujo saldo não pode ficar negativo
- II. Conta poupança, que oferece rendimentos diários sobre o saldo
- e. Cada conta deverá ter:
- I. Um número, que identifica unicamente a conta no banco, e deve ser
gerado automaticamente quando a conta é cadastrada
- II. Um dono, identificado por um número de CPF
- III. Uma senha de acesso
- IV. Um saldo
