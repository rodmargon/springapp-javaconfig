package com.mycompany.springapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.springapp.service.PriceIncrease;
import com.mycompany.springapp.service.ProductManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller
@RequestMapping(value="/priceIncrease.htm")
public class PriceIncreaseFormController {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    private ProductManager productManager;
    
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid PriceIncrease priceIncrease, BindingResult result) {
    	if(result.hasErrors()) {
    		return "priceIncrease";
    	}
    	
    	int increase = priceIncrease.getPercentage();
    	logger.info("Increasing prices by " + increase + "%.");
    	
    	productManager.increasePrice(increase);
    	 
    	return "redirect:/hello.htm";
    }
    
    @RequestMapping(method = RequestMethod.GET)    
    protected PriceIncrease formBackingObject(HttpServletRequest request) throws ServletException {
    	PriceIncrease priceIncrease = new PriceIncrease();
    	priceIncrease.setPercentage(15);
    	return priceIncrease;
    }
    
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public ProductManager getProductManager() {
        return productManager;
    }
}
