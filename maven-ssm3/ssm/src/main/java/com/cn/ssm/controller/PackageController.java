package com.cn.ssm.controller;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cn.ssm.entity.Package;
import com.cn.ssm.entity.User;
import com.cn.ssm.entity.Consumables;
import com.cn.ssm.entity.Goods;
import com.cn.ssm.service.IUserService;

import net.sf.json.JSONObject;

import com.cn.ssm.dao.PackageMapper;
import com.cn.ssm.dao.ConsumablesMapper;
import com.cn.ssm.dao.GoodsMapper;
import com.cn.ssm.dao.UserMapper;

@Controller// 注册为spring容器bean
@RequestMapping("/package")//请求映射
public class PackageController {
    	@Resource //注入对象
    private IUserService userService;
    	@Resource
    private PackageMapper packageMapper;
    	@Resource
    private GoodsMapper goodsMapper;
    	@Resource
    private ConsumablesMapper consumablesMapper;
    	@Resource
    private UserMapper userMapper;
    
    private User user=null;
    	String message = new String();
    private ArrayList<Package> records=new ArrayList<>();
    private ArrayList<Goods> goods=new ArrayList<>();
    
    private ArrayList<Goods> g_consumables=new ArrayList<>();
    private  ArrayList<Goods> g_pieces=new ArrayList<>();
    private  ArrayList<Goods> g_equipments=new ArrayList<>();
    
    private  ArrayList<Package> p_consumables=new ArrayList<>();
    private  ArrayList<Package> p_pieces=new ArrayList<>();
    private  ArrayList<Package> p_equipments=new ArrayList<>();
    
    boolean order=true;//标记排序方式
    
    //分类
    public void classify() {
		g_consumables.clear();
		g_pieces.clear();
		g_equipments.clear();
		p_consumables.clear();
		p_pieces.clear();
		p_equipments.clear();
		
		for (int i=0;i<goods.size();i++) {
			if(goods.get(i).getGoodsType().equals("consumable"))
			{
				g_consumables.add(goods.get(i));
				p_consumables.add(records.get(i));	
			}
			else if(goods.get(i).getGoodsType().equals("piece"))
			{
				g_pieces.add(goods.get(i));
				p_pieces.add(records.get(i));	
			}
			else if(goods.get(i).getGoodsType().equals("equipment"))
			{
				g_equipments.add(goods.get(i));
				p_equipments.add(records.get(i));	
			}
		}
	}
    
    public void init(int id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		records.clear();
		goods.clear();
		message="按获得时间顺序排序";
		records=packageMapper.selectByUserID(id);
		
		if(records.isEmpty()==false)
		{
			for (int i=0;i<records.size();i++) {
				goods.add(goodsMapper.selectByPrimaryKey(records.get(i).getGoodsId()));
			}
		}
		classify();
		order=true;
	}
    
    public void reorder_goodsID(int id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		records.clear();
		goods.clear();
		records=packageMapper.selectByUserID_orderbygoods_ID(id);
	
		if(records.isEmpty()==false)
		{
			for (int i=0;i<records.size();i++) {
				goods.add(goodsMapper.selectByPrimaryKey(records.get(i).getGoodsId()));
			}
		}
		
		classify();
		
		message="按物品类型名称排序";
		order=false;
	}
    
    public void reorder_goods_num(int id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		records.clear();
		goods.clear();
		records=(ArrayList<Package>) packageMapper.selectByUserID_orderbygoods_num(id);
	
		if(records.isEmpty()==false)
		{
			for (int i=0;i<records.size();i++) {
				goods.add(goodsMapper.selectByPrimaryKey(records.get(i).getGoodsId()));
			}
		}
		
		classify();
		
		message="按物品数量排序";
		order=false;
	}
    
  //将当前用户package_num对应的的记录全部列出来（package.goods_num,goods）
    private ModelAndView show(int id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
        	ModelAndView mav = new ModelAndView("package");
        	mav.addObject("message",message);
        	mav.addObject("records","consumables:"+p_consumables.toString()+"\n"+"pieces"+p_pieces.toString()+"\n"+"equipments:"+p_equipments.toString());
        	mav.addObject("list",records);
        	mav.addObject("goods",goods);  
        	mav.addObject("id",id);
        return mav;
    }

    @RequestMapping(value="/index")
	private ModelAndView package_index(@RequestParam("user_id") int id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
    	if(order)//显示顺序
    		{
    			init(id,request,response);
    		}
    	else
    		{
    			reorder_goods_num(id,request,response);
    		}
        	return show(id,request,response);	
    }
    
    @RequestMapping(value="reOrder")
	public ModelAndView reOrder(@RequestParam("user_id") int id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
		order=!order;
		return new ModelAndView("redirect:/package/index?user_id="+String.valueOf(id));
    }
    
    //http://localhost:8080/maven-ssm/package/pieceTogether?user_id=0&record_id=2
    	//前端判断，对于碎片类型的物品，数量大于等于合成所需即提供合成按钮
        	@Transactional//事务管理
    	@RequestMapping(value="pieceTogether")
    	public ModelAndView pieceTogether(@RequestParam("user_id") int id,@RequestParam("record_id") int record_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
    		//从前端的item获取package_id
    		//int package_id=2;
    		//碎片数量大于合成所需时可以进行合成
    		Package record=p_pieces.get(record_id);
    		Goods good=g_pieces.get(record_id);
  		
    		record.setGoodsNum(record.getGoodsNum()-good.getGoodsAttr());
    		
    	if(record.getGoodsNum()==0)//当碎片使用完时删除package中的记录
    		packageMapper.deleteByPrimaryKey(record.getPackageId());
    		packageMapper.updateByPrimaryKey(record);

    		good=goodsMapper.selectBygoods_name(good.getGoodsName(),"player");
    		//判断玩家仓库中是否存在此球员
    		record=packageMapper.selectByUserID_goodsid(id,good.getId());
    		if(record==null)
    		{
    			//如果玩家仓库中没有此球员，则添加新球员数据
    			Package newrecord=new Package();
    			newrecord.setGoodsId(good.getId());
    			newrecord.setUserId(id);
    			packageMapper.insert_autoIncrement(newrecord);
    		}
    		else//更新物品信息
    		{
    			record.setGoodsNum(record.getGoodsNum()+1);
    			packageMapper.updateByPrimaryKey(record);
    		}
    		
    		return new ModelAndView("redirect:/package/index?user_id="+String.valueOf(id));
    	}
    
        	//http://localhost:8080/maven-ssm/package/apply?user_id=0&record_id=0
        	//对于消耗品，提供使用接口
            	@Transactional
        	@RequestMapping(value="apply")
        	public ModelAndView apply(@RequestParam("user_id") int id,@RequestParam("record_id") int record_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
        		//从前端的item获取package_id
        		Package record=p_consumables.get(record_id);
        		Goods good=g_consumables.get(record_id);
        		
        				//添加体验卡记录
        				Consumables consumable=new Consumables();
        				Timestamp currentTime=new Timestamp(System.currentTimeMillis());
        				Calendar currCalendar = Calendar.getInstance();
        				currCalendar.setTime(currentTime);
        				currCalendar.add(Calendar.DAY_OF_MONTH, good.getGoodsAttr());//设置可使用期限
        				currentTime=new Timestamp(currCalendar.getTimeInMillis());
        				
        				consumable.setGoodsId(good.getId());
        				consumable.setUserId(id);
        				consumable.setLiveTime(currentTime);
        				
        				consumablesMapper.insert_AI(consumable);
        				
        				//更新package中记录
        				record.setGoodsNum(record.getGoodsNum()-1);
        			if(record.getGoodsNum()==0)
        				{
        					packageMapper.deleteByPrimaryKey(record.getPackageId());
        				}
        			else
        				{
        					packageMapper.updateByPrimaryKey(record);
        				}
        				
        		return new ModelAndView("redirect:/package/index?user_id="+String.valueOf(id));
        	}
            	
            	
        private void flush(HttpServletRequest request, HttpServletResponse response)  throws Exception {
        	response.setContentType("application/json");
    		PrintWriter out=null;
    		JSONObject json = new JSONObject();
    	
    		try {
    			out=response.getWriter();
    			
    			json.put("g_consumables", g_consumables);
    			json.put("g_equipments", g_equipments);
    			json.put("g_pieces", g_pieces);
    			json.put("p_consumables", p_consumables);
    			json.put("p_equipments", p_equipments);
    			json.put("p_pieces", p_pieces);
    			
    			out.write(json.toString());
    		}finally{
    		out.flush();
    		out.close();
    		}
        }
            	
    @RequestMapping(value="/index.json")
	private void package_index_client(@RequestParam("user_id") int id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
    	if(order)//显示顺序
    		{
    			init(id,request,response);
    		}
    	else
    		{
    			reorder_goods_num(id,request,response);
    		}
    	flush(request,response);
    }
    
    @RequestMapping(value="reOrder.json")
	public void reOrder_client(@RequestParam("user_id") int id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
		order=!order;
		package_index_client(id,request,response);
    }

//http://localhost:8080/maven-ssm/package/apply.json?id=0&package_id=0
//对于消耗品，提供使用接口
@Transactional
@RequestMapping(value="apply.json")
public void apply_client(@RequestParam("user_id") int id,@RequestParam("record_id") int record_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
	//从前端的item获取package_id
	Package record=p_consumables.get(record_id);
	Goods good=g_consumables.get(record_id);

	//查找是否存在在使用中的同类物品
	Consumables con_record=consumablesMapper.selectByuserId_recordId(id,good.getId());
	if(con_record==null)
	{
		//添加体验卡记录
				Consumables consumable=new Consumables();
				Timestamp currentTime=new Timestamp(System.currentTimeMillis());
				Calendar currCalendar = Calendar.getInstance();
				currCalendar.setTime(currentTime);
				currCalendar.add(Calendar.DAY_OF_MONTH, good.getGoodsAttr());//设置可使用期限
				currentTime=new Timestamp(currCalendar.getTimeInMillis());
			
				consumable.setGoodsId(good.getId());
				consumable.setUserId(id);
				consumable.setLiveTime(currentTime);
			
				consumablesMapper.insert_AI(consumable);
	}
	else
	{
		//更新con_record
		Calendar currCalendar = Calendar.getInstance();
		currCalendar.setTime(con_record.getLiveTime());
		currCalendar.add(Calendar.DAY_OF_MONTH, good.getGoodsAttr());//延长可使用期限
		Timestamp liveTime=new Timestamp(currCalendar.getTimeInMillis());
		con_record.setLiveTime(liveTime);
		consumablesMapper.updateByPrimaryKey(con_record);
	}
	
		//更新package中记录
		record.setGoodsNum(record.getGoodsNum()-1);
		if(record.getGoodsNum()==0)
		{
			packageMapper.deleteByPrimaryKey(record.getPackageId());
		}
		else
		{
			packageMapper.updateByPrimaryKey(record);
		}
		
		package_index_client(id,request,response);
	}

//http://localhost:8080/maven-ssm/package/apply.json?id=0&package_id=0
//对于消耗品，提供使用接口
@Transactional
@RequestMapping(value="equip.json")
public void equip_client(@RequestParam("user_id") int id,@RequestParam("record_id") int record_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
	//从前端的item获取package_id
	Package record=p_equipments.get(record_id);
	Goods good=g_equipments.get(record_id);

	//查找是否存在在使用中的同类物品
		Consumables con_record=consumablesMapper.selectByuserId_recordId(id,good.getId());
		if(con_record==null)
		{
			//添加体验卡记录
			Consumables consumable=new Consumables();
			Timestamp currentTime=new Timestamp(System.currentTimeMillis());
			Calendar currCalendar = Calendar.getInstance();
			currCalendar.setTime(currentTime);
			currCalendar.add(Calendar.DAY_OF_MONTH, good.getGoodsAttr());//设置可使用期限
			currentTime=new Timestamp(currCalendar.getTimeInMillis());
	
			consumable.setGoodsId(good.getId());
			consumable.setUserId(id);
			consumable.setLiveTime(currentTime);
	
			consumablesMapper.insert_AI(consumable);
		}
		else
		{
			//更新con_record
			Calendar currCalendar = Calendar.getInstance();
			currCalendar.setTime(con_record.getLiveTime());
			currCalendar.add(Calendar.DAY_OF_MONTH, good.getGoodsAttr());//延长可使用期限
			Timestamp liveTime=new Timestamp(currCalendar.getTimeInMillis());
			con_record.setLiveTime(liveTime);
			consumablesMapper.updateByPrimaryKey(con_record);
		}
	
		//更新package中记录
		record.setGoodsNum(record.getGoodsNum()-1);
		if(record.getGoodsNum()==0)
		{
			packageMapper.deleteByPrimaryKey(record.getPackageId());
		}
		else
		{
			packageMapper.updateByPrimaryKey(record);
		}
		
		package_index_client(id,request,response);
	}


//http://localhost:8080/maven-ssm/package/pieceTogether?user_id=0&record_id=2
//前端判断，对于碎片类型的物品，数量大于等于合成所需即提供合成按钮
@Transactional//事务管理
@RequestMapping(value="pieceTogether.json")
public void pieceTogether_client(@RequestParam("user_id") int id,@RequestParam("record_id") int record_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
	//从前端的item获取package_id
	//碎片数量大于合成所需时可以进行合成
	Package thisrecord=p_pieces.get(record_id);
	Goods thisgood=g_pieces.get(record_id);
	
	Goods good=goodsMapper.selectBygoods_name(thisgood.getGoodsName(),"player");
	//判断玩家仓库中是否存在此球员
	Package record=packageMapper.selectByUserID_goodsid(id,good.getId());
	if(record==null)
	{
		//如果玩家仓库中没有此球员，则添加新球员数据
		Package newrecord=new Package();
		newrecord.setGoodsId(good.getId());
		newrecord.setUserId(id);
		newrecord.setGoodsNum(1);
		packageMapper.insert_autoIncrement(newrecord);
	}
	else//更新物品信息
	{
		System.out.println("原有： "+record.getGoodsNum());
		record.setGoodsNum((int)record.getGoodsNum()+1);
		System.out.println("现有： "+record.getGoodsNum());
		packageMapper.updateByPrimaryKey(record);
	}
	
	thisrecord.setGoodsNum((int)thisrecord.getGoodsNum()-(int)thisgood.getGoodsAttr());
	
	if(thisrecord.getGoodsNum()==0)//当碎片使用完时删除package中的记录
		packageMapper.deleteByPrimaryKey(thisrecord.getPackageId());
	packageMapper.updateByPrimaryKey(thisrecord);
	
	package_index_client(id,request,response);
	}
}

