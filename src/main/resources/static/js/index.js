function adaptation() {
    var w = document.body.clientWidth;
    var h = document.body.clientHeight;
    var nw = 1920,
        nh = 2464 //1080;2795
    var left, top, scale;
    scale = w / nw;
    left = (nw * scale - nw) / 2;
    top = -(nh - nh * scale) / 2;
    document.getElementById('main').setAttribute('style', 'transform: scale(' + scale + ');left:' + left + 'px;top:' + top + 'px;position:absolute');
}

$(function() {
    getMap('gdMap');
    adaptation();
    var historyData = [{
            value: '3784.74',
            name: '缴存总额'
        },
        {
            value: '2211.95',
            name: '发放贷款总额'
        },
        {
            value: '1572.89',
            name: '个人提取总额'
        },
    ]
    var toyearData = [{
                value: '2',
                name: '人员报警'
            },
            {
                value: '6',
                name: '环境报警'
            },
            {
                value: '2',
                name: '塔吊报警'
            },
            {
                value: '1',
                name: '升降机报警'
            },
            {
                value: '8',
                name: '监控报警'
            },

        ]
        // gjjChart('historyTotal', '历年总览', historyData);
    gjjChart('toyear', '2019', toyearData);

    homeAdmin();
    weixiu();


    // 进度条
    progress("#slider1", 390)
    progress("#slider2", 330)
    progress("#slider3", 150)
        // 数字翻牌
    run("newQys", 0, 30000)
    run("newRys", 0, 40000)
    run("newXms", 0, 35000)
    run("newZzs", 0, 50000)

    run("hQys", 325, 60000)
    run("hRys", 4610, 45000)
    run("hXms", 1230, 30000)
    run("hZzs", 2272, 50000)

    run("zhgdRy", 0, 35000)
    run("zhgdRj", 0, 45000)
    run("zhgdTd", 0, 30000)
    run("zhgdSjj", 0, 50000)
    run("zhgdJk", 0, 50000)


    // 弹框操作
    // $("#phqChange").click(function() {
    //     $("#phqDiv").show();
    //     tableChart();
    // })
    $("#gcxmChange").click(function() {
        $("#gcxmDiv").show();
    })
    $("#keshiguanli").click(function() {
        $("#keshiDiv").show();
    })
    $(".close-btn").click(function() {
        $(".modal-box").hide().removeClass('full');
    })
    $(".fullscreen-btn").click(function() {
        $(".modal-box").toggleClass('full')
        tableChart();
    })
    $(".table-btn").click(function() {
        $(".data-table").show()
        $("#tableChart").hide();
    })
    $(".tj-btn").click(function() {
        $(".data-table").hide()
        $("#tableChart").show();
        tableChart()
    })

})

window.onresize = function() {
        adaptation()
    }
    //地图
function getMap(elemId) {
    $.ajax({
        url: 'static/mapjson/srs.json',
        type: 'get',
        dataType: 'json',
        success: function(res) {
            var option = {
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
                    left: '550',
                    top: '0',
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

// 公积金监管chart图
function gjjChart(elem, name, data) {
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x: 'center',
            top: 1,
            textStyle: {
                color: '#a9c6e8'
            }
        },
        color: ['#15FFCA', '#FECA14', '#1DB9F4', '#c7aef0', '#F1833D'],
        series: [{
            name: name,
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: data,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };
    var myChart = echarts.init(document.getElementById(elem));
    myChart.setOption(option);
    $(window).resize(function() {
        myChart.resize();
    })
}

// 房地产监管chart图
function homeAdmin() {
    var chartBox = document.getElementById('chartBox');
    // 初始化echarts对象
    var myChart = echarts.init(chartBox);
    get('sqlQuery/getHouseAreaSales.do', function(data) { //与服务器连接成功 ,获取本年住宅销售面积、销售额、预售面积和非住宅销售面积、销售额
        //获取到的json数据是个对象
        data.data.ZZMJ = Math.round(data.data.ZZMJ / 10000 * 100) / 100
        data.data.FZZMJ = Math.round(data.data.FZZMJ / 10000 * 100) / 100
        data.data.ZZXSE = Math.round(data.data.ZZXSE / 100000000 * 100) / 100
        data.data.FZZXSE = Math.round(data.data.FZZXSE / 100000000 * 100) / 100
            //console.log(data.data.FZZXSE);
        myChart.setOption(option = {
            legend: {
                x: 'center',
                top: 10,
                textStyle: {
                    color: '#a9c6e8'
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            color: ['#15FFCA', '#FECA14', '#1DB9F4', '#c37ee3'],
            series: [{
                type: 'pie',
                name: '用地供应(公顷)',
                radius: 60,
                center: ['27%', '50%'],
                data: [{
                        value: data.data.ZZMJ,
                        name: '住宅用地供应'
                    },
                    {
                        value: data.data.FZZMJ,
                        name: '商服用地供应'
                    }
                ]
            }, {
                type: 'pie',
                name: '开发投资(亿元)',
                radius: 60,
                center: ['72%', '50%'],
                data: [{
                        value: data.data.ZZXSE,
                        name: '住宅计划销售额'
                    },
                    {
                        value: data.data.FZZXSE,
                        name: '非住宅计划销售额'
                    }
                ]
            }]
        });

        $(window).resize(function() {
            myChart.resize();
        });
    });
};
// 维修资金chart图
function weixiu() {
    var myChart = echarts.init(document.getElementById('weixiuChart'));

    get('queryWyjg/getJsnUse.do', function(res) {
        let LJYEA = Math.round(res.data.LJYEA / 100000000 * 100) / 100
        let LJYEB = Math.round(res.data.LJYEB / 100000000 * 100) / 100
        let LJYEC = Math.round(res.data.LJYEC / 100000000 * 100) / 100
        let JCA = Math.round(res.data.JCA / 100000000 * 100) / 100
        let JCB = Math.round(res.data.JCB / 100000000 * 100) / 100
        let JCC = Math.round(res.data.JCC / 100000000 * 100) / 100
        let ZQA = Math.round(res.data.ZQA / 100000000 * 100) / 100
        let ZQB = Math.round(res.data.ZQB / 100000000 * 100) / 100
        let ZQC = Math.round(res.data.ZQC / 100000000 * 100) / 100

        myChart.setOption(option = {
            title: {
                text: '近三年维修资金情况',
                x: 'center',
                textStyle: {
                    color: '#a9c6e8'
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                top: 25,
                textStyle: {
                    color: '#a9c6e8'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [{
                type: 'value',
                name: '单位：亿元',
                nameLocation: 'center',
                axisLine: {
                    lineStyle: {
                        color: '#a9c6e8'
                    }
                },
            }],
            yAxis: [{
                type: 'category',
                axisTick: {
                    show: false
                },
                data: ['2017', '2018', '2019'],
                axisLine: {
                    lineStyle: {
                        color: '#a9c6e8'
                    }
                },
            }],
            color: ['#DAC345', '#5596FC', '#F09404'],
            series: [{
                    name: '累记余额',
                    type: 'bar',
                    label: {
                        normal: {
                            show: true,
                            position: 'inside'
                        }
                    },
                    data: [LJYEC, LJYEB, LJYEA]
                },
                {
                    name: '缴存',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true
                        }
                    },
                    data: [JCC, JCB, JCA]
                },
                {
                    name: '支取',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'left'
                        }
                    },
                    data: [-ZQC, -ZQB, -ZQA]
                }
            ]
        })
    });
    $(window).resize(function() {
        myChart.resize();
    })
}

// tablechart
function tableChart() {
    option = {
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
            data: ['任务总数(万户)', '总改造数(万户)', '总投资额(亿元)'],
            x: 'center',
            top: 20
        },
        grid: {
            top: '10%',
            left: '5%',
            right: '5%',
            bottom: '10%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: ['2010', '20phqDiv', '2012', '2013', '2014', '2015', '2016', '2017', '2018'],
            axisPointer: {
                type: 'shadow'
            },
            axisLine: {
                lineStyle: {
                    color: '#999'
                }
            }
        }],
        yAxis: [{
                type: 'value',
                name: '万户',
                axisLine: {
                    lineStyle: {
                        color: '#999'
                    }
                },
            },
            {
                type: 'value',
                name: '亿元',
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
                name: '任务总数(万户)',
                type: 'bar',
                itemStyle: {
                    color: '#00BDD7'
                },
                data: [800, 1000, 1200, 1400, 1600, 1800, 2000, 2200, 2400]
            },
            {
                name: '总改造数(万户)',
                type: 'bar',
                itemStyle: {
                    color: '#0090D7'
                },
                data: [1000, 1200, 1500, 1700, 1900, 2200, 2400, 2600, 2800]
            },
            {
                name: '总投资额(亿元)',
                type: 'line',
                itemStyle: {
                    color: '#E8B5E2'
                },
                yAxisIndex: 1,
                data: [0.56, 0.55, 0.62, 0.60, 0.53, 0.54, 0.65, 0.63, 0.48]
            }
        ]
    };
    var myChart = echarts.init(document.getElementById('tableChart'));
    myChart.setOption(option);
    myChart.resize();
}

// 数字翻牌
function run(elem, num, date) {
    var numVal = num;
    $("#" + elem).rollNumDaq({
        deVal: numVal,
        className: 'new-num'
    });
    setInterval(function() {
        numVal++;
        $("#" + elem).rollNumDaq({
            deVal: numVal,
            className: 'new-num'
        });
    }, date)
}
(function($, window, document) {
    "use strict";
    var defaults = {
        deVal: 0, //传入值
        className: '', //样式名称
        digit: '' //默认显示几位数字
    };

    function rollNumDaq(obj, options) {
        this.obj = obj;
        this.options = $.extend(defaults, options);
        this.init = function() {
            this.initHtml(obj, defaults);
        }
    }
    rollNumDaq.prototype = {
        initHtml: function(obj, options) {
            var strHtml = '<ul class="' + options.className + ' num-box">';
            var valLen = options.digit || (options.deVal + '').length;
            if (obj.find('.' + options.className).length <= 0) {
                for (var i = 0; i < valLen; i++) {
                    strHtml += '<li class="num-item"><div class="item-box"><span class="num0">0</span> <span class="num1">1</span> <span class="num2">2</span> <span class="num3">3</span> <span class="num4">4</span><span class="num5">5</span> <span class="num6">6</span> <span class="num7">7</span> <span class="num8">8</span> <span class="num9">9</span><span class="num0">0</span> <span class="num1">1</span> <span class="num2">2</span> <span class="num3">3</span> <span class="num4">4</span><span class="num5">5</span> <span class="num6">6</span> <span class="num7">7</span> <span class="num8">8</span> <span class="num9">9</span></div></li>';
                }
                strHtml += '</ul>';
                obj.html(strHtml);
            }
            this.scroNum(obj, options);
        },
        scroNum: function(obj, options) {
            var number = options.deVal;
            var $item_box = obj.children('.' + options.className).find('.item-box');
            var h = $('.num-item').height();
            $item_box.css('transition', 'all .5s ease-in-out');
            var numberStr = number.toString();
            if (numberStr.length <= $item_box.length - 1) {
                var tempStr = '';
                for (var a = 0; a < $item_box.length - numberStr.length; a++) {
                    tempStr += '0';
                }
                numberStr = tempStr + numberStr;
            }

            var numberArr = numberStr.split('');
            $item_box.each(function(i, item) {
                setTimeout(function() {
                    $item_box.eq(i).css('top', -parseInt(numberArr[i]) * h - h * 10 + 'px');
                }, i * 100)
            });
        }
    }
    $.fn.rollNumDaq = function(options) {
        var $that = this;
        var rollNumObj = new rollNumDaq($that, options);
        rollNumObj.init();
    };
})(jQuery, window, document);

// 进度条
function progress(elem, length) {
    // d3.interval(function(){
    d3.select(elem).transition()
        .duration(1000)
        .attrTween("width", function() {
            var i = d3.interpolate(0, length);
            var that = this;
            return function(t) {
                that.style.width = i(t) + 'px';
            };
        });
    // },3000)
}