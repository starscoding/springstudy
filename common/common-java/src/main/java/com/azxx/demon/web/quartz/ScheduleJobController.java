package com.azxx.demon.web.quartz;

import com.alibaba.druid.util.StringUtils;
import com.azxx.demon.service.ScheduleJobService;
import com.azxx.demon.vo.ScheduleJobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * author : benjamin
 * createTime : 2017.06.06
 * description : 定时任务控制器
 * version : 1.0
 */
@Controller("quartz")
public class ScheduleJobController {
    /** job service */
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 任务页面
     *
     * @return
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String inputScheduleJob(ScheduleJobVo scheduleJobVo, ModelMap modelMap) {

        if (scheduleJobVo.getScheduleJobId() != null) {
            ScheduleJobVo scheduleJob = scheduleJobService.get(scheduleJobVo.getScheduleJobId());
            scheduleJob.setKeywords(scheduleJobVo.getKeywords());
            modelMap.put("scheduleJobVo", scheduleJob);
        }

        return "quartz/addJob";
    }

    /**
     * 删除任务
     *
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteScheduleJob(Long scheduleJobId) {

        scheduleJobService.delete(scheduleJobId);

        return "redirect:quartz/listJob.jsp";
    }

    /**
     * 运行一次
     *
     * @return
     */
    @RequestMapping(value = "run", method = RequestMethod.GET)
    public String runOnceScheduleJob(Long scheduleJobId) {

        scheduleJobService.runOnce(scheduleJobId);

        return "redirect:quartz/listJob.jsp";
    }

    /**
     * 暂停
     *
     * @return
     */
    @RequestMapping(value = "pause", method = RequestMethod.GET)
    public String pauseScheduleJob(Long scheduleJobId) {
        scheduleJobService.pauseJob(scheduleJobId);
        return "redirect:quartz/listJob.jsp";
    }

    /**
     * 恢复
     *
     * @return
     */
    @RequestMapping(value = "resume", method = RequestMethod.GET)
    public String resumeScheduleJob(Long scheduleJobId) {
        scheduleJobService.resumeJob(scheduleJobId);
        return "redirect:quartz/listJob.jsp";
    }

    /**
     * 保存任务
     *
     * @param scheduleJobVo
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveScheduleJob(ScheduleJobVo scheduleJobVo) {

        //测试用随便设个状态
        scheduleJobVo.setStatus("1");

        if (scheduleJobVo.getScheduleJobId() == null) {
            scheduleJobService.insert(scheduleJobVo);
        } else if (StringUtils.equalsIgnoreCase(scheduleJobVo.getKeywords(),"delUpdate")){
            //直接拿keywords存一下，就不另外重新弄了
            scheduleJobService.delUpdate(scheduleJobVo);
        }else {
            scheduleJobService.update(scheduleJobVo);
        }
        return "redirect:quartz/listJob.jsp";
    }

    /**
     * 任务列表页
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listScheduleJob(ScheduleJobVo scheduleJobVo, ModelMap modelMap) {

        try {
            List<ScheduleJobVo> scheduleJobVoList = scheduleJobService.queryList(scheduleJobVo);
            modelMap.put("scheduleJobVoList", scheduleJobVoList);

            List<ScheduleJobVo> executingJobList = scheduleJobService.queryExecutingJobList();
            modelMap.put("executingJobList", executingJobList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "quartz/listJob";
    }

}
