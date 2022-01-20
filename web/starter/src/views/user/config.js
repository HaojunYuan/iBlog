export const columns = [
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
    scopedSlots: {customRender: 'nickName'},
  },
  {
    title: 'Action',
    key: 'action',
    scopedSlots: {customRender: 'action'},
  },
];


export const  formFields = {
  id: 0,
  username: '',
  password: '',
  nickName: '',
};