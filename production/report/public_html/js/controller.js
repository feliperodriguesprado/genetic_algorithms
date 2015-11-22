var chart, line, position, label, result = window.result, generationList = [];

chart = new Chart();
chart.htmlElement = "div_chart";
chart.chartTitle = "Gráfico de produção de peças por hora do melhor indivíduo por geração";
chart.chartType = "ColumnChart";
chart.curveType = false;
chart.pointSize = 2;
chart.vAxisFormat = "decimal";
chart.hAxisIsLabel = true;
chart.hAxisTitle = "Geração";
chart.vAxisTitle = "Peças/hora";
chart.vGridlinesCount = 6;
chart.vMaxValue = 1000;
chart.vMinValue = 0;
chart.height = 500;
//chart.width = 1000;

var summary = document.getElementById('div_sumarry');

line = new Line('Solução');

result.forEach(function (generation) {

    chart.addLabel(new window.Label(generation.number + 'º'));

    generation.population.individualList.forEach(function (individual) {

        var title, table, tr1, th1, th2, th3, th4, tr2, td1, td2, td3, td4;

        line.addPosition(new Position(undefined, individual.value));

        title = document.createElement('h4');
        title.innerHTML = generation.number + 'º geração - ' + individual.value + ' peças/hora';

        table = document.createElement('table');
        table.style.width='100%';

        tr1 = document.createElement('tr');
        th1 = document.createElement('td');
        th1.innerHTML = 'Matrícula funcionário';
        th2 = document.createElement('td');
        th2.innerHTML = 'Nome funcionário';
        th3 = document.createElement('td');
        th3.innerHTML = 'Código máquina';
        th4 = document.createElement('td');
        th4.innerHTML = 'Nome máquina';
        tr1.appendChild(th1);
        tr1.appendChild(th2);
        tr1.appendChild(th3);
        tr1.appendChild(th4);

        table.appendChild(tr1);
        summary.appendChild(title);
        summary.appendChild(table);

        individual.chromossomeList.forEach(function (chromossome) {
            tr2 = document.createElement('tr');
            
            td1 = document.createElement('td');
            td1.innerHTML = chromossome.employeeId;
            td2 = document.createElement('td');
            td2.innerHTML = chromossome.employeeName;
            td3 = document.createElement('td');
            td3.innerHTML = chromossome.machine.code;
            td4 = document.createElement('td');
            td4.innerHTML = chromossome.machine.description;
            
            tr2.appendChild(td1);
            tr2.appendChild(td2);
            tr2.appendChild(td3);
            tr2.appendChild(td4);
            table.appendChild(tr2);
        });

    });
});

chart.addLine(line);

window.charts.addChart(chart);

window.charts.generateCharts();