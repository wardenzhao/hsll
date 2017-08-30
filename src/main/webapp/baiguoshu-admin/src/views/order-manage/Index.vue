<style lang="scss" scoped>
.applyLook{
  color:#3c8dbc
}

.buyRecordbox .el-form-item{
  margin-bottom: 0
}
.header-box{
  display: flex;
  .h-left{
    flex-grow: 4
  }
  .h-right{
    text-align: right;
    flex-grow: 1
  }
}
</style>

<template lang="html">

<div class="">
    <h3 class="h3-t">订单管理</h3>
    <div class="header-box">
      <div class="h-left">
        <el-tabs v-model="activeName2" type="card" @tab-click="handleClick">
          <el-tab-pane label="全部" name="-1"></el-tab-pane>
          <el-tab-pane label="提货卡已发货" name="0"></el-tab-pane>
          <el-tab-pane label="提货卡未发货" name="1"></el-tab-pane>
          <el-tab-pane label="已发货" name="2"></el-tab-pane>
          <el-tab-pane label="未发货" name="3"></el-tab-pane>
        </el-tabs>
      </div>
      <div class="h-right">
        <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchName" placeholder="订单号/购买人姓名/提货码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="queryOrder">查询</el-button>
      </el-form-item>
    </el-form>
      </div>
    </div>


    <!-- table list -->
    <div class="app-table">
        <el-table ref="multipleTable" tooltip-effect="dark" @selection-change="handleTableChange" :data="tableData" border style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="orderNo" label="订单编号"></el-table-column>
            <el-table-column prop="orderType" :formatter="formatOrderType"  label="订单类型"></el-table-column>
            <el-table-column prop="orderDate" label="下单时间"></el-table-column>
            <el-table-column prop="buyUser" label="购买者"></el-table-column>
            <el-table-column prop="wxNumber" label="微信号"></el-table-column>
            <el-table-column prop="goodName" label="商品"></el-table-column>
            <el-table-column prop="address" label="收货信息"></el-table-column>
            <el-table-column prop="goodCode" label="提货码"></el-table-column>
            <el-table-column prop="batchInfo"  label="批次"></el-table-column>
            <el-table-column prop="orderStatus" :formatter="formatStatus" label="订单状态"></el-table-column>
            <el-table-column width="200" label="发票信息">
              <template scope="scope">
                <div v-for="(item,index) in JSON.parse(scope.row.taxInfo)" :key="index">
                  <div v-if="item">发票编号:{{item.receiptNo}}</div>
                  <div v-if="item">发票抬头:{{item.receiptTitle}}</div>
                  <div v-if="item">发票类型:{{item.receiptType}}</div>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="userReply" label="买家留言"></el-table-column>
            <el-table-column label="操作">
              <template scope="scope">
                <span class="applyLook" @click="clickApplyLook(scope.row.orderNo)">查看</span>
              </template>
            </el-table-column>
        </el-table>
        <div class="pager-box" v-if="total>pageSize">
                <el-pagination @current-change="handleCurrentChange" :page-size="pageSize" layout="total, prev, pager, next" :total="total">
                </el-pagination>
            </div>
    </div>
    <!-- 新增、修改弹层 -->
    <el-dialog title="dialogTitle" :visible.sync="dialogVisible" size="small" @close="resetForm('ruleForm')">
        <div class="dialog-form">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px">
                <el-form-item label="活动名称" prop="name">
                    <el-input class="input" v-model="ruleForm.name" placeholder="请输入活动名称"></el-input>
                </el-form-item>
                <el-form-item label="主标文字" prop="mainTxt">
                    <el-input class="input" v-model="ruleForm.mainTxt" placeholder="请输入主标文字"></el-input>
                </el-form-item>
                <el-form-item label="副标文字" prop="viceTxt">
                    <el-input class="input" v-model="ruleForm.viceTxt" placeholder="请输入副标文字"></el-input>
                </el-form-item>
                <el-form-item label="说明文字" prop="intro">
                    <el-input class="input" type="textarea" v-model="ruleForm.intro" placeholder="请输入说明文字"></el-input>
                </el-form-item>
                <el-form-item label="展示图片" prop="">

                </el-form-item>
                <el-form-item label="规格及价格设置" prop="">

                </el-form-item>

                <el-form-item label="规则提示" prop="specifications">
                    <el-input class="input" v-model="ruleForm.specifications" placeholder="请输入规则提示"></el-input>
                </el-form-item>
                <el-form-item label="发货地" prop="address">
                    <el-input class="input" v-model="ruleForm.address" placeholder="请输入发货地"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-dialog>
    <!-- 删除弹层 -->

    <el-dialog title="订单申请" :visible.sync="dialogVisible2" size="small">
        <div class="dialog-form buyRecordbox">
            <el-form label-width="150px">

              <el-form-item label="订单编号">
                  {{orderNo}}
              </el-form-item>
              <el-form-item label="下单时间">
                  {{orderDate}}
              </el-form-item>
              <el-form-item label="购买者">
                  {{buyUser}}
              </el-form-item>
              <el-form-item label="微信号">
                  {{wxNumber}}
              </el-form-item>
              <el-form-item label="商品">
                  {{goodName}}
              </el-form-item>
              <el-form-item label="收货信息">
                  {{address}}
              </el-form-item>
              <el-form-item label="提货码 ">
                  {{goodCode}}
              </el-form-item>
              <el-form-item label="批次 ">
                  {{batchInfo}}
              </el-form-item>
              <el-form-item label="订单状态 ">
                  <span v-if="orderStatus=='0'">提货卡未发货</span>
                  <span v-if="orderStatus=='1'">提货卡已发货</span>
                  <span v-if="orderStatus=='2'">未发货</span>
                  <span v-if="orderStatus=='3'">已发货</span>
              </el-form-item>
              <el-form-item label="发票信息 ">

                <div v-for="(item,index) in taxInfo" :key="index">
                  <div v-if="item">发票编号:{{item.receiptNo}}</div>
                  <div v-if="item">发票抬头:{{item.receiptTitle}}</div>
                  <div v-if="item">发票类型:{{item.receiptType}}</div>
                </div>
              </el-form-item>
              <el-form-item label="买家留言 ">
                  {{userReply}}
              </el-form-item>
                <el-form-item>
                    <el-button v-if="orderStatus=='0' || orderStatus=='2'" type="primary" @click="deliveryClick(orderNo,orderStatus)">确认发货</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-dialog>
</div>

</template>

<script>

export default {
    data() {
            return {
              dialogVisible2:false,
              pageNo: 0,
              pageSize: 10,
              total: null,
              status:'',
                activeName2: '-1',
                tableData: [],
                dialogVisible: false,
                dialogTitle: '',
                ruleForm: {
                    name: '',
                    mainTxt:'',
                    viceTxt:'',
                    intro:'',
                    specifications:'',
                    address:'',
                },
                rules: {
                    name: [{
                        required: true,
                        message: '活动名称20字以内',
                        trigger: 'blur change',
                        max:20,
                        min:1
                    }],
                    mainTxt: [{
                        required: true,
                        message: '主标文字20字以内',
                        trigger: 'blur change',
                        max:20,
                        min:1
                    }],
                    viceTxt: [{
                        required: true,
                        message: '副标文字20字以内',
                        trigger: 'blur change',
                        max:20,
                        min:1
                    }],
                    intro: [{
                        required: true,
                        message: '说明文字100字以内',
                        trigger: 'blur change',
                        max:100,
                        min:1
                    }],
                    specifications: [{
                        required: true,
                        message: '规格提示30字以内',
                        trigger: 'blur change',
                        max:30,
                        min:1
                    }],
                    address: [{
                        required: true,
                        message: '发货地20字以内',
                        trigger: 'blur change',
                        max:20,
                        min:1
                    }],
                },
                orderNo:'',
                orderDate:'',
                buyUser:'',
                wxNumber:'',
                goodName:'',
                address:'',
                goodCode:'',
                batchInfo:'',
                orderStatus:'',
                taxInfo:'',
                userReply:'',
                searchName:''
            }
        },
        create() {

        },
        mounted() {
          this.orderList()
          // this.queryOrder()
        },
        updated() {

        },
        methods: {
            formatOrderType(row, column) {
                var columnV = row[column.property];
                if (columnV === undefined) {
                    return ''
                }
                var orderType = '';
                switch (columnV) {
                    case "1":
                        orderType = "提货";
                        break;
                    case "0":
                        orderType = "购买";
                        break;
                    default:
                        break;
                }
                return orderType;
            },
            formatStatus(row, column) {
                var columnV = row[column.property];
                if (columnV === undefined) {
                    return ''
                }
                var orderType = '';
                switch (columnV) {
                    case "0":
                        orderType = "提货卡未发货";
                        break;
                    case "1":
                        orderType = "提货卡已发货";
                        break;
                    case "2":
                        orderType = "未发货";
                        break;
                    case "3":
                        orderType = "已发货";
                        break;
                    default:
                        break;
                }
                return orderType;
            },
            orderList(){
              this.$http.get(this.HttpUrl.UrlConfig.orderList+'?pageNo='+this.pageNo + '&pageSize='+this.pageSize + '&status='+this.activeName2)
                  .then(res => {
                      res = res.data
                      this.tableData = res.orderInfos
                      this.total = res.listCount
                  }).catch(error => {
                      console.log(error)
                  })
            },
            handleCurrentChange(val){
              this.pageNo = parseInt(val)-1
              this.orderList()
            },
            // 选择table 项
            handleTableChange() {

                },
                // 新增
                addHandler() {

                    this.dialogTitle = "新增商品"
                    this.dialogVisible = true

                },
                // 修改
                updateHandler() {
                    this.dialogTitle = "修改商品"
                    this.dialogVisible = true
                },
                // 删除
                deleteHandler() {

                },
                // 提交
                submitForm(formName) {
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            this.handleSubmitServer;
                        } else {
                            return false;
                        }
                    });
                },
                // 提交服务
                handleSubmitServer(){

                },
                // 关闭回调清空数据
                handleClose(){

                },
                resetForm(formName) {
                  this.$refs[formName].resetFields();
                },
                handleClick(tab, event) {
                  console.log(this.activeName2)
                  this.orderList()
                },
                clickApplyLook(orderNo){
                  this.dialogVisible2 = true
                  this.$http.get(this.HttpUrl.UrlConfig.orderDetail+'?orderNo='+orderNo)
                      .then(res => {
                          res = res.data
                          this.orderNo=res.orderNo,
                          this.orderDate=res.orderDate,
                          this.buyUser=res.buyUser,
                          this.wxNumber=res.wxNumber,
                          this.goodName=res.goodName,
                          this.address=res.address,
                          this.goodCode=res.goodCode,
                          this.batchInfo=res.batchInfo,
                          this.orderStatus=res.orderStatus,
                          this.taxInfo=JSON.parse(res.taxInfo),
                          this.userReply=res.userReply
                      }).catch(error => {
                          console.log(error)
                      })

                },
                deliveryClick(orderNo,status ){
                    let status1 =''
                    if(status=='0'){
                      status1 = '1'
                    }else if(status=='2'){
                      status1 = '3'
                    }
                    this.$http.get(this.HttpUrl.UrlConfig.orderSend+'?orderNo='+orderNo + '&status='+status1)
                        .then(res => {
                            res = res.data
                            if(res.ret == 0){
                              this.$message({
                                  message: '发货成功',
                                  type: 'success'
                              });
                              this.dialogVisible2 = false
                              this.pageNo = 0
                              this.orderList()
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
                queryOrder(){
                    if(this.searchName==''){
                      this.$message({
                          message: '请输入订单号/购买人姓名/提货码',
                          type: 'warning'
                      });
                      return
                    }
                    this.$http.get(this.HttpUrl.UrlConfig.orderSearch+'?pageNo='+this.pageNo + '&pageSize='+this.pageSize +'&searchName='+this.searchName)
                        .then(res => {
                            res = res.data
                            this.tableData = res.orderInfos
                            this.total = res.listCount
                        }).catch(error => {
                            console.log(error)
                        })
                },

        }
}

</script>
