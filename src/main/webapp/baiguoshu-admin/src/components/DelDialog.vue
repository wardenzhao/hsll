<template lang="html">
<div class="">
  <el-dialog :title="delTitle" :visible.sync="deleteDialogVisible" size="tiny" :close-on-click-modal="false" :close-on-press-escape="false">
  <span>确定删除吗？</span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="deleteDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="handleDeleteData">确 定</el-button>
  </span>
  </el-dialog>
</div>
</template>

<script>
export default {
  props:['delTitle'],
  data() {
          return {
              deleteDialogVisible:false
          }
      },
      methods:{
        childMethod(){
            this.deleteDialogVisible = true
        },
        delSucess(){
            this.deleteDialogVisible = false
        },
        handleDeleteData(){
            this.$emit("listenToDel")
        }
      }
}
</script>

<style lang="css">
</style>
