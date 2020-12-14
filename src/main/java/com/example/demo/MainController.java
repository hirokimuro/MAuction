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
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public ModelAndView testGet(ModelAndView mv , String[] args ) 
	{
		ProductData products = repositoryP.findById((long)1).get();
		products.getPhot();
		mv.addObject("phot",products.getPhot());
		
		StringBuffer data = new StringBuffer();
		data.append("data:image/jpeg;base64,");
		data.append(products.getPhot());
		mv.addObject("image",data.toString());
		mv.setViewName("test");
		return mv;
	
	}
	@RequestMapping(value="/a", method=RequestMethod.GET)
	public ModelAndView headerGet(ModelAndView mv , String[] args ) 
	{
		List<ProductData> products = repositoryP.findAll();
		ProductData products1 = repositoryP.findById((long)1).get();
		products1.getPhot();
		StringBuffer data = new StringBuffer();
		data.append("data:image/jpeg;base64,");
		data.append(products1.getPhot());
		
		mv.addObject("products",products);
		mv.addObject("phot",data.toString());
		mv.setViewName("header");
		
//		Date dateNow = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd");
//		mv.addObject("dateNow",sdf.format(dateNow));
		return mv;
	
	}
	
	
	@RequestMapping(value="/listing",method=RequestMethod.GET)
	public ModelAndView listingGet(ModelAndView mv , String[] args ) 
	{
//		ProductData products = repositoryP.findAll();
		List<ProductData> products = repositoryP.findAll();
//		ProductData products1 = repositoryP.findById((long)1).get();(情報が無いためこのページで使えない)
//		products.phot();
//		mv.addObject("phot",products1.getPhot());
//		if(((ProductData) products).getPhot() != null) {
//		String image =((ProductData) products).getPhot();
//		StringBuffer data = new StringBuffer();
//		data.append("data:image/jpeg;base64,");
//		data.append(image);
//		mv.addObject("phot",data.toString());
//		}
		mv.addObject("products",products);
		mv.setViewName("listing");
	
		
		
		Date dateNow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd");
		mv.addObject("dateNow",sdf.format(dateNow));
		
		return mv;
	}
	
	
	@RequestMapping(value="/listing", method = RequestMethod.POST)
	public ModelAndView listingPost(@RequestParam("image") MultipartFile image,@ModelAttribute("formModel") ProductData productData, ModelAndView mv,
			String[] args,Model model)throws Exception{
	
    StringBuffer data = new StringBuffer();
        
    String base64 = new String(Base64.encodeBase64(image.getBytes()),"ASCII");
   
    System.out.println(base64);
    productData.setPhot(base64);
    
//    data.append("data:image/jpeg;base64,");
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
