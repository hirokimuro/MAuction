package com.example.demo;


import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName.Form;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
//	@Autowired
//	DatailProductRepository datailRepository;
	@Autowired
	UserDataRepository repository;
	@Autowired
	ProductDataRepository repositoryP;
	
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ModelAndView productListGet(ModelAndView mv) {
//        List<ProductData> productDataList = repositoryP.findAll();
//        mv.addObject("productDataList", productDataList);
//        mv.setViewName("productList");
//        return mv;
//    }
// 
//    @RequestMapping(value = "/list", method = RequestMethod.POST)
//    public ModelAndView productListPost(@ModelAttribute("formModel") ProductData
//    		productData, ModelAndView mv, HttpServletRequest httpServletRequest) {
//        String category = httpServletRequest.getRemoteUser();
//        DatailProduct datailProduct = datailRepository.findOne(category);
//        ProductData productData = new ProductData();
//        productData.setPhot(phot);
//        productData.setName(name);
//        repositoryP.save(productData);
//        return productListGet(mv);
//    }
	
	
//	@ModelAttribute
//	public ProductData setForm() {
//		return new ProductData();
//	}

	
	
	
	@RequestMapping(value="/listing",method=RequestMethod.GET)
	public ModelAndView listingGet(ModelAndView mv , String[] args ) 
	{
		List<ProductData> products = repositoryP.findAll();
		mv.addObject("products",products);
		mv.setViewName("listing");
		
		
		Date dateNow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd");
		mv.addObject("dateNow",sdf.format(dateNow));
		
		
		return mv;
	}
	
	
	@RequestMapping(value="/listing", method = RequestMethod.POST)
	public ModelAndView listingPost(@ModelAttribute("formModel") ProductData productData, ModelAndView mv,
			String[] args,Model model,@RequestParam("phot") String phot)throws Exception{
	
	byte[] photByte = Base64.decodeBase64(phot);
//	return null;
//    StringBuffer data = new StringBuffer();
//    String base64 = new String(Base64.encodeBase64(productData.getPhot().getBytes()),"ASCII");
//    data.append("data:image/jpeg;base64,");
//    data.append(base64);
    
    //StringBuffer phot = new StringBuffer();
//    productData.getPhot();	
//    String base64 = new String(Base64.encodeBase64(phot.getBytes()),"ASCII");
    System.out.println(photByte);
    
    
//    data.append("data:phot:image/jpeg;base64,");
//    data.append(base64);
  
//    model.addAttribute("base64phot",data.toString());
    
    repositoryP.saveAndFlush(productData);
	return new ModelAndView("redirect:/listing");
	
	}
	
	@RequestMapping(value="/regist",method=RequestMethod.GET)
	public ModelAndView memberRegistGet(ModelAndView mv) {
		List<UserData> customers = repository.findAll();
		mv.addObject("customers",customers);
		mv.setViewName("memberRegist");
		return mv;
	}
	@RequestMapping(value="/regist", method = RequestMethod.POST)
	public ModelAndView memberRegistPost(@ModelAttribute("formModel") UserData
	userData, ModelAndView mv){
	repository.saveAndFlush(userData);
	return new ModelAndView("redirect:/regist");
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
