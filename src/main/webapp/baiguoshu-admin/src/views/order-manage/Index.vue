<style lang="scss">



</style>

<template lang="html">

<div class="">
    <h3 class="h3-t">订单管理</h3>
    <el-tabs v-model="activeName2" type="card" @tab-click="handleClick">
      <el-tab-pane label="全部" name="first">全部</el-tab-pane>
      <el-tab-pane label="提货卡已发货" name="second">提货卡已发货</el-tab-pane>
      <el-tab-pane label="提货卡未发货" name="third">角色管理</el-tab-pane>
      <el-tab-pane label="已发货" name="fourth">定时任务补偿</el-tab-pane>
      <el-tab-pane label="未发货" name="five">定时任务补1偿</el-tab-pane>
    </el-tabs>
    <!-- table list -->
    <div class="app-table">
        <el-table ref="multipleTable" tooltip-effect="dark" @selection-change="handleTableChange" :data="tableData" border style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="" label="商品名称"></el-table-column>
            <el-table-column prop="" label="主标"></el-table-column>
            <el-table-column prop="" label="副标"></el-table-column>
            <el-table-column prop="" label="说明文字"></el-table-column>
            <el-table-column prop="" label="图片"></el-table-column>
            <el-table-column prop="" label="规格及价格"></el-table-column>
            <el-table-column prop="" label="发货地"></el-table-column>
            <el-table-column prop="" label="状态"></el-table-column>
        </el-table>
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
</div>

</template>

<script>

export default {
    data() {
            return {
                activeName2: 'first',
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
                },
                handleClick(tab, event) {
        console.log(tab, event);
      }
        }
}

</script>
