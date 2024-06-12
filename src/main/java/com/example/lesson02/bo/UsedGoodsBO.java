package com.example.lesson02.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lesson02.domain.UsedGoods;

@Service // Spring bean으로 등록
public class UsedGoodsBO {
	
	// input: X (컨트롤러로부터 들어온 요청)
	// output: (Repository한테 받은 것을) List<UsedGoods> (컨트롤러에게 준다)
	public List<UsedGoods> getUsedGoodsList() {
		
	}
}
