
package com.example.demo;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountDAO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Role;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final AccountDAO account;
	private final HttpSession session;
//	private PasswordEncoder passwordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		
		// loadUser가 실행될 때, 토큰의 정보를 받아오고 그 뒤에 실행킨 loadUser()의 메소드는 해당 토큰을 통해 유저의 정보를 가져옴   
		OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);

		// 어떤 소셜 로그인을 이용하는지 구분하기 위해 사용, 우리의 경우 카카오만 사용
		String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();

		// OAuth2 로그인시 키 값이 됨, 카카오는 id 임
		String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails()
				.getUserInfoEndpoint().getUserNameAttributeName();

		// OAuth2 로그인을 통해 가져온 OAuth2User의 attribute를 담아주는 of 메소드.
		// oAuth2User.getAttributes() 에는 다음 값이 담긴다.
		// 구글 예시) { sub=1231344534523565757, name=홍길동, given_name=길동, family_name=홍,
		// picture=https://xxx, email=xxx@gmail.com, email_verified=true, locale=ko}
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
				oAuth2User.getAttributes());
		System.out.println("attributes:"+attributes.getUsername());
		String aid = "kakao"+attributes.getUsername();
		String encode = "1234";
		Account a = new Account(aid, encode,attributes.getEmail(), attributes.getName(),null, null,  null, null,null,null);
		System.out.println("커스텀 서비스  :"+ a.getEmail());
		// user 예시) username=google_1231344534523565757 , password =
		// $2a$10$nlkgA6oUE.7KpHSO6tDDpOBth4PICf1DeQiHQ2qbbaA8o3s1osGvG
		Account loadOrSaveUser = loadOrSave(a);
		session.setAttribute("user", loadOrSaveUser);
		return new CustomIntegratedLogin(loadOrSaveUser, attributes);
	}

	// 이미 저장된 유저라면 load, 아니면 save
	private Account loadOrSave(Account user) {
		// 이미 저장된 유저인지 확인
		Optional<Account> usercheck = account.findByEmail(user.getEmail());
		
		if (usercheck.isEmpty()) { // 소셜로그인을 시도한 아이디가 없다면 저장하고
			System.out.println("존재하지 않는 회원:");
			return account.save(new Account(user.getAid(), user.getPwd(), user.getEmail(), user.getName_(), null, null, null,  null,null,Role.USER));
			
			
		} else {
			System.out.println("존재하는 회원 :"+user.getAid());
            return user;
		}
	}
}
