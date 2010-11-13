package springsprout.modules.study.support;


public enum StudyAttrList {
	TITLE_ADD("title", "스터디 추가 "), TITLE_UPDATE("title", "스터디 수정"),
	BACKURL("backUrl"), ISUPDATE("isUpdate"), MINITAB_ACTIVE("minitab_active"), 
	MINITAB_PAST("minitab_past"), SESSION_FLASH_MSG("SESSION_FLASH_MSG"),
	IS_ALREADY_JOIN_MEMBER("isAlreadyJoinMember"), IS_MANAGER_OR_ADMIN("isManagerOrAdmin"),
	LIST("list"), MEMBER_COUNT("memberCount");
	
	private String name;
	private String value;
	
	StudyAttrList(String name) {
		this.name = name;
	}
	
	StudyAttrList(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String key() {
		return name;
	}
	
	public String value() {
		return value;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
