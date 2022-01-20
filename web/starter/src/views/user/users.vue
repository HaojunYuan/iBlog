<template>
  <div>
    <div>
      <a-button type="primary" @click="onCreate">
        <a-icon type="plus"/>
        New account
      </a-button>
    </div>
    <a-input-search placeholder="input search text" style="width: 200px" @search="onSearch"/>
    <a-table :columns="columns" :data-source="data" bordered>
      <a slot="name" slot-scope="text">{{ text }}</a>
      <span slot="customTitle"><a-icon type="smile-o"/> Name</span>
      <span slot="tags" slot-scope="tags">
      <a-tag
        v-for="tag in tags"
        :key="tag"
        :color="tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'"
      >
        {{ tag.toUpperCase() }}
      </a-tag>
    </span>
      <span slot="action" slot-scope="text, record">

        <a-popconfirm
          title="Are you sure delete this user?"
          ok-text="Yes"
          cancel-text="No"
          @confirm="handleDelete(record)"
        >
            <a>Delete</a>
        </a-popconfirm>
        <a @click="onEdit(record)" style="margin-left: 10px">Edit</a>


    </span>
    </a-table>

    <user-form-drawer
      :visible="userFormDrawerVisible"
      :action="userFormDrawerAction"
      :user="userFormDrawerValue"
      @close="()=> userFormDrawerVisible = false"
      @submit="onSubmitSuccess"
    />

  </div>

</template>
<script>


import axios from 'axios';
import qs from 'qs';
import {columns,formFields} from './config'
import  UserFormDrawer from './form-drawer';

export default {
  components: {UserFormDrawer},
  comments:{
    UserFormDrawer
  },
  data() {
    return {
      data: [],
      columns: columns,
      form: this.$form.createForm(this),
      visible: false,

      userFormDrawerVisible: false,
      userFormDrawerAction: 'create',
      userFormDrawerValue: Object.assign({},formFields),

      query: {
        page: 1,
        offset: 10,
        total: 0,
        totalPage: 1,
        s: null,
      }
    };
  },
  mounted() {
    this.loadData();
  },
  methods: {
    loadData() {
      axios.get('/api/v1/user/users?' + qs.stringify(this.query)).then(res => {
        this.data = res.data.result.items;
        Object.assign(this.query, {
          total: res.data.result.total,
          totalPage: res.data.result.totalPage,
        });
      })
    },
    onSearch(s) {
      this.query.s = s;
      this.loadData();
    },
    handleDelete(record) {
      // /api/v1/user/<id>/delete
      let url = `/api/v1/user/users/${record.id}/delete`;
      axios.get(url).then(res => {
        // this.$message.success('message')
        // this.$message.error('error')
        this.$message[res.data.success ? 'success' : 'error'](res.data.message);
        if (res.data.success) {
          this.loadData();
        }
      })
    },

    onEdit(record){
      this.userFormDrawerAction = 'edit';
      this.userFormDrawerValue = record;
      this.userFormDrawerVisible = true;
    },
    onCreate(){
      this.userFormDrawerAction = 'create';
      this.userFormDrawerValue = Object.assign({},formFields);
      this.userFormDrawerVisible = true;
    },
    onSubmitSuccess(user){
      this.userFormDrawerVisible = false;
      this.loadData();
    }
  }
};
</script>