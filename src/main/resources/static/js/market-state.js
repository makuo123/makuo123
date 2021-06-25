$(function() {
    getMap('gdMap');
    houseSell();
    housePrice();
    marketState();
    kucunchart();

    adaptation();
})

window.onresize = function() {
    adaptation()
}

function adaptation() {

    var w = window.innerWidth;
    var h = window.innerHeight
    var nw = 1920,
        nh = 1000.2;
    var left, top, scale;
    scale = w / nw;
    left = (nw * scale - nw) / 2;
    scale2 = h / nh
    top = -(nh - nh * scale2) / 2;

    document.getElementById('main').setAttribute('style', 'transform: scale(' + scale + ',' + scale2 + ');left:' + left + 'px;top:' + top + 'px;position:absolute;');
}
//住宅销售情况
function houseSell() {
    var chartBox = document.getElementById('houseSell');
    var myChart = echarts.init(chartBox);
    get('sqlQuery/getHouseAreaCityAddion.do', function(res) { //与服务器连接成功 ,获取本年住宅销售面积、销售额、预售面积和非住宅销售面积、销售额

        var dataArr = [];
        var month = new Date();
        var year = month.getFullYear();
        month.setMonth(month.getMonth() + 1, 1); //获取到当前月份,设置月份
        for (var i = 0; i < 12; i++) {
            month.setMonth(month.getMonth() - 1); //每次循环一次 月份值减1
            var m = month.getMonth() + 1;
            m = m < 10 ? "0" + m : m;
            dataArr.push(month.getFullYear() + "-" + m + "月");
        }
        //console.log(dataArr);

        let list1 = Object.values(res.data);

        list1 = list1.map(item => {
            item = (Math.round(Number(item) / 10000 * 100) / 100).toFixed(2)
            return item
        });
        console.log('tag1', list1)
        myChart.setOption(option = {
            title: {
                text: '2017年以来商品房销售面积累记同比增速',
                x: 'center',
                top: 5,
                textStyle: {
                    color: '#fff',
                    fontSize: 14
                }
            },
            tooltip: {
                trigger: 'axis',
            },
            legend: {
                x: 'center',
                top: 25,
                textStyle: {
                    color: '#ddd',
                    fontSize: 10
                }
            },
            grid: {
                top: '20%',
                left: '3%',
                right: '2%',
                bottom: '5%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                axisLine: {
                    lineStyle: {
                        color: '#999'
                    }
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#999',
                    }
                },
                axisLabel: {
                    interval: 0,
                    rotate: '40',
                    margin: '10',
                },
                data: dataArr
            },
            yAxis: {
                type: 'value',
                axisLine: {
                    lineStyle: {
                        color: '#999'
                    }
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#999',
                    }
                }
            },
            series: [
                // {
                //     name: '全国增速(%)',
                //     type: 'line',
                //     data: [820, 932, 901, 934, 1290, 1330, 1320, 800, 1500, 655, 755, 877, 458, 874, 796],
                //     itemStyle: {
                //         color: '#589CD7'
                //     },
                //     label: {
                //         normal: {
                //             show: true,
                //             position: 'top'
                //         }
                //     }
                // },
                {
                    name: '全市增速(%)',
                    data: list1,
                    type: 'line',
                    itemStyle: {
                        color: '#F1833D'
                    },
                    label: {
                        normal: {
                            show: true,
                            position: 'bottom'
                        }
                    }
                }
            ]
        });

        $(window).resize(function() {
            myChart.resize();
        })
    });

}
// 住宅价格情况
function housePrice() {
    var myChart = echarts.init(document.getElementById('housePrice'));

    get('sqlQuery/getHouseAvaragePrice.do', function(res) {
        get('sqlQuery/getStockHouseAvaragePrice.do', function(res2) {
            var dataArr = [];
            var month = new Date();
            var year = month.getFullYear();
            month.setMonth(month.getMonth() + 1, 1); //获取到当前月份,设置月份
            for (var i = 0; i < 12; i++) {
                month.setMonth(month.getMonth() - 1); //每次循环一次 月份值减1
                var m = month.getMonth() + 1;
                m = m < 10 ? "0" + m : m;
                dataArr.push(month.getFullYear() + "-" + m + "月");
            }
            let list1 = Object.values(res.data)
            let list2 = Object.values(res2.data)
            myChart.setOption(option = {
                title: {
                    text: '2017年6月以来全市住宅价格走势',
                    x: 'center',
                    top: 5,
                    textStyle: {
                        color: '#fff',
                        fontSize: 14
                    }
                },
                tooltip: {
                    trigger: 'axis',
                },
                legend: {
                    x: 'center',
                    top: 25,
                    textStyle: {
                        color: '#ddd',
                        fontSize: 10
                    }
                },
                grid: {
                    top: '20%',
                    left: '3%',
                    right: '2%',
                    bottom: '5%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    axisLine: {
                        lineStyle: {
                            color: '#999'
                        }
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: '#999',
                        }
                    },
                    axisLabel: {
                        interval: 0,
                        rotate: '40',
                        margin: '10',
                    },
                    data: dataArr
                },
                yAxis: {
                    type: 'value',
                    axisLine: {
                        lineStyle: {
                            color: '#999'
                        }
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: '#999',
                        }
                    }
                },
                series: [{
                        name: '商品房住宅均价',
                        type: 'line',
                        data: list1,
                        itemStyle: {
                            color: '#589CD7'
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        }
                    },
                    {
                        name: '存量房住宅均价',
                        data: list2,
                        type: 'line',
                        itemStyle: {
                            color: '#F1833D'
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'bottom'
                            }
                        }
                    }
                ]
            });
        });
        $(window).resize(function() {
            myChart.resize();
        });
    });
}
// 市场供求情况
function marketState() {
    var myChart = echarts.init(document.getElementById('marketStateChart'));
    var dataArr = [];
    var month = new Date();
    var year = month.getFullYear();
    month.setMonth(month.getMonth(), 1); //获取到当前月份,设置月份
    for (var i = 0; i < 12; i++) {
        month.setMonth(month.getMonth() - 1); //每次循环一次 月份值减1
        var m = month.getMonth() + 1;
        m = m < 10 ? "0" + m : m;
        dataArr.push(month.getFullYear() + "-" + m + "月");
    }
    var option = {
        title: {
            text: '2017年以来全市新建商品住宅供销比走势',
            x: 'center',
            top: 5,
            textStyle: {
                color: '#fff',
                fontSize: 14
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        legend: {
            data: ['住宅批准预售面积', '住宅网签面积', '供销比'],
            x: 'center',
            top: 25,
            textStyle: {
                color: '#ddd',
                fontSize: 10
            }
        },
        grid: {
            top: '20%',
            left: '3%',
            right: '2%',
            bottom: '5%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: dataArr,
            axisPointer: {
                type: 'shadow'
            },
            axisLine: {
                lineStyle: {
                    color: '#999'
                }
            },
            axisLabel: {
                interval: 0,
                rotate: '40',
                margin: '10',
            },
        }],
        yAxis: [{
                type: 'value',
                name: '单位:万平方米',
                axisLine: {
                    lineStyle: {
                        color: '#999'
                    }
                },
            },
            {
                type: 'value',
                name: '供销比',
                axisLine: {
                    lineStyle: {
                        color: '#999'
                    }
                },
                splitLine: {
                    show: false,
                }
            }
        ],
        series: [{
                name: '住宅批准预售面积',
                type: 'bar',
                itemStyle: {
                    color: '#58ACE7'
                },
                data: []
            },
            {
                name: '住宅网签面积',
                type: 'bar',
                itemStyle: {
                    color: '#EA5694'
                },
                data: []
            },
            {
                name: '供销比',
                type: 'line',
                itemStyle: {
                    color: '#BFBFCB'
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                yAxisIndex: 1,
                data: []
            }
        ]
    };

    function myChart_chart(myChart, option) {
        let data1 = [];
        let data2 = [];
        let data3 = [];
        get('sqlQuery/getLastestYearHousePreArea.do', function(res) {
            data1 = Object.values(res.data)

            data1 = data1.map(item => {
                    item = (Math.round(item / 10000 * 100) / 100).toFixed(2)
                    return item
                })
                //console.log('data1', data1)
            myChart.setOption({
                series: [{
                        name: '住宅批准预售面积',
                        type: 'bar',
                        itemStyle: {
                            color: '#58ACE7'
                        },
                        data: data1
                    },
                    {
                        name: '住宅网签面积',
                        type: 'bar',
                        itemStyle: {
                            color: '#EA5694'
                        },
                        data: data2
                    },
                    {
                        name: '供销比',
                        type: 'line',
                        itemStyle: {
                            color: '#BFBFCB'
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        yAxisIndex: 1,
                        data: data3
                    }
                ]
            });
        });
        get('sqlQuery/getLastestYearHouseNetArea.do', function(res) {
            data2 = Object.values(res.data)
            data2 = data2.map(item => {
                    item = (Math.round(item / 10000 * 100) / 100).toFixed(2)
                    return item
                })
                //console.log('data2', data2)
            myChart.setOption({
                series: [{
                        name: '住宅批准预售面积',
                        type: 'bar',
                        itemStyle: {
                            color: '#58ACE7'
                        },
                        data: data1
                    },
                    {
                        name: '住宅网签面积',
                        type: 'bar',
                        itemStyle: {
                            color: '#EA5694'
                        },
                        data: data2
                    },
                    {
                        name: '供销比',
                        type: 'line',
                        itemStyle: {
                            color: '#BFBFCB'
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        yAxisIndex: 1,
                        data: data3
                    }
                ]
            });
        });
        get('sqlQuery/getLastestYearSupplySale.do', function(res) {
            data3 = res.data.map(item => {
                    item = (Math.round(Number(item.tg) / Number(item.xs) * 10000) / 100).toFixed(2)
                    return item
                })
                //console.log('data3', data3)
            myChart.setOption({
                series: [{
                        name: '住宅批准预售面积',
                        type: 'bar',
                        itemStyle: {
                            color: '#58ACE7'
                        },
                        data: data1
                    },
                    {
                        name: '住宅网签面积',
                        type: 'bar',
                        itemStyle: {
                            color: '#EA5694'
                        },
                        data: data2
                    },
                    {
                        name: '供销比',
                        type: 'line',
                        itemStyle: {
                            color: '#BFBFCB'
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        yAxisIndex: 1,
                        data: data3
                    }
                ]
            });
        });
        myChart.setOption(option);
    }
    myChart_chart(myChart, option);
    $(window).resize(function() {
        myChart.resize();
    })
}



// 库存去化周期
function kucunchart() {
    var dataArr = [];
    var month = new Date();
    var year = month.getFullYear();
    month.setMonth(month.getMonth() + 1, 1); //获取到当前月份,设置月份
    for (var i = 0; i < 12; i++) {
        month.setMonth(month.getMonth() - 1); //每次循环一次 月份值减1
        var m = month.getMonth() + 1;
        m = m < 10 ? "0" + m : m;
        dataArr.push(month.getFullYear() + "年" + m + "月");
    }
    var myChart = echarts.init(document.getElementById('kucun'));
    var option = {
        title: {
            text: '2017年以来全市商品房去化周期走势',
            x: 'center',
            top: 5,
            textStyle: {
                color: '#fff',
                fontSize: 14
            }
        },
        legend: {
            data: ['住宅去化周期(月)', '非住宅去化周期'],
            x: 'center',
            top: '25px',
            textStyle: {
                color: '#6784BA'
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        xAxis: {
            data: dataArr,
            type: 'category',
            axisLine: {
                lineStyle: {
                    color: '#999'
                }
            },
            axisLabel: {
                interval: 0,
                rotate: '40',
                margin: '10',
            },
        },
        yAxis: [{
            type: 'value',

            axisLine: {
                lineStyle: {
                    color: '#999'
                }
            },
            splitLine: {
                lineStyle: {
                    color: ['#0A2748']
                }
            }
        }, ],
        series: [{
                name: '住宅去化周期(月)',
                type: 'bar',
                itemStyle: {
                    color: '#58ACE7'
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: []
            },
            {
                name: '非住宅去化周期',
                type: 'bar',
                itemStyle: {
                    color: '#F37C2B'
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: []
            }
        ]
    }


    function myChart_chart(myChart, option) {
        let data1 = [];
        let data2 = [];
        get('sqlQuery/getLastestYearHouseDecCycle.do', function(res) {

            let a1 = Math.round(res.data.kcmj / res.data.DYMJ202003 * 100) / 100
            let a2 = Math.round(res.data.kcmj / res.data.DYMJ202002 * 100) / 100
            let a3 = Math.round(res.data.kcmj / res.data.DYMJ202001 * 100) / 100
            let a4 = Math.round(res.data.kcmj / res.data.DYMJ201912 * 100) / 100
            let a5 = Math.round(res.data.kcmj / res.data.DYMJ201911 * 100) / 100
            let a6 = Math.round(res.data.kcmj / res.data.DYMJ201910 * 100) / 100
            let a7 = Math.round(res.data.kcmj / res.data.DYMJ201909 * 100) / 100
            let a8 = Math.round(res.data.kcmj / res.data.DYMJ201908 * 100) / 100
            let a9 = Math.round(res.data.kcmj / res.data.DYMJ201907 * 100) / 100
            let a10 = Math.round(res.data.kcmj / res.data.DYMJ201906 * 100) / 100
            let a11 = Math.round(res.data.kcmj / res.data.DYMJ201905 * 100) / 100
            let a12 = Math.round(res.data.kcmj / res.data.DYMJ201904 * 100) / 100

            data1.push(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12)


            // data1 = Object.values(res.data)
            // data1 = data1.map(item => {
            //     item = Math.round(item / 10000 * 100) / 100
            //     return item
            // })
            myChart.setOption({
                series: [{
                        name: '住宅去化周期(月)',
                        type: 'bar',
                        itemStyle: {
                            color: '#58ACE7'
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        data: data1
                    },
                    {
                        name: '非住宅去化周期',
                        type: 'bar',
                        itemStyle: {
                            color: '#F37C2B'
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        data: data2
                    }
                ]
            });
        });
        get('sqlQuery/getLastestYearNonHouseDecCycle.do', function(res) {
            // data2 = Object.values(res.data)
            // data2 = data2.map(item => {
            //         item = Math.round(item / 10000 * 100) / 100
            //         return item
            //     })
            //console.log('data2', data2)
            let a1 = Math.round(res.data.kcmj / res.data.DYMJ202003 * 100) / 100
            let a2 = Math.round(res.data.kcmj / res.data.DYMJ202002 * 100) / 100
            let a3 = Math.round(res.data.kcmj / res.data.DYMJ202001 * 100) / 100
            let a4 = Math.round(res.data.kcmj / res.data.DYMJ201912 * 100) / 100
            let a5 = Math.round(res.data.kcmj / res.data.DYMJ201911 * 100) / 100
            let a6 = Math.round(res.data.kcmj / res.data.DYMJ201910 * 100) / 100
            let a7 = Math.round(res.data.kcmj / res.data.DYMJ201909 * 100) / 100
            let a8 = Math.round(res.data.kcmj / res.data.DYMJ201908 * 100) / 100
            let a9 = Math.round(res.data.kcmj / res.data.DYMJ201907 * 100) / 100
            let a10 = Math.round(res.data.kcmj / res.data.DYMJ201906 * 100) / 100
            let a11 = Math.round(res.data.kcmj / res.data.DYMJ201905 * 100) / 100
            let a12 = Math.round(res.data.kcmj / res.data.DYMJ201904 * 100) / 100

            data2.push(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12)
            myChart.setOption({
                series: [{
                        name: '住宅去化周期(月)',
                        type: 'bar',
                        itemStyle: {
                            color: '#58ACE7'
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        data: data1
                    },
                    {
                        name: '非住宅去化周期',
                        type: 'bar',
                        itemStyle: {
                            color: '#F37C2B'
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        data: data2
                    }
                ]
            });
        });

        myChart.setOption(option);
    }
    myChart_chart(myChart, option);
    $(window).resize(function() {
        myChart.resize();
    })
}
//地图
function getMap(elemId) {
    $.ajax({
        url: '../static/mapjson/srs.json',
        type: 'get',
        dataType: 'json',
        success: function(res) {
            var option = {
                title: {
                    text: '上饶市',
                    textStyle: {
                        color: '#fff',
                    }
                },
                tooltip: {
                    trigger: 'item',
                    showDelay: 0,
                    transitionDuration: 0.2,
                    formatter: function(params) {
                        var value = (params.value + '').split('.');
                        value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,');
                        return params.seriesName + '<br/>' + params.name + ': ' + value;
                    }
                },
                visualMap: {
                    left: 'right',
                    min: 5000,
                    max: 500000,
                    inRange: {
                        color: ['#57A777', '#7CA28F', '#76B996', '#68AAAE', '#27516B', '#96BCD2', '#5D9ABF', '#70B5A1', '#63AAD8', '#8DBBD6', '#62A9D8', '#3D7395', '#336482', '#27516B']
                    },
                    text: ['High', 'Low'], // 文本，默认为数值文本
                    calculable: true
                },
                series: [{
                    name: '上饶市',
                    type: 'map',
                    zoom: 1.20,
                    map: '上饶市',
                    itemStyle: {
                        borderColor: '#fff',
                        borderWidth: '3',
                        emphasis: {
                            label: {
                                show: true,
                                color: '#fff',
                            }
                        }
                    },
                    label: {
                        normal: {
                            show: true,
                            color: '#fff',
                        },
                        emphasis: {
                            show: true,
                            color: '#fff'
                        }
                    },
                    data: [{
                            name: '信州区',
                            value: 30000
                        },
                        {
                            name: '广丰区',
                            value: 13500
                        },
                        {
                            name: '广信区',
                            value: 14000
                        },
                        {
                            name: '德兴市',
                            value: 20000
                        },
                        {
                            name: '玉山县',
                            value: 12000
                        },
                        {
                            name: '铅山县',
                            value: 23000
                        },
                        {
                            name: '横峰县',
                            value: 20000
                        },
                        {
                            name: '弋阳县',
                            value: 10000
                        },
                        {
                            name: '余干县',
                            value: 40000
                        },
                        {
                            name: '鄱阳县',
                            value: 16000
                        },
                        {
                            name: '万年县',
                            value: 20000
                        },
                        {
                            name: '婺源县',
                            value: 52000
                        },
                    ]
                }]
            };

            echarts.registerMap('上饶市', res);
            var chart = echarts.init(document.getElementById(elemId));
            chart.setOption(option);
            $(window).resize(function() {
                chart.resize();
            })
        }
    })
}