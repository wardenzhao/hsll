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
            <el-table-column prop="name" label="商品"></el-table-column>
            <el-table-column prop="" label="规格"></el-table-column>
            <el-table-column prop="" label="批次"></el-table-column>
            <el-table-column prop="" label="数量"></el-table-column>
            <el-table-column prop="" label="已用数量"></el-table-column>
            <el-table-column prop="" label="提货码"></el-table-column>
        </el-table>
        <div class="pager-box" v-if="total>pageSize">
                <el-pagination @current-change="handleCurrentChange" :page-size="pageSize" layout="total, prev, pager, next" :total="total">
                </el-pagination>
            </div>
    </div>
</div>

</template>

<script>

export default {
    data() {
            return {
                tableData: [],
                pageNo: 1,
                pageSize: 10,
                total: null,
            }
        },
        create() {

        },
        mounted() {

        },
        updated() {

        },
        methods: {
                batchList(){
                  this.tableData = [
                    {
                      "id": 1,
                      "name": "第一批",
                      "number": 2000,
                      "usedNum": 1234
                    },
                    {
                      "id": 2,
                      "name": "第一批",
                      "number": 2010,
                      "usedNum": 1235
                    }
                  ]
                  let datas = {

                  }
                  // this.$http.post(this.htmlUrl.batchList)
                  //     .then(res => {
                  //         // console.log(res)
                  //         res = res.data
                  //         if (res.rtnCode === "0000000") {
                  //             // console.log(res)
                  //             this.tableData = res.batchList
                  //
                  //
                  //
                  //
                  //         } else {
                  //             this.$message.error(res.msg);
                  //         }
                  //
                  //     }).catch(error => {
                  //         console.log(error)
                  //     })
                },
                handleCurrentChange(val) {
                    this.pageNo = val;
                    this.batchList()
                },
                // 新增
                addHandler() {
                    this.dialogTitle = "新增批次"
                    this.dialogVisible = true

                }
        }
}

</script>
