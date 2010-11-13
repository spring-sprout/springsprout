package springsprout.common.conversion;

import org.springframework.binding.convert.converters.StringToDate;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.stereotype.Component;

import springsprout.common.util.DateUtils;

@Component("applicationConversionService")
public class ApplicationConversionService extends DefaultConversionService {

	@Override
	protected void addDefaultConverters() {
		super.addDefaultConverters();
		
		StringToDate dateConverter = new StringToDate();
		dateConverter.setPattern(DateUtils.yyyyMMdd_SLASH);
		addConverter("shortDate", dateConverter);
		
		StringToDate timeConverter = new StringToDate();
		timeConverter.setPattern(DateUtils.HHmm_COLON);
		addConverter("shortTime", timeConverter);
		
		StringToMember memberConverter = new StringToMember();
		addConverter("member", memberConverter);
	}

}
