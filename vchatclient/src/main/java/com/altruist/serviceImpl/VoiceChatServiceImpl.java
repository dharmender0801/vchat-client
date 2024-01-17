package com.altruist.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.altruist.controller.MainController;
import com.altruist.entity.BillingEntity;
import com.altruist.entity.CallPatchLogicEntity;
import com.altruist.entity.CallPatchLogicLogEntity;
import com.altruist.entity.ChatLogEntity;
import com.altruist.entity.GrantListEntity;
import com.altruist.entity.IvrLogsEntity;
import com.altruist.entity.ProductConfigEntity;
import com.altruist.entity.ProfileEntity;
import com.altruist.entity.ProfileLogEntity;
import com.altruist.entity.VoiceChatEntity;
import com.altruist.entity.VoiceChatUnsubEntity;
import com.altruist.repo.BillingRepo;
import com.altruist.repo.CallPatchLogicLogRepo;
import com.altruist.repo.CallPatchLogicRepo;
import com.altruist.repo.ChatLogRepo;
import com.altruist.repo.GrantListRepo;
import com.altruist.repo.IvrLogsReop;
import com.altruist.repo.ProductConfigRepo;
import com.altruist.repo.ProfileLogRepo;
import com.altruist.repo.ProfileRepo;
import com.altruist.repo.UtilityRepo;
import com.altruist.repo.VoiceChatRepo;
import com.altruist.repo.VoiceChatUnsubRepo;
import com.altruist.service.VoiceChatService;
import com.altruist.utils.MessageSender;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Service
public class VoiceChatServiceImpl implements VoiceChatService {

	VoiceChatEntity voicechatentity = null;
	VoiceChatEntity savedvoicechatentity = null;
	BillingEntity billingentity = null;
	ProductConfigEntity productconfigentity = null;

	@Autowired
	VoiceChatRepo voicechatrepo;

	@Autowired
	BillingRepo billingrepo;

	@Autowired
	ProductConfigRepo productconfigrepo;

	@Autowired
	GrantListRepo grantlistrepo;

	@Autowired
	CallPatchLogicRepo callpatchlogicrepo;

	@Autowired
	CallPatchLogicLogRepo callpatchlogiclogrepo;

	@Autowired
	ProfileRepo profilerepo;

	@Autowired
	ProfileLogRepo profilelogrepo;

	@Autowired
	IvrLogsReop ivrlogsrepo;

	@Autowired
	UtilityRepo utilityrepo;

	@Autowired
	VoiceChatUnsubRepo voicechatunsubrepo;

	@Autowired
	ChatLogRepo chatlogrepo;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	MessageSender msgsender;

	@Value("${IVRLOGS}")
	String IVRLOGS;

	@Value("${CHATLOGS}")
	String CHATLOGS;

	@Value("${country.code}")
	String countrycode;

	@Value("${system.recordPath}")
	String systemrecordPath;

	@Value("${system.recordPath.copy}")
	String systemrecordPathcopy;

	@Value("${sub.msg.id}")
	String submsgid;

	@Value("${alreadysub.msg.id}")
	String alreadymsgid;

	@Value("${unsub.msg.id}")
	String unsubmsgid;

	@Value("${release.msg.id}")
	String releasemsgid;

	@Value("${login.msg.id}")
	String loginmsgid;

	@Value("${logout.msg.id}")
	String logoutmsgid;

	@Value("${failurebparty.msg.id}")
	String failurebpartymsgid;

	@Value("${freetrail.msg.id}")
	String freetrailmsgid;

	@Value("${block.msg.id}")
	String blockmsgid;

	@Value("${unblock.msg.id}")
	String unblockmsgid;

	@Value("${transfer.msg.id}")
	String transfermsgid;

	@Override
	public String addSub(String ani, String cmpid, String planid, String usertype, String action, String dnis,
			String circleid, String langid, String mode, String platform) {

		String response = null;
		System.out.println("ANI  " + ani);
		log.info(" " + new Date() + " Inside Action 1 with ANI " + ani);
		try {
			voicechatentity = voicechatrepo.findByAni(ani);
//		log.info("ANI found "+voicechatentity.toString());
			productconfigentity = productconfigrepo.findByUserType(usertype);
//		System.out.println(productconfigentity.toString());
			if (productconfigentity != null) {
				if (voicechatentity != null) {
					response = "ALREADY_SUBSCRIBE";
					msgsender.receiveData(ani, alreadymsgid);
				} else {
					voicechatentity = new VoiceChatEntity();
					voicechatentity.setAni(String.valueOf(ani));
					voicechatentity.setGender("N");
					voicechatentity.setCmpid(Long.valueOf(cmpid));
					voicechatentity.setPlanId(Long.valueOf(planid));
					voicechatentity.setUserType(usertype);
					voicechatentity.setDnis(dnis);
					voicechatentity.setCircleId(circleid);
					voicechatentity.setLangId(langid);
					voicechatentity.setMact(mode);
					voicechatentity.setRecordStatus("1");
					voicechatentity.setSubDateTime(new Date(System.currentTimeMillis()));
					System.out.println(voicechatentity.toString());
					voicechatrepo.save(voicechatentity);
					savedvoicechatentity = voicechatentity;
					if (savedvoicechatentity != null) {
						billingentity = new BillingEntity();
						billingentity.setAni(ani);
						billingentity.setCircleId(circleid);
						billingentity.setDateTime(new Date(System.currentTimeMillis()));
						billingentity.setMode(mode);
						billingentity.setTotalAmount(productconfigentity.getPrice());

						billingrepo.save(billingentity);

						response = "recordstatus='" + savedvoicechatentity.getRecordStatus() + "';langid='"
								+ savedvoicechatentity.getLangId() + "'" + ";circleid='"
								+ savedvoicechatentity.getCircleId() + "';voicechatid='"
								+ savedvoicechatentity.getVoiceChatId() + "';userType='"
								+ savedvoicechatentity.getUserType() + "'" + ";gender='"
								+ savedvoicechatentity.getGender() + "';age='" + savedvoicechatentity.getAge()
								+ "';recordprofile='" + savedvoicechatentity.getRecordProfile() + "'"
								+ ";granttype='LOGIN';ChatAllowed='underprocess';unsub_flag='2';consent_flag='2';";

						msgsender.receiveData(ani, savedvoicechatentity.getVoiceChatId(), submsgid);

					} else {
						response = "recordstatus='0';langid='0';circleid='0';voicechatid='0';userType='GSM';gender='N';age='0';recordprofile='0'"
								+ ";granttype='LOGOUT'";
					}
				}
			} else {
				response = "recordstatus='0';langid='0';circleid='0';voicechatid='0';userType='GSM';gender='N';age='0';recordprofile='0'"
						+ ";granttype='LOGOUT'";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String checkStatus(String ani) {
		String response = null;
		String granttype = null;
		String chatallowed = "LOGOUT";
		Date currentDate = new Date();

		try {
			voicechatentity = voicechatrepo.findByAni(ani);

			if (voicechatentity != null) {
				if (grantlistrepo.countByAuthorizer(ani) > 0) {
					granttype = "LOGOUT";
				} else {
					granttype = "LOGIN";
				}

				if (voicechatentity.getNextBilledDate() == null) {
					chatallowed = "underprocess";
					System.out.println("in null logic " + voicechatentity.getAni() + "chatallowed" + chatallowed);
				} else if (voicechatentity.getNextBilledDate().compareTo(currentDate) < 0) {
					chatallowed = "lowbalance";
					System.out.println("in null logic " + voicechatentity.getAni() + "chatallowed" + chatallowed);
				} else {
					chatallowed = "yes";
				}

				response = "recordstatus='" + voicechatentity.getRecordStatus() + "';langid='"
						+ voicechatentity.getLangId() + "'" + ";circleid='" + voicechatentity.getCircleId()
						+ "';voicechatid='" + voicechatentity.getVoiceChatId() + "';userType='"
						+ voicechatentity.getUserType() + "'" + ";gender='" + voicechatentity.getGender() + "';age='"
						+ voicechatentity.getAge() + "';recordprofile='" + voicechatentity.getRecordProfile() + "'"
						+ ";granttype='" + granttype + "';ChatAllowed='" + chatallowed
						+ "';unsub_flag='2';consent_flag='2';";
			} else {
				voicechatentity = new VoiceChatEntity();
				voicechatentity.setAni(ani);
				voicechatentity.setRecordStatus("0");
				voicechatentity.setLangId("0");
				voicechatentity.setCircleId("0");
				voicechatentity.setVoiceChatId("0");
				voicechatentity.setUserType("GSM");
				voicechatentity.setGender("N");
				voicechatentity.setAge("0");
				voicechatentity.setRecordProfile("0");

				response = "recordstatus='0';langid='0';circleid='0';voicechatid='0';userType='GSM';gender='N';age='0';recordprofile='0'"
						+ ";granttype='LOGOUT'";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String updateProfile(String ani, String cmpid, String planid, String usertype, String action, String dnis,
			String gender, String genderupdatemode, String age, String langid, String circleid) {

		String response = null;
		VoiceChatEntity updatedentity = null;
		Date currentDate = new Date();

		voicechatentity = voicechatrepo.findByAni(ani);
		String granttype = null;
		if (grantlistrepo.countByAuthorizer(ani) > 0) {
			granttype = "LOGOUT";
		} else {
			granttype = "LOGIN";
		}
		String chatallowed = null;

		if (voicechatentity != null) {
			if (voicechatentity.getNextBilledDate() == null) {
				chatallowed = "underprocess";
				System.out.println("in null logic " + voicechatentity.getAni() + "chatallowed" + chatallowed);
			} else if (voicechatentity.getNextBilledDate().compareTo(currentDate) < 0) {
				chatallowed = "lowbalance";
				System.out.println("in null logic " + voicechatentity.getAni() + "chatallowed" + chatallowed);
			} else {
				chatallowed = "yes";
			}
			System.out.println(voicechatentity.toString());

			if (gender != null && (age == null && langid == null)) {
				if (gender != "N" || !gender.equalsIgnoreCase("N")) {
					System.out.println("Inside gender not null");
					voicechatentity.setGender(gender.toUpperCase());
					voicechatentity.setGenderUpdateDate(new Date(System.currentTimeMillis()));
					voicechatentity.setGenderupdatemode(genderupdatemode);
					updatedentity = voicechatrepo.save(voicechatentity);
				}
			} else if (age != null && (gender == null && langid == null)) {
				System.out.println("Inside age not null");
				voicechatentity.setAge(age);

				updatedentity = voicechatrepo.save(voicechatentity);
			} else {
				System.out.println("Inside langid not null");
				voicechatentity.setLangId(langid);

				updatedentity = voicechatrepo.save(voicechatentity);
			}

			response = "recordstatus='" + voicechatentity.getRecordStatus() + "';langid='" + voicechatentity.getLangId()
					+ "'" + ";circleid='" + voicechatentity.getCircleId() + "';voicechatid='"
					+ voicechatentity.getVoiceChatId() + "';userType='" + voicechatentity.getUserType() + "'"
					+ ";gender='" + voicechatentity.getGender() + "';age='" + voicechatentity.getAge()
					+ "';recordprofile='" + voicechatentity.getRecordProfile() + "'" + ";granttype='" + granttype
					+ "';ChatAllowed='" + chatallowed + "';unsub_flag='2';consent_flag='2';";
		} else {
			response = "recordstatus='0';langid='0';circleid='0';voicechatid='0';userType='GSM';gender='N';age='0';recordprofile='0'"
					+ ";granttype='LOGOUT'";
		}
		return response;
	}

	@Override
	public String blockUnblockProfile(String ani, String cmpid, String planid, String action, String granttype,
			String dnis, String authrizedchatid) {

		String response = null;
		VoiceChatEntity voicechatentity = null;
		VoiceChatEntity voicechatentity1 = null;
		GrantListEntity grantlistentity = null;
		CallPatchLogicEntity callpatchlogicentity = null;
		ProfileEntity profileentity = null;
		try {
			voicechatentity1 = voicechatrepo.findByAni(ani);

			if (voicechatentity1 != null) {
				voicechatentity = voicechatrepo.findByVoiceChatId(authrizedchatid);

				if (voicechatentity != null) {

					if (granttype != null && granttype.equalsIgnoreCase("BLOCK")) {
						if (authrizedchatid.startsWith("854")) {
							authrizedchatid = authrizedchatid.substring(3, authrizedchatid.length());
						}
						grantlistentity = new GrantListEntity();
						grantlistentity.setAuthorized(voicechatentity.getAni());
						grantlistentity.setAuthorizedChatId(authrizedchatid);
						grantlistentity.setAuthorizer(ani);
						grantlistentity.setAuthorizerChatId(voicechatentity1.getVoiceChatId());
						grantlistentity.setGrantType(granttype.toUpperCase());

						grantlistrepo.save(grantlistentity);

						response = "Chat ID Blocked";

						msgsender.receiveData(ani, blockmsgid);
						;
					} else if (granttype != null && granttype.equalsIgnoreCase("UNBLOCK")) {
						if (authrizedchatid.startsWith("854")) {
							authrizedchatid = authrizedchatid.substring(3, authrizedchatid.length());
						}
						grantlistrepo.deleteByAuthorizerAndAuthorizedChatId(ani, authrizedchatid);
						response = "Chat ID Unblocked";
						msgsender.receiveData(ani, unblockmsgid);
					} else if (granttype != null && granttype.equalsIgnoreCase("LOGIN")) {

						grantlistentity = grantlistrepo.findByAuthorizerAndGrantType(ani, "LOGOUT");
						if (grantlistentity != null) {
							if (grantlistrepo.deleteByAuthorizer(ani) > 0) {
								callpatchlogicentity = callpatchlogicrepo.findByAni(ani);
								if (callpatchlogicentity != null) {
									callpatchlogicentity.setLoginStatus("LOGIN");
									callpatchlogicentity = callpatchlogicrepo.save(callpatchlogicentity);
									response = "ANI deleted from grant list and updated into call patch";
									profileentity = profilerepo.findByAni(ani);
									if (profileentity != null) {
										profileentity.setIsActive("1");
										profilerepo.save(profileentity);

										response = "granttype='LOGIN';secondbparty='" + ani + "'";
									}
								} else {
									response = "ANI Not Found in call patch";
								}

								System.out.println("ANI deleted from grant list");

							}
						} else {
							callpatchlogicentity = callpatchlogicrepo.findByAni(ani);
							if (callpatchlogicentity != null) {
								callpatchlogicentity.setLoginStatus("LOGIN");
								callpatchlogicentity = callpatchlogicrepo.save(callpatchlogicentity);

								response = "granttype='LOGIN';secondbparty='" + ani + "'";
							} else {
								System.out.println("ANI Not Found in call patch");
							}
						}
						msgsender.receiveData(ani, loginmsgid);
					} else if (granttype != null && granttype.equalsIgnoreCase("LOGOUT")) {
						grantlistentity.setAuthorized(voicechatentity1.getAni());
						grantlistentity.setAuthorizedChatId(voicechatentity1.getVoiceChatId());
						grantlistentity.setAuthorizer(voicechatentity1.getAni());
						grantlistentity.setAuthorizerChatId(voicechatentity1.getVoiceChatId());
						grantlistentity.setGrantType(granttype.toUpperCase());

						grantlistentity = grantlistrepo.save(grantlistentity);

						callpatchlogicentity = callpatchlogicrepo.findByAni(ani);
						if (callpatchlogicentity != null) {
							callpatchlogicentity.setLoginStatus("LOGOUT");
							callpatchlogicentity = callpatchlogicrepo.save(callpatchlogicentity);

							profileentity = profilerepo.findByAni(ani);
							if (profileentity != null) {
								profileentity.setIsActive("0");
								profilerepo.save(profileentity);
							}

							response = "granttype='LOGOUT';secondbparty='" + ani + "'";

						} else {
							response = "ANI Not Found in call patch";
						}

						msgsender.receiveData(ani, logoutmsgid);

					} else if (granttype != null && granttype.equalsIgnoreCase("BUSY")) {
						grantlistentity = new GrantListEntity();
						grantlistentity.setAuthorized(voicechatentity1.getAni());
						grantlistentity.setAuthorizedChatId(voicechatentity1.getVoiceChatId());
						grantlistentity.setAuthorizer(voicechatentity1.getAni());
						grantlistentity.setAuthorizerChatId(voicechatentity1.getVoiceChatId());
						grantlistentity.setGrantType(granttype.toUpperCase());

						grantlistrepo.save(grantlistentity);

						response = "granttype='busy';secondbparty='" + ani + "'";

					} else if (granttype != null && granttype.equalsIgnoreCase("FREEBUSY")) {
						grantlistrepo.deleteByAuthorizer(ani);

						response = "granttype='freebusy';secondbparty='" + ani + "'";
					}
				} else {
					response = "grantflag='FAILED';secondbparty='" + ani + "'";
				}
			} else {
				response = "ANI not found";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<String> getAllProfiles(String gender) {
		List<ProfileEntity> allactiveprofiles = new ArrayList<>();
		List<String> contactlist = new ArrayList<>();

		if (gender != null || !gender.equalsIgnoreCase("")) {
			allactiveprofiles = profilerepo.findTop50ByGenderAndIsActiveOrderByRownoDesc(gender, "1");
		} else {
			allactiveprofiles = profilerepo.findTop50ByGenderAndIsActiveOrderByRownoDesc("F", "1");
		}
		for (ProfileEntity profile : allactiveprofiles) {
			contactlist.add(profile.getAni());
		}
		System.out.println(contactlist.toString());
		return contactlist;
	}

	@Override
	public String saveIvrLogs(String ani, String cmpid, String planid, String action, String dnis,
			String callstartdatetime, String callenddatetime, String historybutton, String platform, String pulse,
			String duration, String incomingChannel) {

		String response = null;
		IvrLogsEntity ivrlogsentity = new IvrLogsEntity();
		VoiceChatEntity voicechatentity = null;
		String recordstatus = null;
		String circleid = null;
		String langid = null;
		voicechatentity = voicechatrepo.findByAni(ani);
		if (voicechatentity != null) {
			recordstatus = voicechatentity.getRecordStatus();
			circleid = voicechatentity.getCircleId();
			langid = voicechatentity.getLangId();
		} else {
			recordstatus = "0";
			circleid = "0";
			langid = "0";
		}

		ivrlogsentity.setAni(ani);
		ivrlogsentity.setStarttime(callstartdatetime);
		ivrlogsentity.setEndtime(callenddatetime);
		ivrlogsentity.setDateTime(new Date());
		ivrlogsentity.setDnis(dnis);
		ivrlogsentity.setDuration(null);
		try {
			ivrlogsentity.setHistoryButton(URLDecoder.decode(historybutton, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ivrlogsentity.setPulse(Integer.parseInt(pulse));
		ivrlogsentity.setDuration(Integer.parseInt(duration));
		ivrlogsentity.setRecordStatus(recordstatus);
		ivrlogsentity.setCircle(circleid);

		ivrlogsentity = ivrlogsrepo.save(ivrlogsentity);

		if (ivrlogsentity != null) {
			response = "cdr='Success';";
		}

		try {
			StringBuffer strLog = new StringBuffer();
			strLog.append(",");
			strLog.append(incomingChannel);
			strLog.append(",");
			strLog.append(callstartdatetime);
			strLog.append(",");
			strLog.append(callenddatetime);
			strLog.append(",");
			strLog.append(pulse);
			strLog.append(",");
			strLog.append(duration);
			strLog.append(",");
			strLog.append(ani);
			strLog.append(",");
			strLog.append(circleid);
			strLog.append(",");
			strLog.append(dnis);
			strLog.append(",");
			strLog.append(recordstatus);
			strLog.append(",");
			strLog.append(langid);
			strLog.append(",");
			strLog.append("vxml");
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
			Date CurrentDate = new Date();
			String fil_date = bartDateFormat.format(Long.valueOf(CurrentDate.getTime()));
			File hFile = null;
			String FileName = IVRLOGS + "clog_paid" + fil_date + ".txt";
			hFile = new File(FileName);
			if (hFile.exists()) {
				FileWriter hFileWriter = new FileWriter(hFile, true);
				hFileWriter.write(String.valueOf(strLog.toString()) + "\n");
				hFileWriter.close();
			} else {
				FileWriter hFileWriter = new FileWriter(hFile, true);
				hFileWriter.write(
						",line,CallStartDateTime,CallEndDateTime,Pulses,Duration,Ani,Circle,Dnis,check_status,Lang,platform\n");
				hFileWriter.write(String.valueOf(strLog.toString()) + "\n");
				hFileWriter.close();
			}
			hFile = null;
		} catch (Exception excep) {
			excep.printStackTrace();
		}

		return response;
	}

	@Override
	public String getCallpartyStatus(String ani, String cmpid, String planid, String action, String dnis,
			String calledparty_gender, String callparty_circleid, String chatid) {

		String response = null;
		String calledpartystatus = "";
		String calledpartygender = "N";
		String calledpartyani = "N";
		String calledpartycircleid = "N";

		VoiceChatEntity voicechatentity = voicechatrepo.findTop1ByVoiceChatId(chatid);

		if (voicechatentity == null) {
			calledpartystatus = "INVALID";
		} else {
			calledpartygender = voicechatentity.getGender();
			calledpartyani = voicechatentity.getAni();
			calledpartycircleid = voicechatentity.getCircleId();

			long count = grantlistrepo.countByAuthorizerChatIdAndGrantType(chatid, "LOGOUT");
			if (count > 0) {
				calledpartystatus = "LOGOUT";
			} else {
				long count1 = grantlistrepo.countByAuthorizerChatIdOrAuthorizedChatIdAndGrantType(chatid, chatid,
						"BUSY");
				if (count1 > 0) {
					calledpartystatus = "BUSY";
				} else {
					long count2 = grantlistrepo.countByAuthorizedChatIdAndGrantTypeAndAuthorizer(chatid, "BLOCK",
							calledpartyani);
					if (count2 > 0) {
						calledpartystatus = "BLOCK";
					} else {
						calledpartystatus = "IDLE";
					}
				}
			}
		}
		response = calledpartystatus + "_" + calledpartygender + "_" + calledpartyani + "_" + calledpartycircleid;
		return response;
	}

	@Override
	public String updateTransferRelease(String ani, String action, String flag, String in_value1, String in_value2,
			String in_value3, String in_value4, String in_value5, String in_value6, String in_value7, String in_value8,
			String method) {
		VoiceChatEntity voicechatentity = null;
		String outStr = null;
		voicechatentity = voicechatrepo.findByAni(ani);
		if (voicechatentity != null) {

			StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("UTILITY");
			storedProcedure.registerStoredProcedureParameter("inUserId", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("inFlag", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("inValue1", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("inValue2", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("inValue3", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("inValue4", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("inValue5", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("inValue6", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("inValue7", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("inValue8", String.class, ParameterMode.IN);

			// Register the OUT parameter
			storedProcedure.registerStoredProcedureParameter("OUT_STR", String.class, ParameterMode.OUT);

			storedProcedure.setParameter("inUserId", ani);
			storedProcedure.setParameter("inFlag", flag);
			storedProcedure.setParameter("inValue1", in_value1);
			storedProcedure.setParameter("inValue2", in_value2);
			storedProcedure.setParameter("inValue3", in_value3);
			storedProcedure.setParameter("inValue4", in_value4);
			storedProcedure.setParameter("inValue5", in_value5);
			storedProcedure.setParameter("inValue6", in_value6);
			storedProcedure.setParameter("inValue7", in_value7);
			storedProcedure.setParameter("inValue8", in_value8);

			storedProcedure.execute();

			// Retrieve the output parameter
			outStr = (String) storedProcedure.getOutputParameterValue("OUT_STR");

			if (outStr.contains("release") || outStr.contains("RELEASE")) {
				msgsender.receiveData(ani, voicechatentity.getVoiceChatId(), releasemsgid);
			} else if (outStr.contains("transfer") || outStr.contains("TRANSFER")) {
				msgsender.receiveData(ani, voicechatentity.getVoiceChatId(), transfermsgid);
			}
		} else {
			outStr = "ANI not found";
		}
		return outStr;

	}

	@Override
	public String updateUnSub(String ani, String cmpid, String planid, String usertype, String action, String dnis,
			String circleid) {
		String response = null;
		System.out.println("ANI  " + ani);
		VoiceChatEntity voicechatentity = null;
		VoiceChatUnsubEntity voicechatunsub = null;
		ProfileEntity profileentity = null;
		ProfileLogEntity profilelogentity = null;
		CallPatchLogicEntity callpatchlogic = null;
		CallPatchLogicLogEntity callpatchlogiclog = null;

		try {
			voicechatentity = voicechatrepo.findByAni(ani);
			if (voicechatentity != null) {
				voicechatunsub = new VoiceChatUnsubEntity();
				voicechatunsub.setAni(ani);
				voicechatunsub.setActiveLevel(action);
//					voicechatunsub.setActivityName(voicechatentity.get);
				voicechatunsub.setAge(voicechatentity.getAge());
				voicechatunsub.setCampaignId(voicechatentity.getCmpid());
				voicechatunsub.setCircleId(voicechatentity.getCircleId());
				voicechatunsub.setDefaultAmount(voicechatentity.getDefaultAmount());
				voicechatunsub.setDnis(dnis);
				voicechatunsub.setGender(voicechatentity.getGender());
//					voicechatunsub.setGenderDateTime(voicechatentity.getGe);
//					voicechatunsub.setGenderMode(response);
				voicechatunsub.setGenderUpdateDate(voicechatentity.getGenderUpdateDate());
				voicechatunsub.setGenderUpdateMode(voicechatentity.getGenderMode());
				voicechatunsub.setLangId(voicechatentity.getLangId());
				voicechatunsub.setLastBilledDate(voicechatentity.getLastBilledDate());
				voicechatunsub.setMact(voicechatentity.getMact());
				voicechatunsub.setMdact(voicechatentity.getMdact());
				voicechatunsub.setNextBilledDate(voicechatentity.getNextBilledDate());
				voicechatunsub.setOperatorId(voicechatentity.getOperatorId());
				voicechatunsub.setPlanId(voicechatentity.getPlanId());
				voicechatunsub.setRecordProfile(voicechatentity.getRecordProfile());
				voicechatunsub.setRecordStatus(voicechatentity.getRecordStatus());
				voicechatunsub.setRenPack(voicechatentity.getRenPack());
				voicechatunsub.setSubAmount(voicechatentity.getSubAmount());
				voicechatunsub.setSubDateTime(voicechatentity.getSubDateTime());
//					voicechatunsub.setSubscriberUid(voicechatentity.getSu);
				voicechatunsub.setSubscriptionId(voicechatentity.getSubscriptionId());
				voicechatunsub.setUnsubDateTime(new Date());
				voicechatunsub.setUnsubDnis(dnis);
				voicechatunsub.setUserType(usertype);
				voicechatunsub.setVendorId(voicechatentity.getVendorid());
				voicechatunsub.setVoiceChatId(voicechatentity.getVoiceChatId());
				voicechatunsub.setUnsubmode(usertype);

				voicechatunsubrepo.save(voicechatunsub);
				System.out.println("saved into voicechat unsub");

				if (voicechatrepo.deleteByAni(ani) > 0) {
					System.out.println(" and deleted from voicechat");
				} else {
					System.out.println("Not deleted");
				}

				profileentity = profilerepo.findByAni(ani);
				if (profileentity != null) {
					profileentity.setIsActive("0");
					profilerepo.save(profileentity);

					System.out.println("saved into profile log");

					if (profilerepo.deleteByAni(ani) > 0) {
						System.out.println(" deleted from profile");
					}

				}

				callpatchlogic = callpatchlogicrepo.findByAni(ani);
				if (callpatchlogic != null) {
					callpatchlogiclog = new CallPatchLogicLogEntity();
					callpatchlogiclog.setAni(ani);
					callpatchlogiclog.setAParty(callpatchlogic.getAParty());
					callpatchlogiclog.setChatId(callpatchlogic.getChatId());
					callpatchlogiclog.setCircleId(circleid);
					callpatchlogiclog.setCounts(callpatchlogic.getCounts());
					callpatchlogiclog.setCreatedOn(callpatchlogic.getCreatedOn());
					callpatchlogiclog.setFailedCalls(callpatchlogic.getFailedCalls());
					callpatchlogiclog.setGender(callpatchlogic.getGender());
					callpatchlogiclog.setLastCallTime(callpatchlogic.getLastCallTime());
					callpatchlogiclog.setLastFailedTime(callpatchlogic.getLastFailedTime());
					callpatchlogiclog.setLastSuccessTime(callpatchlogic.getLastSuccessTime());
					callpatchlogiclog.setLine(callpatchlogic.getLine());
					callpatchlogiclog.setLoginCounter(callpatchlogic.getLoginCounter());
					callpatchlogiclog.setLogDateTime(new Date());
					callpatchlogiclog.setLoginStatus(callpatchlogic.getLoginStatus());
					callpatchlogiclog.setPriority(callpatchlogic.getPriority());
					callpatchlogiclog.setRegion(callpatchlogic.getRegion());
					callpatchlogiclog.setState(callpatchlogic.getState());
					callpatchlogiclog.setSuccessCalls(callpatchlogic.getSuccessCalls());
					callpatchlogiclog.setUserIdentity(callpatchlogic.getUserIdentity());

					callpatchlogiclogrepo.save(callpatchlogiclog);
					System.out.println("saved into callpatch logs ");
					if (callpatchlogicrepo.deleteByAni(ani) > 0) {
						System.out.println("and deleted from call patch logic table");
					}

				}
				response = "SUCCESS";
			} else {
				response = "NOT_SUBSCRIBE";
			}
			msgsender.receiveData(ani, unsubmsgid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public String getHobbyInterest(String ani, String cmpid, String planid, String action, String dnis,
			String userprofiletypeid, String userprofilevalues) {
		String response = null;
		System.out.println("ANI  " + ani);
		VoiceChatEntity voicechatentity = null;

		try {
			voicechatentity = voicechatrepo.findByAni(ani);
			if (voicechatentity != null) {
				if (userprofiletypeid == "1") {
					response = "interest=" + userprofilevalues;
				} else if (userprofiletypeid == "2") {
					response = "hobby=" + userprofilevalues;
				} else {
					response = "recordstatus='" + voicechatentity.getRecordStatus() + "';langid='"
							+ voicechatentity.getLangId() + "';circleid='" + voicechatentity.getCircleId()
							+ "';voicechatid='" + voicechatentity.getVoiceChatId() + "';userType='"
							+ voicechatentity.getUserType() + "';gender='" + voicechatentity.getGender() + "';age='"
							+ voicechatentity.getAge() + "';recordprofile='" + voicechatentity.getAge()
							+ "';granttype='LOGIN';";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String saveRecordFile(String ani, String filename) {
		String response = null;
		String record = null;
		LocalDateTime currentDateTime = LocalDateTime.now();
		ProfileEntity profileentity = null;
		VoiceChatEntity voicechatentity = null;

		try {
			voicechatentity = voicechatrepo.findByAni(ani);
			if (voicechatentity != null) {
				int len = countrycode.length();
				ani = ani.trim();
				if (ani.startsWith("+"))
					ani = ani.substring(1);
				if (ani.startsWith("0"))
					ani = ani.substring(1);
				if (ani.substring(0, len).equals(countrycode))
					ani = ani.substring(len);

				String fn = ani;
				String dtFile = String.valueOf(systemrecordPath) + "/" + fn + ".ulaw";
				String srFile = systemrecordPathcopy + filename + ".ulaw";
				System.out.println(String.valueOf(filename) + "--" + fn);
				File srfn = new File(srFile);
				long filesize = srfn.length();
				System.out.println("File Size " + filesize);
				filesize /= 1024L;
				System.out.println("File Size after divide " + filesize);
				if (filesize > 10L) {
					copyfile(srFile, dtFile);
					System.out.println("going to save in tbl_profile");

					ProfileEntity profile = profilerepo.findByAni(ani);
					if (profile != null) {

						profile.setDatetime(new Date());

						profilerepo.save(profile);
					} else {
						profileentity = new ProfileEntity();
						profileentity.setAni(ani);
						profileentity.setCircleid(voicechatentity.getCircleId());
						profileentity.setDatetime(new Date());
						profileentity.setGender(voicechatentity.getGender());
						profileentity.setIsActive("1");
						profileentity.setLangid(voicechatentity.getLangId());
						profileentity.setRecordProfile(voicechatentity.getRecordProfile());

						profilerepo.save(profileentity);

						System.out.println("saved into profile log");

					}
				} else {
					String sessionid1 = "0";
					System.out.println("I am in else after finally");
					System.out.print("record ='0.ulaw';sessionid='" + sessionid1 + "';");
				}

				int year = currentDateTime.getYear();
				int month = currentDateTime.getMonthValue();
				int day = currentDateTime.getDayOfMonth();
				int hour = currentDateTime.getHour();
				int minute = currentDateTime.getMinute();
				int second = currentDateTime.getSecond();
				record = ani + year + month + day + hour + minute + second + ".ulaw";

				response = "record='" + record + "';sessionid='0'";
			} else {
				response = "Not in voicechat";
			}

			// Extract and print year, month, day, hours, minutes, and seconds

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public void copyfile(String srFile, String dtFile) {
		try {
			System.out.println("srFile :" + srFile);
			System.out.println("dtFile :" + dtFile);
			File f1 = new File(srFile);
			File f2 = new File(dtFile);
			InputStream in = new FileInputStream(f1);
			OutputStream out = new FileOutputStream(f2);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0)
				out.write(buf, 0, len);
			in.close();
			out.close();
		} catch (FileNotFoundException ex) {
			System.out.println(String.valueOf(ex.getMessage()) + " in the specified directory.");
		} catch (IOException e) {
			System.out.println("Error" + e.getMessage());
		}
	}

	@Override
	public String chatLogs(String ani, String dnis, String chatid, String patchedani, String patchedchatid,
			String duration1, String callstatus, String calltime, String callendtime, String cmpid, String planid,
			String serviceSelect, String circleid, String gender, String calledparty_gender,
			String calledparty_circleid) {
		String response = null;
		try {
			if (callstatus.equalsIgnoreCase("SUCCESS")) {
				String[] callDate = getCallDate(calltime, callendtime);
				System.out.println(ani + " starttime :" + callDate[0] + " endtime :" + callDate[1] + " duration :"
						+ duration1 + " Bparty :" + patchedani + " Bchatid :" + patchedchatid + " ServiceSelect :"
						+ serviceSelect + " callstatus :" + callstatus);
				int pulse = getPulse(duration1);
				boolean logflag = true;
				DBLogging(ani, dnis, chatid, patchedani, patchedchatid, duration1, callstatus, calltime, callendtime,
						cmpid, planid, serviceSelect, circleid, gender, calledparty_gender, calledparty_circleid,
						callDate, pulse);

				System.out.println("CHAT CASE SUCCESS DB $$$$$$$$$$$$$$$$$ " + duration1);
				FileLogging(ani, dnis, chatid, patchedani, patchedchatid, duration1, callstatus, calltime, callendtime,
						cmpid, planid, serviceSelect, circleid, gender, calledparty_gender, calledparty_circleid,
						callDate, pulse);
				System.out.println("CHAT CASE SUCCESS FILE $$$$$$$$$$$$$$$$$$$" + duration1);
				System.out.println("CHAT CASE SUCCESS FILE $$$$$$$$$$$$$$$$$$$" + duration1);

				response = "Calledstatus='Success';";
			} else {
				response = "Calledstatus='Fail';";
			}
		} catch (Exception excep) {

			excep.printStackTrace();
		}

		return response;
	}

	public String[] getCallDate(String calltime, String callendtime) {
		String[] callDate = new String[2];
		try {

			String[] arrchat = (String[]) null;
			String[] arrrchat = (String[]) null;
			arrchat = calltime.split("_");
			arrrchat = callendtime.split("_");
			String[] sdate = (String[]) null;
			String[] sdate1 = (String[]) null;
			String[] sdatetime = (String[]) null;
			String[] sdatetime1 = (String[]) null;
			String da1 = "";
			String da = "";
			String callschattime = "";
			String callechattime = "";
			sdate = arrchat[0].split("-");
			if (sdate[0].length() == 1)
				sdate[0] = "0" + sdate[0];
			if (sdate[1].length() == 1)
				sdate[1] = "0" + sdate[1];
			if (sdate[2].length() == 1)
				sdate[2] = "0" + sdate[2];
			da = String.valueOf(sdate[2]) + "-" + sdate[1] + "-" + sdate[0];
			sdatetime = arrchat[1].split(":");
			String datime = "";
			if (sdatetime[0].length() == 1)
				sdatetime[0] = "0" + sdatetime[0];
			if (sdatetime[1].length() == 1)
				sdatetime[1] = "0" + sdatetime[1];
			if (sdatetime[2].length() == 1)
				sdatetime[2] = "0" + sdatetime[2];
			datime = String.valueOf(sdatetime[0]) + ":" + sdatetime[1] + ":" + sdatetime[2];
			callschattime = String.valueOf(da) + " " + datime;
			callDate[0] = callschattime;
			sdate1 = arrrchat[0].split("-");
			if (sdate1[0].length() == 1)
				sdate1[0] = "0" + sdate1[0];
			if (sdate1[1].length() == 1)
				sdate1[1] = "0" + sdate1[1];
			if (sdate1[2].length() == 1)
				sdate1[2] = "0" + sdate1[2];
			da1 = String.valueOf(sdate1[2]) + "-" + sdate1[1] + "-" + sdate1[0];
			sdatetime1 = arrrchat[1].split(":");
			String datime1 = "";
			if (sdatetime1[0].length() == 1)
				sdatetime1[0] = "0" + sdatetime1[0];
			if (sdatetime1[1].length() == 1)
				sdatetime1[1] = "0" + sdatetime1[1];
			if (sdatetime1[2].length() == 1)
				sdatetime1[2] = "0" + sdatetime1[2];
			datime1 = String.valueOf(sdatetime1[0]) + ":" + sdatetime1[1] + ":" + sdatetime1[2];
			callechattime = String.valueOf(da1) + " " + datime1;
			callDate[1] = callechattime;
		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return callDate;
	}

	private int getPulse(String duration) {
		int dur = Integer.parseInt(duration);
		int r = dur % 60;
		int pulses = 0;
		pulses = dur / 60;
		if (r > 0)
			pulses++;
		return pulses;
	}

	private void DBLogging(String ani, String dnis, String chatid, String patchedani, String patchedchatid,
			String duration1, String callstatus, String calltime, String callendtime, String cmpid, String planid,
			String serviceSelect, String circleid, String gender, String calledparty_gender,
			String calledparty_circleid, String[] callDate, int pulse) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			VoiceChatEntity voicechatentity = voicechatrepo.findByAni(ani);
			ChatLogEntity chatlogentity = null;
			if (voicechatentity != null) {
				CallPatchLogicEntity callpatchlogic = callpatchlogicrepo.findByAni(ani);
				chatlogentity = new ChatLogEntity();
				chatlogentity.setAchatid(voicechatentity.getVoiceChatId());
				chatlogentity.setACircle(voicechatentity.getCircleId());
				chatlogentity.setAparty(ani);
				chatlogentity.setAparty_Gender(voicechatentity.getGender());
				chatlogentity.setBchatid(patchedchatid);
				chatlogentity.setBparty(patchedani);
				chatlogentity.setBparty_Gender(calledparty_gender);
				chatlogentity.setCallStartDateTime(dateFormat.parse(callDate[0]));
				chatlogentity.setCallEndDateTime(dateFormat.parse(callDate[1]));
				chatlogentity.setCallstatus(callstatus);
				chatlogentity.setDnis(dnis);
				chatlogentity.setDuration(Integer.parseInt(duration1));
				chatlogentity.setLine(0);
				chatlogentity.setPlatform("vxml");
				chatlogentity.setPulses(pulse);
				chatlogentity.setRegion(callpatchlogic.getRegion());
				chatlogentity.setServiceSelect(serviceSelect);

				chatlogrepo.save(chatlogentity);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void FileLogging(String ani, String dnis, String chatid, String patchedani, String patchedchatid,
			String duration1, String callstatus, String calltime, String callendtime, String cmpid, String planid,
			String serviceSelect, String circleid, String gender, String calledparty_gender,
			String calledparty_circleid, String[] callDate, int pulse) {
		try {
			VoiceChatEntity voicechatentity = voicechatrepo.findByAni(ani);
			ChatLogEntity chatlogentity = null;
			if (voicechatentity != null) {
				CallPatchLogicEntity callpatchlogic = callpatchlogicrepo.findByAni(ani);
				int durint = Integer.valueOf(duration1).intValue();
				System.out.println("duration is ::" + durint);
				SimpleDateFormat artDateFormat = new SimpleDateFormat("yyyyMMdd");
				Date CurrentDates = new Date();
				String fil_dates = artDateFormat.format(Long.valueOf(CurrentDates.getTime()));
				String ChatFileName = null;
				if (callstatus.equalsIgnoreCase("SUCCESS")) {
					ChatFileName = String.valueOf(CHATLOGS + "CHAT" + fil_dates + ".txt");
				} else {
					ChatFileName = String.valueOf(CHATLOGS + "FAIL" + fil_dates + ".txt");
				}
				StringBuffer strLog = new StringBuffer();
				strLog.append("0");
				strLog.append(",");
				strLog.append(ani);
				strLog.append(",");
				strLog.append(voicechatentity.getVoiceChatId());
				strLog.append(",");
				strLog.append(voicechatentity.getCircleId());
				strLog.append(",");
				strLog.append(duration1);
				strLog.append(",");
				strLog.append(patchedani);
				strLog.append(",");
				strLog.append(patchedchatid);
				strLog.append(",");
				strLog.append(callDate[0]);
				strLog.append(",");
				strLog.append(callDate[1]);
				strLog.append(",");
				strLog.append(serviceSelect);
				strLog.append(",");
				strLog.append(dnis);
				strLog.append(",");
				strLog.append(callstatus);
				strLog.append(",");
				strLog.append("vxml");
				strLog.append(",");
				strLog.append(voicechatentity.getGender());
				strLog.append(",");
				strLog.append(calledparty_gender);
				strLog.append(",");
				strLog.append(pulse);
				strLog.append(",");
				strLog.append(callpatchlogic.getRegion());
				strLog.append(",");
				strLog.append(callpatchlogic.getPriority());
				File hfailFile = new File(ChatFileName);
				FileWriter hFailFileWriter = null;
				if (hfailFile.exists()) {
					hFailFileWriter = new FileWriter(hfailFile, true);
				} else {
					hFailFileWriter = new FileWriter(hfailFile, true);
					hFailFileWriter.write(
							"line,Aparty,Achatid,ACircle,Duration,Bparty,Bchatid,CallStartDateTime,CallEndDateTime,ServiceSelect,Dnis,callstatus,platform,AGender,BGender,pulse,region,priority\n");
				}
				hFailFileWriter.write(String.valueOf(strLog.toString()) + "\n");
				hFailFileWriter.close();
				if (durint > 4) {
					callstatus = "SUCCESS";
				} else {

					System.out.println("callstatus is :" + callstatus);
				}
				System.out.println("final callstatus is :" + callstatus);

			} else {

			}
		} catch (Exception excep) {
			excep.printStackTrace();
		}
	}

}
