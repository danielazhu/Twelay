package twelayQuartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TwitterMessageJob implements Job {
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap map = context.getJobDetail().getJobDataMap();
		String message = (String) map.get("message");
		String recipient = (String) map.get("recipient");
		
		
		/*
		 * TODO: connect to twitter API
		 */
		
	}
}
