package com.app.happybox.service.user;

import com.app.happybox.type.Role;
import com.app.happybox.entity.user.Member;
import com.app.happybox.domain.user.MemberDTO;
import com.app.happybox.provider.UserDetail;
import com.app.happybox.repository.user.MemberRepository;
import com.app.happybox.type.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Qualifier("member") @Primary
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    //    Member 회원가입
    @Override
    public Member join(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberDTO.setMemberRole(Role.MEMBER);
        return memberRepository.save(toMemberEntity(memberDTO));
    }

    //    Member 로그인
    @Override
    public Optional<Member> login(String memberId, String memberPassword) {
        Optional<Member> member = memberRepository.logIn(memberId, memberPassword);
        return member;
    }

    //    아이디찾기(PhoneNumber)
    @Override
    public Optional<String> findMemberIdByPhoneNumber(String memberPhoneNumber) {
        Optional<String> memberId = memberRepository.findMemberIdByPhoneNumber(memberPhoneNumber);
        return memberId;
    }

    //    아이디찾기(Email)
    @Override
    public Optional<String> findMemberIdByEmail(String memberEmail) {
        Optional<String> memberId = memberRepository.findMemberIdByEmail(memberEmail);
        return memberId;
    }

    @Override
    public Optional<Member> findDeliveryInfoById(Long memberId) {
        Optional<Member> member = memberRepository.findDeliveryAddressByMemberId_QueryDSL(memberId);
        return member;
    }

    @Override
    public void updateMemberInfoById(Member member) {
        memberRepository.setMemberInfoById_QueryDSL(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(username).orElseThrow(()-> new UsernameNotFoundException(username + " not found"));
        return UserDetail.builder()
                .id(member.getId())
                .memberId((member.getUserId()))
                .memberPassword(member.getUserPassword())
                .memberRole(member.getUserRole())
                .build();
    }

    @Override
    public void updateUserStatusById(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        member.setUserStatus(UserStatus.UNREGISTERED);
    }
}
