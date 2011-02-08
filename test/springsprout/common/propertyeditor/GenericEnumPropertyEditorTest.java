package springsprout.common.propertyeditor;

import org.junit.Test;
import springsprout.common.enumeration.PersistentEnum;
import springsprout.common.enumeration.PersistentEnumUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class GenericEnumPropertyEditorTest {
	
	@Test
	public void create() throws Exception {
		SampleEnumPropertuEditor pe = new SampleEnumPropertuEditor();
		pe.setValue(PersistentEnumUtil.valueOf(SampleEnum.class, 10));
		assertThat(pe.getAsText(), is("10"));
		pe.setAsText("20");
		assertTrue(pe.getValue().equals(PersistentEnumUtil.valueOf(SampleEnum.class, 20)));
	}

	private class SampleEnumPropertuEditor extends GenericEnumPropertyEditor<SampleEnum>{}
	
	private enum SampleEnum implements PersistentEnum {
		A(10, "에이", 1), 
		B(20, "비", 2), 
		C(30, "씨", 3);
		
		private final Integer value;
		private final String descr;
		private final int order;
		
		private SampleEnum(Integer value, String descr, int order) {
			this.value = value;
			this.descr = descr;
			this.order = order;
		}
		
		public Integer getValue() {
			return value;
		}
		public String getDescr() {
			return descr;
		}
		public int getOrder(){
			return order;
		}
	}
	

}
