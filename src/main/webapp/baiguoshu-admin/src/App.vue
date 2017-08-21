<template>
  <div id="app">
    <transition name="fade" mode="out-in">
    <router-view></router-view>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style lang="scss">
@import "./assets/styles/common.scss";
.fade-enter-active,
.fade-leave-active {
    transition: opacity .2s
}
.fade-enter,
.fade-leave-to
/* .fade-leave-active in <2.1.8 */

{
    opacity: 0
}
</style>
