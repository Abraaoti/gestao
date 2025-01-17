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
            url: '/frequencias/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'funcionario.nome'},
            {data: 'data', render: function (data) {
                    if (data === null) {
                        return '';
                    } else {

                        return moment(data).format('L');
                    }
                }},
            {data: 'entrada', render: function (entrada) {
                    if (entrada === null) {
                        return '';
                    } else {

                        return moment(entrada, "h:mm:ss").format('HH:mm:ss');
                    }

                }},
            {data: 'intervalo', render: function (intervalo) {
                    if (intervalo === null) {
                        return '';
                    } else {
                        return moment(intervalo, "h:mm:ss").format('HH:mm:ss');
                    }
                }},
            {data: 'retorno', render: function (retorno) {
                    if (retorno === null) {
                        return '';
                    } else {
                        return moment(retorno, "h:mm:ss").format('HH:mm:ss');
                    }
                }},
            {data: 'saida', render: function (saida) {
                    if (saida === null) {
                        return '';
                    } else {

                        return moment(saida, "h:mm:ss").format('HH:mm:ss');
                    }
                }},

            {data: 'entrada', render: function (status) {
                   // var horas_minutos = DataTable.render.moment(status,"h:mm:ss").format('HH:mm:ss');
                    if (status !== null) {
                                        
                        let p = 'PRESENTE';                    

                        return '<span style="color:gray; font-weight: bold;">' + p + '</span>';
                    }
                      
                         let f = 'FALTA';              
                        return '<span style="color:red; font-weight: bold;">' + f + '</span>';

                   
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
                    return '<a class="btn btn-danger btn-sm btn-block" href="/frequencias/delete/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            },
            {orderable: false,
                data: 'funcionario.id',
                "render": function (funcionario_id) {
                    return '<a class="btn btn-info btn-sm btn-block" href="/frequencias/baterPonto/' +
                          funcionario_id + '" role="button"><i class="fas fa-plus"></i></a>';
                }
            }

        ]


    });
});
