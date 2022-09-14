package com.kim.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.biz.member.MemberService;
import com.kim.biz.member.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		memberDAO.insert_M(vo);
	}

	@Override
	public MemberVO selectOneMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberDAO.selectOne(vo);
	}

	@Override
	public void updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMember(MemberVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MemberVO> selectAllMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
