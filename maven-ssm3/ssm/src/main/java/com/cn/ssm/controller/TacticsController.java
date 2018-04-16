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
	
	/*
	 * 处理最开始的战术按钮响应
	 * 向数据库查询当前装备的进攻和防守战术
	 */
	@RequestMapping("equipped tactics")
    public String toEquipped(HttpServletRequest request,Model model){
		
		int userId = Integer.parseInt(request.getParameter("id"));

        User user = userService.getById(userId);
        TacticsVO tacticsVO = new TacticsVO();
        tacticsVO.setoTactics(oTacticsService.getByID(user.getoTactics()));
        tacticsVO.setdTactics(dTacticsService.getByID(user.getdTactics()));
        
        JSONObject json = JSONObject.fromObject(tacticsVO);
        
        return "equippedTactics";
    }
	
	/*
	 * 处理进攻按钮相应
	 * 向数据库查询所有的进攻战术
	 */
    @RequestMapping("otacticsShow")
    public String toOTactics(HttpServletRequest request,Model model){

        List<OTactics> oTactics = oTacticsService.getAll();
        
        JSONArray json = JSONArray.fromObject(oTactics);
        
        return "showOtactics";
    }
    
    /*
     * 处理防守按钮相应
     * 向数据库查询所有的防守战术
     */
    @RequestMapping("dtacticsShow")
    public String toDTactics(HttpServletRequest request,Model model){

    	List<DTactics> dTactics = dTacticsService.getAll();
        
        JSONArray json = JSONArray.fromObject(dTactics);
        
        return "showDtactics";
    }
    
    /*
     * 处理防守战术布置按钮相应
     * 对于不同的战术进行不同的战力偏正计算
     */
    @RequestMapping("dtacticsEquipped")
    public String toDTacticsEquipped(HttpServletRequest request,Model model){

    	int dtactics_Id = Integer.parseInt(request.getParameter("dtactics_id"));
        
        //JSONArray json = JSONArray.fromObject(dTactics);
        
        return "equippedDtactics";
    }
    
    /*
     * 处理进攻战术布置按钮相应
     * 对于不同的战术进行不同的战力偏正计算
     */
    @RequestMapping("otacticsEquipped")
    public String toOTacticsEquipped(HttpServletRequest request,Model model){

    	int otactics_Id = Integer.parseInt(request.getParameter("otactics_id"));
        
        //JSONArray json = JSONArray.fromObject(dTactics);
        
        return "equippedOtactics";
    }
}
