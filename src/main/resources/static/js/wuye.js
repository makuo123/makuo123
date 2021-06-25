$(function() {
    adaptation();
    initBMap();
    fyfqChart();
    initPieChart()
})
window.onresize = function() {
    adaptation()
}

function adaptation() {
    var w = window.innerWidth;
    var h = window.innerHeight
    var nw = 1920,
        nh = 1080;
    var left, top, scale;
    scale = w / nw;
    left = (nw * scale - nw) / 2;
    scale2 = h / nh
    top = -(nh - nh * scale2) / 2;
    document.getElementById('main').setAttribute('style', 'transform: scale(' + scale + ',' + scale2 + ');left:' + left + 'px;top:' + top + 'px;position:absolute;');
}
// 维修项目统计
function fyfqChart() {
    var myChart = echarts.init(document.getElementById('barEchart'));
    get('queryWyjg/getWxxmList.do', function(res) {
        let data1 = res.data.map(item => {
            return item.XMMC
        })

        let data2 = res.data.map(item => {
            return item.XMGS
        })

        myChart.setOption(option = {
            legend: {
                data: ['项目个数'],
                right: '20px',
                top: '10px',
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
            grid: {
                left: '3%',
                right: '3%',
                bottom: '3%',
                containLabel: true
            },
            textStyle: {
                color: '#a9c6e8'
            },
            xAxis: {
                data: data1,
                type: 'category',
                axisLine: {
                    lineStyle: {
                        color: '#a9c6e8'
                    }
                }
            },
            yAxis: {
                axisLine: {
                    lineStyle: {
                        color: '#a9c6e8'
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: ['#a9c6e8']
                    }
                }
            },
            series: [{
                name: '项目个数',
                type: 'bar',
                itemStyle: {
                    color: '#249FFD'
                },
                data: data2
            }]
        });
    });

    $(window).resize(function() {
        myChart.resize();
    })
}
// 维修项目资金情况
function initPieChart() {
    var myChart = echarts.init(document.getElementById("pieEchart"));

    get('queryWyjg/getWxxmList.do', function(res) {
        console.log('pieEchart', res)
        let data1 = res.data.map(item => {
            return item.XMMC
        });
        let data3 = res.data.map(item => {
            item.XMWXZJ = Math.round(item.XMWXZJ / 10000 * 100) / 100
            return item.XMWXZJ
        });
        myChart.setOption(option = option = {
            xAxis: {
                type: 'category',
                axisLine: {
                    lineStyle: {
                        color: '#FECA14'
                    }
                },
                data: data1
            },
            yAxis: {
                name: '单位：万元',
                type: 'value',
                axisLine: {
                    lineStyle: {
                        color: '#FECA14'
                    }
                },
            },
            series: [{
                data: data3,
                type: 'line',

                smooth: true
            }]
        });

    });

    $(window).resize(function() {
        myChart.resize();
    })
}

function initBMap() {
    get('queryWyjg/getLpDetailsList.do', function(res) {
        var dataArr = res.data;

        //创建Map实例
        var map = new BMap.Map("BMap");
        //var point = new BMap.Point(117.948313, 28.462404); // 创建点坐标 
        var point = new BMap.Point(117.991391, 28.454208); // 创建点坐标   
        map.centerAndZoom(point, 14); // 初始化地图，设置中心点坐标和地图级别
        // 自定义地图样式
        map.setMapStyleV2({
            styleId: 'a7d06a8530de07fe4e002028fd84376d'
        });
        map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
        // 创建标注点,enableMessage:true-允许信息窗发送短息
        var myIcon = new BMap.Icon('../static/img/icon/icon_location.png', new BMap.Size(32, 32));

        function addMarker(point, content, labelName) {
            var marker = new BMap.Marker(point, {
                icon: myIcon
            });
            var label = new BMap.Label(labelName, {
                offset: new BMap.Size(35, 0)
            });
            label.setStyle({
                border: "none",
                background: "transparent",
                color: '#E4312F',
                fontSize: '20px',
                fontWeight: '700',
                textShadow: '0px 0px 2px #fff'
            })
            marker.setLabel(label);
            map.addOverlay(marker);
            addClickHandler(content, marker);
        }
        //console.log('dataArr', dataArr)
        for (var i = 0; i < dataArr.length; i++) {
            var point = new BMap.Point(dataArr[i].X, dataArr[i].Y);
            //console.log('dataArr[i].X', dataArr[i].X)
            var content = "<div class='infoBoxContent clearfix'>" +
                "<p class='communityName'><span>" + dataArr[i].LPMC + "</span>(" + dataArr[i].X + "," + dataArr[i].Y + ")<p>" +
                "<p class='title'>小区信息</p>" +
                "<table class='dataInfo'>" +
                "<tr><td>项目区域：</td><td class='w150'>" + dataArr[i].SZCQ + "</td><td>占地面积：</td><td class='w150'>" + dataArr[i].ZDMJ + "</td></tr>" +
                "<tr><td>房屋套数：</td><td class='w150'>" + dataArr[i].ZTS + "套</td><td>绿化率：</td><td class='w150'>" + dataArr[i].LHL + "%</td></tr>" +
                "<tr><td>开发企业：</td><td colspan='3'>" + dataArr[i].KFQYMC + "</td></tr>" +
                "<tr><td>项目地址：</td><td colspan='3'>" + dataArr[i].XMDZ + "</td></tr>" +
                "</table>"
                // + "<p class='seeMore' onclick='seeDetail(" + dataArr[i].ID + ")'>查看更多信息&gt;&gt;</p>" +
            "</div>"

            addMarker(point, content, dataArr[i].LPMC)
        }
        //监听标注点点击事件
        var lastInfoBox = null;

        function addClickHandler(content, marker) {
            marker.addEventListener("click", function(e) {
                var point = new BMap.Point(e.target.getPosition().lng, e.target.getPosition().lat);
                // 将当前点移动到中心位置
                setTimeout(function() {
                    map.panTo(point)
                }, 100)
                var infoBox = new BMapLib.InfoBox(map, content, {
                    closeIconMargin: "10px 10px 0 0",
                    closeIconUrl: "../static/img/icon/close.png",
                    enableAutoPan: false
                });
                if (lastInfoBox) {
                    lastInfoBox.close()
                }
                lastInfoBox = infoBox;
                infoBox.open(marker);
            });
        }

    });
}
// 查看更多详细信息
function seeDetail(id) {
    alert("项目ID" + id)
}