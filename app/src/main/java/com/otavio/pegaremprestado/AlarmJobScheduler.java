package com.otavio.pegaremprestado;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class AlarmJobScheduler extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {

        Log.e("onStartJOB","Alarm disparado\n"+params.getJobId());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
