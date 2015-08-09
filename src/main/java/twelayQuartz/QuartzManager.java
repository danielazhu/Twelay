package twelayQuartz;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzManager {

	private Scheduler sched;
	private int jobCounter;
	
	public QuartzManager() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		this.sched = sf.getScheduler();
		jobCounter = 0;
	}
	
	private JobDetail createJob(String message, String recipient) {
		
		JobDetail job = JobBuilder.newJob(TwitterMessageJob.class).withIdentity("job" + jobCounter, "twitter_msg").build();
		
		JobDataMap map = job.getJobDataMap();
		map.put("message", message);
		map.put("recipient", recipient);
		
		return job;
	}
	
	public void createSimpleJob(String message, String recipient) throws SchedulerException {
		
		int interval = -1;
		int repeat = -1;
		/*
		 * TODO: get input variables and calculate interval and repeat
		 */
		
		JobDetail job = createJob(message, recipient);
		
		ScheduleBuilder schedule = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(interval).withRepeatCount(repeat);
		Trigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger" + jobCounter, "twitter_msg").withSchedule(schedule).build();
				
		sched.scheduleJob(job, trigger);
	}
	
	public void createCronJob() {
		
	}
}
