package com.github.sdd330.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
public class TestController {

	@RequestMapping(value="/test/get/json", method=RequestMethod.GET, produces="application/json")
	public String testGetJson() {
		JsonObject jsonObject = new JsonObject();
		JsonObject message = new JsonObject();
		message.addProperty("message", "a test");
		jsonObject.add("test", message);

		return jsonObject.toString();
	}
}