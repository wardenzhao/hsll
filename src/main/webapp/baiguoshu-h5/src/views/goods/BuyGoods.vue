<style lang="less">

@import "../../../node_modules/vux/src/styles/weui/base/mixin/setOnepx";
@import "../../../node_modules/vux/src/styles/close";
.goods-main {
    background: #fff;
    .buy-btn {
        position: fixed;
        left: 0;
        bottom: 0;
        z-index: 1000;
        width: 100%;
        height: 66px;
        font-size: 18px;
        color: #fff;
        line-height: 66px;
        text-align: center;
        background: rgba(223, 75, 36, 1);
    }
    .goods-info {
        margin: 4px 22px;
        .h3-title {
            position: relative;
            width: 100%;
            font-size: 18px;
            color: #000;
            line-height: 43px;
            font-weight: normal;
            &:after {
                .setBottomLine(#ccc);
            }
        }
        .tips1 {
            font-size: 16px;
            color: #df4b24;
            padding: 15px 0;
        }
        .price-box {
            position: relative;
            .yen {
                vertical-align: middle;
                color: #e63c0f;
                font-size: 18px;
                font-family: Arial;
                -webkit-font-smoothing: antialiased;
            }
            .price {
                vertical-align: middle;
                font-size: 30px;
                color: #e63c0f;
                font-weight: bolder;
                font-family: Arial;
                -webkit-font-smoothing: antialiased;
                font-weight: normal;
            }
        }
    }
    .address-box {
        position: relative;
        font-size: 16px;
        padding: 10px 0 15px;
        color: #444;
        &:after {
            .setBottomLine(#ccc);
        }
    }
    .tips2 {
        font-size: 13px;
        color: #c1c1c1;
        line-height: 20px;
        padding: 10px 0 0;
    }
}

.buy-dialog {
    position: absolute;
    bottom: 66px;
    left: 0;
    width: 100%;
    height: auto;
    background: #fff;
    .weui-mask {
        position: fixed;
        z-index: 1000;
        top: 0;
        right: 0;
        left: 0;
        bottom: 0;
        background: rgba(0, 0, 0, .6);
    }
    .buy-dialog-main {
        position: relative;
        z-index: 1001;
        left: 0;
        bottom: 0;
        background: #fff;
        padding: 10px 0;
        .close-btn {
            position: absolute;
            z-index:2500;
            right: 10px;
            top: 10px;
            border: 1px solid #ccc;
            border-radius: 30px;
        }
        .goods-box {
            position: relative;
            margin: 10px;
            overflow: hidden;
            padding: 10px 0 10px;
            &:after {
                .setBottomLine(#ccc);
            }
            .img {
                float: left;
                img {
                    width: 80px;
                    height: 80px;
                    border: 1px solid #f1f1f1;
                }
            }
            .img-t {
                position: relative;
                top: 5px;
                font-size: 16px;
                color: #000;
                margin-left: 1rem;
            }
        }
        .h4-title {
            @include sc(0.6rem, #555);
            font-weight: normal;
        }
        .goods-standard,
        .goods-num,
        .goods-total {
            position: relative;
            margin: 10px;
            &:after {
                .setBottomLine(#ccc);
            }
        }
        .btn-list {
            padding: 15px 0 20px;
            .weui-btn_default {
                background: #fff;
                font-size: 14px;
            }
            .active {
                color: #e63c0f;
            }
            .weui-btn.active:after {
                border-color: #e63c0f;
            }
        }
        .goods-num {
            .weui-cells {
                margin: 0;
                .weui-cell {
                    padding-left: 0;
                    padding-bottom: 20px;
                }
                &:before {
                    border-top: 0;
                }
            }
        }
        .goods-total {
            &:after {
                .setBottomLine(#fff);
            }
        }
    }
}


/* 开始过渡阶段,动画出去阶段 */

.fade-enter-active,
.fade-leave-active {
    transition: all 0.5s ease-out;
}


/* 进入开始 */

.fade-enter {
    transform: translateY(1000px);
    opacity: 0;
}


/* 出去终点 */

.fade-leave-active {
    transform: translateY(500px);
    opacity: 0;
}
.vux-flexbox-item{
  button.weui-btn, input.weui-btn{
        width: 91%;
  }
}

</style>

<template lang="html">

<div class="goods-main">
    <swiper :list="demo01_list" v-model="demo01_index" height="250px" @on-index-change="demo01_onIndexChange"></swiper>
    <div class="goods-info">
        <h3 class="h3-title">{{goodTitle}}</h3>
        <p class="tips1">{{goodSubTitle}}</p>
        <div class="price-box">
            <em class="yen">¥</em>
            <span class="price">{{goodPrice}}</span>
        </div>
        <div class="address-box">
            <p><span>发货地:{{goodAddress}}</span></p>
        </div>
        <div class="tips2">
            <p>{{goodDes}}</p>
        </div>
        <div class="buy-btn" @click="handlerBuyBtn">立即购买</div>
    </div>
    <transition name="fade">

        <div class="buy-dialog" v-if="show">
            <div class="weui-mask"></div>
            <div class="buy-dialog-main">
                <span class="close-btn" @click="handlerCloseBtn">
                    <span class="vux-close"></span>
                </span>
                <div class="goods-box">
                    <span class="img">
                  <img :src="thumb" alt="">
                </span>
                    <span class="img-t">{{goodName}}</span>
                </div>
                <div class="goods-standard">
                    <h4 class="h4-title">规格</h4>
                    <flexbox class="btn-list">
                      <flexbox-item v-for="(item,index) in goodsDatas" :key="index">
                          <x-button @click.native="selGood(item)" :class="{'active':item.active}">{{item.name}}</x-button>
                      </flexbox-item>
                    </flexbox>
                </div>
                <div class="goods-num">
                    <group>
                        <x-number title="数量" v-model="numValue" :min="1" @on-change="change"></x-number>
                    </group>
                </div>
                <div class="goods-total">
                    <h4 class="h4-title">共计:¥{{total}}</h4>
                </div>
                <div class="buy-btn" @click.active='subCart'>下一步</div>
            </div>
        </div>
    </transition>
</div>

</template>

<script>
import Vue from 'vue'
import {
    setStore, getStore ,getUrlKey
}
from '../../config/mUtils'
import {
    Swiper,
    XDialog,
    TransferDomDirective as TransferDom,
    XButton,
    Flexbox,
    FlexboxItem,
    Group,
    XNumber
}
from 'vux'

import {mapMutations} from 'vuex'

// const baseList = [{
//     url: 'javascript:',
//     img: 'https://static.vux.li/demo/1.jpg',
//     title: '送你一朵fua'
// }, {
//     url: 'javascript:',
//     img: 'https://static.vux.li/demo/2.jpg',
//     title: '送你一辆车'
// }, {
//     url: 'javascript:',
//     img: 'https://static.vux.li/demo/3.jpg',
//     title: '送你一次旅行'
// }]
export default {
    directives: {
        TransferDom
    },
    components: {
        Swiper,
        XDialog,
        XButton,
        Flexbox,
        FlexboxItem,
        Group,
        XNumber
    },
    data() {
        return {
            goodTitle:'',
            goodSubTitle:'',
            goodPrice:'',
            goodAddress:'',
            goodDes:'',
            goodIcon:'',
            goodsDatas:[],
            show: false,
            dialogShow: false,
            myAddress: false,
            demo01_list: [],
            demo01_index: 0,
            total: 0,
            numValue:1,
            price:0,
            thumb:'',
            goodName:'',
            goodId:'',
            goodNum:null,
            goodSpecId:null
        }
    },
    created(){
      if(getUrlKey('openId')){
        setStore('openId',getUrlKey('openId'))
      }
    },
    mounted() {
        this.getGoods()
    },
    methods: {
      ...mapMutations([
        'ADD_CART'
      ]),
        getGoods(){
          var datas = {
              "openId":getStore('openId')
          }
          // console.log(datas)
          this.$http.post(this.HttpUrl.UrlConfig.getGoods,datas)
                        .then(res => {
                          var res = res.data
                            if(res.code=='1'){// 成功

                              // this.demo01_list = advImgStr
                              this.goodName = res.data[0].goodName
                              this.goodTitle = res.data[0].goodTitle
                              this.goodSubTitle = res.data[0].goodSubTitle
                              this.goodPrice = res.data[0].goodPrice
                              this.goodAddress = res.data[0].goodAddress
                              this.goodDes = res.data[0].goodDes
                              this.goodIcon = res.data[0].goodIcon
                              this.goodsDatas = res.data[0].goodSpec
                              this.thumb = res.data[0].thumb
                              this.goodId = res.data[0].goodId


                              this.$nextTick(()=>{
                                Vue.set(this.goodsDatas[0],'active',true)
                                this.price = this.goodsDatas[0].price
                                this.goodSpecId = this.goodsDatas[0].id
                                this.goodSpeName = this.goodsDatas[0].name
                                this.total = parseInt(this.numValue) * this.price
                              })







                              // let advImg = res.data[0].advImg.split(',')
                              let advImgStr = res.data[0].advImg.split(',')
                              // console.log(advImgStr)
                              // console.log(advImg)

                              let advImgArr = []
                              advImgStr.forEach((val)=>{
                                // console.log(val)
                                advImgArr.push({
                                  url: 'javascript:',
                                  img: val,
                                  title: ''
                                })
                              })
                              this.demo01_list = advImgArr
                            }else{ // 异常
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
        demo01_onIndexChange(index) {
                this.demo01_index = index
            },
            // 新增地址
            addAddress() {
                this.dialogShow = true

            },
            change(val) {
                this.goodNum = parseInt(val)
                console.log('change', val)
                this.total = parseInt(val) * this.price
            },
            handlerBuyBtn() {
                this.show = true
            },
            handlerCloseBtn(){
              console.log(11)
              this.show = false
            },
            selGood(item){
                let that = this
                this.goodSpecId = item.id
                this.goodSpeName = item.name
                this.price = item.price
                console.log(item.id)
                this.$nextTick(()=>{
                  this.goodsDatas.forEach((item)=>{
                    Vue.set(item,'active',false)
                  })
                  Vue.set(item,'active',true)
                  this.price = item.price
                  this.total = parseInt(this.numValue) * this.price
                })
                this.numValue = 1
            },
            subCart(){

              let datas = {
                'openId':getStore('openId'),
                'goodId':this.goodId,
                'goodNum':this.goodNum,
                'goodSpecId':this.goodSpecId,
                'goodSpeName':this.goodSpeName,
                'price':this.price,
                'thumb':this.thumb,
                'goodName':this.goodName,
                'goodPrice':this.total,
                // 'address':this.address,
                // 'person':this.person,
                // 'phone':this.phone,
                // 'receiptType':this.receiptType,
                // 'receiptTitle':this.receiptTitle,
                // 'receiptNo':this.receiptNo,
                // 'buyerMessage':this.buyerMessage
              }
              setStore('buyInfo',datas)

              this.$router.push({
                path:'/confirm-order'
              })
              console.log(this.price)
            }
    }
}

</script>
