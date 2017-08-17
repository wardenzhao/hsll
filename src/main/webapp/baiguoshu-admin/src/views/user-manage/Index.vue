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
            <el-table-column prop="" label="微信号"></el-table-column>
            <el-table-column prop="" label="微信名"></el-table-column>
            <el-table-column prop="" label="姓名"></el-table-column>
            <el-table-column prop="" label="手机号码"></el-table-column>
            <el-table-column prop="" label="性别"></el-table-column>
            <el-table-column prop="" label="地址信息"></el-table-column>
            <el-table-column prop="" label="会员邀请码"></el-table-column>
            <el-table-column prop="" label="购买记录"></el-table-column>
        </el-table>
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
                <el-form-item label="姓名" prop="name">
                    <el-input class="input" v-model="ruleForm.name" placeholder="请输入姓名"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="phone">
                    <el-input class="input" v-model="ruleForm.phone" placeholder="请输入手机号码"></el-input>
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
                    <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
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
                wechatId:'微信号',
                wechatName:'微信名',
                InviteCode:'邀请码',
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
                    phone: [{
                        required: true,
                        message: '主标文字20字以内',
                        trigger: 'blur change',
                        max:20,
                        min:1
                    }]
                }

            }
        },
        create() {

        },
        mounted() {

        },
        updated() {

        },
        methods: {
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
                }
        }
}

</script>
