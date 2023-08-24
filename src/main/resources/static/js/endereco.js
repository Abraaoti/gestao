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
            url: '/enderecos/datatables/server/endereco',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'pessoaEndereco.nome'},
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
                    return '<a class="btn btn-success btn-sm btn-block" href="/enderecos/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/enderecos/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
});
