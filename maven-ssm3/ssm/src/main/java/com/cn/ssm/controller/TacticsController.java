package com.cn.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ssm.entity.DTactics;
import com.cn.ssm.entity.OTactics;
import com.cn.ssm.entity.User;
import com.cn.ssm.service.IDTacticsService;
import com.cn.ssm.service.IOTacticsService;
import com.cn.ssm.service.IUserService;
import com.cn.ssm.vo.TacticsVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/tactics")
public class TacticsController {
	
	@Resource
    private IOTacticsService oTacticsService;
	@Resource
    private IDTacticsService dTacticsService;
	@Resource
    private IUserService userService;
	
	@RequestMapping("equipped tactics")
    public String toEquipped(HttpServletRequest request,Model model){
		
		int userId = Integer.parseInt(request.getParameter("id"));

        User user = userService.getById(userId);
        TacticsVO tacticsVO = new TacticsVO();
        tacticsVO.oTactics = oTacticsService.getByID(user.getoTactics());
        tacticsVO.dTactics = dTacticsService.getByID(user.getdTactics());
        
        JSONObject json = JSONObject.fromObject(tacticsVO);
        
        return "equippedTactics";
    }
	
    @RequestMapping("otacticsShow")
    public String toOTactics(HttpServletRequest request,Model model){

        List<OTactics> oTactics = oTacticsService.getAll();
        
        JSONArray json = JSONArray.fromObject(oTactics);
        
        return "showOtactics";
    }
    
    @RequestMapping("dtacticsShow")
    public String toDTactics(HttpServletRequest request,Model model){

        List<DTactics> dTactics = dTacticsService.getAll();
        
        JSONArray json = JSONArray.fromObject(dTactics);
        
        return "showDtactics";
    }
    
   
}
