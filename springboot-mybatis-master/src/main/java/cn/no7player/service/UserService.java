package cn.no7player.service;

import cn.no7player.mapper.UserMapper;
import cn.no7player.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserInfo(){
        User user=userMapper.findUserInfo();
        //User user=null;
        return user;
    }
    
    public List<User> getUsers(User user){
        return userMapper.getUsers(user);
       
    }
    
    public int getUserCount(User user){
        return userMapper.getUserCount(user);
       
    }

}
