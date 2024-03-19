$(document).ready(function () {
    moment.locale('pt-BR');
    var table = $('#table-informacao').DataTable({
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
            url: '/pessoa/datatables/informacao',
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
    
          
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/telefones/add/' +
                            id + '" role="button"><i class="material-icons">contact_phone</i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/enderecos/add/' +
                            id + '" role="button"><i class="material-icons">location_city</i></a>';
                }
            }
           
        ]
    });
});
