<style lang="scss" scoped>


.buyLook{
  color:#3c8dbc
}
.addressList{
  margin: 10px 0 0;
}
.buyRecordbox .el-form-item{
  margin-bottom: 0
}
.df .el-form-item__content{
  margin-left: 20px
}
.list-line{
  border-bottom: 1px solid #ccc;
  margin-bottom: 10px;
}
</style>

<template lang="html">

<div class="">
    <h3 class="h3-t">会员管理</h3>
    <!-- btn -->
    <div class="btn-box">
        <el-button type="success" @click='addHandler'>新增会员</el-button>
        <el-button type="warning" @click='updateHandler'>修改会员</el-button>
        <el-button type="danger" @click='deleteHandler'>删除会员</el-button>
    </div>
    <!-- table list -->
    <div class="app-table">
        <el-table ref="multipleTable" tooltip-effect="dark" @selection-change="handleTableChange" :data="tableData" border style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="wxNumber" label="微信号"></el-table-column>
            <el-table-column prop="" label="微信名"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="phone" label="手机号码"></el-table-column>
            <el-table-column prop="sex" :formatter="formatSex" label="性别"></el-table-column>
            <el-table-column  width="200" label="地址信息">
              <template scope="scope">
                  <div class="addressList" v-for="(item,index) in JSON.parse(scope.row.address)" :key="index">
                      <div class="">
                        {{item.person}}({{item.phone}})
                      </div>
                      <div class="">
                        {{item.address}}
                      </div>
                  </div>
              </template>
            </el-table-column>
            <el-table-column prop="inviteCode" label="会员邀请码"></el-table-column>
            <el-table-column label="购买记录">
              <template scope="scope">
                <span class="buyLook" @click="clickBuyLook(scope.row.id)">购买记录</span>
              </template>
            </el-table-column>
        </el-table>
        <div class="pager-box" v-if="total>pageSize">
                <el-pagination @current-change="handleCurrentChange" :page-size="pageSize" layout="total, prev, pager, next" :total="total">
                </el-pagination>
            </div>
    </div>
    <!-- 新增、修改弹层 -->
    <el-dialog title="新增会员" :visible.sync="dialogVisible" size="small">
        <div class="dialog-form">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px">
                <el-form-item label="姓名" prop="name">
                    <el-input class="input" v-model="ruleForm.name" placeholder="请输入姓名"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="phone">
                    <el-input class="input" v-model="ruleForm.phone" placeholder="请输入手机号码"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="sex">
                  <el-radio-group v-model="ruleForm.sex">
                    <el-radio label="1">男</el-radio>
                    <el-radio label="0">女</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-dialog>
    <!-- 删除弹层 -->
    <el-dialog title="修改会员" :visible.sync="updateDialogVisible" size="small">
        <div class="dialog-form">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px">
              <el-form-item label="微信号">
                  <el-input class="input" v-model="wechatId" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="微信名">
                  <el-input class="input" v-model="wechatName" :disabled="true"></el-input>
              </el-form-item>
                <el-form-item label="姓名" prop="name2">
                    <el-input class="input" v-model="ruleForm.name2" placeholder="请输入姓名"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="phone2">
                    <el-input class="input" v-model="ruleForm.phone2" placeholder="请输入手机号码"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="sex2">
                  <el-radio-group v-model="ruleForm.sex2">
                    <el-radio label="1">男</el-radio>
                    <el-radio label="0">女</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="地址信息">
                  <div class="addressList" v-for="(item,index) in addressList" :key="index">
                      <div class="">
                        {{item.person}}({{item.phone}})
                      </div>
                      <div class="">
                        {{item.address}}
                      </div>
                  </div>

                </el-form-item>
                <el-form-item label="会员邀请码">
                    <el-input class="input" v-model="InviteCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm2('ruleForm')">确定</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-dialog>
    <el-dialog title="购买记录" :visible.sync="recordDialogVisible" size="small">
        <div class="dialog-form buyRecordbox">
          <el-form class="list-line" label-width="80px" v-for="(item,index) in buyRecord" :key="index">
            <el-form-item label="订单编号" class="" >
              {{item.orderNo}}
            </el-form-item>
            <el-form-item label="下单时间" class="">
              {{item.orderTime}}
            </el-form-item>
            <el-form-item label="" class="df">
                <h2>{{item.specName}}</h2>
            </el-form-item>
            <el-form-item label="收货地址" >
                {{item.recAddress}}
            </el-form-item>
            <el-form-item label="收货人" >
                {{item.recPerson}}
            </el-form-item>
            <el-form-item label="电话号码" >
                {{item.recPhone}}
            </el-form-item>
            <el-form-item label="订单状态" >
                <span v-if="item.status==0">提货卡未发货</span>
                <span v-if="item.status==1">提货卡已发货</span>
                <span v-if="item.status==2">未发货</span>
                <span v-if="item.status==3">已发货</span>
            </el-form-item>
          </el-form>
        </div>
    </el-dialog>
    <del-dialog delTitle='删除会员' ref="refDelDialog" @listenToDel='listenToDel'></del-dialog>
</div>

</template>

<script>
import qs from 'qs'
import DelDialog from '../../components/DelDialog'
export default {
  components : {
      DelDialog
  },
    data() {
            return {
                tableData: [],
                dialogVisible: false,
                updateDialogVisible: false,
                recordDialogVisible:false,
                dialogTitle: '',
                wechatId:'',
                wechatName:'',
                InviteCode:'',
                pageNo: 0,
                pageSize: 10,
                total: null,
                ruleForm: {
                    name: '',
                    phone:'',
                    name2: '',
                    phone2:'',
                    sex:'',
                    sex2:''
                },
                rules: {
                    name: [{
                        required: true,
                        message: '请输入姓名',
                        trigger: 'blur change',
                    }],
                    phone: [{
                        required: true,
                        validator: (rule, value, callback) => {
                            if (/^\d+$/.test(value) == false) {
                                callback(new Error("手机号只能输入数字"));
                            } else if (/^1[3|4|5|6|7|8|9][0-9]{1}[0-9]{8}$/.test(value) == false) {
                                callback(new Error("手机号格式不正确"));
                            } else {
                                callback();
                            }
                        },
                        trigger: 'blur change',
                    }],
                    name2: [{
                        required: true,
                        message: '请输入姓名',
                        trigger: 'blur change',
                    }],
                    phone2: [{
                        required: true,
                        validator: (rule, value, callback) => {
                            if (/^\d+$/.test(value) == false) {
                                callback(new Error("手机号只能输入数字"));
                            } else if (/^1[3|4|5|6|7|8|9][0-9]{1}[0-9]{8}$/.test(value) == false) {
                                callback(new Error("手机号格式不正确"));
                            } else {
                                callback();
                            }
                        },
                        trigger: 'blur change',
                    }],
                    sex:[{
                      required: true,
                      message: '请选择男女',
                      trigger: 'blur change',
                    }],
                    sex2:[{
                      required: true,
                      message: '请选择男女',
                      trigger: 'blur change',
                    }]
                },
                multipleSelection:[],
                addressList:[],
                buyRecord:[]
            }
        },
        create() {

        },
        mounted() {
          this.memberList()
        },
        updated() {

        },
        methods: {
          // 获取会员列表
          memberList(){
            this.$http.get(this.HttpUrl.UrlConfig.memberList+'?pageNo=' + this.pageNo + '&pageSize='+this.pageSize)
                .then(res => {
                    console.log(res)
                    res = res.data
                    this.tableData = res.memberList
                    this.total = parseInt(res.listCount)
                }).catch(error => {
                    console.log(error)
                })
          },
          // 格式化
                formatSex(row, column) {
                    var columnV = row[column.property];
                    if (columnV === undefined) {
                        return ''
                    }
                    var sex = '';
                    switch (columnV) {
                        case 1:
                            sex = "男";
                            break;
                        case 0:
                            sex = "女";
                            break;
                        default:
                            break;
                    }
                    return sex;
                },
            // 选择table 项
            handleTableChange(val) {
                this.multipleSelection = val
                },
                // 新增
                addHandler() {

                    this.dialogTitle = "新增商品"
                    this.dialogVisible = true

                },
                // 修改
                updateHandler() {
                  console.log(this.multipleSelection)
                  if (this.multipleSelection.length != 1) {
                      this.$message({
                          message: '请选择一项后在修改',
                          type: 'warning'
                      });
                      return;
                  }
                    this.dialogTitle = "修改商品"
                    this.updateDialogVisible = true
                    this.memberDetail()
                },
                memberDetail(){
                  let datas = {
                    'id':this.multipleSelection[0].id
                  }
                  this.$http.post(this.HttpUrl.UrlConfig.memberDetail,qs.stringify(datas))
                      .then(res => {
                          console.log(res)
                          res = res.data
                          this.wechatId = ''
                          this.wechatName = ''
                          this.ruleForm.name2 = res.name
                          this.ruleForm.phone2 = res.phone
                          this.InviteCode = res.inviteCode
                          this.ruleForm.sex2 = res.sex +''
                          this.addressList = JSON.parse(res.address)

                      }).catch(error => {
                          console.log(error)
                      })
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
                listenToDel(){
                  let datas = {
                    'id':this.multipleSelection[0].id
                  }
                  this.$http.post(this.HttpUrl.UrlConfig.memberDel,qs.stringify(datas))
                      .then(res => {
                          res = res.data
                          if (res.ret == 0) {
                            this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                            this.pageNo = 0
                            this.memberList()
                            this.$refs.refDelDialog.delSucess()
                          } else {
                              this.$message.error(res.msg);
                          }
                      }).catch(error => {
                          console.log(error)
                      })
                },
                // 新增提交
                submitForm(formName) {
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            this.handleSubmitServer();
                        } else {
                            return false;
                        }
                    });
                },
                // 修改提交
                submitForm2(formName) {
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            this.handleSubmitServer2();
                        } else {
                            return false;
                        }
                    });
                },
                // 新增提交服务
                handleSubmitServer(){
                  let datas = {
                    'name':this.ruleForm.name,
                    'phone':this.ruleForm.phone,
                    'sex':this.ruleForm.sex
                  }
                  this.$http.post(this.HttpUrl.UrlConfig.memberAdd,datas)
                      .then(res => {
                          res = res.data
                          if (res.ret === 0) {
                            this.$message({
                                message: '新增成功',
                                type: 'success'
                            });
                            this.dialogVisible = false
                            this.memberList()
                          } else {
                              this.$message.error(res.msg);
                          }
                      }).catch(error => {
                          console.log(error)
                      })
                },
                handleSubmitServer2(){
                  let datas = {
                    'id':this.multipleSelection[0].id,
                    'name':this.ruleForm.name2,
                    'phone':this.ruleForm.phone2,
                    'sex':this.ruleForm.sex2
                  }
                  this.$http.post(this.HttpUrl.UrlConfig.memberUpdate,datas)
                      .then(res => {
                          res = res.data
                          if (res.ret === 0) {
                            this.$message({
                                message: '修改成功',
                                type: 'success'
                            });
                            this.updateDialogVisible = false
                            this.memberList()
                          } else {
                              this.$message.error(res.msg);
                          }
                      }).catch(error => {
                          console.log(error)
                      })

                },
                // 关闭回调清空数据
                handleClose(){

                },
                resetForm(formName) {
                  this.$refs[formName].resetFields();
                },
                // 会员购买记录
                clickBuyLook(id){
                  this.recordDialogVisible = true
                  this.$http.get(this.HttpUrl.UrlConfig.memberBuyInfo+'?id='+id)
                      .then(res => {
                          res = res.data

                          this.buyRecord = res
                      }).catch(error => {
                          console.log(error)
                      })
                }

        }
}

</script>
