package springsprout.common;

import org.springframework.mobile.device.site.CookieSitePreferenceRepository;
import org.springframework.mobile.device.site.SitePreferenceHandler;
import org.springframework.mobile.device.site.StandardSitePreferenceHandler;
import org.springframework.mobile.device.switcher.SiteSwitcherHandlerInterceptor;
import org.springframework.mobile.device.switcher.SiteUrlFactory;
import org.springframework.mobile.device.switcher.StandardSiteUrlFactory;

public class SpringSproutSiteSwitcher extends SiteSwitcherHandlerInterceptor{

	public SpringSproutSiteSwitcher(SiteUrlFactory normalSiteUrlFactory,
			SiteUrlFactory mobileSiteUrlFactory,
			SitePreferenceHandler sitePreferenceHandler) {
		super(normalSiteUrlFactory, mobileSiteUrlFactory, sitePreferenceHandler);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 봄싹용 SiteSwitcher. Spring Mobile에서 지원하지 않아서 /m 으로 보내는 Factory-Method 개발
	 * @param serverName
	 * @return
	 */
	public static SiteSwitcherHandlerInterceptor slashM(String serverName) {
		return new SiteSwitcherHandlerInterceptor(new StandardSiteUrlFactory(serverName), new StandardSiteUrlFactory(serverName + "/m"), new StandardSitePreferenceHandler(new CookieSitePreferenceRepository("." + serverName)));
	}
}
