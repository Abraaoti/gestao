$(document).ready(function () {


    moment.locale('pt-BR');
    var table = $('#table-empresa').DataTable({
        searching: true,
        order: [[1, "asc"]],
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/pt-BR.json'
        },
        ajax: {
            url: '/pessoa_juridica/datatables/server',
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
            {data: 'cnpj'},
            {data: 'ie'},
            {data: 'im'},
            {data: 'capital', render: $.fn.dataTable.render.number('.', ',', 2, 'R$ ')}
            ,
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/enderecos/add/' +
                            id + '" role="button"><i class="material-icons">location_city</i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/telefone/add/' +
                            id + '" role="button"><i class="material-icons">phone</i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/pessoa_juridica/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
          
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/pessoa_juridica/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
});
