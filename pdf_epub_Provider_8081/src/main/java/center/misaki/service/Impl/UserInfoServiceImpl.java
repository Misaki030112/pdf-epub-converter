package center.misaki.service.Impl;

import center.misaki.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public boolean isPathValid(String path, String username) {
        return true;
    }


    @Override
    public List<String> findAllPath(String username) {
        return null;
    }
}
