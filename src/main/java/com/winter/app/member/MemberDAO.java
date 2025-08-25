package com.winter.app.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.products.CartVO;
import com.winter.app.products.ProductVO;

@Mapper
public interface MemberDAO {
	public int join(MemberVO memberVO) throws Exception;
	public int addProfile(ProfileVO memberProfileVO) throws Exception;
	public int addRole(Map<String, Object> map) throws Exception;
	public MemberVO checkUsername(MemberVO memberVO) throws Exception;
	public MemberVO detail(MemberVO memberVO) throws Exception;
	public int update(MemberVO memberVO) throws Exception;
	public int cart(CartVO cartVO) throws Exception;
	public List<ProductVO> cartList(MemberVO memberVO) throws Exception;
	public int cartDelete(CartVO cartVO) throws Exception;
	
	public int passwordUpdate(MemberVO memberVO) throws Exception;
}
