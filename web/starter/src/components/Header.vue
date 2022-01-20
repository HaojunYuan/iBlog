
<template>
  <div style="height: 50px;line-height: 50px; border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 400px; padding-left: 23px; font-weight: bold; color: dodgerblue;font-size: 30px">Backstage Management</div>
    <div style="flex: 1"></div>
<!--    <div style="width: 120px"><el-dropdown split-button type="primary" @click="handleClick">-->
<!--      Menu-->
<!--      <template #dropdown>-->
<!--        <el-dropdown-menu>-->
<!--          <el-dropdown-item>Personal Information</el-dropdown-item>-->
<!--          <el-dropdown-item>Exit</el-dropdown-item>-->
<!--        </el-dropdown-menu>-->
<!--      </template>-->
<!--    </el-dropdown>-->
<!--    </div>-->
  </div>
</template>


<script>
import { defineComponent } from 'vue'
// import {ArrowDown} from "@element-plus/icons-vue";
export default {
  name: "Header",
  // components:{ArrowDown},
  setup() {
    const handleClick = () => {
      alert('button click')
    }
    return {
      handleClick,
    }
  },
}
</script>

<style scoped>

</style>