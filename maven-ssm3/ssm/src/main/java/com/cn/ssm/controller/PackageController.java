package com.cn.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ssm.entity.Package;
import com.cn.ssm.entity.User;
import com.cn.ssm.entity.Goods;
import com.cn.ssm.service.IUserService;
import com.cn.ssm.dao.PackageMapper;
import com.cn.ssm.dao.GoodsMapper;

@Controller// 注册为spring容器bean
@RequestMapping("/")//请求映射
public class PackageController {
    	@Resource //注入对象
    private IUserService userService;
    	@Resource
    private PackageMapper packageMapper;
    	@Resource
    private GoodsMapper goodsMapper;
    
    private User user=null;
    	String message = new String();
    private List<Package> records=new ArrayList<>();
    private List<Goods> goods=new ArrayList<>();
    boolean order=true;//标记排序方式
    public void init(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//从HttpRequest解析用户信息，从中得到用户ID
		//int userID= Integer.parseInt(request.getParameter("id"));
		//user=userService.getById(userID);
		records.clear();
		goods.clear();
		message="按获得时间顺序排序";
		records=packageMapper.selectByUserID(0);//user.getID());
	
		if(records.isEmpty()==false)
		{
			for (int i=0;i<records.size();i++) {
				goods.add(goodsMapper.selectByPrimaryKey(records.get(i).getGoodsId()));
			}
		}
		
		order=true;
	}
    
    public void reorder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//从HttpRequest解析用户信息，从中得到用户ID
		//int userID= Integer.parseInt(request.getParameter("id"));
		//user=userService.getById(userID);
		records.clear();
		goods.clear();
		records=packageMapper.selectByUserID_orderbygoods_ID(0);//user.getID());
	
		if(records.isEmpty()==false)
		{
			for (int i=0;i<records.size();i++) {
				goods.add(goodsMapper.selectByPrimaryKey(records.get(i).getGoodsId()));
			}
		}
		
		message="按物品类型排序";
		order=false;
	}
    
  //将当前用户package_num对应的的记录全部列出来（package.goods_num,goods）
    private ModelAndView show(HttpServletRequest request, HttpServletResponse response)  throws Exception {
        	ModelAndView mav = new ModelAndView("package");
        	mav.addObject("message",message);
        	mav.addObject("records",records.toString());
        	mav.addObject("goods",goods.toString());    	
        return mav;
    }

    @RequestMapping(value="package")
	private ModelAndView package_index(HttpServletRequest request, HttpServletResponse response)  throws Exception {
    	if(order)
    		{
    			init(request,response);
    		}
    	else
    		{
    			reorder(request,response);
    		}
        	return show(request,response);	
    }
    
    @RequestMapping(value="package/reOrder")
	public ModelAndView reOrder(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		order=!order;
		return new ModelAndView("redirect:/package");
    }
	
	
	//前端判断，对于碎片类型的物品，数量大于等于合成所需即提供合成按钮
	@RequestMapping(value="package/pieceTogether")
	public ModelAndView pieceTogether(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		//从前端的item获取package_id
		int package_id=2;
		//碎片数量大于合成所需时可以进行合成
		Package record=records.get(package_id);
		Goods good=goods.get(package_id);
		
		System.out.println(records.toString());
		
		record.setGoodsNum(record.getGoodsNum()-good.getGoodsAttr());
		
		if(record.getGoodsNum()==0)//当碎片使用完时删除package中的记录
			packageMapper.deleteByPrimaryKey(record.getPackageId());
		packageMapper.updateByPrimaryKey(record);

		good=goodsMapper.selectBygoods_name(good.getGoodsName());
		//判断玩家仓库中是否存在此球员
		record=packageMapper.selectByUserID_goodsid(0,good.getId());//user.getID()
		if(record==null)
		{
			//如果玩家仓库中没有此球员，则添加新球员数据
			Package newrecord=new Package();
			newrecord.setGoodsId(good.getId());
			newrecord.setUserId(0);//user.getid()
			packageMapper.insert_autoIncrement(newrecord);
		}
		else//更新物品信息
		{
			record.setGoodsNum(record.getGoodsNum()+1);
			packageMapper.updateByPrimaryKey(record);
		}
		
		return new ModelAndView("redirect:/package");
	}
	
	//对于消耗品，提供使用接口
	@RequestMapping(value="package/apply")
	public ModelAndView apply(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		//从前端的item获取package_id
		int package_id=1;
	
		Package record=records.get(package_id);
		Goods good=goods.get(package_id);
		
		//添加消耗品表记录
		

		return new ModelAndView("redirect:/package");
	}
}
