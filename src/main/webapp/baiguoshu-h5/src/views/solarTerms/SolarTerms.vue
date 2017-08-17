<template lang="html">
<div class="">
  <swiper :list="demo01_list" v-model="demo01_index" height="250px" @on-index-change="demo01_onIndexChange"></swiper>
</div>
</template>

<script>
import {
    Swiper
}
from 'vux'
export default {
  components: {
      Swiper
  },
  data(){
    return {
      demo01_list: baseList,
    }
  }
}
</script>

<style lang="less">
</style>
