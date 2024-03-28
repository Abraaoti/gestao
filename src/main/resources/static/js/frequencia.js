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
            url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/pt-BR.json'
        },
        ajax: {
            url: '/frequencias/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'funcionario.nome'},
            {data: 'data', render:function (data) { return moment(data).format('L');} },
            {data: 'entradaManha', render:function (entradaManha) { return moment(entradaManha).format('L');} },
            {data: 'saidaManha', render:function (saidaManha) { return moment(saidaManha).format('L');} },
            {data: 'entradaTarde', render:function (entradaTarde) { return moment(entradaTarde).format('L');} },
            {data: 'saidaTarde', render:function (saidaTarde) { return moment(saidaTarde).format('L');} },
            {data: 'entradaExtra', render:function (entradaExtra) { return moment(entradaExtra).format('L');} },
            {data: 'saidaExtra', render:function (saidaExtra) { return moment(saidaExtra).format('L');} },
          
             {data: 'status', render: function (data, type) {

                    var st = $.fn.dataTable.render.text().display(data);
                    if (type === 'display') {
                        let color = 'gray';
                        if (data === 'PRESENTE') {
                            color = 'green';
                        } else if (data === 'FALTA') {
                            color = 'red';
                        }

                        return '<span style="color:' + color + '; font-weight: bold;">' + st + '</span>';
                    }

                    return status;
                }

             
            },
               {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/frequencias/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/frequencias/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }

        ]
       
    
    });
});
