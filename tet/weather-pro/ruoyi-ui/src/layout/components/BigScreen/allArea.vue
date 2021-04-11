<!--
 * @Author: your name
 * @Date: 2021-2-5 17:08:45
 * @LastEditTime:  2021-2-5 17:08:45
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \isio-member-ui\src\layout\components\bigScreen\allArea.vue
-->
<template>
  <div class="js-con">
    <p class="js-title">准池线各工区降雨量统计</p>
    <li class="leg-line"></li>
    <!-- 顶部-线路选择tab -->
    <div class="tab-box sta-tab">
      <ul class="top-tab">
        <li class="tabNor" @click="station=0" :class="{tabNow:station==0}">新朔铁路全线</li>
        <li class="tabNor" @click="station=1" :class="{tabNow:station==1}">大准线</li>
        <li class="tabNor" @click="station=2" :class="{tabNow:station==2}">准池线</li>
        <li class="tabNor" @click="station=3" :class="{tabNow:station==3}">巴准线</li>
      </ul>
    </div>
    <!-- 柱状图 -->
    <div class="js-chart" ref="allAreaChart">
      <div id="allArea" ref="dom"></div>
    </div>
    <!-- 地图时间tab -->
    <ul class="left-tab time-tab">
      <li class="tabNor" @click="time=0" :class="{tabNow:time==0}">
        <i class="el-icon-caret-right"></i>
        <span>10分钟</span>
      </li>
      <li class="tabNor" @click="time=1" :class="{tabNow:time==1}">
        <i class="el-icon-caret-right"></i>
        <span>1小时</span>
      </li>
      <li class="tabNor" @click="time=2" :class="{tabNow:time==2}">
        <i class="el-icon-caret-right"></i>
        <span>日连续</span>
      </li>
      <li class="tabNor" @click="time=3" :class="{tabNow:time==3}">
        <i class="el-icon-caret-right"></i>
        <span>日连续 + 1小时</span>
      </li>
    </ul>
  </div>
</template>

<script>
  import echarts from 'echarts';
  export default {
    props: {
      showDialog: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        time:0,
        station:0,
        data: [],
        dom: null,
      }
    },
    mounted() {
      // this.$nextTick(() => {      // 给图标宽高 使图标填满容器
      //   document.getElementById('allArea').style.width = this.$refs.allAreaChart.offsetWidth + 'px';
      //   document.getElementById('allArea').style.height = this.$refs.allAreaChart.offsetHeight + 'px';
      //   this.draw();
      // })
    },
    watch: {
      showDialog(newValue,oldValue) {
        this.$nextTick(() => {
          document.getElementById('allArea').removeAttribute('_echarts_instance_');
          document.getElementById('allArea').style.width = this.$refs.allAreaChart.offsetWidth + 'px';
          document.getElementById('allArea').style.height = this.$refs.allAreaChart.offsetHeight + 'px';
          this.draw();
        })
      }
    },
    methods: {
      // 画图
      draw() {
        // 网络请求 假装从后端拿回来的数据
        this.data = [
          { name: '外西沟', value: 10 },
          { name: '巡道', value: 20 },
          { name: '海子湾', value: 30 },
          { name: '八里铺', value: 40 },
          { name: '蔡家屯AT所', value: 50 },
          { name: '六里庄', value: 60 },
          { name: '苏家沟AT所', value: 70 },
          { name: '高家堡车站', value: 80 },
          { name: '唐公塔', value: 90 }
        ];
        // 拼轴显示和数据的数组
        let xAxisText = [];
        let dataList = [];
        this.data.forEach(item => {
          xAxisText.push(item.name);
          dataList.push(item.value)
        })
        // 从这里开始 创建自定义图形 —— 长方体的正面
        var MyCubeRect = echarts.graphic.extendShape({
          shape: {
            x: 0,
            y: 0,
            width: 180,      // 长方体宽度
            zWidth: 8,      // 阴影折角宽
            zHeight: 4      // 阴影折角高
          },
          buildPath: function (ctx, shape) {
            const api = shape.api;
            const xAxisPoint = api.coord([shape.xValue, 0]);
            const p0 = [shape.x, shape.y];
            const p1 = [shape.x - shape.width / xAxisText.length, shape.y];
            const p4 = [shape.x + shape.width / xAxisText.length, shape.y];
            const p2 = [xAxisPoint[0] - shape.width / xAxisText.length, xAxisPoint[1]];
            const p3 = [xAxisPoint[0] + shape.width / xAxisText.length, xAxisPoint[1]];

            ctx.moveTo(p0[0], p0[1]); //0
            ctx.lineTo(p1[0], p1[1]); //1
            ctx.lineTo(p2[0], p2[1]); //2
            ctx.lineTo(p3[0], p3[1]); //3
            ctx.lineTo(p4[0], p4[1]); //4
            ctx.lineTo(p0[0], p0[1]); //0
            ctx.closePath();
          }
        })

        // 创建第二个自定义图形 —— 长方体的上面和侧面
        var MyCubeShadow = echarts.graphic.extendShape({
          shape: {
            x: 0,
            y: 0,
            width: 180,
            zWidth: 8,
            zHeight: 4
          },
          buildPath: function (ctx, shape) {
            const api = shape.api;
            const xAxisPoint = api.coord([shape.xValue, 0]);
            const p0 = [shape.x, shape.y];
            const p1 = [shape.x - shape.width / xAxisText.length, shape.y];
            const p4 = [shape.x + shape.width / xAxisText.length, shape.y];
            const p6 = [shape.x + shape.width / xAxisText.length + shape.zWidth, shape.y - shape.zHeight];
            const p7 = [shape.x - shape.width / xAxisText.length + shape.zWidth, shape.y - shape.zHeight];
            const p3 = [xAxisPoint[0] + shape.width / xAxisText.length, xAxisPoint[1]];
            const p5 = [xAxisPoint[0] + shape.width / xAxisText.length + shape.zWidth, xAxisPoint[1] - shape.zHeight];

            ctx.moveTo(p4[0], p4[1]); //4
            ctx.lineTo(p3[0], p3[1]); //3
            ctx.lineTo(p5[0], p5[1]); //5
            ctx.lineTo(p6[0], p6[1]); //6
            ctx.lineTo(p4[0], p4[1]); //4

            ctx.moveTo(p4[0], p4[1]); //4
            ctx.lineTo(p6[0], p6[1]); //6
            ctx.lineTo(p7[0], p7[1]); //7
            ctx.lineTo(p1[0], p1[1]); //1
            ctx.lineTo(p4[0], p4[1]); //4
            ctx.closePath();
          }
        });
        echarts.graphic.registerShape('MyCubeRect', MyCubeRect);
        echarts.graphic.registerShape('MyCubeShadow', MyCubeShadow);
        const option = {
          // color: ['#33b56a', '#fdcf5c', '#4c90ff', '#fe7b7a', '#69ccf6', '#a38bf8', '#ff9561', '#8cb0ea', '#fe81b4', '#ffb258'],
          title: {
            text: '准池线各工区近1小时逐小时降雨量',
            left: 'center',
            top: 20,
            textStyle:{
              color:'#00D9E8',
              //字体风格,'normal','italic','oblique'
              fontStyle:'normal',
              fontWeight:'normal',
      　　　　 fontSize:16
            },
          },
          legend: {
            show: false,
            top: 25
          },
          grid: {
            left: '3%',
            right: '4%',
            top: '15%',
            bottom: '0%',
            containLabel: true
          },
          xAxis: {
            type: 'category',
            data: xAxisText,
            boundaryGap: true,
            interval: 0,
            name:'站点',
            axisLabel: {
              color: '#fff',
              //  让x轴文字方向为竖向
              // interval: 0,
              // formatter: function (value) {
              //   return value.split('').join('\n')
              // }
            },
            //坐标轴颜色
            axisLine: {
              lineStyle:{
                color:'#6C9DFF'
              }
            },
            //坐标刻度隐藏
            axisTick: {
              show:false
            },
            // 网格线
            splitLine: {
              show: false
        　　},
          },
          yAxis: {
            type: 'value',
            name:'降水量(mm)',
            axisLabel: {
              color: '#fff'
            },
            //坐标轴颜色
            axisLine: {
              lineStyle:{
                color:'#6C9DFF'
              }
            },
            //坐标刻度隐藏
            axisTick: {
              show:false
            },
            // 网格线
            splitLine: {
              show: true,
              lineStyle:{
                 color: ['#386FB4'],
                 width: 1,
                 type: 'solid'
              }
        　　},
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            },
          },
          //图例
          series: [{
            name: '降水量',
            type: 'custom',
            renderItem: (params, api) => {
              let location = api.coord([api.value(0), api.value(1)]);
              return {
                type: 'group',
                children: [{
                  type: 'MyCubeRect',
                  shape: {
                    api,
                    xValue: api.value(0),
                    yValue: api.value(1),
                    x: location[0],
                    y: location[1]
                  },
                  style: api.style(),      // api.style()——继承原本的样式
                }, {
                  type: 'MyCubeShadow',
                  shape: {
                    api,
                    xValue: api.value(0),
                    yValue: api.value(1),
                    x: location[0],
                    y: location[1]
                  },
                  style: {
                    fill: api.style(),
                    text: ''            // 继承原本样式的基础上将label清空 如果不清空生成的图上会显示两个重叠的label
                  }
                }]
              }
            },
            stack: '总量',
            label: {
              show: false,
              position: 'top',
              color: '#fff',
              formatter: `{c}mm`,
              fontSize: 16,
              distance: 15
            },
            // 柱状图渐变色
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                  offset: 0,
                  color: "#177AFC" // 0% 处的颜色
                }, {
                  offset: 1,
                  color: "#14D4FF" // 100% 处的颜色
                }], false)
              }
            },
            // itemStyle: {
            //   normal: {
            //     color: (params) => {
            //       // 使每根柱子颜色都不一样
            //       let colorList = ['#33b56a', '#fdcf5c', '#4c90ff', '#fe7b7a', '#69ccf6', '#a38bf8', '#ff9561', '#8cb0ea', '#fe81b4', '#ffb258'];
            //       if (params.dataIndex + 1 <= colorList.length) {
            //         return colorList[params.dataIndex]
            //       } else {
            //         // 如果柱子的数量超过颜色数组 就从头再来一遍
            //         return colorList[params.dataIndex - colorList.length]
            //       }
            //     }
            //   }
            // },
            data: dataList
          }]
        };
        this.dom = echarts.init(this.$refs.dom);
        this.dom.setOption(option, true)
        // window.addEventListener("resize", () => {
        //   if (document.getElementById('allArea') && this.$refs.allAreaChart) {
        //     document.getElementById('allArea').removeAttribute('_echarts_instance_');
        //     document.getElementById('allArea').style.width = this.$refs.allAreaChart.offsetWidth + 'px';
        //     document.getElementById('allArea').style.height = this.$refs.allAreaChart.offsetHeight + 'px';
        //     this.dom.resize();
        //   }
        // });
      }
    }
  }
</script>

<style scoped lang="scss">
  .js-chart {
    width: 100%;
    height: 60%;
    margin-top:50px;
  }
</style>
