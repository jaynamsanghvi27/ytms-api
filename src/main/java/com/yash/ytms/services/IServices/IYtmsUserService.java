package com.yash.ytms.services.IServices;

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.YtmsUserDto;

import java.util.List;
import java.util.Map;

/**
 * Project Name - ytms-api
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 25-01-2024
 */
public interface IYtmsUserService {

    YtmsUserDto createNewUser(YtmsUserDto userDto);

    YtmsUserDto getUserByEmailAdd(String emailAdd);

    List<YtmsUserDto> getAllPendingUsers();

    Boolean approvePendingUser(String emailAdd);

    ResponseWrapperDto forgotPassword(String email);

    Boolean resetPassword(Map<String, String> map);
    Boolean changePassword(Map<String, String> map);
}
