<style lang="scss">



</style>

<template lang="html">

<div class="">
    <h3 class="h3-t">批次管理</h3>
    <!-- btn -->
    <div class="btn-box">
        <el-button type="success" @click='addHandler'>新增批次</el-button>
    </div>
    <!-- table list -->
    <div class="app-table">
        <el-table ref="multipleTable" tooltip-effect="dark" :data="tableData" border style="width: 100%">
            <el-table-column width="80" type="index" label="序号"></el-table-column>
            <el-table-column prop="createTime" label="生成时间"></el-table-column>
            <el-table-column prop="goodsName" label="商品"></el-table-column>
            <el-table-column prop="goodSpecName" label="规格"></el-table-column>
            <el-table-column prop="name" label="批次"></el-table-column>
            <el-table-column prop="number" label="数量"></el-table-column>
            <el-table-column prop="usedNum" label="已用数量"></el-table-column>
            <el-table-column prop="goodsCode" label="提货码">
              <template scope="scope">
                <span @click="clickBatchLook(scope.row.batchCode)">查看</span>
              </template>
            </el-table-column>
        </el-table>
        <div class="pager-box" v-if="total>pageSize">
                <el-pagination @current-change="handleCurrentChange" :page-size="pageSize" layout="total, prev, pager, next" :total="total">
                </el-pagination>
            </div>
    </div>

    <el-dialog title="新增批次" :visible.sync="dialogVisible" size="small" @close="resetForm('ruleForm')">
        <div class="dialog-form">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px">
                <el-form-item label="商品" prop="goodsInfo">
                  <el-select v-model="ruleForm.goodsInfo" placeholder="请选择商品" @change="goodsInfoChange">
                    <el-option v-for="(item,index) in getGoodsInfoArr" :key="item.id" :label="item.name" :value="item.id"> {{item.name}}</el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="规格" prop="goodSpec">
                  <el-select v-model="ruleForm.goodSpec" placeholder="请选择规则" @click="goodSpecChange">
                    <el-option v-for="(item,index) in getGoodSpecArr" :key="item.id" :label="item.specName" :value="item.id"> {{item.specName}}</el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="批次" prop="name">
                  第<el-input class="input"  v-model="ruleForm.name" placeholder="请输入批次"></el-input>批
                </el-form-item>
                <el-form-item label="数量" prop="number">
                  <el-input class="input"  v-model="ruleForm.number" placeholder="请输入数量"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-dialog>



    <el-dialog title="批次详情" :visible.sync="dialogBatchVisible" size="large">
        <div class="dialog-form">
          <el-table ref="multipleTable" tooltip-effect="dark" :data="tableData" border style="width: 100%">
              <el-table-column width="80" type="index" label="序号"></el-table-column>
              <el-table-column prop="" label="提货码"></el-table-column>
              <el-table-column prop="" label="生成时间"></el-table-column>
              <el-table-column prop="" label="批次"></el-table-column>
              <el-table-column prop="" label="商品"></el-table-column>
              <el-table-column prop="" label="规格"></el-table-column>
          </el-table>
          <div class="pager-box" v-if="total2>pageSize2">
              <el-pagination @current-change="handleCurrentChange2" :page-size="pageSize2" layout="total, prev, pager, next" :total="total2">
              </el-pagination>
          </div>
        </div>
    </el-dialog>
</div>

</template>

<script>
import qs from 'qs'
export default {
    data() {
            return {
                tableData: [],
                dialogVisible:false,
                dialogBatchVisible:false,
                pageNo: 0,
                pageSize: 10,
                total: null,
                pageNo2: 0,
                pageSize2: 10,
                total2: null,
                getGoodsInfoArr:[],
                getGoodSpecArr:[],
                ruleForm: {
                    goodsInfo:'',
                    goodSpec:'',
                    name:'',
                    number:''
                },
                rules: {
                    // goodsInfo: [{
                    //     required: true,
                    //     message: '请选择商品',
                    //     trigger: 'change',
                    // }],
                    // goodSpec: [{
                    //     required: true,
                    //     message: '请选择规则',
                    //     trigger: 'change',
                    // }],
                    // name: [{
                    //     required: true,
                    //     message: '请输入批次',
                    //     trigger: 'blur change'
                    // }],
                    // number: [{
                    //     required: true,
                    //     message: '请输入数量',
                    //     trigger: 'blur change'
                    // }]
                },
                specId:'',
                goodId:'',
                batchCode:''
            }
        },
        create() {

        },
        mounted() {
          this.batchList()
          this.getGoodsInfo()
        },
        updated() {

        },
        methods: {
                batchList(){
                  this.$http.get(this.HttpUrl.UrlConfig.batchList+'?pageNo='+this.pageNo + '&pageSize='+this.pageSize)
                      .then(res => {
                          res = res.data
                          this.tableData = res.batchList
                          this.total =parseInt(res.listCount)

                      }).catch(error => {
                          console.log(error)
                      })
                },
                handleCurrentChange(val) {
                    this.pageNo = val;
                    this.batchList()
                },
                handleCurrentChange2(val) {
                    this.pageNo2 = val;
                    this.batchDetailsList()
                },
                // 新增
                addHandler() {
                    this.dialogTitle = "新增批次"
                    this.dialogVisible = true

                },
                // 批次获取商品列表
                getGoodsInfo(){
                  this.$http.get(this.HttpUrl.UrlConfig.getGoodsInfo)
                      .then(res => {
                          res = res.data
                          this.getGoodsInfoArr = res

                      }).catch(error => {
                          console.log(error)
                      })
                },
                goodsInfoChange(val){
                  this.goodId = val
                  this.getGoodSpecArr = []
                  this.ruleForm.goodSpec = ''
                  this.getGoodSpec(val)
                },
                goodSpecChange(val){
                  this.specId = val

                },
                // 批次获取商品规格
                getGoodSpec(goodId){
                  this.$http.get(this.HttpUrl.UrlConfig.getGoodSpec + "?goodId="+goodId)
                      .then(res => {
                          res = res.data
                          this.getGoodSpecArr = res
                      }).catch(error => {
                          console.log(error)
                      })
                },
                submitForm(formName) {
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            this.handleSubmitServer();
                        } else {
                            return false;
                        }
                    });
                },
                resetForm(formName) {
                    this.$refs[formName].resetFields();
                },
                // 提交服务
                handleSubmitServer() {
                    let datas = {
                      'goodId':this.goodId,
                      'specId':this.specId,
                      'name':this.ruleForm.name,
                      'number':this.ruleForm.number
                    }
                    this.$http.post(this.HttpUrl.UrlConfig.batchAdd,qs.stringify(datas))
                        .then(res => {
                            res = res.data
                            if(res.ret == '0'){
                              this.$message({
                                  message: '新建成功',
                                  type: 'success'
                              });
                              this.dialogVisible = false
                              this.batchList()
                            }else{
                              this.$message({
                                  message: res.msg,
                                  type: 'warning'
                              });
                            }

                        }).catch(error => {
                            console.log(error)
                        })
                },
                batchDetailsList(){

                },
                clickBatchLook(batchCode){
                  this.dialogBatchVisible = true
                  this.viewGoodsCode(batchCode)
                },
                viewGoodsCode(batchCode){
                  this.$http.get(this.HttpUrl.UrlConfig.viewGoodsCode+'?batchCode='+batchCode + '&pageNo=' + this.pageNo2 + '&pageSize='+this.pageSize2)
                      .then(res => {
                          res = res.data
                          if(res.ret == '0'){
                            this.$message({
                                message: '新建成功',
                                type: 'success'
                            });
                            this.dialogVisible = false
                            this.batchList()
                          }else{
                            this.$message({
                                message: res.msg,
                                type: 'warning'
                            });
                          }

                      }).catch(error => {
                          console.log(error)
                      })
                }
        }
}

</script>
