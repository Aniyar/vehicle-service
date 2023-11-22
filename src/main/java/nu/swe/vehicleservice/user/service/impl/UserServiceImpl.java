package nu.swe.vehicleservice.user.service.impl;

import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.security.CurrentUser;
import nu.swe.vehicleservice.user.dto.request.UserCreateRequest;
import nu.swe.vehicleservice.user.dto.request.UserGetRequest;
import nu.swe.vehicleservice.user.dto.request.UserPasswordResetRequest;
import nu.swe.vehicleservice.user.dto.request.UserUpdateRequest;
import nu.swe.vehicleservice.user.dto.response.UserResponse;
import nu.swe.vehicleservice.user.entity.UserEntity;
import nu.swe.vehicleservice.user.exception.UserException;
import nu.swe.vehicleservice.user.mapper.UserMapper;
import nu.swe.vehicleservice.user.repository.UserRepository;
import nu.swe.vehicleservice.user.service.UserService;
import nu.swe.vehicleservice.user.util.UserValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static nu.swe.vehicleservice.user.enums.UserErrorCode.*;


/**
 * This is an implementation of {@link UserService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CurrentUser currentUser;
    private final UserRepository userRepository;


    @Override
    public void resetCurrentUserPassword(UserPasswordResetRequest request) {
        resetPassword(currentUser.getId(), request);
    }

    @Override
    public void resetPassword(Integer id, UserPasswordResetRequest request) {
        if (!UserValidationUtil.isValidPassword(request.getNewPassword())) {
            throw new UserException(USER_PASSWORD_IS_INVALID);
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new UserException(USER_PASSWORDS_DOESNT_MATCH);
        }

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserException(USER_NOT_FOUND));
        user.setPassword(new String(request.getNewPassword()));
        userRepository.save(user);
    }


    @Override
    public void create(UserCreateRequest request) {
        UserEntity user = UserMapper.INSTANCE.toRepresentation(request);
        userRepository.save(user);
    }

    @Override
    public void update(Integer id, UserUpdateRequest request) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserException(USER_NOT_FOUND));
        user = UserMapper.INSTANCE.toRepresentation(request, user);
        userRepository.save(user);
    }

    @Override
    public void update(UserUpdateRequest request) {
        update(currentUser.getId(), request);
    }


    @Override
    public UserResponse findCurrentUser() {
        return findById(currentUser.getId());
    }

    @Override
    public UserResponse findById(Integer id) {
        UserEntity user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        return UserMapper.INSTANCE.toResponse(user);
    }


    @Override
    public PageResponse<UserResponse> findAllPageable(UserGetRequest request, Pageable pageable) {
        Page<UserEntity> page = userRepository.findAll(pageable);
        return PageResponse.fromPage(page.map(UserMapper.INSTANCE::toResponse));
    }


    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

}
