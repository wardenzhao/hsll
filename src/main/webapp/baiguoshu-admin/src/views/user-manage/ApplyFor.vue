<style lang="scss">



</style>

<template lang="html">

<div class="">
    <h3 class="h3-t">申请管理</h3>
    <!-- btn -->
    <!-- table list -->
    <div class="app-table">
        <el-table ref="multipleTable" tooltip-effect="dark" @selection-change="handleTableChange" :data="tableData" border style="width: 100%">
            <el-table-column prop="" label="微信号"></el-table-column>
            <el-table-column prop="" label="姓名"></el-table-column>
            <el-table-column prop="" label="手机号码"></el-table-column>
            <el-table-column prop="" label="性别"></el-table-column>
            <el-table-column prop="" label="地址信息"></el-table-column>
            <el-table-column prop="" label="推荐会员"></el-table-column>
            <el-table-column prop="" label="状态"></el-table-column>
            <el-table-column prop="" label="操作"></el-table-column>
        </el-table>
    </div>
    <!-- 新增、修改弹层 -->
    <el-dialog title="查看申请" :visible.sync="dialogVisible" size="small" @close="resetForm('ruleForm')">
        <div class="dialog-form">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px">
              <el-form-item label="微信号">
                  <el-input class="input" v-model="wechatId" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="姓名">
                  <el-input class="input" v-model="name" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="手机号码">
                  <el-input class="input" v-model="phone" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="性别">
                  <el-input class="input" v-model="sex" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="地址信息">
                  <!-- <el-input class="input" v-model="wechatName" :disabled="true"></el-input> -->
                  <p>ddd</p>
                  <p>ddd</p>
                  <p>ddd</p>
                  <p>ddd</p>
              </el-form-item>
              <el-form-item label="推荐会员">
                  <el-input class="input" v-model="members" :disabled="true"></el-input>
              </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
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
                tableData: [],
                dialogVisible: false,
                updateDialogVisible: false,
                wechatId:'',
                wechatName:'',
                name:'',
                phone:'',
                sex:''
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
