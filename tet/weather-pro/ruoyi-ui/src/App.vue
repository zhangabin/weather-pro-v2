<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default  {
  name:  'App'
}
</script>
<style scoped lang="scss">
//屏蔽地图logo ::v-deep是scss的写法 类似/deep/
::v-deep a.mapboxgl-ctrl-logo {
  display: none;
}
::v-deep div.mapboxgl-ctrl-attrib-inner {
  display: none;
}
</style>
