$(document).ready(function () {
    moment.locale('pt-BR');
    var table = $('#table-frequencia').DataTable({
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
            url: '/frequencia/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'funcionario.nome'},
            {data: 'funcionario.cargo.nome'},
          
            {data: 'data', render:
                        function (nascimento) {
                            return moment(nascimento).format('L');
                        }
            },
             {data: 'status'
             
            },
               {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/frequencia/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/frequencia/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
});
