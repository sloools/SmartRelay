package com.boot.smartrelay.service;

import com.boot.smartrelay.beans.DataBean;
import com.boot.smartrelay.beans.User;

public interface UserService {
    boolean setData(String userId, DataBean dataBean);
}
