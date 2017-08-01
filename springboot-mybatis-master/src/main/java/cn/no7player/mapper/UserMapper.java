package cn.no7player.mapper;

import java.util.List;

import cn.no7player.model.User;

public interface UserMapper {
    public User findUserInfo();
    
    public List<User> getUsers(User user);
    
    public int getUserCount(User user);
    	
    
}
