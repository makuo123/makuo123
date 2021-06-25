$(function() {
    initBMap();
    adaptation()
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

function initBMap(map) {
    var map = new BMap.Map("mapContent", {
        minZoom: 8,
        maxZoom: 10
    }); // 创建Map实例
    map.centerAndZoom(new BMap.Point(113.271851, 23.137683), 8); // 初始化地图,设置中心点坐标和地图级别
    map.setMapStyleV2({
        styleId: '0af7b4272fe9a858622505f596e20203'
    });
    map.enableScrollWheelZoom();
    map.panBy(150, 0);

    var cityName = '上饶市';
    map.addControl(new BMap.OverviewMapControl()); //添加缩略地图控件
    map.enableScrollWheelZoom();
    map.addControl(new BMap.NavigationControl({
        type: BMAP_NAVIGATION_CONTROL_LARGE,
        anchor: BMAP_ANCHOR_TOP_LEFT,
        offset: new BMap.Size(40, 250)
    }));
    var bdary = new BMap.Boundary();
    bdary.get(cityName, function(rs) { //获取行政区域
        console.log(rs)
        var EN_JW = "180, 90;"; //东北角
        var NW_JW = "-180,  90;"; //西北角
        var WS_JW = "-180, -90;"; //西南角
        var SE_JW = "180, -90;"; //东南角
        //4.添加环形遮罩层
        var ply1 = new BMap.Polygon(rs.boundaries[5] + SE_JW + SE_JW + WS_JW + NW_JW + EN_JW + SE_JW, {
            strokeColor: "none",
            fillColor: "rgb(178,218,253)",
            fillOpacity: 1
        }); //建立多边形覆盖物
        map.addOverlay(ply1);
        getRegion(map)
    });
}


function getRegion(map) {
    var dataArr = [{
            "name": '信州区',
            "cp": [117.969388, 28.439407]
        },
        {
            "name": '广丰区',
            "cp": [118.197997, 28.443377]
        },
        {
            "name": '广信区',
            "cp": [117.915584, 28.453933]
        },
        {
            "name": '德兴市',
            "cp": [117.588353, 28.951806]
        },
        {
            "name": '玉山县',
            "cp": [118.250988, 28.68851]
        },
        {
            "name": '铅山县',
            "cp": [117.719299, 28.320232]
        },
        {
            "name": '横峰县',
            "cp": [117.602968, 28.413186]
        },
        {
            "name": '弋阳县',
            "cp": [117.456078, 28.384336]
        },
        {
            "name": '余干县',
            "cp": [116.70045, 28.709134]
        },
        {
            "name": '鄱阳县',
            "cp": [116.71114, 29.013727]
        },
        {
            "name": '万年县',
            "cp": [117.064596, 28.700306]
        },
        {
            "name": '婺源县',
            "cp": [117.868445, 29.253787]
        },

    ];
    dataArr.forEach((element, index) => {
        var bdary = new BMap.Boundary();
        bdary.get(element['name'], rs => {
            var count = rs.boundaries.length;
            for (let i = 0; i < count; i++) {
                var ply = new BMap.Polygon(rs.boundaries[i], {
                    strokeWeight: 2,
                    strokeColor: '#057BE0',
                    fillOpacity: 1,
                    fillColor: 'transparent'
                });
                map.addOverlay(ply);
            }
            citySetLabel(new BMap.Point(element['cp'][0], element['cp'][1]), element['name'] + ':' + index + '个', map);
        });
    });
}

function citySetLabel(cityCenter, cityName, map) {
    var label = new BMap.Label(cityName, {
        offset: new BMap.Size(-20, -20),
        position: cityCenter
    });
    label.setStyle({
        border: 'none',
        background: '#0060CF',
        'font-size': '16px',
        color: '#fff',
    });
    map.addOverlay(label);
    label.addEventListener("click", function(res) {
        console.log(res)
        map.setZoom(10);
        map.panTo(new BMap.Point(res.point.lng, res.point.lat));
    });
}