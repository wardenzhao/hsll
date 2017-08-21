<style lang="scss">



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
            <el-table-column prop="sex" label="性别"></el-table-column>
            <el-table-column prop="address" label="地址信息"></el-table-column>
            <el-table-column prop="inviteCode" label="会员邀请码"></el-table-column>
            <el-table-column prop="" label="购买记录"></el-table-column>
        </el-table>
        <div class="pager-box" v-if="total>pageSize">
                <el-pagination @current-change="handleCurrentChange" :page-size="pageSize" layout="total, prev, pager, next" :total="total">
                </el-pagination>
            </div>
    </div>
    <!-- 新增、修改弹层 -->
    <el-dialog title="新增会员" :visible.sync="dialogVisible" size="small" @close="resetForm('ruleForm')">
        <div class="dialog-form">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px">
                <el-form-item label="姓名" prop="name">
                    <el-input class="input" v-model="ruleForm.name" placeholder="请输入姓名"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="phone">
                    <el-input class="input" v-model="ruleForm.phone" placeholder="请输入手机号码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-dialog>
    <!-- 删除弹层 -->
    <el-dialog title="修改会员" :visible.sync="updateDialogVisible" size="small" @close="resetForm('ruleForm')">
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
                <el-form-item label="地址信息">
                    <!-- <el-input class="input" v-model="wechatName" :disabled="true"></el-input> -->
                    <p>ddd</p>
                    <p>ddd</p>
                    <p>ddd</p>
                    <p>ddd</p>
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
    <el-dialog title="购买记录" :visible.sync="recordDialogVisible" size="small" @close="resetForm('ruleForm')">
        <div class="dialog-form">

        </div>
    </el-dialog>
</div>

</template>

<script>

export default {
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
                pageNo: 1,
                pageSize: 10,
                total: null,
                ruleForm: {
                    name: '',
                    phone:'',
                    name2: '',
                    phone2:''
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
                },
                multipleSelection:[]
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
            // 选择table 项
            handleTableChange() {
                this.multipleSelection = val
                },
                // 新增
                addHandler() {

                    this.dialogTitle = "新增商品"
                    this.dialogVisible = true

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
                    this.dialogTitle = "修改商品"
                    this.dialogVisible = true
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
                  let datas = {
                    'id':this.multipleSelection[0].id
                  }
                  this.$http.post(this.HttpUrl.UrlConfig.memberDel,datas)
                      .then(res => {
                          res = res.data
                          if (res.ret === "0") {
                            this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                            this.list()
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
                    'phone':this.ruleForm.phone
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
                    'phone':this.ruleForm.phone2
                  }
                  this.$http.post(this.HttpUrl.UrlConfig.memberUpdate,datas)
                      .then(res => {
                          res = res.data
                          if (res.ret === 0) {
                            this.$message({
                                message: '修改成功成功',
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
                // 关闭回调清空数据
                handleClose(){

                },
                resetForm(formName) {
                  this.$refs[formName].resetFields();
                },
                // 获取会员列表
                memberList(){
                  this.$http.get(this.HttpUrl.UrlConfig.memberList)
                      .then(res => {
                          console.log(res)
                          res = res.data
                          this.tableData = res
                      }).catch(error => {
                          console.log(error)
                      })
                }
        }
}

</script>
