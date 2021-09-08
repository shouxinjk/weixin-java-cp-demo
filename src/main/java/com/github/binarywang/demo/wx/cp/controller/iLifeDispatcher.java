package com.github.binarywang.demo.wx.cp.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.demo.wx.cp.utils.HttpClientHelper;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;

@RestController
@RequestMapping("/ilife-wework/wework/ilife")
public class iLifeDispatcher {
	private final Logger logger = LoggerFactory.getLogger(iLifeDispatcher.class);
	SimpleDateFormat dateFormatLong = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	String webHookUrlPrefix = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=";
	String webHookCompanyBroker = "03e0992f-0c8a-4043-aa84-411b62e4eb03";
	String webHookBrokerCustomer = "7a7d33d4-8e81-46c5-816e-306cc8d18334";
	
	/**
	 * 接收occasion定义的通知任务，包括企业微信群、企业朋友圈推送等
	 * 当前仅支持微信自带类型，需要提前将msg组织好。
	 * 参考：https://work.weixin.qq.com/api/doc/90000/90136/91770
	 */
	//发送模板消息到达人管理群
	@RequestMapping(value = "/notify-cp-company-broker", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendMsgToCompanyBrokerGroup(@RequestBody JSONObject json) throws WxErrorException, IOException {
		logger.debug("try to send occasion notification message.[json]"+JSONObject.toJSONString(json));
		JSONObject result = HttpClientHelper.getInstance().post(webHookUrlPrefix+webHookCompanyBroker, json,null);//由公众号处理，包括查询条目等
		return result;
	}
	
	//发送消息到达人客户群：当前不能实现。
	@RequestMapping(value = "/notify-cp-broker-customer", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendMsgToBrokerCustomerGroup(@RequestBody JSONObject json) throws WxErrorException, IOException {
		logger.debug("try to send occasion notification message.[json]"+JSONObject.toJSONString(json));
		JSONObject result = HttpClientHelper.getInstance().post(webHookUrlPrefix+webHookBrokerCustomer, json,null);//由公众号处理，包括查询条目等
		return result;
	}
	
	//发送消息到达人企业微信朋友圈
	@RequestMapping(value = "/notify-cp-broker-moment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendMsgToBrokerMoment(@RequestBody JSONObject json) throws WxErrorException, IOException {
		logger.debug("try to send occasion notification message.[json]"+JSONObject.toJSONString(json));
		JSONObject result = HttpClientHelper.getInstance().post(webHookUrlPrefix+webHookBrokerCustomer, json,null);//由公众号处理，包括查询条目等
		return result;
	}
	
}
