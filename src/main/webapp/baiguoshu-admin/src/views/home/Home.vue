<style lang="scss">
@import "../../assets/styles/mixin";
.home-container {
    position: absolute;
    top: 0px;
    bottom: 0px;
    width: 100%;
    .header {
        @include wh(100%, 50px);
        background-color: #3c8dbc;
        .u-sysname {
            @include sc(16px, #fff);
            line-height: 50px;
            padding: 0 0 0 20px;
        }
    }
    .app-container {
        width: 100%;
        display: flex;
        position: absolute;
        top: 0;
        bottom: 0px;
        overflow: hidden;
        .aside-menu {
            flex: 0 0 200px;
            width: 200px;
            background: #eef1f6;
            .el-menu-item.is-active {
                color: #3c8dbc;
            }
        }
        .app-main {
            flex: 1;
            overflow-y: scroll;
            .app-view{
              padding: 20px;
              .h3-t{
                position: relative;
                height: 30px;
                line-height: 30px;
                border-bottom: 1px solid #3c8dbc;
                @include sc(18px, #3c8dbc);
                margin-bottom: 20px;
              }
              .app-table{
                margin-top: 20px;
              }
              .dialog-form{
                .input{
                  width: 200px;
                }
              }
            }
        }
    }
}

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


.icon-order{
  background-image: url(../../assets/images/order.png);
}
.icon-goods{
  background-image: url(../../assets/images/goods.png);
}
.icon-content{
  background-image: url(../../assets/images/content.png);
}
.icon-user{
  background-image: url(../../assets/images/user.png);
}
</style>

<template lang="html">
<div class="home-container">
    <div class="app-container">
        <aside class="aside-menu">
            <el-menu :unique-opened="true" :default-active="$route.path" class="" @open="handleOpen" @close="handleClose">
                <template v-for="(item,index) in $router.options.routes" v-if="!item.hidden">
                    <el-submenu :index="item.name" v-if="!item.noDropdown">
                        <template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>
                        <router-link v-for="child in item.children" :key="child.path" v-if="!child.hidden" :to="child.path">
                            <el-menu-item :index="child.path">
                                {{child.name}}
                            </el-menu-item>
                        </router-link>
                    </el-submenu>
                    <router-link v-if="item.noDropdown&&item.children.length>0" :to="item.children[0].path">
                        <el-menu-item :index="item.children[0].path">
                            <i :class="item.iconCls"></i>{{item.children[0].name}}
                        </el-menu-item>
                    </router-link>
                </template>
            </el-menu>
        </aside>
        <section class="app-main">
          <div class="app-view">
            <transition name="fade" mode="out-in">
                <router-view></router-view>
            </transition>
          </div>
        </section>
    </div>
</div>
</template>
<script>

export default {
    created() {
        },
        components: {

        },
        data() {
            return {
            }
        },
        methods: {
            handleOpen(key, keyPath) {
                    console.log(key, keyPath);
                },
                handleClose(key, keyPath) {
                    console.log(key, keyPath);
                }
        }
}

</script>
