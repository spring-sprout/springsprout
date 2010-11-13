package springsprout.common.util;

import org.hibernate.validator.constraints.impl.EmailValidator;
import springsprout.domain.Member;

import java.util.*;

public class EmailExtractUtil {

	public static Collection<String> extractMessageAllowedEmailCollectionFrom(
			Collection<Member> members) {
		List<String> emails = new ArrayList<String>();
		Iterator<Member> memberIterator = members.iterator();

        while(memberIterator.hasNext()){
			Member member = memberIterator.next();
			if(member.getIsAllowedGoogleTalk()){
                addIfValid(emails, member.getEmail());
			}
		}
		return emails;
	}

    public static String[] extractMailAllowedEmailFrom(
			Collection<Member> members) {
		List<String> recievers = new ArrayList<String>();
		Iterator<Member> memberIterator = members.iterator();
		
		while(memberIterator.hasNext()){
			Member member = memberIterator.next();
			if(member.getIsAllowedEmail()){
				addIfValid(recievers, member.getEmail());
			}
		}
		return recievers.toArray(new String[recievers.size()]);
	}

    public static Collection<String> extractEmailCollectionFrom(Member member) {
        if (isNotValid(member.getEmail())) return null;
        return Arrays.asList(new String[]{member.getEmail()});
	}

	public static String[] extractEmailArrayForm(Member member) {
        if (isNotValid(member.getEmail())) return null;
        String[] recievers = new String[1];
		recievers[0] = member.getEmail();
		return recievers;
	}

    private static void addIfValid(List<String> emails, String email) {
        if (isValid(email))
            emails.add(email);
    }

    private static boolean isValid(String email) {
    	return new EmailValidator().isValid(email, null);
    }
    
    private static boolean isNotValid(String email) {
    	return !isValid(email);
    }
    
    

}
