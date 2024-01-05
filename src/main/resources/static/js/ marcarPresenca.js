$(document).ready(function () {
    moment.locale('pt-BR');
    var table = $('#table-marcar-presenca').DataTable({
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
            url: '/funcionarios/marcar',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'nome'},
            {data: 'sobrenome'},
           
            {data: 'cargo.nome'},
          
            {orderable: false,
                data: 'id',
                "render": function (id) { 
                    return '<a class="btn btn btn-outline-info btn-sm btn-block" href="/frequencia/funcionario/' +
                            id + '" role="button"><i class="fa-solid fa-check"></i></a>';
                }
            },
          
           
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
                    return '<a class="btn btn-danger btn-sm btn-block" href="/funcionarios/delete/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
});
