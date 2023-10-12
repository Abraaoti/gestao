
var real_data = /*[[${chartData}]]*/'noValue';
google.charts.load('current', {'packages': ['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawPieChart);
google.charts.setOnLoadCallback(drawColumnChart);
function drawColumnChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Topping');
    data.addColumn('number', 'Slices');
    Object.keys(real_data).forEach(function (key) {
        data.addRow(real_data[key]);
    });
    var options = {
        title: 'Estatísticas do Protal',
        hAxis: {
            title: 'Meses',
        },
        vAxis: {
            title: 'Ver contagem'
        }
    };
    var chart = new google.visualization.ColumnChart(document
            .getElementById('chart_div'));
    chart.draw(data, options);
}
function drawPieChart() {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Topping');
    data.addColumn('number', 'Slices');
    Object.keys(real_data).forEach(function (key) {
        data.addRow(real_data[key]);
    });

    // Set chart options
    var options = {
        title: 'Estatísticas do Protal'
    };
    var chart = new google.visualization.PieChart(document
            .getElementById('piechart'));
    chart.draw(data, options);
}