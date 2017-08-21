<style lang="less">
.goods-sucess {
  padding: 80px 11px;
  .img{
    width: 60px;
    height: auto;
    display: block;
    margin: 0 auto;
  }
  .sucess-txt{
    text-align: center;
    line-height: 40px;
    font-size: 20px;
  }
  .txt{
    display: block;
    width: 80%;
    padding: 40px 0 0;
    text-align: center;
    font-size: 16px;
    color:#666;
    margin: 0 auto;
  }
  .btn{
    width: 168px;
    height:35px;
    line-height: 35px;
    display: block;
    margin: 130px auto 0;
    text-align: center;
    font-size: 18px;
    color:rgb(0, 146, 63);
    border:1px solid rgb(0, 146, 63);
    border-radius: 5px;
  }
}


</style>

<template lang="html">

<div class="goods-sucess">
  <img class="img" src="../../assets/images/sucess.png" alt="">
  <p class="sucess-txt">提货成功</p>
  <p class="txt">{{txtInfo}}</p>
  <span class="btn">了解好柿连连柿饼</span>
</div>

</template>

<script>




import {
    getUrlKey
}
from '../../config/mUtils'

export default {

    data() {
        return {
          txtInfo:''
        }
    },
    created(){
        document.title="提货成功"
    },
    mounted() {
      this.txtInfo = getUrlKey('result')
    },
    methods: {

    }
}

</script>
