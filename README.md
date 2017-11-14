# Almoxarifado Virtual

Desenvolvimento de um sistema web para administração do almoxarifado da prefeitura universitária da UFCG. 
A documentação encontra-se no [link](https://docs.google.com/document/d/18nQ2cuIIs-PgoCt-6JNp9HPHbzD-M1HATFQc4R1_-nM/edit#).

## Arquitetura
Os arquivos com o design de arquitetura estão na pasta de mesmo nome, em formato .xml e podem ser visualizados através da ferramenta [link](https://www.draw.io/).

## Setup

### 1º Passo
Baixe o [script](https://gist.githubusercontent.com/alessandroliafook/710821d1965bbf5a4217cc2eba029b96/raw/3e20e545e959b3286554814251213c3770419683/postgresql.sh) e execute para instalação do banco de dados.

Finalizada a instalação configure o usuário e o password:
```
sudo -u postgres createuser avirtual -s
sudo -u postgres psql
postgres=# \password avirtual
# Configure o password igual ao usuário
Digite nova senha: avirtual 
Digite-a novamente: avirtual
```

### 2º Passo
Realize a instalação do [pgAdmin4](https://www.pgadmin.org/download/).
Obs. Caso use um sistema operacional ubuntu pode seguir o [tutorial](https://askubuntu.com/questions/831262/how-to-install-pgadmin-4-in-desktop-mode-on-ubuntu) para realizar a instalação do software.

Após a instalação, realize a criação do banco de dados através do software.
Lembre de configurar os nomes como sendo:
Host: localhost
Nome: postgres
Usuario: avirtual
Senha: avirtual
