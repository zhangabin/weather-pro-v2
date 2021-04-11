import mapboxgl from 'mapbox-gl'
import log from '@/views/monitor/job/log'
/**
 * 自定义雨量计站点样式
 * @param color 雨量计指示颜色
 * @param status 是否显示闪烁动画 （true 是 false 否）
 * @param lng 雨量计经度
 * @param lat 雨量计纬度
 * @param id 雨量计id
 * @param name 雨量计名称
 * @param line 所属铁路线
 * @returns {HTMLDivElement}
 */
export function markerPoint(color, status, lng, lat, id, name, line) {
  //创建动画div
  const  animationDiv = document.createElement('div');
  animationDiv.className = 'marker-warning';
  animationDiv.setAttribute("data-id",id)
  animationDiv.setAttribute("data-lng",lng)
  animationDiv.setAttribute("data-lat",lat)
  animationDiv.setAttribute("data-name",name)
  animationDiv.setAttribute("data-line",line)
  //创建底色div
  const bottomDiv = document.createElement('div');
  bottomDiv.id = "div1"
  if(status){
    bottomDiv.style.backgroundColor = color
  }
  animationDiv.appendChild(bottomDiv);
  //创建底色div
  const bottomDiv1 = document.createElement('div');
  bottomDiv1.id = "div2"
  animationDiv.appendChild(bottomDiv1);
  //创建色块div
  const colorDiv = document.createElement('div');
  colorDiv.id = "div3"
  colorDiv.style.backgroundColor = color
  animationDiv.appendChild(colorDiv)
  animationDiv.addEventListener("click",(ev) =>{
    ev.stopPropagation()
  })
  return animationDiv
}

/**
 * 修改雨量计指示点颜色
 * @param el 站点样式对象 HTMLElement
 * @param color 雨量计站点颜色
 */
export function changeMarkerStatus(el, color){
  el.firstElementChild.style.backgroundColor = color
  el.lastElementChild.style.backgroundColor = color
}

/**
 * 显示当前线路marker
 * @param stations 站点集合
 * @param type 线路类型
 */
export function showMarkerByRailType(stations, type) {
  stations.forEach(s => {
    let el = s.getElement();
    let { line } = el.dataset
    let popup = s.getPopup()
    //需要显示
    if(line === type ||  type === 'qx' ){
      el.style.display = 'block'
      //如果弹窗关闭则打开
      if (popup !== null && !popup.isOpen()){
        s.togglePopup()
      }
    }else{
      el.style.display = 'none'
      //如果弹窗打开则关闭
      if (popup !== null && popup.isOpen()){
        s.togglePopup()
      }
    }
  })
}

/**
 * 创建弹窗
 * @param map 地图实例
 */
export function createPopup(map) {
  return  new mapboxgl.Popup({anchor:"left",closeOnClick:false,closeButton:false})
    .setHTML(`<div class="map-info info-left"><div>某某站（52mm）</div></div>`)
    // .setHTML(`<li class="w-info" >
    //                 <div class="w-sta">
    //                   <p @click="singleArea = true">外西沟—清水河</p>
    //                   <span class="w-alert1">封锁警戒</span>
    //                 </div>
    //                 <p class="w-text">1小时降水量48mm（阈值：45mm）</p>
    //           </li>`)
    .setMaxWidth("300px")
    .addTo(map)
}
