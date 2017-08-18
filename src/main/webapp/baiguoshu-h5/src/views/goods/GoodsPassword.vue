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
                    path: '/comfirm-goods'
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

                let datas = {
                    "openId": "12345678", //类型：String  必有字段  备注：无
                    "code": "1", //类型：String  必有字段  备注：1成功0失败-1系统错误
                    "msg": "success", //类型：String  必有字段  备注：返回信息
                    "data": { //类型：Object  必有字段  备注：无
                        "goodName": "好柿饼", //类型：String  必有字段  备注：商品名称
                        "goodImg": "http://www.adasda.com/aaa.png", //类型：String  必有字段  备注：商品图片
                        "goodBatch": "此盒柿饼是好柿连连第一批122盒", //类型：String  必有字段  备注：商品批次
                        "reserveMember": "张三", //类型：String  必有字段  备注：预定会员
                        "goodId": "1234", //类型：String  必有字段  备注：商品ID
                        "takeCode": "12321", //类型：String  必有字段  备注：提货码
                        "sendTime": "2017年7月12日 12:00" //类型：String  必有字段  备注：发货时间
                    }
                }
                datas.data['takeCode'] = takeCode

                setStore('takeGoodInfo',datas.data)

                if (flag) {
                    let datas = {
                        'openId': '123456',
                        'takeCode': takeCode
                    }
                    this.$http.post(this.HttpUrl.UrlConfig.takeGood, datas)
                        .then(res => {
                            var res = res.data
                            if (res.code == "1") {

                                this.$router.push({
                                    path: '/comfirm-goods'
                                })
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


            },
            handelClick() {

            }
    }
}

</script>
