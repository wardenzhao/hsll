<style lang="scss">



</style>

<template lang="html">

<div class="">
    <h3 class="h3-t">科室信息管理</h3>
    <!-- btn -->
    <div class="btn-box">
        <el-button type="success" @click='addHandler'>发布内容</el-button>
        <el-button type="warning" @click='updateHandler'>编辑内容</el-button>
        <el-button type="danger" @click='deleteHandler'>删除内容</el-button>
    </div>
    <!-- table list -->
    <div class="app-table">
        <el-table ref="multipleTable" tooltip-effect="dark" @selection-change="handleTableChange" :data="tableData" border style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="" label="节气"></el-table-column>
            <el-table-column prop="" label="说明文字"></el-table-column>
            <el-table-column prop="" label="图片"></el-table-column>
        </el-table>
    </div>
    <!-- 新增、修改弹层 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" size="small" @close="resetForm('ruleForm')">
        <div class="dialog-form">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px">
                <el-form-item label="选择节气" prop="solar-terms">
                  <el-select v-model="ruleForm.goods" @change="handleChangSolarTerms" placeholder="请选择节气">
                      <el-option :label="节气1" :value="1"></el-option>
                      <el-option :label="节气2" :value="2"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="说明文字" prop="intro">
                    <el-input class="input" type="textarea" v-model="ruleForm.intro" placeholder="请输入说明文字"></el-input>
                </el-form-item>
                <el-form-item label="展示图片" prop="">

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
                }
        }
}

</script>
