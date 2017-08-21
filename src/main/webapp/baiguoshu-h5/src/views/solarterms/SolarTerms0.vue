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

</style>

<template lang="html">

<div class="goods-main">
    <swiper loop="true" dots-position="center" :list="demo01_list" v-model="demo01_index" height="250px" @on-index-change="demo01_onIndexChange"></swiper>
</div>

</template>

<script>

import {
    Swiper
}
from 'vux'



const baseList = [{
    url: 'javascript:',
    img: 'https://static.vux.li/demo/1.jpg',
    title: '送你一朵fua'
}, {
    url: 'javascript:',
    img: 'https://static.vux.li/demo/2.jpg',
    title: '送你一辆车'
}, {
    url: 'javascript:',
    img: 'https://static.vux.li/demo/3.jpg',
    title: '送你一次旅行'
}]
export default {
    components: {
        Swiper
    },
    data() {
        return {
            demo01_list: baseList,
            demo01_index: 0,
        }
    },
    mounted() {
    },
    methods: {
        demo01_onIndexChange(index) {
                this.demo01_index = index
            },
    }
}

</script>
