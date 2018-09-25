package com.azxx.demon.web.batch;

import com.azxx.demon.batch.DeviceCommand;
import com.azxx.demon.batch.HelloItemProcessor;
import com.azxx.demon.batch.HelloLineAggregator;
import com.azxx.demon.batch.HelloLineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Smile on 2018/9/25.
 */
@RestController
@RequestMapping("batchtest")
public class BatchController {

    private static Logger logger = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    @Qualifier("batchTxManager")
    PlatformTransactionManager transactionManager;

    @RequestMapping(value = "run",method = RequestMethod.GET)
    public void run(){

        // 创建reader
        FlatFileItemReader<DeviceCommand> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("data/batch-data.csv"));
        flatFileItemReader.setLineMapper(new HelloLineMapper());

        // 创建processor
        HelloItemProcessor helloItemProcessor = new HelloItemProcessor();

        // 创建writer
        FlatFileItemWriter<DeviceCommand> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new FileSystemResource("data/batch-data.csv"));
        flatFileItemWriter.setLineAggregator(new HelloLineAggregator());

        // 创建Step
        StepBuilderFactory stepBuilderFactory = new StepBuilderFactory(jobRepository, transactionManager);
        Step step = stepBuilderFactory.get("step")
                .<DeviceCommand, DeviceCommand>chunk(1)
                .reader(flatFileItemReader)       // 读操作
                .processor(helloItemProcessor)    // 处理操作
                .writer(flatFileItemWriter)       // 写操作
                .build();

        // 创建Job
        JobBuilderFactory jobBuilderFactory = new JobBuilderFactory(jobRepository);
        Job job = jobBuilderFactory.get("job")
                .start(step)
                .build();
        // 启动任务
        try {
            jobLauncher.run(job, new JobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }

    }
}
