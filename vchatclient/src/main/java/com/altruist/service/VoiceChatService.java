package com.altruist.service;

import java.util.List;

public interface VoiceChatService {

	public String addSub(String ani, String cmpid, String planid, String usertype, String action, String dnis, String circleid, String langid, String mode, String platform);
	
	public String checkStatus(String ani);

	public String updateProfile(String ani, String cmpid, String planid, String usertype, String action, String dnis,
			String gender,String gendermode, String age, String langid, String circleid);

	public String blockUnblockProfile(String ani, String cmpid, String planid, String action, String granttype,
			String dnis, String authrizedchatid);

	public List<String> getAllProfiles(String gender);

	public String saveIvrLogs(String ani, String cmpid, String planid, String action, String dnis,
			String callstartdatetime, String callenddatetime, String historybutton, String platform, String pulse, String duration, String incomingChannel);

	public String getCallpartyStatus(String ani, String cmpid, String planid, String action, String dnis,
			String calledparty_gender, String callparty_circleid, String chatid);

	public String updateTransferRelease(String ani, String action, String flag, String in_value1, String in_value2,
			String in_value3, String in_value4, String in_value5, String in_value6, String in_value7, String in_value8, String method);

	public String updateUnSub(String ani, String cmpid, String planid, String usertype, String action, String dnis,
			String circleid);

	public String getHobbyInterest(String ani, String cmpid, String planid, String action, String dnis,
			String userprofiletypeid, String userprofilevalues);

	public String saveRecordFile(String ani, String filename);

	public String chatLogs(String ani, String dnis, String chatid, String patchedani, String patchedchatid,
			String duration1, String callstatus, String calltime, String callendtime, String cmpid, String planid,
			String serviceSelect, String circleid, String gender, String calledparty_gender,
			String calledparty_circleid);
	
	
}
