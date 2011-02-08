package springsprout.common.test;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.io.InputStream;

public class DBUnitSupport {
	
	enum DataType {EXCEL, FLATXML}

	@Autowired
	private DataSource dataSource;
	
	protected void cleanInsertXmlData(String fileSource) throws Exception {
		insertData(fileSource, DataType.FLATXML, DatabaseOperation.CLEAN_INSERT);
	}	
	protected void cleanInsertXlsData(String fileSource) throws Exception {
		insertData(fileSource, DataType.EXCEL, DatabaseOperation.CLEAN_INSERT);
	}
	
	private void insertData(String fileSource, DataType type, DatabaseOperation operation) throws Exception {
		InputStream sourceStream = new ClassPathResource(fileSource, getClass()).getInputStream();
		
		IDataSet dataset = null;
		if (type == DataType.EXCEL) {
			dataset = new XlsDataSet(sourceStream);
		}
		else if (type == DataType.FLATXML) {
			dataset = new FlatXmlDataSet(sourceStream);
		}
		
		operation.execute(
				new DatabaseConnection(DataSourceUtils.getConnection(dataSource)), dataset);
	}
	
	protected void insertXmlData(String fileSource) throws Exception {
		insertData(fileSource, DataType.FLATXML, DatabaseOperation.INSERT);
	}
	
	protected void insertXlsData(String fileSource) throws Exception {
		insertData(fileSource, DataType.EXCEL, DatabaseOperation.INSERT);
	}

}
