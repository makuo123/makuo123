var chartSeries = new Array();
var prevTime = "";

function removeChartAttr() {
    $('#context_chart').removeAttr();
}

function createXYTimeChartOptions(appData) {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    var highchartOptions = {
        chart: {
            type: 'spline',
            animation: Highcharts.svg,
            marginRight: 20,
            events: {
                load: function () {
                    chartSeries = this.series;
                }
            }
        },
        title: {
            text: appData.title
        },
        legend: {
            enabled: true,
            align: 'center',
            verticalAlign: 'bottom',
            x: 0,
            y: 0
        },
        xAxis: {
            title: {
                text: appData.xTitle
            },
            type: 'datetime',
            tickWidth: 1,
            labels: {
                x: 0,
                y: 20,
                style: {
                    color: '#000000'
                },
                formatter: function () {
                    return Highcharts.dateFormat('%H:%M', this.value);//设置时间显示格式
                }
            }
        },
        yAxis: {
            title: {
                text: appData.yTitle//设置y轴标题
            },
            plotLines: [{
                value: 0,
                width: 10,
                color: '#808080'
            }]
        },
        tooltip: {//设置数据点tip效果，
            backgroundColor: '#FCFFC5',
            borderColor: 'black',
            borderRadius: 10,
            borderWidth: 3,
            shadow: true,
            animation: true,
            style: {
                color: "#ff0000",
                fontSize: "12px",
                fontWeight: "blod",
                fontFamily: "Courir new"
            },
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' + 'Time: '
                    + Highcharts.dateFormat('%Y-%m-%d %H:%M', this.x)
                    + '<br/>' + 'Value: '
                    + Highcharts.numberFormat(this.y, 1);
            }
        },
        credits: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: []
    };

    $.each(appData.series, function (index, value) {//这里用于设置显示历史缓冲区（历史数据，数组形式），也决定了数据点显示数目
        highchartOptions.series.push({
            name: value.name,
            data: value.data,
            lineWidth: 4,
        });
    });

    return highchartOptions;
}

function inputXYData2ChartSeries(data_time, data_json_array) {
    if (data_time == prevTime)
        return;
//    var x = (new Date(data_time.replace(/-/g, "/")));
//    x = x.getTime() - x.getSeconds() * 1000;
    $.each(data_json_array, function (index, val) {
        chartSeries[index].addPoint([data_time, val], true, true);
    });
    prevTime = data_time;
}

function test_action() {
    var currentDate = new Date();
    var x = currentDate.getTime() - currentDate.getSeconds() * 1000;
    var test_data = [];
    test_data.push(Math.random());
    test_data.push(Math.random());
    inputXYData2ChartSeries(x, test_data);
}

function test_create_xy_time_chart() {
    var input_json = {
        title: "Test",
        xTitle: "x title",
        yTitle: "y title",
        series: [],
    };
    var currentDate = new Date();
    var currentMinute = currentDate.getTime() - currentDate.getSeconds() * 1000;
    var data1 = [], time = currentMinute, i;
    for (i = -12; i <= 0; i++) {
        data1.push({
            x: time + i * 60000,
            y: Math.random()
        });
    }
    input_json.series.push({
        name: "line1",
        data: data1
    });
    var data2 = [], time = currentMinute, i;
    for (i = -12; i <= 0; i++) {
        data2.push({
            x: time + i * 60000,
            y: Math.random()
        });
    }
    input_json.series.push({
        name: "line2",
        data: data2
    });
    var highchart_options = createXYTimeChartOptions(input_json);
    $('#context_chart').show();
    $('#context_chart').highcharts(highchart_options);
    var chart = $('#context_chart').highcharts();

//加了一条告警线，垂直于Y轴
    chart.yAxis[0].addPlotLine({
        value: 0.5,
        width: 4,
        color: 'red',
        id: 'plot-line-1'
    });
}

$(document).ready(function () {
    test_create_xy_time_chart();
    window.setInterval(test_action, 1 * 60000);

});

















