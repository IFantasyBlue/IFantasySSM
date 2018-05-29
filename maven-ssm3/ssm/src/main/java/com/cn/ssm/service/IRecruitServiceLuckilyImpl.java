package com.cn.ssm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.entity.Goods;
import com.cn.ssm.entity.Package;
import com.cn.ssm.entity.TeamMembers;
import com.cn.ssm.dao.GoodsMapper;
import com.cn.ssm.dao.PackageMapper;
import com.cn.ssm.dao.UserMapper;
import com.cn.ssm.dao.TeamMembersMapper;

@Service
@Transactional
public class IRecruitServiceLuckilyImpl implements IRecruitLuckilyService {

	@Resource
	public PackageMapper packageMapper;
	@Resource
	public UserMapper userMapper;
	@Resource
	public GoodsMapper goodsMapper;
	@Resource
	public TeamMembersMapper teamMembersMapper;
	@Resource
	private IUserService userService;

	public int findRecruitId(Map<Integer,Double> goodsMap) {
		List<Double> sortRateList = new ArrayList<Double>();   
        Double sumRate = 0D;   // 计算概率总和
        for(Double prob : goodsMap.values()){
            sumRate += prob;
        }
        if(sumRate != 0){
            double rate = 0D;   //概率所占比例
            for(Double prob : goodsMap.values()){
                rate += prob;   
                sortRateList.add(rate / sumRate); // 构建一个比例区段组成的集合(避免概率和不为1)
            }
            // 随机生成一个随机数，并排序
            double random = Math.random();
            sortRateList.add(random);
            Collections.sort(sortRateList);
 
            // 返回该随机数在比例集合中的索引
            int recruitProb =sortRateList.indexOf(random);
    		
    		List<Integer> keyList = new ArrayList<Integer>();
    		for(Integer key : goodsMap.keySet()){
    			   keyList.add(key);
    		}
    		
    		int recruitGoodsId;  //随机招募到的goods的id
    		if(recruitProb!=0) {
    			recruitGoodsId=keyList.get(recruitProb-1);  
    		}else {
    			recruitGoodsId=keyList.get(0);  
    		}
    		return recruitGoodsId;
        }
		return -1;
	}
	
	public Goods updatePackage(Map<Integer,Double> goodsMap ,int id) {
		int recruitGoodsId=findRecruitId(goodsMap);
		System.out.println(recruitGoodsId+"  抽到的goods的id");
		Goods goods=goodsMapper.selectByPrimaryKey(recruitGoodsId);  //抽到的goods对象

		String goodsType=goods.getGoodsType();
		System.out.println(goodsType+"  goods的类型");
		String name=goods.getGoodsName();
		Package record= new Package();
		record=packageMapper.selectByUserID_goodsid(id,recruitGoodsId);
		
		//如果招募到的是球员 则在TeamMembers里面查找是否存在此球员
		if(!(goodsType.equals("ExpCard")||goodsType.equals("Fragment")||goodsType.equals("MoneyCard"))) {
			int playerId= recruitGoodsId;
			TeamMembers  member =new TeamMembers();
			member = teamMembersMapper.selectByUserID_playerid( id,playerId);  //进行查找
			//若没有此球员，则添加球员到TeamMembers里
			if(member==null) {
				TeamMembers  newMember =new TeamMembers();
				newMember.setPlayerId(playerId);
				newMember.setPosition(goodsType);
				newMember.setUserId(id);
				teamMembersMapper.insertSelective(newMember);
				return goods;
			}else {  //若有球员，则将球员打成碎片放在用户背包里
				Goods fragment= goodsMapper.selectByPlayerName(name);
				int fragGoodsId=fragment.getId();
				System.out.println(fragGoodsId+"   碎片的id");
				Package fragRecord = packageMapper.selectByUserID_goodsid(id, fragGoodsId);
				int goodsNum=goods.getGoodsAttr();
					//无该球员碎片，则添加碎片记录
					if(fragRecord==null) {
						System.out.println("添加新碎片记录");
						Package newRecord= new Package();			
						newRecord.setGoodsId(fragGoodsId);
						newRecord.setUserId(id);
						newRecord.setGoodsNum(goodsNum);
						packageMapper.insertfrag_autoIncrement(newRecord);
						return goods;
					}
					//有该球员碎片，则更新碎片记录
					else{
						System.out.println("更改碎片记录");
						fragRecord.setGoodsNum(fragRecord.getGoodsNum()+goodsNum);
						packageMapper.updateByPrimaryKey(fragRecord);
						return goods;
					}	
			}
		}else {
			if(record==null) {
				System.out.println("添加新记录");
				Package newRecord= new Package();
				newRecord.setGoodsId(recruitGoodsId);
				newRecord.setUserId(id);
				packageMapper.insert_autoIncrement(newRecord);
				return goods;
			}else {
				System.out.println("有记录但不是球员  更新记录");
				record.setGoodsNum(record.getGoodsNum()+1);
				packageMapper.updateByPrimaryKey(record);
				return goods;
			}
		}
	}
	
	@Override
	public Goods recruitFree(int id) {		
		List<Goods> goodsList =goodsMapper.selectAllGoods();
		Map<Integer,Double> goodsMap = new LinkedHashMap<Integer,Double>();
		for (Goods goods : goodsList) {
			goodsMap.put(goods.getId(), goods.getGoodsProb());
		}
	   return updatePackage(goodsMap,id);
	}

	@Override
	public Goods recruitOnce(int id) {
		List<Goods> goodsList =goodsMapper.selectOnceGoods();
		Map<Integer,Double> goodsMap = new LinkedHashMap<Integer,Double>();
		for (Goods goods : goodsList) {
			goodsMap.put(goods.getId(), goods.getGoodsProb());
		}
		userMapper.updateMoney(10000, id);
		return updatePackage(goodsMap,id);
	}

	@Override
	public Goods recruitFive(int id) {
		List<Goods> playerList =goodsMapper.selectFiveGoods();
		Map<Integer,Double> goodsMap = new LinkedHashMap<Integer,Double>();
		for (Goods goods : playerList) {
			goodsMap.put(goods.getId(), goods.getGoodsProb());
		}
		userMapper.updateMoney(40000, id);
		return updatePackage(goodsMap,id);
	}

}

