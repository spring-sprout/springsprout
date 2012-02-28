package springsprout.modules.main;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import springsprout.domain.Graffiti;
import springsprout.domain.Member;
import springsprout.modules.main.support.GraffitiDTO;
import springsprout.service.security.SecurityService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GraffitiServiceTest {
    
	GraffitiService graffitiService;
	@Mock SecurityService securityService;
	@Mock GraffitiRepository graffitiRepository;
	
	Calendar cal;
    List<GraffitiDTO> gdtoList;
	
	@Before
	public void setUp() throws Exception {
		graffitiService = new GraffitiService();
		graffitiService.securityService = securityService;
		graffitiService.graffitiRepository = graffitiRepository;
		
		cal = Calendar.getInstance();
        gdtoList = new ArrayList<GraffitiDTO>();
	}
	
	/**
	 * 낙서 쓰기 테스트
	 * 낙서의 총 개수는 100개만 유지되어야 한다.
	 * 비지니스 로직이 올바르게 수행되는지 Mock 을 이용해서 테스트를 해보자...
	 * 
	 * 비지니스 로직은 다음과 같이 수행되어야 한다.
	 * 
	 * 1. graffitiService.write 메소드가 호출
	 * 2. graffitiRepository.getTotalRowCount(낙서 갯수 조회) 를 통해서 낙서의 갯수를 파악
	 * 3. 낙서의 갯수가 100개 이상이면 graffitiRepository.deleteFirstGraffiti(가장 오래된 낙서 1개 삭제) 호출
	 * 4. 낙서의 갯수가 100개 이하가 될때까지 2, 3 번 프로세스를 반복
	 * 5. 입력받은 낙서를 db 에 저장한다.
	 * 
	 * @throws Exception
	 */
	@Test public void addGraffiti() throws Exception {
		Member member = new Member();
		member.setId(1);
		member.setName("tester");
		member.setAvatar("avatar::tester");
		
		when(securityService.getCurrentMember()).thenReturn(member);
		
		// 낙서의 갯수를 반환하는 메소드를 반환값을 직접 구현한다. 한번 반환할때마다 1씩 줄어든다.
		// 현재 테스트 db 에 총 낙서의 갯수가 103개가 있다는 가정을 세워둔다.
		when(graffitiRepository.getTotalRowCount()).thenAnswer(new Answer<Integer>(){
			int graffitiCount = 103;
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				return graffitiCount--;
			}
		});
		
		graffitiService.write("contents");
		
		// 낙서 갯수를 조회하는 메소드가 5번 호출되었는가?
		verify(graffitiRepository, times(5)).getTotalRowCount();
		// 낙서를 삭제하는 메소드가 4번 호출되었는가?
		verify(graffitiRepository, times(4)).deleteFirstGraffiti();
		// 낙서를 쓰는 메소드가 1번 호출되었는가?
		verify(graffitiRepository, times(1)).add((Graffiti) Mockito.any());
	}    

    @Test public void writtenDateFormatTest(){

        GraffitiDTO gdto = new GraffitiDTO();
        cal.set(2009,10,25,01,24,55);
        gdto.setWrittenDate(cal.getTime());

        GraffitiDTO gdto1 = new GraffitiDTO();
        cal.set(2009,10,25,23,24,55);
        gdto1.setWrittenDate(cal.getTime());

        gdtoList.add(gdto);
        gdtoList.add(gdto1);
        gdtoList = graffitiService.formatWrittenDate(gdtoList);
        assertThat(gdtoList.get(0).getFormattedDate() , is("2009.11.25 01:24:55"));
        assertThat(gdtoList.get(1).getFormattedDate() , is("2009.11.25 23:24:55"));
	}

}
