package org.ernest.application.bt.db.manager.users.test;

import org.ernest.applications.bt.db.manager.notices.ct.CreateNoticeInput;
import org.ernest.applications.bt.db.manager.notices.ct.entries.Notice;
import org.ernest.applications.bt.db.manager.notices.ms.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest({"server.port=0"})
public class CrudTest {
	
	@Value("${local.server.port}")
	String port;
	
	@Test
	public void updateUserName(){
		CreateNoticeInput input = new CreateNoticeInput();
		input.setTitle("title");
		input.setContent("content");

		String id = new RestTemplate().postForObject("http://localhost:"+port+"/create", input, String.class);
		Assert.assertEquals(input.getContent(), new RestTemplate().getForObject("http://localhost:"+port+"/retrieve/"+id, Notice.class).getContent());
		new RestTemplate().getForObject("http://localhost:"+port+"/delete/"+id, String.class);	
	}
}