package com.altruist.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altruist.service.VoiceChatService;
import com.altruist.utils.MessageSender;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping
@CrossOrigin(value = "*")
@Slf4j
@Data
public class MainController {

	@Autowired
	VoiceChatService voicechatrservice;
	
	@Autowired
	MessageSender msgsender;
	
	@GetMapping("web")
	public String saveSingleSub(@RequestParam(name="ani",required = false) String ani,
			@RequestParam(name="cmpid",required=false) String cmpid,
			@RequestParam(name="planid",required = false) String planid, 
			@RequestParam(name="userType", required = false) String usertype,
			@RequestParam(name="action",required = false) String action, 
			@RequestParam(name="dnis",required = false) String dnis,
			@RequestParam(name="circleid",required=false) String circleid, 
			@RequestParam(name = "mode", required = false) String mode,
			@RequestParam(name="platform", required = false) String platform,
			@RequestParam(name = "gender", required = false) String gender,
			@RequestParam(name = "genderupdatemode", required = false) String genderupdatemode,
			@RequestParam(name = "age", required = false) String age,
			@RequestParam(name = "langid", required = false) String langid,
			@RequestParam(name = "granttype", required = false) String granttype,
			@RequestParam(name = "authrizedchatid", required = false) String authrizedchatid,
			@RequestParam(name = "calledparty_gender", required = false) String calledparty_gender,
			@RequestParam(name = "callparty_circleid", required = false) String callparty_circleid,
			@RequestParam(name = "chatid", required = false) String chatid,
			@RequestParam(name = "userprofiletypeid", required = false) String userprofiletypeid,
			@RequestParam(name = "userprofilevalues", required = false) String userprofilevalues,
			@RequestParam(name = "callstartdatetime", required = false) String callstartdatetime,
			@RequestParam(name = "callenddatetime", required = false) String callenddatetime,
			@RequestParam(name = "historybutton", required = false) String historybutton,
			 @RequestParam(name = "pulse", required = false) String pulse,
			@RequestParam(name = "callduration", required = false) String duration,
			@RequestParam(name = "incomingChannel", required = false) String incomingChannel,
			@RequestParam(name = "flag", required = false) String flag,
			@RequestParam(name = "in_value1", required = false) String in_value1, @RequestParam(name = "in_value2", required = false) String in_value2,
			@RequestParam(name = "in_value3", required = false) String in_value3, @RequestParam(name = "in_value4", required = false) String in_value4,
			@RequestParam(name = "in_value5", required = false) String in_value5, @RequestParam(name = "in_value6", required = false) String in_value6,
			@RequestParam(name = "in_value7", required = false) String in_value7, @RequestParam(name = "in_value8", required = false) String in_value8,
			@RequestParam(name = "method", required = false) String method,@RequestParam(name = "filename", required = false) String filename,
			@RequestParam(name = "sessionid", required = false) String sessionid,
			
			@RequestParam(name = "patchedani", required = false) String patchedani,
			@RequestParam(name = "patchedchatid", required = false) String patchedchatid,
			@RequestParam(name = "duration", required = false) String duration1,
			@RequestParam(name = "callstatus", required = false) String callstatus,
			@RequestParam(name = "calltime", required = false) String calltime,
			@RequestParam(name = "callendtime", required = false) String callendtime,
			@RequestParam(name = "ServiceSelect", required = false) String ServiceSelect,
			@RequestParam(name = "calledparty_circleid", required = false) String calledparty_circleid
	
			) {
		log.info("Start logging at "+new Date());
		String response = null;
		if(action.equals("1"))
		{
			log.info(" "+new Date()+" Inside Action 1");
			log.info("Request params : "+"ani "+ani+ "cmpid "+cmpid+"planid "+planid+ "usertype "+usertype+"action "+action+"dnis "+dnis+"circleid "+circleid+"langid "+langid+"mode "+mode+"platform "+platform);
			response = voicechatrservice.addSub(ani, cmpid, planid, usertype, action, dnis, circleid, langid, mode,platform);
			log.info("Response : "+response);
		}
		else if(action.equals("2"))
		{
			log.info(" "+new Date()+"Inside Action 2");
			log.info("Request params : "+"ani "+ani);
			response  = voicechatrservice.checkStatus(ani);
			log.info("Response : "+response);
		}
		else if(action.equals("3"))
		{
			log.info(" "+new Date()+"Inside Action 3");
			log.info("Request params : "+"ani "+ani+ "cmpid "+cmpid+"planid "+planid+ "usertype "+usertype+"action "+action+"dnis "+dnis+"circleid "+circleid+"langid "+langid+"genderupdatemode "+genderupdatemode+"gender "+gender+"age "+age);
			response = voicechatrservice.updateProfile(ani, cmpid, planid, usertype, action, dnis, gender, genderupdatemode, age, langid, circleid);
			log.info("Response : "+response);
		}
		else if(action.equals("4"))
		{
			log.info(" "+new Date()+"Inside Action 4");
			log.info("Request params : "+"ani "+ani+ "filename "+filename);
			response = voicechatrservice.saveRecordFile(ani,filename);
			log.info("Response : "+response);
		}
		else if(action.equals("5"))
		{
			log.info(" "+new Date()+"Inside Action 5");
			log.info("Request params : "+"ani "+ani+ "cmpid "+cmpid+"planid "+planid+"action "+action+"dnis "+dnis+"granttype "+granttype+"authrizedchatid "+authrizedchatid);
			response = voicechatrservice.blockUnblockProfile(ani, cmpid, planid, action, granttype, dnis, authrizedchatid);
			log.info("Response : "+response);
		}
		else if(action.equals("6"))
		{
			log.info(" "+new Date()+"Inside Action 6");
			log.info("Request params : "+"gender "+gender);
			log.info("Response : recordprofiles="+String.valueOf(voicechatrservice.getAllProfiles(gender)));
			return "recordprofiles="+String.valueOf(voicechatrservice.getAllProfiles(gender));
			
		}
		
		else if(action.equals("9"))
		{
			log.info(" "+new Date()+"Inside Action 9");
			log.info("Request params : "+"ani "+ani+ "cmpid "+cmpid+"planid "+planid+"action "+action+"dnis "+dnis+"calledparty_gender "+calledparty_gender+"callparty_circleid "+callparty_circleid+"chatid "+chatid);
			response = voicechatrservice.getCallpartyStatus(ani, cmpid, planid, action, dnis, calledparty_gender,	callparty_circleid, chatid);
			log.info("Response :"+response);
		}
		else if(action.equals("10"))
		{
			log.info(" "+new Date()+"Inside Action 10");
			log.info("Request params : "+"ani "+ani+ "cmpid "+cmpid+"planid "+planid+"action "+action+"dnis "+dnis+"usertype "+usertype+"circleid "+circleid);
			response = voicechatrservice.updateUnSub(ani, cmpid, planid, usertype, action, dnis, circleid);
			log.info("Response :"+response);
		}
	
		else if(action.equals("12"))
		{
			log.info(" "+new Date()+"Inside Action 12");
			log.info("Request params : "+"ani "+ani+ "cmpid "+cmpid+"planid "+planid+"action "+action+"dnis "+dnis+"userprofiletypeid "+userprofiletypeid+"userprofilevalues "+userprofilevalues);
			response = voicechatrservice.getHobbyInterest(ani, cmpid, planid, action, dnis, userprofiletypeid,userprofilevalues);
			log.info("Response :"+response);
		}
		else if(action.equals("14"))
		{
			log.info(" "+new Date()+"Inside Action 14");
			log.info("Request params : "+"ani "+ani+ "chatid "+chatid+"patchedani "+patchedani+"action "+action+"dnis "+dnis+"patchedchatid "+patchedchatid+"duration "+duration1+"callstatus "+callstatus+"calltime "+calltime+"callendtime "+callendtime+"cmpid "+cmpid+"planid "+planid+"ServiceSelect "+ServiceSelect+"circleid "+circleid+"gender "+gender+"calledparty_gender "+calledparty_gender+"calledparty_circleid "+calledparty_circleid);
			response = voicechatrservice.chatLogs(ani,dnis,chatid,patchedani,patchedchatid,duration1,callstatus,calltime,callendtime,cmpid,planid,ServiceSelect,circleid,gender,calledparty_gender,calledparty_circleid);
			log.info("Response :"+response);
		}
		else if(action.equals("15"))
		{
			log.info(" "+new Date()+"Inside Action 15");
			log.info("Request params : "+"ani "+ani+ "cmpid "+cmpid+"planid "+planid+"action "+action+"dnis "+dnis+"callstartdatetime "+callstartdatetime+"callenddatetime "+callenddatetime+"historybutton "+historybutton+"platform "+platform+"pulse "+pulse+"duration "+duration+"incomingChannel "+incomingChannel);
			response = voicechatrservice.saveIvrLogs(ani, cmpid, planid, action, dnis, callstartdatetime, callenddatetime,	historybutton, platform, pulse, duration, incomingChannel);
			log.info("Response :"+response);
		}
		else if(action.equals("22"))
		{
			log.info(" "+new Date()+"Inside Action 22");
			log.info("Request params : "+"ani "+ani+"action "+action+"flag "+flag+"in_value1 "+in_value1+"in_value2 "+in_value2+"in_value3 "+in_value3+"in_value4 "+in_value4+"in_value5 "+in_value5+"in_value6 "+in_value6+"in_value7 "+in_value7+"in_value8 "+in_value8+"method "+method);
			response = voicechatrservice.updateTransferRelease(ani, action, flag, in_value1, in_value2, in_value3,in_value4, in_value5, in_value6, in_value7, in_value8, method);
			log.info("Response :"+response);
		}
		else
		{
			log.info(" "+new Date()+"Inside Invalid Action ");
			response="invalid response";
			log.info("Response :"+response);
		}
		log.info("Response to be sent to user after all process :"+response);
		return response;
		
	}

//	@PostMapping("v1/get/single/status")
//	public String checkStatus(@RequestParam("ani") String ani, @RequestParam("cmpid") String cmpid,
//			@RequestParam("planid") String planid, @RequestParam("userType") String usertype,
//			@RequestParam("action") String action, @RequestParam("dnis") String dnis,
//			@RequestParam("circleid") String circleid) {
//
//		String response = voicechatrservice.checkStatus(ani);
//
//		return response;
//	}

//	@PostMapping("v1/update/single/profile")
//	public String updateProfile(@RequestParam(name = "ani") String ani, @RequestParam(name = "cmpid") String cmpid,
//			@RequestParam(name = "planid") String planid, @RequestParam(name = "userType") String usertype,
//			@RequestParam(name = "action") String action, @RequestParam(name = "dnis") String dnis,
//			@RequestParam(name = "gender", required = false) String gender,
//			@RequestParam(name = "genderupdatemode", required = false) String genderupdatemode,
//			@RequestParam(name = "age", required = false) String age,
//			@RequestParam(name = "langid", required = false) String langid,
//			@RequestParam(name = "circleid") String circleid) {
//
//		String response = voicechatrservice.updateProfile(ani, cmpid, planid, usertype, action, dnis, gender,
//				genderupdatemode, age, langid, circleid);
//
//		return response;
//	}

//	@PostMapping("v1/block/unblock/profile")
//	public String blockUnblock(@RequestParam(name = "ani") String ani, @RequestParam(name = "cmpid") String cmpid,
//			@RequestParam(name = "planid") String planid, @RequestParam(name = "action") String action,
//			@RequestParam(name = "granttype", required = false) String granttype,
//			@RequestParam(name = "dnis") String dnis,
//			@RequestParam(name = "authrizedchatid", required = false) String authrizedchatid) {
//		String response = null;
//		response = voicechatrservice.blockUnblockProfile(ani, cmpid, planid, action, granttype, dnis, authrizedchatid);
//		return response;
//	}

//	@PostMapping("v1/get/recorded/profile")
//	public List<String> recordedProfile(@RequestParam(name = "ani") String ani,
//			@RequestParam(name = "cmpid") String cmpid, @RequestParam(name = "planid") String planid,
//			@RequestParam(name = "action") String action, @RequestParam(name = "dnis") String dnis,
//			@RequestParam(name = "gender", required = false) String gender,
//			@RequestParam(name = "chatroom", required = false) String chatroom,
//			@RequestParam(name = "langid", required = false) String langid,
//			@RequestParam(name = "chatType") String chattype) {
//		return voicechatrservice.getAllProfiles(gender);
//	}

//	@PostMapping("v1/save/ivrlogs")
//	public String saveIvrLogs(@RequestParam(name = "ani") String ani, @RequestParam(name = "cmpid") String cmpid,
//			@RequestParam(name = "planid") String planid, @RequestParam(name = "action") String action,
//			@RequestParam(name = "dnis") String dnis,
//			@RequestParam(name = "callstartdatetime", required = false) String callstartdatetime,
//			@RequestParam(name = "callenddatetime", required = false) String callenddatetime,
//			@RequestParam(name = "historybutton", required = false) String historybutton,
//			@RequestParam(name = "platform") String platform, @RequestParam(name = "pulse") String pulse,
//			@RequestParam(name = "callduration") String duration,
//			@RequestParam(name = "incomingChannel") String incomingChannel) {
//		String response = null;
//
//		response = voicechatrservice.saveIvrLogs(ani, cmpid, planid, action, dnis, callstartdatetime, callenddatetime,
//				historybutton, platform, pulse, duration, incomingChannel);
//		return response;
//	}

//	@PostMapping("v1/get/status/calledparty")
//	public String getCalledPartyStatus(@RequestParam(name = "ani") String ani,
//			@RequestParam(name = "cmpid") String cmpid, @RequestParam(name = "planid") String planid,
//			@RequestParam(name = "action") String action, @RequestParam(name = "dnis") String dnis,
//			@RequestParam(name = "calledparty_gender") String calledparty_gender,
//			@RequestParam(name = "callparty_circleid") String callparty_circleid,
//			@RequestParam(name = "chatid") String chatid) {
//		String response = null;
//		response = voicechatrservice.getCallpartyStatus(ani, cmpid, planid, action, dnis, calledparty_gender,
//				callparty_circleid, chatid);
//		return response;
//	}

//	@PostMapping("v1/update/transfer/release")  22
//	public String updateTransferRelease(@RequestParam(name = "ani") String ani,
//			@RequestParam(name = "action") String action, @RequestParam(name = "flag") String flag,
//			@RequestParam(name = "in_value1") String in_value1, @RequestParam(name = "in_value2") String in_value2,
//			@RequestParam(name = "in_value3") String in_value3, @RequestParam(name = "in_value4") String in_value4,
//			@RequestParam(name = "in_value5") String in_value5, @RequestParam(name = "in_value6") String in_value6,
//			@RequestParam(name = "in_value7") String in_value7, @RequestParam(name = "in_value8") String in_value8,
//			@RequestParam(name = "method") String method) {
//		String response = null;
//		response = voicechatrservice.updateTransferRelease(ani, action, flag, in_value1, in_value2, in_value3,
//				in_value4, in_value5, in_value6, in_value7, in_value8, method);
//		return response;
//
//	}

//	@PostMapping("v1/update/single/unsub")  10
//	public String updateSingleUnsub(@RequestParam("ani") String ani, @RequestParam("cmpid") String cmpid,
//			@RequestParam("planid") String planid, @RequestParam("userType") String usertype,
//			@RequestParam("action") String action, @RequestParam("dnis") String dnis,
//			@RequestParam("circleid") String circleid) {
//		String response = null;
//		response = voicechatrservice.updateUnSub(ani, cmpid, planid, usertype, action, dnis, circleid);
//		return response;
//	}

//	@PostMapping("v1/get/hobbyinterest")  12
//	public String getHobbyInterest(@RequestParam("ani") String ani, @RequestParam("cmpid") String cmpid,
//			@RequestParam("planid") String planid, @RequestParam("userprofiletypeid") String userprofiletypeid,
//			@RequestParam("action") String action, @RequestParam("dnis") String dnis,
//			@RequestParam("userprofilevalues") String userprofilevalues) {
//		String response = null;
//
//		response = voicechatrservice.getHobbyInterest(ani, cmpid, planid, action, dnis, userprofiletypeid,
//				userprofilevalues);
//
//		return response;
//	}
}
