<style lang="css">
.input2{
  width: 100px;
}
.specList{
  padding: 0 0 10px;
}

</style>

<template lang="html">

<div class="">
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" size="small" @close="resetForm('ruleForm')">
        <div class="dialog-form">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px">
                <el-form-item label="商品名称" prop="name">
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
                    <div v-if="ruleForm.specDatas.length!=0" class="specList" v-for="(specItem,specIndex) in ruleForm.specDatas" :key="specIndex">
                      规格{{specIndex+1}}：<el-input class="input2" type="text" v-model="specItem.specName"  placeholder="规格名称"></el-input>&nbsp;&nbsp;&nbsp;&nbsp;单价：<el-input class="input2" type="text" v-model="specItem.specPrice"  placeholder="规格价格"></el-input>&nbsp;&nbsp;&nbsp;&nbsp;<el-button type='text' @click="handleDelSpecBtn(specIndex)">删除</el-button>
                    </div>
                    <el-button type='text' @click="handleSpecBtn">新增</el-button>
                </el-form-item>

                <el-form-item label="规则提示" prop="specifications">
                    <el-input class="input" v-model="ruleForm.specifications" placeholder="请输入规则提示"></el-input>
                </el-form-item>
                <el-form-item label="发货地" prop="address">
                    <el-input class="input" v-model="ruleForm.address" placeholder="请输入发货地"></el-input>
                </el-form-item>
                <el-form-item label="图文介绍" prop="content">
                    <vue-editor v-model="ruleForm.content"></vue-editor>
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
import { VueEditor } from 'vue2-editor'
export default {
    props: ['dialogType'],
    components:{
        VueEditor
    },
    data() {
        return {
            goodId:'',
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
                specDatas:[],
                imgUrl:'',
                content:''
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
                content:[{
                  required: true,
                  message: '请输入介绍',
                  trigger: 'blur change',
                }]
            }
        }
    },
    created() {
    },
    mounted() {

    },
    methods:{
      childMethod(type,goodId){
        this.goodId = goodId
        this.dialogVisible = true
        if(type == 'add'){
          this.isUpdate = false
          this.dialogTitle = '新增商品'
        }else if(type == 'update'){
          this.detail()
          this.isUpdate = true
          this.dialogTitle = '修改商品'
        }
      },
      // 提交
      submitForm(formName) {
          this.$refs[formName].validate((valid) => {
              if (valid) {

                  this.handleSubmitServer();
              } else {
                  return false;
              }
          });
      },
      // 提交服务
      handleSubmitServer() {
        let datas = {
          'productName':this.ruleForm.name,
          'title':this.ruleForm.mainTxt,
          'subtitle':this.ruleForm.viceTxt,
          'describe':this.ruleForm.intro,
          'images':this.ruleForm.imgUrl,
          'specs':this.ruleForm.specDatas,
          'specsTips':this.ruleForm.specifications,
          'address':this.ruleForm.address,
        }
        console.log(datas)
        let url = '',
          message =''
        if(this.isUpdate == true){
          url = this.HttpUrl.UrlConfig.goodUpdate
          message = '修改成功'
        }else{
          url = this.HttpUrl.UrlConfig.goodAdd
          message = '新增成功'
        }
        this.$http.post(url,datas)
            .then(res => {
                res = res.data
                if (res.ret === 0) {
                  this.$emit("listenToSuccess",res)
                  this.$message({
                      message: message,
                      type: 'success'
                  });
                  this.dialogVisible = false
                } else {
                    this.$message.error(res.msg);
                }
            }).catch(error => {
                console.log(error)
            })
      },
      resetForm(formName) {
          this.$refs[formName].resetFields();
      },
      handleSpecBtn(){
        this.ruleForm.specDatas.push({
          'specName':'',
          'specPrice':''
        })
      },
      handleDelSpecBtn(specIndex){
        this.ruleForm.specDatas.splice(specIndex,1)
      },
      // 商品详情
      detail(){
        let datas = {
          'id' : this.goodId
        }
        this.$http.get(this.HttpUrl.UrlConfig.detail,datas)
            .then(res => {
                res = res.data
                if (res.ret === 0) {
                  this.ruleForm.name = res.productName
                  this.ruleForm.mainTxt = res.title
                  this.ruleForm.viceTxt = res.subTitle

                } else {
                    this.$message.error(res.msg);
                }
            }).catch(error => {
                console.log(error)
            })
      }
    }
}

</script>
