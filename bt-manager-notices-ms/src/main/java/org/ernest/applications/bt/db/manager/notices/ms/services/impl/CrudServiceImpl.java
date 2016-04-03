package org.ernest.applications.bt.db.manager.notices.ms.services.impl;

import java.util.Date;
import java.util.UUID;

import org.ernest.applications.bt.db.manager.notices.ct.CreateNoticeInput;
import org.ernest.applications.bt.db.manager.notices.ct.entries.Notice;
import org.ernest.applications.bt.db.manager.notices.ct.exceptions.CreateNoticeException;
import org.ernest.applications.bt.db.manager.notices.ct.exceptions.DeleteNoticeException;
import org.ernest.applications.bt.db.manager.notices.ct.exceptions.RetrieveNoticeException;
import org.ernest.applications.bt.db.manager.notices.ms.services.CrudService;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CrudServiceImpl implements CrudService{
	
	@Value("${db.name}")
	private String dbName;
	
	@Value("${db.host}")
	private String dbHost;

	@Override
	public String create(CreateNoticeInput input) throws CreateNoticeException {
		Notice notice = new Notice();
		notice.set_id(UUID.randomUUID().toString());
		notice.setTitle(input.getTitle());
		notice.setContent(input.getContent());
		notice.setDate(new Date());
		try{
			CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties());
			dbClient.save(notice);
			dbClient.shutdown();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new CreateNoticeException(e.getMessage());
		}
		return notice.get_id();
	}

	@Override
	public Notice retrieve(String noticeId) throws RetrieveNoticeException {
		
		try{
			CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties());
			Notice notice = dbClient.find(Notice.class, noticeId);
			dbClient.shutdown();
			
			return notice;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RetrieveNoticeException(e.getMessage());
		}
		
	}
	
	@Override
	public void delete(String noticeId) throws DeleteNoticeException {
		
		try{
			CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties());
			Notice notice = dbClient.find(Notice.class, noticeId);
			dbClient.remove(notice);
			dbClient.shutdown();

			
		}catch(Exception e){
			e.printStackTrace();
			throw new DeleteNoticeException(e.getMessage());
		}
		
	}

	private CouchDbProperties buildCouchDbProperties() {
		CouchDbProperties properties = new CouchDbProperties();
		properties.setDbName(dbName);
		properties.setHost(dbHost);
		properties.setPort(5984);
		properties.setCreateDbIfNotExist(true);
		properties.setProtocol("http");
		return properties;
	}
}
