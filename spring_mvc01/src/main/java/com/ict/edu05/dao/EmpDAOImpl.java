package com.ict.edu05.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu05.vo.DeptVO;
import com.ict.edu05.vo.EmpVO;

@Repository
public class EmpDAOImpl implements EmpDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<EmpVO> getList() throws Exception {
		return sqlSessionTemplate.selectList("emp.list");
	}

	@Override
	public List<EmpVO> getSearch(String deptno) throws Exception {
		return sqlSessionTemplate.selectList("emp.search", deptno);
	}

	@Override
	public List<EmpVO> getSearch(String idx, String keyword) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("idx", idx);
		map.put("keyword", keyword);
		
		return sqlSessionTemplate.selectList("emp.dynamicmap", map);
	}

	@Override
	public DeptVO getDeptSearch(String deptno) throws Exception {
		return sqlSessionTemplate.selectOne("emp.dept", deptno);
	}

	@Override
	public List<EmpVO> getSearch(EmpVO empvo) throws Exception {
		return sqlSessionTemplate.selectList("emp.dynamic", empvo);
	}

}
