package co.yedam.CRUDproject.order.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.CRUDproject.common.DataSource;
import co.yedam.CRUDproject.order.mapper.OrderMapper;
import co.yedam.CRUDproject.order.service.OrderService;
import co.yedam.CRUDproject.order.service.OrderVO;

public class OrderServiceImpl implements OrderService{

	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private OrderMapper map = sqlSession.getMapper(OrderMapper.class);
	
	@Override
	public List<OrderVO> orderSelectList() {
		return map.orderSelectList();
	}

	@Override
	public OrderVO orderSelect(OrderVO vo) {
		return map.orderSelect(vo);
	}

	@Override
	public int orderInsert(OrderVO vo) {
		return map.orderInsert(vo);
	}

	@Override
	public int orderUpdate(OrderVO vo) {
		return map.orderUpdate(vo);
	}

	@Override
	public int orderDelete(OrderVO vo) {
		return map.orderDelete(vo);
	}

}
