$(document).ready(function () {
    moment.locale('pt-BR');
    var table = $('#table-telefone').DataTable({
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
            url: '/telefone/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'pessoa.nome'},
            {data: 'numero'},
            {data: 'tipo'},
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/telefone/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/telefone/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            },
            {orderable: false,
                data: 'pessoa.id',
                "render": function (id) {
                    return '<a class="btn btn-primary btn-sm btn-block" href="/telefone/pessoa/' +
                            id + '" role="button"><i class="fa fa-phone"></i></a>';
                }
            }
        ]
    });
});
