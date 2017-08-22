<style lang="scss">



</style>

<template lang="html">

<div class="">
    <h3 class="h3-t">商品管理</h3>
    <!-- btn -->
    <div class="btn-box">
        <el-button type="success" @click='addHandler'>新增商品</el-button>
        <el-button type="warning" @click='updateHandler'>修改商品</el-button>
        <el-button type="danger" @click='deleteHandler'>删除商品</el-button>
    </div>
    <!-- table list -->
    <div class="app-table">
        <el-table tooltip-effect="dark" @selection-change="handleSelectionChange" :data="tableData" border style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="productName" label="商品名称"></el-table-column>
            <el-table-column prop="title" label="主标"></el-table-column>
            <el-table-column prop="subTitle" label="副标"></el-table-column>
            <el-table-column prop="" label="说明文字"></el-table-column>
            <el-table-column prop="images" label="图片"></el-table-column>
            <el-table-column label="规格">
              <template scope="scope">
                <div v-for="(item,index) in scope.row.specs">{{item.specName}}</div>
              </template>
            </el-table-column>
            <el-table-column label="价格">
              <template scope="scope">
                <div v-for="(item,index) in scope.row.specs">{{item.specPrice}}</div>
              </template>
            </el-table-column>
            <el-table-column prop="" label="发货地"></el-table-column>
            <el-table-column label="状态">
              <template scope="scope">
                <!-- <span>{{scope.row.specs[0].specStatus}}</span> -->
              </template>
            </el-table-column>
        </el-table>
    </div>
    <!-- 新增、修改弹层 -->
    <add-update :dialogType='dialogType' ref="refDialog" @listenToSuccess='listenToSuccess'></add-update>
    <del-dialog delTitle='删除商品' ref="refDelDialog" @listenToDel='listenToDel'></del-dialog>


    <!-- 删除弹层 -->

</div>

</template>

<script>
import qs from 'qs'
import AddUpdate from './AddUpdate'
import DelDialog from '../../components/DelDialog'
export default {
    components : {
        AddUpdate,
        DelDialog
    },
    data() {
            return {
                tableData: [],
                dialogType:'',
                multipleSelection:[],
            }
        },
        create() {

        },
        mounted() {
            this.list()

        },
        updated() {

        },
        methods: {
          // 选择table 项
            handleSelectionChange(val) {
                console.log(val)
                this.multipleSelection = val
            },
            // 拉取列表
            list() {
                        this.$http.get(this.HttpUrl.UrlConfig.goodList)
                            .then(res => {
                                console.log(res)
                                res = res.data
                                this.tableData = res
                            }).catch(error => {
                                console.log(error)
                            })
                },

                // 新增
                addHandler() {
                    this.$refs.refDialog.childMethod('add','')
                },
                // 修改
                updateHandler() {
                  if (this.multipleSelection.length != 1) {
                      this.$message({
                          message: '请选择一项后在修改',
                          type: 'warning'
                      });
                      return;
                  }
                  this.$refs.refDialog.childMethod('update',this.multipleSelection[0].productId)
                },
                // 删除
                deleteHandler() {
                  if (this.multipleSelection.length != 1) {
                      this.$message({
                          message: '请选择一项后在删除',
                          type: 'warning'
                      });
                      return;
                  }
                  this.$refs.refDelDialog.childMethod()
                },
                listenToSuccess(data){
                  if(data.ret=='0'){
                    this.list()
                  }else{
                    this.$message({
                        message: data.msg,
                        type: 'warning'
                    });
                  }
                },
                listenToDel(data){
                  let datas = {
                    'goodId':this.multipleSelection[0].productId
                  }
                  this.$http.post(this.HttpUrl.UrlConfig.goodDel,qs.stringify(datas))
                      .then(res => {
                          res = res.data
                          if (res.ret == 0) {
                            this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                            this.list()
                            this.$refs.refDelDialog.delSucess()
                          } else {
                              this.$message.error(res.msg);
                          }
                      }).catch(error => {
                          console.log(error)
                      })
                }

        }
}

</script>
