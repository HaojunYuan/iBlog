<template>
  <a-drawer
    :title="`${action === 'create' ? 'Create a new account' : 'Edit account'}`"
    :width="720"
    :visible="visible"
    :body-style="{ paddingBottom: '80px' }"
    @close="onClose"
  >
    <a-form-model ref="userForm" :rules="rules" :model="form" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-model-item label="Username" prop="username">
        <a-input v-model="form.username" />
      </a-form-model-item>
      <a-form-model-item label="Password" prop="password">
        <a-input v-model="form.password" />
      </a-form-model-item>
      <a-form-model-item label="Nick Name" prop="nickName">
        <a-input v-model="form.nickName" />
      </a-form-model-item>
    </a-form-model>

    <div
      :style="{
          position: 'absolute',
          left: 0,
          bottom: 0,
          width: '100%',
          borderTop: '1px solid #e9e9e9',
          padding: '10px 16px',
          background: '#fff',
          textAlign: 'left',
          zIndex: 1,
        }"
    >
      <a-button :style="{ marginRight: '8px' }" @click="onClose">
        Cancel
      </a-button>
      <a-button type="primary" @click="onSubmit">
        Submit
      </a-button>
    </div>
  </a-drawer>
</template>

<script>
import {formFields} from './config'
import axios from "axios";
import  qs from 'qs';
export default {
  name: 'UserFormDrawer',
  props: {
    visible:{
      type: Boolean,
      default: false,
    },
    user:{
      type: Object,
      default: ()=>{
        return Object.assign({},formFields);
      }
    },
    action:{
      type: String,
      default: 'create'
    }
  },
  data() {
    return {
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      form: Object.assign({}, this.user || {}),
      rules: {
        username: [
          { required: true, message: 'Please input username', trigger: 'blur' },
          { min: 3, max: 10, message: 'Length should be 3 to 10', trigger: 'blur' },
        ],
        password: [
          { required: true, message: 'Please input password', trigger: 'blur' },
          { min: 6, max: 16, message: 'Length should be 6 to 16', trigger: 'blur' },
        ],
        nickName: [{ required: true, message: 'Please input Nick Name', trigger: 'blur' }],
      },
    };
  },
  watch:{
    user: {
      deep: true,
      handler: function (newValue,oldValue){
        this.form = newValue;
      }
    }
  },
  methods: {
    onSubmit() {
      this.$refs.userForm.validate(valid => {
        if (valid) {
          console.log(this.form);
          if (this.action === 'create') {
            this.handleCreateNewUser(this.form);
          } else if (this.action === 'edit' && this.form.id>0){
            this.handleEditUser(this.form);
          } else {
            this.$message.error('Unknown action');
          }
          //alert('submit!');
        } else {
          return false;
        }
      });
    },
    handleCreateNewUser(user) {
      let url = `/api/v1/user/users/create`;
      axios.post(url,qs.stringify(user)).then(res => {
        this.$message[res.data.success ? 'success' : 'error'](res.data.message);
        if (res.data.success) {
          this.$emit('submit',user);
        }
      })
    },
    handleEditUser(user) {
      let url = `/api/v1/user/users/${user.id}/edit`;
      axios.post(url,qs.stringify(user)).then(res => {
        this.$message[res.data.success ? 'success' : 'error'](res.data.message);
        if (res.data.success) {
          this.$emit('submit',user);
        }
      })
    },
    resetForm() {
      this.$refs.userForm.resetFields();
    },
    onClose(){
      this.$emit('close');
    }
  },
};
</script>