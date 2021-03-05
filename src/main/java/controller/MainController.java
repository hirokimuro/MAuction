package controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName.Form;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import entity.DatailProduct;
import entity.ProductData;
import entity.UserData;
import repository.DatailProductRepository;
import repository.ProductDataRepository;
import repository.UserDataRepository;

@Controller
public class MainController {
	
	@Autowired
	DatailProductRepository datailRepository;
	@Autowired
	UserDataRepository repository;
	@Autowired
	ProductDataRepository repositoryP;
//	@Autowired
//	JdbcTemplate jdbcTemplate;
	
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView productListGet(ModelAndView mv,HttpServletResponse httpServletResponse
    		,@CookieValue("key1")String cookieValue,HttpServletRequest request){
        List<DatailProduct> datailProductList = datailRepository.findAll();
        mv.addObject("Lists", datailProductList);
        mv.setViewName("productList");
        
//        httpServletResponse.addCookie(new Cookie("key1","value1"));
        System.out.println(cookieValue);
        
        
        
        Cookie cookie = new Cookie("key1","value1");
        
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
//        
//        Cookie cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if ("id".equals(cookie.getName())) {
//                cookie.setMaxAge(0);
//                cookie.setPath("/");
//                response.addCookie(cookie);
//            }
//        }

        return mv;
    }
 
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView productListPost(@ModelAttribute("formModel") DatailProduct
    		datailProduct, ModelAndView mv,HttpServletRequest httpServletRequest) throws Exception{
//        String name = httpServletRequest.getRemoteUser();
//        ProductData productData = repositoryP.findOne(name).get();

        datailRepository.saveAndFlush(datailProduct);
    	return new ModelAndView("redirect:/list");
//        return productListGet(mv);
    	
    }
	
	
	@RequestMapping(value="/a", method=RequestMethod.GET)
	public ModelAndView headerGet(ModelAndView mv , String[] args ) 
	{
		List<ProductData> products = repositoryP.findAll();
		System.out.println(products.get(0).getUserData().getUsername());
		System.out.println(products.get(0).getId());
		mv.addObject("products",products);
//		mv.addObject("phot",data.toString());
		mv.setViewName("header");
		
		Date dateNow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd");
		mv.addObject("dateNow",sdf.format(dateNow));
		return mv;
	
	}
	
	
	@RequestMapping(value="/listing",method=RequestMethod.GET)
	public ModelAndView listingGet(ModelAndView mv , String[] args) 
	{
//		ProductData products = repositoryP.findAll();
		List<ProductData> products = repositoryP.findAll();

		mv.addObject("products",products);
		mv.setViewName("listing");
		
		Date dateNow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd");
		mv.addObject("dateNow",sdf.format(dateNow));
		
		return mv;
	}
	
	
	@RequestMapping(value="/listing", method = RequestMethod.POST)
	public ModelAndView listingPost(@RequestParam("image") MultipartFile image,@ModelAttribute("formModel") ProductData productData, ModelAndView mv,
	String[] args,Model model,@Validated ProductData productdata,BindingResult br
	,@RequestParam("name") String name,HttpServletRequest httpServletRequest)throws Exception{
	
//	String username = httpServletRequest.getRemoteUser();	
//	System.out.println(username);
	String username = "室";
	UserData userData = repository.findByUsername(username);
//	ProductData products= new ProductData();
	productData.setUserData(userData);
//	products.setName(username);
	
		if(br.hasErrors()) {
			mv.addObject("error","必須項目です");
			return mv;
		}
		
    StringBuffer data = new StringBuffer();  
    String base64 = new String(Base64.encodeBase64(image.getBytes()),"ASCII");
    productData.setPhot(base64);
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
//	@RequestMapping(value="/login")
//	public ModelAndView login(ModelAndView mv) {
//		mv.setViewName("login");
//		return mv;
//	}
	@RequestMapping(value="/regist")
	public ModelAndView memberRegist(ModelAndView mv) {
		mv.setViewName("memberRegist");
		return mv;
	}
	
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
