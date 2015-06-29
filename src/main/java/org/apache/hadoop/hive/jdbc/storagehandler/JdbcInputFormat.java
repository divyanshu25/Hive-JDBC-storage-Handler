package org.apache.hadoop.hive.jdbc.storagehandler;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

public class JdbcInputFormat extends DBInputFormat<DbRecordWritable> {

    private boolean jobConfSet = false;

    /**
     * @see org.apache.hadoop.util.ReflectionUtils#setConf(Object, Configuration)
     */

    /**
     * @see org.apache.hadoop.hive.ql.exec.FetchOperator#getRecordReader()
     */
    @Override
    public void configure(JobConf jobConf) {
        // delay for TableJobProperties is set to the jobConf
    }

    @Override
    public RecordReader<LongWritable, DbRecordWritable> getRecordReader(InputSplit split, JobConf jobConf, Reporter reporter)
            throws IOException {
        if(!jobConfSet) {
            super.configure(jobConf);
            this.jobConfSet = true;
        }
        return super.getRecordReader(split, jobConf, reporter);
    }

    @Override
    public InputSplit[] getSplits(JobConf jobConf, int chunks) throws IOException {
        if(!jobConfSet) {
            super.configure(jobConf);
            this.jobConfSet = true;
        }
        return super.getSplits(jobConf, chunks);
    }

}