package org.ernest.applications.bt.db.manager.notices.ms.services;

import org.ernest.applications.bt.db.manager.notices.ct.CreateNoticeInput;
import org.ernest.applications.bt.db.manager.notices.ct.entries.Notice;
import org.ernest.applications.bt.db.manager.notices.ct.exceptions.CreateNoticeException;
import org.ernest.applications.bt.db.manager.notices.ct.exceptions.DeleteNoticeException;
import org.ernest.applications.bt.db.manager.notices.ct.exceptions.RetrieveNoticeException;

public interface CrudService {

	Notice retrieve(String noticeId) throws RetrieveNoticeException;
	String create(CreateNoticeInput input) throws CreateNoticeException;
	void delete(String noticeId) throws DeleteNoticeException;
}
