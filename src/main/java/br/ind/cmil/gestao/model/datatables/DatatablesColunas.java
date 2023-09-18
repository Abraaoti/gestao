
package br.ind.cmil.gestao.model.datatables;

/**
 *
 * @author abraao
 */
public class DatatablesColunas {

    public static final String[] USUARIOS = {"id", "email", "foto", "ativo", "perfis"};
    public static final String[] PERFIL = {"id", "tp"};
    public static final String[] PESSOA = {"id", "nome", "sobrenome", "nascimento", "endereco", "telefones"};
    public static final String[] HISTORICO_PESSOA = {"id", "nome", "sobrenome", "endereco.id", "endereco.uf", "endereco.cidade", "endereco.bairro", "endereco.rua", "endereco.numero", "endereco.cep", "endereco.complemento", "telefone.id", "telefone.numero", "telefone.tipo"};
    public static final String[] EMPRESA = {"id", "nome", "sobrenome", "nascimento", "cnpj", "ie", "im", "capital", "usuario.email"};
    public static final String[] PESSOAJURIDICA = {"id", "nome", "sobrenome", "nascimento", "cnpj", "ie", "im",};
    public static final String[] FORNECEDORES = {"id", "nome", "sobrenome", "nascimento", "cnpj", "ie", "im"};
    public static final String[] PESSOAFISICA = {"id", "nome", "sobrenome", "nascimento", "salario", "cpf", "rg", "passaporte", "mae", "pai", "ec.desc", "genero.desc", "admissao", "naturalidade"};
    public static final String[] FUNCIONARIOS = {"id", "nome", "sobrenome", "nascimento", "matricula", "salario", "cpf", "rg", "passaporte", "mae", "pai", "ec", "genero", "admissao", "naturalidade", "departamento.nome", "cargo.nome"};

    public static final String[] PRESENCA = {"id", "dataPresenca","horario.id","horatio.horaMinuto","auxiliarAdministrativo.id","funcionario.id","status"};
    public static final String[] PROJETO = {"id", "contrato", "numero", "inicio", "fim", "updatedAt", "responsavel", "seguranca", "gestor", "administrador.id", "administrador.nome"};
    public static final String[] HISTORICO_PRESENCA = {"id", "nome"};
    public static final String[] SOCIO = {"id", "nome"};
    public static final String[] ENDERECO = {"id", "pessoa.nome", "uf", "cidade", "bairro", "rua", "cep", "numero", "complemento"};
    public static final String[] TELEFONE = {"id", "pessoa", "numero", "tipo"};
    public static final String[] EMAIL = {"id", "pessoa", "email"};
    public static final String[] DEPARTAMENTO = {"id", "nome"};
    public static final String[] CATEGORIAS = {"id", "nome"};
    public static final String[] CENTROCUSTO = {"id", "nome"};
    public static final String[] PROCESSOPAGARCONTA = {"id", "emissao", "vencimento", "fornecedor.nome", "documento", "centroCusto.centro", "valor", "qtdparcela", "forma_pagamento", "arquivo", "usuario.email"};
    public static final String[] CONTAPAGAR = {"id", "processoFinanceiro.id", "documento", "valorPagar", "banco", "vencimento", "forma_pagamento", "data_pagamento", "status", "observacao", "total"};
    public static final String[] CARGO = {"id", "nome", "funcionarios"};
    public static final String[] CAMPANHA = {"id", "nome"};
    //public static String[] AVALIACAO = {"id","ruim", "regular", "bom", "data_avaliacao","campanha","centro","auditor"};
    public static final String[] AVALIACAO = {"id", "avaliador", "centro.nome", "campanha.nome", "nota", "data"};
    public static final String[] VALOR = {"id", "ruim", "regular", "bom", "otimo", "excelente"};
    public static final String[] AUDITOR = {"id", "nome", "avaliacoes"};
    public static final String[] COTACAO = {"id", "comprador.nome", "produto.nome", "prazo", "data_cotacao", "qtd"};
    public static final String[] PRODUTO = {"id", "nome", "descricao", "und", "valor", "fornecedor.nome", "categoria.nome"};
    public static final String[] DIRETOR = {"id", "funcionario.nome", "autorizar", "usuario.nome"};
    public static final String[] RH = {"id", "funcionario.nome", "usuario.nome"};
    public static final String[] TECNICO = {"id", "funcionario.nome", "usuario.nome"};
    public static final String[] COMPRADOR = {"id", "funcionario.nome", "usuario.nome"};
    public static final String[] ADMINISTRADOR = {"id", "usuario.nome"};
    public static final String[] SECRETARIO = {"id", "funcionario.nome", "usuario.nome"};
   // public static final String[] DEPENDENTE;
   // public static final String[] IMPRESSORA;
    //public static final String[] IMOBILIZADO;

}
