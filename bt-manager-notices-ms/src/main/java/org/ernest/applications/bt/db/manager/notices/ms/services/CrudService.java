package org.ernest.applications.bt.db.manager.notices.ms.services;

import org.ernest.applications.bt.db.manager.notices.ct.exceptions.CreateNoticeException;
import org.ernest.applications.bt.db.manager.notices.ct.exceptions.DeleteNoticeException;
import org.ernest.applications.bt.db.manager.notices.ct.exceptions.RetrieveNoticeException;

public interface CrudService {

	String retrieve(String noticeId) throws RetrieveNoticeException;
	String create(String content) throws CreateNoticeException;
	void delete(String noticeId) throws DeleteNoticeException;
}
