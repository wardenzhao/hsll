<style lang="scss" scoped>


.applyLook{
  color:#3c8dbc
}
.buyRecordbox .el-form-item{
  margin-bottom: 0
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
            <el-table-column prop="status" label="状态" :formatter="formatStatus"></el-table-column>
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
        <div class="dialog-form buyRecordbox">
            <el-form label-width="150px">
              <el-form-item label="微信号">
                  {{wechatId}}
              </el-form-item>
              <el-form-item label="姓名">
                  {{name}}
              </el-form-item>
              <el-form-item label="手机号码">
                  {{phone}}
              </el-form-item>
              <el-form-item label="性别">
                  {{sex}}
              </el-form-item>
              <el-form-item label="地址信息">
                  <p>{{address}}</p>
              </el-form-item>
              <el-form-item label="推荐会员">
                  {{members}}
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
import qs from 'qs'
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
                formatStatus(row, column) {
                    var columnV = row[column.property];
                    if (columnV === undefined) {
                        return ''
                    }
                    var status = '';
                    switch (columnV) {
                        case 0:
                            status = "待审核";
                            break;
                        case 1:
                            status = "已通过";
                            break;
                        case 2:
                            status = "不通过";
                            break;
                        default:
                            break;
                    }
                    return status;
                },
            handleCurrentChange(val){
              this.pageNo = parseInt(val)-1
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
              this.$http.post(this.HttpUrl.UrlConfig.applyDetail+'?id='+id)
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
              this.$http.post(this.HttpUrl.UrlConfig.applyCheck,qs.stringify(datas))
                  .then(res => {
                      res = res.data
                      if(res.ret == 0){
                        this.$message({
                            message: '通过审核',
                            type: 'success'
                        });
                        this.dialogVisible = false
                        this.pageNo = 0
                        this.applyList()
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
                'status':'2'
              }
              this.$http.post(this.HttpUrl.UrlConfig.applyCheck,qs.stringify(datas))
                  .then(res => {
                      res = res.data
                      if(res.ret == 0){
                        this.$message({
                            message: '不通过审核',
                            type: 'warning'
                        });
                        this.dialogVisible = false
                        this.pageNo = 0
                        this.applyList()
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
