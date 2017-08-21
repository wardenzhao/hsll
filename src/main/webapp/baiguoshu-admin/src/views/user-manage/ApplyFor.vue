<style lang="scss">



</style>

<template lang="html">

<div class="">
    <h3 class="h3-t">申请管理</h3>
    <!-- btn -->
    <!-- table list -->
    <div class="app-table">
        <el-table ref="multipleTable" tooltip-effect="dark" :data="tableData" border style="width: 100%">
            <el-table-column prop="wxNum" label="微信号"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="phone" label="手机号码"></el-table-column>
            <el-table-column prop="sex" label="性别"></el-table-column>
            <el-table-column prop="address" label="地址信息"></el-table-column>
            <el-table-column prop="inviteMember" label="推荐会员"></el-table-column>
            <el-table-column prop="status" label="状态"></el-table-column>
            <el-table-column prop="" label="操作"></el-table-column>
        </el-table>
        <div class="pager-box" v-if="total>pageSize">
                <el-pagination @current-change="handleCurrentChange" :page-size="pageSize" layout="total, prev, pager, next" :total="total">
                </el-pagination>
            </div>
    </div>
    <!-- 新增、修改弹层 -->
    <el-dialog title="查看申请" :visible.sync="dialogVisible" size="small">
        <div class="dialog-form">
            <el-form label-width="150px">
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
                    <el-button type="primary" >确定</el-button>
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
              pageNo: 1,
              pageSize: 10,
              total: null,
                tableData: [],
                dialogVisible: false,
                updateDialogVisible: false,
                wechatId:'',
                wechatName:'',
                name:'',
                phone:'',
                sex:'',
                members:''
            }
        },
        create() {

        },
        mounted() {

        },
        updated() {
          this.applyList()
        },
        methods: {
            handleCurrentChange(val){
              this.pageNo = val
            },
            applyList(){
              this.$http.get(this.HttpUrl.UrlConfig.applyList)
                  .then(res => {
                      res = res.data
                      this.tableData = res.applyList
                      this.total = parseInt(res.listCount)
                  }).catch(error => {
                      console.log(error)
                  })
            }
        }
}

</script>
