package com.qehing.dbService.mapper;

import com.qehing.dbService.dao.Friend;

import java.util.List;

public interface FriendMapper {
    Boolean insertFriend(Friend friend);
    List<Friend> getFriends(Long userId);
    Boolean deleteFriend(Long userId, Long friendId);
}
