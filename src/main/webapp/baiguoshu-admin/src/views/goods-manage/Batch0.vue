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
        <el-table ref="multipleTable" tooltip-effect="dark" @selection-change="handleTableChange" :data="tableData" border style="width: 100%">
            <el-table-column prop="" label="序号"></el-table-column>
            <el-table-column prop="" label="生成时间"></el-table-column>
            <el-table-column prop="" label="商品"></el-table-column>
            <el-table-column prop="" label="规格"></el-table-column>
            <el-table-column prop="" label="批次"></el-table-column>
            <el-table-column prop="" label="数量"></el-table-column>
            <el-table-column prop="" label="已用数量"></el-table-column>
            <el-table-column prop="" label="提货码"></el-table-column>
        </el-table>
    </div>
    <!-- 新增、修改弹层 -->
    <!-- <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" size="small" @close="resetForm('ruleForm')">
        <div class="dialog-form">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px">
                <el-form-item label="商品" prop="goods">
                  <el-select v-model="ruleForm.goods" @change="handleChangGoods" placeholder="请选择商品">
                      <el-option :label="柿饼1" :value="1"></el-option>
                      <el-option :label="柿饼2" :value="2"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="礼品盒" prop="giftGox">
                  <el-select v-model="ruleForm.giftGox" @change="handleChangGiftGox" placeholder="请选择礼品盒">
                      <el-option :label="礼盒装1" :value="1"></el-option>
                      <el-option :label="礼盒装2" :value="2"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="批次" prop="batch">
                    第<el-input class="input" v-model="ruleForm.batch" placeholder="请输入批次"></el-input>批
                </el-form-item>
                <el-form-item label="数量" prop="num">
                    <el-input class="input" type="number" v-model="ruleForm.num" placeholder="请输入数量"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-dialog> -->
    <!-- <el-dialog title="查看" :visible.sync="lookDialogVisible" size="small" @close="resetForm('ruleForm')">
        <div class="dialog-form">
          <div class="app-table">
              <el-table ref="multipleTable" tooltip-effect="dark" @selection-change="handleTableChange" :data="tableData" border style="width: 100%">
                  <el-table-column prop="" label="序号"></el-table-column>
                  <el-table-column prop="" label="提货码"></el-table-column>
                  <el-table-column prop="" label="生成时间"></el-table-column>
                  <el-table-column prop="" label="批次"></el-table-column>
                  <el-table-column prop="" label="商品"></el-table-column>
                  <el-table-column prop="" label="规格"></el-table-column>
              </el-table>
          </div>
        </div>
    </el-dialog> -->
</div>

</template>

<script>

export default {
    data() {
            return {
                tableData: [],
                dialogVisible: false,
                lookDialogVisible:true,
                dialogTitle: '',
                ruleForm: {
                    goods: '',
                    giftGox:'',
                    batch:'',
                    num:'',
                },
                rules: {
                    goods: [{
                        required: true,
                        message: '活动名称20字以内',
                        trigger: 'blur change',
                        max:20,
                        min:1
                    }],
                    giftGox: [{
                        required: true,
                        message: '主标文字20字以内',
                        trigger: 'blur change',
                        max:20,
                        min:1
                    }],
                    batch: [{
                        required: true,
                        message: '副标文字20字以内',
                        trigger: 'blur change',
                        max:20,
                        min:1
                    }],
                    num: [{
                        required: true,
                        message: '说明文字100字以内',
                        trigger: 'blur change',
                        max:100,
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
                    this.dialogTitle = "新增批次"
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
