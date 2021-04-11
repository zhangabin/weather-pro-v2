import '@/assets/styles/bigScreen.scss';
import '@/assets/mapbox/mapbox-gl.scss';
//导入地图组件
import mapboxgl from 'mapbox-gl'
//导入铁路线gis数据
import { getXsRailGisData } from '@/api/gis/gis'
//导入雨量站数据
import {  listRainfall_stationAndWorkArea } from '@/api/sysmanagement/rainfall_station'
//导入marker相关操作
import { markerPoint,  showMarkerByRailType, createPopup } from "@/utils/marker";
import tree from 'element-ui/packages/table/src/store/tree'
export  default {
  data() {
    return {
      map: undefined, //地图对象
      railway:0, //当前所选铁路线
      stations: [], //雨量计站点集合
      mapPositions: [
        {name: 'qx',center:[112.322, 39.723],zoom: 8},
        {name: 'dz',center:[112.522, 40.223],zoom: 8.7},
        {name: 'xz',center:[110.722, 39.723],zoom: 9.2},
        {name: 'zc',center:[112.322, 39.723],zoom: 8.5}
      ]
    }
  },
  mounted() {
    mapboxgl.accessToken = this.mapBoxAccessToken;
    this.map = new mapboxgl.Map({
      container: this.$refs.mapContainer, // container id
      style: this.mapBoxStyleURI, // style URL
      center: [112.322, 39.723], // starting position [lng, lat]
      zoom: 8, // starting zoom
      pitchWithRotate:false, // 防止3d旋转
      dragRotate:false // 防止2d旋转
    });
    //地图加载
    // this.map.on('load',() => {
    //   this.addSource()
    // })
    //添加站点
    this.addStation()
  },
  methods: {
    //切换路线
    changeRail(name,type) {
      this.railway = type
      //设置地图中心与缩放
      let position = this.mapPositions.find(m => m.name === name);
      this.map.setCenter(position.center)
      this.map.setZoom(position.zoom)
      //过滤雨量站（只显示当前线路）
      showMarkerByRailType(this.stations, name)
      //判断铁路线
      if(name === 'qx'){
        this.map.setFilter("railroad" ,['!=','type',name])
        this.map.setFilter("railroad2" ,['!=','type',name])
      }else{
        this.map.setFilter("railroad" ,['==','type',name])
        this.map.setFilter("railroad2" ,['==','type',name])
      }
    },
    //改变工区颜色
    changeRailStatus() {
      //设置要素
      this.map.setFeatureState({
        id: "22",
        source: 'railroad'
      },{
        color: '#a50d21',
        'hover': true,
      })
    },
    addLayer() {
      this.map.addLayer({                 /* 为地图添加layer */
        "id": "railroad",             /* layer id是route */
        "type": "line",            /* line类型layer*/
        "source": "railroad",         /* 资源引用的是上面定义的source*/
        "layout": {
          "line-join": "round",  /* 线条相交的形状 */
          "line-cap": "round"    /* 线条末端形状 */
        },
        "paint": {
          "line-color":   [
            'case',
            ['boolean', ['feature-state', 'hover'], false],
            ['feature-state', 'color'],
            '#69DBE4'],/* 线条颜色 */
          "line-width": 8,       /* 线条宽度 */
          "line-opacity": 0.4,
          "line-blur": 0.4
        }
      });
      this.map.addLayer({                 /* 为地图添加layer */
        "id": "railroad2",             /* layer id是route */
        "type": "line",            /* line类型layer*/
        "source": "railroad",         /* 资源引用的是上面定义的source*/
        "layout": {
          "line-join": "round",  /* 线条相交的形状 */
          "line-cap": "round"    /* 线条末端形状 */
        },
        "paint": {
          "line-color":   [
            'case',
            ['boolean', ['feature-state', 'hover'], false],
            ['feature-state', 'color'],
            '#69DBE4'],/* 线条颜色 */
          "line-width": 4,        /* 线条宽度 */
          "line-opacity": 0.7,
          "line-blur": 0.4
        }
      });
    },
    //获取铁路数据
    async addSource() {
      let { data } = await getXsRailGisData()
      this.map.addSource('railroad', {
        type: 'geojson',
        data: data
      });
      this.addLayer()
    },
    //添加雨量站
    async addStation() {
      let { rows } =  await listRainfall_stationAndWorkArea()
      rows.forEach((item,index) => {
        let marker = new mapboxgl.Marker({element: markerPoint("#3EFF88",false,item.longitude,item.latitude,item.id,item.installationSite, item.line)})
          .setLngLat([item.longitude,item.latitude])
          .addTo(this.map)
        this.stations.push(marker)
      })
    }
  }
}
