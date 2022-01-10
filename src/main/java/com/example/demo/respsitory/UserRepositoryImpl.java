package com.example.demo.respsitory;

import com.example.demo.entity.User;
import com.example.demo.query.ListableUserQuery;
import com.example.demo.query.UserQuery;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public User findOneByUsernameAndAge(String username, int age) {
//        Map<String,Object> map = new HashMap<>();
//        map.put("username",username);
//        map.put("age",age);
//        return sqlSessionTemplate.selectOne("User.findOneByUsernameAndAge",map);

        User user = new User();
        user.setUsername(username);
        user.setAge(age);
        return sqlSession.selectOne("User.findOneByUsernameAndAge",user);
    }
}
