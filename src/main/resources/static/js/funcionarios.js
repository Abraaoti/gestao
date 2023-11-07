$(document).ready(function () {
    moment.locale('pt-BR');
    var table = $('#table-funcionario').DataTable({
        searching: true,
        order: [[1, "asc"]],
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/pt-BR.json',
        },
        ajax: {
            url: '/funcionarios/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'nome'},
            {data: 'sobrenome'},
            {data: 'nascimento', render:
                        function (nascimento) {
                            return moment(nascimento).format('L');
                        }
            },
            {data: 'cpf'},
            {data: 'rg'},
            {data: 'clt'},
            {data: 'mae'},
            {data: 'pai'},
            {data: 'genero'},

            {data: 'estado_civil'},
            {data: 'naturalidade'},
            {data: 'admissao', render:
                        function (admissao) {
                            return moment(admissao).format('L');
                        }
            },
            {data: 'salario' , render: $.fn.dataTable.render.number('.', ',', 2, 'R$ ')},
            {data: 'cargo.nome'},
            {data: 'departamento.nome'},
            {data: 'lotacao.nome'},

            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/funcionarios/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/funcionarios/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
});
