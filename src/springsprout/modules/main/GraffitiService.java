package springsprout.modules.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import springsprout.domain.Graffiti;
import springsprout.modules.main.support.GraffitiDTO;
import springsprout.service.security.SecurityService;

@Service
@Transactional
public class GraffitiService {
	
	private static int GRAFFITI_LIMIT_COUNT = 100;
	
	@Autowired GraffitiRepository graffitiRepository;
	@Autowired SecurityService securityService;
	
	SimpleDateFormat writeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat formatedDateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");

    @PreAuthorize("hasRole('ROLE_MEMBER')")
	public boolean write(String contents) {
        while (graffitiRepository.getTotalRowCount() >= GRAFFITI_LIMIT_COUNT) {
            graffitiRepository.deleteFirstGraffiti();
        }
		Graffiti graffiti = new Graffiti(contents, securityService.getCurrentMember());
		graffitiRepository.add(graffiti);
		return true;
	}

    /**
	 * 낙서 삭제
	 * @param graffitiId 낙서ID
	 */
	public void remove(int graffitiId) {
		graffitiRepository.deleteById(graffitiId);
	}	
	
	/**
	 * 낙서장 목록을 반환한다.
	 * 검색기준 일시를 받아서 검색하되 만약 검색기준 일시가 비어있다면 전체 목록을 반환한다.
	 * @param writeDate 검색기준일시
	 * @return
	 */
	public List<GraffitiDTO> getGraffitiList(String writeDate) {
		if(StringUtils.hasText(writeDate)){
			try{
				return formatWrittenDate(graffitiRepository.getByWriteDate(writeDateFormat.parse(writeDate)));
			}
			catch (ParseException pe) {
				return null;
			}
		}
		
		return formatWrittenDate(graffitiRepository.getAllContents());
	}
	
	public List<GraffitiDTO> getRecentGraffitiList(int lastGraffitiID) {
		return formatWrittenDate(graffitiRepository.getListByID( lastGraffitiID));
	}

    public List<GraffitiDTO> formatWrittenDate(List<GraffitiDTO> graffitiDTOList) {
        for(GraffitiDTO graffitiDTO : graffitiDTOList){
            graffitiDTO.setFormatedDate(formatedDateFormat.format(graffitiDTO.getWrittenDate()));
        }
        return graffitiDTOList;
    }
}