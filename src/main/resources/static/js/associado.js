$(document).ready(function () {
    
    
    moment.locale('pt-BR');
    var table = $('#table-associado').DataTable({
        searching: true,
        order: [[1, "asc"]],
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/associados/datatables/server/associado',
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
            {data: 'matricula'},
            {data: 'cpf'},
            {data: 'rg'},
            {data: 'passaporte'},
            {data: 'mae'},
            {data: 'pai'},
            {data: 'ec'},
            {data: 'genero'},
            {data: 'funcao'},
            {data: 'admissao', render:
                        function (admissao) {
                            return moment(admissao).format('L');
                        }

            },
            {data: 'salario', render: $.fn.dataTable.render.number('.', ',', 2, 'R$ ')},
            {data: 'naturalidade'},
           
          
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/associados/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-primary btn-sm btn-block" href="/associados/novo/endereco/' +
                            id + '" role="button"><i class="fas fa-plus"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-primary btn-sm btn-block" href="/associados/novo/email/' +
                            id + '" role="button"><i class="fas fa-plus"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-primary btn-sm btn-block" href="/associados/novo/telefone/' +
                            id + '" role="button"><i class="fas fa-plus"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/associados/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
});
