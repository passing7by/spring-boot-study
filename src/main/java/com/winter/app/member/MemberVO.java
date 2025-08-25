package com.winter.app.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.winter.app.member.validation.AddGroup;
import com.winter.app.member.validation.UpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	      for(RoleVO roleVO : this.getRoleVOs()) {
	      	authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
	      }
			return authorities;
	}

	@NotBlank(message = "ID는 필수입니다.", groups = AddGroup.class) // 메시지 직접 수정 가능. 단, 다국어 지원 모드에서는 문제가 될 수 있음
	private String username;
	@NotBlank
	@Size(min = 6, max = 8, groups = AddGroup.class)
	private String password;
	
	private String passwordCheck;
	@NotBlank(groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	@Email(groups = {AddGroup.class, UpdateGroup.class})
	private String email;
//	@Pattern(regexp = "")
	private String phone;
//	@Future
	@NotNull(groups = {AddGroup.class, UpdateGroup.class}) // date 타입에는 NotBlank, NotNull 사용시 에러 발생 
	@Past
	private LocalDate birth;
	
	private ProfileVO profileVO;
	
	private List<RoleVO> roleVOs;
}
