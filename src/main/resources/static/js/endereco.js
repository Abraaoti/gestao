$(document).ready(function () {


    moment.locale('pt-BR');
    var table = $('#table-endereco').DataTable({
        searching: true,
        order: [[1, "asc"]],
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/endereco/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'pessoa.nome'},
            {data: 'uf'},
            {data: 'cidade'},
            {data: 'bairro'},
            {data: 'rua'},
            {data: 'cep'},
            {data: 'numero'},
            {data: 'complemento'},
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/endereco/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/endereco/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
});
