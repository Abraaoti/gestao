

$.ajax({
    url: "multiplelinechart",
    success: function (result) {
        var formatteddata = [];
        for (var key in result) {
            var singleObject = {
                name: '',
                data: []
            }
            singleObject.nome = key.toUpperCase();
            for (var i = 0; i < result[key].length; i++) {
                singleObject.data.push(parseInt(result[key][i].sobrenome));
            }
            formatteddata.push(singleObject);
        }

        drawMultipleLineChart(formatteddata);
    }
});

function drawMultipleLineChart(formatteddata) {
    Highcharts.chart('container', {

        title: {
            text: 'RH, 2019-2023'
        },

        subtitle: {
            text: 'Source: cmil.ind.br'
        },

        yAxis: {
            title: {
                text: 'Número de funcionários'
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        },

        plotOptions: {
            series: {
                label: {
                    connectorAllowed: false
                },
                pointStart: 2011
            }
        },

        series: formatteddata,

        responsive: {
            rules: [{
                    condition: {
                        maxWidth: 500
                    },
                    chartOptions: {
                        legend: {
                            layout: 'horizontal',
                            align: 'center',
                            verticalAlign: 'bottom'
                        }
                    }
                }]
        }

    });
}