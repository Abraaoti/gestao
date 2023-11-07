
$(document).ready(function () {

    $.get("/estatistica", function (data) {
        if (!("erro" in data)) {

            $('#funcionarios').html(data.iFuncionarioCount);
            //$('#conta_pagar').html(data.iContaPagarCount);
            //$('#usuarios').html(data.iUsuarioCount);
            //$('#processo').html(data.iProcessoFinanceiroCount);


        } else {

            // limpa_processoFinanceiro();
        }
    });

});