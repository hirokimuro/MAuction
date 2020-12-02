package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
@RequestMapping(value="/",method=RequestMethod.GET)
public ModelAndView indexGet(ModelAndView mv){
	mv.addObject("name", "室大輝");
	mv.addObject("age","年齢がここに入ります");
	mv.addObject("height","身長がここに入ります");
	mv.setViewName("index");
	return mv;
}
@RequestMapping(value="/",method=RequestMethod.POST)
public ModelAndView indexPost(ModelAndView mv,@RequestParam("nameVal")String 
		name,@RequestParam("ageVal")String age,@RequestParam("heightVal")String height){
	ArrayList<String[]>customers=new ArrayList<String[]>();
	customers.add(new String[] {"佐藤HTML太郎","35歳","男性"});
	customers.add(new String[] {"鈴木Java五郎","24歳","男性"});
	customers.add(new String[] {"高橋CSS子","29歳","女性"});
	mv.addObject("customers", customers);
	mv.addObject("name", name);
	mv.addObject("age",age);
	mv.addObject("height",height);
	mv.setViewName("index");
	return mv;
}







}
