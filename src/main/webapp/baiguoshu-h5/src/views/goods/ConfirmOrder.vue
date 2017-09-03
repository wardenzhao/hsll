<style lang="less" scoped>


.goods-info,
.address-msg,
.goods-price {
    padding: 10px 0;
    background: #fff;
    margin-bottom: 11px;
}




.goods-info {
    padding: 16px 15px;
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
  background: #f0eff5;
  width: 100%
}
.goods-main {
    overflow: hidden;
}
.wechat-btn{
  margin: 30px 30px 60px;
}
.statement{
  display: flex;
  .t1{
    width: 95px;
  }
}
</style>

<template lang="html">

<div class="goods-page">
    <div class="goods-main">
        <div class="goods-info">
            <h2 class="t">店铺名称：好柿连连</h2>
            <div class="img-box">
                <div class="img">
                    <img :src="buyInfo.thumb" alt="">
                </div>
                <div class="img-info">
                    <p class="txt" v-text="buyInfo.goodName"></p>
                    <div class="price">
                      <span>¥{{buyInfo.price}}</span>
                      <span>X {{buyInfo.goodNum}}</span>
                    </div>

                </div>
            </div>
            <div class="txt2 statement">
                <span class="t1">特别申明：</span>
                <span>您本次购买的您本次购买的您本次购买的您本次购买的您本次购买的您本次购买的</span>
            </div>
        </div>
        <div class="address-msg">
            <group>
              <cell :title="addressInfo" is-link link="/address-list?page=comfirmOrder">
                <img slot="icon" width="20" style="display:block;margin-right:15px;" src="../../assets/images/map.png">
              </cell>
                <popup-picker title="发票类型：" :data="[invoiceTypeList]" v-model="invoiceTypeValue" @on-change="invoiceTypeValueClick" placeholder="请选择"></popup-picker>
                <x-input v-if="invoiceTypeValue!='不要发票'" :title="invoiceTitle" v-model="receiptTitle" placeholder="明细"></x-input>
                <x-input v-if="invoiceTypeValue=='企业'" title="纳税人识别号："v-model="receiptNo"  placeholder="免费单位请填0"></x-input>
                <x-input title="卖家留言：" placeholder="140字以内" v-model="buyerMessage" :max="140"></x-input>
            </group>
        </div>
        <div class="goods-price">
            <group>
                <cell title="产品：" v-model="goodPrice"></cell>
                <cell title="运费：" v-model="freight"></cell>
                <cell title="合计：" v-model="totalPrice"></cell>
            </group>
        </div>
        <div class="wechat-btn">
          <x-button type="primary" @click.native="buyGoods">微信支付</x-button>
        </div>

    </div>
</div>

</template>

<script>
import qs from 'qs'
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
            buyInfo:'',
            goodPrice:0,
            freight:0,
            totalPrice:0,
            addressInfo:'请选择收货地址',
            comfirmOrderAddress:'',
            invoiceTitle:'',
            orderNo:''
        }
    },
    created() {
        document.title = "确认订单"
    },
    mounted() {
      if(getStore('buyInfo')){
        this.buyInfo = JSON.parse(getStore('buyInfo'))
        this.goodId = this.buyInfo.goodId
        this.goodSpecId = this.buyInfo.goodSpecId
        this.goodSpeName = this.buyInfo.goodSpeName
        this.goodNum = this.buyInfo.goodNum
        this.goodPrice = '¥' + this.buyInfo.goodPrice.toFixed(2)
        this.freight = '¥0.00'
        this.totalPrice = '¥' + this.buyInfo.goodPrice.toFixed(2)
      }
      if(getStore('comfirmOrderAddress')){
        this.comfirmOrderAddress = JSON.parse(getStore('comfirmOrderAddress'))
        this.addressInfo = this.comfirmOrderAddress.address
        this.person = this.comfirmOrderAddress.person
        this.phone = this.comfirmOrderAddress.phone
      }
    },
    methods: {
      invoiceTypeValueClick(val){
        this.receiptTitle = ''
        this.invoiceTypeValue = val.join('')
        if(val.join('')=='个人'){
          this.invoiceTitle = '发票内容：'
        }
        if(val.join('')=='企业'){
          this.invoiceTitle = '发票抬头：'
        }
      },
      buyGoods(){
        let datas = {
            'openId':getStore('openId') ,
            'goodId': this.goodId,
            'goodNum': this.goodNum,
            'goodSpecId': this.goodSpecId,
            'goodSpeName': this.goodSpeName,
            'goodPrice': this.buyInfo.goodPrice.toFixed(2),
            'address': this.addressInfo,
            'person': this.person,
            'phone': this.phone,
            'receiptType': this.invoiceTypeValue.join(''),
            'receiptTitle': this.receiptTitle,
            'receiptNo': this.receiptNo,
            'buyerMessage': this.buyerMessage,
        }
        console.log(datas)
        this.$http.post(this.HttpUrl.UrlConfig.buyGoods, datas)
            .then(res => {
                var res = res.data
                if (res.code == "1") {
                  this.orderNo = res.data
                  setStore('orderNo',this.orderNo)
                  this.hsllPay()
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
      },
      hsllPay(){
        let datas = {
            'openId':getStore('openId') ,
            'orderNo': this.orderNo,
            'totalPrice': this.buyInfo.goodPrice.toFixed(2)
        }
        this.$http.post(this.HttpUrl.UrlConfig.hsllPay, qs.stringify(datas))
            .then(res => {
                var res = res.data
                // console.log(this.$wechat)
                // console.log(WeixinJSBridge.invoke)
              //   WeixinJSBridge.invoke(
              //      'getBrandWCPayRequest', {
              //          "appId" :res.appId,       //公众号名称，由商户传入
              //          "timeStamp":res.timeStamp, //时间戳，自1970年以来的秒数
              //          "nonceStr" : res.nonceStr, //随机串
              //          "package" : res.package,
              //          "signType" :res.signType,  //微信签名方式：
              //          "paySign" : res.paySign     //微信签名
              //      },
              //      (result)=>{
              //         console.log()
              //          if(result.err_msg == "get_brand_wcpay_request:ok" ) {
              //            this.$router.push(
              //              {
              //                path: '/pay-sucess'
              //              })
              //          }
              //      }
              //  );



              //  this.$wechat.chooseWXPay({
                  // "appId" :res.appId,
                  // "timestamp": res.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                  // "nonceStr": res.nonceStr, // 支付签名随机串，不长于 32 位
                  // "package": res.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                  // "signType": res.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                  // "paySign": res.paySign, // 支付签名
              //     success:(result)=> {
              //         // 支付成功后的回调函数
              //         console.log(result)
              //         alert(result)
              //
              //     }
              // });
              var jsStr = {
                "appId" :res.appId,
                "timeStamp": res.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                "nonceStr": res.nonceStr, // 支付签名随机串，不长于 32 位
                "package": res.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                "signType": res.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                "paySign": res.paySign, // 支付签名
              }
              callpay(jsStr)
              function jsApiCall(jsStr) {
                    WeixinJSBridge.invoke(
                    'getBrandWCPayRequest',
                     jsStr,//存在问题的
                     //jsStr,//josn串
                     (result)=> {
                         if(result.err_msg == "get_brand_wcpay_request:ok" ) {
                            window.location.replace("http://www.100fruit.cn/baiguoshu-h5/dist/index.html#/pay-sucess");
                          }
                         //由于官方申明：使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                         //所以在返回ok的时候，只进行成功提示（即使是支付失败，但是返回是ok）
                     }
                     );
                }
                function callpay(jsStr) {
                    if (typeof WeixinJSBridge == "undefined") {
                        if (document.addEventListener) {
                            document.addEventListener('WeixinJSBridgeReady', jsApiCall(jsStr), false);
                        }
                        else if (document.attachEvent) {
                            document.attachEvent('WeixinJSBridgeReady', jsApiCall(jsStr));
                            document.attachEvent('onWeixinJSBridgeReady', jsApiCall(jsStr));
                        }
                    }
                    else {
                        jsApiCall(jsStr);
                    }
                }








            }).catch(error => {
                console.log(error)
            })
      }
    }
}

</script>
