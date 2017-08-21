<style lang="less" scoped>


.goods-info,
.address-msg,
.goods-price {
    padding: 10px 0;
    background: #fff;
    margin-bottom: 19px;
}




.goods-info {
    padding: 25px 22px;
    .t{
      font-size: 16px;
      font-weight: normal;
    }
    .img-box{
      overflow: hidden;
      clear:both;
      margin: 25px 0 0;
      .img{
        float: left;
        width: 72px;
        height: 72px;
        padding-left: -80px;
        img{
          width: 72px;
          height: 72px;
        }
      }
      .img-info{
        width: 100%;
        margin-left: 100px;
        color:#000;
        font-size:14px;
        .txt{
          padding-right: 100px;
        }
        .price{
          margin-top: 15px;
          display: flex;
          justify-content:space-between;
          padding-right: 100px;
          span{

          }
        }
      }

    }
    .txt2{
      padding-top: 20px;
      font-size:12px;
      color:#666;
    }
}
.goods-page{
  background: #ccc;
  width: 100%
}
.goods-main {
    overflow: hidden;
}
.wechat-btn{
  margin: 30px 30px 60px;
}

</style>

<template lang="html">

<div class="goods-page">
    <div class="goods-main">
        <div class="goods-info">
            <h2 class="t">店铺名称：好柿连连</h2>
            <div class="img-box">
                <div class="img">
                    <img src="../../assets/images/1.png" alt="">
                </div>
                <div class="img-info">
                    <p class="txt">好柿连连柿饼2017限量礼盒装</p>
                    <div class="price">
                      <span>¥{{total}}</span>
                      <span>X {{num}}</span>
                    </div>

                </div>
            </div>
            <div class="txt2">
                特别申明：您本次购买的您本次购买的您本次购买的您本次购买的您本次购买的您本次购买的
            </div>
        </div>
        <div class="address-msg">
            <group>
              <cell :title="2222" is-link link="/address-list">
                <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/images/map.png">
              </cell>
                <popup-picker title="发票类型" :data="[invoiceTypeList]" v-model="invoiceTypeValue"  placeholder="请选择"></popup-picker>
                <x-input title="发票内容" placeholder="明细"></x-input>
                <x-input title="发票抬头" placeholder="明细"></x-input>
                <x-input title="纳税人识别号" placeholder="免费单位请填0"></x-input>
                <x-input title="卖家留言" placeholder="140字以内" :max="140"></x-input>
            </group>
        </div>
        <div class="goods-price">
            <group>
                <cell title="产品：" value=""></cell>
                <cell title="运费" value=""></cell>
                <cell title="合计" value=""></cell>
            </group>
        </div>
        <div class="wechat-btn">
          <x-button type="primary" @click.native="buyGoods">微信支付</x-button>
        </div>

    </div>
</div>

</template>

<script>
import {
    setStore, getStore ,getUrlKey
}
from '../../config/mUtils'
import {
    Cell, Group,XInput,
    XButton,
    PopupPicker
}
from 'vux'
export default {
    components: {
        Group,
        Cell,
        XInput,
        XButton,
        PopupPicker
    },
    data() {
        return {
            total: 180,
            num: 1,
            invoiceTypeList:['不要发票','个人','企业'],
            invoiceTypeValue:['不要发票'],
            goodId:'',
            goodNum:'',
            goodSpecId:'',
            goodSpeName:'',
            goodPrice:'',
            address:'',
            person:'',
            phone:'',
            receiptType:'',
            receiptTitle:'',
            receiptNo:'',
            buyerMessage:'',
        }
    },
    created() {
        document.title = "确认订单"
    },
    mounted() {

    },
    methods: {
      buyGoods(){
        let datas = {
            'openId':getStore('openId') ,
            'goodId': this.goodId,
            'goodNum': this.goodNum,
            'goodSpecId': this.goodSpecId,
            'goodSpeName': this.goodSpeName,
            'goodPrice': this.goodPrice,
            'address': this.address,
            'person': this.person,
            'phone': this.phone,
            'receiptType': this.receiptType,
            'receiptTitle': this.receiptTitle,
            'receiptNo': this.receiptNo,
            'buyerMessage': this.buyerMessage,
        }
        this.$http.post(this.HttpUrl.UrlConfig.buyGoods, datas)
            .then(res => {
                var res = res.data
                if (res.code == "1") {

                } else {
                    this.$vux.toast.show({
                        text: res.msg,
                        type: 'text',
                        width: '80%'
                    })
                }
            }).catch(error => {
                console.log(error)
            })
      }
    }
}

</script>
