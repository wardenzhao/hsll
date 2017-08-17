<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style lang="less">
@import '../node_modules/vux/src/styles/reset';
@import '../node_modules/vux/src/styles/1px.less';
html,body,#app{
  height: 100%;
  margin: 0;
}
</style>
