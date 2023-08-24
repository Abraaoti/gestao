$(document).ready(function () {


    moment.locale('pt-BR');
    var table = $('#table-processo').DataTable({
        searching: true,
        order: [[1, "asc"]],
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/processos/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'titulo'},
            {data: 'emissao', render:
                        function (emissao) {
                            return moment(emissao).format('L');
                        }
            },
            {data: 'vencimento', render:
                        function (vencimento) {
                            return moment(vencimento).format('L');
                        }
            },
            {data: 'fornecedor'},

            {data: 'valorTotal', render: $.fn.dataTable.render.number('.', ',', 2, 'R$ ')},
            {data: 'categoria'},
            {data: 'arquivo', render: function (a) {
                    return a.length ?
                            a.length + ' arquivo(s)' :
                            'Sem Arquivo';
                },
                title: "Arquivo"

            },
            {data: 'situacao', render: function (data, type) {

                    var status = $.fn.dataTable.render.text().display(data);
                    if (type === 'display') {
                        let color = 'orange';
                        if (data === 'PAGO') {
                            let color = 'green';
                        } else if (data === 'VENCIDA') {
                            color = 'red';
                        }

                        return '<span style="color:' + color + '">' + status + '</span>';
                    }

                    return status;
                }

            },
            {data: 'operador'},
            {data: 'pagamento', render:function (pagamento ) {
                           
                           
                            if (pagamento === null) {
                                return '';
                            } else {

                                return moment(pagamento).format('L');
                            }

                        }
            },

            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-primary btn-sm btn-block" href="/processos/pagar/' +
                            id + '" role="button"><i class="fab fa-cc-mastercard"></i></a>';
                }
            },

            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/processos/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/processos/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
});

