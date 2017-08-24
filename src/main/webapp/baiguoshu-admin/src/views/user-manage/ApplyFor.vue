<style lang="scss">


.applyLook{
  color:#3c8dbc
}
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
            <el-table-column prop="sex" :formatter="formatSex" label="性别"></el-table-column>
            <el-table-column prop="address" label="地址信息"></el-table-column>
            <el-table-column prop="inviteMemberName" label="推荐会员"></el-table-column>
            <el-table-column prop="status" label="状态"></el-table-column>
            <el-table-column label="操作">
              <template scope="scope">
                <span class="applyLook" @click="clickApplyLook(scope.row.id)">查看</span>
              </template>
            </el-table-column>
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
                  <p>{{address}}</p>
                  <!-- <p>ddd</p>
                  <p>ddd</p>
                  <p>ddd</p> -->
              </el-form-item>
              <el-form-item label="推荐会员">
                  <el-input class="input" v-model="members" :disabled="true"></el-input>
              </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="throughClick">通过</el-button>
                    <el-button type="warning" @click="noThroughClick">不通过</el-button>
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
              pageNo: 0,
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
                members:'',
                address:'',
                id:''
            }
        },
        create() {

        },
        mounted() {
          this.applyList()
        },
        updated() {

        },
        methods: {
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
            handleCurrentChange(val){
              this.pageNo = val
            },
            applyList(){
              this.$http.get(this.HttpUrl.UrlConfig.applyList+'?pageNo='+this.pageNo + '&pageSize='+this.pageSize)
                  .then(res => {
                      res = res.data
                      this.tableData = res.applyList
                      this.total = parseInt(res.listCount)
                  }).catch(error => {
                      console.log(error)
                  })
            },
            clickApplyLook(id){
              this.id = id+''
              this.$http.get(this.HttpUrl.UrlConfig.applyDetail+'?id='+id)
                  .then(res => {
                      res = res.data
                      this.dialogVisible = true
                      this.name = res.name
                      this.phone = res.phone
                      this.sex = res.sex
                      this.address = res.address
                      this.members = res.inviteMemberName
                  }).catch(error => {
                      console.log(error)
                  })
            },
            throughClick(){
              let datas = {
                'id':this.id,
                'status':'1'
              }
              this.$http.post(this.HttpUrl.UrlConfig.applyCheck,datas)
                  .then(res => {
                      res = res.data
                      if(res.ret == '0'){
                        this.$message({
                            message: '通过审核',
                            type: 'success'
                        });
                      }else{
                        this.$message({
                            message: res.msg,
                            type: 'warning'
                        });
                      }
                  }).catch(error => {
                      console.log(error)
                  })

            },
            noThroughClick(){
              let datas = {
                'id':this.id,
                'status':'0'
              }
              this.$http.post(this.HttpUrl.UrlConfig.applyCheck,datas)
                  .then(res => {
                      res = res.data
                      if(res.ret == '0'){
                        this.$message({
                            message: '不通过审核',
                            type: 'warning'
                        });
                      }else{
                        this.$message({
                            message: res.msg,
                            type: 'warning'
                        });
                      }
                  }).catch(error => {
                      console.log(error)
                  })
            }
        }
}

</script>
