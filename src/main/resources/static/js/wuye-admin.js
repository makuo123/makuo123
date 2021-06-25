$(function(){
    areaChart();
    companyComprise();
    xueliChart();
    carportChart();
    buildYear();
    adaptation()
})
function areaChart(){
    var option = {
        title : {
            text: '物业管理区域统计(按面积)',
            x:'center',
            textStyle:{
                color:'#a9c6e8',
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c}万平方米 ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: '100',
            top:'20',
            textStyle: {
                color: '#ddd',
                fontSize:10
            }
        },
        color:['#37A2DA','#67E0E3','#FFDB5C','#FF9F7F'],
        series : [
            {
                name: '区域面积',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                label:{
                    normal: {
                        formatter: '{b} : {c}万平方米',
                    }
                },
                data:[
                    {value:84.93, name:'居住物业'},
                    {value:9.19, name:'商业物业'},
                    {value:3.23, name:'工业物业'},
                    {value:2.1, name:'其他物业'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    var myChart = echarts.init(document.getElementById('areaChart'));
    myChart.setOption(option);
    $(window).resize(function(){
    	myChart.resize();
    })
}
function companyComprise(){
    var option = {
        title : {
            text: '从业主体组成',
            x:'center',
            textStyle:{
                color:'#a9c6e8',
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c}家 ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            textStyle: {
                color: '#ddd',
                fontSize:10
            }
        },
        color:['#37A2DA','#67E0E3','#FFDB5C','#FF9F7F','#E062AE'],
        series: [
            {
                name:'企业类型',
                type:'pie',
                radius: ['50%', '70%'],
                center: ['50%', '60%'],
                label:{
                    normal: {
                        formatter: '{b} : {c}家',
                    }
                },
                data:[
                    {value:500, name:'有限责任公司'},
                    {value:300, name:'国有企业'},
                    {value:300, name:'集体企业'},
                    {value:179, name:'其他'},
                    {value:2000, name:'私营企业'}
                ]
            }
        ]
    };
    var myChart = echarts.init(document.getElementById('companyComprise'));
    myChart.setOption(option);
    $(window).resize(function(){
    	myChart.resize();
    })
}
function xueliChart(){
    var option = {
        title : {
            text: '从业人员学历分布',
            x:'center',
            textStyle:{
                color:'#a9c6e8',
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c}%"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            textStyle: {
                color: '#ddd',
                fontSize:10
            }
        },
        color:['#92D7E7','#59C9E1','#03BBD7','#268ACF','#D0EDF5'],
        series: [
            {
                name:'学历',
                type:'pie',
                selectedMode: 'single',
                radius: ['30%', '60%'],
                center: ['55%', '60%'],
                label:{
                    normal: {
                        formatter: '{b} : {c}%',
                    }
                },
                data:[
                    {value:7.2, name:'专科'},
                    {value:66.7, name:'本科',selected:true},
                    {value:18.0, name:'硕士'},
                    {value:4.5, name:'博士'},
                    {value:3.6, name:'其他'}
                ]
            }
        ]
    };
    var myChart = echarts.init(document.getElementById('xueli'));
    myChart.setOption(option);
    $(window).resize(function(){
    	myChart.resize();
    })
}
function carportChart(){
    var option = {
        title : {
            text: '车库/车位组成情况',
            x:'center',
            textStyle:{
                color:'#a9c6e8',
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c}万个 ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            textStyle: {
                color: '#ddd',
                fontSize:10
            }
        },
        color:['#FF9F7F','#E062AE','#9D96F5','#37A2DA'],
        series: [
            {
                name:'车库类型',
                type:'pie',
                radius: ['50%', '70%'],
                center: ['50%', '60%'],
                label:{
                    normal: {
                        formatter: '{b} : {c}万个({d}%)',
                    }
                },
                data:[
                    {value:2.4, name:'地上车库'},
                    {value:10.4, name:'地上车位'},
                    {value:12.4, name:'地下非人防车位'},
                    {value:7.4, name:'地下人防车位'}
                ]
            }
        ]
    };
    var myChart = echarts.init(document.getElementById('carportChart'));
    myChart.setOption(option);
    $(window).resize(function(){
    	myChart.resize();
    })
}
function buildYear(){
    var option={
        title:{
            text: '单体建筑年代分布情况',
            x:'center',
            textStyle:{
                color:'#a9c6e8',
            }
        },
        legend: {
            // orient: 'vertical',
            x: 'center',
            top:25,
            textStyle: {
                color: '#ddd',
                fontSize:10
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer : {
                type : 'shadow'
            }
        },
        textStyle:{
            color: '#ddd'
        },
        dataset: {
            source:[
                ['年份','数量','占比'],
                [2010,42,63],
                [2011,62,57],
                [2012,62,52],
                [2013,51,38],
                [2014,50,38],
                [2015,81,30],
                [2016,78,42],
                [2017,60,40],
                [2018,60,30],
                [2019,55,32]
            ]
        },
        xAxis: {
            type: 'category',
            axisLine: {
                lineStyle: {
                    color: '#999'
                }
            }
        },
        yAxis: {
            axisLine: {
                lineStyle: {
                    color: '#999'
                }
            },
            splitLine: {
                lineStyle: {
                    color: ['#999']
                }
            }
        },
        series: [
            {
                type: 'bar',
                itemStyle:{
                    color:'#37A2DA'
                },
                barWidth: 20,
            },
            {
                type: 'bar',
                itemStyle:{
                    color:'#FFD85C'
                },
                barWidth: 20,
            }
        ]
    }

    var myChart = echarts.init(document.getElementById('buildYear'));
    myChart.setOption(option);
    $(window).resize(function(){
    	myChart.resize();
    })
}

window.onresize = function(){
    adaptation()
}
function adaptation () {
    var w = document.body.clientWidth;
    var h = document.body.clientHeight;
    var nw = 1920,nh =2187;
    var left, top, scale;
    scale = w / nw;
    left =( nw * scale -nw)/2;
    top=-(nh-nh * scale) / 2;
    document.getElementById('main').setAttribute('style', 'transform: scale('+ scale +');left:'+left+'px;top:'+top+'px;position:absolute');
}