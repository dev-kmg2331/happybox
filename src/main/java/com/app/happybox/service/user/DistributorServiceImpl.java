package com.app.happybox.service.user;

import com.app.happybox.domain.user.DistributorDTO;
import com.app.happybox.domain.user.WelfareDTO;
import com.app.happybox.entity.user.Distributor;
import com.app.happybox.repository.user.DistributorRepository;
import com.app.happybox.type.Role;
import com.app.happybox.type.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Qualifier("distributor") @Primary
public class DistributorServiceImpl implements DistributorService {
    private final DistributorRepository distributorRepository;

    @Override
    public void updateDistributorInfoById(Distributor distributor) {
        distributorRepository.setDistributorInfoById_QueryDSL(distributor);
    }

    @Override
    public void updateUserStatusById(Long distributorId) {
        Distributor distributor = distributorRepository.findById(distributorId).get();
        distributor.setUserStatus(UserStatus.UNREGISTERED);
    }

    @Override
    public Page<Distributor> getList(Pageable pageable) {
        return distributorRepository.findAllWithPaging_QueryDSL(pageable);
    }

    @Override
    public Optional<Distributor> getDetail(Long distributorId) {
        return distributorRepository.findDistributorById_QueryDSL(distributorId);
    }

//    유통 회원가입
    @Override
    public void join(DistributorDTO distributorDTO, PasswordEncoder passwordEncoder) {

        distributorDTO.setUserPassword(passwordEncoder.encode(distributorDTO.getUserPassword()));
        distributorDTO.setUserRole(Role.DISTRIBUTOR);
        distributorDTO.setUserStatus(UserStatus.REGISTERED);
        distributorRepository.save(toDistributorEntity(distributorDTO));
    }
}
