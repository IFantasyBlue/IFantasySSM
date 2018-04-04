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
    	@Resource
    private IUserService userService;
    	@Resource
    private User user;
    	@Resource
    private PackageMapper packageMapper;
    	@Resource
    private GoodsMapper goodsMapper;
    	
    private List<Package> records=new ArrayList<>();
    private List<Goods> goods=new ArrayList<>();
    
    ////从HttpRequest解析用户信息
    private void getUser(HttpServletRequest request, HttpServletResponse response)
    {
    	int userID= Integer.parseInt(request.getParameter("id"));
    		user=userService.getById(userID);
    }
	//@Resource //注入对象
	@RequestMapping(value="package")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		//int userID= Integer.parseInt(request.getParameter("id"));
		//user=userService.getById(userID);
		//从HttpRequest解析用户信息，从中得到用户所对应的package_num
		records=packageMapper.selectByPackage_num(1);//user.getPackageNum());
		
		if(records.isEmpty()==false)
		{
			for (int i=0;i<records.size();i++) {
				goods.add(goodsMapper.selectByPrimaryKey(records.get(i).getGoodsId()));
			}
		}
		
        	ModelAndView mav = new ModelAndView("package");
        	
        	//将当前用户package_num对应的的记录全部列出来（package.goods_num,goods）
        	mav.addObject("message","goods: ");
        	mav.addObject("records",records.toString());
        	mav.addObject("goods",goods.toString());
        	
        return mav;
    }
	
	@RequestMapping(value="pieceTogether")
	public ModelAndView pieceTogether(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		//从前端的item获取<package>list,<goods>list,package_id
		
		Package record=new Package();
		ModelAndView mav = new ModelAndView("package");
		return mav;
	}
}
