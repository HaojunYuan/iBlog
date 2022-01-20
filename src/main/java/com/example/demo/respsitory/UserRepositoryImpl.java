package com.example.demo.respsitory;

import com.example.demo.entity.User;
import com.example.demo.query.ListableUserQuery;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private SqlSession sqlSession;

    public UserRepositoryImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> findByQuery(ListableUserQuery query) {
        return sqlSession.selectList("User.findByQuery",query);
    }

    @Override
    public long countByQuery(ListableUserQuery query) {
        return sqlSession.selectOne("User.countByQuery",query);
    }

    @Override
    public List<User> findAllUser() {
        return sqlSession.selectList("User.findAllUser");
    }

    @Override
    public User findOneById(int id) {
        return sqlSession.selectOne("User.findOneById",id);
    }

    @Override
    public User findOneByUsername(String username) {
        //        Map<String,Object> map = new HashMap<>();
//        map.put("username",username);
//        map.put("age",age);
//        return sqlSessionTemplate.selectOne("User.findOneByUsernameAndAge",map);

        User user = new User();
        user.setUsername(username);
        return sqlSession.selectOne("User.findOneByUsername",user);
    }

    @Override
    public boolean deleteUserById(int id) {
        return sqlSession.update("User.deleteUserById",id)>0;
    }

    @Override
    public boolean has(User user) {
        return (Integer) sqlSession.selectOne("User.has",user)>0;
    }

    @Override
    public boolean createNewUser(User user) {
        return sqlSession.insert("User.createNewUser",user)>0;
    }

    @Override
    public boolean editUser(User user) {
        return sqlSession.update("User.editUser", user)>0;
    }
}
