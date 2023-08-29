package co.yedam.hello.product.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.hello.common.DataSource;
import co.yedam.hello.product.mapper.ProductMapper;
import co.yedam.hello.product.service.ProductService;
import co.yedam.hello.product.service.ProductVO;

public class productServiceImpl implements ProductService {
	// 데이터베이스 연결
	// DAO 연결
	private SqlSession sqlSession = DataSource.getInstance().openSession(true); // true하면 auto commit 실행 시킬 수 있도록 설정
	// 수행시킬 mapper
	private ProductMapper map = sqlSession.getMapper(ProductMapper.class);
	
	@Override
	public List<ProductVO> productSelectList() {
		return map.productSelectList();		// 리스트 구조 보를 하나씩 하나씩 배열에
	}

	@Override
	public ProductVO productSelect(ProductVO vo) {
		return map.productSelect(vo);		// 보(테이블) 하나를 들고옴
	}

	@Override
	public int productInsert(ProductVO vo) {
		return map.productInsert(vo);	
	}

	@Override
	public int productDelete(ProductVO vo) {
		return map.productDelete(vo);
	}

	@Override
	public int productUpdate(ProductVO vo) {
		return map.productUpdate(vo);
	}

}
