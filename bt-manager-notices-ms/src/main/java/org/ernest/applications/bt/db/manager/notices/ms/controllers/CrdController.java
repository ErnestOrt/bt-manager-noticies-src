package org.ernest.applications.bt.db.manager.notices.ms.controllers;

import org.ernest.applications.bt.db.manager.notices.ct.CreateNoticeInput;
import org.ernest.applications.bt.db.manager.notices.ct.entries.Notice;
import org.ernest.applications.bt.db.manager.notices.ct.exceptions.CreateNoticeException;
import org.ernest.applications.bt.db.manager.notices.ct.exceptions.DeleteNoticeException;
import org.ernest.applications.bt.db.manager.notices.ct.exceptions.RetrieveNoticeException;
import org.ernest.applications.bt.db.manager.notices.ms.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrdController {

	@Autowired
	CrudService crudService;
	
	@RequestMapping("/create")
	public String create(@RequestBody CreateNoticeInput input) throws CreateNoticeException {
		return crudService.create(input);
	}
	
	@RequestMapping(path = "/retrieve/{noticeId}", method = RequestMethod.GET)
	public Notice retrieve(@PathVariable("noticeId") String noticeId) throws RetrieveNoticeException {
		return crudService.retrieve(noticeId);
	}
	
	@RequestMapping(path = "/delete/{noticeId}", method = RequestMethod.GET)
	public void delete(@PathVariable("noticeId") String noticeId) throws DeleteNoticeException {
		crudService.delete(noticeId);
	}
}