
package com.ustory.techbox.utils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * This method define the task used to do something. Typically you should override
 * {@link #onExecute(TaskOperation)} method to do you things, on the other hand, you
 * also can override the {@link #onProgressUpdate(Object)} method to get the progress of
 * you things.
 * </p>
 *
 * <p>
 * NOTE:
 * There is an very important thing you should pay attention to, you must specify the task
 * is running on background thread or UI thread, the default flag is true ---- running on
 * background thread.
 * </p>
 *
 * @author LeeHong
 *
 * @date 2012/10/30
 */
public abstract class Task
{
    private int             mId               = 0;

    /**
     * The task name.
     */
    private String          mName             = null;

    /**
     * Indicate this task is canceled or not.
     */
    private AtomicBoolean   mCancelled        = new AtomicBoolean(false);

    /**
     * The task status, default value is {@link Status#PENDING}.
     */
    private volatile Status mStatus           = Status.PENDING;

    /**
     * The running status, default value is {@link RunningStatus#UI_THREAD}.
     */
    private volatile RunningStatus mRunStatus = RunningStatus.UI_THREAD;

    /**
     * Indicates the current status of the task. Each status will be set only once
     * during the lifetime of a task.
     */
    public enum Status
    {
        /**
         * Indicates that the task has not been executed yet.
         */
        PENDING,

        /**
         * Indicates that the task is running.
         */
        RUNNING,

        /**
         * Indicates that {@link Task#onExecute} has finished.
         */
        FINISHED,
    }

    /**
     * Indicate the task running status.
     */
    public enum RunningStatus
    {
        /**
         * Indicate the task is running in the background thread.
         */
        WORK_THREAD,

        /**
         * Indicate the task is running in the UI thread.
         */
        UI_THREAD,
    }

    public Task(Task task)
    {
        this.mRunStatus = task.mRunStatus;
        this.mName      = task.mName;
        this.mStatus    = task.mStatus;
    }

    /**
     * The constructor method.
     *
     * @param status indicate the task is running in background thread or not.
     */
    public Task(RunningStatus status)
    {
        this(status, null);
    }

    public Task(RunningStatus status, String name)
    {
        mRunStatus = status;
        mName = name;
    }

    public abstract TaskOperation onExecute(TaskOperation operation);

    /**
     * Called when change the progress, this method is running in UI thread.
     *
     * @param progresses
     */
    public void onProgressUpdate(Object progresses)
    {
    }

    /**
     * Cancel the task.
     */
    public void cancel()
    {
        mCancelled.set(true);
    }

    /**
     * Indicate the task is canceled or not.
     *
     * @return
     */
    public boolean isCancelled()
    {
        return mCancelled.get();
    }

    /**
     * Get the running status.
     *
     * @return
     */
    public RunningStatus getRunningStatus()
    {
        return mRunStatus;
    }

    /**
     * Set the name of the task.
     *
     * @param name The task name.
     */
    public void setTaskName(String name)
    {
        mName = name;
    }

    /**
     * Get the task name.
     *
     * @return the task name.
     */
    public String getTaskName()
    {
        return mName;
    }

    /**
     * Set the status of the task.
     *
     * @param status
     */
    public void setStatus(Status status)
    {
        mStatus = status;
    }

    /**
     * Get the status of the task.
     *
     * @return
     */
    public Status getStatus()
    {
        return mStatus;
    }

    /**
     * Set the id of the task.
     *
     * @param id
     */
    public void setTaskId(int id)
    {
        mId = id;
    }

    /**
     * Get the task id.
     */
    public int getTaskId()
    {
        return mId;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("name = ").append(mName).append("  ");
        sb.append("id = ").append(mId).append("  ");
        sb.append(super.toString());

        return sb.toString();
    }
}
