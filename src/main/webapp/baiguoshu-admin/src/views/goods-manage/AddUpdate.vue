<style lang="css">



</style>

<template lang="html">

<div class="">
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" size="small" @close="resetForm('ruleForm')">
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
</div>

</template>

<script>

export default {
    props: ['dialogType'],
    data() {
        return {
            isUpdate:false,
            dialogVisible: false,
            dialogTitle: '',
            ruleForm: {
                name: '',
                mainTxt: '',
                viceTxt: '',
                intro: '',
                specifications: '',
                address: '',
            },
            rules: {
                name: [{
                    required: true,
                    message: '活动名称20字以内',
                    trigger: 'blur change',
                    max: 20,
                    min: 1
                }],
                mainTxt: [{
                    required: true,
                    message: '主标文字20字以内',
                    trigger: 'blur change',
                    max: 20,
                    min: 1
                }],
                viceTxt: [{
                    required: true,
                    message: '副标文字20字以内',
                    trigger: 'blur change',
                    max: 20,
                    min: 1
                }],
                intro: [{
                    required: true,
                    message: '说明文字100字以内',
                    trigger: 'blur change',
                    max: 100,
                    min: 1
                }],
                specifications: [{
                    required: true,
                    message: '规格提示30字以内',
                    trigger: 'blur change',
                    max: 30,
                    min: 1
                }],
                address: [{
                    required: true,
                    message: '发货地20字以内',
                    trigger: 'blur change',
                    max: 20,
                    min: 1
                }],
            }
        }
    },
    created() {
    },
    mounted() {

    },
    methods:{
      childMethod(type){
        this.dialogVisible = true
        if(type == 'add'){
          this.isUpdate = false
          this.dialogTitle = '新增商品'
        }else if(type == 'update'){
          this.isUpdate = true
          this.dialogTitle = '修改商品'
        }
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
      handleSubmitServer() {
        let url = '',
          message =''
        if(this.isUpdate == true){
          url = this.htmlUrl.goodUpdate
          message = '修改成功'
        }else{
          url = this.htmlUrl.goodAdd
          message = '新增成功'
        }
        this.$http.post(url)
            .then(res => {
                res = res.data
                if (res.ret === "0") {
                  this.$emit("listenToSuccess",res)
                  this.$message({
                      message: message,
                      type: 'success'
                  });


                } else {
                    this.$message.error(res.msg);
                }

            }).catch(error => {
                console.log(error)
            })
      },
      resetForm(formName) {
          this.$refs[formName].resetFields();
      }
    }
}

</script>
