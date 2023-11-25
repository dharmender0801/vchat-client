package com.altruist.utils;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.altruist.entity.SmsEntity;
import com.altruist.repo.SmsRepo;

@Component
public class MessageSender {
	
	@Value("${sub.msg.send}")
	String submsgflag;
	@Value("${alreadysub.msg.send}")
	String  alreadysubflag;
	@Value("${unsub.msg.send}")
	String unsubflag;
	@Value("${release.msg.send}")
	String releaseflag;
	@Value("${login.msg.send}")
	String loginflag;
	@Value("${logout.msg.send}")
	String logoutflag;
	@Value("${failurebparty.msg.send}")
	String failurebpartyflag;
	@Value("${freetrail.msg.send}")
	String freetrailflag;
	@Value("${block.msg.send}")
	String blockmsgflag;
	@Value("${unblock.msg.send}")
	String unblockmsgflag;
	@Value("${transfer.msg.send}")
	String transfermsgflag;
	
	@Value("${sub.msg}")
	String submsg;
	@Value("${unsub.msg}")
	String unsubmsg;
	@Value("${alreadysub.msg}")
	String alreadysubmsg;
	@Value("${release.msg}")
	String releasemsg;
	@Value("${login.msg}")
	String loginmsg;
	@Value("${logout.msg}")
	String logoutmsg;
	@Value("${failurebparty.msg}")
	String failurebpartymsg;
	@Value("${freetrail.msg}")
	String freetrailmsg;
	@Value("${block.msg}")
	String blockmsg;
	@Value("${unblock.msg}")
	String unblockmsg;
	@Value("${transfer.msg}")
	String transfermsg;
	
	@Autowired
	SmsRepo smsrepo;
	
	public void receiveData(String ani, String msgid)
	{
		if(msgid.equals("1"))
		{
			System.out.println("sub msg");
			if(submsgflag=="1")
			{
				System.out.println("Message to send");
				String sms = submsg;
				sms = sms.replace("{ani}", ani);
				sendMsg(sms,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
		else if(msgid.equals("2"))
		{
			System.out.println("Message Id "+msgid);
			System.out.println("Already sub flag "+alreadysubflag);
			String sms = alreadysubmsg;
			sms = sms.replace("{ani}", ani);
			if(alreadysubflag.equals("1"))
			{
				System.out.println("Message to send");
				sendMsg(sms,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
		else if(msgid.equals("3"))
		{
			if(unsubflag.equals("1"))
			{
				System.out.println("Message to send");
				sendMsg(unsubmsg,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
		else if(msgid.equals("4"))
		{
			if(releaseflag.equals("1"))
			{
				System.out.println("Message to send");
				sendMsg(releasemsg,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
		else if(msgid.equals("5"))
		{
			if(loginflag.equals("1"))
			{
				System.out.println("Message to send");
				sendMsg(loginmsg,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
		else if(msgid.equals("6"))
		{
			if(logoutflag.equals("1"))
			{
				System.out.println("Message to send");
				sendMsg(logoutmsg,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
		else if(msgid.equals("7"))
		{
			if(failurebpartyflag.equals("1"))
			{
				System.out.println("Message to send");
				sendMsg(failurebpartymsg,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
		else if(msgid.equals("8"))
		{
			if(freetrailflag.equals("1"))
			{
				System.out.println("Message to send");
				sendMsg(freetrailmsg,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
		else if(msgid.equals("9"))
		{
			if(blockmsgflag.equals("1"))
			{
				System.out.println("Message to send");
				sendMsg(blockmsg,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
		else if(msgid.equals("10"))
		{
			if(unblockmsgflag.equals("1"))
			{
				System.out.println("Message to send");
				sendMsg(unblockmsg,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
		else if(msgid.equals("11"))
		{
			if(transfermsgflag.equals("1"))
			{
				System.out.println("Message to send");
				sendMsg(transfermsg,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
	}



	public void sendMsg(String msg,String ani) {
		SmsEntity smsentity =new SmsEntity();
		LocalDateTime currentDateTime = LocalDateTime.now();
		int year = currentDateTime.getYear();
		int month = currentDateTime.getMonthValue();
		int day = currentDateTime.getDayOfMonth();
		int hour = currentDateTime.getHour();
		int minute = currentDateTime.getMinute();
		int second = currentDateTime.getSecond();
		
		String msgid= ""+ year + month + day + hour + minute + second; 
		smsentity.setAni(ani);
		smsentity.setDatetime(new Date());
		smsentity.setDnis("854");
		smsentity.setMsg(msg);
		smsentity.setMessageid(msgid);
		smsentity.setPriority(0);
		smsentity.setType("IVR");
		smsentity.setStatus("0");
		try {
			smsrepo.save(smsentity);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void receiveData(String ani, String voiceChatId, String msgid) {
		if(msgid.equals("1"))
		{
			System.out.println("sub msg");
			if(submsgflag=="1")
			{
				System.out.println("Message to send");
				String sms = submsg;
				sms = sms.replace("{chatid}", voiceChatId);
				sendMsg(sms,ani);
			}
			else
			{
				System.out.println("Msg not sent");
			}
		}
		
	}

}
