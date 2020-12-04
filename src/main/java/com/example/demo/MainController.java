package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@Autowired
	UserDataRepository repository;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView indexGet(ModelAndView mv) {
		List<UserData> customers = repository.findAll();
		mv.addObject("customers",customers);
		mv.setViewName("index");
		return mv;
	}
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ModelAndView indexPost(@ModelAttribute("formModel") UserData
	userData, ModelAndView mv){
	repository.saveAndFlush(userData);
	return new ModelAndView("redirect:/");
	}
	@RequestMapping(value="/auction")
	public ModelAndView homePage(ModelAndView mv) {
		mv.setViewName("homePage");
		return mv;
	}
	@RequestMapping(value="/login")
	public ModelAndView login(ModelAndView mv) {
		mv.setViewName("login");
		return mv;
	}
	@RequestMapping(value="/regist")
	public ModelAndView memberRegist(ModelAndView mv) {
		mv.setViewName("memberRegist");
		return mv;
	}
//@RequestMapping(value="/",method=RequestMethod.GET)
//public ModelAndView indexGet(ModelAndView mv){
//	mv.addObject("name", "室大輝");
//	mv.addObject("age","年齢がここに入ります");
//	mv.addObject("height","身長がここに入ります");
//	mv.setViewName("index");
//	return mv;
//}
//@RequestMapping(value="/",method=RequestMethod.POST)
//public ModelAndView indexPost(ModelAndView mv,@RequestParam("nameVal")String 
//		name,@RequestParam("ageVal")String age,@RequestParam("heightVal")String height){
//	ArrayList<String[]>customers=new ArrayList<String[]>();
//	customers.add(new String[] {"佐藤HTML太郎","35歳","男性"});
//	customers.add(new String[] {"鈴木Java五郎","24歳","男性"});
//	customers.add(new String[] {"高橋CSS子","29歳","女性"});
//	mv.addObject("customers", customers);
//	mv.addObject("name", name);
//	mv.addObject("age",age);
//	mv.addObject("height",height);
//	mv.setViewName("index");
//	return mv;
//}

}
