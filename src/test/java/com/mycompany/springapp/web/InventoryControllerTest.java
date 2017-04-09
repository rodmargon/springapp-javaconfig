package com.mycompany.springapp.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springapp.domain.Product;
import com.mycompany.springapp.repository.InMemoryProductDao;
import com.mycompany.springapp.service.SimpleProductManager;

public class InventoryControllerTest {

	@Test
	public void testHandleRequest() throws Exception {
		InventoryController controller = new InventoryController();
		SimpleProductManager spm = new SimpleProductManager();
		spm.setProductDao(new InMemoryProductDao(new ArrayList<Product>()));
		controller.setProductManager(spm);
		ModelAndView modelView =  controller.handleRequest(null, null);
		assertEquals("hello", modelView.getViewName());
		assertNotNull(modelView.getModel());
		@SuppressWarnings("unchecked")
		Map<String, Object> modelMap = (Map<String, Object>) modelView.getModelMap().get("model");
		String nowValue = (String) modelMap.get("now");
		assertNotNull(nowValue);
	}

}
