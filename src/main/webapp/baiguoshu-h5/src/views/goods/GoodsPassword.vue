<style lang="less">

.goods-pwd {
    overflow: hidden;
    clear: both;
    .goods-pwd-content {
        width: 308px;
        height: 162px;
        border: 1px solid #ccc;
        margin: 144px auto 40px;
        border-radius: 10px;
        .txt {
            line-height: 50px;
            font-size: 18px;
            text-align: center;
            color: #000;
            border-bottom: 1px solid #ccc;
        }
        .txt2 {
            text-align: center;
            line-height: 50px;
            font-size: 16px;
        }
        .input-list {
            margin: 0 auto;
            width: 279px;
            clear: both;
            input {
                float: left;
                border: 1px solid #ccc;
                width: 30px;
                height: 30px;
                display: inline-block;
                margin-right: -1px;
                text-align: center;
                font-size: 16px;
            }
        }
    }
}

.wechat-btn {
    margin: 30px 30px 60px;
}

</style>

<template lang="html">

<div class="goods-pwd">
    <div class="goods-pwd-content">
        <div class="txt">请输入提货密码</div>
        <p class="txt2">好柿连连</p>
        <div class="input-list">
            <input v-for="(n,index) in nums" maxlength="1" type="text" name="" v-model="n.num">
        </div>
    </div>
    <div class="wechat-btn">
        <x-button type="primary" @click.native="takeGood">确定</x-button>
    </div>
</div>

</template>

<script>

import {
    setStore, getStore
}
from '../../config/mUtils'
import {
    XButton
}
from 'vux'
export default {
    components: {
        XButton
    },
    data() {
        return {
            nums: [{
                num: ''
            }, {
                num: ''
            }, {
                num: ''
            }, {
                num: ''
            }, {
                num: ''
            }, {
                num: ''
            }, {
                num: ''
            }, {
                num: ''
            }, {
                num: ''
            }],
            reserveMember: ''
        }
    },
    created() {
        document.title = "提货密码"
    },
    mounted() {

    },
    methods: {
        takeGood() {

          this.$router.push({
              path: '/comfirm-goods',
              query: { takeCode: '123456789' }
          })



                let takeCode = '',
                    flag = true
                this.nums.forEach((val, index) => {
                    if (!val.num) {
                        this.$vux.toast.text('请正确输入提货密码')
                        flag = false
                        return false
                    }
                    takeCode += val.num
                })
                console.log(takeCode)
                if (flag) {
                  this.$router.push({
                      path: '/comfirm-goods',
                      query:{
                        takeCode:takeCode
                      }
                  })


                }


            },
            handelClick() {

            }
    }
}

</script>
