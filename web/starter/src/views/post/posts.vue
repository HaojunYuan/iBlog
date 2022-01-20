<template>
  <div>
    <a-input-search placeholder="input search posts" style="width: 200px" @search="onSearch" />
    <a-table :columns="columns" :data-source="data">
      <a slot="name" slot-scope="text">{{ text }}</a>
      <span slot="customTitle"><a-icon type="smile-o" /> Name</span>
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
      <a>Invite 一 {{ record.name }}</a>
      <a-divider type="vertical" />
      <a>Delete</a>
      <a-divider type="vertical" />
      <a class="ant-dropdown-link"> More actions <a-icon type="down" /> </a>
    </span>
    </a-table>
  </div>

</template>
<script>
const columns = [
  {
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'Username',
    dataIndex: 'username',
    key: 'username',
  },
  {
    title: 'Password',
    dataIndex: 'password',
    key: 'password',
  },
  {
    title: 'Nick Name',
    key: 'nickName',
    dataIndex: 'nickName',
    scopedSlots: { customRender: 'nickName' },
  },
  {
    title: 'Action',
    key: 'action',
    scopedSlots: { customRender: 'action' },
  },
];


import axios from 'axios';
import qs from 'qs';

export default {
  data() {
    return {
      data: [],
      columns,

      query:{
        page:1,
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
  methods:{
    loadData(){
      axios.get('/api/v1/user/users?'+qs.stringify(this.query)).then(res=>{
        this.data = res.data.result.items;
        Object.assign(this.query, {
          total: res.data.result.total,
          totalPage: res.data.result.totalPage,
        });
      })
    },
    onSearch(s){
      this.query.s = s;
      this.loadData();
    }
  }
};
</script>